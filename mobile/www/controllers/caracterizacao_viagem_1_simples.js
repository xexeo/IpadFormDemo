controllers.caracterizacao_viagem_1_simples = {
    config : function(){
        var me = controllers.caracterizacao_viagem_1_simples;
        me.fillSelect();
        me.buttons();
    },
    
    buttons : function(){
        $('#caracterizacao_viagem_1_simples_back').click( function(){
            app.trocaPagina('caracterizacao_simples.html', controllers.identificacao_visual_simples);
        });
        
       $("#caracterizacao_viagem_1_simples_avancar").click(function(){
           app.trocaPagina('caracterizacao_viagem_2_simples.html', controllers.caracterizacao_viagem_1_simples);
       })
    },
    
    fillSelect : function(){
    	
    	// Preenche combos
        var insert_paises = "<option value='-1'>Selecione</option>\n"; 
        
        //preenchendo paises
         $.each(paises.listados(), function(index, item){
            insert_paises += "<option value='" + item + "'>" + item +"</option>\n";
        });
        $("#origem_simples").html(insert_paises);
        $("#destino_simples").html(insert_paises);

        var frequencia_sel = ['Dia', 'Semana', 'Mês', 'Ano', 'Eventualmente'];
        var insert_frequencia = "<option value='-1'>Selecione</option>\n";
        $.each(frequencia_sel, function(index, item){
            insert_frequencia += "<option value='" + item + "'>" + item + "</option>\n"; 
        })
        $("#frequencia_sel_simples").html(insert_frequencia).selectmenu("refresh", true);
        
        var motivos = ['Asfalto/Sinalização', 'Caminho mais curto', 'Caminho mais rápido', 'Proximidade hotéis/postos',
                       'Segurança', 'Turismo/Paisagem', 'Ausência de pedágio', 'Ponto obrigatório de passagem', 'Outros'];
        var insert_motivo = "<option value='-1'>Selecione</option>\n";
        $.each(motivos, function(index, item){
            insert_motivo += "<option value='" + item + "'>" + item + "</option>\n"; 
        })
        $("#motivo_simples").html(insert_motivo).selectmenu("refresh", true);
        
        
        // Valores anteriores e eventos
        // TODO
        
        
        // Progresso
        // TODO

        
    }
    
};

