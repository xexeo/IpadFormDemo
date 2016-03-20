controllers.identificacao_visual_simples = {
  config : function(){
       var insert_paises = ""; 
       
       //preenchendo paises
        $.each(paises.listados(), function(index, item){
           insert_paises += "<option value='" + item + "'>" + item +"</option>\n";
       });
       $("#pais_simples").html(insert_paises);
       
        //progresso
       $('#reboque_simples_sim').click(function(){$('#grupo_placa_estrangeira_simples').show()});
       $('#reboque_simples_nao').click(function(){$('#grupo_placa_estrangeira_simples').show()});
       
       $('#placa_estrangeira_simples_sim').click(function(){$('#grupo_pais_simples').show()});
       $('#placa_estrangeira_simples_nao').click(function(){
                                                $('#grupo_pais_simples').hide()
                                                });
       
    }  
};
