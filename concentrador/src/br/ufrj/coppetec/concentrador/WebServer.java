package br.ufrj.coppetec.concentrador;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.SocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

/**
 *
 * @author mangeli
 */
public class WebServer implements Container{
    private JTextArea observer;
    private SocketProcessor realServer;
    private SocketAddress address;
    private Connection conn;
    
    public WebServer(JTextArea observer, int serverPort) throws IOException{
        this.observer = observer;
        this.realServer = new ContainerSocketProcessor(this);
        this.conn = new SocketConnection(this.realServer);
        this.address = new InetSocketAddress(serverPort);
        this.conn.connect(address);
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
            } 
            
            switch (caminho){
                case "":
                    this.sendHello(body);
                    break;
                case "dados":
                    if(query.size() > 0 && query.get("team").equals("9393")){
                       this.storeValues(body, query); 
                    }else{
                        this.sendError(body, resp);
                    }
                    break;
                default:
                   this.sendError(body, resp);
            }
                    
            
        }catch (Exception e){
            observer.append("ERRO recebendo dados: " + e.getMessage() + "\n");
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
    
    private void sendError(PrintStream body, Response resp) throws Exception{
        resp.setStatus(Status.NOT_FOUND);
        body.close();
        throw new Exception("tentativa de acesso não autorizado " + new SimpleDateFormat("dd/MM/yyy k:m:s").format(new Date()));
    }
}
