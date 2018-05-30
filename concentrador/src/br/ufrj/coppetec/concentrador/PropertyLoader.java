package br.ufrj.coppetec.concentrador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe que carrega um arquivo no formato _properties_ para representação interna do programa.
 * O arquivo no formato `properties` é utilizado para armazenar configurações do programa.
 * 
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public class PropertyLoader {
	private static Logger logger = LogManager.getLogger(PropertyLoader.class);
	String propertyFile;	///< Caminho do arquivo
	Properties properties;	///< Estrutura de dados com o conteúdo do arquivo no formato `chave=valor`

	/**
	 * Construtor que usa o caminho padrão do arquivo.
	 * Assume o arquivo no pacote `resources` no caminho `config.properties`
	 */
	PropertyLoader() throws IOException {
		this("config.properties");
	}

	/**
	 * Construtor configurável.
	 * @param fileName caminho do arquivo no formato `properties`
	 */
	PropertyLoader(String fileName) throws IOException {
		propertyFile = fileName;
		setProperty();
	}

	/**
	 * Monta a representação interna do arquivo `properties` informado na construção do objeto.
	 */
	private void setProperty() throws IOException {
		InputStream inputStream = null;
		Properties prop = new Properties();
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertyFile + "' not found in the classpath");
			}
		} catch (Exception e) {
			logger.catching(e);
			throw e;
		} finally {
			inputStream.close();
		}

		this.properties = prop;
		logger.info("Properties file readed.");
	}

	/**
	 * Retorna a representação interna do arquivo `properties`
	 * @return a representação interna do arquivo
	 */
	public Properties getProperties() {
		return properties;
	}

}
