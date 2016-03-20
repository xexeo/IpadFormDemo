controllers.identificacao_visual_simples = {
  config : function(){
      //é preciso carregar o controller anterior
      //$('#identificacao_visual_simples_back').click($.mobile.back);
      $('#identificacao_visual_simples_back').click( function(){
            app.trocaPagina('../../selecionar_tipo.html', controllers.selecionar_tipo);
        });
      
       var insert_paises = "<option value='-1'>Selecione</option>\n"; 
       
       //preenchendo paises
        $.each(paises.listados(), function(index, item){
           insert_paises += "<option value='" + item + "'>" + item +"</option>\n";
       });
       $("#pais_simples").html(insert_paises);
       
       //fill controls with previous values
       if(registro.reboque != null && registro.reboque){
           $('#reboque_simples_sim').prop('checked', true).checkboxradio('refresh');
           $('#grupo_placa_estrangeira_simples').show();
       } else if (registro.reboque != null && !registro.reboque){
           $('#reboque_simples_nao').prop('checked', true).checkboxradio('refresh');;
           $('#grupo_placa_estrangeira_simples').show();
       } else if (registro.reboque == null){
           $('#reboque_simples_sim').prop('checked', false).checkboxradio('refresh');
           $('#reboque_simples_nao').prop('checked', false).checkboxradio('refresh');
           $('#grupo_placa_estrangeira_simples').hide();
       }
       
       if (registro.placa_estrangeira != null && registro.placa_estrangeira){
            $('#placa_estrangeira_simples_sim').prop('checked', true).checkboxradio('refresh');;
            $('#grupo_pais_simples').show();
            $("#grupo_avancar_simples").hide();
       } else if (registro.placa_estrangeira != null && !registro.placa_estrangeira){
            $('#placa_estrangeira_simples_nao').prop('checked', true).checkboxradio('refresh');;
            $('#grupo_pais_simples').hide();
            $("#pais_simples option[value='-1']").attr('selected', true);
       } else if (registro.placa_estrangeira == null){
           $('#placa_estrangeira_simples_nao').prop('checked', false).checkboxradio('refresh');;
           $('#placa_estrangeira_simples_sim').prop('checked', false).checkboxradio('refresh');;
           $('#grupo_pais_simples').hide();
           $("#pais_simples option[value='-1']").attr('selected', true);
           $("#grupo_avancar_simples").hide();
       }
       
       if (registro.pais != null){
           $("#pais_simples option[value='" + registro.pais + "'").attr('selected', true);
           $("#grupo_avancar_simples").show();
       }
       
       $("select#pais_simples").selectmenu("refresh", true);
           
       
       
       //progresso
       $('#reboque_simples_sim').click(function(){
           $('#grupo_placa_estrangeira_simples').show();
           registro.reboque = true;
       });
       $('#reboque_simples_nao').click(function(){
           $('#grupo_placa_estrangeira_simples').show();
           registro.reboque = false;
       });
       
       $('#placa_estrangeira_simples_sim').click(function(){
           $('#grupo_pais_simples').show();
           registro.placa_estrangeira = true;
           
           if(Number($('#pais_simples').val()) == -1){
               $("#grupo_avancar_simples").hide();
           }
           
       });
       $('#placa_estrangeira_simples_nao').click(function(){
            $('#grupo_pais_simples').hide()
            registro.placa_estrangeira = false;
            $("#grupo_avancar_simples").show();
        });
        
        $('#pais_simples').change(function(){
            if(Number($(this).val()) != -1){
                registro.pais = $(this).val();
                $("#grupo_avancar_simples").show();
            } else {
                $("#grupo_avancar_simples").hide();
                registro.pais = null;
            }
            
            
        });
       
    }  
};
