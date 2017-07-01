/* global util, registro */

controllers.caracterizacao_onibus = {
	config : function() {
		var me = controllers.caracterizacao_onibus;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#caracterizacao_onibus_avancar").click(function() {
			var ok = controllers.caracterizacao_onibus.validar_componentes();
			if (ok) {
				app.trocaPagina('views/onibus/caracterizacao_viagem_onibus.html', controllers.caracterizacao_viagem_onibus);
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		var lista_anos = [];
		for (var ano = 2017; ano > 1899; ano--) {
			lista_anos = lista_anos.concat(ano);
		}
		util.inicializaSelectCustomValueAsIndex("ano_onibus", lista_anos, "Selecione");

		util.inicializaTabelaAuxiliar("propriedade_onibus", "Selecione", lista_propriedade_veiculo, "onibus");

	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		util.progressoSelect("anoDeFabricacao", "ano_onibus", "grupo_propriedade_onibus");
		util.progressoSelect("idPropriedadesDoVeiculo", "propriedade_onibus", "grupo_caracterizacao_onibus_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		if (util.validaSelect("ano_onibus", "Ano do veículo") && util.validaSelect("propriedade_onibus", "Propriedade")) {
			return true;
		}
		return false;
	}

};
