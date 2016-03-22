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
           app.trocaPagina('caracterizacao_viagem_simples.html', controllers.caracterizacao_viagem_simples);
       })
    },
    
    fillSelect : function(){
    	
    	// Preenche combos
    	var anos = "<option value='-1'>Selecione</option>\n";
        for (var ano=2016;ano>1899;ano--){
            anos += "<option value='" + String(ano) + "'>" + String(ano) + "</option>\n";
        }
        $("#ano_simples").html(anos).selectmenu("refresh", true);
        
        var propriedades = ['Próprio', 'Alugado/Fretado', 'Empresa', 'Taxi', 'Serviços Públicos', 'Outros'];
        var insert_propriedades = "<option value='-1'>Selecione</option>\n";
        $.each(propriedades, function(index, item) {
        	insert_propriedades += "<option value='" + item + "'>" + item + "</option>\n";
        })
        $("#propriedade_simples").html(insert_propriedades).selectmenu("refresh", true);

        var combustiveis = ['Álcool/Etanol', 'Bi-Combustível/Etanol', 'Diesel', 'Gasolina', 'GNV/Gás Natural', 'Híbrido'];
        // Se o veículo leve for moto, as opções para combustível são diferentes
        if (registro.codVeiculo == 'tpVL03') {
        	combustiveis = ['Bi-Combustível/Etanol', 'Gasolina'];
		}
        var insert_combustiveis = "<option value='-1'>Selecione</option>\n";
        $.each(combustiveis, function(index, item){
            insert_combustiveis += "<option value='" + item + "'>" + item + "</option>\n"; 
        })
        $("#combustivel_simples").html(insert_combustiveis).selectmenu("refresh", true);
        
        
        // Valores anteriores e eventos
        if (registro.ano != null) {
        	$("#ano_simples option[value='" + registro.ano + "'").attr('selected', true);
        	$("#grupo_propriedade_simples").show();
		}
        $("select#ano_simples").selectmenu("refresh", true);
        
        if (registro.propriedade != null) {
        	$("#propriedade_simples option[value='" + registro.propriedade + "'").attr('selected', true);
        	$("#grupo_combustivel_simples").show();
		}
        $("select#propriedade_simples").selectmenu("refresh", true);
        
        if (registro.combustivel!= null) {
        	$("#combustivel_simples option[value='" + registro.combustivel + "'").attr('selected', true);
        	$("#grupo_caracterizacao_simples_avancar").show();
		}
        $("select#combustivel_simples").selectmenu("refresh", true);
        
        
        // Progresso
        $('#ano_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('ano', $(this).val());
                $("#grupo_propriedade_simples").show();
            } else {
                $("#grupo_propriedade_simples").hide();
                app.setAtributo('ano', null);
            }
        });
        $('#propriedade_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('propriedade', $(this).val());
                $("#grupo_combustivel_simples").show();
            } else {
                $("#grupo_combustivel_simples").hide();
                app.setAtributo('propriedade', null);
            }
        });
        $('#combustivel_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('combustivel', $(this).val());
                $("#grupo_caracterizacao_simples_avancar").show();
            } else {
                $("#grupo_caracterizacao_simples_avancar").hide();
                app.setAtributo('combustivel', null);
            }
        });
        
    }
    
};

