controllers.identificacao_visual_simples = {
  config : function(){
      var me = controllers.identificacao_visual_simples;
      me.fillSelect();
      me.buttons();
  },
  
  buttons : function(){
      $('#identificacao_visual_simples_back').click( function(){
          app.trocaPagina('../confirmar_veiculo.html', controllers.confirmar_veiculo);
      });
      
     $("#identificacao_visual_simples_avancar").click(function(){
         app.trocaPagina('caracterizacao_simples.html', controllers.caracterizacao_simples);
     })
  },
  
  fillSelect : function(){
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
            $("#grupo_identificacao_visual_simples_avancar").hide();
       } else if (registro.placa_estrangeira != null && !registro.placa_estrangeira){
            $('#placa_estrangeira_simples_nao').prop('checked', true).checkboxradio('refresh');;
            $('#grupo_pais_simples').hide();
            $("#pais_simples option[value='-1']").attr('selected', true);
       } else if (registro.placa_estrangeira == null){
           $('#placa_estrangeira_simples_nao').prop('checked', false).checkboxradio('refresh');;
           $('#placa_estrangeira_simples_sim').prop('checked', false).checkboxradio('refresh');;
           $('#grupo_pais_simples').hide();
           $("#pais_simples option[value='-1']").attr('selected', true);
           $("#grupo_identificacao_visual_simples_avancar").hide();
       }
       
       if (registro.pais != null){
           $("#pais_simples option[value='" + registro.pais + "'").attr('selected', true);
           $("#grupo_identificacao_visual_simples_avancar").show();
       } else if (registro.placa_estrangeira != null && !registro.placa_estrangeira){
           $("#grupo_identificacao_visual_simples_avancar").show();
       }
       
       $("select#pais_simples").selectmenu("refresh", true);
           
       
       
       //progresso
       $('#reboque_simples_sim').click(function(){
           $('#grupo_placa_estrangeira_simples').show();
           app.setAtributo('reboque', true);
       });
       $('#reboque_simples_nao').click(function(){
           $('#grupo_placa_estrangeira_simples').show();
           app.setAtributo('reboque', false);
       });
       
       $('#placa_estrangeira_simples_sim').click(function(){
           $('#grupo_pais_simples').show();
           app.setAtributo('placa_estrangeira', true);
           
           if(Number($('#pais_simples').val()) == -1){
               $("#grupo_identificacao_visual_simples_avancar").hide();
           }
           
       });
       $('#placa_estrangeira_simples_nao').click(function(){
            $('#grupo_pais_simples').hide()
            app.setAtributo('placa_estrangeira', false);
            app.setAtributo('pais', null);
            
            $("#pais_simples option:contains('Selecione')").prop({selected: true});
            $("select#pais_simples").selectmenu("refresh", true);
            
            $("#grupo_identificacao_visual_simples_avancar").show();
        });
        
        $('#pais_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('pais', $(this).val());
                $("#grupo_identificacao_visual_simples_avancar").show();
            } else {
                $("#grupo_identificacao_visual_simples_avancar").hide();
                app.setAtributo('pais', null);
            }
            
        });
       
    }  
};
