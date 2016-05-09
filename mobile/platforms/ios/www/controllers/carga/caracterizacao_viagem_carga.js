controllers.caracterizacao_viagem_carga = {
	config : function() {
		var me = controllers.caracterizacao_viagem_carga;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	buttons : function() {
		$("#caracterizacao_viagem_carga_avancar").click(function() {
			var ok = controllers.caracterizacao_viagem_carga.validar_componentes();
			if (ok) {
				app.trocaPagina('views/carga/caracterizacao_viagem2_carga.html', controllers.caracterizacao_viagem2_carga);
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		// INICIO PAÍSES
		util.inicializaSelectPais("idOrigemPais", "origem_pais_carga", true, "País");
		util.inicializaSelectCustomValueAsIndex("origem_uf_carga", lista_estados, "UF");

		util.inicializaSelectPais("idDestinoPais", "destino_pais_carga", true, "País");
		util.inicializaSelectCustomValueAsIndex("destino_uf_carga", lista_estados, "UF");
		// FIM PAÍSES

		util.inicializaSelectFrequencia('carga');

		var lista_motivos_rota = [ 'Asfalto/Sinalização', 'Caminho mais curto', 'Caminho mais rápido', 'Ordens da empresa',
				'Ausência de pedágio', 'Próximo a hotéis e postos', 'Segurança', 'Outros' ];
		util.inicializaSelect("motivo_rota_carga", lista_motivos_rota);
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		// Origem
		util.progressoSelectPais("idOrigemPais", "idOrigemMunicipio", "origem_pais_carga", "origem_uf", "origem_municipio",
				"grupo_destino_carga", "carga");
		util.progressoSelect("origem_uf", "origem_uf_carga", "grupo_origem_municipio_carga");
		$('#origem_uf_carga').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#origem_municipio_carga').off("click").click(
								function() {
									util.autocomplete("origem_municipio_carga", lista_municipios[estado], "Município de origem",
											"Entre com o município de origem.");
								}).trigger('click');
					}
				});
		util.progressoInputText("idOrigemMunicipio", "origem_municipio_carga", "grupo_destino_carga", true);

		// Destino
		util.progressoSelectPais("idDestinoPais", "idDestinoMunicipio", "destino_pais_carga", "destino_uf", "destino_municipio",
				"grupo_frequencia_carga", "carga");
		util.progressoSelect("destino_uf", "destino_uf_carga", "grupo_destino_municipio_carga");
		$('#destino_uf_carga').change(
				function() {
					var estado = $(this).val();
					if (estado != '-1') {
						$('#destino_municipio_carga').off("click").click(
								function() {
									util.autocomplete("destino_municipio_carga", lista_municipios[estado],
											"Município de destino", "Entre com o município de destino.");
								}).trigger('click');
					}
				});
		util.progressoInputText("idDestinoMunicipio", "destino_municipio_carga", "grupo_frequencia_carga", true);

		// Frequencia
		$('#frequencia_num_carga').keyup(function() {
			app.setAtributo('frequenciaQtd', $(this).val());
		});
		util.progressoSelect("frequenciaPeriodo", "frequencia_sel_carga", "grupo_motivo_rota_carga");

		// Motivo rota
		util.progressoSelect("idMotivoDeEscolhaDaRota", "motivo_rota_carga", "grupo_caracterizacao_viagem_carga_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		if (util.validaSelect("origem_pais_carga", "Origem da viagem")
				&& util.validaSelect("destino_pais_carga", "Destino da viagem")
				&& util.validaInputText("frequencia_num_carga", "Frequência da viagem")
				&& util.validaSelect("frequencia_sel_carga", "Frequência da viagem")
				&& util.validaSelect("motivo_rota_carga", "Motivo da escolha da rota")) {

			var validacoes = true;
			if ((Number($("#origem_pais_carga").val())) == 1) { // Brasil
				validacoes = validacoes
						&& (util.validaSelect("origem_uf_carga", "Origem da viagem - estado") && util.validaSelect(
								"origem_municipio_carga", "Origem da viagem - município"));
			}

			if ((Number($("#destino_pais_carga").val())) == 1) { // Brasil
				validacoes = validacoes
						&& (util.validaSelect("destino_uf_carga", "Destino da viagem - estado") && util.validaSelect(
								"destino_municipio_carga", "Destino da viagem - município"));
			}

			if (Number($("#frequencia_num_carga").val()) < 1) {
				validacoes = validacoes && (util.validaInputNumberRange("frequencia_num_carga", "Frequência da viagem", 1));
			}

			return validacoes;
		}
		return false;
	}

};
