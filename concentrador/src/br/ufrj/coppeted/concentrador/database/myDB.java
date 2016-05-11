package br.ufrj.coppeted.concentrador.database;

/**
 *
 * @author mangeli
 */
public class myDB extends Db{
	
	public myDB() throws Exception {
        super("org.sqlite.JDBC", "jdbc:sqlite:dados.db");
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
