package br.ufrj.coppetec.concentrador.database;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.ufrj.coppetec.concentrador.Concentrador;
import br.ufrj.coppetec.concentrador.Janela;
import br.ufrj.coppetec.concentrador.Util;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;


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
	
	public int importData() throws Exception {
		this.openTransaction();
		this.counter = importData(Concentrador.database);
		this.commit();
		//final JDialog dialog = new JDialog(this.janela, "Dialog", ModalityType.APPLICATION_MODAL);
		//JOptionPane.showMessageDialog(this.janela, "Total de registros inseridos: " + Integer.toString(this.counter));
		logger.debug("Novos registros: "+Integer.toString(this.counter));
		return counter;
	}

	public int importData(myDB concentradorDb) throws Exception {
		Db db = this;
		SwingWorker<Integer, Void> mySwingWorker = new SwingWorker<Integer, Void>(){
         
			@Override
			protected Integer doInBackground() throws Exception {
				db.setStatement();
				ResultSet rs = db.executeQuery("SELECT * FROM tblDados;");
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
				sqlbase += "duracaoPesq";
				sqlbase += ") ";
				String sql = "";
				String idCombustivel = "";
				boolean placaEstrangeira;
				int counter=0;
				while (rs.next()) {
					try {
						idCombustivel = (rs.getString("idCombustivel") == null && rs.getString("cancelado").equals("0")) ? "3" : rs
								.getString("idCombustivel");
						placaEstrangeira = (Util.getSQLiteBoolean(rs.getString("placaEstrangeira")) == "1");
						sql = sqlbase + " VALUES (";
						sql += " '" + rs.getString("id") + "', ";
						sql += "0, "; // não enviado
						sql += "1, "; // esta no note
						sql += Util.getSQLiteBoolean(rs.getString("cancelado")) + ", ";
						sql += rs.getString("idPosto") + ", ";
						sql += "'" + rs.getString("sentido") + "', ";
						sql += "'" + rs.getString("idIpad") + "', ";
						// sql += "'" + rs.getString("uuid") + "', ";
						sql += "'" + rs.getString("login") + "', ";
						sql += "'" + rs.getString("dataIniPesq") + "', ";
						sql += "'" + rs.getString("dataFimPesq") + "', ";
						if (placaEstrangeira){
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
						sql += Util.getSQLiteIntLimited(rs.getString("numeroDePessoasNoVeiculo"), "numeroDePessoasNoVeiculo") + ", ";
						sql += Util.getSQLiteIntLimited(rs.getString("numeroDePessoasATrabalho"), "numeroDePessoasATrabalho") + ", ";
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
						sql += Util.getSQLiteRealLimited(rs.getString("valorDoFrete"),"valorDoFrete") + ", ";
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
						sql += rs.getString("idPerguntaExtra")+ ", ";
						sql += rs.getString("duracaoPesq");
						sql += "); ";
						concentradorDb.setStatement();

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
	
	public int getCounter(){
		return counter;
	}
}
