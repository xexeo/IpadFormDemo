package br.ufrj.coppetec.concentrador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Conjunto de métodos utilitários de uso geral.
 * 
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public final class Util {

	public static final String TODAS = "Todas";													///< Variável de controle da representação da opção __TODAS__ nas _caixas de seleção_.
	private static Logger logger = LogManager.getLogger(Util.class);

	private static HashMap<String, Integer> inputLimits = new HashMap<String, Integer>();			///< Estrutura de dados para armazenar os limites, em número de caracteres, dos campos de entrada de dados.

	public static final SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");			///< Formatador de datas para uso no banco de dados
	public static final SimpleDateFormat sdfBrazil = new SimpleDateFormat("dd/MM/yyyy");			///< Formatador de datas no formato pt_BR
	public static final SimpleDateFormat sdfArq = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");	///< Formatador de datas para registro de eventos

	/**
	 * Construtor privado.
	 * Evita a criação de objetos da classe e otimiza o uso de memória pelo programa. Os métodos estão disponíveis para uso sem a
	 * necessidade de criação de objetos da classe devido ao modificador _publi static_.
	 */
	private Util() {
	}

	/**
	 * Concatena vetores de dados.
	 * @param <T>	tipo de dado contido nos vetores.
	 * @param a		vetor de dados
	 * @param b		vetor de dados
	 * @return		vetor resultante, com os dados de ambos os vetores de entrada
	 */
	public static <T> T[] concatArrays(T[] a, T[] b) {
		int aLen = a.length;
		int bLen = b.length;

		@SuppressWarnings("unchecked")
		T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}

	/**
	 * Retorna um vetor com as datas válidas da pesquisa.
	 * Constrói uma estrutura de dados com as datas do arquivo _properties_ de configuração.
	 * @return vetor com datas válidas
	 */
	public static String[] getValidDatesStr() {
		String[] r;
		r = Concentrador.configuration.getProperty("validDays").split(",");
		for (int i = 0; i < r.length; i++) {
			r[i] = r[i].trim();
		}
		return r;
	}
	
	/**
	 * Retorna um vetor com as datas de treinamento.
	 * Constrói uma estrutura de dados com as datas do arquivo _properties_ de configuração.
	 * @return vetor com as datas de treinamento
	 */
	public static String[] getTrainingDatesStr() {
		String[] r;
		r = Concentrador.configuration.getProperty("trainingDays").split(",");
		for (int i = 0; i < r.length; i++) {
			r[i] = r[i].trim();
		}
		return r;
	}

	/**
	 * Verifica se determinada data é uma data válida de pesquisa.
	 * @param date data a ser verificada
	 * @return true se a data for uma data de pesquisa válida.
	 * @throws java.text.ParseException
	 */
	public static boolean isInValidPeriod(Date date) throws ParseException {
		return getValidDates().contains(lowDateTime(date));
	}

	/**
	 * Retorna uma estrutura de dados __Set__ contendo as datas válidas de pesquisa. 
	 * @return conjunto com datas válidas de pesquisa.
	 * @throws java.text.ParseException
	 */
	public static Set<Date> getValidDates() throws ParseException {
		return parseDates(getValidDatesStr());
	}

	/**
	 * Retorna uma estrutura de dados __Set__ contendo as datas válidas de pesquisa. 
	 * @return conjunto com datas de treinamento.
	 * @throws java.text.ParseException
	 */
	public static Set<Date> getTrainingDates() throws ParseException {
		return parseDates(getTrainingDatesStr());
	}

	/**
	 * Converte um vetor de datas no formato _cadeia de caracteres_ para um conjunto de objetos _Date_ .
	 * Método utilitário de uso interno.
	 * @param datesStr vetor com datas
	 * @return conjunto com datas
	 * @throws java.text.ParseException
	 */
	protected static Set<Date> parseDates(String[] datesStr) throws ParseException {
		Set<Date> dates = new HashSet<Date>(datesStr.length);
		for (int i = 0; i < datesStr.length; i++) {
			try {
				dates.add(sdfBrazil.parse(datesStr[i].trim()));
			} catch (ParseException e) {
				logger.error(String.format("Erro ao carregar datas (formato inválido da data: %s).", datesStr[i]), e);
				throw e;
			}
		}
		return dates;
	}

	/**
	 * Constrói uma lista de datas válidas adequada para uso em consultas ao banco de dados.
	 * @return lista de datas no formato de uma _cadeia de caracteres_
	 * @throws java.text.ParseException
	 */
	public static String getValidDatesListInSQL() throws ParseException {
		return getDatesListInSQL(getValidDates());
	}

	/**
	 * Constrói uma lista de datas de treinamento adequada para uso em consultas ao banco de dados.
	 * @return lista de datas no formato de uma _cadeia de caracteres_
	 * @throws java.text.ParseException
	 */
	public static String getTrainingDatesListInSQL() throws ParseException {
		return getDatesListInSQL(getTrainingDates());
	}

	/**
	 * Converte um conjunto de objetos _Date_ para uma _cadeia de caracteres_ contendo as datas.
	 * Método utilitário de uso interno.
	 * @param dates conjunto de objetos _Date_
	 * @return _cadeia de caracteres_ contendo as datas
	 * @throws java.text.ParseException
	 */
	protected static String getDatesListInSQL(Set<Date> dates) throws ParseException {
		String dateListInSQL = "";
		for (Date date : dates) {
			dateListInSQL += ", '" + sdfSQL.format(date) + "'";
		}
		dateListInSQL += ")";
		return dateListInSQL.replaceFirst(", ", "(");
	}

	
	/**
	 * Retorna o valor do horário minimo para a data de referencia passada. 
	 * Por exemplo se a data for "30/01/2017 as 17h:33m:12s e 299ms" a data retornada por este metodo será "30/01/2017 as
	 * 00h:00m:00s e 000ms".
	 * 
	 * @param date de referencia.
	 * @return representa o horário minimo para dia informado.
	 */
	public static Date lowDateTime(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		toOnlyDate(aux); // zera os parametros de hour,min,sec,millisec
		return aux.getTime();
	}

	
	/**
	 * Zera todas as referencias de hora, minuto, segundo e milissegundo do {@link Calendar}.
	 * @param date a ser modificado.
	 */
	private static void toOnlyDate(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * Preenche a estrutura _inputLimits_ .
	 * Preenche a estrutura com os dados oriundos do arquivo de configuração.
	 */
	public static void populateInputLimits() {
		inputLimits.put("placaEstrangeira",
				Integer.parseInt(Concentrador.configuration.getProperty("placaEstrangeira", "20").trim()));
		inputLimits.put("frequenciaQtd", Integer.parseInt(Concentrador.configuration.getProperty("frequenciaQtd", "3").trim()));
		inputLimits.put("numeroDePessoasNoVeiculo",
				Integer.parseInt(Concentrador.configuration.getProperty("numeroDePessoasNoVeiculo", "2").trim()));
		inputLimits.put("pesoDaCarga", Integer.parseInt(Concentrador.configuration.getProperty("pesoDaCarga", "5").trim()));
		inputLimits.put("valorDoFrete", Integer.parseInt(Concentrador.configuration.getProperty("valorDoFrete", "5").trim()));
		inputLimits.put("valorDaCarga", Integer.parseInt(Concentrador.configuration.getProperty("valorDaCarga", "15").trim()));
		inputLimits.put("pesquisador_1", Integer.parseInt(Concentrador.configuration.getProperty("pesquisador_1", "20").trim()));
		inputLimits.put("pesquisador_2", Integer.parseInt(Concentrador.configuration.getProperty("pesquisador_2", "20").trim()));
		inputLimits.put("local", Integer.parseInt(Concentrador.configuration.getProperty("local", "20").trim()));
	}

	/**
	 * Converte uma _cadeia de caracteres_ contendo um valor lógico booleano [true|false|| 1 | 0] para a representação adequado ao banco de dados.
	 * @param val booleano a ser convertido
	 * @return _cadeia de carateres_ com a representação do valor de entrada
	 * @throws java.lang.Exception
	 */
	public static String getSQLiteBoolean(String val) throws Exception {
		if (inputLimits.isEmpty()) {
			String msg = "inputLimits variable is not initialized.";
			logger.info(msg);
			throw new Exception(msg);
		}
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

	/**
	 * Limita o comprimento de um valor numérico inteiro recuperado do banco de dados de acordo com o valor configurado em _inputLimits_.
	 * @param val		valor a ser limitado
	 * @param fieldName	nome do campo
	 * @return			valor com restrição de comprimento
	 * @throws			java.lang.Exception
	 */
	public static String getSQLiteIntLimited(String val, String fieldName) throws Exception {
		if (inputLimits.isEmpty()) {
			String msg = "inputLimits variable is not initialized.";
			logger.info(msg);
			throw new Exception(msg);
		}
		String r = null;
		if (val != null) {
			Integer limit = inputLimits.get(fieldName);
			if (limit == null) {
				limit = 2;
			}
			r = cutString(val, limit);

			try {
				Integer.parseInt(r);
			} catch (NumberFormatException e) {
				logger.debug("Erro na conversão do campo " + fieldName, e);
				r = new String(new char[limit]).replace("\0", "9");
			}
		}
		return r;
	}

	/**
	 * Limita o comprimento de um valor numérico de ponto flutuante recuperado do banco de dados de acordo com o valor configurado em _inputLimits_.
	 * @param val		valor a ser limitado
	 * @param fieldName	nome do campo
	 * @return			valor com restrição de comprimento
	 * @throws			java.lang.Exception
	 */
	public static String getSQLiteRealLimited(String val, String fieldName) throws Exception {
		if (inputLimits.isEmpty()) {
			String msg = "inputLimits variable is not initialized.";
			logger.info(msg);
			throw new Exception(msg);
		}
		String r = null;
		if (val != null) {
			Integer limit = inputLimits.get(fieldName);
			if (limit == null) {
				limit = 2;
			}
			// if is a decimal separator
			if (val.indexOf('.') != -1) {
				String intPart = cutString(val.split("\\.")[0], limit);
				r = intPart + "." + cutString(val.split("\\.")[1], 2);

			} else {
				r = cutString(val, limit);
			}

			try {
				Float.parseFloat(r);
			} catch (NumberFormatException e) {
				logger.debug("Erro na conversão do campo " + fieldName, e);
				r = new String(new char[limit]).replace("\0", "9");
			}
		}
		return r;
	}

	/**
	 * Limita o comprimento de uma _cadeia de caracteres_ recuperada do banco de dados de acordo com o valor configurado em _inputLimits_.
	 * @param val		_cadeia de caracteres_ a ser limitada
	 * @param fieldName	nome do campo
	 * @return			valor com restrição de comprimento
	 * @throws			java.lang.Exception
	 */
	public static String getStringLimited(String val, String fieldName) throws Exception {
		if (inputLimits.isEmpty()) {
			String msg = "inputLimits variable is not initialized.";
			logger.info(msg);
			throw new Exception(msg);
		}
		String r = null;
		if (val != null) {
			Integer limit = inputLimits.get(fieldName);
			if (limit == null) {
				limit = 20;
			}
			if (val.length() > limit) {
				r = val.substring(0, limit - 1);
			} else {
				r = val;
			}
		}
		return r;
	}

	/**
	 * Impõe limite de comprimento a um inteiro representado por uma _cadeia de caracteres_ .
	 * No caso de um valor de comprimento maior que o limite ter sido informado, o valor retornado é o maior inteiro com o número de 
	 * caracteres igual ao limite.
	 * Método utilitário de uso interno.
	 * @param str		cadeia a ser limitada
	 * @param maxLength	comprimento máximo
	 * @return			valor retornado limitado ao comprimento informado
	 */
	private static String cutString(String str, int maxLength) {
		String r;
		if (str.length() > maxLength) {
			char[] chars = new char[maxLength];
			Arrays.fill(chars, '9');
			r = new String(chars);
		} else {
			r = str;
		}
		return r;
	}

	/**
	 * Retorna o valor do hash de um arquivo
	 * @param digest		manipulador do algorítmo especificado para o cálculo do hash
	 * @param file			arquivo
	 * @return				valor do hash
	 * @throws IOException 
	 */
	public static String getFileChecksum(MessageDigest digest, File file) throws IOException {
		// Get file input stream for reading the file content
		FileInputStream fis = new FileInputStream(file);

		// Create byte array to read data in chunks
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;

		// Read file data and update in message digest
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}
		;

		// close the stream; We don't need it now.
		fis.close();

		// Get the hash's bytes
		byte[] bytes = digest.digest();

		// This bytes[] has bytes in decimal format;
		// Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		// return complete hash
		return sb.toString();
	}

}
