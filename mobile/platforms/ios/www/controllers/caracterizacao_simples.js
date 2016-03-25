/* global util, registro */

controllers.caracterizacao_simples = {
    config : function(){
        var me = controllers.caracterizacao_simples;
		me.inicializaElementos();
		me.recuperaDadosRegistro();
		me.progressoTela();
        me.buttons();
    },
    
    buttons : function(){
        $('#caracterizacao_simples_back').click( function(){
            app.trocaPagina('views/simples/identificacao_visual.html', controllers.identificacao_visual_simples);
        });
        
       $("#caracterizacao_simples_avancar").click(function(){
           app.trocaPagina('views/simples/caracterizacao_viagem_simples.html', controllers.caracterizacao_viagem_simples);
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
        	combustiveis = ['Bi-Combustível/Etanol', 'Gasolina'];
		}
        util.inicializaSelect("combustivel_simples", lista_combustiveis);
	},

	
	//Preenche os elementos da tela com os valores salvos no registro
	recuperaDadosRegistro : function() {

		util.recuperaSelect(registro.ano, "ano_simples", "grupo_propriedade_simples");
		util.recuperaSelect(registro.propriedade, "propriedade_simples", "grupo_combustivel_simples");
		util.recuperaSelect(registro.combustivel, "combustivel_simples", "grupo_caracterizacao_simples_avancar");
	},


	//Controla o show e hide dos elementos da tela
	progressoTela : function() {
		
		util.progressoSelect("ano", "ano_simples", "grupo_propriedade_simples");
		util.progressoSelect("propriedade", "propriedade_simples", "grupo_combustivel_simples");
		util.progressoSelect("combustivel", "combustivel_simples", "grupo_caracterizacao_simples_avancar");
    }
    
};

