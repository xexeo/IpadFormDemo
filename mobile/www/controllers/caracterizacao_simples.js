controllers.caracterizacao_simples = {
    config : function(){
        var me = controllers.caracterizacao_simples;
        me.fillSelect();
        me.buttons();
    },
    
    buttons : function(){
        $('#caracterizacao_simples_back').click( function(){
            app.trocaPagina('identificacao_visual.html', controllers.identificacao_visual_simples);
        });
        
       $("#caracterizacao_simples_avancar").click(function(){
           app.trocaPagina('caracterizacao_viagem_1.html', controllers.caracterizacao_viagem_1_simples);
       })
    },
    
    fillSelect : function(){
        var anos = "<option value='-1'>Selecione</option>\n";
        
        for (var ano=2016;ano>1899;ano--){
            anos += "<option value='" + String(ano) + "'>" + String(ano) + "</option>\n";
        }
        
        $("#ano_simples").html(anos).selectmenu("refresh", true);
        
        //TODO: implementar restrição a combustíveis de moto - necessita códigos veículos leves
        
        var combustiveis = ['Álcool/Etanol', 'Bi-Combustível/Etanol', 'Diesel',
                            'Gasolina', 'GNV/Gás Natural', 'Híbrido'
                            ];
        var insert_combustiveis = "<option value='-1'>Selecione</option>\n";
        
        $.each(combustiveis, function(index, item){
            insert_combustiveis += "<option value='" + item + "'>" + item + "</option>\n"; 
        })
        
        $("#combustivel_simples").html(insert_combustiveis).selectmenu("refresh", true);
    }
    
    
    
};

