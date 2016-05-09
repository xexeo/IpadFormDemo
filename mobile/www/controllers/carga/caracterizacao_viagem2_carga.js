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
				app.finalizaRegistro(function() {
					app.trocaPagina('views/menu.html', controllers.menu);
				});
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		// EMBARQUE DA CARGA
		util.inicializaSelectCustomValueAsIndex("embarque_uf_carga", lista_estados, "UF");
		var lista_locais = [ 'Porto Seco', 'Porto Marítimo', 'Terminal Ferroviário', 'Terminal Hidroviário',
				'Empresa Particular', 'Atacadista/Distribuidor', 'Outros' ];
		util.inicializaSelect("embarque_local_carga", lista_locais);

		// DESEMBARQUE DA CARGA
		util.inicializaSelectCustomValueAsIndex("desembarque_uf_carga", lista_estados, "UF");
		util.inicializaSelect("desembarque_local_carga", lista_locais);

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

		util.progressoRadioSimNaoAlternado("sabe_embarque", "sabe_embarque_carga", "div_sabe_embarque_sim",
				"grupo_sabe_desembarque_carga");
		$('#sabe_embarque_carga_nao').click(function() {
			util.progressoRestartSelect("embarque_uf_carga", "UF");
			util.progressoRestartSelect("embarque_local_carga", "Selecione");
			$('#embarque_mun_carga').val("");
			app.setAtributo("embarque_uf", null);
			app.setAtributo("municipioEmbarqueCarga", null);
			app.setAtributo("idLocalEmbarqueCarga", null);
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
		util.progressoInputText("municipioEmbarqueCarga", "embarque_mun_carga", "embarque_local_carga", true);
		util.progressoSelect("idLocalEmbarqueCarga", "embarque_local_carga", "grupo_sabe_desembarque_carga");

		var cargaPerigosa = app.getAtributo('possuiCargaPerigosa'); // TODO atualizar se nome do campo no registro for modificado
		if (cargaPerigosa == true) {
			util.progressoRadioSimNaoAlternado("sabe_desembarque", "sabe_desembarque_carga", "div_sabe_desembarque_sim",
					"grupo_parada_especial_carga");
		} else {
			util.progressoRadioSimNaoAlternado("sabe_desembarque", "sabe_desembarque_carga", "div_sabe_desembarque_sim",
					"grupo_municipios_parada_carga");
		}

		$('#sabe_desembarque_carga_nao').click(function() {
			util.progressoRestartSelect("desembarque_uf_carga", "UF");
			util.progressoRestartSelect("desembarque_local_carga", "Selecione");
			$('#desembarque_mun_carga').val("");
			app.setAtributo("desembarque_uf", null);
			app.setAtributo("municipioDesembarqueCarga", null);
			app.setAtributo("idLocalDesembarqueCarga", null);
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
		util.progressoInputText("municipioDesembarqueCarga", "desembarque_mun_carga", "desembarque_local_carga", true);
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
			if ($(this).is(':checked')) {
				util.progressoRestartSelect("municipios_parada_uf1_carga", "UF");
				util.progressoRestartSelect("municipios_parada_uf2_carga", "UF");
				$('#municipios_parada_mun1_carga').val("");
				$('#municipios_parada_mun2_carga').val("");
				app.setAtributo("", null);
			}
		});

		$('#municipios_parada_nao_sei_carga').click(function() {
			util.progressoRestartSelect("desembarque_uf_carga", "UF");
			util.progressoRestartSelect("desembarque_local_carga", "Selecione");
			$('#desembarque_mun_carga').val("");
			app.setAtributo("municipios_parada_uf1", null);
			app.setAtributo("municipios_parada_uf2", null);
			app.setAtributo("paradaObrigatoriaMunicipio1", null);
			app.setAtributo("paradaObrigatoriaMunicipio2", null);
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

		// Parada obrigatoria UF1 MUN1
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

		// TODO

		return true;
	}

};
