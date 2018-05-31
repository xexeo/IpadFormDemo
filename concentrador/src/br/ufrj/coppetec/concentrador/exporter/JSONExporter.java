package br.ufrj.coppetec.concentrador.exporter;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufrj.coppetec.concentrador.Janela;
import static br.ufrj.coppetec.concentrador.Util.TODAS;
import br.ufrj.coppetec.concentrador.database.myDB;

/**
 * Exporta dados presentes no banco interno da aplicação para arquivos contendo a representação JSON das pesquisas.
 * A representação JSON dos dados atende as especificações necessárias ao envio para o servidor de centralização das pesquisas.
 * 
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public class JSONExporter {

	private static Logger logger = LogManager.getLogger(JSONExporter.class);

	private File file;			///< Arquivo destino
	private File tmpFile;		///< Arquivo temporário necessário ao processamento intermediário da exportação
	private DbTable table;		///< Tabela que contém os dados a serem exportados
	private Janela janela;		///< Referência a tela principal da aplicação

	/**
	 * Enumeração utilizada para controlar o que será exportado.
	 * Todas as pesquisas, as não enviadas ou de determinada data
	 */
	static public enum WhatToExport {
		ALL, NOT_SENT, DATE
	}

	private JSONBuilder jsonBuilder; ///< Referência para um construtor de representação JSON

	/**
	 * Enumeração utilizada para controlar a tabela originária dos dados exportados.
	 */
	static public enum DbTable {
		PV("voltable"), OD("odtable");
		private final String text;

		DbTable(final String s) {
			this.text = s;
		}

		public String toString() {
			return text;
		}

		public String sql(Integer posto) throws ParseException {
			String r = "SELECT * FROM ";
			if (text.equals(myDB.TABLE_NAME_OD)) {
				r += "odtable WHERE cancelado=0 and idPosto=" + posto.intValue();
			} else {
				r += "voltable WHERE posto=" + posto.intValue();
			}
			r += " AND " + myDB.getConditionByValidDate(text);
			return r;
		}

	}

	/**
	 * Constrói e configura um objeto de exportação de dados.
	 * @param f			referência para o arquivo onde os dados exportados serão armazenados
	 * @param t			tabela de onde os dados serão exportados
	 * @param janela	referência para a tela principal do sistema
	 */
	public JSONExporter(File f, DbTable t, Janela janela) {
		this.file = f;
		this.table = t;
		this.janela = janela;
		this.tmpFile = new File("temp.json");
		switch (t) {
		case PV:
			jsonBuilder = new PvJSONbuilder();
			break;
		case OD:
			jsonBuilder = new OdJSONbuilder();
			break;
		}
	}

	/**
	* Exporta os dados de um posto de pesquisa.
	 * @param posto identificação do posto de pesquisa
	*/
	public void export(Integer posto) {
		export(posto, null);
	}

	/**
	 * Exporta os dados de um posto de pesquisa em determinada data.
	 * @param posto	identificação do posto de pesquisa
	 * @param date	data das pesquisas a serem exportadas
	 */
	public void export(Integer posto, String date) {

		SwingWorker<Boolean, Void> mySwingWorker = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				return JSONExporter.this.backgroundExport(posto, date);
			}

		};

		final JDialog dialog = new JDialog(this.janela, "Dialog", Dialog.ModalityType.APPLICATION_MODAL);

		mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("state")) {
					if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
						dialog.dispose();
						try {
							if (mySwingWorker.get() == true) {
								String msgSucesso = "Arquivo " + JSONExporter.this.file.getName() + " exportado com sucesso.";
								JOptionPane.showMessageDialog(janela, msgSucesso,
										"Exportação de dados.", JOptionPane.INFORMATION_MESSAGE);
								logger.info (msgSucesso);
							}
						} catch (Exception e) {
							logger.error("Error exportando dados: ", e);
						}

					}
				}
			}

		});

		mySwingWorker.execute();

		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(progressBar, BorderLayout.CENTER);
		panel.add(new JLabel("Aguarde a exportação dos dados......."), BorderLayout.PAGE_START);
		dialog.add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(this.janela);
		dialog.setVisible(true);

	}

	/**
	 * Exporta os dados de um posto de pesquisa.
	 * Método para execução em segunto plano
	 * @param posto identificação do posto de pesquisa
	 * @return		true se a exportação ocorreu sem problemas
	 */
	private Boolean backgroundExport(Integer posto) {
		return backgroundExport(posto, null);
	}

	/**
	 * Exporta os dados de um posto de pesquisa em determinada data.
	 * Método para execução em segundo plano
	 * @param posto identificação do posto de pesquisa
	 * @param date	data das pesquisas a serem exportadas
	 * @return		true se a exportação ocorreu sem problemas
	 */
	private Boolean backgroundExport(Integer posto, String date) {
		myDB database = null;
		ResultSet result;
		FileWriter writer;
		try {
			String qry = this.table.sql(posto);

			if (date != null) {
				if (date.equals(TODAS)){
					qry += " AND " + myDB.getConditionByValidDate(this.table.toString()) + ";";
				} else {
					if (this.table.text.equals(myDB.TABLE_NAME_OD)) {
						qry += " AND dataIniPesq like '" + date + "%';";
					} else {
						qry += " AND data like '" + date + "%';";
					}
				}
				
			}

			database = myDB.getInstance();
			database.openTransaction();
			database.setStatement();
			result = database.executeQuery(qry);

			// writer = new FileWriter(file);
			writer = new FileWriter(tmpFile);
			writer.write(jsonBuilder.build(result));
			result.close();
			writer.close();
			Zipper zipper = new Zipper(tmpFile, file);
			zipper.zipIt();
			database.executeStatement("UPDATE " + this.table.toString() + " SET enviado=1 WHERE enviado=0;");
			database.commit();
			tmpFile.delete();
		} catch (JSONException je) {
			logger.warn("Não existem dados para exportação", je);
			// probably empty table
			JOptionPane.showMessageDialog(janela, "Não existem dados para exportação.", "Exportação de dados.",
					JOptionPane.INFORMATION_MESSAGE);
			if (database != null)
				database.rollback();
			return false;
		} catch (Exception e) {
			logger.error("Erro ao exportar dados.", e);
			JOptionPane.showMessageDialog(janela, "Erro ao exportar dados:\n" + e.getMessage(), "Erro na exportação de dados.",
					JOptionPane.ERROR_MESSAGE);
			if (database != null)
				database.rollback();
			return false;
		}

		return true;
	}

	/**
	 * Converte um valor numérico inteiro para sua representação na forma de um objeto
	 * @param val	valor a ser convertido
	 * @return		objeto retornado
	 */
	static public Object getJSONInteger(String val) {
		Object r;
		if (val == null) {
			r = JSONObject.NULL;
		} else {
			r = new Integer(val);
		}

		return r;
	}

	/**
	 * Converte um valor numérico real para sua representação na forma de um objeto
	 * @param val	valor a ser convertido
	 * @return		objeto retornado
	 */
	static public Object getJSONDouble(String val) {
		Object r;
		if (val == null) {
			r = JSONObject.NULL;
		} else {
			r = JSONObject.stringToValue(val);
		}
		return r;
	}

	/**
	 * Converte uma _cadeia de caracteres_ para sua representação adequada para a inclusão em um objeto JSON
	 * @param val	_cadeia de caracteres_ a ser convertida
	 * @return		objeto retornado
	 */
	static public Object getJSONString(String val) {
		Object r;
		if (val == null || val.equals("null")) {
			r = JSONObject.NULL;
		} else {
			r = val;
		}

		return r;
	}

}
