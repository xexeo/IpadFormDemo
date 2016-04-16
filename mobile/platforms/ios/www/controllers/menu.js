controllers.menu = {
	config : function() {
        var externalFolder;
        if (device.platform == 'iOS'){
            externalFolder = cordova.file.documentsDirectory;
        }else if(device.platform == 'Android'){
            externalFolder = cordova.file.externalDataDirectory;
        }
        
		$('#menu_nova_pesquisa').click(function() {
			// clear registro
			app.iniciaRegistro();
			app.trocaPagina("views/selecionar_tipo.html", controllers.selecionar_tipo);
		});

		$("#duplica_log").click(function() {
			app.copyFile(app.logFileName,
                cordova.file.dataDirectory,
                externalFolder,
                function(){
                    alert('Arquivo de log ' + app.logFileName + ' exportado com sucesso.');
                });
		});

		$("#duplica_db").click(function() {
            
            app.database.close(function() {
                app.copyFile(app.dbName, 
                    'cdvfile://localhost/library/LocalDatabase',
                    externalFolder,
                    function(){
                        alert('Banco de dados ' + app.dbName + ' exportado com sucesso.');
                        app.openDB();
                    });
            }, function(err) {
                myLogger.write(JSON.stringify(err));
            });
        
        });

	},

};
