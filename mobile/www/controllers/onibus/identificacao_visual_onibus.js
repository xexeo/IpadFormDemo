/// @file identificacao_visual_onibus.
/// @namespace controllers.identificacao_visual_onibus
/// Funções da tela identificacao_visual_onibus
controllers.identificacao_visual_onibus = {
	/// @function controllers.identificacao_visual_onibus.config
	/// Controla o comportamento da tela
	/// @return {void} função sem retorno
	config : function() {
		var me = controllers.identificacao_visual_onibus;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	/// @function controllers.identificacao_visual_onibus.buttons
	/// Comportamento dos botões
	/// @return {void} função sem retorno
	buttons : function() {
		$("#identificacao_visual_onibus_avancar").click(function() {
			var ok = controllers.identificacao_visual_onibus.validar_componentes();
			if (ok) {
				app.trocaPagina('views/onibus/caracterizacao_onibus.html', controllers.caracterizacao_onibus);
			}
		})
	},

	/// @function controllers.identificacao_visual_onibus.inicializaElementos
	/// Inicializa os elementos da tela
	/// @return {void} função sem retorno
	inicializaElementos : function() {
		util.inicializaSelectPais("idPaisPlacaEstrangeira", "pais_onibus", false);
		util.inicializaPlacas("onibus");
	},

	/// @function controllers.identificacao_visual_onibus.progressoTela
	/// Controla o show e hide dos elementos da tela
	/// @return {void} função sem retorno
	progressoTela : function() {

		util.progressoRadioPlacaEstrangeira("onibus");

		// País (somente placa estrangeira)
		util.progressoSelect("idPaisPlacaEstrangeira", "pais_onibus", "grupo_placa_unica_onibus");

		// Placa Brasil
		util.progressoPlacaNumeros("onibus", "grupo_identificacao_visual_onibus_avancar");

		// Placa única
		util.progressoInputText("placa_unica", "placa_unica_onibus", "grupo_identificacao_visual_onibus_avancar");
	},

	/// @function controllers.identificacao_visual_onibus.validar_componentes
	/// Controla as validações dos componentes de tela após clicar em AVANÇAR
	/// @return {bool} true se os valores informados forem válidos
	validar_componentes : function(id_avancar) {

		if (util.validaRadioSimNao("placa_estrangeira_onibus", "Placa estrangeira")) {
			var option = $('input[name=placa_estrangeira_onibus]:checked').val();
			if (option == 'sim') {
				var ok_placa_estrangeira = true;
				if ((Number($("#pais_onibus").val())) == 1) { // Brasil
					alert("O país do veículo de placa estrangeira não pode ser Brasil");
					ok_placa_estrangeira = false;
				}

				return ok_placa_estrangeira && util.validaSelect("pais_onibus", "País")
						&& util.validaInputText("placa_unica_onibus", "Placa do veículo");
			} else if (option == 'nao') {
				return util.validaInputText("placa_letras_onibus", "Placa do veículo (letras)")
						&& util.validaInputText("placa_numeros_onibus", "Placa do veículo (números)")
						&& util.validaLenInputText("placa_letras_onibus", "Placa do veículo (letras)")
						&& util.validaLenInputText("placa_numeros_onibus", "Placa do veículo (números)");
			}

			return true;
		}
		return false;
	}
};
