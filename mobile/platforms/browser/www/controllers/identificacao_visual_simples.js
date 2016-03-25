controllers.identificacao_visual_simples = {
	config : function() {
		var me = controllers.identificacao_visual_simples;
		me.inicializaElementos();
		me.recuperaDadosRegistro();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$('#identificacao_visual_simples_back').click(function() {
			app.trocaPagina('../confirmar_veiculo.html', controllers.confirmar_veiculo);
		});

		$("#identificacao_visual_simples_avancar").click(function() {
			app.trocaPagina('caracterizacao_simples.html', controllers.caracterizacao_simples);
		})
	},
	
	//Inicializa os elementos da tela
	inicializaElementos : function() {
		
		util.inicializaSelectPaises("pais_simples");
	},

	//Preenche os elementos da tela com os valores salvos no registro
	recuperaDadosRegistro : function() {

		util.recuperaRadioSimNao(registro.reboque, "reboque_simples", "grupo_placa_estrangeira_simples");
		
		if (registro.placa_estrangeira != null && registro.placa_estrangeira) {
			$('#placa_estrangeira_simples_sim').prop('checked', true).checkboxradio('refresh');
			$('#grupo_pais_simples').show();
			$("#grupo_identificacao_visual_simples_avancar").hide();
		} else if (registro.placa_estrangeira != null && !registro.placa_estrangeira) {
			$('#placa_estrangeira_simples_nao').prop('checked', true).checkboxradio('refresh');
			$('#grupo_pais_simples').hide();
			$("#pais_simples option[value='-1']").attr('selected', true);
		} else if (registro.placa_estrangeira == null) {
			$('#placa_estrangeira_simples_nao').prop('checked', false).checkboxradio('refresh');
			$('#placa_estrangeira_simples_sim').prop('checked', false).checkboxradio('refresh');
			$('#grupo_pais_simples').hide();
			$("#pais_simples option[value='-1']").attr('selected', true);
			$("#grupo_identificacao_visual_simples_avancar").hide();
		}
		
		if (registro.pais != null) {
			$("#pais_simples option[value='" + registro.pais + "']").attr('selected', true);
			$("#grupo_identificacao_visual_simples_avancar").show();
		} else if (registro.placa_estrangeira != null && !registro.placa_estrangeira) {
			$("#grupo_identificacao_visual_simples_avancar").show();
		}
		$("select#pais_simples").selectmenu("refresh", true);

	},
	
	//Controla o show e hide dos elementos da tela
	progressoTela : function() {
		
		util.progressoRadioSimNao("reboque", "reboque_simples", "grupo_placa_estrangeira_simples");
		
		$('#placa_estrangeira_simples_sim').click(function() {
			$('#grupo_pais_simples').show();
			app.setAtributo('placa_estrangeira', true);

			if (Number($('#pais_simples').val()) == -1) {
				$("#grupo_identificacao_visual_simples_avancar").hide();
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

			$("#grupo_identificacao_visual_simples_avancar").show();
		});
		
		util.progressoSelect("pais", "pais_simples", "grupo_identificacao_visual_simples_avancar");
		
	}
};
