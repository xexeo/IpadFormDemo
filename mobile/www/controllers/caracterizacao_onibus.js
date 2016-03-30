/* global util, registro */

controllers.caracterizacao_onibus = {
    config : function(){
        var me = controllers.caracterizacao_onibus;
		me.inicializaElementos();
		me.progressoTela();
        me.buttons();
    },
    
    buttons : function(){
       $("#caracterizacao_onibus_avancar").click(function(){
           app.trocaPagina('views/onibus/caracterizacao_viagem_onibus.html', controllers.caracterizacao_viagem_onibus);
       })
    },
    
	//Inicializa os elementos da tela
    inicializaElementos : function(){
    	
    	var lista_anos = [];
        for (var ano=2016;ano>1899;ano--){
        	lista_anos = lista_anos.concat(ano);
        }
        util.inicializaSelect("ano_onibus", lista_anos);
        
        var lista_propriedades = ['Próprio', 'Alugado/Fretado', 'Empresa', 'Serviços Públicos', 'Outros'];
        util.inicializaSelect("propriedade_onibus", lista_propriedades);
	},

	
	//Controla o show e hide dos elementos da tela
	progressoTela : function() {
		
		util.progressoSelect("ano", "ano_onibus", "grupo_propriedade_onibus");
		util.progressoSelect("propriedade", "propriedade_onibus", "grupo_caracterizacao_onibus_avancar");
    }
    
};
