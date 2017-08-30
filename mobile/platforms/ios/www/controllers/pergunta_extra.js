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
		
		if (logins.user_logado != null && (logins.user_logado.usr == '111' || logins.user_logado.usr == 'Teste')) {
			$("#grupo_pergunta_extra_p1").show();
			util.inicializaSelect("select_pergunta_extra_p1", lista_opcoes);
		}
		else if (logins.user_logado != null && logins.user_logado.usr == '148') {
			$("#grupo_pergunta_extra_p2").show();
			util.inicializaSelect("select_pergunta_extra_p2", lista_opcoes);
		}
		else if (logins.user_logado != null && logins.user_logado.usr == '156') {
			$("#grupo_pergunta_extra_p3").show();
			util.inicializaSelect("select_pergunta_extra_p3", lista_opcoes);
		}
		else if (logins.user_logado != null && (logins.user_logado.usr == '184' || logins.user_logado.usr == '185')) {
			$("#grupo_pergunta_extra_p4").show();
			util.inicializaSelect("select_pergunta_extra_p4", lista_opcoes);
		}
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		if (logins.user_logado != null && logins.user_logado.usr == '111') {
			util.progressoSelect("idPerguntaExtra", "select_pergunta_extra_p1", "grupo_pergunta_extra_avancar");
		}
		else if (logins.user_logado != null && logins.user_logado.usr == '148') {
			util.progressoSelect("idPerguntaExtra", "select_pergunta_extra_p2", "grupo_pergunta_extra_avancar");
		}
		else if (logins.user_logado != null && logins.user_logado.usr == '156') {
			util.progressoSelect("idPerguntaExtra", "select_pergunta_extra_p3", "grupo_pergunta_extra_avancar");
		}
		else if (logins.user_logado != null && (logins.user_logado.usr == '184' || logins.user_logado.usr == '185')) {
			util.progressoSelect("idPerguntaExtra", "select_pergunta_extra_p4", "grupo_pergunta_extra_avancar");
		}
		else {
			// Não deveria entrar aqui. Mostra o botão avançar para finalizar a pesquisa e não ficar travado.
			$("#grupo_pergunta_extra_avancar").show();
		}
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		return util.validaSelect("select_pergunta_extra", "da resposta");
	}

};
