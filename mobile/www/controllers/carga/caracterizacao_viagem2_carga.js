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

		$('#possui_carga_sim').click(function() {
			$('#div_possui_carga_sim').show();
			$('#div_possui_carga_nao').hide();
			app.setAtributo(nome_registro, true);
		});
		$('#possui_carga_nao').click(function() {
			$('#div_possui_carga_sim').hide();
			$('#div_possui_carga_nao').show();
			app.setAtributo(nome_registro, false);
		});

		// $('#produto_carga').click(
		// function() {
		// util.autocomplete("produto_carga", lista_produtos, "Tipo de carga", "Entre com o produto da carga");
		// }).trigger('click');

		util.progressoInputText("idProduto", "produto_carga", "grupo_peso_carga", true);

		util.progressoInputText("pesoDaCarga", "peso_carga", "grupo_valor_frete_carga");

		$('#' + nome_campo + '_sim').click(function() {
			$('#' + grupo_proximo).show();
			app.setAtributo(nome_registro, true);
		});
		$('#' + nome_campo + '_nao').click(function() {
			$('#' + grupo_proximo).show();
			app.setAtributo(nome_registro, false);
		});

		// TODO valor frete

		// TODO valor carga nota fiscal

		$('#embarque_uf_carga').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#embarque_mun_carga').off("click").click(
								function() {
									util.autocomplete("embarque_mun_carga", lista_municipios[estado], "Município de embarque",
											"Município onde a carga foi emcarbada");
								}).trigger('click');
					}
				});
		util.progressoInputText("municipioEmbarqueCarga", "embarque_mun_carga", /* TODO */"", true);

		// TODO local embarque

		// TODO componentes desembarque

		// TODO carga anterior
		// util.autocomplete("produto_anterior_carga", lista_produtos, "Tipo de carga", "Entre com o produto da carga");

		// TODO indo pegar carga

		// TODO utiliza parada especial

		// TODO componentes sugestao parada obrigatoria

		// TODO Usar checkbox para desabilitar as opções e marcar não sabe -->

	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		// TODO

		return false;
	}

};
