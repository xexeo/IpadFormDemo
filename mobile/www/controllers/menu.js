/* global util, datas, app, ipadID */
/// @file menu.js
/// @namespace controllers.menu
/// Funções da tela de menu
controllers.menu = {
	/// @function controllers.menu.config
	/// Controla o comportamento da tela de menu
	/// @return {void} função sem retorno
	config : function() {
		$('#display_posto').html(app.posto);
		$('#display_idIpad').html(ipadID.id);
		$('#display_sentido').html(app.sentido);
		$('#display_data_hora').html(util.formatDateTimeToDisplay());
		var updater_dataTimeToDisplay = setInterval(function(){
			$('#display_data_hora').html(util.formatDateTimeToDisplay());
		},10000);
		
		if (app.isTreinamento){
				$('#banner_supra').html("<br>O sistema está em modo de treinamento").addClass('avisoTreinamento');
			}else{
				$('#banner_supra').html("").removeClass('avisoTreinamento');
		}
		
        $('#menu_nova_pesquisa').click(function() {
			clearInterval(updater_dataTimeToDisplay);
			
			if (datas.verificaData() && app.isTreinamento) {
				alert("Login de treinamento não podem ser \nutilizado em dias de produção de pesquisas reais.\nA aplicação será reiniciada",
				"Controle de acesso", app.restart,	'error');
			} else if (!datas.verificaData() && !app.isTreinamento){
				alert("Logins de produção não podem ser \nutilizados fora das datas de produção de pesquisas reais.\nA aplicação será reiniciada",
				"Controle de acesso", app.restart, 'error');
			} else {
				// clear registro
				app.iniciaRegistro();
				app.trocaPagina("views/selecionar_tipo.html", controllers.selecionar_tipo);
			}
		});

        $('#menu_sumario').click(function() {
			$.mobile.loading("show");
			app.buscaDuracoesRegistros(function(){
				app.buscaUltimaPesquisa(function(){
					app.buscaRegistrosCancelados(function(){
						$.mobile.loading("hide");
						clearInterval(updater_dataTimeToDisplay);
						app.trocaPagina("views/sumario.html", controllers.sumario);
					});
				});
			});
			
		});
		
		$("#menu_trocar_sentido").click(function(){
			var novo_sentido;
			if (app.sentido == 'AB'){
				novo_sentido = 'BA'
			}else{
				novo_sentido = 'AB'
			}
			app.validaOperacoes(function(){
					app.sentido = novo_sentido;
					app.trocaPagina("views/menu.html", controllers.menu);
				}, 
				"Insira a senha para alterar o sentido para <span style='font-weight: bolder;'>" + novo_sentido + "</span>.",
				"Alterar Sentido",
				"Senha incorreta.\nDeseja tentar novamente?",
				"Senha Incorreta", 
				"Alterar Sentido",
				"Voltar"
			);
		});

		$('#testaBugOld').click(function(){
			for (i = 0; i < 51; i++){
				app.trocaPagina("views/selecionar_tipo.html", controllers.selecionar_tipo, 'old');
				app.trocaPagina("views/menu.html", controllers.menu, 'old');
			}
		});

		$('#testaBugNew').click(function(){
			for (i = 0; i < 51; i++){
				app.trocaPagina("views/selecionar_tipo.html", controllers.selecionar_tipo);
				app.trocaPagina("views/menu.html", controllers.menu);
			}
		});

	},
};
