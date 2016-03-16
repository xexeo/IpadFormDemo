var app={
		
	versao: "2.0.0",
	
	user_admin : {
		usuario: 'admin',
		senha: "123"
		
	},

	login: function() {
		var usuario = $("#usuario").val();
		var senha = $("#senha").val();
		if (usuario == app.user_admin.usuario && senha == app.user_admin.senha) {
			//navega para págine e executa o script de configuração depois do carregamento
         app.trocaPagina("menu.html", pages_config.menu)
			
		} else {
         alert("usuário e senha informados não estão cadastrados no sistema");               
      }
		
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
   },

   trocaPagina : function (pagina, configs){
      $(":mobile-pagecontainer").on("pagecontainershow", configs);
      $(":mobile-pagecontainer").pagecontainer("change", pagina);
   }


};      // end of app

var pages_config = {
	
   menu : function(){
		$('#menu_nova_pesquisa').click( function(){
         app.trocaPagina("selecionar_tipo.html");
      });
	},

   nova_pesquisa : function(){},

   
   
	
	 
};

$( document ).ready(function(){app.initialize()});
