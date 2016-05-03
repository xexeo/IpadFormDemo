controllers.caracterizacao_viagem2_carga = {
	config : function() {
		var me = controllers.caracterizacao_viagem2_carga;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#caracterizacao_viagem2_carga_avancar").click(function() {
			var ok = controllers.caracterizacao_viagem2_carga.validar_componentes();
			if (ok) {
				app.finalizaRegistro(function() {
					app.trocaPagina('views/menu.html', controllers.menu);
				});
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {
		// TODO
		
		// TODO Trocar por oncomplete!
		util.inicializaSelectTipoDeCarga("produto_carga","Selecione",lista_produtos);
		
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		// TODO
		
		// TODO Usar checkbox para desabilitar as opções e marcar não sabe -->

	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		
		// TODO
		
		return false;
	}

};
