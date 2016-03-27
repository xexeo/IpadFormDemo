var app = {

	versao : "2.0.0",

	user_admin : {
            usuario : 'admin',
            senha : "123"
	},

	login : function() {
            var usuario = $("#usuario").val();
            var senha = $("#senha").val();
            if (usuario == app.user_admin.usuario && senha == app.user_admin.senha) {
                    // navega para págine e executa o script de configuração depois do carregamento
                    app.trocaPagina("views/menu.html", controllers.menu)

            } else {
                    // TODO: Trocar por um popup "mais elegante"
                    var msg = "usuário e senha informados não estão cadastrados no sistema"; 
                    alert(msg);
                    myLogger.write(msg);
            }
	},
        

	logout : function() {
            $("#usuario").val('').textinput("refresh");
            $("#senha").val('').textinput("refresh");
            app.trocaPagina($('#page_login'));
	},

	/*
	 * Application constructor
	 */
	initialize : function() {
            this.bindEvents();
            this.extraConfig();
            this.baseUrl = window.location.href.replace("index.html", "");

	},



	/*
	 * bind any events that are required on startup to listeners:
	 */
	bindEvents : function() {
            document.addEventListener('deviceready', app.onDeviceReady);
            //window.addEventListener('filePluginIsReady', app.onFileSystemReady, false);    
             console.log('bindindEvents');
    	},


	/*
	 * this runs when the device is ready for user interaction:
	 */
	onDeviceReady : function() {
            console.log('device ready');
            if(device.platform == 'iOS' || (device.platform == 'browser' && device.model == 'Firefox')){
                //alert('tô no ' + device.platform);
                setTimeout(function() {
                    navigator.splashscreen.hide();
                    console.log("esperando" + device.platform);
                }, 3000);
                app.onFileSystemReady();
            }
            
            if(device.platform == 'browser' && device.model != 'Firefox'){
                alert('ATENÇÃO!!! \n Use o Firefox para fazer a simulação (cordova run browser --target=firefox)');
            }
  	},
        
        onFileSystemReady : function(){
            console.log("folder dos dados: ", cordova.file.dataDirectory);
            window.resolveLocalFileSystemURL(cordova.file.dataDirectory, function(dir){
                
                console.log('file system ready: ', dir);
                
                dir.getFile("log.txt", {create: true}, function(file){
                    console.log("arquivo de log: ", file);
                    myLogger.setLogFile(file);
                    file.createWriter(function(fileWriter){
                        myLogger.setLogWriter(fileWriter);
                    }, function(){
                        console.log('erro criando o escritor do log');
                    });
                });    
            }, function(err){
                console.log("erro no sistema de arquivos: " + err.name + " -> "+ err.message);
                alert("erro no sistema de arquivos: " + err.name + " -> "+ err.message);
            });
            
        },
        
        
        extraConfig : function() {
            // initialize panel
            $(function() {
                $("[data-role=panel]").panel().enhanceWithin();
            });
            $("#versao").html(this.versao);
            $("#entrar").click(this.login);
            $("#btn_sair").click(this.logout);
        },

	trocaPagina : function(view, controller) {
            if (controller != null) {
                $(":mobile-pagecontainer").off("pagecontainershow", controller.config).on("pagecontainershow", controller.config);
            }

            $(":mobile-pagecontainer").pagecontainer("change", app.baseUrl + view);
            myLogger.write(view);
	},

	setAtributo : function(nome, valor) {
            registro[nome] = valor;
            // TODO logar de forma dequada ao dispositivo
            try{
                myLogger.write(JSON.stringify(registro));
            } catch(e){
                console.log(e.message);
            }
            
	},

	limpaRegistro : function() {
            try{
                myLogger.write('reiniciando registro');
                registro = {};
                myLogger.write(JSON.stringify(registro));
            } catch(e){
                console.log(e.message);
            }
            
                
	},
        
        baseUrl : null,
        
        
        
}; // end of app

// Registro do momento
var registro = {};

$(document).ready(function() {
    insert_controllers.insert();
    app.initialize();
});

