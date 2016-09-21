/* global util, registro */

controllers.caracterizacao_simples = {
	config : function() {
		var me = controllers.caracterizacao_simples;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#caracterizacao_simples_avancar").click(function() {
			var ok = controllers.caracterizacao_simples.validar_componentes();
			if (ok) {
				app.trocaPagina('views/simples/caracterizacao_viagem_simples.html', controllers.caracterizacao_viagem_simples);
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		var lista_anos = [];
		for (var ano = 2016; ano > 1899; ano--) {
			lista_anos = lista_anos.concat(ano);
		}
		util.inicializaSelectCustomValueAsIndex("ano_simples", lista_anos, "Selecione");

		util.inicializaTabelaAuxiliar("propriedade_simples", "Selecione", lista_propriedade_veiculo, "simples");

		if (registro.tipo == 'm') {
			// Moto
			util.inicializaTabelaAuxiliar("combustivel_simples", "Selecione", lista_combustivel, "moto");
		}
		else {
			// Veículo simples comum
			util.inicializaTabelaAuxiliar("combustivel_simples", "Selecione", lista_combustivel, "simples");
		}
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		util.progressoSelect("anoDeFabricacao", "ano_simples", "grupo_propriedade_simples");
		util.progressoSelect("idPropriedadesDoVeiculo", "propriedade_simples", "grupo_combustivel_simples");
		util.progressoSelect("idCombustivel", "combustivel_simples", "grupo_caracterizacao_simples_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		if (util.validaSelect("ano_simples", "Ano do veículo") && util.validaSelect("propriedade_simples", "Propriedade")
				&& util.validaSelect("combustivel_simples", "Combustível")) {
			return true;
		}
		return false;
	}

};
