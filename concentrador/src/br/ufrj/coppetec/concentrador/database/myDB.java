package br.ufrj.coppetec.concentrador.database;

import java.math.BigInteger;
import java.sql.ResultSet;

/**
 *
 * @author mangeli
 */
public class myDB extends Db {

	public myDB() throws Exception {
		super("org.sqlite.JDBC", "jdbc:sqlite:dados.db");
	}

	public int verifyPV(PVregister reg) throws Exception {
		this.setStatement();
		String qry = "SELECT * FROM voltable WHERE posto = " + Integer.toString(reg.posto);
		// qry += " AND pista='" + reg.pista + "'";
		qry += " AND data='" + reg.data + "'";
		qry += " AND hora=" + Integer.toString(reg.hora);
		qry += " AND sentido='" + reg.sentido + "'";
		qry += " AND posto='" + Integer.toString(reg.posto) + "'";
		// qry += " AND pesquisador1='" + reg.pesquisador1 + "'";
		// qry += " AND pesquisador2='" + reg.pesquisador2 + "'";

		ResultSet result = this.executeQuery(qry);
		int r = ((result.next()) ? result.getInt("id") : 0);
		result.close();
		return r;
	}

	public void updatePV(PVregister reg, int id) throws Exception {
		String sql = "UPDATE voltable SET ";
		sql += "enviado=" + Integer.toString(reg.enviado) + ",";
		sql += "posto=" + Integer.toString(reg.posto) + ",";
		sql += "pista=" + "'" + reg.pista + "', ";
		sql += "data=" + "'" + reg.data + "',";
		sql += "hora=" + Integer.toString(reg.hora) + ", ";
		sql += "sentido=" + "" + "'" + reg.sentido + "', ";
		sql += "local=" + "'" + reg.local + "', ";
		sql += "pesquisador1=" + "'" + reg.pesquisador1 + "', ";
		sql += "pesquisador2=" + "'" + reg.pesquisador2 + "', ";
		sql += "P1=" + "'" + reg.p1 + "', ";
		sql += "P2=" + "'" + reg.p2 + "', ";
		sql += "P3=" + "'" + reg.p3 + "', ";
		sql += "M=" + "'" + reg.m + "', ";
		sql += "O1=" + "'" + reg.o1 + "', ";
		sql += "O2=" + "'" + reg.o2 + "',";
		sql += "O3=" + "'" + reg.o3 + "', ";
		sql += "C1=" + "'" + reg.c1 + "', ";
		sql += "C2=" + "'" + reg.c2 + "', ";
		sql += "C3=" + "'" + reg.c3 + "', ";
		sql += "C4=" + "'" + reg.c4 + "', ";
		sql += "C5=" + "'" + reg.c5 + "', ";
		sql += "S1=" + "'" + reg.s1 + "', ";
		sql += "S2=" + "'" + reg.s2 + "', ";
		sql += "S3=" + "'" + reg.s3 + "', ";
		sql += "S4=" + "'" + reg.s4 + "', ";
		sql += "S5=" + "'" + reg.s5 + "', ";
		sql += "S6=" + "'" + reg.s6 + "', ";
		sql += "SE1=" + "'" + reg.se1 + "', ";
		sql += "SE2=" + "'" + reg.se2 + "', ";
		sql += "SE3=" + "'" + reg.se3 + "', ";
		sql += "SE4=" + "'" + reg.se4 + "', ";
		sql += "SE5=" + "'" + reg.se5 + "', ";
		sql += "R1=" + "'" + reg.r1 + "', ";
		sql += "R2=" + "'" + reg.r2 + "', ";
		sql += "R3=" + "'" + reg.r3 + "', ";
		sql += "R4=" + "'" + reg.r4 + "', ";
		sql += "R5=" + "'" + reg.r5 + "', ";
		sql += "R6=" + "'" + reg.r6 + "'";
		sql += " WHERE id=" + "'" + id + "'";
		this.setStatement();
		this.executeStatement(sql);
	}

