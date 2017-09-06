package br.ufrj.coppetec.concentrador.database;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import br.ufrj.coppetec.concentrador.Concentrador;
import br.ufrj.coppetec.concentrador.Util;
import java.sql.SQLException;

/**
 *
 * @author mangeli
 */
public class myDB extends Db {

	public static final String TABLE_IMPORTED_FILES = "importedFiles";
	public static final String TABLE_IMPORTED_FILES_ID = "id";
	public static final String TABLE_IMPORTED_FILES_PATH = "path";
	public static final String TABLE_IMPORTED_FILES_DATE = "DATE";

	public static final String TABLE_NAME_OD = "odtable";
	public static final String TABLE_NAME_VOL = "voltable";

	private static Logger logger = LogManager.getLogger(myDB.class);

	private static myDB instance = null;

	private myDB() throws Exception {
		super("org.sqlite.JDBC", "jdbc:sqlite:dados.db");

	}

	public static myDB getInstance() throws Exception {
		if (instance == null) {
			instance = new myDB();
		}
		return instance;
	}

	public static String getConditionByValidDate(String tableName) throws ParseException {
		return String.format("%s %s IN %s", (Concentrador.treinamento ? "NOT" : ""),
				(tableName.equalsIgnoreCase(TABLE_NAME_OD) ? "DATE(dataIniPesq)" : "data"), Util.getValidDatesListInSQL());
	}

	public void keepAlive() throws Exception {
		openTransaction();
		this.executeQuery("Select 1;");
		commit();
	}

