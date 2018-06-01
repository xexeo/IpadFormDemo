/// @file myDb.js
/// @namespace myDb
/// Funções para manipulação do banco de dados
myDb = {

	camposNaoExportaveisJson : [ 'id', 'uuid', 'login', 'erro', 'duracaoPesq' ],

	tabelaOD : [
		{field : 'id', type : 'text primary key'},
		{field : 'estaNoNote', type : 'integer'}, // Boolean 1->true || false, otherwise;
		{field : 'cancelado', type : 'integer'},
		{field : 'idPosto', type : 'integer', notNull: true},
		{field : 'sentido', type : 'text', notNull: true},
		{field : 'idIpad', type : 'text', notNull: true},
		// {field : 'uuid', type : 'text'},
		{field : 'login', type : 'text'}, // idPost + sentido (redundante?)
		{field : 'dataIniPesq', type : 'text'},
		{field : 'dataFimPesq', type : 'text'},
		{field : 'placa', type : 'text'},
		{field : 'anoDeFabricacao', type : 'integer', notNull: true},
		{field : 'tipo', type : 'text', notNull: true},
		{field : 'idOrigemPais', type : 'integer', notNull: true},
		{field : 'idOrigemMunicipio', type : 'integer'},
		{field : 'idDestinoPais', type : 'integer', notNull: true},
		{field : 'idDestinoMunicipio', type : 'integer'},
		{field : 'idMotivoDeEscolhaDaRota', type : 'integer'},
		{field : 'frequenciaQtd', type : 'integer'},
		{field : 'frequenciaPeriodo', type : 'text', notNull: true},
		{field : 'idPropriedadesDoVeiculo', type : 'integer', notNull: true},
		{field : 'placaEstrangeira', type : 'integer'}, // Boolean 1->true || false, otherwise;
		{field : 'idPaisPlacaEstrangeira', type : 'integer'},
		{field : 'idCombustivel', type : 'integer', notNull: true},
		{field : 'categoria', type : 'text', notNull: true},
		{field : 'possuiReboque', type : 'integer'},
		{field : 'numeroDePessoasNoVeiculo', type : 'integer'},
		{field : 'numeroDePessoasATrabalho', type : 'integer'},
		{field : 'idRendaMedia', type : 'integer'},
		{field : 'idMotivoDaViagem', type : 'integer'},
		{field : 'tipoCaminhao', type : 'text'},
		{field : 'idTipoDeContainer', type : 'integer'},
		{field : 'idTipoCarroceria', type : 'integer'},
		{field : 'rntrc', type : 'text'},
		{field : 'possuiCargaPerigosa', type : 'integer'}, // Boolean 1->true || false, otherwise;
		{field : 'idNumeroDeRisco', type : 'integer'},
		{field : 'idNumeroDaOnu', type : 'integer'},
		{field : 'idAgregado', type : 'integer'},
		{field : 'placaVermelha', type : 'integer'}, // Boolean 1->true || false, otherwise;
		{field : 'idTipoDeViagemOuServico', type : 'integer'},
		{field : 'pesoDaCarga', type : 'real'},
		{field : 'valorDoFrete', type : 'real'},
		{field : 'utilizaParadaEspecial', type : 'integer'}, // Boolean 1->true || false, otherwise;
		{field : 'idProduto', type : 'integer'},
		{field : 'idCargaAnterior', type : 'integer'},
		{field : 'valorDaCarga', type : 'real'},
		{field : 'municipioEmbarqueCarga', type : 'integer'},
		{field : 'idLocalEmbarqueCarga', type : 'integer'},
		{field : 'municipioDesembarqueCarga', type : 'integer'},
		{field : 'idLocalDesembarqueCarga', type : 'integer'},
		{field : 'indoPegarCarga', type : 'integer'},
		{field : 'paradaObrigatoriaMunicipio1', type : 'integer'},
		{field : 'paradaObrigatoriaMunicipio2', type : 'integer'},
		{field : 'idPerguntaExtra', type : 'integer'},
		{field : 'idPerguntaExtra2', type : 'integer'},
		{field : 'erro', type : 'text'},
		{field : 'duracaoPesq', type : 'integer'},
		{field : 'treinamento', type : 'integer', notNull: true}// Boolean 1->true || false, otherwise;
	],

	/// @function myDb.fieldExists
	/// Verifica se um campo existe na estrutura da tabela do banco de dados
	/// @param {string} str identificação do campo
	/// @return {bool} resultado da verificação
	fieldExists : function(str) {
		var found = false;
		$.each(myDb.tabelaOD, function(index, item) {
			if (item.field == str) {
				found = true;
				return false; // breaks $.each
			}
		});
		return found;
	},

	/// @function myDb.cretateTbDados
	/// Cria a tabela para armazenar os dados das pesquisas
	/// @param {function} cb função _callback_
	/// @return {void} função sem retorno
	cretateTblDados : function(cb) {
		app.logger.log("criando tabela: tblDados");
		app.database.transaction(function(tx) {
			var sql = "CREATE TABLE IF NOT EXISTS tblDados ( ";
			$.each(myDb.tabelaOD, function(index, item) {
				sql += item.field + " " + item.type;
				if (index < myDb.tabelaOD.length - 1)
					sql += ", ";
			});
			sql += ");";
			tx.executeSql(sql);
		}, function(e) {
			app.logger.log('ERRO: ' + e.message);
		}, function() {
			app.logger.log("tabela criada: tblDados");
			if(util.isFunction(cb)){
				cb();
			}
		});

	},

	/// @function myDb.createTblSchema
	/// Cria a tabela para armazer a versão do esquema do banco de dados
	/// @param {function} cb função _callback_
	/// @return {void} função sem retorno
	createTblSchema : function(cb) {
		app.logger.log("criando tabela: tblSchema");
		app.database.transaction(function(tx) {
			var sql = "CREATE TABLE IF NOT EXISTS tblSchema (versao integer);";
			tx.executeSql(sql);
		}, function(e) {
			app.logger.log('ERRO: ' + e.message);
		}, function() {
			app.logger.log("tabela criada: tblSchema");		
			if(util.isFunction(cb)){
				cb();
			}
		});

	},
	
	/// @function myDb.updateSchema
	/// Atualiza o esquema do banco de dados
	/// @param {function} cb função _callback_
	/// @return {void} função sem retorno
	updateSchema : function(cb){
		app.logger.log("update schema");
		myDb.createTblSchema(function(){
			var versaoAtual = 0;
			app.database.transaction(function(tx) {
				tx.executeSql("SELECT versao from tblSchema LIMIT 1;", [], function(tx, res) {
					if( res.rows.length > 0) {
						versaoAtual = res.rows.item(0).versao;
					}
					
					if(versaoAtual == 0){
						tx.executeSql("INSERT INTO tblSchema VALUES(" + app.versaoBD + ");");
					} else if (versaoAtual != app.versaoBD){
						tx.executeSql("UPDATE tblSchema SET versao = " + app.versaoBD + ");");
						
						if (versaoAtual < 1 ){
							tx.executeSql("ALTER TABLE tblDados ADD COLUMN idPerguntaExtra2 integer;");
						}
					}
				
					
				});
			
			}, function(e){
				app.logger.log('ERRO: ' + e.message);
			}, function(){
				app.logger.log("BD schema anterior: " + versaoAtual);
				app.logger.log("BD schema atual: " + app.versaoBD);
				if(util.isFunction(cb)){
					cb();
				}
			});
		});
	},

	
	/// @function myDb.insertRegistro
	/// Registra os dados de uma pesquisa no banco de dados
	/// @param {Registro} reg estrutura de dados com todas as informações de uma pesquisa
	/// @param {function} fail função _callback_ disparada no caso de falha
	/// @param {function} success função _callback_ dispara no caso de sucesso
	/// @return {void} função sem retorno
	insertRegistro : function(reg, fail, success) {
		if (reg.id != null){
				app.logger.log("inserindo registro: " + reg.id);
			try {
				app.database.transaction(function(tx) {

					var fields = "(";
					var places = "(";
					var values = [];
					$.each(myDb.tabelaOD, function(index, item) {
						fields += item.field;
						places += "?";
						var value = reg[item.field];
						values.push((value == undefined ? null : value));
						if (index < myDb.tabelaOD.length - 1) {
							fields += ", ";
							places += ", ";
						}
					});
					fields += ")";
					places += ");";

					var sql = "INSERT INTO tblDados " + fields + " VALUES " + places;

					tx.executeSql(sql, values, function(tx, res) {
						app.logger.log('id inserido no banco de dados: ' + res.insertId);
					});
				},
				// transaction fail
				function(e) {
					// inseriu = false;
					app.logger.log('ERRO ao inserir registro (' + reg.id + '): ' + e.message);
					fail(e);
				},
				// transaction success
				function() {
					// inseriu = true;
					app.logger.log("registro inserido: " + reg.id);
					success();
				});
			} catch (e) {
				fail(e);
			}
		} else {
			app.logger.log("Tentativa de inserção de registro sem id");
		}
		
	},
	
	/// @function myDb.sanitize
	/// Executa rotina de verificação do banco
	/// @param {function} cb função _callback_
	/// @return {void} função sem retorno
	sanitize: function(cb){
		app.database.transaction(function(tx) {
			var sql = "DELETE from tblDados WHERE id is null;";
			tx.executeSql(sql);
		},
		//fail
		function(e){
			app.logger.log('ERRO ao limpar a base de dados: ' + e.message);
		},
		//success
		function(){
			app.logger.log('Base de dados limpa');
			if (util.isFunction(cb)){
				cb();
			}
		})
	},

/*
select date(dataIniPesq) as 'diaPesq',AVG(duracaoPesq) as 'mediaDia',SUM(duracaoPesq) as 'somaDia',COUNT(id) as 'qtdDia', MAX(duracaoPesq) as 'maxTempoDia', MIN(duracaoPesq) as 'minTempoDia'
from tblDados
WHERE cancelado = 0
GROUP by date(dataIniPesq)
ORDER BY diaPesq DESC;
*/
	/// @function myDb.selectDuracoesDiaRegistro
	/// Recupera a duração das pesquisas
	/// @param {function} fail função _callback_ disparada em caso de falha
	/// @param {function} success função _callback_ dispara em caso de sucesso
	/// @return {void} função sem retorno
	selectDuracoesDiaRegistro : function(fail, success) {
		app.logger.log("(selectDuracoesDiaRegistro) buscando no registro");
		var linhas = [];
		var treinamento = (app.isTreinamento)? 1 : 0;
		try {
			app.database.transaction(function(tx) {

				var sql = "SELECT DATE(dataIniPesq) as 'diaPesq'," +
						" AVG(duracaoPesq) as 'mediaDia'," +
						" SUM(duracaoPesq) as 'somaDia',"+
						" COUNT(id) as 'qtdDia'," +
						" MAX(duracaoPesq) as 'maxTempoDia'," +
						" MIN(duracaoPesq) as 'minTempoDia'" +
						" FROM tblDados WHERE cancelado = 0" +
						" AND treinamento = " + treinamento +
						" AND idPosto = " + app.posto +
						" GROUP by DATE(dataIniPesq) ORDER BY diaPesq DESC";

				tx.executeSql(sql, [], function(tx, res) {
					app.logger.log('qtd linhas select: ' + res.rows.length);
					for (i = 0; i < res.rows.length; i++) {
						var elem = res.rows.item(i);
						linhas.push(elem);
						app.logger.log('item ' + i + ':' + elem.diaPesq + '; ' + elem.mediaDia  + '; ' + elem.somaDia + '; ' + elem.qtdDia + '; ' + elem.maxTempoDia + '; ' + elem.minTempoDia);
					}
				});
			},
			// transaction fail
			function(e) {
				app.logger.log('ERRO ao buscar tempos das pesquisas: ' + e.message);
				fail(e);
			},
			// transaction success
			function() {
				app.logger.log("Busca dos tempos das pesquisas realizado com sucesso");
				app.sumario_lista = linhas;
				success();
			});
		} catch (e) {
			fail(e);
		}
	},

	/// @function myDb.selectUltimaPesquisaValida
	/// Seleciona a última pesquisa válida
	/// @param {function} fail função _callback_ disparada em caso de falha
	/// @param {function} success função _callback_ dispara em caso de sucesso
	/// @return {void} função sem retorno
	selectUltimaPesquisaValida : function(fail, success) {
		app.logger.log("(selectUltimaPesquisaValida) buscando no registro");
		var ultimoRegistro = [];
		var treinamento = (app.isTreinamento)? 1 : 0;
		try {
			app.database.transaction(function(tx) {

				var sql = "SELECT dataIniPesq," +
						" duracaoPesq" +
						" FROM tblDados WHERE cancelado = 0" +
						" AND treinamento = " + treinamento +
						" AND idPosto = " + app.posto +
						" ORDER BY dataIniPesq DESC" +
						" LIMIT 1";

				tx.executeSql(sql, [], function(tx, res) {
					if( res.rows.length > 0) {
						ultimoRegistro.push(res.rows.item(0).dataIniPesq);
						ultimoRegistro.push(res.rows.item(0).duracaoPesq);
						app.logger.log('Ultimo registro: dataIniPesq = ' + ultimoRegistro[0] + '; duracaoPesq = ' + ultimoRegistro[1]);
					}
				});
			},
			// transaction fail
			function(e) {
				app.logger.log('ERRO ao buscar última pesquisa: ' + e.message);
				fail(e);
			},
			// transaction success
			function() {
				app.logger.log("Busca da última pesquisa realizada com sucesso");
				app.ultima_pesquisa = ultimoRegistro;
				success();
			});
		} catch (e) {
			fail(e);
		}
	},

/*
select date(dataIniPesq) as 'diaPesq',AVG(duracaoPesq) as 'mediaDia',SUM(duracaoPesq) as 'somaDia',COUNT(id) as 'qtdDia'
from tblDados
WHERE cancelado = 1
GROUP by date(dataIniPesq)
ORDER BY diaPesq DESC;
*/
	/// @function myDb.selectRegistrosCancelados
	/// Seleciona os registros cancelados
	/// @param {function} fail função _callback_ disparada em caso de falha
	/// @param {function} success função _callback_ dispara em caso de sucesso
	/// @return {void} função sem retorno
	selectRegistrosCancelados : function(fail, success) {
		app.logger.log("(selectRegistrosCancelados) buscando no registro");
		var linhas = [];
		var treinamento = (app.isTreinamento)? 1 : 0;
		try {
			app.database.transaction(function(tx) {

				var sql = "SELECT DATE(dataIniPesq) as 'diaPesq'," +
						" AVG(duracaoPesq) as 'mediaDia'," +
						" SUM(duracaoPesq) as 'somaDia',"+
						" COUNT(id) as 'qtdDia'" +
						" FROM tblDados WHERE cancelado = 1" +
						" AND treinamento = " + treinamento +
						" AND idPosto = " + app.posto +
						" GROUP by DATE(dataIniPesq) ORDER BY diaPesq DESC";

				tx.executeSql(sql, [], function(tx, res) {
					app.logger.log('qtd linhas select: ' + res.rows.length);
					for (i = 0; i < res.rows.length; i++) {
						var elem = res.rows.item(i);
						linhas.push(elem);
						app.logger.log('item ' + i + ':' + elem.diaPesq + '; ' + elem.mediaDia  + '; ' + elem.somaDia  + '; ' + elem.qtdDia);
					}
				});
			},
			// transaction fail
			function(e) {
				app.logger.log('ERRO ao buscar pesquisas canceladas: ' + e.message);
				fail(e);
			},
			// transaction success
			function() {
				app.logger.log("Busca das pesquisas canceladas realizada com sucesso");
				app.sumario_lista_cancelados = linhas;
				success();
			});
		} catch (e) {
			fail(e);
		}
	},

	/// @function myDb.exportaDbToJson
	/// Exporta os registros do banco de dados no formato JSON
	/// @param {jsonWriter} writer referência para um objeto que escreve o arquivo json
	/// @param {function} fail função _callback_ disparada em caso de falha
	/// @param {function} success função _callback_ dispara em caso de sucesso
	/// @return {void} função sem retorno
	exportaDbToJson : function(writer, fail, success) {
		app.logger.log("exportando Json: ");
		var treinamento = (app.isTreinamento)? 1 : 0;
		try {
			app.database.transaction(function(tx) {
				var fields = "";
				$.each(myDb.tabelaOD, function(index, item) {
					fields += item.field;
					if (index < myDb.tabelaOD.length - 1) {
						fields += ", ";
					}
				});

				var sql = "SELECT " + fields + " FROM tblDados WHERE cancelado = 0 AND treinamento = " + treinamento + " ;";

				tx.executeSql(sql, [], function(tx, res) {
					for (var rowIndex = 0; rowIndex < res.rows.length; rowIndex++) {
						var rowDB = res.rows.item(rowIndex);
						var rowJson = "{";
						if (rowIndex == 0) {
							rowJson = "[{";
						}
						$.each(myDb.tabelaOD, function(index, item) {
							if (!util.contains(item.field, myDb.camposNaoExportaveisJson)) {
								var value = rowDB[item.field];
								if (value != null) {
									// app.logger.log('FIELD (' + item.type + '): ' + item.field + '\tVALUE (' + typeof value
									// + '): ' + value);
									var typeValue = typeof value;
									if (item.type == 'text') {
										value = '"' + value + '"';
									} else if (item.type == 'integer' && (typeValue != 'number')) {
										if (typeValue == 'boolean') {
											value = Number(value);
										} else if (typeValue == 'string') {
											value = ((value == 'true') ? 1 : ((value == 'false') ? 0 : (isNaN(value) ? ('"'
													+ value + '"') : Number(value))));
										}
									}
								} else if (item.notNull) {
									if (item.type == 'text') {
										value = '""';
									} else {
										value = 0;
									}
								}
								rowJson += '"' + item.field + '":' + value;
								if (index < myDb.tabelaOD.length - 2) { // (length - 2) pq o último campo ('erro') tb não será exportado
									rowJson += ",";
								}
							}
						});
						rowJson += "}";
						if (rowIndex < res.rows.length - 1) {
							rowJson += ",";
						} else {
							rowJson += "]";
						}
						writer.appendRow(rowJson);
					}
				});

			},
			// transaction fail
			function(e) {
				app.logger.log('ERRO ao exportar os dados para o formato JSON: ' + e.message);
				if (util.isFunction(fail)) {
					fail(e);
				}
			},
			// transaction success
			function() {
				app.logger.log("Dados exportados para o formato JSON.");
				if (util.isFunction(success)) {
					success();
				}
			});
		} catch (e) {
			if (util.isFunction(fail)) {
				fail(e);
			}
		}
	}

};
