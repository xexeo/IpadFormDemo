controllers.confirmar_veiculo = {
	config : function() {

		$("#img_confirmar_veiculo").attr('src', app.imagemPath);
		$("#conf_veic_nao").click(function() {
                        //clear
			app.iniciaRegistro();
			app.trocaPagina('views/selecionar_tipo.html', controllers.selecionar_tipo);
		});
		$("#conf_veic_sim").click(
				function() {
					var imgFileName = app.imagemPath.split("/").reverse()[0];
					var catVeiculo;
					if (imgFileName.match(/(m)[_0-9]*\.png/g) != null) {
						catVeiculo = 'Moto';
					} else if (imgFileName.match(/(p)[_0-9]*\.png/g) != null) {
						catVeiculo = 'Leve';
					} else if (imgFileName.match(/(o)[_0-9]*\.png/g) != null) {
						catVeiculo = 'Onibus';
					} else // if (imgFileName.match(/(c|r|s|se)[_0-9]*\.png/g) != null)
					{
						catVeiculo = 'Pesado';
						var tipoCaminhao;
						if (imgFileName.match(/(c)[_0-9]*\.png/g) != null) {
							tipoCaminhao = 'Leve';
						}
						if (imgFileName.match(/(s)[_0-9]*\.png/g) != null) {
							tipoCaminhao = 'Semirreboque';
						}
						if (imgFileName.match(/(se)[_0-9]*\.png/g) != null) {
							tipoCaminhao = 'Semirreboque Especial';
						}
						if (imgFileName.match(/(r)[_0-9]*\.png/g) != null) {
							tipoCaminhao = 'Reboque';
						}
						app.setAtributo('tipoCaminhao', tipoCaminhao);
					}
					app.setAtributo('categoria', catVeiculo);
					app.trocaPagina("views/" + registro.classeVeiculo + '/identificacao_visual.html',
							controllers['identificacao_visual_' + registro.classeVeiculo]);
				});
	}
}
