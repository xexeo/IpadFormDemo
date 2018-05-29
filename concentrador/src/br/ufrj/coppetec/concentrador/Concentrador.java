package br.ufrj.coppetec.concentrador;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.ufrj.coppetec.concentrador.database.myDB;
import java.awt.Color;

/**
 * Classe principal do sistema, representa o fluxo de inicialização e mantém as informações de identificação do posto 
 * 
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */

public class Concentrador {
	private static Logger logger = LogManager.getLogger(Concentrador.class);

	// static WebServer wServer;
	static Janela janela;							///< Janela da interface com o usuário
	public static myDB database;					///< Classe de conexão com o banco de dados
	public static String trecho;					///< Trecho do posto de pesquisa
	public static String posto;					///< Identificação do posto de pesquisa
	public static String version = "3.6";			///< Versão do programa
	public static int dbVersion = 2;				///< Versão do banco de dados
	public static Properties configuration;		///< Configurações do programa
	public static boolean treinamento = false;	///< Identificação do modo de treinamento ou pesquisa

	
	/**
	 * Método do fluxo principal de execução do sistema.
	 * Exibe splash screen com a versão atual do sistema, carrega configurações, inicia conexão com o banco de dados, 
	 * exibe a janela de login e a janela principal da interface do usuário
	 * 
	 * @param args o programa é executado sem parâmetros
	 */
	public static void main(String[] args) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		
		logger.info("PNCT Concentrador versão: " + version);

		Splash splash = new Splash();
		splash.setLocationRelativeTo(null);
		splash.setVersionLabet(version);
		splash.setVisible(true);

		// lendo arquivo properties
		try {
			PropertyLoader propLoader = new PropertyLoader();
			configuration = propLoader.getProperties();
			Util.populateInputLimits();
		} catch (IOException ex) {
			logger.catching(ex);
			System.exit(1);
		}

		// criando o banco de dados
		try {
			database = myDB.getInstance();
			database.initDatabaseTables();

		} catch (Exception e) {
			logger.error("Erro ao acessar o BD.", e);
			JOptionPane.showMessageDialog(null, "Erro acessando o banco de dados: \n" + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		janela = new Janela();

		
		LoginJanela loginJanela = new LoginJanela(janela, true);
		loginJanela.setLocationRelativeTo(null);
		loginJanela.setVisible(true);

		// wait
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		splash.setVisible(false);
		
		janela.setTitle(janela.getTituloJanela());
		janela.setAlertaTreinamento();
		
		try {
			janela.initDatesToShow();
			janela.lblPosto_dados.setText(posto);
			janela.lblTrecho_dados.setText(trecho);
			janela.setVisible(true);
		} catch (ParseException e) {
			logger.error("Erro ao carregar datas válidas.", e);
			JOptionPane.showMessageDialog(null, "Erro ao carregar datas válidas." + e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Retorna o posto de pesquisa sob operação do sistema
	 * 
	 * @return Integer posto de pesquisa sob operação do sistema
	 */
	public static int getPostoInt() {
		return Integer.parseInt(Concentrador.posto);
	}
	
}
