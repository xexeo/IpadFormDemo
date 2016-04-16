var app = {

	debugOnBrowser : true, // sem o cordova
	// debugOnBrowser : false, // com o cordova

	versao : "2.0.0",

	user_admin : {
		usuario : 'admin', // usuario mestre
		senha : "123" // senha mestra
	},

	autentica : function(usuario, senha) {
		if ((usuario == app.user_admin.usuario) && (senha == app.user_admin.senha)) {
			return true;
		} else {
			// TODO: recuperar tb dos logins cadastrados
		}
		return false;
	},

	login : function() {
		var usuario = $("#usuario").val().trim();
		var senha = $("#senha").val().trim();
		if (app.autentica(usuario, senha)) {
			myLogger.write("Login efetuado pelo usuário: " + usuario);
			// navega para págine e executa o script de configuração depois do carregamento
			app.trocaPagina("views/menu.html", controllers.menu)
			// set user_login
			app.user_login = usuario;
			// set senha_login
			app.senha_login = senha;
			// limpa o registro
			app.limpaRegistro();
		} else {
			// TODO: Trocar por um popup "mais elegante"
			var msg = "usuário e senha informados não estão cadastrados no sistema";
			alert(msg);
			myLogger.write(msg);
		}
	},

	logout : function() {
		// limpa o registro
		app.limpaRegistro();
		// realiza logout
		$("#usuario").val('').textinput("refresh");
		$("#senha").val('').textinput("refresh");
		$(":mobile-pagecontainer").pagecontainer("change", $("#page_login"));
		myLogger.write('Logout');
	},

	/**
	 * Validates interview's cancellation
	 * 
	 * @param Function
	 *            cb receives a Boolean representing the success in validation
	 */
	validaCancelamento : function(cb) {
		navigator.notification.prompt("Insira a senha para cancelar a entrevista.", function(results) {
			// botão cancelar
			if (results.buttonIndex == 1) {
				if (!util.isEmpty(results.input1) && results.input1 == app.senha_login) {
					cb(true);
				} else {
					navigator.notification.confirm("Senha incorreta para o cancelamento.\nDeseja tentar novamente?", function(
							results) {
						// button ok
						if (results == 1) {
							app.validaCancelamento(cb);
						}
					}, "Senha incorreta.");
				}
			} else {
				cb(false);
			}
		}, "Cancelamento de entrevista", [ "Cancelar entrevista", "Continuar entrevista" ]);
	},

	/*
	 * Application constructor
	 */
	initialize : function() {
		this.bindEvents();
		this.extraConfig();
		this.baseUrl = window.location.href.replace("index.html", "");

	},

	/*
	 * bind any events that are required on startup to listeners:
	 */
	bindEvents : function() {
		document.addEventListener('deviceready', app.onDeviceReady);
		// tentativa para chrome e opera
		// document.addEventListener('filePluginIsReady', app.onFileSystemReady, false);

		console.log('bindindEvents');
	},

	/*
	 * this runs when the device is ready for user interaction:
	 */
	onDeviceReady : function() {
		console.log('device ready');
		if (device.platform == 'iOS' || device.platform == 'Android' || (device.platform == 'browser' && device.model == 'Firefox')) {
			// alert('tô no ' + device.platform);
			setTimeout(function() {
				navigator.splashscreen.hide();
				console.log("esperando " + device.platform);
			}, 3000);
			// fordebug
			navigator.notification.alert('conecte o debugger', app.onFileSystemReady, 'Alerta de desenvolvimento', 'OK');
			if (device.platform != 'browser') {
				app.debugOnBrowser = false;
			}
			// app.onFileSystemReady();
		}

		if (device.platform == 'browser' && device.model != 'Firefox') {
			alert('ATENÇÃO!!! \n Use o Firefox para fazer a simulação (cordova run browser --target=firefox)');
		}

		if (device.platform == 'iOS'){
            // configurando a statusBar
            StatusBar.overlaysWebView(false);
            StatusBar.backgroundColorByName("black"); // black, darkGray, lightGray, white, gray, red, green, blue, cyan, yellow, magenta, orange, purple, brown
		} else if (device.platform == 'Android'){
            StatusBar.hide();
        }
        
        
		// alert sem a página como título
		window.alert = function(txt, cb) {
			navigator.notification.alert(txt, cb, "Aviso", "Fechar");
		}

	},

	onFileSystemReady : function() {
		console.log("folder dos dados: ", cordova.file.dataDirectory);

		// setting logger writer
		window.resolveLocalFileSystemURL(cordova.file.dataDirectory, function(dir) {
			console.log('file system ready: ', dir);
			dir.getFile(app.logFileName, {
				create : true
			}, function(file) {
				console.log("arquivo de log: ", file);
				myLogger.setLogFile(file);
				file.createWriter(function(fileWriter) {
					myLogger.setLogWriter(fileWriter);
				}, function() {
					console.log('erro criando o escritor do log');
				});
				app.openDB();

			});
		}, function(err) {
			console.log("erro no sistema de arquivos: " + err.name + " -> " + err.message);
			alert("erro no sistema de arquivos: " + err.name + " -> " + err.message);
		});

	},

	openDB : function() {
        app.database = sqlitePlugin.openDatabase({
            name : app.dbName,
            iosDatabaseLocation : 'default'
        },
        // sucsess
        function() {
            myLogger.write('Conexão com o banco de dados criada com sucesso.');
            myDb.cretateTblDados();
        },
        // fail
        function(err) {
            myLogger.write('ERRO ao tentar conectar com o banco de dados.');
            myLogger.write(JSON.stringify(err));
        });
	},

	extraConfig : function() {
		// initialize panel
		$(function() {
			$("[data-role=panel]").panel().enhanceWithin();
		});
		$("#versao").html(this.versao);
		$("#entrar").click(this.login);
		$("#btn_sair").click(this.logout);
	},

	trocaPagina : function(view, controller) {
		if (controller != null) {
			$(":mobile-pagecontainer").off("pagecontainershow", controller.config).on("pagecontainershow", controller.config);
		}

		$(":mobile-pagecontainer").pagecontainer("change", app.baseUrl + view);
		myLogger.write(view);
	},

	setAtributo : function(nome, valor) {
		registro[nome] = valor;
		try {
			myLogger.write(JSON.stringify(registro));
		} catch (e) {
			myLogger.write(e.message);
		}

	},

	cancelar : function() {
		app.validaCancelamento(function(result) {
			if (result) {
				app.cancelaRegistro(function(){
                    app.trocaPagina('views/menu.html', controllers.menu);
                });
			}
		});
	},

	limpaRegistro : function() {
		myLogger.write('Limpando registro', true);
		registro = {};
	},

	iniciaRegistro : function() {
		try {
			var now = new Date();
			myLogger.write('Iniciando registro');
			app.limpaRegistro();
			var uuid_device = "browser";
			if (!app.debugOnBrowser) {
				uuid_device = device.uuid;
			}
			app.setAtributo('id', uuid_device + String(util.getTimeInSeconds(now)));
			app.setAtributo('login', app.user_login); // TODO idPosto + sentido?
			app.setAtributo('uuid', uuid_device);
			app.setAtributo('timestampIniPesq', util.getTimeInSeconds(now));
			// TODO: falta setar os seguintes atributos:
			// idPosto
			// sentido
			// idIpad
			myLogger.write(JSON.stringify(registro));
			myLogger.write('Registro iniciado: ' + registro.id);
		} catch (e) {
			myLogger.write(e.message);
		}
	},

	setCamposDerivados : function() {
		if (!util.isEmpty(registro.placa_letras) && !util.isEmpty(registro.placa_numeros)) {
			app.setAtributo('placa', registro.placa_letras + "-" + registro.placa_numeros);
		} else if (!util.isEmpty(registro.placa_letras)) {
			app.setAtributo('placa', registro.placa_letras);
		} else if (!util.isEmpty(registro.placa_numeros)) {
			app.setAtributo('placa', registro.placa_numeros);
		}

		if (!util.isEmpty(registro.frequencia_num) || !util.isEmpty(registro.frequencia_sel)) {
			app.setAtributo('frequencia', registro.frequencia_num + " por " + registro.frequencia_sel);
		}

		var municipioSplit;
		if (registro.origem_municipio != null) {
			municipioSplit = registro.origem_municipio.split("|");
			app.setAtributo('idOrigemMunicipio', municipioSplit[0]);
			app.setAtributo('geocod_origem', municipioSplit[1]);
		}

		if (registro.destino_municipio != null) {
			municipioSplit = registro.destino_municipio.split("|");
			app.setAtributo('idDestinoMunicipio', municipioSplit[0]);
			app.setAtributo('geocod_destino', municipioSplit[1]);
		}

		app.setAtributo('timestampIniPesq', new Date());
	},

	finalizaRegistro : function() {
        myLogger.write('Finalizando registro: ' + registro.id);
        app.setCamposDerivados();
        myDb.insertRegistro(registro,
            //erro
            function(error){
                myLogger.write('Erro inserindo registro: ' + error.message);
                //confirma se tenta outra vez
                navigator.notification.confirm("Houve uma falha ao inserir o registro.\nDeseja tentar novamente?", 
                    function(results) {
						// button Sim
						if (results == 1) {
							app.finalizaRegistro(); 
						}
					},
                    "Falha na gravação.", //título
                    ["Sim", "Não"] //botões -> o índice do botão escolhido, começando em 1, (não fui eu que quiz assim) volta no results
                );
            },
            //ok
            function(){
                myLogger.write('Registro finalizado: ' + registro.id);
				app.limpaRegistro();
				alert("Entrevista registrada.");
        });
        
        
		/*
         * aqui eu vou reescrever a função aqui em cima mantendo a lógica mas trocando o paradigma
         * 
         * var tentarNovamente;
		var saved;
		do {
			tentarNovamente = false;
			saved = false;
			try {
				myLogger.write('Finalizando registro: ' + registro.id);
				app.setCamposDerivados();
				saved = myDb.insertRegistro(registro);
			} catch (e) {
				myLogger.write(e.message);
			}
			if (saved) {
				myLogger.write('Registro finalizado: ' + registro.id);
				app.limpaRegistro();
				alert("Entrevista registrada.");
			} else {
				// TITLE: "Falha ao gravar informações."
				var tentarNovamente = confirm("Houve uma falha ao finalizar a entrevista.\nDeseja tentar novamente?");
			}
		} while (tentarNovamente);*/
	},

	cancelaRegistro : function(cb) {
        myLogger.write('Cancelando registro: ' + registro.id);
        app.setCamposDerivados();
        app.setAtributo('cancelado', 1);
        myDb.insertRegistro(registro,
        function(error){
            myLogger.write(error.message);
            //confirma se tenta outra vez
            navigator.notification.confirm("Houve uma falha ao registrar o cancelamento.\nDeseja tentar novamente?", 
                function(results) {
                    // button Sim
                    if (results == 1) {
                        app.finalizaRegistro(); 
                    }
                },
                "Falha na gravação.", //título
                ["Sim", "Não"] //botões -> o índice do botão escolhido, começando em 1, (não fui eu que quiz assim) volta no results
            );
        }, function(){
            myLogger.write('Registro cancelado: ' + registro.id);
            app.limpaRegistro();
            alert("Entrevista cancelada.");
            if (cb != null){
                cb();
            }
        
        });
        
		/*var tentarNovamente;
		var saved;
		do {
			tentarNovamente = false;
			saved = false;
			try {
				myLogger.write('Cancelando registro: ' + registro.id);
				app.setCamposDerivados();
				app.setAtributo('cancelado', 1);
				saved = myDb.insertRegistro(registro);
			} catch (e) {
				myLogger.write(e.message);
			}
			if (saved) {
				myLogger.write('Registro cancelado: ' + registro.id);
				app.limpaRegistro();
				alert("Entrevista cancelada.");
			} else {
				// TITLE: "Falha ao gravar informações."
				var tentarNovamente = confirm("Houve uma falha o registro.\nDeseja tentar novamente?");
			}
		} while (tentarNovamente);*/
	},

	/**
	 * Remove a file from fs, if it exists
	 * 
	 * @param String
	 *            fileName
	 * @param String
	 *            dir -> prefers cordova.file.{dir}
	 * @param function
	 *            cbSuccess success callback
	 * @param function
	 *            cbFail error callback
	 */
	removeFile : function(fileName, dirURI, cbSuccess, cbFail) {
		window.resolveLocalFileSystemURL(dirURI,
		// success
		function(folder) {
			myLogger.write('Removendo arquivo ' + fileName + ' do folder ' + JSON.stringify(folder));
			folder.getFile(fileName, {
				create : false
			}, function(arquivo) {
				arquivo.remove(function() {
					myLogger.write('Arquivo ' + fileName + ' removido');
					if(util.isFunction(cbSuccess)){
                        cbSuccess();
                    }
				});
			}, function(err) {
				myLogger.write('Arquivo ' + fileName + ' não existe no folder ' + JSON.stringify(folder));
				if(util.isFunction(cbFail)){
                    cbFail();
                }
            
			});
		},
		// fail
		function(err) {
			myLogger.write('Erro resolvendo o diretório ' + dir + JSON.stringify(err));
		});
	},

	/**
	 * Copy a file overwriting the destination file, if it exists
	 * 
	 * @param String
	 *            fileName
	 * @param String
	 *            originDir
	 * @param String
	 *            destDir
	 * @param Function
	 *            cb success callback function
	 */
	copyFile : function(fileName, originDirURI, destDirURI, cb) {
		// get original dir
		resolveLocalFileSystemURL(originDirURI,
		// success
		function(dir) {
			// get original file
			dir.getFile(fileName, {},
			// success getting original file
			function(file) {
				// resolving destination
				resolveLocalFileSystemURL(destDirURI,
				// success resolving destination
				function(destDir) {
					myLogger.write('Copiando o arquivo ' + fileName + ' do folder ' + dir.nativeURL + ' para o folder '
							+ destDir.nativeURL);
					// removing destination file
					app.removeFile(fileName, destDirURI, function() {
						realCopier(file, fileName, destDir);
					},
					// mesmo se não conseguir remover
					function() {
						realCopier(file, fileName, destDir);
					});
				}, function(err) {
					myLogger.write('Erro acessando o folder de destino ' + destDir + ' ' + JSON.stringify(err));
				});

			}, function(err) {
				myLogger.write('Erro acessando arquivo original ' + fileName + ' ' + JSON.stringify(err));
			});

		}, function(err) {
			myLogger.write('Erro acessando o folder original ' + originDirURI + ' ' + JSON.stringify(err));
		});
		// internal function
		function realCopier(f, name, d) {
			f.copyTo(d, name, function() {
				// success
				myLogger.write('Arquivo ' + name + ' copiado.');
                if (util.isFunction(cb)) {
					cb();
				}
			}, function(err) {
				myLogger.write('Erro copiando arquivo ' + name + ' ' + JSON.stringify(err));
                
			});
		}
		;
	},

	baseUrl : null,

	logFileName : "log.txt", // TODO: uuid no nome do arquivo

	user_login : null,

	dbName : "dados.db", // TODO: uuid no nome do arquivo

	senha_login : null

}; // end of app

// Registro do momento
var registro;

$(document).ready(function() {
	insert_controllers.insert();
	app.initialize();
});
