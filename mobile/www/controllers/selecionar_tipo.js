controllers.selecionar_tipo = {
	config : function() {

		$(".switch_classe_veiculo").click(function() {
			$('#sel_tipo_veiculo_1').toggle();
			$('#sel_tipo_veiculo_2').toggle();
		});

		$(".img_sel_veiculo").on("click", controllers.selecionar_tipo.image_click);

	},
	
	image_click : function(){
		app.imagemPath = $(this).attr('src');
			app.setAtributo('tipo', $(this).attr('id'));
		var limitePessoas = $(this).attr('limitePessoas');
		if ((!util.isEmpty(limitePessoas)) && (!isNaN(limitePessoas))) {
			app.setAtributo('limitePessoas', parseInt(limitePessoas));
		} else {
			app.setAtributo('limitePessoas', 999); // máximo de pessoas indefinido
		}

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
		$(".img_sel_veiculo").off("click",controllers.selecionar_tipo.image_click);
		app.trocaPagina("views/confirmar_veiculo.html", controllers.confirmar_veiculo);
	},

}