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
            //only run controller one time    
            if ($.inArray(view, pilhaViewes) == -1){
                    $(":mobile-pagecontainer").on("pagecontainershow", controller);
                    pilhaViewes.push(view);
            }
		
            $(":mobile-pagecontainer").pagecontainer("change", view);
	}

}; // end of app

/*var pages_config = {

	menu : function() {
		$('#menu_nova_pesquisa').click(function() {
			app.trocaPagina("selecionar_tipo.html", pages_config.selecionar_tipo);
		});
	},

	selecionar_tipo : function() {
		$(".img_sel").each(function() {
			$(this).click(function() {
				veiculo_confirmar = $(this).attr('id');
				app.trocaPagina("confirmar_veiculo.html", pages_config.confirmar_veiculo);
			})
		})
	},

	confirmar_veiculo : function() {
		var image_path = "img/tipoVeiculo/" + veiculo_confirmar + ".png";
		$("#img_confirmar_veiculo").attr('src', image_path);
		// $("#conf_veic_nao").click();
	},

	nova_pesquisa : function() {
	}

};*/ //end configs

//var veiculo_confirmar = {};


// Registro do momento
var registro = {};

var pilhaViewes = [];

$(document).ready(function() {
	app.initialize()
});
