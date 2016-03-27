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

A ausência dos plugins causa erros de execução, variáveis e métodos utilizados pelo aplicativo não estarão presentes. A lista de plugins instalados no projeto pode ser conferida com o comando `cordova plugin list`.

__ATENÇÃO:__ Os plugins podem ser desinstalados após um `pull` ou `checkout` do projeto. Verifique a instalação dos plugins em casos de erro de execução.

### Emulação do aplicativo em browser
Devido aos plugins que dependem de informações do dispositivo, a simulação do aplicativo em um browser precisa ocorrer com o 
comando:

`cordova run browser --target=firefox`

__obs:__ Chrome e Opera apresentam problemas para acesso a arquivos.


