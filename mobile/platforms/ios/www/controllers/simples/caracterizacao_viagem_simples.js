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
				app.finalizaRegistro(function() {
					app.trocaPagina('views/menu.html', controllers.menu);
				});
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		// INICIO PAÍSES
		util.inicializaSelectCustom("origem_pais_simples", paises.listados(), "País");
		util.inicializaSelectCustomValueAsIndex("origem_uf_simples", lista_estados, "UF");

		util.inicializaSelectCustom("destino_pais_simples", paises.listados(), "País");
		util.inicializaSelectCustomValueAsIndex("destino_uf_simples", lista_estados, "UF");
		// FIM PAÍSES

		var lista_frequencias = [ 'Dia', 'Semana', 'Mês', 'Ano', 'Eventualmente' ];
		util.inicializaSelect("frequencia_sel_simples", lista_frequencias);

		var lista_motivos_rota = [ 'Asfalto/Sinalização', 'Caminho mais curto', 'Caminho mais rápido',
				'Proximidade hotéis/postos', 'Segurança', 'Turismo/Paisagem', 'Ausência de pedágio',
				'Ponto obrigatório de passagem', 'Outros' ];
		util.inicializaSelect("motivo_rota_simples", lista_motivos_rota);

		var lista_motivos_viagem = [ 'Compra', 'Estudo', 'Lazer', 'Saúde', 'Trabalho', 'Negócios', 'Outros' ];
		util.inicializaSelect("motivo_viagem_simples", lista_motivos_viagem);

		var lista_rendas = [ 'Até R$ 1.500,00', 'De R$ 1.501,00 a R$ 3.000,00', 'De R$ 3.001,00 a R$ 4.500,00',
				'De R$ 4.501,00 a R$ 6.500,00', 'De R$ 6.501,00 a R$ 10.500,00', 'Acima de R$ 10.501,00', 'Não informada',
				'Sem renda própria' ];
		util.inicializaSelect("renda_simples", lista_rendas);
		
		
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		// Origem
		util.progressoSelectPais("idOrigemPais", "origem_pais_simples", "origem_uf", "origem_municipio", "grupo_destino_simples",
				"simples");
		util.progressoSelect("origem_uf", "origem_uf_simples", "grupo_origem_municipio_simples");
		$('#origem_uf_simples').change(function() {
			if ($(this).val() != '-1') {
				//util.inicializaSelectMunicipio("origem_municipio_simples", $(this).val(), "Município");
				
				$('#origem_minicipio_simples').show().click(function(){
						util.autocomplete("origem_municipio_simples", lista_municipios[$(this).val()]);
				}).textinput();
			}
		});
		util.progressoSelect("origem_municipio", "origem_municipio_simples", "grupo_destino_simples");

		// Destino
		util.progressoSelectPais("idDestinoPais", "destino_pais_simples", "destino_uf", "destino_municipio",
				"grupo_frequencia_simples", "simples");
		util.progressoSelect("destino_uf", "destino_uf_simples", "grupo_destino_municipio_simples");
		$('#destino_uf_simples').change(function() {
			if ($(this).val() != '-1') {
				util.inicializaSelectMunicipio("destino_municipio_simples", $(this).val(), "Município");
			}
		});
		util.progressoSelect("destino_municipio", "destino_municipio_simples", "grupo_frequencia_simples");

		// Frequencia
		$('#frequencia_num_simples').keyup(function() {
			app.setAtributo('frequencia_num', $(this).val());
		});
		util.progressoSelect("frequencia_sel", "frequencia_sel_simples", "grupo_motivo_rota_simples");

		// Motivo rota
		util.progressoSelect("idMotivoDeEscolhaDaRota", "motivo_rota_simples", "grupo_motivo_viagem_simples");

		// Motivo viagem
		$('#motivo_viagem_simples').change(function() {
			app.logger.log("====change: MOTIVO A TRABALHO====");
			if (Number($(this).val()) == -1) {
				$("#grupo_pessoas_ambos").hide();
				$("#grupo_pessoas_trabalho_simples").hide();
				app.setAtributo("idMotivoDaViagem", null);
				app.setAtributo("numeroDePessoasATrabalho", null);
				$("#pessoas_trabalho_simples").val(null);
			} else {
				app.setAtributo("idMotivoDaViagem", $(this).val());
				$("#grupo_pessoas_ambos").show();
				if (Number($(this).val()) == 5) { // TODO Trabalho = 5. Ajustar se id mudar.
					if (Number($("#pessoas_simples").val()) > 0) {
						$("#grupo_pessoas_trabalho_simples").show();
					}
				} else {
					$("#grupo_pessoas_trabalho_simples").hide();
					app.setAtributo("numeroDePessoasATrabalho", null);
					$("#pessoas_trabalho_simples").val(null);
					if (Number($("#pessoas_simples").val()) > 0) {
						$("#grupo_renda_simples").show();
					}
				}
			}
		});

		// Pessoas no veículo
		$('#pessoas_simples').change(function() {
			app.setAtributo("numeroDePessoasNoVeiculo", $(this).val());
		});
		$('#pessoas_simples').keyup(function() {
			app.logger.log("====keyup: PESSOAS A TRABALHO====");
			if (Number($("#pessoas_simples").val()) > 0) {
				if (Number($('#motivo_viagem_simples').val()) == 5) { // TODO Trabalho. Ajustar se id mudar.
					$("#grupo_pessoas_trabalho_simples").show();
				} else {
					$("#grupo_renda_simples").show();
				}
			} else {
				$("#grupo_pessoas_trabalho_simples").hide();
				$("#grupo_renda_simples").hide();
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

			var ok_origem_bra = true;
			if ((Number($("#origem_pais_simples").val())) == 1) { // Brasil
				ok_origem_bra = (util.validaSelect("origem_uf_simples", "Origem da viagem - estado") && util.validaSelect(
						"origem_municipio_simples", "Origem da viagem - município"));
			}

			var ok_destino_bra = true;
			if ((Number($("#destino_pais_simples").val())) == 1) { // Brasil
				ok_destino_bra = (util.validaSelect("destino_uf_simples", "Destino da viagem - estado") && util.validaSelect(
						"destino_municipio_simples", "Destino da viagem - município"));
			}

			var qtd_pessoas_trabalho = true;
			if ((Number($("#motivo_viagem_simples").val())) == 5) { // TODO Trabalho. Ajustar se id mudar.
				qtd_pessoas_trabalho = util.validaInputNumberRange("pessoas_trabalho_simples", "Pessoas a trabalho", 1, Number($(
						"#pessoas_simples").val()));
			} else {
				qtd_pessoas_trabalho = util.validaInputNumberRange("pessoas_trabalho_simples", "Pessoas a trabalho", 0, 0);
			}

			return (ok_origem_bra && ok_destino_bra && qtd_pessoas_trabalho);
		}
		return false;
	}

};
