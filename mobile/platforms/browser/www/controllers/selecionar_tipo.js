controllers.selecionar_tipo = {
    config : function() {
        //$(".img_sel").off();
        //console.log('unbind');
            $(".img_sel").click(function() {
                            //console.log('clicked');
                            //clear registro
                            //registro = {};
                            
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
                        //console.log("confirmar_veiculo.html", 'controllers.confirmar_veiculo');
                    })
            
    },

}