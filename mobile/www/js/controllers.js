var controllers = controllers || {};

//insert controller files
var insert_controllers={
    //files to be inserted
    controller_files : ['menu.js', 
                        'selecionar_tipo.js',
                        'confirmar_veiculo.js', 
                        'identificacao_visual_simples.js'],
    
    controller_files_path : 'controllers/',
    
    insert : function(){
            var me = this; 
            $.each(me.controller_files,
                    function(index, item){
                        var s = document.createElement("script");
                        s.type = "text/javascript";
                        s.src = me.controller_files_path + item;
                        $("head").append(s);
                });
    }
};



/*var controllers = {
    menu : function() {
		$('#menu_nova_pesquisa').click(function() {
			app.trocaPagina("selecionar_tipo.html", controllers.selecionar_tipo);
            });
    },

    selecionar_tipo : function() {
            $(".img_sel").click(function() {
                            //veiculo_confirmar = $(this).attr('id');
                            registro.codVeiculo = $(this).attr('id');
                            
                            if ($(this).hasClass('v-simples')){
                                registro.classeVeiculo = 'simples';
                            } else if ($(this).hasClass('v-onibus')){
                                registro.classeVeiculo = 'onibus';
                            } else if ($(this).hasClass('v-onibus')){
                                registro.classeVeiculo = 'carga';
                            }
                            
                        app.trocaPagina("confirmar_veiculo.html", controllers.confirmar_veiculo);
                        console.log("confirmar_veiculo.html", 'controllers.confirmar_veiculo');
                    })
            
    },

    confirmar_veiculo : function() {
            var image_path = "img/tipoVeiculo/" + registro.codVeiculo + ".png";
            $("#img_confirmar_veiculo").attr('src', image_path);
            $("#conf_veic_nao").click(function(){
                registro = {};
                app.trocaPagina('selecionar_tipo.html', controllers.selecionar_tipo);
            });
            $("#conf_veic_sim").click(function(){
                
                app.trocaPagina('views/' + registro.classeVeiculo + '/identificacao_visual.html',
                                controllers['identificacao_visual_' + registro.classeVeiculo]);
            })
            
    },
    
    identificacao_visual_simples : function(){
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
       
    },

    nova_pesquisa : function() {
    }
}*/

