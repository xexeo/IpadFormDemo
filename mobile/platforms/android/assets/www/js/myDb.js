myDb = {

	tabelaOD : [
		{field : 'id', type : 'text'}, // TODO (redundante? id + idIpad) Number(String(device.serial) + String(Math.floor(Date.now() / 1000)) that is the device serial concats with unix timestamp
		{field : 'estaNoNote', type : 'integer'}, //Boolean 1->true||false, otherwise;
		{field : 'cancelado', type : 'integer'},
		{field : 'idPosto' , type : 'integer'},
		{field : 'sentido' , type : 'text'},
		{field : 'idIpad' , type : 'text'},
		{field : 'login' , type : 'text'}, // idPost + sentido (redundante?)
		{field : 'timestampIniPesq' , type : 'text'},
		{field : 'timestampFimPesq' , type : 'text'},
		{field : 'tipo' , type : 'text'},
		{field : 'classeVeiculo' , type : 'text'},
		{field : 'possuiReboque' , type : 'text'},
		{field : 'placaEstrangeira' , type : 'integer'}, //Boolean 0->false||true, otherwise; //é preciso mesmo? se a placa não for estrangeira o país é o Brasil, oras
		{field : 'idPaisPlacaEstrangeira' , type : 'integer'}, 
		{field : 'placa' , type : 'text'}, 
		{field : 'anoDeFabricacao', type : 'integer'},
		{field : 'idPropriedadesDoVeiculo', type : 'integer'},
		{field : 'idCombustivel', type : 'integer'},
		{field : 'idOrigemPais', type : 'integer'},
		{field : 'idOrigemMunicipio', type : 'integer'},
		{field : 'geocod_origem', type : 'integer'},
		{field : 'idDestinoPais', type : 'integer'},
		{field : 'idDestinoMunicipio', type : 'integer'},
		{field : 'geocod_destino', type : 'integer'},
		{field : 'frequencia', type : 'text'},
		{field : 'idMotivoDeEscolhaDaRota', type : 'text'},
		{field : 'idMotivoDaViagem', type :'text'},
		{field : 'numeroDePessoasNoVeiculo', type : 'integer'},
		{field : 'numeroDePessoasATrabalho', type : 'integer'},
		{field : 'idRendaMedia', type :'text'},
	],


	fieldExists : function(str) {
		var found = false;
		$.each(myDb.tabelaOD, function(index, item) {
			if (item.field == str) {
				found = true;
			}
		});
		return found;
	},

	cretateTblDados : function() {
		myLogger.write("criando tabela: tblDados");
		app.database.transaction(
            function(tx) {
                var sql = "CREATE TABLE IF NOT EXISTS tblDados \
                        (id text primary key, \
                        registro text, \
                        estado text) ";
                tx.executeSql(sql);
            }, function(e) {
                myLogger.write('ERRO: ' + e.message);
            }, function(){
                myLogger.write("tabela criada: tblDados");
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
		myLogger.write("inserindo registro: " + reg.id);
		try{
            app.database.transaction(function(tx) {
                var sql = "INSERT INTO tblDados (id, registro, estado) VALUES (? , ? , ?)";
                tx.executeSql(sql, [ reg.id, JSON.stringify(reg), 'NAO_ENVIADO' ], function(tx, res) {
                    myLogger.write('id inserido no banco de dados: ' + res.insertId);
                });
            },
            // transaction fail
            function(e) {
                //inseriu = false;
                myLogger.write('ERRO ao inserir registro (' + reg.id + '): ' + e.message);
                fail(e);
            },
            // transaction success
            function() {
                //inseriu = true;
                myLogger.write("registro inserido: " + reg.id);
                success();
            });
        } catch(e){
            fail(e);
        }
	}

};
