package br.ufrj.coppetec.concentrador.database;

import br.ufrj.coppetec.concentrador.Concentrador;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author mangeli
 */
public class ImportedDB extends Db{
	public ImportedDB(String path) throws Exception {
		super("org.sqlite.JDBC", "jdbc:sqlite:" + path);
	}
	
	public void importData()throws Exception{
		this.setStatement();
		ResultSet rs = this.executeQuery("SELECT * FROM tblDados;");
		String sqlbase = "INSERT OR IGNORE INTO odTable ("
					+ "id, "
					+ "enviado, "
					+ "estaNoNote, "
					+ "cancelado, "
					+ "idPosto, "
					+ "sentido, "
					+ "idIpad, "
					//+ "uuid, "
					+ "login, "
					+ "dataIniPesq, "
					+ "dataFimPesq, "
					+ "placa, "
					+ "anoDeFabricacao, "
					+ "tipo, "
					+ "idOrigemPais, "
					+ "idOrigemMunicipio, "
					+ "idDestinoPais, "
					+ "idDestinoMunicipio, "
					+ "idMotivoDeEscolhaDaRota, "
					+ "frequenciaQtd, "
					+ "frequenciaPeriodo, "
					+ "idPropriedadesDoVeiculo, "
					+ "placaEstrangeira, "
					+ "idPaisPlacaEstrangeira, "
					+ "idCombustivel, "
					+ "categoria, "
					+ "possuiReboque, "
					+ "numeroDePessoasNoVeiculo, "
					+ "numeroDePessoasATrabalho, "
					+ "idRendaMedia, "
					+ "idMotivoDaViagem, "
					+ "tipoCaminhao, "
					+ "idTipoDeContainer, "
					+ "idTipoCarroceria, "
					+ "rntrc, "
					+ "possuiCargaPerigosa, "
					+ "idNumeroDeRisco, "
					+ "idNumeroDaOnu, "
					+ "idAgregado, "
					+ "placaVermelha, "
					+ "idTipoDeViagemOuServico, "
					+ "pesoDaCarga, "
					+ "valorDoFrete, "
					+ "utilizaParadaEspecial, "
					+ "idProduto, "
					+ "idCargaAnterior, "
					+ "valorDaCarga, "
					+ "municipioEmbarqueCarga, "
					+ "idLocalEmbarqueCarga, "
					+ "municipioDesembarqueCarga, "
					+ "idLocalDesembarqueCarga, "
					+ "indoPegarCarga, "
					+ "paradaObrigatoriaMunicipio1, "
					+ "paradaObrigatoriaMunicipio2, "
					+ "idPerguntaExtra"
					+ ") ";
		String sql;
		int counter = 0;
		while (rs.next()){
			sql = sqlbase + " VALUES ("
					+ " '" + rs.getString("id") + "', "
					+ "0," //não enviado
					+ "1, " //esta no note
					+ myDB.getSQLiteBoolean(rs.getString("cancelado"))  + ", "
					+ rs.getString("idPosto")  + ", "
					+ "'" + rs.getString("sentido") + "', "
					+ "'" + rs.getString("idIpad") + "', "
					//+ "'" + rs.getString("uuid") + "', "
					+ "'" + rs.getString("login") + "', "
					+ "'" + rs.getString("dataIniPesq") + "', "
					+ "'" + rs.getString("dataFimPesq") + "', "
					+ "'" + rs.getString("placa") + "', "
					+ rs.getString("anoDeFabricacao") + ", "
					+ "'" + rs.getString("tipo") + "', "
					+ rs.getString("idOrigemPais") + ", "
					+ rs.getString("idOrigemMunicipio") + ", "
					+ rs.getString("idDestinoPais") + ", "
					+ rs.getString("idDestinoMunicipio") + ", "
					+ rs.getString("idMotivoDeEscolhaDaRota") + ", "
					+ rs.getString("frequenciaQtd") + ", "
					+ "'" + rs.getString("frequenciaPeriodo") + "', "
					+ rs.getString("idPropriedadesDoVeiculo") + ", "
					+ myDB.getSQLiteBoolean(rs.getString("placaEstrangeira")) + ", "
					+ rs.getString("idPaisPlacaEstrangeira") + ", "
					+ rs.getString("idCombustivel") + ", "
					+ "'" + rs.getString("categoria") + "', "
					+ myDB.getSQLiteBoolean(rs.getString("possuiReboque")) + ", "
					+ rs.getString("numeroDePessoasNoVeiculo") + ", "
					+ rs.getString("numeroDePessoasATrabalho") + ", "
					+ rs.getString("idRendaMedia") + ", "
					+ rs.getString("idMotivoDaViagem") + ", "
					+ "'" + rs.getString("tipoCaminhao") + "', "
					+ rs.getString("idTipoDeContainer") + ", "
					+ rs.getString("idTipoCarroceria") + ", "
					+ "'" + rs.getString("rntrc") + "', "
					+ myDB.getSQLiteBoolean(rs.getString("possuiCargaPerigosa")) + ", "
					+ rs.getString("idNumeroDeRisco") + ", "
					+ rs.getString("idNumeroDaOnu") + ", "
					+ rs.getString("idAgregado") + ", "
					+ myDB.getSQLiteBoolean(rs.getString("placaVermelha")) + ", "
					+ rs.getString("idTipoDeViagemOuServico") + ", "
					+ rs.getString("pesoDaCarga") + ", "
					+ rs.getString("valorDoFrete") + ", "
					+ myDB.getSQLiteBoolean(rs.getString("utilizaParadaEspecial")) + ", "
					+ rs.getString("idProduto") + ", "
					+ rs.getString("idCargaAnterior") + ", "
					+ rs.getString("valorDaCarga") + ", "
					+ rs.getString("municipioEmbarqueCarga") + ", "
					+ rs.getString("idLocalEmbarqueCarga") + ", "
					+ rs.getString("municipioDesembarqueCarga") + ", "
					+ rs.getString("idLocalDesembarqueCarga") + ", "
					+ myDB.getSQLiteBoolean(rs.getString("indoPegarCarga")) + ", "
					+ rs.getString("paradaObrigatoriaMunicipio1") + ", "
					+ rs.getString("paradaObrigatoriaMunicipio2") + ", "
					+ rs.getString("idPerguntaExtra") 
					+ "); ";
			Concentrador.database.setStatement();
			counter += Concentrador.database.executeStatement(sql);
			//System.out.println(sql);
		}
		rs.close();
		JOptionPane.showMessageDialog(null, "Total de registros inseridos: " + Integer.toString(counter));
	}
}
