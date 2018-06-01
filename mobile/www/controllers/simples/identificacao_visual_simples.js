/// @file identificacao_visual_simples.js
/// @namespace controllers.identificacao_visual_simples
/// Funções da tela identificacao_visual_simples
controllers.identificacao_visual_simples = {
	/// @function controllers.identificacao_visual_simples.config
	/// Controla o comportamento da tela
	/// @return {void} função sem retorno
	config : function() {
		var me = controllers.identificacao_visual_simples;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},
	
	/// @function controllers.identificacao_visual_simples.buttons
	/// Comportamento dos botões
	/// @return {void} função sem retorno
	buttons : function() {
		$("#identificacao_visual_simples_avancar").click(function() {
			var ok = controllers.identificacao_visual_simples.validar_componentes();
			if (ok) {
				app.trocaPagina('views/simples/caracterizacao_simples.html', controllers.caracterizacao_simples);
			}
		})
	},
	
	/// @function controllers.identificacao_visual_simples.inicializaElementos
	/// Inicializa os elementos da tela
	/// @return {void} função sem retorno
	inicializaElementos : function() {
		util.inicializaSelectPais("idPaisPlacaEstrangeira", "pais_simples", false);
		util.inicializaPlacas("simples");
	},

	/// @function controllers.identificacao_visual_simples.progressoTela
	/// Controla o show e hide dos elementos da tela
	/// @return {void} função sem retorno
	progressoTela : function() {

		util.progressoRadioSimNao("possuiReboque", "reboque_simples", "grupo_placa_estrangeira_simples");

		util.progressoRadioPlacaEstrangeira("simples");

		// País (somente placa estrangeira)
		util.progressoSelect("idPaisPlacaEstrangeira", "pais_simples", "grupo_placa_unica_simples");

		// Placa Brasil
		util.progressoPlacaNumeros("simples", "grupo_identificacao_visual_simples_avancar");

		// Placa única
		util.progressoInputText("placa_unica", "placa_unica_simples", "grupo_identificacao_visual_simples_avancar");
	},

	/// @function controllers.identificacao_visual_simples.validar_componentes
	/// Controla as validações dos componentes de tela após clicar em AVANÇAR
	/// @return {void} função sem retorno
	validar_componentes : function(id_avancar) {

		if (util.validaRadioSimNao("reboque_simples", "Reboque")
				&& util.validaRadioSimNao("placa_estrangeira_simples", "Placa estrangeira")) {

			var option = $('input[name=placa_estrangeira_simples]:checked').val();
			if (option == 'sim') {
				var ok_placa_estrangeira = true;
				if ((Number($("#pais_simples").val())) == 1) { // Brasil
					alert("O país do veículo de placa estrangeira não pode ser Brasil");
					ok_placa_estrangeira = false;
				}

				return ok_placa_estrangeira && util.validaSelect("pais_simples", "País")
						&& util.validaInputText("placa_unica_simples", "Placa do veículo");
			} else if (option == 'nao') {
				return util.validaInputText("placa_letras_simples", "Placa do veículo (letras)")
						&& util.validaInputText("placa_numeros_simples", "Placa do veículo (números)")
						&& util.validaLenInputText("placa_letras_simples", "Placa do veículo (letras)")
						&& util.validaLenInputText("placa_numeros_simples", "Placa do veículo (números)");
			}

			return true;
		}
		return false;
	}

};
