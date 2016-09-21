controllers.sumario = {
	config : function() {

		var dataSet = [];
		for (i = 0; i < app.sumario_lista.length; i++) {
			var elem = app.sumario_lista[i];
			dataSet.push([util.formatDateSumario(elem.diaPesq),elem.qtdDia,util.secToFrase(elem.somaDia),
				util.secToFrase(Math.round(elem.mediaDia * 100) / 100),
				util.secToFrase(elem.maxTempoDia),util.secToFrase(elem.minTempoDia)]);
		}

		$('#sumario_tabela').DataTable({
			"data": dataSet,
			"paging": false,
			"info": false,
			"ordering" : false,
			"searching" : false,
			"columns": [
				{ 
					title: "Dia",
					className : "cell_dia"},
				{ 
					title: "Quantidade",
					className : "cell_quantidade"},
				{ 
					title: "Tempo total",
					className : "cell_tempo_total"},
				{ 
					title: "Tempo médio",
					className : "cell_tempo_medio"},
				{ 
					title: "Tempo máximo",
					className : "cell_tempo_maximo"},
				{ 
					title: "Tempo mínimo",
					className : "cell_tempo_minimo"}
			]
		});
		
		if(app.ultima_pesquisa.length == 2) {
			$('#sumario_ult_pesq').text(
					"A última pesquisa válida foi realizada em " + util.formatDateAndTimeSumario(app.ultima_pesquisa[0]) +
					" e durou " + util.secToFrase(app.ultima_pesquisa[1],true));
		}

		// Utilizado para testes
		$('#sumario_test_button').click(function() {
			//app.buscaDuracoesRegistros();
			
			/*
			// var lista = app.getAtributo('sumario_lista');
			for (i = 0; i < lista.length; i++) {
				var elem = lista[i];
				app.logger.log('item ' + i + ':' + elem.dataIniPesq + '; ' + elem.duracaoPesq);
			}
			*/
		});

		$('#sumario_retornar_menu').click(function() {
			app.trocaPagina('views/menu.html', controllers.menu);
		});
	},
};
