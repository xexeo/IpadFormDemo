package br.ufrj.coppeted.concentrador.database;



/**
 *
 * @author mangeli
 */
public class myDB extends Db{
	
	public myDB() throws Exception {
        super("org.sqlite.JDBC", "jdbc:sqlite:dados.db");
    }
	
	public void createVolTable() throws Exception{
		this.setStatement();
        String qry = "CREATE TABLE IF NOT EXISTS voltable "
                + " (id int, "
				+ "enviado int, "
				+ "posto int, "
				+ "pista text, "
				+ "data text, "
                + "hora int, "
				+ "sentido text, "
				+ "local text, "
				+ "pesquisador text, "
				+ "origemipad int, " //não faz sentido
				+ "_P1 text, "
				+ "_P2 text, "
				+ "_P3 text, "
				+ "_M text, "
				+ "_2CB text, "
				+ "_3CB text, "
				+ "_4CB text, "
				+ "_2C text, "
				+ "_3C text, "
				+ "_4C text, "
				+ "_4CD text, "
				+ "_3D text, "
				+ "_2S1 text, "
				+ "_2S2 text, "
				+ "_2S3 text, "
				+ "_3S1 text, "
				+ "_3S2 text, "
				+ "_3S3 text, "
				+ "_3T4 text, "
				+ "_3T6 text, "
				+ "_3R6 text, "
				+ "_3V5 text, "
				+ "_3M6 text, "
				+ "_3Q4 text, "
				+ "_2C2 text, "
				+ "_3C2 text, "
				+ "_3C3 text, "
				+ "_3D4 text "
				+ "); ";
       this.executeStatement(qry);
	}
	
	public void createODTable() throws Exception{
		this.setStatement();
        String qry = "CREATE TABLE IF NOT EXISTS odTable ("
					+ "id text primary key, "
					+ "estaNoNote integer, "
					+ "cancelado integer, "
					+ "idPosto integer, "
					+ "sentido text, "
					+ "idIpad text, "
					+ "uuid text, "
					+ "login text, "
					+ "timestampIniPesq text, "
					+ "timestampFimPesq text, "
					+ "placa text, "
					+ "anoDeFabricacao integer, "
					+ "tipo text, "
					+ "idOrigemPais integer, "
					+ "idOrigemMunicipio integer, "
					+ "idDestinoPais integer, "
					+ "idDestinoMunicipio integer, "
					+ "idMotivoDeEscolhaDaRota text, "
					+ "frequenciaQtd integer, "
					+ "frequenciaPeriodo text, "
					+ "idPropriedadesDoVeiculo integer, "
					+ "placaEstrangeira text, "
					+ "idPaisPlacaEstrangeira integer, "
					+ "idCombustivel integer, "
					+ "categoria text, "
					+ "possuiReboque text, "
					+ "numeroDePessoasNoVeiculo integer, "
					+ "numeroDePessoasATrabalho integer, "
					+ "idRendaMedia integer, "
					+ "idMotivoDaViagem integer, "
					+ "tipoCaminhao text, "
					+ "idTipoDeContainer integer, "
					+ "idTipoCarroceria integer, "
					+ "rntrc text, "
					+ "possuiCargaPerigosa integer, "
					+ "idNumeroDeRisco integer, "
					+ "idNumeroDaOnu integer, "
					+ "idAgregado integer, "
					+ "placaVermelha integer, "
					+ "idTipoDeViagemOuServico integer, "
					+ "pesoDaCarga real, "
					+ "valorDoFrete real, "
					+ "utilizaParadaEspecial integer, "
					+ "idProduto integer, "
					+ "idCargaAnterior integer, "
					+ "valorDaCarga real, "
					+ "municipioEmbarqueCarga integer, "
					+ "idLocalEmbarqueCarga integer, "
					+ "municipioDesembarqueCarga integer, "
					+ "idLocalDesembarqueCarga integer, "
					+ "indoPegarCarga integer, "
					+ "paradaObrigatoriaMunicipio1 integer, "
					+ "paradaObrigatoriaMunicipio2 integer, "
					+ "idPerguntaExtra integer"
					+ "); ";
		this.executeStatement(qry);
	}
    
}
