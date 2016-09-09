/* global util */

controllers.caracterizacao_viagem_simples = {
	config : function() {
		var me = controllers.caracterizacao_viagem_simples;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#caracterizacao_viagem_simples_avancar").click(function() {
			var ok = controllers.caracterizacao_viagem_simples.validar_componentes();
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
			$("#caracterizacao_viagem_simples_avancar").html("Avançar");
		}

		// INICIO PAÍSES
		util.inicializaSelectPais("idOrigemPais", "origem_pais_simples", true, "País");
		util.inicializaSelectCustomValueAsIndex("origem_uf_simples", lista_estados, "UF");

		util.inicializaSelectPais("idDestinoPais", "destino_pais_simples", true, "País");
		util.inicializaSelectCustomValueAsIndex("destino_uf_simples", lista_estados, "UF");
		// FIM PAÍSES

		util.inicializaSelectFrequencia('simples');

		util.inicializaTabelaAuxiliar("motivo_rota_simples", "Selecione", lista_motivo_escolha_rota, "simples");

		util.inicializaTabelaAuxiliar("motivo_viagem_simples", "Selecione", lista_motivo_viagem, "simples");

		var lista_rendas = [ 'R$ 1,00 a R$ 1.600,00', 'R$ 1.601,00 a R$ 2.400,00', 'R$ 2.401,00 a R$ 4.000,00',
				'R$ 4.001,00 a R$ 8.000,00', 'R$ 8.001,00 a R$ 16.600,00', 'Acima de R$ 16.601,00', 'Não informado', 'Sem renda' ];
		util.inicializaSelect("renda_simples", lista_rendas);

	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		// Origem
		util.progressoSelectPais("idOrigemPais", "idOrigemMunicipio", "origem_pais_simples", "origem_uf", "origem_municipio",
				"grupo_destino_simples", "simples");
		util.progressoSelect("origem_uf", "origem_uf_simples", "grupo_origem_municipio_simples");
		$('#origem_uf_simples').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#origem_municipio_simples').off("click").click(
								function() {
									util.autocomplete("origem_municipio_simples", lista_municipios[estado],
											"Município de origem", "Entre com o município de origem.");
								}).trigger('click');
					}
				});
		util.progressoInputText("idOrigemMunicipio", "origem_municipio_simples", "grupo_destino_simples", true);

		// Destino
		util.progressoSelectPais("idDestinoPais", "idDestinoMunicipio", "destino_pais_simples", "destino_uf",
				"destino_municipio", "grupo_frequencia_simples", "simples");
		util.progressoSelect("destino_uf", "destino_uf_simples", "grupo_destino_municipio_simples");
		$('#destino_uf_simples').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						// util.inicializaSelectMunicipio("destino_municipio_simples", $(this).val(), "Município");
						$('#destino_municipio_simples').off("click").click(
								function() {
									util.autocomplete("destino_municipio_simples", lista_municipios[estado],
											"Município de destino", "Entre com o município de destino.");
								}).trigger('click');
					}
				});
		util.progressoInputText("idDestinoMunicipio", "destino_municipio_simples", "grupo_frequencia_simples", true);

		// Frequencia
		$('#frequencia_num_simples').change(function() {
			app.setAtributo('frequenciaQtd', $(this).inputmask('unmaskedvalue'));
		});
		util.progressoSelect("frequenciaPeriodo", "frequencia_sel_simples", "grupo_motivo_rota_simples");

		// Motivo rota
		util.progressoSelect("idMotivoDeEscolhaDaRota", "motivo_rota_simples", "grupo_motivo_viagem_simples");

		// Motivo viagem
		$('#motivo_viagem_simples').change(function() {
			if (Number($(this).val()) == -1) {
				$("#grupo_pessoas_ambos").hide();
				$("#grupo_pessoas_trabalho_simples").hide();
				app.setAtributo('idMotivoDaViagem', null);
				app.setAtributo('numeroDePessoasATrabalho', null);
				$("#pessoas_trabalho_simples").val(null);
			} else {
				app.setAtributo('idMotivoDaViagem', $(this).val());
				$("#grupo_pessoas_ambos").show();
				util.limitaTamanhoCampo('pessoas_simples','limitePessoas');
				if (Number($(this).val()) == 6) { // TODO Trabalho. Ajustar se id mudar.
					if (Number($("#pessoas_simples").val()) > 0) {
						$("#grupo_pessoas_trabalho_simples").show();
					}
				} else {
					$("#grupo_pessoas_trabalho_simples").hide();
					app.setAtributo('numeroDePessoasATrabalho', null);
					$("#pessoas_trabalho_simples").val(null);
					if (Number($("#pessoas_simples").val()) > 0) {
						$("#grupo_renda_simples").show();
					}
				}
			}
		});

		// Pessoas no veículo
		$('#pessoas_simples').change(function() {
			app.setAtributo('numeroDePessoasNoVeiculo', $(this).val());
		});
		$('#pessoas_simples').keyup(function() {
			var temTrabalho = (Number($('#motivo_viagem_simples').val()) == 6); // TODO Trabalho. Ajustar se id mudar.
			if (Number($("#pessoas_simples").val()) > 0) {
				if (temTrabalho) {
					$("#grupo_pessoas_trabalho_simples").show();
				} else {
					$("#grupo_renda_simples").show();
				}
			} else {
				$("#grupo_renda_simples").hide();
				if (temTrabalho) {
					$("#grupo_pessoas_trabalho_simples").hide();
					$('#pessoas_trabalho_simples').val("");
					app.setAtributo('numeroDePessoasATrabalho', null);
				}
			}
		});

		// Pessoas a Trabalho
		util.progressoInputText("numeroDePessoasATrabalho", "pessoas_trabalho_simples", "grupo_renda_simples");

		// Renda
		util.progressoSelect("idRendaMedia", "renda_simples", "grupo_caracterizacao_viagem_simples_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		if (util.validaSelect("origem_pais_simples", "Origem da viagem")
				&& util.validaSelect("destino_pais_simples", "Destino da viagem")
				&& util.validaInputText("frequencia_num_simples", "Frequência da viagem")
				&& util.validaSelect("frequencia_sel_simples", "Frequência da viagem")
				&& util.validaSelect("motivo_rota_simples", "Motivo da escolha da rota")
				&& util.validaSelect("motivo_viagem_simples", "Motivo da viagem")
				&& util.validaInputText("pessoas_simples", "Pessoas no veículo")
				&& util.validaSelect("renda_simples", "Renda do condutor")) {

			var validacoes = true;
			if (Number($("#origem_pais_simples").val()) == 1) { // Brasil
				validacoes = validacoes
						&& (util.validaSelect("origem_uf_simples", "Origem da viagem - estado") && util.validaInputText(
								"origem_municipio_simples", "Origem da viagem - município"));
			}

			if (Number($("#destino_pais_simples").val()) == 1) { // Brasil
				validacoes = validacoes
						&& (util.validaSelect("destino_uf_simples", "Destino da viagem - estado") && util.validaInputText(
								"destino_municipio_simples", "Destino da viagem - município"));
			}

			if (Number($("#frequencia_num_simples").val()) < 1) {
				validacoes = validacoes
						&& (util.validaInputNumberRange("frequencia_num_simples", "Frequência da viagem", 1, 99999999));
			}

			validacoes = validacoes && util.validaLimitePessoas("pessoas_simples", "Pessoas no veículo");

			if (Number($("#motivo_viagem_simples").val()) == 6) { // TODO Trabalho. Ajustar se id mudar.
				validacoes = validacoes
						&& (util.validaInputNumberRange("pessoas_trabalho_simples", "Pessoas a trabalho", 1, Number($(
								"#pessoas_simples").val())));
			} else {
				validacoes = validacoes && (util.validaInputNumberRange("pessoas_trabalho_simples", "Pessoas a trabalho", 0, 0));
			}

			return validacoes;
		}
		return false;
	}

};
