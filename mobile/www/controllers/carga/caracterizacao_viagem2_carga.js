controllers.caracterizacao_viagem2_carga = {
	config : function() {
		var me = controllers.caracterizacao_viagem2_carga;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#caracterizacao_viagem2_carga_avancar").click(function() {
			var ok = controllers.caracterizacao_viagem2_carga.validar_componentes();
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
			$("#caracterizacao_viagem2_carga_avancar").html("Avançar");
		}

		// EMBARQUE DA CARGA
		util.inicializaSelectCustomValueAsIndex("embarque_uf_carga", lista_estados, "UF");
		var lista_locais = [ 'Porto Seco', 'Porto Marítimo', 'Terminal Ferroviário', 'Terminal Hidroviário',
				'Empresa Particular', 'Atacadista/Distribuidor', 'Outros' ];
		util.inicializaSelectCustom("embarque_local_carga", lista_locais, "Tipo do Local");

		// DESEMBARQUE DA CARGA
		util.inicializaSelectCustomValueAsIndex("desembarque_uf_carga", lista_estados, "UF");
		util.inicializaSelectCustom("desembarque_local_carga", lista_locais, "Tipo do Local");

		// SUGESTAO DE MUNICIPIOS PARADA OBRIGATORIA
		util.inicializaSelectCustomValueAsIndex("municipios_parada_uf1_carga", lista_estados, "UF");
		util.inicializaSelectCustomValueAsIndex("municipios_parada_uf2_carga", lista_estados, "UF");

	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		util.progressoRadioSimNaoAlternado("possui_carga", "possui_carga", "div_possui_carga_sim", "div_possui_carga_nao");

		$('#produto_carga').click(function() {
			util.autocomplete("produto_carga", lista_produtos, "Tipo de carga", "Entre com o produto da carga");
		})

		util.progressoInputText("idProduto", "produto_carga", "grupo_peso_carga", true);

		util.progressoInputText("pesoDaCarga", "peso_carga", "grupo_valor_frete_carga");

		// UNIDADE DO PESO DA CARGA
		app.setAtributo("unidadePesoDaCarga", $('input[name="unidade_peso_carga"]:checked').val());
		$('input[name=unidade_peso_carga]').change(function() {
			app.setAtributo("unidadePesoDaCarga", $('input[name="unidade_peso_carga"]:checked').val());
		});

		util.progressoInputMoney("valorDoFrete", "valor_frete_carga", "grupo_valor_nota_carga");

		util.progressoInputMoney("valorDaCarga", "valor_nota_carga", "grupo_sabe_embarque_carga");

		util.progressoCheckboxAlternado("embarqueCargaNaoSabe", "embarque_carga_nao_sei", "grupo_sabe_desembarque_carga",
				"div_sabe_embarque_sim");
		$('#embarque_carga_nao_sei').click(function() {
			util.progressoRestartSelect("embarque_uf_carga", "UF");
			util.progressoRestartSelect("embarque_local_carga", "Tipo do Local");
			$('#embarque_mun_carga').val("");
			app.setAtributo("embarque_uf", null);
			app.setAtributo("municipioEmbarqueCarga", null);
			app.setAtributo("idLocalEmbarqueCarga", null);
			if ($(this).is(':checked')) {
				$('#grupo_embarque_mun_carga').hide();
				$('#grupo_embarque_local_carga').hide();
			}
		});
		util.progressoSelect("embarque_uf", "embarque_uf_carga", "grupo_embarque_mun_carga");
		$('#embarque_uf_carga').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#embarque_mun_carga').off("click").click(
								function() {
									util.autocomplete("embarque_mun_carga", lista_municipios[estado], "Município de embarque",
											"Entre com o município de embarque");
								}).trigger('click');
					}
				});
		util.progressoInputText("municipioEmbarqueCarga", "embarque_mun_carga", "grupo_embarque_local_carga", true);
		util.progressoSelect("idLocalEmbarqueCarga", "embarque_local_carga", "grupo_sabe_desembarque_carga");

		var cargaPerigosa = app.getAtributo('possuiCargaPerigosa');
		if (cargaPerigosa == true) {
			util.progressoCheckboxAlternado("desembarqueCargaNaoSabe", "desembarque_carga_nao_sei",
					"grupo_parada_especial_carga", "div_sabe_desembarque_sim");

		} else {
			util.progressoCheckboxAlternado("desembarqueCargaNaoSabe", "desembarque_carga_nao_sei",
					"grupo_municipios_parada_carga", "div_sabe_desembarque_sim");
			;
		}
		$('#desembarque_carga_nao_sei').click(function() {
			util.progressoRestartSelect("desembarque_uf_carga", "UF");
			util.progressoRestartSelect("desembarque_local_carga", "Tipo do Local");
			$('#desembarque_mun_carga').val("");
			app.setAtributo("desembarque_uf", null);
			app.setAtributo("municipioDesembarqueCarga", null);
			app.setAtributo("idLocalDesembarqueCarga", null);
			if ($(this).is(':checked')) {
				$('#grupo_desembarque_mun_carga').hide();
				$('#grupo_desembarque_local_carga').hide();
			}
		});

		util.progressoSelect("desembarque_uf", "desembarque_uf_carga", "grupo_desembarque_mun_carga");
		$('#desembarque_uf_carga').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#desembarque_mun_carga').off("click").click(
								function() {
									util.autocomplete("desembarque_mun_carga", lista_municipios[estado],
											"Município de desembarque", "Entre com o município de desembarque");
								}).trigger('click');
					}
				});
		util.progressoInputText("municipioDesembarqueCarga", "desembarque_mun_carga", "grupo_desembarque_local_carga", true);
		if (cargaPerigosa == true) {
			util.progressoSelect("idLocalDesembarqueCarga", "desembarque_local_carga", "grupo_parada_especial_carga");
		} else {
			util.progressoSelect("idLocalDesembarqueCarga", "desembarque_local_carga", "grupo_municipios_parada_carga");
		}

		util.progressoRadioSimNaoAlternado("carga_anterior", "carga_anterior_carga", "grupo_produto_anterior_carga",
				"grupo_indo_pegar_carga");
		$('#carga_anterior_carga_nao').click(function() {
			$('#produto_anterior_carga').val("");
			app.setAtributo("idCargaAnterior", null);
		});
		$('#produto_anterior_carga').click(function() {
			util.autocomplete("produto_anterior_carga", lista_produtos, "Tipo de carga", "Entre com o produto da carga");
		})
		util.progressoInputText("idCargaAnterior", "produto_anterior_carga", "grupo_indo_pegar_carga", true);

		if (cargaPerigosa == true) {
			util.progressoRadioSimNao("indoPegarCarga", "indo_pegar_carga", "grupo_parada_especial_carga");
		} else {
			util.progressoRadioSimNao("indoPegarCarga", "indo_pegar_carga", "grupo_municipios_parada_carga");
		}

		util.progressoRadioSimNao("utilizaParadaEspecial", "parada_especial_carga", "grupo_municipios_parada_carga");

		util.progressoCheckboxAlternado("municipiosParadaNaoSabe", "municipios_parada_nao_sei_carga",
				"grupo_caracterizacao_viagem2_carga_avancar", "div_municipios_parada_carga");

		$('#municipios_parada_nao_sei_carga').click(function() {
			util.progressoRestartSelect("municipios_parada_uf1_carga", "UF");
			util.progressoRestartSelect("municipios_parada_uf2_carga", "UF");
			$('#municipios_parada_mun1_carga').val("");
			$('#municipios_parada_mun2_carga').val("");
			app.setAtributo("municipios_parada_uf1", null);
			app.setAtributo("municipios_parada_uf2", null);
			app.setAtributo("paradaObrigatoriaMunicipio1", null);
			app.setAtributo("paradaObrigatoriaMunicipio2", null);
			if ($(this).is(':checked')) {
				$('#div_municipios_parada_carga').hide();
			}
		});

		// Parada obrigatoria UF1 MUN1
		util.progressoSelect("municipios_parada_uf1", "municipios_parada_uf1_carga", "grupo_municipios_parada_mun1_carga");
		$('#municipios_parada_uf1_carga').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#municipios_parada_mun1_carga').off("click").click(
								function() {
									util.autocomplete("municipios_parada_mun1_carga", lista_municipios[estado], "Município",
											"Entre com o município");
								}).trigger('click');
					}
				});
		util.progressoInputText("paradaObrigatoriaMunicipio1", "municipios_parada_mun1_carga",
				"grupo_caracterizacao_viagem2_carga_avancar", true);

		// Parada obrigatoria UF2 MUN2
		util.progressoSelect("municipios_parada_uf2", "municipios_parada_uf2_carga", "grupo_municipios_parada_mun2_carga");
		$('#municipios_parada_uf2_carga').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#municipios_parada_mun2_carga').off("click").click(
								function() {
									util.autocomplete("municipios_parada_mun2_carga", lista_municipios[estado], "Município",
											"Entre com o município");
								}).trigger('click');
					}
				});
		util.progressoInputText("paradaObrigatoriaMunicipio2", "municipios_parada_mun2_carga",
				"grupo_caracterizacao_viagem2_carga_avancar", true);
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		if (util.validaRadioSimNao("possui_carga", "Possui carga")) {
			var opt_possui_carga = $('input[name=possui_carga]:checked').val();
			if (opt_possui_carga == 'sim') {
				// POSSUI CARGA = SIM
				var ok_possui_carga_sim = true;
				if (!util.validaInputText("produto_carga", "Tipo de carga")
						|| !util.validaInputText("peso_carga", "Peso da carga")
						|| !util.validaInputText("valor_frete_carga", "Valor do frete")
						|| !util.validaInputText("valor_nota_carga", "Valor da carga/nota fiscal")) {
					ok_possui_carga_sim = false;
				}

				var ok_local_embarque = true;
				var ckb_embarque = $('#embarque_carga_nao_sei').is(':checked');
				if (!ckb_embarque) {
					// LOCAL EMBARQUE
					if (!util.validaSelect("embarque_uf_carga", "Embarque da carga - UF")
							|| !util.validaInputText("embarque_mun_carga", "Embarque da carga - Município")
							|| !util.validaSelect("embarque_local_carga", "Embarque da carga - Local")) {
						ok_local_embarque = false;
					}
				}

				var ok_local_desmbarque = true;
				var ckb_desembarque = $('#desembarque_carga_nao_sei').is(':checked');
				if (!ckb_desembarque) {
					// LOCAL DESEMBARQUE
					if (!util.validaSelect("desembarque_uf_carga", "Desembarque da carga - UF")
							|| !util.validaInputText("desembarque_mun_carga", "Desembarque da carga - Município")
							|| !util.validaSelect("desembarque_local_carga", "Desembarque da carga - Local")) {
						ok_local_desmbarque = false;
					}
				}

				if (!(ok_possui_carga_sim && ok_local_embarque && ok_local_desmbarque)) {
					return false;
				}
			} else if (opt_possui_carga == 'nao') {
				// POSSUI CARGA = NAO
				if (util.validaRadioSimNao("carga_anterior_carga", "Carga anterior")
						&& util.validaRadioSimNao("indo_pegar_carga", "Indo pegar uma carga")) {
					var opt_carga_anterior = $('input[name=carga_anterior_carga]:checked').val();
					if (opt_carga_anterior == 'sim') {
						if (!util.validaInputText("produto_anterior_carga", "Tipo de carga")) {
							return false;
						}
					}
				} else {
					return false;
				}
			}
		}

		var cargaPerigosa = app.getAtributo('possuiCargaPerigosa');
		if (cargaPerigosa == true) {
			if (!util.validaRadioSimNao("parada_especial_carga", "Utiliza parada especial")) {
				return false;
			}
		}

		var ckb_municipios_parada = $('#municipios_parada_nao_sei_carga').is(':checked');
		if (!ckb_municipios_parada) {
			// SUGESTAO DE MUNICIPIOS DE PARADA
			var uma_sugestao = false;
			if (util.validaSelect("municipios_parada_uf1_carga") && util.validaInputText("municipios_parada_mun1_carga")) {
				uma_sugestao = true;
			}
			if (util.validaSelect("municipios_parada_uf2_carga") && util.validaInputText("municipios_parada_mun2_carga")) {
				uma_sugestao = true;
			}
			if (!uma_sugestao) {
				alert("Preencha pelo menos um dos campos de sugestão de parada obrigatória para descanso",
						"Erro no preenchimento!", null, 'error');
			}
			return uma_sugestao;
		} else {
			return true;
		}

		return false;
	}

};
