package br.ufrj.coppetec.concentrador.database;

import java.sql.ResultSet;

/**
 *
 * @author mangeli
 */
public class myDB extends Db{
	
	
	
	public myDB() throws Exception {
        super("org.sqlite.JDBC", "jdbc:sqlite:dados.db");
    }
	
	public int verifyPV(PVregister reg)throws Exception{
		this.setStatement();
		String qry = "SELECT * FROM voltable WHERE posto = " + Integer.toString(reg.posto)
					//+ " AND pista='" + reg.pista + "'"
					+ " AND data='" + reg.data + "'"
					+ " AND hora=" + Integer.toString(reg.hora)
					+ " AND sentido='" + reg.sentido + "'"
					+ " AND posto='" + Integer.toString(reg.posto) + "'"
					//+ " AND pesquisador1='" + reg.pesquisador1 + "'"
					//+ " AND pesquisador2='" + reg.pesquisador2 + "'"
					;
		ResultSet result = this.executeQuery(qry);
		int r = (result.next())? result.getInt("id") : 0;
		result.close();
		return r;
	}
	
	public void updatePV(PVregister reg, int id)throws Exception{
		String sql = "UPDATE voltable SET "
				+ "enviado=" + Integer.toString(reg.enviado) + ","
				+ "posto=" + Integer.toString(reg.posto) + ","
				+ "pista=" + "'" + reg.pista + "', "
				+ "data=" + "'" + reg.data + "',"
				+ "hora=" + Integer.toString(reg.hora) + ", "
				+ "sentido=" + "" + "'" + reg.sentido + "', "
				+ "local="+ "'" + reg.local + "', "
				+ "pesquisador1=" + "'" + reg.pesquisador1 + "', "
				+ "pesquisador2="+ "'" + reg.pesquisador2 + "', "
				+ "P1=" + "'" + reg.p1 + "', "
				+ "P2=" + "'" + reg.p2 + "', "
				+ "P3=" + "'" + reg.p3 + "', "
				+ "M=" + "'" + reg.m + "', "
				+ "O1=" + "'" + reg.o1 + "', "
				+ "O2=" + "'" + reg.o2 + "',"
				+ "O3=" + "'" + reg.o3 + "', "
				+ "C1=" + "'" + reg.c1 + "', "
				+ "C2=" + "'" + reg.c2 + "', "
				+ "C3=" + "'" + reg.c3 + "', "
				+ "C4=" + "'" + reg.c4 + "', "
				+ "C5=" + "'" + reg.c5 + "', "
				+ "S1=" + "'" + reg.s1 + "', "
				+ "S2=" + "'" + reg.s2 + "', "
				+ "S3=" + "'" + reg.s3 + "', "
				+ "S4=" + "'" + reg.s4 + "', "
				+ "S5=" + "'" + reg.s5 + "', "
				+ "S6=" + "'" + reg.s6 + "', "
				+ "SE1=" + "'" + reg.se1 + "', "
				+ "SE2=" + "'" + reg.se2 + "', "
				+ "SE3=" + "'" + reg.se3 + "', "
				+ "SE4=" + "'" + reg.se4 + "', "
				+ "SE5=" + "'" + reg.se5 + "', "
				+ "R1=" + "'" + reg.r1 + "', "
				+ "R2=" + "'" + reg.r2 + "', "
				+ "R3=" + "'" + reg.r3 + "', "
				+ "R4=" + "'" + reg.r4 + "', "
				+ "R5=" + "'" + reg.r5 + "', "
				+ "R6=" + "'" + reg.r6 + "'"
				+ " WHERE id=" + "'" + id +"'";
		this.setStatement();
		this.executeStatement(sql);
	}
	
