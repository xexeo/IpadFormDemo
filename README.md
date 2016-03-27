# ipaddnit
Repositorio do projeto ipad para DNIT

## plugins necessários
Comandos para instalação dos plugins do cordova necessários para a compilação do aplicativo: <br>
`cordova plugin add cordova-plugin-device` <br>
`cordova plugin add cordova-plugin-file` <br>
`cordova plugin add cordova-plugin-splashscreen` <br>
<p>A ausência dos plugins causa erros de execução, variáveis e métodos utilizados pelo aplicativo não estarão presentes. A lista de plugins instalados no projeto pode ser conferida com o comando `cordova plugin list`.</p>

## emulação do aplicativo em browser
Devido aos plugins que dependem de informações do dispositivo, a simulação do aplicativo em um browser precisa ocorrer com o 
comando: <br>
`cordova run browser --target=firefox` <br>
Chrome e Opera apresentam problemas para acesso a arquivos.