	public int inputPV(PVregister reg) throws Exception {
		int timeId = (int) (System.currentTimeMillis() / 1000L);

		this.setStatement();
		String sql = "INSERT OR IGNORE INTO voltable (";
		sql += "id, enviado, posto, pista, data, hora, sentido, local, pesquisador1, pesquisador2, ";
		sql += "P1, P2, P3, M, O1, O2, O3, C1, C2, C3, C4, C5, ";
		sql += "S1, S2, S3, S4, S5, S6, SE1, SE2, SE3, SE4, SE5, R1, R2, R3, R4, R5, R6";
		sql += ") VALUES (";
		sql += Integer.toString(timeId) + ", ";
		sql += Integer.toString(reg.enviado) + ",";
		sql += Integer.toString(reg.posto) + ",";
		sql += "'" + reg.pista + "', ";
		sql += "'" + reg.data + "',";
		sql += Integer.toString(reg.hora) + ", ";
		sql += "'" + reg.sentido + "', ";
		sql += "'" + reg.local + "', ";
		sql += "'" + reg.pesquisador1 + "', ";
		sql += "'" + reg.pesquisador2 + "', ";
		sql += "'" + reg.p1 + "', ";
		sql += "'" + reg.p2 + "', ";
		sql += "'" + reg.p3 + "', ";
		sql += "'" + reg.m + "', ";
		sql += "'" + reg.o1 + "', ";
		sql += "'" + reg.o2 + "',";
		sql += "'" + reg.o3 + "', ";
		sql += "'" + reg.c1 + "', ";
		sql += "'" + reg.c2 + "', ";
		sql += "'" + reg.c3 + "', ";
		sql += "'" + reg.c4 + "', ";
		sql += "'" + reg.c5 + "', ";
		sql += "'" + reg.s1 + "', ";
		sql += "'" + reg.s2 + "', ";
		sql += "'" + reg.s3 + "', ";
		sql += "'" + reg.s4 + "', ";
		sql += "'" + reg.s5 + "', ";
		sql += "'" + reg.s6 + "', ";
		sql += "'" + reg.se1 + "', ";
		sql += "'" + reg.se2 + "', ";
		sql += "'" + reg.se3 + "', ";
		sql += "'" + reg.se4 + "', ";
		sql += "'" + reg.se5 + "', ";
		sql += "'" + reg.r1 + "', ";
		sql += "'" + reg.r2 + "', ";
		sql += "'" + reg.r3 + "', ";
		sql += "'" + reg.r4 + "', ";
		sql += "'" + reg.r5 + "', ";
		sql += "'" + reg.r6 + "') ";
		int r = this.executeStatement(sql);

		return r;
	}

	public void createVolTable() throws Exception {
		this.setStatement();
		String qry = "CREATE TABLE IF NOT EXISTS voltable ";
		qry += " (id int PRIMARY KEY, ";
		qry += "enviado integer, ";
		qry += "posto integer, ";
		qry += "pista text, ";
		qry += "data text, ";
		qry += "hora integer, ";
		qry += "sentido text, ";
		qry += "local text, ";
		qry += "pesquisador1 text, ";
		qry += "pesquisador2 text, ";
		qry += "P1 text, ";
		qry += "P2 text, ";
		qry += "P3 text, ";
		qry += "M text, ";
		qry += "O1 text, ";
		qry += "O2 text, ";
		qry += "O3 text, ";
		qry += "C1 text, ";
		qry += "C2 text, ";
		qry += "C3 text, ";
		qry += "C4 text, ";
		qry += "C5 text, ";
		qry += "R1 text, ";
		qry += "R2 text, ";
		qry += "R3 text, ";
		qry += "R4 text, ";
		qry += "R5 text, ";
		qry += "R6 text, ";
		qry += "S1 text, ";
		qry += "S2 text, ";
		qry += "S3 text, ";
		qry += "S4 text, ";
		qry += "S5 text, ";
		qry += "S6 text, ";
		qry += "SE1 text, ";
		qry += "SE2 text, ";
		qry += "SE3 text, ";
		qry += "SE4 text, ";
		qry += "SE5 text ";
		qry += "); ";
		this.executeStatement(qry);

	}

