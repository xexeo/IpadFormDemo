controllers.menu = {
	config : function() {
        $('#menu_nova_pesquisa').click(function() {
			// clear registro
			app.iniciaRegistro();
			app.trocaPagina("views/selecionar_tipo.html", controllers.selecionar_tipo);
		});

		$("#duplica_log").click(function() {
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
			
		});

		$("#duplica_db").click(function() {
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

	},

};
