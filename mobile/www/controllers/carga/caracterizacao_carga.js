/* global util, registro */
/// @file caracterizacao_carga.js
/// @namespace controllers.caracterizacao_carga
/// Funções da tela caracterizacao_carga
controllers.caracterizacao_carga = {
	/// @function controllers.caracterizacao_carga.config
	/// Controla o comportamento da tela
	/// @return {void} função sem retorno
	config : function() {
		var me = controllers.caracterizacao_carga;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

	// TODO Acertar essa constante após alterar para o id correto
	idPropriedadePropriaParticularAutônomo : 2,

	/// @function controllers.caracterizacao_carga.buttons
	/// Controla o comportamento dos botões
	/// @return {void} função sem retorno
	buttons : function() {
		$("#caracterizacao_carga_avancar").click(function() {
			var ok = controllers.caracterizacao_carga.validar_componentes();
			if (ok) {
				app.trocaPagina('views/carga/caracterizacao_viagem_carga.html', controllers.caracterizacao_viagem_carga);
			}
		})
	},

	/// @function controllers.caracterizacao_carga.inicializaElementos
	/// Inicializa os elementos da tela
	/// @return {void} função sem retorno
	inicializaElementos : function() {

//		var lista_anos = [];
//		for (var ano = 2017; ano > 1899; ano--) {
//			lista_anos = lista_anos.concat(ano);
//		}

		var lista_anos = util.geraListaAnos();
		
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

	/// @function controllers.caracterizacao_carga.progressoTela
	/// Controla o show e hide dos elementos da tela
	/// @return {void} função sem retorno
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

	/// @function controllers.caracterizacao_carga.validar_componentes
	/// Controla as validações dos componentes de tela após clicar em AVANÇAR
	/// @return {bool} true se os valores informados forem válidos
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
