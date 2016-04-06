controllers.identificacao_visual_simples = {
	config : function() {
		var me = controllers.identificacao_visual_simples;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#identificacao_visual_simples_avancar").click(function() {
			var ok = controllers.identificacao_visual_simples.validar_componentes();
			if(ok) {
				app.trocaPagina('views/simples/caracterizacao_simples.html', controllers.caracterizacao_simples);
			}
		})
	},
	
	//Inicializa os elementos da tela
	inicializaElementos : function() {
		
		util.inicializaSelect("pais_simples", paises.listados());
	},

	//Controla o show e hide dos elementos da tela
	progressoTela : function() {
		
		util.progressoRadioSimNao("reboque", "reboque_simples", "grupo_placa_estrangeira_simples");
		
		$('#placa_estrangeira_simples_sim').click(function() {
			$('#grupo_pais_simples').show();
			app.setAtributo('placa_estrangeira', true);

			if (Number($('#pais_simples').val()) == -1) {
				$("#grupo_placa_simples").hide();
			}

		});
		$('#placa_estrangeira_simples_nao').click(function() {
			$('#grupo_pais_simples').hide()
			app.setAtributo('pais', null);
			app.setAtributo('placa_estrangeira', false);

			$("#pais_simples option:contains('Selecione')").prop({
				selected : true
			});
			$("select#pais_simples").selectmenu("refresh", true);

			$("#grupo_placa_simples").show();
		});
		
		util.progressoSelect("pais", "pais_simples", "grupo_placa_simples");
		
		util.progressoInputText("placa_letras", "placa_letras_simples", "grupo_placa_numeros_simples");
		util.progressoInputText("placa_numeros", "placa_numeros_simples", "grupo_identificacao_visual_simples_avancar");
	},
	
	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		
		if(util.validaRadioSimNao("reboque_simples", "Reboque") && 
				util.validaRadioSimNao("placa_estrangeira_simples", "Placa estrangeira") &&
				util.validaInputText("placa_letras_simples", "Placa do veículo") &&
				util.validaInputText("placa_numeros_simples", "Placa do veículo")) {
			
			var option = $('input[name=placa_estrangeira_simples]:checked').val();
			if(option == 'sim') {
				
				var ok_placa_estrangeira = true;
				if((Number($("#pais_simples").val())) == 0) { // Brasil
					alert("O país do veículo de placa estrangeira não pode ser Brasil");
					ok_placa_estrangeira = false;
				}
				
				return ok_placa_estrangeira && util.validaSelect("pais_simples", "País");
			}
			
			return true;
		}
		return false;
	}
	
};
