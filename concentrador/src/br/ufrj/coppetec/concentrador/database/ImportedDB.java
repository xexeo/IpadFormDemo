package br.ufrj.coppetec.concentrador.database;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.ufrj.coppetec.concentrador.Concentrador;

/**
 *
 * @author mangeli
 */
public class ImportedDB extends Db {

	private static Logger logger = LogManager.getLogger(ImportedDB.class);

	public ImportedDB(String path) throws Exception {
		super("org.sqlite.JDBC", "jdbc:sqlite:" + path);
	}

	public void importData() throws Exception {
		this.setStatement();
		ResultSet rs = this.executeQuery("SELECT * FROM tblDados;");
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
		sqlbase += "idPerguntaExtra";
		sqlbase += ") ";
		String sql = "";
		int counter = 0;
		while (rs.next()) {
			try {
				String idCombustivel = (rs.getString("idCombustivel") == null && rs.getString("cancelado").equals("0")) ? "3" : rs
						.getString("idCombustivel");
				sql = sqlbase + " VALUES (";
				sql += " '" + rs.getString("id") + "', ";
				sql += "0, "; // não enviado
				sql += "1, "; // esta no note
				sql += myDB.getSQLiteBoolean(rs.getString("cancelado")) + ", ";
				sql += rs.getString("idPosto") + ", ";
				sql += "'" + rs.getString("sentido") + "', ";
				sql += "'" + rs.getString("idIpad") + "', ";
				// sql += "'" + rs.getString("uuid") + "', ";
				sql += "'" + rs.getString("login") + "', ";
				sql += "'" + rs.getString("dataIniPesq") + "', ";
				sql += "'" + rs.getString("dataFimPesq") + "', ";
				sql += "'" + rs.getString("placa") + "', ";
				sql += rs.getString("anoDeFabricacao") + ", ";
				sql += "'" + rs.getString("tipo") + "', ";
				sql += rs.getString("idOrigemPais") + ", ";
				sql += rs.getString("idOrigemMunicipio") + ", ";
				sql += rs.getString("idDestinoPais") + ", ";
				sql += rs.getString("idDestinoMunicipio") + ", ";
				sql += rs.getString("idMotivoDeEscolhaDaRota") + ", ";
				sql += myDB.getIntValue(rs.getString("frequenciaQtd")) + ", ";
				sql += "'" + rs.getString("frequenciaPeriodo") + "', ";
				sql += rs.getString("idPropriedadesDoVeiculo") + ", ";
				sql += myDB.getSQLiteBoolean(rs.getString("placaEstrangeira")) + ", ";
				sql += rs.getString("idPaisPlacaEstrangeira") + ", ";
				// sql += rs.getString("idCombustivel") + ", ";
				sql += idCombustivel + ", ";
				sql += "'" + rs.getString("categoria") + "', ";
				sql += myDB.getSQLiteBoolean(rs.getString("possuiReboque")) + ", ";
				sql += myDB.getIntValue(rs.getString("numeroDePessoasNoVeiculo")) + ", ";
				sql += myDB.getIntValue(rs.getString("numeroDePessoasATrabalho")) + ", ";
				sql += rs.getString("idRendaMedia") + ", ";
				sql += rs.getString("idMotivoDaViagem") + ", ";
				sql += "'" + rs.getString("tipoCaminhao") + "', ";
				sql += rs.getString("idTipoDeContainer") + ", ";
				sql += rs.getString("idTipoCarroceria") + ", ";
				sql += "'" + rs.getString("rntrc") + "', ";
				sql += myDB.getSQLiteBoolean(rs.getString("possuiCargaPerigosa")) + ", ";
				sql += rs.getString("idNumeroDeRisco") + ", ";
				sql += rs.getString("idNumeroDaOnu") + ", ";
				sql += rs.getString("idAgregado") + ", ";
				sql += myDB.getSQLiteBoolean(rs.getString("placaVermelha")) + ", ";
				sql += rs.getString("idTipoDeViagemOuServico") + ", ";
				sql += rs.getString("pesoDaCarga") + ", ";
				sql += rs.getString("valorDoFrete") + ", ";
				sql += myDB.getSQLiteBoolean(rs.getString("utilizaParadaEspecial")) + ", ";
				sql += rs.getString("idProduto") + ", ";
				sql += rs.getString("idCargaAnterior") + ", ";
				sql += rs.getString("valorDaCarga") + ", ";
				sql += rs.getString("municipioEmbarqueCarga") + ", ";
				sql += rs.getString("idLocalEmbarqueCarga") + ", ";
				sql += rs.getString("municipioDesembarqueCarga") + ", ";
				sql += rs.getString("idLocalDesembarqueCarga") + ", ";
				sql += myDB.getSQLiteBoolean(rs.getString("indoPegarCarga")) + ", ";
				sql += rs.getString("paradaObrigatoriaMunicipio1") + ", ";
				sql += rs.getString("paradaObrigatoriaMunicipio2") + ", ";
				sql += rs.getString("idPerguntaExtra");
				sql += "); ";
				Concentrador.database.setStatement();

				counter += Concentrador.database.executeStatement(sql);
			} catch (Exception e) {
				// do nothing only ignore error
				logger.error(String.format("Erro a inserir o registro:%s%s", System.lineSeparator(), sql), e);
			}
			// System.out.println(sql);
		}
		rs.close();
		JOptionPane.showMessageDialog(null, "Total de registros inseridos: " + Integer.toString(counter));
	}
}
