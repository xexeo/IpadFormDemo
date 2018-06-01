/* global util, registro */
/// @file caracterizacao_onibus.js
/// @namespace controllers.caracterizacao_onibus
/// Funçoes da tela caracterizacao_onibus
controllers.caracterizacao_onibus = {
	/// @function controllers.caracterizacao_onibus.config
	/// Controla o comportamento da tela
	/// @return {void} função sem retorno
	config : function() {
		var me = controllers.caracterizacao_onibus;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	/// @function controllers.caracterizacao_onibus.buttons
	/// Comportamento dos botões
	/// @return {void} função sem retorno
	buttons : function() {
		$("#caracterizacao_onibus_avancar").click(function() {
			var ok = controllers.caracterizacao_onibus.validar_componentes();
			if (ok) {
				app.trocaPagina('views/onibus/caracterizacao_viagem_onibus.html', controllers.caracterizacao_viagem_onibus);
			}
		})
	},

	/// @function controllers.caracterizacao_onibus.inicializaElementos
	/// Inicializa os elementos da tela
	/// @return {void} função sem retorno
	inicializaElementos : function() {

//		var lista_anos = [];
//		for (var ano = 2017; ano > 1899; ano--) {
//			lista_anos = lista_anos.concat(ano);
//		}

		var lista_anos = util.geraListaAnos();
		
		util.inicializaSelectCustomValueAsIndex("ano_onibus", lista_anos, "Selecione");

		util.inicializaTabelaAuxiliar("propriedade_onibus", "Selecione", lista_propriedade_veiculo, "onibus");

	},

	/// @function controllers.caracterizacao_onibus.progressoTela
	/// Controla o show e hide dos elementos da tela
	/// @return {void} função sem retorno
	progressoTela : function() {

		util.progressoSelect("anoDeFabricacao", "ano_onibus", "grupo_propriedade_onibus");
		util.progressoSelect("idPropriedadesDoVeiculo", "propriedade_onibus", "grupo_caracterizacao_onibus_avancar");
	},

	/// @function controllers.caracterizacao_onibus.validar_componentes
	/// Controla as validações dos componentes de tela após clicar em AVANÇAR
	/// @return {bool} true se os valores informados forem válidos
	validar_componentes : function(id_avancar) {
		if (util.validaSelect("ano_onibus", "Ano do veículo") && util.validaSelect("propriedade_onibus", "Propriedade")) {
			return true;
		}
		return false;
	}

};
