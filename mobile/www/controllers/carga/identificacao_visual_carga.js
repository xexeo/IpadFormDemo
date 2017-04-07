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
			$("#grupo_tipo_carroceria_imagens_carga").siblings(":not(#grupo_tipo_carroceria_carga)").hide();
		});
		
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {
		util.inicializaSelectPais("idPaisPlacaEstrangeira", "pais_carga", false);
		util.inicializaPlacas("carga");

		var lista_tipo_conteiner = [ '1 Conteiner de 20 pés', '1 Conteiner de 20 pés e 1 Conteiner de 40 pés',
				'1 Conteiner de 40 pés', '2 Conteineres de 20 pés' ];
		util.inicializaSelect("tipo_conteiner_carga", lista_tipo_conteiner)

		var lista_rntrc = [ 'TAC', 'ETC', 'CTC' ];
		util.inicializaSelectCustomValueAsIndex("placa_vermelha_rntrc_sel_carga", lista_rntrc, "Selecione");
		
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

			app.setAtributo('idTipoCarroceria', $(this).attr('id').split("_")[1]);
			$("#grupo_tipo_carroceria_imagens_carga").hide();
			$("#grupo_tipo_carroceria_imagens_carga").next().show();
		});

		$(".tipo_porta_conteiner").click(function() {
			$("#grupo_tipo_conteiner_carga").show();
		});
		$(".img_carroceria:not(.tipo_porta_conteiner)").click(function() {
			$("#grupo_tipo_conteiner_carga").hide();
			$("#grupo_placa_estrangeira_carga").show();
			app.setAtributo('idTipoDeContainer', null);
			util.progressoRestartSelect("tipo_conteiner_carga", "Selecione");
		});

		util.progressoSelect('idTipoDeContainer', "tipo_conteiner_carga", "grupo_placa_estrangeira_carga");

		util.progressoRadioPlacaEstrangeira("carga");

		// País (somente placa estrangeira)
		util.progressoSelect("idPaisPlacaEstrangeira", "pais_carga", "grupo_placa_unica_carga");

		// Placa Brasil
		util.progressoPlacaNumeros("carga", "grupo_placa_vermelha_carga");

		// Placa única
		$('#placa_unica_carga').keyup(function() {
			if (util.isEmpty($(this).val())) {
				$("#grupo_carga_perigosa_carga").hide();
				$("#placa_vermelha_rntrc_num_carga").val(null);
			} else {
				$("#grupo_carga_perigosa_carga").show();
			}
		});
		$('#placa_unica_carga').change(function() {
			app.setAtributo('placa_unica', $(this).val());
			app.setAtributo('placaVermelha', false);
			app.setAtributo('placa_vermelha_rntrc_sel', null);
			app.setAtributo('placa_vermelha_rntrc_num', null);
		});

		// Placa Vermelha
		$('#placa_vermelha_carga_sim').click(function() {
			$('#grupo_qr_code_carga').show();
			if ((registro.placa_vermelha == undefined) || (!registro.placa_vermelha)) {
				$('#grupo_carga_perigosa_carga').hide();
				$('#grupo_placa_vermelha_rntrc_num_carga').hide();
			}
			app.setAtributo('placaVermelha', true);
		});
		$('#placa_vermelha_carga_nao').click(function() {
			$('#grupo_qr_code_carga').hide();
			$('#grupo_placa_vermelha_rntrc_carga').hide();
			$('#grupo_carga_perigosa_carga').show();
			app.setAtributo('placaVermelha', false);
			app.setAtributo('placa_vermelha_rntrc_sel', null);
			app.setAtributo('placa_vermelha_rntrc_num', null);
			$("#placa_vermelha_rntrc_num_carga").val(null);
			util.progressoRestartSelect("placa_vermelha_rntrc_sel_carga", "Selecione");
		});
		
		//QR Code
		$('#qr_code_carga_sim').click(function() {
			$('#grupo_placa_vermelha_rntrc_carga').show();
			app.setAtributo('placa_vermelha_rntrc_num', null);
			$("#placa_vermelha_rntrc_num_carga").val(null);
			$('#placa_vermelha_rntrc_num_carga').attr('maxlength','9');
			$('#placa_vermelha_rntrc_num_carga').attr('minlength','9');
			$('#grupo_carga_perigosa_carga').hide();
			util.progressoRestartSelect("placa_vermelha_rntrc_sel_carga", "Selecione");
		});
		$('#qr_code_carga_nao').click(function() {
			$('#grupo_placa_vermelha_rntrc_carga').show();
			app.setAtributo('placa_vermelha_rntrc_num', null);
			$("#placa_vermelha_rntrc_num_carga").val(null);
			$('#placa_vermelha_rntrc_num_carga').attr('maxlength','8');
			$('#placa_vermelha_rntrc_num_carga').attr('minlength','8');
			$('#grupo_carga_perigosa_carga').hide();
			util.progressoRestartSelect("placa_vermelha_rntrc_sel_carga", "Selecione");
		});

		// RNTRC
		$("#placa_vermelha_rntrc_sel_carga").change(function() {
			var nome_registro = "placa_vermelha_rntrc_sel";
			if (Number($(this).val()) != -1) {
				app.setAtributo(nome_registro, $(this).val());
				$("#grupo_placa_vermelha_rntrc_num_carga").show();
			} else {
				app.setAtributo(nome_registro, null);
				app.setAtributo('placa_vermelha_rntrc_num', null);
				$("#placa_vermelha_rntrc_num_carga").val(null);
				$("#grupo_placa_vermelha_rntrc_num_carga").hide();
				$("#grupo_carga_perigosa_carga").hide();
			}
		});
		util.progressoInputTextLen("placa_vermelha_rntrc_num", "placa_vermelha_rntrc_num_carga", "grupo_carga_perigosa_carga");

		// Carga Perigosa
		$('#carga_perigosa_carga_sim').click(function() {
			$('#grupo_carga_perigosa_numeros_carga').show();
			if ((registro.carga_perigosa == undefined) || (!registro.carga_perigosa)) {
				$('#grupo_identificacao_visual_carga_avancar').hide();
			}
			app.setAtributo('possuiCargaPerigosa', true);
		});
		$('#carga_perigosa_carga_nao').click(function() {
			$('#grupo_carga_perigosa_numeros_carga').hide();
			$('#grupo_identificacao_visual_carga_avancar').show();
			app.setAtributo('possuiCargaPerigosa', false);
			app.setAtributo('idNumeroDeRisco', null);
			app.setAtributo('idNumeroDaOnu', null);
			$('#carga_perigosa_risco_carga').val("");
			$('#carga_perigosa_onu_carga').val("");
		});
		util.progressoInputText("idNumeroDeRisco", "carga_perigosa_risco_carga", "grupo_carga_perigosa_onu_carga");
		util.progressoInputText("idNumeroDaOnu", "carga_perigosa_onu_carga", "grupo_identificacao_visual_carga_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		if (util.validaRadioChecked("tipo_carroceria_carga", "Tipo de carroceria")
				&& util.validaRadioSimNao("placa_estrangeira_carga", "Placa estrangeira")
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

			var ok_placa_estrangeira = true;
			var ok_placa_veiculo = true;
			var option = $('input[name=placa_estrangeira_carga]:checked').val();
			if (option == 'sim') {
				if ((Number($("#pais_carga").val())) == 1) { // Brasil
					alert("O país do veículo de placa estrangeira não pode ser Brasil");
					ok_placa_estrangeira = false;
				}

				if (!util.validaSelect("pais_carga", "País") || !util.validaInputText("placa_unica_carga", "Placa do veículo")) {
					ok_placa_veiculo = false;
				}
			} else if (option == 'nao') {
				if (!util.validaInputText("placa_letras_carga", "Placa do veículo (letras)")
						|| !util.validaInputText("placa_numeros_carga", "Placa do veículo (números)")
						|| !util.validaLenInputText("placa_letras_carga", "Placa do veículo (letras)")
						|| !util.validaLenInputText("placa_numeros_carga", "Placa do veículo (números)")) {
					ok_placa_veiculo = false;
				}
			}

			var ok_placa_vermelha = true;
			if (option == 'nao') {
				ok_placa_vermelha = util.validaRadioSimNao("placa_vermelha_carga", "Placa vermelha");
				var opt_vermelha = $('input[name=placa_vermelha_carga]:checked').val();
				if (opt_vermelha == 'sim') {
					if (!util.validaSelect("placa_vermelha_rntrc_sel_carga", "RNTRC")) {
						ok_placa_vermelha = false;
					}
					if (!util.validaInputText("placa_vermelha_rntrc_num_carga", "Número do RNTRC")
							|| !util.validaLenInputText("placa_vermelha_rntrc_num_carga", "Número do RNTRC")) {
						ok_placa_vermelha = false;
					}
				}
			}

			var ok_carga_perigosa = true;
			var opt_perigosa = $('input[name=carga_perigosa_carga]:checked').val();
			if (opt_perigosa == 'sim') {
				if (!util.validaInputText("carga_perigosa_risco_carga", "Número de Risco")
						|| !util.validaInputText("carga_perigosa_onu_carga", "Número da ONU")) {
					ok_carga_perigosa = false;
				}
				if (!util.validaValueInList("carga_perigosa_risco_carga", "Número de Risco", lista_numero_risco, "idNumeroDeRisco")
						|| !util.validaValueInList("carga_perigosa_onu_carga", "Número da ONU", lista_numero_onu, "idNumeroDaOnu")) {
					ok_carga_perigosa = false;
				}
			}

			return ok_tipo_conteiner && ok_placa_estrangeira && ok_placa_veiculo && ok_placa_vermelha && ok_carga_perigosa;
		}

		return false;
	}
};
