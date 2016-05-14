controllers.selecionar_tipo = {
	config : function() {

		$(".switch_classe_veiculo").click(function() {
			$('#sel_tipo_veiculo_1').toggle();
			$('#sel_tipo_veiculo_2').toggle();
		});

		$(".img_sel_veiculo").click(function() {

			app.imagemPath = $(this).attr('src');
			// veiculo_confirmar = $(this).attr('id');
			// app.setAtributo('tipo', $(this).attr('id').split("_")[0]);
			app.setAtributo('tipo', $(this).attr('id'));

			if ($(this).hasClass('v-simples')) {
				app.setAtributo('classeVeiculo', 'simples');
			} else if ($(this).hasClass('v-onibus')) {
				app.setAtributo('classeVeiculo', 'onibus');
			} else if ($(this).hasClass('v-carga')) {
				app.setAtributo('classeVeiculo', 'carga');
			} else if ($(this).hasClass('v-semireboque')) {
				app.setAtributo('classeVeiculo', 'carga');
			} else if ($(this).hasClass('v-combinado')) {
				app.setAtributo('classeVeiculo', 'carga');
			} else if ($(this).hasClass('v-reboque')) {
				app.setAtributo('classeVeiculo', 'carga');
			}

			app.trocaPagina("views/confirmar_veiculo.html", controllers.confirmar_veiculo);
		});
		
	},

}