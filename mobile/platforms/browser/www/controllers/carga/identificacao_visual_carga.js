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

			$("#pais_carga option:contains('Selecione')").prop({
				selected : true
			});
			$("select#pais_carga").selectmenu("refresh", true);

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
			app.setAtributo('placa_vermelha_rntrc_sel_carga', null);
			app.setAtributo('placa_vermelha_rntrc_num_carga', null);

			$("#placa_vermelha_rntrc_sel_carga option:contains('RNTRC')").prop({
				selected : true
			});
			$("select#placa_vermelha_rntrc_sel_carga").selectmenu("refresh", true);
			$("#placa_vermelha_rntrc_num_carga").val(null);
		});
		util
				.progressoSelect("placa_vermelha_rntrc_sel", "placa_vermelha_rntrc_sel_carga",
						"grupo_placa_vermelha_rntrc_num_carga");
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

			$("#carga_perigosa_risco_carga option:contains('Número de Risco')").prop({
				selected : true
			});
			$("select#carga_perigosa_risco_carga").selectmenu("refresh", true);

			$("#carga_perigosa_onu_carga option:contains('Número da ONU')").prop({
				selected : true
			});
			$("select#carga_perigosa_onu_carga").selectmenu("refresh", true);
		});

		util.progressoSelect("carga_perigosa_risco", "carga_perigosa_risco_carga", "grupo_carga_perigosa_onu_carga");
		util.progressoInputText("carga_perigosa_onu", "carga_perigosa_onu_carga", "grupo_identificacao_visual_carga_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		// TODO CONTINUAR AS VALIDAÇÕES QUE FALTAM

		if (util.validaRadioSimNao("placa_estrangeira_carga", "Placa estrangeira")
				&& util.validaInputText("placa_letras_carga", "Placa do veículo")
				&& util.validaInputText("placa_numeros_carga", "Placa do veículo")
				&& util.validaRadioSimNao("placa_vermelha_carga", "Placa vermelha")
				&& util.validaRadioSimNao("carga_perigosa_carga", "Carga perigosa")) {

			var option = $('input[name=placa_estrangeira_carga]:checked').val();
			if (option == 'sim') {

				var ok_placa_estrangeira = true;
				if ((Number($("#pais_carga").val())) == 1) { // Brasil
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
