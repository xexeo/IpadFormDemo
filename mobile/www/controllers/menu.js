controllers.menu = {
	config : function() {
		$('#display_posto').html(app.posto);
		$('#display_idIpad').html(ipadID.id);
		$('#display_sentido').html(app.sentido);
		$('#display_data_hora').html(util.formatDateTimeToDisplay());
		var updater_dataTimeToDisplay = setInterval(function(){
			$('#display_data_hora').html(util.formatDateTimeToDisplay());
		},10000);
		
        $('#menu_nova_pesquisa').click(function() {
			// clear registro
			app.iniciaRegistro();
			clearInterval(updater_dataTimeToDisplay);
			app.trocaPagina("views/selecionar_tipo.html", controllers.selecionar_tipo);
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

		/*$("#duplica_log").click(function() {
			if (app.filePaths){
				app.copyFile(app.logFileName,
                cordova.file.dataDirectory,
                app.filePaths.externalFolder,
                function(newName){
                    alert('Arquivo de log ' + newName + ' exportado com sucesso.');
                });
			} else {
				alert('Operação não realizada, o sistema de arquivos não foi definido');
			}
			
		});*/

		/*$("#duplica_db").click(function() {
            if(app.filePaths){
				app.database.close(function() {
                app.copyFile(app.dbName, 
                    app.filePaths.dbFolder,
                    app.filePaths.externalFolder,
                    function(newName){
                        alert('Banco de dados ' + newName + ' exportado com sucesso.');
                        app.openDB();
                    });
				}, function(err) {
                app.logger.log(JSON.stringify(err));
				});
			} else {
				alert('Operação não realizada, o sistema de arquivos não foi definido');
			}
        });
		
		$("#menu_logout").click(function () {
			app.logout();
		});*/

	},
};
