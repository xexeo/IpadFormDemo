# ipaddnit
Repositorio do projeto ipad para DNIT

* [Módulo mobile](#módulo-mobile)
* [Módulo concentrador](#módulo-concentrador)

---
## Módulo mobile 
### Ambiente de desenvolvimento
O ambiente de desenvolvimento precisa da instalação de `node.js`, `npm` (gerenciador de pacotes do `node.js`), `cordova`, e SDKs para as plataformas de deploy `android` e `iOS`. O deploy para a plataforma browser não precisa de um SDK.

* [instalação `node.js`](https://nodejs.org/en/download/package-manager/)
* [upgrade do `node.js`via `npm`] (https://davidwalsh.name/upgrade-nodejs)
* [instalação `cordova`] (https://cordova.apache.org/docs/en/latest/guide/cli/index.html#installing-the-cordova-cli)
* [instalação SDK `android` (opcional)] (https://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#requirements-and-support)
* [instalação SDK `iOS` (apenas em Macs)](https://cordova.apache.org/docs/en/latest/guide/platforms/ios/index.html#requirements-and-support)

### Plugins cordova necessários
Comandos para instalação dos plugins do cordova necessários para a compilação do aplicativo. Esses comandos devem ser executados no diretório raiz do projeto. No nosso caso, no diretório `mobile`.

* `cordova plugin add cordova-plugin-dialogs`
* `cordova plugin add cordova-plugin-device`
* `cordova plugin add cordova-plugin-file`
* `cordova plugin add cordova-plugin-itunesfilesharing`
* `cordova plugin add cordova-plugin-splashscreen`
* `cordova plugin add cordova-plugin-statusbar`
* `cordova plugin add cordova-sqlite-storage`
* `cordova plugin add cordova-plugin-battery-status`

A ausência dos plugins causa erros de execução, variáveis e métodos utilizados pelo aplicativo não estarão presentes. A lista de plugins instalados no projeto pode ser conferida com o comando `cordova plugin list`.

__ATENÇÃO:__ Os plugins podem ser desinstalados após um `pull` ou `checkout` do projeto. Verifique a instalação dos plugins em casos de erro de execução.

### Execução do aplicativo no browser sem utilizar o cordova
Esta forma de execução não tem suporte a gravação de registro. Utilizar somente para desenvolvimento das telas.
<del>Para funcionar abra o arquivo www/controllers/menu.js e comente as linhas de código a seguir:</del>

__ATENÇÃO:__ Os comentários não são mais necessários. A atual versão de desenvolvimento é capaz de detectar esse tipo de execução e fazer os tratamentos adequados para permitir o desenvolvimento da interface (em teste).

```
        if (device.platform == 'iOS'){
            _externalFolder = cordova.file.documentsDirectory;
            _dbFolder = 'cdvfile://localhost/library/LocalDatabase';
        }else if(device.platform == 'Android'){
            _externalFolder = cordova.file.externalDataDirectory;
            _dbFolder = cordova.file.applicationStorageDirectory + "databases";
        }
```
 

### Emulação do aplicativo em browser
Devido aos plugins que dependem de informações do dispositivo, a simulação do aplicativo em um browser precisa ocorrer com o 
comando:

`cordova run browser --target=firefox`

__obs:__ Chrome e Opera apresentam problemas para acesso a arquivos.

### Emulação do aplicativo no Simulator (apenas em Macs)
Todas as funcionalidades do app podem ser testadas no Simulator. Para executar o app no Simulator basta usar o seguinte comando, no diretório do projeto (o Xcode precisa estar instalado):

`cordova run ios --debug --emulator --targe=iPad-2`

#### Visualizando arquivos de dados no Simulator (apenas em Macs)
Apesar do Simulator não aparecer no iTunes, a exportação dos arquivos pode ser testada acessando-se diretamente os folders do dispositivo emulado no sistema de arquivos do computador. O caminho fica desponível nas primeiras linhas do log no debugger, como no exemplo abaixo:

```
folder dos dados:  – "file:///Users/ludes/Library/Developer/CoreSimulator/Devices/B3E97709-7AF9-4898-BBEE-C9B6B19F563B/data/Containers/Data/Application/BFA5C1FB-C6B1-4595-99F9-4C0F960FDB07/Library/NoCloud/"
````

Para acessar o folder, abra o finder, use a combinção de teclas `command + shift + G` para entrar com o endereço sem o protocolo e as duas primeiras barras. Para o endereço do exemplo acima, seria assim:

```
/Users/ludes/Library/Developer/CoreSimulator/Devices/B3E97709-7AF9-4898-BBEE-C9B6B19F563B/data/Containers/Data/Application/BFA5C1FB-C6B1-4595-99F9-4C0F960FDB07/Library/NoCloud/
````
O arquivo do banco de dados fica no folder `Library/LocalDatabase` os arquivos exportados e visíveis pelo iTunes ficam no folder `Documents`, no mesmo nível do folder `Library`.

Para visualizar os dados no arquivo sqlite, uma sugestão é usar o add-on do firefox [SQLite Manager] (https://addons.mozilla.org/pt-br/firefox/addon/sqlite-manager/), ou o software [DB Browser for SQLite] (http://sqlitebrowser.org/). 

Para visualizar os dados no formato JSON, pode-se usar o visualizador [Online JSON Viewer] (http://jsonviewer.stack.hu/).

### Mantendo apenas um app disponível no iOS
Uma solicitação do cliente é impedir o uso de outro aplicativo no dispositivo.

* [Guided Access] (https://support.apple.com/en-us/HT202612)

[Para funcionar depois do reboot o passcode do dispositivo deve estar desabilitado.](http://stackoverflow.com/questions/20864999/make-ios-application-run-at-startup)

#### Configurar o acesso guiado:
+ `Ajustes -> Geral -> Acessibilidade -> Acesso Guiado`

#### Setup das opções
+ Acesso Guiado = `On`
+ Ajustes de Código -> Definir Código do Acesso Guiado = ~`9393`~ `5833`
+ Limite de Tempo -> `[Som = Nenhum, Falar = Off]`
+ Atalho de Acessibilidade = `Off`

#### Ativar o acesso guiado:
+ Iniciar o app
+ Triplo clique no botão Home

#### Setup das opções
Após ativar o acesso guiado
+ executar triplo clique no botão `home`
+ entrar com a senha `9393`
+ Botões de hardware: 
  + Botão Repousar/Despertar = `On`
  + Botões de Volume = `Off`
  + Movimento = `Off`
  + Teclados = `On`
+ Toque = `On`
+ Limite de Tempo:
  + Limite de Tempo = `Off`

---
## Módulo concentrador
### Ambiente de Desenvolvimento
O projeto foi iniciado no NetBeans usando java 8.

### Biblioteca server http
As classes da biblioteca [Simple] (http://www.simpleframework.org/) já estão configuradas no projeto. 

* <del>[Tutorial] (http://www.simpleframework.org/doc/tutorial/tutorial.php)</del>
* [Javadocs] (http://www.simpleframework.org/doc/javadoc/index.html)

O Tutorial está desatualizado e faz uso de classes que não existem na versão atual da biblioteca. Para facilitar, fiz um programinha de exemplo, adaptando o material do tutorial com o exemplo da página do código no github [SimpleFramework] (https://github.com/ngallagher/simpleframework/blob/master/simple-demo/simple-demo/src/main/java/org/simpleframework/demo/http/WebServer.java).

```java
package simpleserver;
/**
 * @author mangeli
 */
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.SocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class SimpleServer implements Container{
	@Override
	public void handle(Request req, Response resp) {
		try{
			PrintStream body = resp.getPrintStream();
			Query query = req.getQuery();
	    
			long time = System.currentTimeMillis();
	    
			resp.setValue("Content-Type", "text/plain");
			resp.setValue("Server", "SimpleServer/1.0 (concentrador)");
			resp.setDate("Date", time);
			resp.setDate("Last-Modified", time);

			body.println("Hello World!");
	    
			//echo POST/GET field:value
			for (String q : query.keySet()){
				body.println(q + " : " + query.get(q));
			}
	    
			body.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	* @param args the command line arguments
	*/
	public static void main(String[] args) throws Exception{
	
		Container container = new SimpleServer();
		SocketProcessor server = new ContainerSocketProcessor(container);
		Connection conn = new SocketConnection(server);
		SocketAddress address = new InetSocketAddress(8080);
		System.out.println("Server running...");
		System.out.println("Press ENTER to stop it.");
		conn.connect(address);
		
		System.in.read();
		
		conn.close();
		System.out.println("Server stoped!");
	}
	
}
```

### Biblioteca SQLite

* [sqlite-jdbc] (https://bitbucket.org/xerial/sqlite-jdbc/downloads)
  * [descrição e tutorial] (https://bitbucket.org/xerial/sqlite-jdbc) 
  * [Exemplos de uso] (https://bitbucket.org/xerial/sqlite-jdbc/wiki/Usage)
