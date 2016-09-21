/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mangeli
 */
public class PropertyLoader {
	private static Logger logger = LogManager.getLogger(PropertyLoader.class);
	String propertyFile;
	Properties properties;
		
	PropertyLoader(String fileName) throws IOException {
		propertyFile = fileName;
		setProperty();
	}
	
	PropertyLoader() throws IOException{
		propertyFile = "config.properties";
		setProperty();
	}
	
	private void setProperty() throws IOException{
		InputStream inputStream = null;
		Properties prop = new Properties();
		try{
			inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);
			
			if (inputStream != null){
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertyFile + "' not found in the classpath");
			}
		}catch (Exception e){
			logger.catching(e);
			throw e;
		}finally{
			inputStream.close();
		}
		
		this.properties = prop;
		logger.info("Properties file readed.");
	}
	
	public Properties getProperties() {
		return properties;
	}
	
}
