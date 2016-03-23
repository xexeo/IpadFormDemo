controllers.caracterizacao_viagem_simples = {
    config : function(){
        var me = controllers.caracterizacao_viagem_simples;
        me.fillSelect();
        me.buttons();
    },
    
    buttons : function(){
        $('#caracterizacao_viagem_simples_back').click( function(){
            app.trocaPagina('caracterizacao_simples.html', controllers.caracterizacao_simples);
        });
        
       $("#caracterizacao_viagem_simples_avancar").click(function(){
           app.trocaPagina('pergunta_final_simples.html', controllers.pergunta_final_simples);
       })
    },
    
    fillSelect : function(){
    	
    	// Preenche combos
        
        // TODO Incluir opções de país, estado e cidade em origem-destino
    	var insert_paises_origem = "<option value='-1'>Selecione</option>\n";
    	$.each(paises.listados(), function(index, item){
    		insert_paises_origem += "<option value='" + item + "'>" + item +"</option>\n";
        });
        $("#origem_simples").html(insert_paises_origem).selectmenu("refresh", true);
        
    	var insert_paises_destino = "<option value='-1'>Selecione</option>\n";
    	$.each(paises.listados(), function(index, item){
    		insert_paises_destino += "<option value='" + item + "'>" + item +"</option>\n";
        });
        $("#destino_simples").html(insert_paises_destino).selectmenu("refresh", true);

        var frequencia_sel = ['Dia', 'Semana', 'Mês', 'Ano', 'Eventualmente'];
        var insert_frequencia = "<option value='-1'>Selecione</option>\n";
        $.each(frequencia_sel, function(index, item){
            insert_frequencia += "<option value='" + item + "'>" + item + "</option>\n"; 
        })
        $("#frequencia_sel_simples").html(insert_frequencia).selectmenu("refresh", true);
        
        var motivos_rota = ['Asfalto/Sinalização', 'Caminho mais curto', 'Caminho mais rápido', 'Proximidade hotéis/postos',
                       'Segurança', 'Turismo/Paisagem', 'Ausência de pedágio', 'Ponto obrigatório de passagem', 'Outros'];
        var insert_motivo_viagem = "<option value='-1'>Selecione</option>\n";
        $.each(motivos_rota, function(index, item){
            insert_motivo_viagem += "<option value='" + item + "'>" + item + "</option>\n"; 
        })
        $("#motivo_rota_simples").html(insert_motivo_viagem).selectmenu("refresh", true);
        
        var motivos_viagem = ['Compra', 'Estudo', 'Lazer', 'Saúde','Trabalho', 'Negócios', 'Outros'];
        var insert_motivo_viagem = "<option value='-1'>Selecione</option>\n";
        $.each(motivos_viagem, function(index, item){
        	insert_motivo_viagem += "<option value='" + item + "'>" + item + "</option>\n"; 
        })
        $("#motivo_viagem_simples").html(insert_motivo_viagem).selectmenu("refresh", true);
        
        var rendas = ['Até R$ 1.500,00', 'De R$ 1.501,00 a R$ 3.000,00', 'De R$ 3.001,00 a R$ 4.500,00', 
                      'De R$ 4.501,00 a R$ 6.500,00', 'De R$ 6.501,00 a R$ 10.500,00', 'Acima de R$ 10.501,00',
                      'Não informada', 'Sem renda própria'];
        var insert_renda = "<option value='-1'>Selecione</option>\n";
        $.each(rendas, function(index, item){
        	insert_renda += "<option value='" + item + "'>" + item + "</option>\n"; 
        })
        $("#renda_simples").html(insert_renda).selectmenu("refresh", true);
        
        
        // Valores anteriores e eventos
        if (registro.origem != null) {
        	$("#origem_simples option[value='" + registro.origem + "'").attr('selected', true);
        	$("#grupo_destino_simples").show();
		}
        $("select#origem_simples").selectmenu("refresh", true);
        
        if (registro.destino != null) {
        	$("#destino_simples option[value='" + registro.destino + "'").attr('selected', true);
        	$("#grupo_frequencia_simples").show();
		}
        $("select#destino_simples").selectmenu("refresh", true);
        
        controllers.caracterizacao_viagem_simples.auxPreencheElementosText(registro.frequencia_num, "frequencia_num", "motivo_rota");
        controllers.caracterizacao_viagem_simples.auxPreencheElementosSel(registro.frequencia_sel, "frequencia_sel", "motivo_rota");
        
        controllers.caracterizacao_viagem_simples.auxPreencheElementosSel(registro.motivo_rota, "motivo_rota", "pessoas");
        
        controllers.caracterizacao_viagem_simples.auxPreencheElementosText(registro.pessoas, "pessoas", "motivo_viagem");
        controllers.caracterizacao_viagem_simples.auxPreencheElementosText(registro.pessoas_trabalho, "pessoas_trabalho", "motivo_viagem");
        
        controllers.caracterizacao_viagem_simples.auxPreencheElementosSel(registro.motivo_viagem, "motivo_viagem", "renda");
        
        controllers.caracterizacao_viagem_simples.auxPreencheElementosSelAvancar(registro.renda, "renda", "caracterizacao_viagem");


        // Progresso
        $('#origem_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('origem', $(this).val());
                $("#grupo_destino_simples").show();
            } else {
                $("#grupo_destino_simples").hide();
                app.setAtributo('origem', null);
            }
        });
        $('#destino_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('destino', $(this).val());
                $("#grupo_frequencia_simples").show();
            } else {
                $("#grupo_frequencia_simples").hide();
                app.setAtributo('destino', null);
            }
        });
        $('#frequencia_num_simples').blur(function(){
        	app.setAtributo('frequencia_num', $(this).val());
        });
        $('#frequencia_sel_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('frequencia_sel', $(this).val());
                $("#grupo_motivo_rota_simples").show();
            } else {
                $("#grupo_motivo_rota_simples").hide();
                app.setAtributo('frequencia_sel', null);
            }
        });
        $('#motivo_rota_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('motivo_rota', $(this).val());
                $("#grupo_pessoas_simples").show();
            } else {
                $("#grupo_pessoas_simples").hide();
                app.setAtributo('motivo_rota', null);
            }
        });
        $('#pessoas_simples').blur(function(){
        	app.setAtributo('pessoas', $(this).val());
        	$("#grupo_motivo_viagem_simples").show();
        });
        $('#pessoas_trabalho_simples').blur(function(){
        	app.setAtributo('pessoas_trabalho', $(this).val());
        	$("#grupo_motivo_viagem_simples").show();
        });
        $('#motivo_viagem_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('motivo_viagem', $(this).val());
                $("#grupo_renda_simples").show();
            } else {
                $("#grupo_renda_simples").hide();
                app.setAtributo('motivo_viagem', null);
            }
        });
        $('#renda_simples').change(function(){
            if(Number($(this).val()) != -1){
                app.setAtributo('renda', $(this).val());
                $("#grupo_caracterizacao_viagem_simples_avancar").show();
            } else {
                $("#grupo_caracterizacao_viagem_simples_avancar").hide();
                app.setAtributo('renda', null);
            }
        });
    },
    
    auxPreencheElementosSel : function(reg, nome_campo, nome_proximo) {
    	var nome_simples = nome_campo + "_simples";
    	var grupo_proximo = "grupo_" + nome_proximo + "_simples";
        if (reg != null) {
        	$("#" + nome_simples + " option[value='" + reg + "'").attr('selected', true);
        	$("#" + grupo_proximo).show();
		}
        $("select#" + nome_simples).selectmenu("refresh", true);
    },
    
    auxPreencheElementosText : function(reg, nome_campo, nome_proximo) {
    	var nome_simples = nome_campo + "_simples";
    	var grupo_proximo = "grupo_" + nome_proximo + "_simples";
        if (reg != null) {
        	$("#" + nome_simples).attr('value', reg);
        	$("#" + grupo_proximo).show();
		}
    },
    
    auxPreencheElementosSelAvancar : function(reg, nome_campo, nome_proximo) {
    	var nome_simples = nome_campo + "_simples";
    	var grupo_proximo = "grupo_" + nome_proximo + "_simples_avancar";
        if (reg != null) {
        	$("#" + nome_simples + " option[value='" + reg + "'").attr('selected', true);
        	$("#" + grupo_proximo).show();
		}
        $("select#" + nome_simples).selectmenu("refresh", true);
    }
    
    
};

