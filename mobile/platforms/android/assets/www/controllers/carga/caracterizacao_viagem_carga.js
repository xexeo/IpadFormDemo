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
				app.finalizaRegistro();
				app.trocaPagina('views/menu.html', controllers.menu);
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		// INICIO PAÍSES
		util.inicializaSelectCustom("origem_pais_carga", paises.listados(), "País");
		util.inicializaSelectCustomValueAsIndex("origem_uf_carga", lista_estados, "UF");

		util.inicializaSelectCustom("destino_pais_carga", paises.listados(), "País");
		util.inicializaSelectCustomValueAsIndex("destino_uf_carga", lista_estados, "UF");
		// FIM PAÍSES

		var lista_frequencias = [ 'Dia', 'Semana', 'Mês', 'Ano', 'Eventualmente' ];
		util.inicializaSelect("frequencia_sel_carga", lista_frequencias);

		// TODO Acertar lista motivos
		var lista_motivos_rota = [ 'Asfalto/Sinalização', 'Caminho mais curto', 'Caminho mais rápido',
				'Proximidade hotéis/postos', 'Segurança', 'Turismo/Paisagem', 'Ausência de pedágio',
				'Ponto obrigatório de passagem', 'Outros' ];
		util.inicializaSelect("motivo_rota_carga", lista_motivos_rota);

	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		// Origem
		util.progressoSelectPais("idOrigemPais", "origem_pais_carga", "origem_uf", "origem_municipio", "grupo_destino_carga",
				"carga");
		util.progressoSelect("origem_uf", "origem_uf_carga", "grupo_origem_municipio_carga");
		$('#origem_uf_carga').change(function() {
			if ($(this).val() != '-1') {
				util.inicializaSelectMunicipio("origem_municipio_carga", $(this).val(), "Município");
			}
		});
		util.progressoSelect("origem_municipio", "origem_municipio_carga", "grupo_destino_carga");

		// Destino
		util.progressoSelectPais("idDestinoPais", "destino_pais_carga", "destino_uf", "destino_municipio",
				"grupo_frequencia_carga", "carga");
		util.progressoSelect("destino_uf", "destino_uf_carga", "grupo_destino_municipio_carga");
		$('#destino_uf_carga').change(function() {
			if ($(this).val() != '-1') {
				util.inicializaSelectMunicipio("destino_municipio_carga", $(this).val(), "Município");
			}
		});
		util.progressoSelect("destino_municipio", "destino_municipio_carga", "grupo_frequencia_carga");

		// Frequencia
		$('#frequencia_num_carga').keyup(function() {
			app.setAtributo('frequencia_num', $(this).val());
		});
		util.progressoSelect("frequencia_sel", "frequencia_sel_carga", "grupo_motivo_rota_carga");

		// TODO Motivo rota
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {

		// TODO
		return true;
	}

};
