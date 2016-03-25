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
			alert("usuário e senha informados não estão cadastrados no sistema");
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

	},

	/*
	 * bind any events that are required on startup to listeners:
	 */
	bindEvents : function() {
		document.addEventListener('deviceready', this.onDeviceReady, false);
	},

	/*
	 * this runs when the device is ready for user interaction:
	 */
	onDeviceReady : function() {
		console.log('device ready');
	},
	/*
	 * appends @error to the message div:
	 */
	showError : function(error) {
		app.display(error);
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

		$(":mobile-pagecontainer").pagecontainer("change", view);
	},

	setAtributo : function(nome, valor) {
		registro[nome] = valor;
		// TODO logar de forma dequada ao dispositivo
		console.log(registro);
	},

	limpaRegistro : function() {
		registro = {};
                
	},
        
        /*
                     // it will return base domain name only. e.g. yahoo.co.in
        findBaseUrl : function() {
            var r;
                try {
                    var url = location.href;

                    var start = url.indexOf('//');
                    if (start < 0)
                        start = 0 
                    else 
                        start = start + 2;

                    var end = url.indexOf('/', start);
                    if (end < 0) end = url.length - start;

                    var baseURL = url.substring(start, end);
                    r = baseURL;
                }
                catch (arg) {
                    r = null;
                } finally{
                    return r;    
                }
                
            },
       
          */  


}; // end of app

// Registro do momento
var registro = {};

$(document).ready(function() {
	insert_controllers.insert();
	app.initialize();

});

/* all visited pages in the cache */
$(":mobile-pagecontainer").page({
	domCache : true
});

$(document).bind("mobileinit", function() {
	$.mobile.page.prototype.options.domCache = true;
});

// visited views
// //var pilhaViews = [];
