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

		util.progressoInputText("valorDoFrete", "valor_frete_carga", "grupo_valor_nota_carga");

		util.progressoInputText("valorDaCarga", "valor_nota_carga", "grupo_sabe_embarque_carga");

		util.progressoRadioSimNaoAlternado("sabe_embarque", "sabe_embarque_carga", "div_sabe_embarque_sim",
				"grupo_sabe_desembarque_carga");
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

		// TODO Show do componente abaixo depende se eh carga especial. Se nao, faz show no seguinte.
		util.progressoRadioSimNaoAlternado("sabe_desembarque", "sabe_desembarque_carga", "div_sabe_desembarque_sim", /*
																														 * TODO
																														 * verificar
																														 * se eh
																														 * carga
																														 * especial
																														 */
		"grupo_parada_especial_carga");
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
		util.progressoSelect("idLocalDesembarqueCarga", "desembarque_local_carga", /* TODO verificar se eh carga especial */
		"grupo_parada_especial_carga");
		// TODO Show do componente acima depende se eh carga especial. Se nao, faz show no seguinte.

		// TODO carga anterior
		util.progressoRadioSimNaoAlternado("carga_anterior", "carga_anterior_carga", "grupo_produto_anterior_carga",
				"grupo_indo_pegar_carga");
		$('#produto_anterior_carga').click(function() {
			util.autocomplete("produto_anterior_carga", lista_produtos, "Tipo de carga", "Entre com o produto da carga");
		})
		util.progressoInputText("idCargaAnterior", "produto_anterior_carga", "grupo_indo_pegar_carga", true);

		// TODO Show do componente abaixo depende se eh carga especial. Se nao, faz show no seguinte.
		util.progressoRadioSimNao("indoPegarCarga", "indo_pegar_carga", "grupo_parada_especial_carga")
		
		util.progressoRadioSimNao("utilizaParadaEspecial", "parada_especial_carga", "grupo_municipios_parada_carga");

		util.progressoCheckboxAlternado("municipiosParadaNaoSabe", "municipios_parada_nao_sei_carga", "grupo_caracterizacao_viagem2_carga_avancar", "div_municipios_parada_carga");
		
		// TODO parada obrigatoria uf1 mun1

		// TODO parada obrigatoria uf2 mun2
		

		// TODO Usar checkbox para desabilitar as opções e marcar não sabe -->

		// TODO setCamposDerivados unidade default é Ton; Converter para Kg antes de salvar no registro
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		// TODO

		return false;
	}

};
