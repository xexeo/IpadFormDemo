/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author mangeli
 */
public final class Util {

	private static Logger logger = LogManager.getLogger(Util.class);

	private static HashMap<String, Integer> inputLimits = new HashMap<String, Integer>();

	public static final SimpleDateFormat SDF_SQL_DATE_ONLY = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat SDF_BRAZIL = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat SDF_ARQ = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	private Util() {
	}

	public static <T> T[] concatArrays(T[] a, T[] b) {
		int aLen = a.length;
		int bLen = b.length;

		@SuppressWarnings("unchecked")
		T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}

	public static String[] getValidDatesStr() {
		String[] r;
		r = Concentrador.configuration.getProperty("validDays").split(",");
		for (int i = 0; i < r.length; i++) {
			r[i] = r[i].trim();
		}
		return r;
	}

	public static String[] getTrainingDatesStr() {
		String[] r;
		r = Concentrador.configuration.getProperty("trainingDays").split(",");
		for (int i = 0; i < r.length; i++) {
			r[i] = r[i].trim();
		}
		return r;
	}

	public static boolean isInValidPeriod(Date date) throws ParseException {
		return getValidDates().contains(lowDateTime(date));
	}

	// public static boolean isOutValidPeriod(Date date) throws ParseException {
	// return (getMinValidDate().compareTo(date) > 0) || (getMaxValidDate().compareTo(date) < 0);
	// }
	//
	// public static boolean isBeforeValidPeriod(Date date) throws ParseException {
	// return getMinValidDate().compareTo(date) > 0;
	// }
	//
	// public static boolean isAfterValidPeriod(Date date) throws ParseException {
	// return getMaxValidDate().compareTo(date) < 0;
	// }
	//
	// public static boolean isInOrAfterValidPeriod(Date date) throws ParseException {
	// // return getMinValidDate().compareTo(date) <= 0; /* Se o intervalo não for contínuo, essa linha não é adequada! */
	// return isInValidPeriod(date) || isAfterValidPeriod(date);
	// }

	public static Set<Date> getValidDates() throws ParseException {
		return parseDates(getValidDatesStr());
	}

	public static Set<Date> getTrainingDates() throws ParseException {
		return parseDates(getTrainingDatesStr());
	}

	protected static Set<Date> parseDates(String[] datesStr) throws ParseException {
		Set<Date> dates = new HashSet<Date>(datesStr.length);
		for (int i = 0; i < datesStr.length; i++) {
			try {
				dates.add(SDF_BRAZIL.parse(datesStr[i].trim()));
			} catch (ParseException e) {
				logger.error(String.format("Erro ao carregar datas (formato inválido da data: %s).", datesStr[i]), e);
				throw e;
			}
		}
		return dates;
	}

	public static String getValidDatesListInSQL() throws ParseException {
		return getDatesListInSQL(getValidDates());
	}

	public static String getTrainingDatesListInSQL() throws ParseException {
		return getDatesListInSQL(getTrainingDates());
	}

	protected static String getDatesListInSQL(Set<Date> dates) throws ParseException {
		String dateListInSQL = "";
		for (Date date : dates) {
			dateListInSQL += ", '" + SDF_SQL_DATE_ONLY.format(date) + "'";
		}
		dateListInSQL += ")";
		return dateListInSQL.replaceFirst(", ", "(");
	}

	// public static String getMinValidDateSQL() throws ParseException {
	// return SDF_SQL_DATE_ONLY.format(getMinValidDate());
	// }
	//
	// public static String getMaxValidDateSQL() throws ParseException {
	// return SDF_SQL_DATE_ONLY.format(getMaxValidDate());
	// }
	//
	// public static Date getMinValidDate() throws ParseException {
	// Set<Date> sortedValidDates = new TreeSet<Date>(getValidDates());
	// return sortedValidDates.iterator().next();
	// }
	//
	// public static Date getMaxValidDate() throws ParseException {
	// Set<Date> sortedValidDates = new TreeSet<Date>(Collections.reverseOrder());
	// sortedValidDates.addAll(getValidDates());
	// return highDateTime(sortedValidDates.iterator().next());
	// }

	/**
	 * Retorna o valor do horário minimo para a data de referencia passada. <BR>
	 * <BR>
	 * Por exemplo se a data for "30/01/2017 as 17h:33m:12s e 299ms" a data retornada por este metodo será "30/01/2017 as
	 * 00h:00m:00s e 000ms".
	 * 
	 * @param date
	 *            de referencia.
	 * @return {@link Date} que representa o horário minimo para dia informado.
	 */
	public static Date lowDateTime(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		toOnlyDate(aux); // zera os parametros de hour,min,sec,millisec
		return aux.getTime();
	}

	// /**
	// * Retorna o valor do horário maximo para a data de referencia passada. <BR>
	// * <BR>
	// * Por exemplo se a data for "30/01/2017 as 17h:33m:12s e 299ms" a data retornada por este metodo será "30/01/2017 as
	// * 23h:59m:59s e 999ms".
	// *
	// * @param date
	// * de referencia.
	// * @return {@link Date} que representa o horário maximo para dia informado.
	// */
	// public static Date highDateTime(Date date) {
	// Calendar aux = Calendar.getInstance();
	// aux.setTime(date);
	// toFinalDate(aux); // maximiza os parametros de hour,min,sec,millisec
	// return aux.getTime();
	// }

	/**
	 * Zera todas as referencias de hora, minuto, segundo e milissegundo do {@link Calendar}.
	 * 
	 * @param date
	 *            a ser modificado.
	 */
	private static void toOnlyDate(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
	}

	// /**
	// * Maximiza todas as referencias de hora, minuto, segundo e milissegundo do {@link Calendar}.
	// *
	// * @param date
	// * a ser modificado.
	// */
	// private static void toFinalDate(Calendar date) {
	// date.set(Calendar.HOUR_OF_DAY, 23);
	// date.set(Calendar.MINUTE, 59);
	// date.set(Calendar.SECOND, 59);
	// date.set(Calendar.MILLISECOND, 999);
	// }
	//
	// public static Date incrementDay(Date date, int amount) {
	// return incrementDayOfMonth(date, amount);
	// }
	//
	// public static Date incrementDayOfMonth(Date date, int amount) {
	// return incrementDate(date, Calendar.DAY_OF_MONTH, amount);
	// }
	//
	// private static Date incrementDate(Date date, int field, int amount) {
	// Calendar calendar = new GregorianCalendar();
	// calendar.setTime(date);
	// calendar.add(field, amount);
	// return calendar.getTime();
	// }

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
