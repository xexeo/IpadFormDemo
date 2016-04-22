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

		util.progressoRadioPlacaEstrangeira("onibus");

		// País (somente placa estrangeira)
		util.progressoSelect("idPaisPlacaEstrangeira", "pais_onibus", "grupo_placa_unica_onibus");
		
		// Placa Brasil
		util.progressoInputText("placa_letras", "placa_letras_onibus", "grupo_placa_numeros_onibus");
		util.progressoInputText("placa_numeros", "placa_numeros_onibus", "grupo_identificacao_visual_onibus_avancar");
		
		// Placa única
		util.progressoInputText("placa_unica", "placa_unica_onibus", "grupo_identificacao_visual_onibus_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		if(util.validaRadioSimNao("placa_estrangeira_onibus", "Placa estrangeira")) {
			var option = $('input[name=placa_estrangeira_onibus]:checked').val();
			if(option == 'sim') {
				var ok_placa_estrangeira = true;
				if((Number($("#pais_onibus").val())) == 1) { // Brasil
					alert("O país do veículo de placa estrangeira não pode ser Brasil");
					ok_placa_estrangeira = false;
				}
				
				return ok_placa_estrangeira && util.validaSelect("pais_onibus", "País") && 
					util.validaInputText("placa_unica_onibus", "Placa do veículo");
			}
			else if(option == 'nao') {
				return util.validaInputText("placa_letras_onibus", "Placa do veículo") && 
					util.validaInputText("placa_numeros_onibus", "Placa do veículo")
			}
			
			return true;
		}
		return false;
	}
};
