controllers.menu = {
	config : function() {
        var _externalFolder;
        var _dbFolder;
        
        if (device.platform == 'iOS'){
            _externalFolder = cordova.file.documentsDirectory;
            _dbFolder = 'cdvfile://localhost/library/LocalDatabase';
        }else if(device.platform == 'Android'){
            _externalFolder = cordova.file.externalDataDirectory;
            _dbFolder = cordova.file.applicationStorageDirectory + "databases";
        }
        
		$('#menu_nova_pesquisa').click(function() {
			// clear registro
			app.iniciaRegistro();
			app.trocaPagina("views/selecionar_tipo.html", controllers.selecionar_tipo);
		});

		$("#duplica_log").click(function() {
			app.copyFile(app.logFileName,
                cordova.file.dataDirectory,
                _externalFolder,
                function(newName){
                    alert('Arquivo de log ' + newName + ' exportado com sucesso.');
                });
		});

		$("#duplica_db").click(function() {
            
            app.database.close(function() {
                app.copyFile(app.dbName, 
                    _dbFolder,
                    _externalFolder,
                    function(newName){
                        alert('Banco de dados ' + newName + ' exportado com sucesso.');
                        app.openDB();
                    });
            }, function(err) {
                myLogger.write(JSON.stringify(err));
            });
        
        });

	},

};
