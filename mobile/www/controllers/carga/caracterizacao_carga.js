/* global util, registro */

controllers.caracterizacao_carga = {
	config : function() {
		var me = controllers.caracterizacao_carga;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	// TODO Acertar essa constante após alterar para o id correto
	idPropriedadePropriaParticularAutônomo : 2,

	buttons : function() {
		$("#caracterizacao_carga_avancar").click(function() {
			var ok = controllers.caracterizacao_carga.validar_componentes();
			if (ok) {
				app.trocaPagina('views/carga/caracterizacao_viagem_carga.html', controllers.caracterizacao_viagem_carga);
			}
		})
	},

	// Inicializa os elementos da tela
	inicializaElementos : function() {

		var lista_anos = [];
		for (var ano = 2017; ano > 1899; ano--) {
			lista_anos = lista_anos.concat(ano);
		}
		util.inicializaSelectCustomValueAsIndex("ano_carga", lista_anos, "Selecione");

		// placa cinza
		if (app.getAtributo('placaVermelha') == true) {
			// placa vermelha
			util.inicializaTabelaAuxiliar("propriedade_carga", "Selecione", lista_propriedade_veiculo, "carga_vermelha");
		} else {
			// placa cinza
			util.inicializaTabelaAuxiliar("propriedade_carga", "Selecione", lista_propriedade_veiculo, "carga_cinza");
		}

		var lista_agregado = [ 'Transportadora', 'Empresa/Frota própria', 'Não' ];
		util.inicializaSelect("agregado_carga", lista_agregado);
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {
		var me = controllers.caracterizacao_carga;

		util.progressoSelect("anoDeFabricacao", "ano_carga", "grupo_propriedade_carga");

		$('#propriedade_carga').change(function() {
			var selecionado = Number($(this).val());
			if (selecionado != -1) {
				app.setAtributo('idPropriedadesDoVeiculo', $(this).val());

				if (selecionado == me.idPropriedadePropriaParticularAutônomo) {
					$("#grupo_agregado_carga").show();
					$("#grupo_caracterizacao_carga_avancar").hide();
				} else {
					$("#grupo_agregado_carga").hide();
					$("#grupo_caracterizacao_carga_avancar").show();

					app.setAtributo('idAgregado', null);
					util.progressoRestartSelect("agregado_carga", "Selecione");
				}
			} else {
				$("#grupo_agregado_carga").hide();
				$("#grupo_caracterizacao_carga_avancar").hide();
				app.setAtributo('idPropriedadesDoVeiculo', null);
				app.setAtributo('idAgregado', null);
				util.progressoRestartSelect("agregado_carga", "Selecione");
			}
		});

		util.progressoSelect("idAgregado", "agregado_carga", "grupo_caracterizacao_carga_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		var me = controllers.caracterizacao_carga;

		if (util.validaSelect("ano_carga", "Ano de fabricação") && util.validaSelect("propriedade_carga", "Propriedade")) {

			if (Number($("#propriedade_carga").val()) == me.idPropriedadePropriaParticularAutônomo) {
				return util.validaSelect("agregado_carga", "Agregado");
			}

			return true;
		}
		return false;
	}

};
