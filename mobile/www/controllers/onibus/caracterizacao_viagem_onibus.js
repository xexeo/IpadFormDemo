controllers.caracterizacao_viagem_onibus = {
	config : function() {
		var me = controllers.caracterizacao_viagem_onibus;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#btn_cancelar").click(app.cancelar);
		$("#caracterizacao_viagem_onibus_avancar").click(function() {
			var ok = controllers.caracterizacao_viagem_onibus.validar_componentes();
			if (ok) {
				app.finalizaRegistro();
				app.trocaPagina('views/menu.html', controllers.menu);
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		// INICIO PAÍSES
		util.inicializaSelectCustom("origem_pais_onibus", paises.listados(), "País");
		util.inicializaSelectCustomValueAsIndex("origem_uf_onibus", lista_estados, "UF");

		util.inicializaSelectCustom("destino_pais_onibus", paises.listados(), "País");
		util.inicializaSelectCustomValueAsIndex("destino_uf_onibus", lista_estados, "UF");
		// FIM PAÍSES

		var lista_frequencias = [ 'Dia', 'Semana', 'Mês', 'Ano', 'Eventualmente' ];
		util.inicializaSelect("frequencia_sel_onibus", lista_frequencias);

		var lista_tipo_viagem = [ 'Regular', 'Turismo', 'Fretado/Particular', 'Outros' ];
		util.inicializaSelect("tipo_viagem_onibus", lista_tipo_viagem);
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		// Origem
		util.progressoSelectPais("idOrigemPais", "origem_pais_onibus", "origem_uf", "origem_municipio", "grupo_destino_onibus",
				"onibus");
		util.progressoSelect("origem_uf", "origem_uf_onibus", "grupo_origem_municipio_onibus");
		$('#origem_uf_onibus').change(function() {
			if ($(this).val() != '-1') {
				util.inicializaSelectMunicipio("origem_municipio_onibus", $(this).val(), "Município");
			}
		});
		util.progressoSelect("origem_municipio", "origem_municipio_onibus", "grupo_destino_onibus");

		// Destino
		util.progressoSelectPais("idDestinoPais", "destino_pais_onibus", "destino_uf", "destino_municipio",
				"grupo_frequencia_onibus", "onibus");
		util.progressoSelect("destino_uf", "destino_uf_onibus", "grupo_destino_municipio_onibus");
		$('#destino_uf_onibus').change(function() {
			if ($(this).val() != '-1') {
				util.inicializaSelectMunicipio("destino_municipio_onibus", $(this).val(), "Município");
			}
		});
		util.progressoSelect("destino_municipio", "destino_municipio_onibus", "grupo_frequencia_onibus");

		// Frequencia
		$('#frequencia_num_onibus').keyup(function() {
			app.setAtributo('frequencia_num', $(this).val());
		});
		util.progressoSelect("frequencia_sel", "frequencia_sel_onibus", "grupo_tipo_viagem_onibus");

		util.progressoSelect("idTipoDeViagemOuServico", "tipo_viagem_onibus", "grupo_pessoas_onibus");

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

			var ok_origem_bra = true;
			if ((Number($("#origem_pais_onibus").val())) == 1) { // Brasil
				ok_origem_bra = (util.validaSelect("origem_uf_onibus", "Origem da viagem - estado") && util.validaSelect(
						"origem_municipio_onibus", "Origem da viagem - município"));
			}

			var ok_destino_bra = true;
			if ((Number($("#destino_pais_onibus").val())) == 1) { // Brasil
				ok_destino_bra = (util.validaSelect("destino_uf_onibus", "Destino da viagem - estado") && util.validaSelect(
						"destino_municipio_onibus", "Destino da viagem - município"));
			}

			return (ok_origem_bra && ok_destino_bra);
		}
		return false;
	}

};
