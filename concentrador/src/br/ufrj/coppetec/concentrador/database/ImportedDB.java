package br.ufrj.coppetec.concentrador.database;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.ufrj.coppetec.concentrador.Concentrador;
import br.ufrj.coppetec.concentrador.Janela;
import br.ufrj.coppetec.concentrador.Util;

/**
 *
 * @author mangeli
 */
public class ImportedDB extends Db {

	private static Logger logger = LogManager.getLogger(ImportedDB.class);
	private Janela janela;
	private int counter;

	public ImportedDB(String path, Janela janela) throws Exception {
		super("org.sqlite.JDBC", "jdbc:sqlite:" + path);
		this.janela = janela;
		counter = 0;
	}

	public void sanitize() throws Exception {
		openTransaction();
		this.executeStatement("DELETE FROM tblDados WHERE id is null or id = 'null';");
		commit();
	}

	public int importData() throws Exception {
		this.sanitize();
		this.openTransaction();
		this.counter = importData(Concentrador.database);
		this.commit();
		// final JDialog dialog = new JDialog(this.janela, "Dialog", ModalityType.APPLICATION_MODAL);
		// JOptionPane.showMessageDialog(this.janela, "Total de registros inseridos: " + Integer.toString(this.counter));
		logger.debug("Novos registros: " + Integer.toString(this.counter));
		return counter;
	}

