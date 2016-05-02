package br.ufrj.coppetec.concentrador;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	private JTextField txtPort;
    private SocketProcessor realServer;
    private SocketAddress address;
    private Connection conn;
	private int listeningPort;
    
    public WebServer(JTextArea observer, JTextField txtPort) throws IOException{
        this.observer = observer;
		this.txtPort = txtPort;
        this.realServer = new ContainerSocketProcessor(this);
        this.conn = new SocketConnection(this.realServer);
		
		int initialPort = 8000;
		int finalPort = 8090;
        this.listeningPort = 0;
		
		for(int port = initialPort; port <= finalPort; port++){
			try{
				this.address = new InetSocketAddress(port);
				this.conn.connect(address);
				this.listeningPort = port;
				break;
			}catch(Exception e){
				observer.append("Erro tentando escutar a porta " + port + ": " + e.getMessage() + "\n");
				txtPort.setText("");
			}
		}
		if(listeningPort != 0){
			observer.append("Servidor escutando a porta " + this.listeningPort + "\n");
			txtPort.setText(Integer.toString(listeningPort));
		}
		
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
