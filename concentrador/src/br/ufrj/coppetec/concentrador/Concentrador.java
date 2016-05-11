package br.ufrj.coppetec.concentrador;

import br.ufrj.coppeted.concentrador.database.myDB;
import javax.swing.JOptionPane;


/**
 *
 * @author mangeli
 */


public class Concentrador {
    static WebServer wServer;
    static Janela janela;
    public static String imagesPath;
    public static myDB database;
	
    public static void main(String[] args) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        System.out.printf("%s.%s()%n", trace[trace.length-1].getClassName(), trace[trace.length-1].getMethodName());
        //imagesPath = System.getProperty("user.dir") + "/images/";
        
				
        //criando o banco de dados
        try{
            database = new myDB();
            createVolTable();
			database.createODTable();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro acessando o banco de dados: \n" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
		janela = new Janela();
        
		
		
		
		//inicial o servidorWeb
		boolean successServer = Concentrador.startServer();
        System.out.println(successServer);
        
        janela.setVisible(true);
		
		LoginJanela loginJanela = new LoginJanela(janela, true);
		loginJanela.setVisible(true);
        
    }
    
    private static boolean startServer(){
     boolean r = true;
     try{
         wServer = new WebServer(janela.txtAreaLog, janela.txtPorta);
     }catch(Exception e){
         r = false;
         e.printStackTrace();
     }finally{
         return r;
     }
    }
    
    private static void createVolTable() throws Exception{
        database.setStatement();
        String qry = "CREATE TABLE IF NOT EXISTS voltable "
                + " (id int, posto int, pista text, data text,"
                + " hora int, sentido text, local text, pesquisador text, origemipad int, dados text); ";
       database.executeStatement(qry);
    }
    
//    private static void createODTable() throws Exception{
//        database.setStatement();
//        String qry = "CREATE TABLE IF NOT EXISTS odTable "
//					+ "(id text primary key, "
//                    + "registro text, estado text); ";
//		database.executeStatement(qry);
//    }
    
}
