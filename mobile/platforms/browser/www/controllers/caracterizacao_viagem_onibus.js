controllers.caracterizacao_viagem_onibus = {
    config : function(){
        var me = controllers.caracterizacao_viagem_onibus;
		me.inicializaElementos();
		me.progressoTela();
        me.buttons();
    },
    
    buttons : function(){
       $("#caracterizacao_viagem_onibus_avancar").click(function(){
           // TODO Salvar dados do ciclo de consulta
    	   
    	   // Ir para tela inicial
    	   app.trocaPagina('views/menu.html', controllers.menu);
       })
    },
    
	//Inicializa os elementos da tela
    inicializaElementos : function(){

    	// TODO Incluir opções de país, estado e cidade em origem-destino
    	
        util.inicializaSelect("origem_onibus", paises.listados());
        
        util.inicializaSelect("destino_onibus", paises.listados());

        //FIM PAÍSES
        
        var lista_frequencias = ['Dia', 'Semana', 'Mês', 'Ano', 'Eventualmente'];
        util.inicializaSelect("frequencia_sel_onibus", lista_frequencias);
        
        var lista_tipo_viagem = ['Regular', 'Turismo', 'Fretado/Particular', 'Outros'];
        util.inicializaSelect("tipo_viagem_onibus", lista_tipo_viagem);
	},

	
	//Controla o show e hide dos elementos da tela
	progressoTela : function() {
		
		util.progressoSelect("origem", "origem_onibus", "grupo_destino_onibus");
		util.progressoSelect("destino", "destino_onibus", "grupo_frequencia_onibus");
        
        $('#frequencia_num_onibus').keyup(function(){
        	app.setAtributo('frequencia_num', $(this).val());
        });
        util.progressoSelect("frequencia_sel", "frequencia_sel_onibus", "grupo_tipo_viagem_onibus");
        
        util.progressoSelect("tipo_viagem", "tipo_viagem_onibus", "grupo_pessoas_onibus");
        
        util.progressoInputText("pessoas", "pessoas_onibus", "grupo_caracterizacao_viagem_onibus_avancar");
    }
};

