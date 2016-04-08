controllers.confirmar_veiculo = {
	config : function() {
		$("#img_confirmar_veiculo").attr('src', app.imagemPath);
		$("#conf_veic_nao").click(function() {
			app.limpaRegistro();
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
					} else // if (imgFileName.match(/(r|s|se)[_0-9]*\.png/g) != null)
					{
						catVeiculo = 'Pesado';
					}
					app.setAtributo('categoria', catVeiculo);
					app.trocaPagina("views/" + registro.classeVeiculo + '/identificacao_visual.html',
							controllers['identificacao_visual_' + registro.classeVeiculo]);
				});
	}
}
