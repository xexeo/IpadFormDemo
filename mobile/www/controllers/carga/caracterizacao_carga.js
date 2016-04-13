/* global util, registro */

controllers.caracterizacao_carga = {
	config : function() {
		var me = controllers.caracterizacao_carga;
		me.inicializaElementos();
		me.progressoTela();
		me.buttons();
	},

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
		for (var ano = 2016; ano > 1899; ano--) {
			lista_anos = lista_anos.concat(ano);
		}
		util.inicializaSelectCustomValueAsIndex("ano_carga", lista_anos, "Selecione");
		
		//placa cinza
		var lista_propriedades = [ 'Empresa/Frota própria'];
		if (false) { //TODO if placa vermelha
			lista_propriedades = [ 'Transportadora', 'Própria/Particular/Autônomo',];
		}
		util.inicializaSelect("propriedade_carga", lista_propriedades);
		
		var lista_agregado = [ 'Transportadora', 'Empresa/Frota própria', 'Não'];
		util.inicializaSelect("agregado_carga", lista_agregado);
	},

	// Controla o show e hide dos elementos da tela
	progressoTela : function() {

		util.progressoSelect("anoDeFabricacao", "ano_carga", "grupo_propriedade_carga");
		
		//TODO Agregado aparece somente se propriedade for 'Própria/Particular/Autônomo'
		util.progressoSelect("idPropriedadesDoVeiculo", "propriedade_carga", "grupo_agregado_carga");
		
		util.progressoSelect("idAgregado", "agregado_carga", "grupo_caracterizacao_carga_avancar");
	},

	// Controla as validações dos componentes de tela após clicar em AVANÇAR
	validar_componentes : function(id_avancar) {
		//TODO
		
		return true;
	}

};
