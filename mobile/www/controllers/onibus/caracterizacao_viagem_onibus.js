controllers.caracterizacao_viagem_onibus = {
	config : function() {
		var me = controllers.caracterizacao_viagem_onibus;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#caracterizacao_viagem_onibus_avancar").click(function() {
			var ok = controllers.caracterizacao_viagem_onibus.validar_componentes();
			if (ok) {
				if (logins.user_logado != null && logins.user_logado.perguntaExtra) {
					app.trocaPagina('views/pergunta_extra.html', controllers.pergunta_extra);
				} else {
					app.finalizaRegistro(function() {
						app.trocaPagina('views/menu.html', controllers.menu);
					});
				}
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		// Verifica se possui Pergunta Extra
		if (logins.user_logado != null && logins.user_logado.perguntaExtra) {
			$("#caracterizacao_viagem_onibus_avancar").html("Avançar");
		}

		// INICIO PAÍSES
		util.inicializaSelectPais("idOrigemPais", "origem_pais_onibus", true, "País");
		util.inicializaSelectCustomValueAsIndex("origem_uf_onibus", lista_estados, "UF");

		util.inicializaSelectPais("idDestinoPais", "destino_pais_onibus", true, "País");
		util.inicializaSelectCustomValueAsIndex("destino_uf_onibus", lista_estados, "UF");
		// FIM PAÍSES

		util.inicializaSelectFrequencia('onibus');

		var lista_tipo_viagem = [ 'Regular', 'Turismo', 'Fretado/Particular', 'Outros' ];
		util.inicializaSelect("tipo_viagem_onibus", lista_tipo_viagem);
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		// Origem
		util.progressoSelectPais("idOrigemPais", "idOrigemMunicipio", "origem_pais_onibus", "origem_uf", "origem_municipio",
				"grupo_destino_onibus", "onibus");
		util.progressoSelect("origem_uf", "origem_uf_onibus", "grupo_origem_municipio_onibus");
		$('#origem_uf_onibus').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#origem_municipio_onibus').off("click").click(
								function() {
									util.autocomplete("origem_municipio_onibus", lista_municipios[estado], "Município de origem",
											"Entre com o município de origem.");
								}).trigger('click')
					}
				});
		util.progressoInputText("idOrigemMunicipio", "origem_municipio_onibus", "grupo_destino_onibus", true);

		// Destino
		util.progressoSelectPais("idDestinoPais", "idDestinoMunicipio", "destino_pais_onibus", "destino_uf", "destino_municipio",
				"grupo_frequencia_onibus", "onibus");
		util.progressoSelect("destino_uf", "destino_uf_onibus", "grupo_destino_municipio_onibus");
		$('#destino_uf_onibus').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#destino_municipio_onibus').off("click").click(
								function() {
									util.autocomplete("destino_municipio_onibus", lista_municipios[estado],
											"Município de destino", "Entre com o município de destino.");
								}).trigger('click');
					}
				});
		util.progressoInputText("idDestinoMunicipio", "destino_municipio_onibus", "grupo_frequencia_onibus", true);

		// Frequencia
		$('#frequencia_num_onibus').change(function() {
			app.setAtributo('frequenciaQtd', $(this).inputmask('unmaskedvalue'));
		});
		util.progressoSelect("frequenciaPeriodo", "frequencia_sel_onibus", "grupo_tipo_viagem_onibus");

		util.progressoSelect("idTipoDeViagemOuServico", "tipo_viagem_onibus", "grupo_pessoas_onibus");
		
		$('#tipo_viagem_onibus').change(function() {
			if (Number($(this).val()) != -1) {
				util.limitaTamanhoCampo('pessoas_onibus','limitePessoas');
			}
		});

		util.progressoInputText("numeroDePessoasNoVeiculo", "pessoas_onibus", "grupo_caracterizacao_viagem_onibus_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		if (util.validaSelect("origem_pais_onibus", "Origem da viagem")
				&& util.validaSelect("destino_pais_onibus", "Destino da viagem")
				&& util.validaInputText("frequencia_num_onibus", "Frequência da viagem")
				&& util.validaSelect("frequencia_sel_onibus", "Frequência da viagem")
				&& util.validaSelect("tipo_viagem_onibus", "Tipo de viagem/serviço")
				&& util.validaInputText("pessoas_onibus", "Pessoas no veículo")) {

			var validacoes = true;
			if (Number($("#origem_pais_onibus").val()) == 1) { // Brasil
				validacoes = validacoes
						&& (util.validaSelect("origem_uf_onibus", "Origem da viagem - estado") && util.validaInputText(
								"origem_municipio_onibus", "Origem da viagem - município"));
			}

			if (Number($("#destino_pais_onibus").val()) == 1) { // Brasil
				validacoes = validacoes
						&& (util.validaSelect("destino_uf_onibus", "Destino da viagem - estado") && util.validaInputText(
								"destino_municipio_onibus", "Destino da viagem - município"));
			}

			if (Number($("#frequencia_num_onibus").val()) < 1) {
				validacoes = validacoes
						&& (util.validaInputNumberRange("frequencia_num_onibus", "Frequência da viagem", 1, 99999999));
			}

			validacoes = validacoes && util.validaLimitePessoas("pessoas_onibus", "Pessoas no veículo");

			return validacoes;
		}
		return false;
	}

};
