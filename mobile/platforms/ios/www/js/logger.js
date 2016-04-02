myLogger = {
    _fila : [],
    
    _ocupado : false,
    
    _logWriter : null,
    
    setLogFile : function(arquivo){
        myLogger._logFile = arquivo;
    },
    
    setLogWriter : function(writer){
        var me = this;
        
        //inicia o monitor
        setInterval(function(){
            myLogger._monitoraFila();
        }, 300);
        
        myLogger._logWriter = writer;
        myLogger._logWriter.onwriteend = function(e){
            me._ocupado = false;
        };
        myLogger._logWriter.onerror = function(e){
            console.log('Erro de escrita: ' + e.message);  
        };
    },
    
    _monitoraFila : function(){
        if (myLogger._fila.length > 0  && !myLogger._ocupado){
            myLogger._ocupado = true;
            myLogger._internalWrite(myLogger._fila.shift());
        }
    },
    
    write: function(str){
        /*if(!myLogger._ocupado){
            myLogger._ocupado = true;
            myLogger._internalWrite(str);
        } else {
            myLogger._fila.push(str);
        }*/
        myLogger._fila.push(str);
    },
    
    /**
    * @param {fileEntry} 
    *      arquivo para leitura
    * @param {function} cb
    *      callback que recebe o resultado da leitura do arquivo de log
    */
   read : function(arquivo, cb){
       arquivo.file(function(file){
           var reader = new FileReader();
           reader.onloadend = function(e){
               cb(this.result);
           };
           reader.readAsText(file);
       });
   },
    
    _internalWrite : function(str){
        var log = "[" + (new Date()) + "] " + str + "\n";
        var blob = new Blob([log], {type:'text/plain'});
        var me = this;
        try{
            me._logWriter.seek(me._logWriter.length);
            me._logWriter.write(blob);
            console.log(log);
        } catch(e){
            console.log(e.message);
        }
    }
        
};


