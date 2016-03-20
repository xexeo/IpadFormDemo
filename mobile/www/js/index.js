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
			app.trocaPagina("menu.html", controllers.menu)

		} else {
			//TODO: Trocar por um popup "mais elegante"
			alert("usuário e senha informados não estão cadastrados no sistema");
		}
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
	},

	trocaPagina : function(view, controller) {
            //only run controller.config one time    
            //if ($.inArray(view, pilhaViews) == -1){
                    $(":mobile-pagecontainer").off("pagecontainershow", controller.config).on("pagecontainershow", controller.config);
            //        pilhaViews.push(view);
            //}
            //$( ":mobile-pagecontainer" ).pagecontainer( "load", pageUrl, { showLoadMsg: false } );
            
            $(":mobile-pagecontainer").pagecontainer("change", view);
	}

}; // end of app


// Registro do momento
var registro = {};


$(document).ready(function() {
        insert_controllers.insert();
	app.initialize();
        
});


/* all visited pages in the cache */
$(":mobile-pagecontainer").page({ domCache: true });

$(document).bind("mobileinit", function(){
    $.mobile.page.prototype.options.domCache = true;
});

//visited views
////var pilhaViews = [];