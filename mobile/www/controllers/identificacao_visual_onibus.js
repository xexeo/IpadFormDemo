controllers.identificacao_visual_onibus = {
	config : function() {
		var me = controllers.identificacao_visual_onibus;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#identificacao_visual_onibus_avancar").click(function() {
			app.trocaPagina('views/onibus/caracterizacao_onibus.html', controllers.caracterizacao_onibus);
		})
	},
	
	//Inicializa os elementos da tela
	inicializaElementos : function() {
		
		util.inicializaSelectPaises("pais_onibus");
	},

	//Controla o show e hide dos elementos da tela
	progressoTela : function() {
		
		$('#placa_estrangeira_onibus_sim').click(function() {
			$('#grupo_pais_onibus').show();
			app.setAtributo('placa_estrangeira', true);

			if (Number($('#pais_onibus').val()) == -1) {
				$("#grupo_placa_onibus").hide();
			}

		});
		$('#placa_estrangeira_onibus_nao').click(function() {
			$('#grupo_pais_onibus').hide()
			app.setAtributo('pais', null);
			app.setAtributo('placa_estrangeira', false);

			$("#pais_onibus option:contains('Selecione')").prop({
				selected : true
			});
			$("select#pais_onibus").selectmenu("refresh", true);

			$("#grupo_placa_onibus").show();
		});
		
		util.progressoSelect("pais", "pais_onibus", "grupo_placa_onibus");
		
		util.progressoInputText("placa_letras", "placa_letras_onibus", "grupo_placa_numeros_onibus");
		util.progressoInputText("placa_numeros", "placa_numeros_onibus", "grupo_identificacao_visual_onibus_avancar");
		
	}
};
