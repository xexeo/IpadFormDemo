controllers.pergunta_extra = {
	config : function() {
		var me = controllers.pergunta_extra;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#pergunta_extra_avancar").click(function() {
			var ok = controllers.pergunta_extra.validar_componentes();
			if (ok) {
				app.finalizaRegistro(function() {
					app.trocaPagina('views/menu.html', controllers.menu);
				});
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		util.inicializaSelectFrequencia('simples');

		var lista_opcoes = [ 'Sim, trocaria minha rota atual por esta', 'Não, prefiro usar a rota atual',
				'Minha rota não seria afetada', 'Não sei informar' ];
		util.inicializaSelect("select_pergunta_extra", lista_opcoes);
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		util.progressoSelect("idPerguntaExtra", "select_pergunta_extra", "grupo_pergunta_extra_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		return util.validaSelect("select_pergunta_extra", "da resposta");
	}

};
