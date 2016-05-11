myDb = {

		
	tabelaOD : [
		{field : 'id', type : 'text primary key'}, // IMPORTANTE: não enviar p/ o servidor
		{field : 'estaNoNote', type : 'integer'}, //Boolean 1->true || false, otherwise;
		{field : 'cancelado', type : 'integer'},
		{field : 'idPosto' , type : 'integer'},
		{field : 'sentido' , type : 'text'},
		{field : 'idIpad' , type : 'text'},
		{field : 'uuid' , type : 'text'}, // IMPORTANTE: não enviar p/ o servidor
		{field : 'login' , type : 'text'}, // idPost + sentido (redundante?)
		{field : 'timestampIniPesq' , type : 'text'},
		{field : 'timestampFimPesq' , type : 'text'},
		{field : 'placa' , type : 'text'}, 
		{field : 'anoDeFabricacao', type : 'integer'},
		{field : 'tipo' , type : 'text'},
		{field : 'idOrigemPais', type : 'integer'},
		{field : 'idOrigemMunicipio', type : 'integer'},
		{field : 'idDestinoPais', type : 'integer'},
		{field : 'idDestinoMunicipio', type : 'integer'},
		{field : 'idMotivoDeEscolhaDaRota', type : 'text'},
		{field : 'frequenciaQtd', type : 'integer'}, 
		{field : 'frequenciaPeriodo', type : 'text'}, 
		{field : 'idPropriedadesDoVeiculo', type : 'integer'},
		{field : 'placaEstrangeira' , type : 'text'}, // Boolean 0->false || true, otherwise; //é preciso mesmo? se a placa não for estrangeira o país é o Brasil, oras
		{field : 'idPaisPlacaEstrangeira' , type : 'integer'},
		{field : 'idCombustivel', type : 'integer'},
		{field : 'categoria' , type : 'text'},
		{field : 'possuiReboque' , type : 'text'},
		{field : 'numeroDePessoasNoVeiculo', type : 'integer'},
		{field : 'numeroDePessoasATrabalho', type : 'integer'},
		{field : 'idRendaMedia', type :'text'},
		{field : 'idMotivoDaViagem', type :'text'},
		{field : 'tipoCaminhao', type : 'text'},
		{field : 'idTipoDeContainer', type : 'integer'},
		{field : 'idTipoCarroceria', type : 'integer'},
		{field : 'rntrc', type : 'text'},
		{field : 'possuiCargaPerigosa', type : 'integer'}, // Boolean 1->true || false, otherwise;
		{field : 'idNumeroDeRisco', type : 'text'},
		{field : 'idNumeroDaOnu', type : 'text'},
		{field : 'idAgregado', type : 'integer'},
		{field : 'placaVermelha', type : 'integer'}, // Boolean 1->true || false, otherwise;
		{field : 'idTipoDeViagemOuServico', type : 'integer'},
		{field : 'pesoDaCarga', type : 'real'},
		{field : 'valorDoFrete', type : 'real'},
		{field : 'utilizaParadaEspecial', type : 'integer'}, //boolean 1->true
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
		{field : 'idPerguntaExtra', type : 'integer'}
	],


	fieldExists : function(str) {
		var found = false;
		$.each(myDb.tabelaOD, function(index, item) {
			if (item.field == str) {
				found = true;
				return false; //breaks $.each
			}
		});
		return found;
	},

	cretateTblDados : function() {
		app.logger.log("criando tabela: tblDados");
		app.database.transaction(
            function(tx) {
                var sql = "CREATE TABLE IF NOT EXISTS tblDados ( ";
				$.each(myDb.tabelaOD, function(index, item){
					sql += item.field + " " + item.type;
					if (index < myDb.tabelaOD.length-1) sql+= ", ";
				});
				sql += ");";
				tx.executeSql(sql);
            }, function(e) {
                app.logger.log('ERRO: ' + e.message);
            }, function(){
                app.logger.log("tabela criada: tblDados");
        });
		
	},

    /**
     * Inserts a registro variable into database
     * @param Registro reg registro to be inserted into database
     * @param Function fail error callback
     * @param Function success callback
     */
	insertRegistro : function(reg, fail, success) {
		//var inseriu = false;
		app.logger.log("inserindo registro: " + reg.id);
		try{
            app.database.transaction(function(tx) {
                
				
				var fields = "(";
				var places = "(";
				var values = [];
				$.each(myDb.tabelaOD, function(index, item){
					fields += item.field;
					places += "?";
					values.push(reg[item.field]);
					if (index < myDb.tabelaOD.length-1) {
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
                //inseriu = false;
                app.logger.log('ERRO ao inserir registro (' + reg.id + '): ' + e.message);
                fail(e);
            },
            // transaction success
            function() {
                //inseriu = true;
                app.logger.log("registro inserido: " + reg.id);
                success();
            });
        } catch(e){
            fail(e);
        }
	}

};
