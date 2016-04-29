package br.ufrj.coppetec.concentrador;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;

/**
 *
 * @author mangeli
 */
public class WebServer implements Container{
    JTextArea observer;
    public WebServer(JTextArea observer) {
        this.observer = observer;
    }
    
    
    @Override
    public void handle(Request req, Response resp) {
        try{
            PrintStream body = resp.getPrintStream();
            Query query = req.getQuery();
            long time = System.currentTimeMillis();
            String caminho = "";
            
            
            
            resp.setValue("Content-Type", "text/plain");
            resp.setValue("Server", "Concentrador/1.0");
            resp.setDate("Date", time);
            resp.setDate("Last-Modified", time);
            
            //se existe um caminho na url
            if (req.getPath().getSegments().length > 0){
                caminho = req.getPath().getSegments()[0];
            } else {
                this.sendHello(body); //raiz do servidor
            }
            
            //testa chave
            if(caminho.equals("dados") && query.get("team").equals("9393")){
                this.storeValues(body, query);
            } else {
                resp.setStatus(Status.NOT_FOUND);
                body.close();
                throw new Exception("ERRO : Tentativa de acesso não autorizado " + new SimpleDateFormat("dd/MM/yyy k:m:s").format(new Date()));
            }
            
            
            
            
        }catch (Exception e){
            observer.append("ERRO recebendo dados:" + e.getMessage());
        }finally{
            
        }
    }
    
    private void sendHello(PrintStream body){
        body.println("Servidor do concentrador");
        body.close();
    }
    
    private void storeValues(PrintStream body, Query query){
        this.observer.append("Dados corretos");
    }
}
