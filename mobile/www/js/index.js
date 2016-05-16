var app = {

	debugOnBrowser : true, // sem o cordova
	// debugOnBrowser : false, // com o cordova

	versao : "2.0.0",

	login : function() {
		var usuario = $("#usuario").val().trim();
		var senha = $("#senha").val().trim();

		// configura identificador do ipad, para executar uma única vez(durante a instalação)
		if (logins.autenticaMaster(usuario, senha)) {
			ipadID.requestID(function(id) {
				$("#ipadID").html(id);
			});
		} else if (logins.autentica(usuario, senha)) {
			app.logger.log("Login efetuado pelo usuário: " + usuario);
			// navega para página e executa o script de configuração depois do carregamento
			app.trocaPagina("views/menu.html", controllers.menu)
			// set: user_login, senha_login, posto e sentido
			app.user_login = usuario;
			app.senha_login = senha;
			app.posto = String(usuario).substr(0, 3);
			app.sentido = String(usuario).substr(3, 2).toUpperCase();
			if (isNaN(app.posto)) { // apenas para efeitos ao usuário de testes
				app.posto = '000';
			}
			if ((app.sentido != 'AB') && (app.sentido != 'BA')) { // apenas para efeitos ao usuário de testes
				app.sentido = 'AB';
			}
			// limpa o registro
			app.limpaRegistro();
		} else {
			// TODO: Trocar por um popup "mais elegante"
			var msg = "Usuário e/ou Senha informados não estão cadastrados no sistema!";
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
		app.user_login = null;
		app.senha_login = null;
		app.posto = null;
		app.sentido = null;
	},

	exportaDbToJson : function() {
		if (app.filePaths) {
			resolveLocalFileSystemURL(app.filePaths.externalFolder, function(dir) {
				var newJsonName = ipadID.id + "_" + app.jsonName;
				app.removeFile(newJsonName, app.filePaths.externalFolder, function() {
					realExporter(newJsonName, dir);
				}, function() {
					realExporter(newJsonName, dir);
				});
			}, function(err) {
				app.logger.log('ERRO ao acessar o folder externo ' + app.filePaths.externalFolder + ' ' + JSON.stringify(err));
			});
		} else {
			alert('Operação não realizada, o sistema de arquivos não foi definido');
		}

		function realExporter(jsonName, dir) {
			dir.getFile(jsonName, {
				create : true
			}, function(file) {
				app.logger.log("arquivo JSON: ", file);
				jsonWriter.setJsonFile(file);
				file.createWriter(function(fileWriter) {
					jsonWriter.setJsonWriter(fileWriter);
					myDb.exportaDbToJson(jsonWriter);
				}, function() {
					app.logger.log('ERRO criando o escritor do JSON a ser exportado.');
				});
			}, function(e) {
				app.logger.log('ERRO ao criar o arquivo JSON a ser exportado: ' + e.message);
			});
		}
	},

	duplicaDb : function() {
		if (app.filePaths) {
			app.database.close(function() {
				app.copyFile(app.dbName, app.filePaths.dbFolder, app.filePaths.externalFolder, function(newName) {
					alert('Banco de dados ' + newName + ' exportado com sucesso.');
					app.openDB();
				});
			}, function(err) {
				app.logger.log(JSON.stringify(err));
			});
		} else {
			alert('Operação não realizada, o sistema de arquivos não foi definido');
		}
	},

	duplicaLog : function() {
		if (app.filePaths) {
			app.copyFile(app.logFileName, cordova.file.dataDirectory, app.filePaths.externalFolder, function(newName) {
				alert('Arquivo de log ' + newName + ' exportado com sucesso.');
			});
		} else {
			alert('Operação não realizada, o sistema de arquivos não foi definido');
		}
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

	validaOperacoes : function(operacao, txtPrompt, titlePrompt, txtConfirm, titleConfirm, btnOKPrompt, btnCancelPrompt) {
		prompt(txtPrompt, function(result) {
			if (result == app.senha_login) {
				operacao();
			} else {
				confirm(txtConfirm,
						function() {
							app.validaOperacoes(operacao, txtPrompt, titlePrompt, txtConfirm, titleConfirm, btnOKPrompt,
									btnCancelPrompt); // try again
						}, null, titleConfirm);
			}
		}, titlePrompt, btnOKPrompt, btnCancelPrompt, "Senha", 'password');
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
			// navigator.notification.alert('conecte o debugger', app.onFileSystemReady, 'Alerta de desenvolvimento', 'OK');

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
		// $("#btn_sair").click(this.logout);

		// valores iniciais (vão ficar assim se estiver usando o browser)
		app.uuid_device = "browser";
		app.logger = window.console;
		ipadID.id = 'browser';

		// botões do menu
		$("#btn_sair").click(
				function() {
					app.validaOperacoes(app.logout, "Insira a senha para realizar o logout.", "Logout",
							"Senha incorreta.\nDeseja tentar novamente?", "Senha Incorreta", "Logout", "Voltar");
				});

		$("#duplica_log").click(
				function() {
					app.validaOperacoes(app.duplicaLog, "Insira a senha para exportar o Log de operações.",
							"Exportar log de operações", "Senha incorreta para a exportação.\nDeseja tentar novamente?",
							"Senha Incorreta", "Exportar", "Voltar");
				});

		$("#duplica_db").click(
				function() {
					app.validaOperacoes(app.duplicaDb, "Insira a senha para exportar o banco de dados.",
							"Exportar banco de dados", "Senha incorreta para a exportação.\nDeseja tentar novamente?",
							"Senha Incorreta", "Exportar", "Voltar");
				});

		$("#exporta_db_to_json").click(
				function() {
					app.validaOperacoes(app.exportaDbToJson, "Insira a senha para exportar os dados no formato JSON.",
							"Exportar JSON", "Senha incorreta para a exportação.\nDeseja tentar novamente?", "Senha Incorreta",
							"Exportar", "Voltar");
				});

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

				// Input money
				$('input[typeMask="money-BRL"]').each(function(key, input) {
					$(input).maskMoney({
						prefix : "R$ ",
						affixesStay : "true",
						thousands : ".",
						decimal : ","
					});
				});

				$('input[mask]').each(function(key, input) {
					var decimalSeparator = $(input).attr('mask-decimal-separator');
					if (util.isEmpty(decimalSeparator)) {
						$(input).inputmask($(input).attr('mask'), {
							'autoUnmask' : true
						});
					} else {
						$(input).inputmask($(input).attr('mask'), {
							'autoUnmask' : true,
							numericInput : true,
							radixPoint : decimalSeparator
						});
					}
				});

				$(".versao #versao").html(app.versao);
				$(".ipad #ipadID").html(ipadID.id);
				$("#posto").html(app.posto);
				$("#sentido").html(app.sentido);

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

	splitAtributo : function(nome) {
		var valor = registro[nome];
		if (!util.isEmpty(valor)) {
			var splited = String(valor).split("|");
			if (splited.length > 1) {
				valor = splited[splited.length - 1].trim();
				if (!util.isEmpty(valor)) {
					if (isNaN(valor)) {
						app.setAtributo(nome, valor);
					} else {
						app.setAtributo(nome, parseInt(valor));
					}
				}
			}
		}
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
			app.setAtributo('id', ipadID.id + util.getTimeUnixTimestamp(now));
			app.setAtributo('login', app.user_login);
			app.setAtributo('idPosto', app.posto);
			app.setAtributo('sentido', app.sentido);
			app.setAtributo('uuid', app.uuid_device);
			app.setAtributo('dataIniPesq', util.getTimeDefaultFormated(now));
			app.setAtributo('idIpad', ipadID.id);
			app.logger.log(JSON.stringify(registro));
			app.logger.log('Registro iniciado: ' + registro.id);
		} catch (e) {
			app.logger.log(e.message);
		}
	},

	setCamposDerivados : function() {

		// TIPO VEICULO
		if (!util.isEmpty(app.getAtributo('tipo'))) {
			var tipoReal = app.getAtributo('tipo').split("_")[0];
			app.setAtributo('tipo', tipoReal.toUpperCase());
		} else if (registro.cancelado != 1) {
			app.setAtributo('erro', "ERRO (tipo vazio)");
			app.logger.log(registro.erro + " no registro: ", registro.id);
		}

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
				app.setAtributo('erro', "ERRO (placa vazio)");
				app.logger.log(registro.erro + " no registro: ", registro.id);
			}
		}

		// FRENQUENCIA
		if (!util.isEmpty(registro.frequenciaPeriodo)) {
			app.setAtributo('frequenciaPeriodo', util.getListaFrequencias()[Number(registro.frequenciaPeriodo) - 1]);
			if (registro.frequenciaPeriodo == 'Eventualmente') {
				app.setAtributo('frequenciaQtd', 1);
			} else if (util.isEmpty(registro.frequenciaQtd) || (Number(registro.frequenciaQtd) <= 0)) {
				if (registro.cancelado != 1) {
					app.setAtributo('erro', "ERRO (frequenciaQtd vazio)");
					app.logger.log(registro.erro + " no registro: ", registro.id);
				}
			}
		} else if (registro.cancelado != 1) {
			app.setAtributo('erro', "ERRO (frequenciaPeriodo vazio)");
			app.logger.log(registro.erro + " no registro: ", registro.id);
		}

		// ORIGEM: MUNICÍPIO
		app.splitAtributo('idOrigemMunicipio');
		if (util.isEmpty(registro.idOrigemMunicipio) && (registro.idOrigemPais == 1)) { // Brasil
			if (registro.cancelado != 1) {
				app.setAtributo('erro', "ERRO (idOrigemMunicipio vazio)");
				app.logger.log(registro.erro + " no registro: ", registro.id);
			}
		}

		// DESTINO: MUNICÍPIO
		app.splitAtributo('idDestinoMunicipio');
		if (util.isEmpty(registro.idDestinoMunicipio) && (registro.idDestinoPais == 1)) { // Brasil
			if (registro.cancelado != 1) {
				app.setAtributo('erro', "ERRO (idDestinoMunicipio vazio)");
				app.logger.log(registro.erro + " no registro: ", registro.id);
			}
		}

		// ESPECÍFICOS PARA FLUXO DE CARGA
		if (app.getAtributo('classeVeiculo') == 'carga') {

			// RNTRC
			if ((!util.isEmpty(registro.placaVermelha)) && registro.placaVermelha) {
				if (!util.isEmpty(registro.placa_vermelha_rntrc_sel) && !util.isEmpty(registro.placa_vermelha_rntrc_num)) {
					app.setAtributo('rntrc', String(registro.placa_vermelha_rntrc_sel)
							+ String(registro.placa_vermelha_rntrc_num));
				} else {
					if (registro.cancelado != 1) {
						app.setAtributo('erro', "ERRO (rntrc vazio)");
						app.logger.log(registro.erro + " no registro: ", registro.id);
					}
				}
			}

			// PESO DA CARGA ('ton' -> 'kg')
			if (!util.isEmpty(registro.pesoDaCarga) && ((typeof registro.pesoDaCarga) != 'number')) {
				var peso = Number(registro.pesoDaCarga);
				if (registro.unidadePesoDaCarga == 'kg') {
					peso = peso / 10;
				} else {
					peso = peso * 100;
				}
				app.setAtributo('pesoDaCarga', peso);
			}
			if (util.isEmpty(registro.pesoDaCarga) && registro.possui_carga && (registro.cancelado != 1)) {
				app.setAtributo('erro', "ERRO (pesoDaCarga vazio)");
				app.logger.log(registro.erro + " no registro: ", registro.id);
			}

			// TIPO DE PRODUTO (CARGA)
			app.splitAtributo('idProduto');
			if (util.isEmpty(registro.idProduto) && registro.possui_carga && (registro.cancelado != 1)) {
				app.setAtributo('erro', "ERRO (idProduto vazio)");
				app.logger.log(registro.erro + " no registro: ", registro.id);
			}

			// CARGA ANTERIOR
			app.splitAtributo('idCargaAnterior');
			if (util.isEmpty(registro.idCargaAnterioro) && registro.carga_anterior && (registro.cancelado != 1)) {
				app.setAtributo('erro', "ERRO (idCargaAnterior vazio)");
				app.logger.log(registro.erro + " no registro: ", registro.id);
			}

			// MUNICÍPIO EMBARQUE DA CARGA
			app.splitAtributo('municipioEmbarqueCarga');
			if (util.isEmpty(registro.municipioEmbarqueCarga) && registro.sabe_embarque && (registro.cancelado != 1)) {
				app.setAtributo('erro', "ERRO (municipioEmbarqueCarga vazio)");
				app.logger.log(registro.erro + " no registro: ", registro.id);
			}

			// MUNICÍPIO DESEMBARQUE DA CARGA
			app.splitAtributo('municipioDesembarqueCarga');
			if (util.isEmpty(registro.municipioDesembarqueCarga) && registro.sabe_desembarque && (registro.cancelado != 1)) {
				app.setAtributo('erro', "ERRO (municipioDesembarqueCarga vazio)");
				app.logger.log(registro.erro + " no registro: ", registro.id);
			}

			// CARGA SUGESTÃO PARADA OBRIGATÓRIA MUNICÍPIOS
			app.splitAtributo('paradaObrigatoriaMunicipio1');
			app.splitAtributo('paradaObrigatoriaMunicipio2');

			if (util.isEmpty(registro.paradaObrigatoriaMunicipio1) && util.isEmpty(registro.paradaObrigatoriaMunicipio1)) {
				if ((!registro.municipiosParadaNaoSabe) && (registro.cancelado != 1)) {
					app.setAtributo('erro', "ERRO (paradaObrigatoriaMunicipio1 ou paradaObrigatoriaMunicipio2 vazio)");
					app.logger.log(registro.erro + " no registro: ", registro.id);
				}
			}
		}

		app.setAtributo('dataFimPesq', util.getTimeDefaultFormated(new Date()));
	},

	finalizaRegistro : function(cb) {
		app.logger.log('Finalizando registro: ' + registro.id);
		app.setAtributo('cancelado', 0);
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

	logFileName : "logOD.txt",

	dbName : "dadosOD.db",

	jsonName : "dadosOD.json",

	user_login : null,

	senha_login : null,

	posto : null,

	sentido : null,

	filePaths : null // { externalFolder : null, dbFolder : null, }
	,

}; // end of app

// Registro do momento
var registro;

$(document).ready(function() {
	insert_controllers.insert();
	app.initialize();
});