	public void createODTable() throws Exception {
		this.setStatement();
		String qry = "CREATE TABLE IF NOT EXISTS odTable (";
		qry += "id text primary key, ";
		qry += "enviado integer, ";
		qry += "estaNoNote integer, ";
		qry += "cancelado integer, ";
		qry += "idPosto integer, ";
		qry += "sentido text, ";
		qry += "idIpad text, ";
		// qry += "uuid text, ";
		qry += "login text, ";
		qry += "dataIniPesq text, ";
		qry += "dataFimPesq text, ";
		qry += "placa text, ";
		qry += "anoDeFabricacao integer, ";
		qry += "tipo text, ";
		qry += "idOrigemPais integer, ";
		qry += "idOrigemMunicipio integer, ";
		qry += "idDestinoPais integer, ";
		qry += "idDestinoMunicipio integer, ";
		qry += "idMotivoDeEscolhaDaRota integer, ";
		qry += "frequenciaQtd integer, ";
		qry += "frequenciaPeriodo text, ";
		qry += "idPropriedadesDoVeiculo integer, ";
		qry += "placaEstrangeira integer, ";
		qry += "idPaisPlacaEstrangeira integer, ";
		qry += "idCombustivel integer, ";
		qry += "categoria text, ";
		qry += "possuiReboque integer, ";
		qry += "numeroDePessoasNoVeiculo integer, ";
		qry += "numeroDePessoasATrabalho integer, ";
		qry += "idRendaMedia integer, ";
		qry += "idMotivoDaViagem integer, ";
		qry += "tipoCaminhao text, ";
		qry += "idTipoDeContainer integer, ";
		qry += "idTipoCarroceria integer, ";
		qry += "rntrc text, ";
		qry += "possuiCargaPerigosa integer, ";
		qry += "idNumeroDeRisco integer, ";
		qry += "idNumeroDaOnu integer, ";
		qry += "idAgregado integer, ";
		qry += "placaVermelha integer, ";
		qry += "idTipoDeViagemOuServico integer, ";
		qry += "pesoDaCarga real, ";
		qry += "valorDoFrete real, ";
		qry += "utilizaParadaEspecial integer, ";
		qry += "idProduto integer, ";
		qry += "idCargaAnterior integer, ";
		qry += "valorDaCarga real, ";
		qry += "municipioEmbarqueCarga integer, ";
		qry += "idLocalEmbarqueCarga integer, ";
		qry += "municipioDesembarqueCarga integer, ";
		qry += "idLocalDesembarqueCarga integer, ";
		qry += "indoPegarCarga integer, ";
		qry += "paradaObrigatoriaMunicipio1 integer, ";
		qry += "paradaObrigatoriaMunicipio2 integer, ";
		qry += "idPerguntaExtra integer";
		qry += "); ";
		this.executeStatement(qry);

	}

	static public String getSQLiteBoolean(String val) {
		String r;
		if (val != null) {
			if (val.equals("false") || val.equals("0")) {
				r = "0";
			} else if (val.equals("true") || val.equals("1")) {
				r = "1";
			} else {
				r = null;
			}
		} else {
			r = null;
		}
		return r;
	}

	private final static BigInteger MAX_INT = BigInteger.valueOf(99999999);

	/**
	 * Retorna o valor inteiro, limitando ao valor máximo permitido (99999999).
	 * 
	 * @param valueStr
	 *            - O valor em uma string.
	 * @return O valor em inteiro. Retornará null se a string não estiver no formato de um inteiro.
	 */
	public static Integer getIntValue(String valueStr) {
		Integer valueInt = null;
		if (valueStr != null) {
			try {
				BigInteger value = new BigInteger(valueStr);
				if (value.compareTo(MAX_INT) > 0) {
					value = MAX_INT;
				}
				valueInt = value.intValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valueInt;
	}

	public static String getStrFromIntValue(String valueStr) {
		Integer valueInt = getIntValue(valueStr);
		return (valueInt == null ? null : String.valueOf(valueInt.intValue()));
	}
}
