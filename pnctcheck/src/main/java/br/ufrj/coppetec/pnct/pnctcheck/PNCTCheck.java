package br.ufrj.coppetec.pnct.pnctcheck;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class PNCTCheck {

	private static Logger logger = LogManager.getLogger(PNCTCheck.class);

	private static final String FOLDER_NAME = "pnct_data";
	private static final String[] END_FILES_NAMES = { "_dadosOD.db", "_dadosOD.json", "_logOD.txt" };

	private final static int BUFFER = 512 * 1024;
	private static final String TMP_IMPORT_ZIP = System.getProperty("java.io.tmpdir") + "/processZip/";
	private static File FOLDER_TMP;

	public static void main(String[] args) {
		try {
			processFolder(FOLDER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (FOLDER_TMP.exists()) {
				try {
					FileUtils.deleteDirectory(FOLDER_TMP);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void processFolder(String folderName) throws IOException, InterruptedException {
		processFolder(new File(folderName));
	}

	private static void processFolder(File folder) throws IOException, InterruptedException {
		FOLDER_TMP = new File(TMP_IMPORT_ZIP);
		if (FOLDER_TMP.exists()) {
			FileUtils.deleteDirectory(FOLDER_TMP);
		}
		FOLDER_TMP.mkdir();
		FOLDER_TMP.deleteOnExit();

		if ((folder != null) && folder.exists()) {
			// logger.info(folder.getAbsolutePath());
			if (folder.isDirectory()) {
				File[] files = folder.listFiles();
				Arrays.sort(files);
				for (File file : files) {
					processFolder(file);
				}
				files = null;
			} else if (folder.isFile()) {
				if (folder.getName().endsWith(".zip")) {
					processFileZip(folder);
				}
			}
		}
	}

	/**
	 * @param folder
	 * @throws IOException
	 */
	private static void processFileZip(File fileZip) throws IOException {
		String posto = fileZip.getName().substring("posto".length(), fileZip.getName().length() - ".zip".length());
		// logger.info("POSTO: " + posto);
		int countLogs = 0;
		Long lengthDadosDB = null;
		List<File> files = extractFileZip(fileZip);
		Pattern pattern = Pattern.compile("^" + posto + "0\\d{2}_[a-z]{3,5}OD\\.[a-z]{2,4}$");
		for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
			File file = iterator.next();
			if (file.getParentFile().getName().equals("logs") && file.getName().startsWith("concentrador")) {
				countLogs++;
			} else if (file.getName().equals("dados.db")) {
				lengthDadosDB = file.length();
				if (lengthDadosDB == 0) {
					logger.info("POSTO: " + posto + "\tERROR: " + file.getAbsolutePath());
				}
			} else {
				boolean error = true;
				for (String endFileName : END_FILES_NAMES) {
					if (file.getName().endsWith(endFileName)) {
						error = !pattern.matcher(file.getName()).find();
						break;
					}
				}
				if (error) {
					logger.info("POSTO: " + posto + "\tERROR: " + file.getAbsolutePath());
				} else {
					// logger.info("POSTO: " + posto + "\tOK");
				}
			}
			// logger.info(file.getAbsolutePath());
			file.delete();
		}
		if (lengthDadosDB == null) {
			logger.info("POSTO: " + posto + "\tERROR: não existe o arquivo dados.db");
		}
		if (countLogs == 0) {
			logger.info("POSTO: " + posto + "\tERROR: não existe nenhum arquivo de log do concentrador");
		}
	}

	/**
	 * @param folder
	 * @return
	 * @throws IOException
	 */
	private static List<File> extractFileZip(File fileZip) throws IOException {
		List<File> files = new ArrayList<File>();

		FileInputStream fin = new FileInputStream(fileZip);
		BufferedInputStream in = new BufferedInputStream(fin);
		ZipArchiveInputStream zipIn = new ZipArchiveInputStream(in);

		ArchiveEntry entry = null;

		/** Read the tar entries using the getNextEntry method **/
		while ((entry = (ArchiveEntry) zipIn.getNextEntry()) != null) {

			/** If the entry is a directory, create the directory. **/
			File fileDest = new File(FOLDER_TMP, entry.getName());
			if (!fileDest.getParentFile().exists()) {
				fileDest.getParentFile().mkdirs();
			} else if (fileDest.exists()) {
				fileDest.delete();
			}
			if (entry.isDirectory()) {
				fileDest.mkdirs();
			}
			/**
			 * If the entry is a file,write the decompressed file to the disk and close destination stream.
			 **/
			else {
				fileDest.createNewFile();
				int count;
				byte data[] = new byte[BUFFER];

				FileOutputStream fos = new FileOutputStream(fileDest);
				BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zipIn.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.close();
				files.add(fileDest);
			}
		}

		/** Close the input stream **/

		zipIn.close();
		return files;
	}

}
