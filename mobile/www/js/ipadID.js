/// @file ipadID.js
/// @namespace ipadID
/// Funções para controle da identificação dos ipads
var ipadID = {
	/// @function ipadID.storeID
	/// Grava a identificação do ipad no sistema de arquivos
	/// @param {string} id	identificação
	/// @param {function} cb função _callback_
	/// @return {void} função sem retorno
	storeID : function(id, cb){
		window.resolveLocalFileSystemURL(cordova.file.dataDirectory,
			function(dir){
				app.removeFile(ipadID.ipadIDFileName,
					cordova.file.dataDirectory,
					function(){_realStore(dir);}, //store even if remove old file fail
					function(){_realStore(dir);}
				);
		});
		
		function _realStore(dir){
			dir.getFile(ipadID.ipadIDFileName, {create:true},
				function(file){
					app.logger.log("Creating ipadIDFile " +  file.nativeURL);
					file.createWriter(function(idWriter){
						idWriter.onerror = function(e){
							app.logger.log("Erro escrevendo arquivo " + ipadID.ipadIDFileName);
						}
						var blob = new Blob([ id ], {
							type : 'text/plain'
						});
						try {
							idWriter.seek(idWriter.length);
							idWriter.write(blob);
							app.logger.log("Arquivo " +  ipadID.ipadIDFileName + " criado.");
							if (util.isFunction(cb)){
								cb();
							}
						} catch (e) {
							if (util.isFunction(cb)){
								cb(e);
							}
							app.logger.log(e.message);
						}
					});
			});
		}
	},
	

	/// @function ipadID.readId
	/// Recupera a identificação do ipad
	/// @param {FileEntry} fileEntry	referência para um arquivo
	/// @param {function} success função _callback_ disparada em caso de sucesso
	/// @param {function} fail função _callback_ disparada em caso de falha
	/// @return {void} função sem retorno
	readId : function(fileEntry, success, fail){
		fileEntry.file(function(file){
			var reader = new FileReader();
			reader.readAsText(file);
			reader.onloadend = function(e){
				ipadID.id = reader.result.toString().trim();
				app.logger.log("Lendo ipadID " + ipadID.id);
				if (util.isFunction(success)){
					success(ipadID.id);
				}
			};
			reader.onerror = function(e){
				app.logger.log("Erro lendo arquivo " + ipadID.ipadIDFileName + " ERRO: " + JSON.stringify(e));
				if(util.isFunction(fail)){
					fail();
				}
			};
			
		});
	},
	/// @function ipadID.requestID
	/// Solicita ao usuário a identificação do ipad
	/// @param {function} success função _callback_
	/// @return {void} função sem retorno
	requestID : function(success){
		navigator.notification.prompt("Entre o ID desse ipad",
			function(results){
				if (results.buttonIndex == 1 || !ipadID._isValid(results.input1.trim())){
					navigator.notification.alert(
							"O ipadID informado deve estar no formato 000000 (seis dígitos), sendo 3 deles para o posto e 3 para o número de ordem [posto][ordem]",
							ipadID.requestID(success),
							"Erro entrando ipadID",
							"OK"
						);
				} else if (results.buttonIndex ==2){
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
	
	/// @function ipadID._isValid
	/// Verifica se o identificador informado é válido
	/// @param {string} id identificador
	/// @return {bool} true se o identificador informado for válido
	_isValid : function (id){
		return (id.length === 6 
				&& $.isNumeric(id) 
				&& id.substring(0,3) <= 303
				&& id.substring(0,3) > 0
				&& id.substring(3) > 0
				&& id.substring(3) <= 15)? true : false;
	},
			
	id : null,
	
	ipadIDFileName : "ipID.txt",
	}
;

