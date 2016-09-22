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
import java.util.ArrayList;
import java.util.Map;
import javax.naming.NoPermissionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mangeli
 */
public class DBFileImporter {
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
			try {
				ImportedDB db = new ImportedDB(f.getAbsolutePath(), null);
				db.importData();
				counter+=db.getCounter();
			} catch (IOException ex) {
				logger.error(String.format("Erro ao acessar o arquivo %s", f.getAbsolutePath()));
			} catch (Exception e) {
				logger.error(String.format("Erro ao importar os dados do arquivo %s", f.getAbsolutePath()), e);
			}
		}
	}
	
	public int getCounter(){
		return counter;
	}
}


