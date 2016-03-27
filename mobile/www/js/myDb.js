myDb = {
    
    tabelaOD : [
        {field : 'id', type : 'integer'}, // Number(String(device.serial) + String(Math.floor(Date.now() / 1000)) that is the device serial concats with unix timestamp
        {field : 'enviado', type : 'integer'},//Boolean 1->true||false, otherwise;
        {field : 'codVeiculo' , type : 'text'},
        {field : 'classeVeiculo' , type : 'text'},
        {field : 'reboque' , type : 'text'},
        {field : 'placa_estrangeira' , type : 'integer'}, //Boolean 0->false||true, otherwise; //é preciso mesmo? se a placa não for estrangeira o país é o Brasil, oras
        {field : 'pais_placa' , type : 'text'}, 
        {field : 'ano_veiculo', type : 'integer'},
        {field : 'propriedade', type : 'text'},
        {field : 'combustivel', type : 'text'},
        {field : 'origem_pais', type : 'text'},
        {field : 'origem_estado', type : 'text'},
        {field : 'origem_cidade', type : 'text'},
        {field : 'destino_pais', type : 'text'},
        {field : 'destino_estado', type : 'text'},
        {field : 'destino_cidade', type : 'text'},
        {field : 'frequencia_vezes', type : 'integer'},
        {field : 'frequencia_periodo', type : 'text'},
        {field : 'motivo_rota', type : 'text'},
        {field : 'motivo_viagem', type :'text'},
        {field : 'ocupantes', type : 'integer'},
        {field : 'ocupantes_trabalho', type : 'integer'},
        {field : 'renda_condutor', type :'text'},
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
};

