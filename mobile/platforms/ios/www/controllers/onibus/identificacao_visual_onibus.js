controllers.identificacao_visual_onibus = {
	config : function() {
		var me = controllers.identificacao_visual_onibus;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#identificacao_visual_onibus_avancar").click(function() {
			var ok = controllers.identificacao_visual_onibus.validar_componentes();
			if (ok) {
				app.trocaPagina('views/onibus/caracterizacao_onibus.html', controllers.caracterizacao_onibus);
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		util.inicializaSelect("pais_onibus", paises.listados());
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		$('#placa_estrangeira_onibus_sim').click(function() {
			$('#grupo_pais_onibus').show();
			app.setAtributo('placaEstrangeira', true);

			if (Number($('#pais_onibus').val()) == -1) {
				$("#grupo_placa_onibus").hide();
			}

		});
		$('#placa_estrangeira_onibus_nao').click(function() {
			$('#grupo_pais_onibus').hide()
			app.setAtributo('idPaisPlacaEstrangeira', null);
			app.setAtributo('placaEstrangeira', false);
			util.progressoRestartSelect("pais_onibus", "Selecione");

			$("#grupo_placa_onibus").show();
		});

		util.progressoSelect("idPaisPlacaEstrangeira", "pais_onibus", "grupo_placa_onibus");

		util.progressoInputText("placa_letras", "placa_letras_onibus", "grupo_placa_numeros_onibus");
		util.progressoInputText("placa_numeros", "placa_numeros_onibus", "grupo_identificacao_visual_onibus_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		if (util.validaRadioSimNao("placa_estrangeira_onibus", "Placa estrangeira")
				&& util.validaInputText("placa_letras_onibus", "Placa do veículo")
				&& util.validaInputText("placa_numeros_onibus", "Placa do veículo")) {

			var option = $('input[name=placa_estrangeira_onibus]:checked').val();
			if (option == 'sim') {

				var ok_placa_estrangeira = true;
				if ((Number($("#pais_onibus").val())) == 1) { // Brasil
					alert("O país do veículo de placa estrangeira não pode ser Brasil");
					ok_placa_estrangeira = false;
				}

				return ok_placa_estrangeira && util.validaSelect("pais_onibus", "País");
			}
			return true;
		}
		return false;
	}
};
