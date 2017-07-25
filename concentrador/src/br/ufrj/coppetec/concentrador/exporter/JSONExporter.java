/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import br.ufrj.coppetec.concentrador.database.myDB;

/**
 *
 * @author mangeli
 */
public class JSONExporter {

	private static Logger logger = LogManager.getLogger(JSONExporter.class);

	private File file;
	private File tmpFile;
	private DbTable table;
	private Janela janela;

	static public enum WhatToExport {
		ALL, NOT_SENT, DATE
	}

	private JSONBuilder jsonBuilder;

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

	public void export(Integer posto) {
		export(posto, null);
	}

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

	private Boolean backgroundExport(Integer posto) {
		return backgroundExport(posto, null);
	}

	private Boolean backgroundExport(Integer posto, String date) {
		myDB database = null;
		ResultSet result;
		FileWriter writer;
		try {
			String qry = this.table.sql(posto);

			if (date != null) {
				if (this.table.text.equals(myDB.TABLE_NAME_OD)) {
					qry += " AND dataIniPesq like '" + date + "%';";
				} else {
					qry += " AND data like '" + date + "%';";
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

	static public Object getJSONInteger(String val) {
		Object r;
		if (val == null) {
			r = JSONObject.NULL;
		} else {
			r = new Integer(val);
		}

		return r;
	}

	static public Object getJSONDouble(String val) {
		Object r;
		if (val == null) {
			r = JSONObject.NULL;
		} else {
			r = JSONObject.stringToValue(val);
		}
		return r;
	}

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
