var app = {

	debugOnBrowser : true, // sem o cordova
	// debugOnBrowser : false, // com o cordova

	versao : "2.0.0",

	user_master : { // Usuário exclusivo para configurar o idIpad na instalação, ele não faz login no aplicativo.
		usuario : 'Master', // usuário mestre
		senha : "Aaa" // senha mestra
	},

	user_admin : { // Usuário destinado aos testes do aplicativo. TODO: iremos removê-lo ao ir para produção?
		usuario : 'admin', // usuário admin
		senha : "123" // senha admin
	},

	autenticaMaster : function(usuario, senha) {
		if ((usuario == app.user_master.usuario) && (senha == app.user_master.senha)) {
			return true;
		}
		return false;
	},

	autentica : function(usuario, senha) {
		if ((app.user_admin != undefined) && (app.user_admin.usuario != undefined) && (usuario == app.user_admin.usuario)
				&& (senha == app.user_admin.senha)) {
			return true;
		} else {
			// TODO: aqui será realizada a autenticação a partir dos logins cadastrados
		}
		return false;
	},

	login : function() {
		var usuario = $("#usuario").val().trim();
		var senha = $("#senha").val().trim();

		// configura identificador do ipad, para executar uma única vez(durante a instalação)
		if (app.autenticaMaster(usuario, senha)) {
			ipadID.requestID(function(id) {
				$("#ipadID").html(id);
			});
		} else if (app.autentica(usuario, senha)) {
			app.logger.log("Login efetuado pelo usuário: " + usuario);
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
			app.logger.log(msg);
		}
	},

	logout : function() {
		// limpa o registro
		app.limpaRegistro();
		// realiza logout
		$("#usuario").val('').textinput("refresh");
		$("#senha").val('').textinput("refresh");
		$(":mobile-pagecontainer").pagecontainer("change", $("#page_login"));
		app.logger.log('Logout');
	},

	/**
	 * Validates interview's cancellation
	 * 
	 * @param Function
	 *            cb receives a Boolean representing the success in validation
	 */
	validaCancelamento : function(cb) {
		prompt("Insira a senha para cancelar a entrevista.", function(result) {
			// botão prosseguir cancelamento
			if (result == app.senha_login) {
				cb(true);
			} else {
				confirm("Senha incorreta para o cancelamento.\nDeseja tentar novamente?",
				// ok button pressed
				function() {
					app.validaCancelamento(cb);

				}, function() {
					cb(false);
				}, "Senha incorreta.");

			}
		}, "Cancelamento de entrevista", "Cancelar entrevista", "Voltar", "Senha", 'password');
	},

	/*
	 * Application constructor
	 */
	initialize : function() {
		this.bindEvents();
		this.extraConfig();
		this.baseUrl = window.location.href.replace("index.html", "");
		// configuring dialogs
		window.alert = myDialogs.alert;
		window.prompt = myDialogs.prompt;
		window.confirm = myDialogs.confirm;

	},

	/*
	 * bind any events that are required on startup to listeners:
	 */
	bindEvents : function() {
		document.addEventListener('deviceready', app.onDeviceReady);
		console.log('bindindEvents');
	},

	/*
	 * this runs when the device is ready for user interaction:
	 */
	onDeviceReady : function() {
		app.logger.log('device ready');

		// a plataforma Browser não permite o desenvolvimento das escritas em arquivo
		if (device.platform == 'iOS' || device.platform == 'Android') {
			app.filePaths = {}; // inicia a variável

			setTimeout(function() {
				navigator.splashscreen.hide();
				console.log("esperando " + device.platform);
			}, 3000);

			// durante o debug, inicia o sistema de arquivos
			navigator.notification.alert('conecte o debugger', app.onFileSystemReady, 'Alerta de desenvolvimento', 'OK');

		}

		// nessa altura do desenvolvimento não faz sentido
		/*
		 * if (device.platform == 'browser' && device.model != 'Firefox') { alert('ATENÇÃO!!! \n Use o Firefox para fazer a
		 * simulação (cordova run browser --target=firefox)'); }
		 */

		if (device.platform == 'iOS') {
			// configurando a statusBar
			StatusBar.overlaysWebView(false);
			StatusBar.backgroundColorByName("black");
			// black, darkGray, lightGray, white, gray, red, green, blue, cyan, yellow, magenta, orange, purple, brown

			// ajusta os caminhos para o iOS
			app.filePaths['externalFolder'] = cordova.file.documentsDirectory;
			app.filePaths['dbFolder'] = 'cdvfile://localhost/library/LocalDatabase';

		} else if (device.platform == 'Android') {
			StatusBar.hide();
			StatusBar.backgroundColorByName("darkGray");

			// ajusta os caminhos para o Android
			app.filePaths['externalFolder'] = cordova.file.externalDataDirectory;
			app.filePaths['dbFolder'] = cordova.file.applicationStorageDirectory + "databases";
		}

		// alert sem a página como título
		/*
		 * window.alert = function(txt, cb) { navigator.notification.alert(txt, cb, "Aviso", "Fechar"); }
		 */

		// novo valor para variavel criada no extraConfig()
		app.uuid_device = device.uuid;

	},

	onFileSystemReady : function() {

		app.logger.log("folder dos dados: ", cordova.file.dataDirectory);

		// setting ipadID file and value
		window.resolveLocalFileSystemURL(cordova.file.dataDirectory,
		// success resolving dir
		function(dir) {
			app.logger.log('file system ready: ', dir);
			// modifica o atributo ipadId.id que havia sido configurado no extraConfig() como 'browser'
			// reading ipadID file
			dir.getFile(ipadID.ipadIDFileName, {
				create : false
			}, function(file) {
				ipadID.readId(file, function(id) {
					$("#ipadID").html(id);
					afterIpadID(id, dir);
				});
			}, function(e) {
				ipadID.requestID(function(id) {
					$("#ipadID").html(id);
					afterIpadID(id, dir);
				});
			});
		},
		// error resolving dir
		function(err) {
			app.logger.log("erro no sistema de arquivos: " + err.name + " -> " + err.message);
			alert("erro no sistema de arquivos: " + err.name + " -> " + err.message);
		});

		// internal function
		function afterIpadID(id, dir) {

			dir.getFile(app.logFileName, {
				create : true
			},
			// success getting file
			function(file) {
				app.logger.log("arquivo de log: ", file);
				myLogger.setLogFile(file);
				file.createWriter(function(fileWriter) {
					myLogger.setLogWriter(fileWriter);
					app.openDB(); // if successfully create fileWriter, open database
					// novo valor para variável criada no extraConfig
					app.logger = myLogger;
				},
				// error creating fileWriter
				function() {
					app.logger.log('erro criando o escritor do log');
				});
			});
		}
		;

	},

	openDB : function() {
		app.database = sqlitePlugin.openDatabase({
			name : app.dbName,
			iosDatabaseLocation : 'default'
		},
		// sucsess
		function() {
			app.logger.log('Conexão com o banco de dados criada com sucesso.');
			myDb.cretateTblDados();
		},
		// fail
		function(err) {
			app.logger.log('ERRO ao tentar conectar com o banco de dados.');
			app.logger.log(JSON.stringify(err));
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

		// valores iniciais (vão ficar assim se estiver usando o browser)
		app.uuid_device = "browser";
		app.logger = window.console;
		ipadID.id = 'browser';

		// remove o filtro original do autocomplete para poder filtrar acentos
		$.mobile.filterable.prototype.options.filterCallback = function(index, value) {
			return false
		};
	},

	trocaPagina : function(view, controller) {
		if (controller != null) {
			app.onChangeHandler.controller = controller.config;
			$(":mobile-pagecontainer").off("pagecontainershow", app.onChangeHandler.handler).on("pagecontainershow",
					app.onChangeHandler.handler);
		}

		$(":mobile-pagecontainer").pagecontainer("change", app.baseUrl + view);
		app.logger.log(view);
	},

	// para apendar coisas aos controllers
	onChangeHandler : {
		handler : function() {
			try {
				app.onChangeHandler.controller();

				// Botão "Cancelar"
				$("#btn_cancelar").click(app.cancelar);

				// Input radio
				$('input[type="radio"]').click(function() {
					$(this).focus();
				});

				// Input number
				$('input[type="number"]').keyup(function() {
					var regex = new RegExp("[^0-9]+");
					$(this).val($(this).val().replace(regex, ''));
				});

				if (typeof device != 'undefined' && device.platform == "Android") {
					StatusBar.hide();
				}
			} catch (e) {
				app.logger.log(e);
				alert(e.message);
			}
		},

		controller : null,
	},

	getAtributo : function(nome) {
		return registro[nome];
	},

	setAtributo : function(nome, valor) {
		registro[nome] = valor;
		app.logger.log(JSON.stringify(registro));
	},

	cancelar : function() {
		app.validaCancelamento(function(result) {
			if (result) {
				app.cancelaRegistro(function() {
					app.trocaPagina('views/menu.html', controllers.menu);
				});
			}
		});
	},

	limpaRegistro : function() {
		app.logger.log('Limpando registro', true);
		registro = {};
	},

	iniciaRegistro : function() {
		try {
			var now = new Date();
			app.logger.log('Iniciando registro');
			app.limpaRegistro();
			app.setAtributo('id', ipadID.id + String(util.getTimeInSeconds(now)));
			app.setAtributo('login', app.user_login); // TODO idPosto + sentido?
			app.setAtributo('uuid', app.uuid_device);
			app.setAtributo('timestampIniPesq', util.getTimeInSeconds(now));
			app.setAtributo('idIpad', ipadID.id);
			// TODO: falta setar os seguintes atributos:
			// idPosto
			// sentido
			app.logger.log(JSON.stringify(registro));
			app.logger.log('Registro iniciado: ' + registro.id);
		} catch (e) {
			app.logger.log(e.message);
		}
	},

	setCamposDerivados : function() {
		// PLACA
		if ((!util.isEmpty(registro.placaEstrangeira)) && (!registro.placaEstrangeira)) {
			if ((!util.isEmpty(registro.placa_letras)) && (!util.isEmpty(registro.placa_numeros))) {
				app.setAtributo('placa', registro.placa_letras.toUpperCase() + "-" + registro.placa_numeros);
			}
		} else if ((!util.isEmpty(registro.placaEstrangeira)) && registro.placaEstrangeira) {
			if (!util.isEmpty(registro.placa_unica)) {
				app.setAtributo('placa', registro.placa_unica.toUpperCase());
			}
		}
		if (util.isEmpty(registro.placa)) {
			if (registro.cancelado != 1) {
				// TODO: ERRO (placa vazia)
				app.logger.log("ERRO (placa vazia) no registro: ", registro.id);
			}
		}

		// FRENQUENCIA
		if (registro.frequencia_sel == util.getIdxArray('Eventualmente', util.getListaFrequencias())) {
			if ((util.isEmpty(registro.frequencia_num) || (registro.frequencia_num < 1))) {
				app.setAtributo('frequencia_num', 1);
			}
			app.setAtributo('frequencia', 'Eventualmente');
		} else if ((!util.isEmpty(registro.frequencia_num)) && (!util.isEmpty(registro.frequencia_sel))) {
			app.setAtributo('frequencia', registro.frequencia_num + " por "
					+ util.getListaFrequencias()[Number(registro.frequencia_sel) - 1]);
		} else {
			if (registro.cancelado != 1) {
				// TODO: ERRO (frequencia vazia)
				app.logger.log("ERRO (frequencia vazia) no registro: ", registro.id);
			}
		}

		// ORIGEM: MUNICÍPIO E GEOCOD
		var municipioSplit;
		if (!util.isEmpty(registro.origem_municipio)) {
			municipioSplit = registro.origem_municipio.split("|");
			app.setAtributo('idOrigemMunicipio', municipioSplit[1].trim());
			app.setAtributo('geocod_origem', municipioSplit[2].trim());
		}
		if ((util.isEmpty(registro.idOrigemMunicipio) || util.isEmpty(registro.geocod_origem)) && (registro.idOrigemPais == 1)) { // Brasil
			if (registro.cancelado != 1) {
				// TODO: ERRO (destino vazio)
				app.logger.log("ERRO (destino vazio) no registro: ", registro.id);
			}
		}

		// DESTINO: MUNICÍPIO E GEOCOD
		if (!util.isEmpty(registro.destino_municipio)) {
			municipioSplit = registro.destino_municipio.split("|");
			app.setAtributo('idDestinoMunicipio', municipioSplit[1].trim());
			app.setAtributo('geocod_destino', municipioSplit[2].trim());
		}
		if ((util.isEmpty(registro.idDestinoMunicipio) || util.isEmpty(registro.geocod_destino)) && (registro.idDestinoPais == 1)) { // Brasil
			if (registro.cancelado != 1) {
				// TODO: ERRO (destino vazio)
				app.logger.log("ERRO (destino vazio) no registro: ", registro.id);
			}
		}

		// RNTRC
		if ((!util.isEmpty(registro.placaVermelha)) && registro.placaVermelha) {
			if (!util.isEmpty(registro.placa_vermelha_rntrc_sel) && !util.isEmpty(registro.placa_vermelha_rntrc_num)) {
				app.setAtributo('rntrc', String(registro.placa_vermelha_rntrc_sel) + String(registro.placa_vermelha_rntrc_num));
			} else {
				if (registro.cancelado != 1) {
					// TODO: ERRO (rntrc vazio)
					app.logger.log("ERRO (rntrc vazio) no registro: ", registro.id);
				}
			}
		}

		app.setAtributo('timestampFimPesq', util.getTimeInSeconds(new Date()));
	},

	finalizaRegistro : function(cb) {
		app.logger.log('Finalizando registro: ' + registro.id);
		app.setCamposDerivados();
		myDb.insertRegistro(registro,
		// erro
		function(error) {
			app.logger.log('Erro ao inserir registro: ' + error.message);
			// confirma se tenta outra vez
			confirm("Houve uma falha ao inserir o registro.\nDeseja tentar novamente?",
			// button ok
			function() {
				app.finalizaRegistro(cb);
			},
			// button cancel
			function() {
				app.logger.log("Descartando registro: " + registro.id);
				if (cb != null) {
					cb();
				}
			}, "Falha na gravação.", // título
			"Sim", "Não (irá descartar o registro)");
		},
		// ok
		function() {
			app.logger.log('Registro finalizado: ' + registro.id);
			app.limpaRegistro();
			alert("Entrevista registrada.");
			if (cb != null) {
				cb();
			}
		});
	},

	cancelaRegistro : function(cb) {
		app.logger.log('Cancelando registro: ' + registro.id);
		app.setAtributo('cancelado', 1);
		app.setCamposDerivados();
		myDb.insertRegistro(registro, function(error) {
			app.logger.log(error.message);
			// confirma se tenta outra vez
			confirm("Houve uma falha ao registrar o cancelamento.\nDeseja tentar novamente?",
			// button ok
			function() {
				app.cancelaRegistro(cb);
			},
			// button cancel
			function() {
				app.logger.log("Descartando registro: " + registro.id);
				if (cb != null) {
					cb();
				}

			}, "Falha na gravação.", // título
			"Sim", "Não (irá descartar o registro)");
		}, function() {
			app.logger.log('Registro cancelado: ' + registro.id);
			app.limpaRegistro();
			alert("Entrevista cancelada.");
			if (cb != null) {
				cb();
			}
		});
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
			app.logger.log('Removendo arquivo ' + fileName + ' do folder ' + JSON.stringify(folder));
			folder.getFile(fileName, {
				create : false
			}, function(arquivo) {
				arquivo.remove(function() {
					app.logger.log('Arquivo ' + fileName + ' removido');
					if (util.isFunction(cbSuccess)) {
						cbSuccess();
					}
				});
			}, function(err) {
				app.logger.log('Arquivo ' + fileName + ' não existe no folder ' + JSON.stringify(folder));
				if (util.isFunction(cbFail)) {
					cbFail();
				}

			});
		},
		// fail
		function(err) {
			app.logger.log('Erro resolvendo o diretório ' + dir + JSON.stringify(err));
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
		var newName = ipadID.id + "_" + fileName;
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
					app.logger.log('Copiando o arquivo ' + newName + ' do folder ' + dir.nativeURL + ' para o folder '
							+ destDir.nativeURL);
					// removing destination file
					app.removeFile(newName, destDirURI, function() {
						realCopier(file, newName, destDir);
					},
					// mesmo se não conseguir remover
					function() {
						realCopier(file, newName, destDir);
					});
				}, function(err) {
					app.logger.log('Erro acessando o folder de destino ' + destDir + ' ' + JSON.stringify(err));
				});

			}, function(err) {
				app.logger.log('Erro acessando arquivo original ' + fileName + ' ' + JSON.stringify(err));
			});

		}, function(err) {
			app.logger.log('Erro acessando o folder original ' + originDirURI + ' ' + JSON.stringify(err));
		});
		// internal function
		function realCopier(f, newName, d) {
			f.copyTo(d, newName, function() {
				// success
				app.logger.log('Arquivo ' + newName + ' copiado.');
				if (util.isFunction(cb)) {
					cb(newName);
				}
			}, function(err) {
				app.logger.log('Erro copiando arquivo ' + newName + ' ' + JSON.stringify(err));

			});
		}
		;
	},

	baseUrl : null,

	logFileName : "log.txt",

	user_login : null,

	dbName : "dados.db",

	senha_login : null,

	filePaths : null // { externalFolder : null, dbFolder : null, }
	,

}; // end of app

// Registro do momento
var registro;

$(document).ready(function() {
	insert_controllers.insert();
	app.initialize();
});
