/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mangeli
 */
public final class Util {
	
	private static Logger logger = LogManager.getLogger(Util.class);
	
	private static HashMap<String,Integer> inputLimits = new HashMap();
	
	private Util(){
	}
	
	static public void populateInputLimits(){
		inputLimits.put("placaEstrangeira",
						Integer.parseInt(Concentrador.configuration.getProperty("placaEstrangeira", "20").trim()));
		inputLimits.put("frequenciaQtd",
						Integer.parseInt(Concentrador.configuration.getProperty("frequenciaQtd", "3").trim()));
		inputLimits.put("numeroDePessoasNoVeiculo",
						Integer.parseInt(Concentrador.configuration.getProperty("numeroDePessoasNoVeiculo", "2").trim()));
		inputLimits.put("pesoDaCarga",
						Integer.parseInt(Concentrador.configuration.getProperty("pesoDaCarga", "5").trim()));
		inputLimits.put("valorDoFrete",
						Integer.parseInt(Concentrador.configuration.getProperty("valorDoFrete", "5").trim()));
		inputLimits.put("valorDaCarga",
						Integer.parseInt(Concentrador.configuration.getProperty("valorDaCarga", "15").trim()));
		inputLimits.put("pesquisador_1",
						Integer.parseInt(Concentrador.configuration.getProperty("pesquisador_1", "20").trim()));
		inputLimits.put("pesquisador_2",
						Integer.parseInt(Concentrador.configuration.getProperty("pesquisador_2", "20").trim()));
		inputLimits.put("local",
						Integer.parseInt(Concentrador.configuration.getProperty("local", "20").trim()));
	}
	
	static public String getSQLiteBoolean(String val) throws Exception {
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

	static public String getSQLiteIntLimited(String val, String fieldName) throws Exception{
		if (inputLimits.isEmpty()) {
			String msg = "inputLimits variable is not initialized.";
			logger.info(msg);
			throw new Exception(msg);
		}
		String r = null;
		if (val != null){
			Integer limit = inputLimits.get(fieldName);
			if (limit == null){
				limit = 2;
			}
			r = cutString(val, limit);
		}
		return r;
	}
	
	static public String getSQLiteRealLimited(String val, String fieldName) throws Exception{
		if (inputLimits.isEmpty()) {
			String msg = "inputLimits variable is not initialized.";
			logger.info(msg);
			throw new Exception(msg);
		}
		String r = null;
		if (val != null){
			Integer limit = inputLimits.get(fieldName);
			if (limit == null){
				limit = 2;
			} 
			//if is a decimal separator
			if (val.indexOf('.') != -1){
				String intPart = cutString(val.split(".")[0], limit);
				r = intPart + "." + val.split(".")[1];
			
			} else {
				r = cutString(val, limit);
			}
			
			
		}
		return r;
	}
	
	public static String getStringLimited(String val, String fieldName) throws Exception{
		if (inputLimits.isEmpty()) {
			String msg = "inputLimits variable is not initialized.";
			logger.info(msg);
			throw new Exception(msg);
		}
		String r = null;
		if (val != null){
			Integer limit = inputLimits.get(fieldName);
			if (limit == null){
				limit = 20;
			}
			if (val.length() > limit){
				r = val.substring(0, limit-1);
			} else{
				r = val;
			}
		}
		return r;
	}
	
	private static String cutString(String str, int maxLength){
		String r;
		if (str.length() > maxLength){
				char[] chars = new char[maxLength];
				Arrays.fill(chars, '9');
				r = new String(chars);
			} else {
				r = str;
			}
		return r;
	}
	
	public static String getFileChecksum(MessageDigest digest, File file) throws IOException {
    //Get file input stream for reading the file content
    FileInputStream fis = new FileInputStream(file);
     
    //Create byte array to read data in chunks
    byte[] byteArray = new byte[1024];
    int bytesCount = 0; 
      
    //Read file data and update in message digest
    while ((bytesCount = fis.read(byteArray)) != -1) {
        digest.update(byteArray, 0, bytesCount);
    };
     
    //close the stream; We don't need it now.
    fis.close();
     
    //Get the hash's bytes
    byte[] bytes = digest.digest();
     
    //This bytes[] has bytes in decimal format;
    //Convert it to hexadecimal format
    StringBuilder sb = new StringBuilder();
    for(int i=0; i< bytes.length ;i++)
    {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    }
     
    //return complete hash
   return sb.toString();
}
}
