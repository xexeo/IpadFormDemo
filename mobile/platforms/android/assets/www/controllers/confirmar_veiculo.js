controllers.confirmar_veiculo = {
    config : function() {
            $("#img_confirmar_veiculo").attr('src', app.imagemPath);
            $("#conf_veic_nao").click(function(){
            	app.limpaRegistro();
                app.trocaPagina('selecionar_tipo.html', controllers.selecionar_tipo);
            });
            $("#conf_veic_sim").click(function(){
                
                app.trocaPagina("views/" + registro.classeVeiculo + '/identificacao_visual.html',
                                controllers['identificacao_visual_' + registro.classeVeiculo]);
            });
        }
}

