myDb = {
    
    tabelaOD : [
        {field : 'id', type : 'text'}, // TODO (redundante? id + idIpad) Number(String(device.serial) + String(Math.floor(Date.now() / 1000)) that is the device serial concats with unix timestamp
        {field : 'estaNoNote', type : 'integer'}, //Boolean 1->true||false, otherwise;
        {field : 'cancelado', type : 'integer'},
        {field : 'idPosto' , type : 'integer'},
        {field : 'sentido' , type : 'text'},
        {field : 'idIpad' , type : 'text'},
        {field : 'login' , type : 'text'}, // idPost + sentido (redundante?)
        {field : 'dataIniPesq' , type : 'text'},
        {field : 'dataFimPesq' , type : 'text'},
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
		app.database
				.transaction(
						function(tx) {
							var sql = "CREATE TABLE IF NOT EXISTS tblDados \
                     (id text primary key, \
                      registro text, \
                      estado text) ";
							tx.executeSql(sql);
						}, function(e) {
							myLogger.write('ERRO: ' + e.message);
						});
	},

	insertRegistro : function(reg) {
		app.database.transaction(function(tx) {
			var sql = "INSERT INTO tblDados (id, registro, estado) VALUES (? , ? , ?)";
			tx.executeSql(sql, [ reg.id, JSON.stringify(reg), 'não enviado' ], function(tx, res) {
				myLogger.write('id inserido no banco de dados: ' + res.insertId);
			}, function(e) {
				myLogger.write('ERRO: ' + e.message);
			});
		});
	}
};

