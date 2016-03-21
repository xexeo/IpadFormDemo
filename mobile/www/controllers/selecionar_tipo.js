controllers.selecionar_tipo = {
    config : function() {
        //$(".img_sel").off();
        //console.log('unbind');
            $(".img_sel").click(function() {
                            
                            //veiculo_confirmar = $(this).attr('id');
                            app.setAtributo('codVeiculo', $(this).attr('id'));
                            
                            if ($(this).hasClass('v-simples')){
                            	app.setAtributo('classeVeiculo', 'simples');
                            } else if ($(this).hasClass('v-onibus')){
                            	app.setAtributo('classeVeiculo', 'onibus');
                            } else if ($(this).hasClass('v-carga')){
                            	app.setAtributo('classeVeiculo', 'carga');
                            }
                            
                        app.trocaPagina("confirmar_veiculo.html", controllers.confirmar_veiculo);
                    })
            
    },

}