	public void sanitize() throws Exception {
		try {
			openTransaction();
			this.executeStatement("DELETE FROM odtable WHERE id is null OR id = 'null';");
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	public int verifyPV(PVKey regKey) throws Exception {
		int r = 0;
		try {
			this.openTransaction();
			this.setStatement();
			String qry = "SELECT * FROM voltable WHERE posto = " + Integer.toString(regKey.posto);
			// qry += " AND pista='" + reg.pista + "'";
			qry += " AND data='" + regKey.data + "'";
			qry += " AND hora=" + Integer.toString(regKey.hora);
			qry += " AND sentido='" + regKey.sentido + "'";
			// qry += " AND posto='" + Integer.toString(reg.posto) + "'";
			// qry += " AND pesquisador1='" + reg.pesquisador1 + "'";
			// qry += " AND pesquisador2='" + reg.pesquisador2 + "'";

			ResultSet result = this.executeQuery(qry);
			r = ((result.next()) ? result.getInt("id") : 0);
			result.close();
			this.commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
		return r;
	}

	public int verifyPV(PVregister reg) throws Exception {
		return verifyPV(new PVKey(reg));
	}

	public PVregister getPVRegister(PVKey key) throws Exception {
		return getPVRegister(verifyPV(key));
	}

	public PVregister getPVRegister(int id) throws Exception {
		PVregister pvR = new PVregister();
		String qry = "SELECT * FROM voltable WHERE id = " + "'" + id + "'";

		this.setStatement();
		ResultSet result = this.executeQuery(qry);
		if (result.next()) {
			pvR.posto = result.getInt("posto");
			pvR.pista = result.getString("pista");
			pvR.data = result.getString("data");
			pvR.hora = result.getInt("hora");
			pvR.sentido = result.getString("sentido");
			pvR.local = result.getString("local");
			pvR.pesquisador1 = result.getString("pesquisador1");
			pvR.pesquisador2 = result.getString("pesquisador2");
			pvR.p1 = result.getString("P1");
			pvR.p2 = result.getString("P2");
			pvR.p3 = result.getString("P3");
			pvR.m = result.getString("M");
			pvR.o1 = result.getString("O1");
			pvR.o2 = result.getString("O2");
			pvR.o3 = result.getString("O3");
			pvR.c1 = result.getString("C1");
			pvR.c2 = result.getString("C2");
			pvR.c3 = result.getString("C3");
			pvR.c4 = result.getString("C4");
			pvR.c5 = result.getString("C5");
			pvR.s1 = result.getString("S1");
			pvR.s2 = result.getString("S2");
			pvR.s3 = result.getString("S3");
			pvR.s4 = result.getString("S4");
			pvR.s5 = result.getString("S5");
			pvR.s6 = result.getString("S6");
			pvR.se1 = result.getString("SE1");
			pvR.se2 = result.getString("SE2");
			pvR.se3 = result.getString("SE3");
			pvR.se4 = result.getString("SE4");
			pvR.se5 = result.getString("SE5");
			pvR.r1 = result.getString("R1");
			pvR.r2 = result.getString("R2");
			pvR.r3 = result.getString("R3");
			pvR.r4 = result.getString("R4");
			pvR.r5 = result.getString("R5");
			pvR.r6 = result.getString("R6");
		}
		result.close();
		return pvR;
	}

	public void deletePV(PVKey key) throws Exception {
		try {
			openTransaction();
			this.setStatement();
			String qry = "DELETE FROM voltable WHERE posto = " + Integer.toString(key.posto);
			qry += " AND data='" + key.data + "'";
			qry += " AND hora=" + Integer.toString(key.hora);
			qry += " AND sentido='" + key.sentido + "'";

			this.executeStatement(qry);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	private String[] getDates(String table, String nameFieldDate, String postoField, Integer posto, String extra_condition)
			throws Exception {
		String validPeriodCondition = " AND " + getConditionByValidDate(table) + " ";
		this.setStatement();
		ResultSet result = null;
		String[] datesReturn = null;
		int qtd = 0;
		String qry = "SELECT COUNT(DISTINCT DATE(" + nameFieldDate + ")) AS qtd FROM " + table + " WHERE " + postoField + "="
				+ posto + " " + validPeriodCondition + extra_condition;
		result = this.executeQuery(qry);
		if (result.next()) {
			qtd = result.getInt("qtd");
		}
		result.close();
		if (qtd != 0) {
			datesReturn = new String[qtd];
			this.setStatement();
			qry = "SELECT DISTINCT DATE(" + nameFieldDate + ") AS d FROM " + table + " WHERE " + postoField + "=" + posto + " "
					+ validPeriodCondition + extra_condition + " ORDER BY d DESC";
			result = this.executeQuery(qry);
			int count = 0;
			while (result.next()) {
				datesReturn[count++] = result.getString("d");
			}
		}
		return datesReturn;
	}

	public String[] getODDates(Integer posto) throws Exception {
		return getDates("odTable", "dataIniPesq", "idPosto", posto, " AND cancelado=0");
	}

	public String[] getVolDates(Integer posto) throws Exception {
		return getDates("voltable", "data", "posto", posto, "");
	}

	public Map<String, Integer> getSumVol(String[] fieldNames, String date) throws Exception {
		Map<String, Integer> returnData = new HashMap<String, Integer>();
		try {
			openTransaction();
			this.setStatement();
			String qry;
			JSONObject jsonObject = null;
			for (int i = 0; i < fieldNames.length; i++) {
				returnData.put(fieldNames[i], 0);
			}
			if (date == null) {
				qry = "SELECT * FROM voltable WHERE " + getConditionByValidDate(TABLE_NAME_VOL);
			} else {
				qry = "SELECT * FROM voltable WHERE data = '" + date + "'";
			}

			ResultSet result = this.executeQuery(qry);
			while (result.next()) {
				for (String field : returnData.keySet()) {
					jsonObject = new JSONObject(result.getString(field));
					returnData.put(field, returnData.get(field) + jsonObject.getInt("Hora1") + jsonObject.getInt("Hora2"));
				}
			}
			result.close();
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
		return returnData;

	}

	public Map<String, Map<Integer, Integer>> getSumVolPerHour(String[] fieldNames, String date) throws Exception {
		Map<String, Map<Integer, Integer>> returnData = new HashMap<String, Map<Integer, Integer>>();

		try {
			openTransaction();
			this.setStatement();
			String qry;
			JSONObject jsonObject = null;
			for (int i = 0; i < fieldNames.length; i++) {
				returnData.put(fieldNames[i], new HashMap<Integer, Integer>());
				for (int j = 0; j < 24; j += 2) {
					returnData.get(fieldNames[i]).put(j, 0);
				}
			}
			if (date == null) {
				qry = "SELECT * FROM voltable WHERE " + getConditionByValidDate(TABLE_NAME_VOL);
			} else {
				qry = "SELECT * FROM voltable WHERE data = '" + date + "'";
			}
			ResultSet result = this.executeQuery(qry);
			while (result.next()) {
				for (String field : returnData.keySet()) {
					jsonObject = new JSONObject(result.getString(field));
					returnData.get(field).put(result.getInt("hora"), returnData.get(field).get(result.getInt("hora"))
							+ jsonObject.getInt("Hora1") + jsonObject.getInt("Hora2"));
				}
			}
			result.close();
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
		return returnData;
	}

	public void updatePV(PVregister reg, int id) throws Exception {
		try {
			openTransaction();
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
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	public int inputPV(PVregister reg) throws Exception {
		int r = -1;
		try {
			openTransaction();
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
			r = this.executeStatement(sql);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
		return r;
	}

	public void initDatabaseTables() throws Exception {
		boolean newDB;
		try {
			openTransaction();
			newDB = isClean();
			createVolTable();
			createODTable();
			createImportedFilesTable();
			updateSchema(newDB);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}
	
	/**
	 * Verifica se a tabale odTable já existe no banco
	 * @return true se o banco não possuía a tabela odTable (banco recém criado)
	 * @throws Exception 
	 */
	private boolean isClean() throws Exception{
		boolean r = true;
		this.setStatement();
		ResultSet result = this.executeQuery("SELECT * FROM SQLITE_MASTER WHERE TYPE='table' AND name='odTable';");
		if (result.next()){
			r = false;
		}
		return r;
	}

	private void createVolTable() throws Exception {
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

	private void createImportedFilesTable() throws Exception {
		this.setStatement();
		String qry = "CREATE TABLE IF NOT EXISTS " + TABLE_IMPORTED_FILES + " (";
		qry += TABLE_IMPORTED_FILES_ID + " text primary key, ";
		qry += TABLE_IMPORTED_FILES_PATH + " text unique,";
		qry += TABLE_IMPORTED_FILES_DATE + " datetime DEFAULT CURRENT_TIMESTAMP";
		qry += "); ";
		this.executeStatement(qry);
	}

	private void createODTable() throws Exception {
		this.setStatement();
		String qry = "CREATE TABLE IF NOT EXISTS odTable (";
		qry += "id text primary key NOT NULL, ";
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
		qry += "idPerguntaExtra integer,";
		qry += "idPerguntaExtra2 integer,";
		qry += "duracaoPesq integer, ";
		qry += "treinamento integer";
		// IMPORTANTE: se adicionar mais campos ao final, não esquecer do separador no campo anterior
		// mudanças no banco devem ser refletidas com método updateSchemaScript()
		qry += "); ";
		this.executeStatement(qry);
		this.sanitize();

	}
	
	private void updateSchema(boolean newDB) throws Exception{
		int schemaVersion = 0;
		ResultSet schemaResult;
		this.setStatement();
		this.executeStatement("CREATE TABLE IF NOT EXISTS versaoSchema (versao integer);");
		schemaResult = this.executeQuery("SELECT versao FROM versaoSchema;");
		if (schemaResult.next()){
			schemaVersion = schemaResult.getInt("versao");
		}
		
		logger.info("Versão do schema: " + schemaVersion);
		if (schemaVersion != Concentrador.dbVersion){
			logger.info("Atualizando schema do banco de dados");
			this.updateSchemaScript(schemaVersion, newDB);
		}
		
	}

	private void updateSchemaScript(int oldVersion, boolean newDB) throws Exception{
		this.setStatement();
		
		if(!newDB && oldVersion < 1){
			//rodando atualização
			this.executeStatement("ALTER TABLE odTable ADD COLUMN treinamento integer;");
		}
		
		
		//atualizando a tabela de versão
		if(oldVersion == 0){ //a tabela de versão foi recem criada e não tem um registro
			this.executeStatement("INSERT INTO versaoSchema VALUES (" + Concentrador.dbVersion + ");");
		} else {
			this.executeStatement("UPDATE versaoSchema SET versao = " + Concentrador.dbVersion + ";");
		}
		
		if(oldVersion < 2){
			this.executeStatement("ALTER TABLE odTable ADD COLUMN idPerguntaExtra2 integer;");
		}
		
		logger.info("Versão do schema: " + Concentrador.dbVersion);
	}
	
	public Vector<String> fetchReportODColumns() throws Exception {
		Vector<String> cols = new Vector<String>();
		openTransaction();
		String sel_sql = String.format("SELECT DISTINCT idIpad FROM odTable WHERE %s ORDER BY idIpad ASC",
				getConditionByValidDate(TABLE_NAME_OD));
		ResultSet result = this.executeQuery(sel_sql);
		cols.add("");
		while (result.next())
			cols.add(result.getString("idIpad"));
		commit();
		return cols;
	}

	public Vector<String> fetchReportODRows(Integer posto) throws Exception {
		Vector<String> rows = new Vector<String>();
		openTransaction();
		String validPeriodCondition = " AND " + getConditionByValidDate(TABLE_NAME_OD);
		String sel_sql = "SELECT DISTINCT DATE(dataIniPesq) AS data FROM odTable WHERE idPosto=" + posto + " AND cancelado=0 "
				+ validPeriodCondition + " ORDER BY date(dataIniPesq) DESC";
		ResultSet result = this.executeQuery(sel_sql);
		while (result.next()) {
			Date day = Util.sdfSQL.parse(result.getString("data"));
			rows.add(Util.sdfBrazil.format(day));
		}
		commit();
		return rows;
	}

	public Map<String, Integer> fetchReportODData(String strData, Integer posto) throws Exception {
		Date data = Util.sdfBrazil.parse(strData);
		openTransaction();
		String sqlData = Util.sdfSQL.format(data);
		String sel_sql = "SELECT idIpad, COUNT(idIpad) AS times FROM odTable " + " WHERE DATE(dataIniPesq)='" + sqlData
				+ "' AND cancelado=0 AND idPosto=" + posto + " GROUP BY idIpad ";

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		ResultSet result = this.executeQuery(sel_sql);
		while (result.next()) {
			String ipad = result.getString("idIpad");
			Integer count = result.getInt("times");
			map.put(ipad, count);
		}
		commit();
		return map;
	}

	public Map<String, Map<String, Integer>> fetchReportODData(Integer posto) throws Exception {
		openTransaction();
		String validPeriodCondition = " AND " + getConditionByValidDate(TABLE_NAME_OD);
		String sel_sql = "SELECT DATE(dataIniPesq) AS dia, idIpad, COUNT(idIpad) AS times FROM odTable "
				+ "WHERE cancelado=0 AND idPosto=" + posto + validPeriodCondition + " GROUP BY date(dataIniPesq), idIpad "
				+ "ORDER BY date(dataIniPesq) ASC, idIpad ASC";
		HashMap<String, Map<String, Integer>> data = new HashMap<String, Map<String, Integer>>();
		ResultSet result = this.executeQuery(sel_sql);
		while (result.next()) {
			Date day = Util.sdfSQL.parse(result.getString("dia"));
			String ipad = result.getString("idIpad");
			Integer times = result.getInt("times");

			logger.debug(day.getTime() + ":" + ipad + "(" + times + ")");
			if (data.containsKey(day)) {
				Map<String, Integer> regs = data.get(day);
				regs.put(ipad, times);
				data.put(Util.sdfBrazil.format(day), regs);
			} else {
				HashMap<String, Integer> regs = new HashMap<String, Integer>();
				regs.put(ipad, times);
				data.put(Util.sdfBrazil.format(day), regs);
			}
		}
		result.close();
		commit();
		return data;
	}

	// public final static BigInteger MAX_SAFE_INT = BigInteger.valueOf(99999999);
	//
	// /**
	// * Retorna o valor inteiro, limitando ao valor máximo permitido (99999999).
	// *
	// * @param valueStr
	// * - O valor em uma string.
	// * @return O valor em inteiro.
	// */
	// public static Integer getIntValue(String valueStr) {
	// Integer valueInt = null;
	// if (valueStr != null) {
	// BigInteger value = new BigInteger(valueStr);
	// if (value.compareTo(MAX_SAFE_INT) > 0) {
	// value = MAX_SAFE_INT;
	// logger.warn(String.format("Valor inteiro maior que o permitido (%s > %d)", valueStr, MAX_SAFE_INT.intValue()));
	// }
	// valueInt = value.intValue();
	// }
	// return valueInt;
	// }

	// public static String getStrFromIntValue(String valueStr) {
	// Integer valueInt = getIntValue(valueStr);
	// return (valueInt == null ? null : String.valueOf(valueInt.intValue()));
	// }
}
