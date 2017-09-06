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
		
		if (logins.user_logado != null && (logins.user_logado.usr == '019' || logins.user_logado.usr == '149'
										|| logins.user_logado.usr == '019t' || logins.user_logado.usr == '149t')) {
			$("#grupo_pergunta_extra_p1").show();
			util.inicializaSelect("select_pergunta_extra_p1", lista_opcoes);
			if (logins.user_logado != null && (logins.user_logado.usr == '149' || logins.user_logado.usr == '149t')) {
				util.inicializaSelect("select_pergunta_extra_p2", lista_opcoes);
			}
		}
		else if (logins.user_logado != null && (logins.user_logado.usr == '040' || logins.user_logado.usr == '040t')) {
			$("#grupo_pergunta_extra_p3").show();
			util.inicializaSelect("select_pergunta_extra_p3", lista_opcoes);
		}
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		if (logins.user_logado != null && (logins.user_logado.usr == '019' || logins.user_logado.usr == '019t')) {
			util.progressoSelect("idPerguntaExtra", "select_pergunta_extra_p1", "grupo_pergunta_extra_avancar");
		}
		else if (logins.user_logado != null && (logins.user_logado.usr == '149' || logins.user_logado.usr == '149t')) {
			util.progressoSelect("idPerguntaExtra", "select_pergunta_extra_p1", "grupo_pergunta_extra_p2");
			util.progressoSelect("idPerguntaExtra2", "select_pergunta_extra_p2", "grupo_pergunta_extra_avancar");
		}
		else if (logins.user_logado != null && (logins.user_logado.usr == '040' || logins.user_logado.usr == '040t')) {
			util.progressoSelect("idPerguntaExtra", "select_pergunta_extra_p3", "grupo_pergunta_extra_avancar");
		}
		else {
			// Não deveria entrar aqui. Mostra o botão avançar para finalizar a pesquisa e não ficar travado.
			$("#grupo_pergunta_extra_avancar").show();
		}
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		if (logins.user_logado != null && (logins.user_logado.usr == '019' || logins.user_logado.usr == '019t')) {
			return util.validaSelect("select_pergunta_extra_p1", "Pergunta Extra");
		}
		else if (logins.user_logado != null && (logins.user_logado.usr == '149' || logins.user_logado.usr == '149t')) {
			var ok = util.validaSelect("select_pergunta_extra_p1", "Pergunta Extra 1");
			ok = ok && util.validaSelect("select_pergunta_extra_p2", "Pergunta Extra 2");
			return ok;
		}
		else if (logins.user_logado != null && (logins.user_logado.usr == '040' || logins.user_logado.usr == '040t')) {
			return util.validaSelect("select_pergunta_extra_p3", "Pergunta Extra");
		}
		return false;
	}

};
