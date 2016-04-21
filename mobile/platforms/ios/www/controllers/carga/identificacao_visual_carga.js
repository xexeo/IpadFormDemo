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
			if (ok) {
				app.trocaPagina('views/carga/caracterizacao_carga.html', controllers.caracterizacao_carga);
			}
		});
		$('#tipo_carroceria_carga').click(function() {
			$("#grupo_tipo_carroceria_imagens_carga").show();
			$("#grupo_tipo_carroceria_imagens_carga").siblings(":not(#grupo_tipo_carroceria_carga)").each(function(key, value) {
				$(value).hide();
			});
		});
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		var lista_tipo_conteiner = [ '1 Conteiner de 20 pés', '1 Conteiner de 20 pés e 1 Conteiner de 40 pés',
				'1 Conteiner de 40 pés', '2 Conteineres de 20 pés' ];
		util.inicializaSelect("tipo_conteiner_carga", lista_tipo_conteiner)

		util.inicializaSelect("pais_carga", paises.listados());

		var lista_rntrc = [ 'TAC', 'ETC', 'CTC' ];
		util.inicializaSelectCustomValueAsIndex("placa_vermelha_rntrc_sel_carga", lista_rntrc, "RNTRC");

		util.inicializaSelectCargaRiscoOnu("carga_perigosa_risco_carga", "Número de Risco", lista_numero_risco);
		util.inicializaSelectCargaRiscoOnu("carga_perigosa_onu_carga", "Número da ONU", lista_numero_onu);

		$("img:not([src*='outros']).img_carroceria").each(function(key, value) {
			$(value).after("<span>" + $(value).attr('alt') + "</span>");
		});
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		$(".img_carroceria").click(function() {
			var imgAlt = $(this).attr('alt');
			var elemTipoCarroceria = $('#tipo_carroceria_carga');
			elemTipoCarroceria.val(imgAlt);
			$('label[for="' + elemTipoCarroceria.attr('id') + '"]').text(imgAlt);

			app.setAtributo('tipo_carroceria', $(this).attr('id').split("_")[1]);
			$("#grupo_tipo_carroceria_imagens_carga").hide();
			$("#grupo_tipo_carroceria_imagens_carga").next().show();
		});

		$(".tipo_porta_conteiner").click(function() {
			$("#grupo_tipo_conteiner_carga").show();
		});
		$(".img_carroceria:not(.tipo_porta_conteiner)").click(function() {
			$("#grupo_tipo_conteiner_carga").hide();
			$("#grupo_placa_estrangeira_carga").show();
			app.setAtributo("tipo_conteiner", null);
			util.progressoRestartSelect("tipo_conteiner_carga", "Selecione");
		});

		util.progressoSelect("tipo_conteiner", "tipo_conteiner_carga", "grupo_placa_estrangeira_carga");

		$('#placa_estrangeira_carga_sim').click(function() {
			$('#grupo_pais_carga').show();
			app.setAtributo('placa_estrangeira', true);

			if (Number($('#pais_carga').val()) == -1) {
				$("#grupo_placa_carga").hide();
			}
		});
		$('#placa_estrangeira_carga_nao').click(function() {
			$('#grupo_pais_carga').hide()
			app.setAtributo('placa_estrangeira', false);
			app.setAtributo('idPaisPlacaEstrangeira', null);
			util.progressoRestartSelect("pais_carga", "Selecione");

			$("#grupo_placa_carga").show();
		});
		util.progressoSelect("idPaisPlacaEstrangeira", "pais_carga", "grupo_placa_carga");

		util.progressoInputText("placa_letras", "placa_letras_carga", "grupo_placa_numeros_carga");
		util.progressoInputText("placa_numeros", "placa_numeros_carga", "grupo_placa_vermelha_carga");

		$('#placa_vermelha_carga_sim').click(function() {
			$('#grupo_placa_vermelha_rntrc_carga').show();
			$('#grupo_carga_perigosa_carga').hide();
			app.setAtributo('placa_vermelha', true);
		});
		$('#placa_vermelha_carga_nao').click(function() {
			$('#grupo_placa_vermelha_rntrc_carga').hide();
			$('#grupo_carga_perigosa_carga').show();
			app.setAtributo('placa_vermelha', false);
			app.setAtributo('placa_vermelha_rntrc_sel', null);
			app.setAtributo('placa_vermelha_rntrc_num', null);

			util.progressoRestartSelect("placa_vermelha_rntrc_sel_carga", "Selecione");
			$("#placa_vermelha_rntrc_num_carga").val(null);
		});
		util.progressoSelect("placa_vermelha_rntrc_sel", "placa_vermelha_rntrc_sel_carga","grupo_placa_vermelha_rntrc_num_carga");
		util.progressoInputText("placa_vermelha_rntrc_num", "placa_vermelha_rntrc_num_carga", "grupo_carga_perigosa_carga");

		$('#carga_perigosa_carga_sim').click(function() {
			$('#grupo_carga_perigosa_numeros_carga').show();
			$('#grupo_identificacao_visual_carga_avancar').hide();
			app.setAtributo('carga_perigosa', true);
		});
		$('#carga_perigosa_carga_nao').click(function() {
			$('#grupo_carga_perigosa_numeros_carga').hide();
			$('#grupo_identificacao_visual_carga_avancar').show();
			app.setAtributo('carga_perigosa', false);
			app.setAtributo('carga_perigosa_risco', null);
			app.setAtributo('carga_perigosa_onu', null);

			util.progressoRestartSelect("carga_perigosa_risco_carga", "Número de Risco");
			util.progressoRestartSelect("carga_perigosa_onu_carga", "Número da ONU");
		});

		util.progressoSelect("carga_perigosa_risco", "carga_perigosa_risco_carga", "grupo_carga_perigosa_onu_carga");
		util.progressoInputText("carga_perigosa_onu", "carga_perigosa_onu_carga", "grupo_identificacao_visual_carga_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		
		if (util.validaRadioChecked("tipo_carroceria_carga", "Tipo de carroceria")
				&& util.validaRadioSimNao("placa_estrangeira_carga", "Placa estrangeira")
				&& util.validaInputText("placa_letras_carga", "Placa do veículo")
				&& util.validaInputText("placa_numeros_carga", "Placa do veículo")
				&& util.validaRadioSimNao("placa_vermelha_carga", "Placa vermelha")
				&& util.validaRadioSimNao("carga_perigosa_carga", "Carga perigosa")) {

			var ok_tipo_conteiner = true;
			var opt_conteiner = $('input[name=tipo_carroceria_carga]').val();
			if (opt_conteiner == 'Porta-Conteiner') {
				if (!util.validaSelect("tipo_conteiner_carga", "Porta-Conteiner")) {
					ok_tipo_conteiner = false;
				}
			}
			
			var ok_placa_estrangeira = true;
			var opt_estrangeira = $('input[name=placa_estrangeira_carga]:checked').val();
			if (opt_estrangeira == 'sim' && util.validaSelect("pais_carga", "País")) {
				if ((Number($("#pais_carga").val())) == 1) { // Brasil
					alert("O país do veículo de placa estrangeira não pode ser Brasil");
					ok_placa_estrangeira = false;
				}
			}
			
			var ok_placa_vermelha = true;
			var opt_vermelha = $('input[name=placa_vermelha_carga]:checked').val();
			if ( opt_vermelha == 'sim') {
				if (!util.validaSelect("placa_vermelha_rntrc_sel_carga", "RNTRC")) {
					ok_placa_vermelha = false;
				}
				if (!util.validaInputText("placa_vermelha_rntrc_num_carga", "Número do RNTRC")) {
					ok_placa_vermelha = false;
				}
			}
			
			var ok_carga_perigosa = true;
			var opt_perigosa = $('input[name=carga_perigosa_carga]:checked').val();
			if ( opt_perigosa == 'sim') {
				if (!util.validaSelect("carga_perigosa_risco_carga", "Número de risco")) {
					ok_carga_perigosa = false;
				}
				if (!util.validaSelect("carga_perigosa_onu_carga", "Número da ONU")) {
					ok_carga_perigosa = false;
				}
			}
			
			return ok_tipo_conteiner && ok_placa_estrangeira && ok_placa_vermelha && ok_carga_perigosa;
		}
		
		return false;
	}
};
