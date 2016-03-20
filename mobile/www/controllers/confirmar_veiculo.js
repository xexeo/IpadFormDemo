controllers.confirmar_veiculo = {
    config : function() {
            var image_path = "img/tipoVeiculo/" + registro.codVeiculo + ".png";
            $("#img_confirmar_veiculo").attr('src', image_path);
            $("#conf_veic_nao").click(function(){
                registro.codVeiculo = null;
                registro.classeVeiculo = null;
                app.trocaPagina('selecionar_tipo.html', controllers.selecionar_tipo);
            });
            $("#conf_veic_sim").click(function(){
                
                app.trocaPagina('views/' + registro.classeVeiculo + '/identificacao_visual.html',
                                controllers['identificacao_visual_' + registro.classeVeiculo]);
            });
        }
}