	public int importData(myDB concentradorDb) throws Exception {
		Db db = this;
		this.sanitize();
		SwingWorker<Integer, Void> mySwingWorker = new SwingWorker<Integer, Void>() {

			@Override
			protected Integer doInBackground() throws Exception {
				db.setStatement();
				// String queryToImport = String.format("SELECT * FROM tblDados WHERE treinamento = %d;",
				// (Concentrador.treinamento ? 1 : 0));
				/* Outra possibilidade para a mesma consulta acima: */
				// String queryToImport = String.format("SELECT * FROM tblDados WHERE %s;",
				// myDB.getConditionByValidDate(myDB.TABLE_NAME_OD));
				/* Mas como o ideal é importar tudo, e deixar que o concentrador exiba as informações conforme o contexto. */
				String queryToImport = "SELECT * FROM tblDados;";

				ResultSet rs = db.executeQuery(queryToImport);
				String sqlbase = "INSERT OR IGNORE INTO odTable (";

				sqlbase += "id, ";
				sqlbase += "enviado, ";
				sqlbase += "estaNoNote, ";
				sqlbase += "cancelado, ";
				sqlbase += "idPosto, ";
				sqlbase += "sentido, ";
				sqlbase += "idIpad, ";
				// sqlbase += "uuid, "
				sqlbase += "login, ";
				sqlbase += "dataIniPesq, ";
				sqlbase += "dataFimPesq, ";
				sqlbase += "placa, ";
				sqlbase += "anoDeFabricacao, ";
				sqlbase += "tipo, ";
				sqlbase += "idOrigemPais, ";
				sqlbase += "idOrigemMunicipio, ";
				sqlbase += "idDestinoPais, ";
				sqlbase += "idDestinoMunicipio, ";
				sqlbase += "idMotivoDeEscolhaDaRota, ";
				sqlbase += "frequenciaQtd, ";
				sqlbase += "frequenciaPeriodo, ";
				sqlbase += "idPropriedadesDoVeiculo, ";
				sqlbase += "placaEstrangeira, ";
				sqlbase += "idPaisPlacaEstrangeira, ";
				sqlbase += "idCombustivel, ";
				sqlbase += "categoria, ";
				sqlbase += "possuiReboque, ";
				sqlbase += "numeroDePessoasNoVeiculo, ";
				sqlbase += "numeroDePessoasATrabalho, ";
				sqlbase += "idRendaMedia, ";
				sqlbase += "idMotivoDaViagem, ";
				sqlbase += "tipoCaminhao, ";
				sqlbase += "idTipoDeContainer, ";
				sqlbase += "idTipoCarroceria, ";
				sqlbase += "rntrc, ";
				sqlbase += "possuiCargaPerigosa, ";
				sqlbase += "idNumeroDeRisco, ";
				sqlbase += "idNumeroDaOnu, ";
				sqlbase += "idAgregado, ";
				sqlbase += "placaVermelha, ";
				sqlbase += "idTipoDeViagemOuServico, ";
				sqlbase += "pesoDaCarga, ";
				sqlbase += "valorDoFrete, ";
				sqlbase += "utilizaParadaEspecial, ";
				sqlbase += "idProduto, ";
				sqlbase += "idCargaAnterior, ";
				sqlbase += "valorDaCarga, ";
				sqlbase += "municipioEmbarqueCarga, ";
				sqlbase += "idLocalEmbarqueCarga, ";
				sqlbase += "municipioDesembarqueCarga, ";
				sqlbase += "idLocalDesembarqueCarga, ";
				sqlbase += "indoPegarCarga, ";
				sqlbase += "paradaObrigatoriaMunicipio1, ";
				sqlbase += "paradaObrigatoriaMunicipio2, ";
				sqlbase += "idPerguntaExtra, ";
				sqlbase += "duracaoPesq, ";
				sqlbase += "treinamento";
				// IMPORTANTE: se adicionar mais campos ao final, não esquecer do separador no campo anterior
				sqlbase += ") ";
				String sql = "";
				String idCombustivel = "";
				String treinamento = "";
				String cancelado = "";
				boolean placaEstrangeira;
				int counter = 0;
				int counterTraining = 0;
				while (rs.next()) {
					try {

						cancelado = Util.getSQLiteBoolean(rs.getString("cancelado"));
						idCombustivel = (rs.getString("idCombustivel") == null && cancelado.equals("0")) ? "3"
								: rs.getString("idCombustivel");
						placaEstrangeira = Util.getSQLiteBoolean(rs.getString("placaEstrangeira")).equals("1");
						treinamento = Util.getSQLiteBoolean(rs.getString("treinamento"));
						sql = sqlbase + " VALUES (";
						sql += " '" + rs.getString("id") + "', ";
						sql += "0, "; // não enviado
						sql += "1, "; // esta no note
						sql += cancelado + ", ";
						sql += rs.getString("idPosto") + ", ";
						sql += "'" + rs.getString("sentido") + "', ";
						sql += "'" + rs.getString("idIpad") + "', ";
						// sql += "'" + rs.getString("uuid") + "', ";
						sql += "'" + rs.getString("login") + "', ";
						sql += "'" + rs.getString("dataIniPesq") + "', ";
						sql += "'" + rs.getString("dataFimPesq") + "', ";
						if (placaEstrangeira) {
							sql += "'" + Util.getStringLimited(rs.getString("placa"), "placaEstrangeira") + "', ";
						} else {
							sql += "'" + rs.getString("placa") + "', ";
						}
						sql += rs.getString("anoDeFabricacao") + ", ";
						sql += "'" + rs.getString("tipo") + "', ";
						sql += rs.getString("idOrigemPais") + ", ";
						sql += rs.getString("idOrigemMunicipio") + ", ";
						sql += rs.getString("idDestinoPais") + ", ";
						sql += rs.getString("idDestinoMunicipio") + ", ";
						sql += rs.getString("idMotivoDeEscolhaDaRota") + ", ";
						sql += Util.getSQLiteIntLimited(rs.getString("frequenciaQtd"), "frequenciaQtd") + ", ";
						sql += "'" + rs.getString("frequenciaPeriodo") + "', ";
						sql += rs.getString("idPropriedadesDoVeiculo") + ", ";
						sql += Util.getSQLiteBoolean(rs.getString("placaEstrangeira")) + ", ";
						sql += rs.getString("idPaisPlacaEstrangeira") + ", ";
						// sql += rs.getString("idCombustivel") + ", ";
						sql += idCombustivel + ", ";
						sql += "'" + rs.getString("categoria") + "', ";
						sql += Util.getSQLiteBoolean(rs.getString("possuiReboque")) + ", ";
						sql += Util.getSQLiteIntLimited(rs.getString("numeroDePessoasNoVeiculo"), "numeroDePessoasNoVeiculo")
								+ ", ";
						sql += Util.getSQLiteIntLimited(rs.getString("numeroDePessoasATrabalho"), "numeroDePessoasATrabalho")
								+ ", ";
						sql += rs.getString("idRendaMedia") + ", ";
						sql += rs.getString("idMotivoDaViagem") + ", ";
						sql += "'" + rs.getString("tipoCaminhao") + "', ";
						sql += rs.getString("idTipoDeContainer") + ", ";
						sql += rs.getString("idTipoCarroceria") + ", ";
						sql += "'" + rs.getString("rntrc") + "', ";
						sql += Util.getSQLiteBoolean(rs.getString("possuiCargaPerigosa")) + ", ";
						sql += rs.getString("idNumeroDeRisco") + ", ";
						sql += rs.getString("idNumeroDaOnu") + ", ";
						sql += rs.getString("idAgregado") + ", ";
						sql += Util.getSQLiteBoolean(rs.getString("placaVermelha")) + ", ";
						sql += rs.getString("idTipoDeViagemOuServico") + ", ";
						sql += Util.getSQLiteRealLimited(rs.getString("pesoDaCarga"), "pesoDaCarga") + ", ";
						sql += Util.getSQLiteRealLimited(rs.getString("valorDoFrete"), "valorDoFrete") + ", ";
						sql += Util.getSQLiteBoolean(rs.getString("utilizaParadaEspecial")) + ", ";
						sql += rs.getString("idProduto") + ", ";
						sql += rs.getString("idCargaAnterior") + ", ";
						sql += Util.getSQLiteRealLimited(rs.getString("valorDaCarga"), "valorDaCarga") + ", ";
						sql += rs.getString("municipioEmbarqueCarga") + ", ";
						sql += rs.getString("idLocalEmbarqueCarga") + ", ";
						sql += rs.getString("municipioDesembarqueCarga") + ", ";
						sql += rs.getString("idLocalDesembarqueCarga") + ", ";
						sql += Util.getSQLiteBoolean(rs.getString("indoPegarCarga")) + ", ";
						sql += rs.getString("paradaObrigatoriaMunicipio1") + ", ";
						sql += rs.getString("paradaObrigatoriaMunicipio2") + ", ";
						sql += rs.getString("idPerguntaExtra") + ", ";
						sql += rs.getString("duracaoPesq") + ", ";
						sql += treinamento;
						// IMPORTANTE: se adicionar mais campos ao final, não esquecer do separador no campo anterior
						sql += "); ";
						concentradorDb.setStatement();

						if (treinamento.equals("1")) {
							counterTraining++;
						}
						counter += concentradorDb.executeStatement(sql);
					} catch (Exception e) {
						// do nothing only ignore error
						logger.error(String.format("Erro a inserir o registro:%s%s", System.lineSeparator(), sql), e);
					}
					// System.out.println(sql);
				}
				rs.close();
				return counter;
			}

		};

		final JDialog dialog = new JDialog(janela, "Aguarde", ModalityType.APPLICATION_MODAL);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("state")) {
					if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
						dialog.dispose();
					}
				}
			}

		});
		mySwingWorker.execute();

		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(progressBar, BorderLayout.CENTER);
		panel.add(new JLabel("Aguarde a importação dos dados......."), BorderLayout.PAGE_START);
		dialog.add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(janela);
		dialog.setVisible(true);

		return mySwingWorker.get();
	}

	public int getCounter() {
		return counter;
	}
}
