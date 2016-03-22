controllers.pergunta_final_simples = {
    config : function(){
        var me = controllers.pergunta_final_simples;
        me.fillSelect();
        me.buttons();
    },
    
    buttons : function(){
        $('#pergunta_final_simples_back').click( function(){
            app.trocaPagina('caracterizacao_viagem_simples.html', controllers.caracterizacao_viagem_simples);
        });
        
       $("#pergunta_final_simples_avancar").click(function(){
    	   // TODO FINALIZAR
    	   // Salvar dados no sqlite; Apresentar mensagem de salvo com sucesso; Direcionar para menu;
       })
    },
    
    fillSelect : function(){
    	//TODO
    	
        
    }
    
};

