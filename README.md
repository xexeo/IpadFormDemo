# ipaddnit
Repositorio do projeto ipad para DNIT

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

* `cordova plugin add cordova-plugin-device`
* `cordova plugin add cordova-plugin-file`
* `cordova plugin add cordova-plugin-splashscreen`
* `cordova plugin add cordova-plugin-statusbar`
* `cordova plugin add cordova-sqlite-storage`
* `cordova plugin add cordova-plugin-itunesfilesharing`
* `cordova plugin add cordova-plugin-dialogs`

A ausência dos plugins causa erros de execução, variáveis e métodos utilizados pelo aplicativo não estarão presentes. A lista de plugins instalados no projeto pode ser conferida com o comando `cordova plugin list`.

__ATENÇÃO:__ Os plugins podem ser desinstalados após um `pull` ou `checkout` do projeto. Verifique a instalação dos plugins em casos de erro de execução.

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

Para visualizar os dados no arquivo sqlite, uma sugestão é usar o add-on do firefox [SQLite Manager] (https://addons.mozilla.org/pt-br/firefox/addon/sqlite-manager/).

Para visualizar os dados no formato JSON, pode-se usar o visualizador [Online JSON Viewer] (http://jsonviewer.stack.hu/).

### Mantendo apenas um app disponível no iOS
Uma solicitação do cliente é impedir o uso de outro aplicativo no dispositivo.

* [Guided Access] (https://support.apple.com/en-us/HT202612)

[Para funcionar depois do reboot o passcode do dispositivo deve estar desabilitado.](http://stackoverflow.com/questions/20864999/make-ios-application-run-at-startup)
