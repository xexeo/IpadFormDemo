var ipadID = {
	
	storeID : function(id, cb){
		window.resolveLocalFileSystemURL(cordova.file.dataDirectory,
			function(dir){
				app.removeFile(ipadID.ipadIDFileName,
					cordova.file.dataDirectory,
					function(){_realStore(dir);},
					function(){_realStore(dir);}
				);
		});
		
		function _realStore(dir){
			dir.getFile(ipadID.ipadIDFileName, {create:true},
				function(file){
					myLogger.write("Creating ipadIDFile " +  file.nativeURL);
					file.createWriter(function(idWriter){
						idWriter.onerror = function(e){
							console.log("Erro escrevendo arquivo " + ipadID.ipadIDFileName);
						}
						var blob = new Blob([ id ], {
							type : 'text/plain'
						});
						try {
							idWriter.seek(idWriter.length);
							idWriter.write(blob);
							console.log("Arquivo " +  ipadID.ipadIDFileName + " criado.");
							if (util.isFunction(cb)){
								cb();
							}
						} catch (e) {
							if (util.isFunction(cb)){
								cb(e);
							}
							console.log(e.message);
						}
					});
			});
		}
	},
	

	/**
	 * Read ipadIDFile
	 * @param Function success callback
	 * @param Function fail callback
	 */
	readId : function(fileEntry, success, fail){
		fileEntry.file(function(file){
			var reader = new FileReader();
			reader.readAsText(file);
			reader.onloadend = function(e){
				ipadID.id = reader.result.toString().trim();
				console.log("Lendo ipadID " + ipadID.id);
				if (util.isFunction(success)){
					success(ipadID.id);
				}
			};
			reader.onerror = function(e){
				console.log("Erro lendo arquivo " + ipadID.ipadIDFileName + " ERRO: " + JSON.stringify(e));
				if(util.isFunction(fail)){
					fail();
				}
			};
			
		});
	},
	
	requestID : function(success){
		navigator.notification.prompt("Entre o ID desse ipad",
			function(results){
				if (results.buttonIndex == 2){
					ipadID.storeID(results.input1.trim(), 
						function(e){
							if (e == null){
								ipadID.id = results.input1.trim();
								alert("Id " +  ipadID.id +  " gravado com sucesso");
								if(util.isFunction(success)){
									success(ipadID.id);
								}
							} else {
								alert(e.message);
							}
					});
				}
			},
			"Identificação do iPad",
			["Cancelar", "Cadastrar"]
		);
	},
		
	id : null,
	
	ipadIDFileName : "ipID.txt",
};

