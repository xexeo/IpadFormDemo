myDb = {
    
    tabelaOD : [
        {field : 'id', type : 'integer'}, // Number(String(device.serial) + String(Math.floor(Date.now() / 1000)) that is the device serial concats with unix timestamp
        {field : 'enviado', type : 'integer'},//Boolean 1->true||false, otherwise;
        {field : 'tipo' , type : 'text'},
        {field : 'classeVeiculo' , type : 'text'},
        {field : 'possuiReboque' , type : 'text'},
        {field : 'placaEstrangeira' , type : 'integer'}, //Boolean 0->false||true, otherwise; //é preciso mesmo? se a placa não for estrangeira o país é o Brasil, oras
        {field : 'pais_placa' , type : 'text'}, 
        {field : 'ano_veiculo', type : 'integer'},
        {field : 'idPropriedadesDoVeiculo', type : 'text'},
        {field : 'idCombustivel', type : 'text'},
        {field : 'idOrigemPais', type : 'text'},
        {field : 'origem_estado', type : 'text'},
        {field : 'origem_cidade', type : 'text'},
        {field : 'idDestinoPais', type : 'text'},
        {field : 'destino_estado', type : 'text'},
        {field : 'destino_cidade', type : 'text'},
        {field : 'frequencia_vezes', type : 'integer'},
        {field : 'frequencia_periodo', type : 'text'},
        {field : 'idMotivoDeEscolhaDaRota', type : 'text'},
        {field : 'idMotivoDaViagem', type :'text'},
        {field : 'ocupantes', type : 'integer'},
        {field : 'ocupantes_trabalho', type : 'integer'},
        {field : 'idRendaMedia', type :'text'},
    ],
    
    fieldExists : function(str){
        var found = false;
        $.each(myDb.tabelaOD, function(index, item){
           if (item.field == str){
               found = true;
           } 
        });
        return found;
    },
    
    exportDB : function(){
        //close db
        app.database.close(
            //success
            function(){
                //get FileEntry of original db file
                window.resolveLocalFileSystemURL(cordova.file.dataDirectory, function(dir){
                    dir.getFile(app.dbName, {create : false}, function(file){
                        window.resolveLocalFileSystemURL(cordova.file.documentsDirectory, function(dest_dir){
                            file.copyTo(dest_dir, app.dbName, function(){
                               window.sqlitePlugin.openDatabase(
                                    {name : app.dbName, iosDatabaseLocation : 'Library/LocalDatabase'},null,
                                    function(err){
                                        myLogger.write(JSON.stringify(err));
                                    }
                               );
                               myLogger.write('Banco de dados exportado.');
                            }, function(err){
                                myLogger.write(JSON.stringify(err));
                            });
                        }, function(err){
                            myLogger.write(JSON.stringify(err));
                        }); 
                    }, function(err){
                        myLogger.write(JSON.stringify(err));
                    }); 
                }, function(err){
                    myLogger.write(JSON.stringify(err));
                });
            },
            //fail
            function(err){
                myLogger.write(JSON.stringify(err));
            }
        );
        
    },
};

