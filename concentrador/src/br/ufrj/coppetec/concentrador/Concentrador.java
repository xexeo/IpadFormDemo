package br.ufrj.coppetec.concentrador;
/**
 *
 * @author mangeli
 */


public class Concentrador {
    static WebServer wServer;
    static Janela janela;
    
    public static void main(String[] args) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        System.out.printf("%s.%s()%n", trace[trace.length-1].getClassName(), trace[trace.length-1].getMethodName());
        janela = new Janela();
        janela.setVisible(true);
        
    }
    
    private static boolean startServer(){
     boolean r = true;
     try{
         wServer = new WebServer(janela.txtAreaLog);
     }catch(Exception e){
         r = false;
         e.printStackTrace();
     }finally{
         return r;
     }
    }
    
}
