var controllers = {
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
    },

    nova_pesquisa : function() {
    }
}

