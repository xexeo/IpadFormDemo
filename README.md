# ipaddnit
Repositorio do projeto ipad para DNIT

## plugins necessários
Comandos para instalação dos plugins do cordova necessários para a compilação do aplicativo:

* `cordova plugin add cordova-plugin-device`
* `cordova plugin add cordova-plugin-file`
* `cordova plugin add cordova-plugin-splashscreen`

A ausência dos plugins causa erros de execução, variáveis e métodos utilizados pelo aplicativo não estarão presentes. A lista de plugins instalados no projeto pode ser conferida com o comando `cordova plugin list`.

__ATENÇÃO:__ Os plugins podem ser desinstalados após um `pull` ou `checkout` do projeto. Verifique a instalação dos plugins em casos de erro de execução.

## emulação do aplicativo em browser
Devido aos plugins que dependem de informações do dispositivo, a simulação do aplicativo em um browser precisa ocorrer com o 
comando:

`cordova run browser --target=firefox`

__obs:__ Chrome e Opera apresentam problemas para acesso a arquivos.