	public int inputPV(PVregister reg)throws Exception{
		int timeId = (int) (System.currentTimeMillis() / 1000L);
		
		this.setStatement();
		String sql = "INSERT OR IGNORE INTO voltable ("
				+ "id, enviado, posto, pista, data, hora, sentido, local, pesquisador1, pesquisador2, " 
				+ "P1, P2, P3, M, O1, O2, O3, C1, C2, C3, C4, C5, "
				+ "S1, S2, S3, S4, S5, S6, SE1, SE2, SE3, SE4, SE5, R1, R2, R3, R4, R5, R6"
				+ ") VALUES ("
				+ Integer.toString(timeId) + ", "
				+ Integer.toString(reg.enviado) + ","
				+ Integer.toString(reg.posto) + ","
				+ "'" + reg.pista + "', "
				+ "'" + reg.data + "',"
				+ Integer.toString(reg.hora) + ", "
				+ "'" + reg.sentido + "', "
				+ "'" + reg.local + "', "
				+ "'" + reg.pesquisador1 + "', "
				+ "'" + reg.pesquisador2 + "', "
				+ "'" + reg.p1 + "', "
				+ "'" + reg.p2 + "', "
				+ "'" + reg.p3 + "', "
				+ "'" + reg.m + "', "
				+ "'" + reg.o1 + "', "
				+ "'" + reg.o2 + "',"
				+ "'" + reg.o3 + "', "
				+ "'" + reg.c1 + "', "
				+ "'" + reg.c2 + "', "
				+ "'" + reg.c3 + "', "
				+ "'" + reg.c4 + "', "
				+ "'" + reg.c5 + "', "
				+ "'" + reg.s1 + "', "
				+ "'" + reg.s2 + "', "
				+ "'" + reg.s3 + "', "
				+ "'" + reg.s4 + "', "
				+ "'" + reg.s5 + "', "
				+ "'" + reg.s6 + "', "
				+ "'" + reg.se1 + "', "
				+ "'" + reg.se2 + "', "
				+ "'" + reg.se3 + "', "
				+ "'" + reg.se4 + "', "
				+ "'" + reg.se5 + "', "
				+ "'" + reg.r1 + "', "
				+ "'" + reg.r2 + "', "
				+ "'" + reg.r3 + "', "
				+ "'" + reg.r4 + "', "
				+ "'" + reg.r5 + "', "
				+ "'" + reg.r6 + 
				"') ";
		int r = this.executeStatement(sql);
		
		return r;
	}
	
	public void createVolTable() throws Exception{
		this.setStatement();
        String qry = "CREATE TABLE IF NOT EXISTS voltable "
                + " (id int PRIMARY KEY, "
				+ "enviado integer, "
				+ "posto integer, "
				+ "pista text, "
				+ "data text, "
                + "hora integer, "
				+ "sentido text, "
				+ "local text, "
				+ "pesquisador1 text, "
				+ "pesquisador2 text, "
				+ "P1 text, "
				+ "P2 text, "
				+ "P3 text, "
				+ "M text, "
				+ "O1 text, "
				+ "O2 text, "
				+ "O3 text, "
				+ "C1 text, "
				+ "C2 text, "
				+ "C3 text, "
				+ "C4 text, "
				+ "C5 text, "
				+ "R1 text, "
				+ "R2 text, "
				+ "R3 text, "
				+ "R4 text, "
				+ "R5 text, "
				+ "R6 text, "
				+ "S1 text, "
				+ "S2 text, "
				+ "S3 text, "
				+ "S4 text, "
				+ "S5 text, "
				+ "S6 text, "
				+ "SE1 text, "
				+ "SE2 text, "
				+ "SE3 text, "
				+ "SE4 text, "
				+ "SE5 text "
				+ "); ";
       this.executeStatement(qry);
	   
	}
	
	public void createODTable() throws Exception{
		this.setStatement();
        String qry = "CREATE TABLE IF NOT EXISTS odTable ("
					+ "id text primary key, "
					+ "enviado integer, "
					+ "estaNoNote integer, "
					+ "cancelado integer, "
					+ "idPosto integer, "
					+ "sentido text, "
					+ "idIpad text, "
					//+ "uuid text, "
					+ "login text, "
					+ "dataIniPesq text, "
					+ "dataFimPesq text, "
					+ "placa text, "
					+ "anoDeFabricacao integer, "
					+ "tipo text, "
					+ "idOrigemPais integer, "
					+ "idOrigemMunicipio integer, "
					+ "idDestinoPais integer, "
					+ "idDestinoMunicipio integer, "
					+ "idMotivoDeEscolhaDaRota integer, "
					+ "frequenciaQtd integer, "
					+ "frequenciaPeriodo text, "
					+ "idPropriedadesDoVeiculo integer, "
					+ "placaEstrangeira text, "
					+ "idPaisPlacaEstrangeira integer, "
					+ "idCombustivel integer, "
					+ "categoria text, "
					+ "possuiReboque integer, "
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
