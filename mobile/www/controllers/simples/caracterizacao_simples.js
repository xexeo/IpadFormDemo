/* global util, registro */
/// @file caracterizacao_simples.js
/// @namespace controllers.caracterizacao_simples
/// Funções da tela caracterizacao_simples
controllers.caracterizacao_simples = {
	/// @funtion controllers.caracterizacao_simples.config
	/// Controla o comportamento da tela caracterizacao_simples
	/// @return {void} função sem retorno
	config : function() {
		var me = controllers.caracterizacao_simples;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	/// @function controllers.caracterizacao_simples.buttons
	/// Comportamento dos botões
	/// @return {void} função sem retorno
	buttons : function() {
		$("#caracterizacao_simples_avancar").click(function() {
			var ok = controllers.caracterizacao_simples.validar_componentes();
			if (ok) {
				app.trocaPagina('views/simples/caracterizacao_viagem_simples.html', controllers.caracterizacao_viagem_simples);
			}
		})
	},
	/// @function controllers.caracterizacao_simples.inicializaElementos
	/// Inicializa os elementos da tela
	/// @return {void} função sem retorno
	inicializaElementos : function() {

//		var lista_anos = [];
//		for (var ano = 2017; ano > 1899; ano--) {
//			lista_anos = lista_anos.concat(ano);
//		}

		var lista_anos = util.geraListaAnos();
		
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

	/// @function controllers.caracterizacao_simples.progressoTela
	/// Controla o show e hide dos elementos da tela
	/// @return {void} função sem retorno
	progressoTela : function() {

		util.progressoSelect("anoDeFabricacao", "ano_simples", "grupo_propriedade_simples");
		util.progressoSelect("idPropriedadesDoVeiculo", "propriedade_simples", "grupo_combustivel_simples");
		util.progressoSelect("idCombustivel", "combustivel_simples", "grupo_caracterizacao_simples_avancar");
	},

	/// @function controllers.caracterizacao_simples.validar_componentes
	/// Controla as validações dos componentes de tela após clicar em AVANÇAR
	/// @return {void} função sem retorno
	validar_componentes : function(id_avancar) {
		if (util.validaSelect("ano_simples", "Ano do veículo") && util.validaSelect("propriedade_simples", "Propriedade")
				&& util.validaSelect("combustivel_simples", "Combustível")) {
			return true;
		}
		return false;
	}

};
