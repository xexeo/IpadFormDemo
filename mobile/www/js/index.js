var app={
		
	versao: "2.0.0",
	
	user_admin : {
		usuario: 'admin',
		senha: "123"
		
	},

	login: function() {
		var retorno = false;
		var usuario = $("#usuario").val();
		var senha = $("#senha").val();
		if (usuario == app.user_admin.usuario && senha == app.user_admin.senha) {
			retorno = true;
			//$.mobile.navigate("menu.html");
			$(":mobile-pagecontainer").pagecontainer("change", "menu.html");
			pages_config.menu();
		}
		
		alert(retorno);
		return retorno;
	},
	
/*
    Application constructor
 */
    initialize: function() {
        this.bindEvents();
        this.extraConfig();
    },
/*
    bind any events that are required on startup to listeners:
*/
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },

    
/*
    this runs when the device is ready for user interaction:
*/
    onDeviceReady: function() {

    },
/*
    appends @error to the message div:
*/
    showError: function(error) {
        app.display(error);
    },

    extraConfig : function(){
        //initialize panel
        $(function () { $("[data-role=panel]").panel().enhanceWithin(); });
        $("#versao").html(this.versao);
        $("#entrar").click(this.login);
        
   }


};      // end of app

var pages_config = {
	menu : function(){
		$("#menu_nova_pesquisa").click(function() {
			//$.mobile.navigate("selecionar_tipo.html");
			$(":mobile-pagecontainer").pagecontainer("change","selecionar_tipo.html");
		});
	},
	
	 
};

$( document ).ready(function(){app.initialize()});
