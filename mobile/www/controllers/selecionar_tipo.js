controllers.selecionar_tipo = {
	config : function() {
		
		$(".switch_classe_veiculo").click(function() {
			console.log(1);
			$('#sel_tipo_veiculo_1').toggle();
			$('#sel_tipo_veiculo_2').toggle();
		});
		
		$(".img_sel").click(function() {

			// veiculo_confirmar = $(this).attr('id');
			app.setAtributo('codVeiculo', $(this).attr('id'));

			if ($(this).hasClass('v-simples')) {
				app.setAtributo('classeVeiculo', 'simples');
			} else if ($(this).hasClass('v-onibus')) {
				app.setAtributo('classeVeiculo', 'onibus');
			} else if ($(this).hasClass('v-carga')) {
				app.setAtributo('classeVeiculo', 'carga');
			}

			app.trocaPagina("confirmar_veiculo.html", controllers.confirmar_veiculo);
		});

	},

}