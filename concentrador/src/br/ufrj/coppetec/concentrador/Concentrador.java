package br.ufrj.coppetec.concentrador;
/**
 *
 * @author mangeli
 */


public class Concentrador {
    static WebServer wServer;
    static Janela janela;
    public static String imagesPath;
    
    public static void main(String[] args) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        System.out.printf("%s.%s()%n", trace[trace.length-1].getClassName(), trace[trace.length-1].getMethodName());
        imagesPath = System.getProperty("user.dir") + "/images/";
        janela = new Janela();
        boolean successServer = Concentrador.startServer();
        System.out.println(successServer);
        
        janela.setVisible(true);
        
    }
    
    private static boolean startServer(){
     boolean r = true;
     try{
         wServer = new WebServer(janela.txtAreaLog, 8080);
     }catch(Exception e){
         r = false;
         e.printStackTrace();
     }finally{
         return r;
     }
    }
    
}
