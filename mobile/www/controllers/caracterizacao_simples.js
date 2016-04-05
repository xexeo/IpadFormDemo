/* global util, registro */

controllers.caracterizacao_simples = {
    config : function(){
        var me = controllers.caracterizacao_simples;
		me.inicializaElementos();
		me.progressoTela();
        me.buttons();
    },
    
    buttons : function(){
       $("#caracterizacao_simples_avancar").click(function(){
			var ok = controllers.caracterizacao_simples.validar_componentes();
			if(ok) {
				app.trocaPagina('views/simples/caracterizacao_viagem_simples.html', controllers.caracterizacao_viagem_simples);
			}
       })
    },
    
	//Inicializa os elementos da tela
    inicializaElementos : function(){
    	
    	var lista_anos = [];
        for (var ano=2016;ano>1899;ano--){
        	lista_anos = lista_anos.concat(ano);
        }
        util.inicializaSelect("ano_simples", lista_anos);
        
        var lista_propriedades = ['Próprio', 'Alugado/Fretado', 'Empresa', 'Taxi', 'Serviços Públicos', 'Outros'];
        util.inicializaSelect("propriedade_simples", lista_propriedades);

        var lista_combustiveis = ['Álcool/Etanol', 'Bi-Combustível/Etanol', 'Diesel', 'Gasolina', 'GNV/Gás Natural', 'Híbrido'];
        // Se o veículo leve for moto, as opções para combustível são diferentes
        if (registro.codVeiculo == 'tpVL03') {
        	lista_combustiveis = ['Bi-Combustível/Etanol', 'Gasolina'];
		}
        util.inicializaSelect("combustivel_simples", lista_combustiveis);
	},

	
	//Controla o show e hide dos elementos da tela
	progressoTela : function() {
		
		util.progressoSelect("ano", "ano_simples", "grupo_propriedade_simples");
		util.progressoSelect("propriedade", "propriedade_simples", "grupo_combustivel_simples");
		util.progressoSelect("combustivel", "combustivel_simples", "grupo_caracterizacao_simples_avancar");
    },

	// Controla as validações dos componentes de tela após clicas em AVANÇAR
	validar_componentes : function(id_avancar) {
		if(util.validaSelect("ano_simples", "Ano do veículo") && 
				util.validaSelect("propriedade_simples", "Propriedade") &&
				util.validaSelect("combustivel_simples", "Combustível")) {
			return true;
		}
		return false;
	}

};

