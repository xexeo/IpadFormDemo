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
		$('#selTipoVeiculo_1').click(function() {
			console.log(1);
			$('#sel_tipo_veiculo_1').toggle();
			$('#sel_tipo_veiculo_2').toggle();
		});
		$('#selTipoVeiculo_2').click(function() {
			console.log(2);
			$('#sel_tipo_veiculo_1').toggle();
			$('#sel_tipo_veiculo_2').toggle();
		});
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
	}

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
