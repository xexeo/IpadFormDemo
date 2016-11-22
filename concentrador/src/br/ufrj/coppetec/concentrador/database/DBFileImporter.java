/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador.database;

import br.ufrj.coppetec.concentrador.Util;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.naming.NoPermissionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mangeli
 */
public class DBFileImporter{
	private static Logger logger = LogManager.getLogger(DBFileImporter.class);
	private File folder;
	private Map<String, String> storedDbFiles; //<path, hasg>
	private Map<File, String> newDbFiles;
	private int counter;
	
	public DBFileImporter(File folder) throws Exception{
		this.folder = folder;
		getStoredFiles();
		counter=0;
	}
	
	private void getStoredFiles() throws Exception {
		/*myDB database = new myDB();
		ResultSet result;
		String qry = "SELECT * FROM importedFiles;";
		database.setStatement();
		result = database.executeQuery(qry);
		database.setStatement();
		while(result.next()){
			storedDbFiles.put(result.getString("path"), result.getString("hash"));
		}
		result.close();*/
	}
	
	public void saveFile(myDB concentrador,String path, String fileName) throws Exception{
		String sqlInsert = "INSERT INTO "+myDB.TABLE_IMPORTED_FILES+" ("
				+ myDB.TABLE_IMPORTED_FILES_ID+","+myDB.TABLE_IMPORTED_FILES_PATH
				+") VALUES ('"+fileName+"','"+path+"')";  
		logger.debug("SQL: "+sqlInsert);
		concentrador.setStatement();
		concentrador.executeStatement(sqlInsert);
	}
	
	public void readNewFiles() throws NoSuchAlgorithmException, IOException, NoPermissionException{
		FileFilter fileFilter = new FileFilter(){
			@Override
			public boolean accept(File f) {
				return f.getAbsolutePath().endsWith(".db");
			}
		};
		
		String hash = null;
		
		File[] fileList = folder.listFiles(fileFilter);
		if(fileList==null)
			throw new NoPermissionException("Verifique a permissao da pasta!");
		
		for (File f : fileList){
			myDB concentrador=null;
			ImportedDB db = null;
			boolean novoArquivo=true;
			try {
				concentrador = myDB.getInstance();
				concentrador.openTransaction();
				logger.info("Importando arquivo: "+f.getAbsolutePath());
				novoArquivo=false;
				saveFile(concentrador, f.getAbsolutePath(), f.getName());
				novoArquivo=true;
				db = new ImportedDB(f.getAbsolutePath(), null);
				counter+=db.importData(concentrador);
				db.closeConnection();
				db=null;
				concentrador.commit();
				logger.info("Arquivo importado: "+f.getAbsolutePath());
			} catch (IOException ex) {
				logger.error(String.format("Erro ao acessar o arquivo %s", f.getAbsolutePath()));
				if(concentrador!=null)concentrador.rollback();
			}catch (SQLException e) {
				if(novoArquivo)
					logger.error(String.format("Erro ao importar os dados do arquivo %s", f.getAbsolutePath()), e);
				if(concentrador!=null)concentrador.rollback();
			}catch (Exception e) {
				logger.error(String.format("Erro ao importar os dados do arquivo %s", f.getAbsolutePath()), e);
				if(concentrador!=null)concentrador.rollback();
			}finally{
				if(db!=null)
					db.closeConnection();
			}
		}
	}
	
	public int getCounter(){
		return counter;
	}
}


