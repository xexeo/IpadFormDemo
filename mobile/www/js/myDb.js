myDb = {

	camposNaoExportaveisJson : [ 'id', 'uuid', 'login', 'erro' ],

	tabelaOD : [
		{field : 'id', type : 'text primary key'},
		{field : 'estaNoNote', type : 'integer'}, // Boolean 1->true || false, otherwise;
		{field : 'cancelado', type : 'integer'},
		{field : 'idPosto', type : 'integer'},
		{field : 'sentido', type : 'text'},
		{field : 'idIpad', type : 'text'},
		// {field : 'uuid', type : 'text'},
		{field : 'login', type : 'text'}, // idPost + sentido (redundante?)
		{field : 'dataIniPesq', type : 'text'},
		{field : 'dataFimPesq', type : 'text'},
		{field : 'placa', type : 'text'},
		{field : 'anoDeFabricacao', type : 'integer'},
		{field : 'tipo', type : 'text'},
		{field : 'idOrigemPais', type : 'integer'},
		{field : 'idOrigemMunicipio', type : 'integer'},
		{field : 'idDestinoPais', type : 'integer'},
		{field : 'idDestinoMunicipio', type : 'integer'},
		{field : 'idMotivoDeEscolhaDaRota', type : 'integer'},
		{field : 'frequenciaQtd', type : 'integer'},
		{field : 'frequenciaPeriodo', type : 'text'},
		{field : 'idPropriedadesDoVeiculo', type : 'integer'},
		{field : 'placaEstrangeira', type : 'integer'}, // Boolean 1->true || false, otherwise;
		{field : 'idPaisPlacaEstrangeira', type : 'integer'},
		{field : 'idCombustivel', type : 'integer'},
		{field : 'categoria', type : 'text'},
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
		{field : 'erro', type : 'text'}
	],

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

	cretateTblDados : function() {
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
		});

	},

	/**
	 * Inserts a registro variable into database
	 * 
	 * @param Registro
	 *            reg registro to be inserted into database
	 * @param Function
	 *            fail error callback
	 * @param Function
	 *            success callback
	 */
	insertRegistro : function(reg, fail, success) {
		app.logger.log("inserindo registro: " + reg.id);
		try {
			app.database.transaction(function(tx) {

				var fields = "(";
				var places = "(";
				var values = [];
				$.each(myDb.tabelaOD, function(index, item) {
					fields += item.field;
					places += "?";
					values.push(reg[item.field]);
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
	},

	exportaDbToJson : function(writer, fail, success) {
		app.logger.log("exportando Json: ");
		try {
			app.database.transaction(function(tx) {
				var fields = "";
				$.each(myDb.tabelaOD, function(index, item) {
					fields += item.field;
					if (index < myDb.tabelaOD.length - 1) {
						fields += ", ";
					}
				});

				var sql = "SELECT " + fields + " FROM tblDados;";

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
