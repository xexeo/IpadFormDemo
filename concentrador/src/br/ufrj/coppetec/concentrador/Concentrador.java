package br.ufrj.coppetec.concentrador;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.ufrj.coppetec.concentrador.database.myDB;

/**
 *
 * @author mangeli
 */

public class Concentrador {
	private static Logger logger = LogManager.getLogger(Concentrador.class);

	// static WebServer wServer;
	static Janela janela;
	public static myDB database;
	public static String trecho;
	public static String posto;
	public static String version = "1.8";

	public static void main(String[] args) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		// System.out.printf("%s.%s()%n", trace[trace.length-1].getClassName(), trace[trace.length-1].getMethodName());
		System.out.println("PNCT Concentrador versão: " + version);

		Splash splash = new Splash();
		splash.setLocationRelativeTo(null);
		splash.setVisible(true);

		// criando o banco de dados
		try {
			database = new myDB();
			database.createVolTable();
			database.createODTable();
		} catch (Exception e) {
			logger.error("Erro ao acessar o BD.", e);
			JOptionPane.showMessageDialog(null, "Erro acessando o banco de dados: \n" + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		janela = new Janela();

		// inicial o servidorWeb
		// boolean successServer = Concentrador.startServer();
		// System.out.println(successServer);

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

		janela.lblPosto_dados.setText(posto);
		janela.lblTrecho_dados.setText(trecho);
		janela.setVisible(true);

	}
	// private static boolean startServer() {
	// boolean r = true;
	// try {
	// wServer = new WebServer(janela.txtAreaLog, janela.txtPorta);
	// } catch (Exception e) {
	// r = false;
	// e.printStackTrace();
	// } finally {
	// return r;
	// }
	// }

	// private static void createVolTable() throws Exception{
	// database.setStatement();
	// String qry = "CREATE TABLE IF NOT EXISTS voltable "
	// + " (id int, posto int, pista text, data text,"
	// + " hora int, sentido text, local text, pesquisador text, origemipad int, dados text); ";
	// database.executeStatement(qry);
	// }

	// private static void createODTable() throws Exception{
	// database.setStatement();
	// String qry = "CREATE TABLE IF NOT EXISTS odTable "
	// + "(id text primary key, "
	// + "registro text, estado text); ";
	// database.executeStatement(qry);
	// }

}
