package br.ufrj.coppeted.concentrador.database;

import br.ufrj.coppetec.concentrador.Concentrador;
import java.sql.ResultSet;

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
					+ "estaNoNote, "
					+ "cancelado, "
					+ "idPosto, "
					+ "sentido, "
					+ "idIpad, "
					+ "uuid, "
					+ "login, "
					+ "timestampIniPesq, "
					+ "timestampFimPesq, "
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
		while (rs.next()){
			sql = sqlbase + " VALUES ("
					+ " '" + rs.getString("id") + "', "
					+ rs.getInt("estaNoNote")  + ", "
					+ rs.getInt("cancelado")  + ", "
					+ rs.getInt("idPosto")  + ", "
					+ "'" + rs.getString("sentido") + "', "
					+ "'" + rs.getString("idIpad") + "', "
					+ "'" + rs.getString("uuid") + "', "
					+ "'" + rs.getString("login") + "', "
					+ "'" + rs.getString("timestampIniPesq") + "', "
					+ "'" + rs.getString("timestampFimPesq") + "', "
					+ "'" + rs.getString("placa") + "', "
					+ rs.getInt("anoDeFabricacao") + ", "
					+ "'" + rs.getString("tipo") + "', "
					+ rs.getInt("idOrigemPais") + ", "
					+ rs.getInt("idOrigemMunicipio") + ", "
					+ rs.getInt("idDestinoPais") + ", "
					+ rs.getInt("idDestinoMunicipio") + ", "
					+ rs.getInt("idMotivoDeEscolhaDaRota") + ", "
					+ rs.getInt("frequenciaQtd") + ", "
					+ "'" + rs.getString("frequenciaPeriodo") + "', "
					+ rs.getInt("idPropriedadesDoVeiculo") + ", "
					+ "'" + rs.getString("placaEstrangeira") + "', "
					+ rs.getInt("idPaisPlacaEstrangeira") + ", "
					+ rs.getInt("idCombustivel") + ", "
					+ "'" + rs.getString("categoria") + "', "
					+ "'" + rs.getString("possuiReboque") + "', "
					+ rs.getInt("numeroDePessoasNoVeiculo") + ", "
					+ rs.getInt("numeroDePessoasATrabalho") + ", "
					+ rs.getInt("idRendaMedia") + ", "
					+ rs.getInt("idMotivoDaViagem") + ", "
					+ "'" + rs.getString("tipoCaminhao") + "', "
					+ rs.getInt("idTipoDeContainer") + ", "
					+ rs.getInt("idTipoCarroceria") + ", "
					+ "'" + rs.getString("rntrc") + "', "
					+ rs.getInt("possuiCargaPerigosa") + ", "
					+ rs.getInt("idNumeroDeRisco") + ", "
					+ rs.getInt("idNumeroDaOnu") + ", "
					+ rs.getInt("idAgregado") + ", "
					+ rs.getInt("placaVermelha") + ", "
					+ rs.getInt("idTipoDeViagemOuServico") + ", "
					+ rs.getFloat("pesoDaCarga") + ", "
					+ rs.getFloat("valorDoFrete") + ", "
					+ rs.getInt("utilizaParadaEspecial") + ", "
					+ rs.getInt("idProduto") + ", "
					+ rs.getInt("idCargaAnterior") + ", "
					+ rs.getFloat("valorDaCarga") + ", "
					+ rs.getInt("municipioEmbarqueCarga") + ", "
					+ rs.getInt("idLocalEmbarqueCarga") + ", "
					+ rs.getInt("municipioDesembarqueCarga") + ", "
					+ rs.getInt("idLocalDesembarqueCarga") + ", "
					+ rs.getInt("indoPegarCarga") + ", "
					+ rs.getInt("paradaObrigatoriaMunicipio1") + ", "
					+ rs.getInt("paradaObrigatoriaMunicipio2") + ", "
					+ rs.getInt("idPerguntaExtra") 
					+ "); ";
			Concentrador.database.setStatement();
			Concentrador.database.executeStatement(sql);
		}
	}
}
