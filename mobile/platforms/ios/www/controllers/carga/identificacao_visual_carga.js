controllers.identificacao_visual_carga = {
	config : function() {
		var me = controllers.identificacao_visual_carga;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#identificacao_visual_carga_avancar").click(function() {
			var ok = controllers.identificacao_visual_carga.validar_componentes();
			if(ok) {
				app.trocaPagina('views/carga/caracterizacao_carga.html', controllers.caracterizacao_carga);
			}
		})
	},
	
	//Inicializa os elementos da tela
	inicializaElementos : function() {
		
		//TODO inicializar tipo de carroceria
		
		util.inicializaSelect("pais_carga", paises.listados());
	},

	//Controla o show e hide dos elementos da tela
	progressoTela : function() {

		//TODO tipo de carroceria
		
		$('#placa_estrangeira_carga_sim').click(function() {
			$('#grupo_pais_carga').show();
			app.setAtributo('placa_estrangeira', true);

			if (Number($('#pais_carga').val()) == -1) {
				$("#grupo_placa_carga").hide();
			}

		});
		$('#placa_estrangeira_carga_nao').click(function() {
			$('#grupo_pais_carga').hide()
			app.setAtributo('pais', null);
			app.setAtributo('placa_estrangeira', false);

			$("#pais_carga option:contains('Selecione')").prop({
				selected : true
			});
			$("select#pais_carga").selectmenu("refresh", true);

			$("#grupo_placa_carga").show();
		});
		
		util.progressoInputText("placa_letras", "placa_letras_carga", "grupo_placa_numeros_carga");
		util.progressoInputText("placa_numeros", "placa_numeros_carga", "grupo_placa_vermelha_carga");
		
		util.progressoRadioSimNao("placa_vermelha", "placa_vermelha_carga", "grupo_carga_perigosa_carga");
		
		util.progressoRadioSimNao("carga_perigosa", "carga_perigosa_carga", "grupo_identificacao_visual_carga_avancar");
	},
	
	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		
		//TODO acrescentar validação tipo de carroceria
		if(util.validaRadioSimNao("placa_estrangeira_carga", "Placa estrangeira") &&
				util.validaInputText("placa_letras_carga", "Placa do veículo") &&
				util.validaInputText("placa_numeros_carga", "Placa do veículo") &&
				util.validaRadioSimNao("placa_vermelha_carga", "Placa vermelha") &&
				util.validaRadioSimNao("carga_perigosa_carga", "Carga perigosa") ) {
			
			var option = $('input[name=placa_estrangeira_carga]:checked').val();
			if(option == 'sim') {
				
				var ok_placa_estrangeira = true;
				if((Number($("#pais_carga").val())) == 1) { // Brasil
					alert("O país do veículo de placa estrangeira não pode ser Brasil");
					ok_placa_estrangeira = false;
				}
				
				return ok_placa_estrangeira && util.validaSelect("pais_carga", "País");
			}
			return true;
		}
		return false;
	}
};
