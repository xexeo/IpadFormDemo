package br.ufrj.coppetec.concentrador.database;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NoPermissionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Importa dados para o banco interno da aplicação.
 *
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public class DBFileImporter {
	private static Logger logger = LogManager.getLogger(DBFileImporter.class);
	private File folder;						///< Diretório com arquivos a serem importados
	@Deprecated
	private Map<String, String> storedDbFiles;	///< Estrutura para controlar arquivos já importados 
	@Deprecated
	private Map<File, String> newDbFiles;		///< Estrutura para controlar arquivos novos
	private int counter;						///< Contador de arquivos importados

	/**
	 * Inicia o importador.
	 * @param folder diretório com as arquivos a serem importados
	 * @throws Exception 
	 */
	public DBFileImporter(File folder) throws Exception {
		this.folder = folder;
		getStoredFiles();
		counter = 0;
	}

	@Deprecated
	private void getStoredFiles() throws Exception {
		// myDB database = new myDB();
		// ResultSet result;
		// String qry = "SELECT * FROM importedFiles;";
		// database.setStatement();
		// result = database.executeQuery(qry);
		// database.setStatement();
		// while (result.next()) {
		// storedDbFiles.put(result.getString("path"), result.getString("hash"));
		// }
		// result.close();
	}

	/**
	 * Registra a importação de um arquivo.
	 * @param concentrador	referência para o banco de dados
	 * @param path			caminho do arquivo importado
	 * @param fileName		nome do arquivo importado
	 * @throws Exception 
	 */
	public void saveFile(myDB concentrador, String path, String fileName) throws Exception {
		String sqlInsert = "INSERT INTO " + myDB.TABLE_IMPORTED_FILES + " (" + myDB.TABLE_IMPORTED_FILES_ID + ","
				+ myDB.TABLE_IMPORTED_FILES_PATH + ") VALUES ('" + fileName + "','" + path + "')";
		logger.debug("SQL: " + sqlInsert);
		concentrador.setStatement();
		concentrador.executeStatement(sqlInsert);
	}

	/**
	 * Fluxo principal do processo de importação de arquivos novos.
	 * Importa o arquivo, registra a importação no banco de dados, importa os dados e registra todas as operações no arquivo de log.
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws NoPermissionException 
	 */
	public void readNewFiles() throws NoSuchAlgorithmException, IOException, NoPermissionException {
		FileFilter fileFilter = new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getAbsolutePath().endsWith(".db");
			}
		};

		String hash = null;

		File[] fileList = folder.listFiles(fileFilter);
		if (fileList == null)
			throw new NoPermissionException("Verifique a permissao da pasta!");

		for (File f : fileList) {
			myDB concentrador = null;
			ImportedDB db = null;
			boolean novoArquivo = true;
			try {
				concentrador = myDB.getInstance();
				concentrador.openTransaction();
				logger.info("Importando arquivo: " + f.getAbsolutePath());
				novoArquivo = false;
				saveFile(concentrador, f.getAbsolutePath(), f.getName());
				novoArquivo = true;
				db = new ImportedDB(f.getAbsolutePath(), null);
				counter += db.importData(concentrador);
				db.closeConnection();
				db = null;
				concentrador.commit();
				logger.info("Arquivo importado: " + f.getAbsolutePath());
			} catch (IOException ex) {
				logger.error(String.format("Erro ao acessar o arquivo %s", f.getAbsolutePath()));
				if (concentrador != null)
					concentrador.rollback();
			} catch (SQLException e) {
				if (novoArquivo)
					logger.error(String.format("Erro ao importar os dados do arquivo %s", f.getAbsolutePath()), e);
				if (concentrador != null)
					concentrador.rollback();
			} catch (Exception e) {
				logger.error(String.format("Erro ao importar os dados do arquivo %s", f.getAbsolutePath()), e);
				if (concentrador != null)
					concentrador.rollback();
			} finally {
				if (db != null)
					db.closeConnection();
			}
		}
	}

	/**
	 * Retorna o contador dos arquivos importados.
	 * @return quantidade de arquivos importados.
	 */
	public int getCounter() {
		return counter;
	}
}
