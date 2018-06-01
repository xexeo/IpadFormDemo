/// @file logger.js
/// @namespace myLogger
/// Funções que executam e controlam o registro das operações executadas na aplicação em um arquivo de texto
myLogger = {
	_fila : [],

	_ocupado : false,

	_logWriter : null,

	/// @function myLogger.setLogFile
	/// Configura o arquivo a ser escrito
	/// @param {FileEntry} arquivo referência para um arquivo
	/// @return {void} função sem retorno
	setLogFile : function(arquivo) {
		myLogger._logFile = arquivo;
	},
	/// @function myLogger.setLogWriter
	/// Configura o processo de escrita do arquivo e inicia o monitoramento da fila que receberá as linhas a serem escritas
	/// @param {FileWriter} writer referência para um objeto escritor
	/// @return {void} função sem retorno
	setLogWriter : function(writer) {
		var me = this;

		// inicia o monitor
		setInterval(function() {
			myLogger._monitoraFila();
		}, 300);

		myLogger._logWriter = writer;
		myLogger._logWriter.onwriteend = function(e) {
			me._ocupado = false;
		};
		myLogger._logWriter.onerror = function(e) {
			console.log('Erro de escrita do log: ' + e.message);
		};
	},
	/// @function myLogger._monitoraFila
	/// Monitora e consome a fila para escrita
	/// @return {void} função sem retorno
	_monitoraFila : function() {
		if (myLogger._fila.length > 0 && !myLogger._ocupado) {
			myLogger._ocupado = true;
			var strVO = myLogger._fila.slice();
			myLogger._fila = [];
//			while (myLogger._fila.length > 0){
//				str.push(myLogger._fila.shift());
//				if (myLogger._fila.length > 1){
//					str += "\n";
//				}
//			}
			myLogger._internalWrite(strVO);
		}
	},

	/// @function myLogger.log
	/// Acrescenta uma linha na fila de escrita
	/// @param {string} str linha a ser escrita
	/// @return {void} função sem retorno
	log : function(str) {
		myLogger._fila.push("[" + device.uuid + "] [" + (new Date()) + "] " + str + "\n"); //log moment
	},

	/// @function myLogger.read
	/// Lê um arquivo
	/// @param {FileEntry} arquivo referência para um arquivo
	/// @param {function} cb função _callback_ que recebe o resultado da leitura do arquivo
	/// @return {void} função sem retorno
	read : function(arquivo, cb) {
		arquivo.file(function(file) {
			var reader = new FileReader();
			reader.onloadend = function(e) {
				cb(this.result);
			};
			reader.readAsText(file);
		});
	},

	/// @function myLogger._internalWrite
	/// Escreve uma linha no arquivo
	/// @param {string} strVO linha a ser escrita
	/// @return {void} função sem retorno
	_internalWrite : function(strVO) {
		//var log = "[" + device.uuid + "] [" + (new Date()) + "] " + str + "\n";
		str = "";
		while(strVO.length > 0){
			str += "[" + (new Date()) + "]" + strVO.shift(); //write moment
			if (strVO.length > 1){
				str += "\n";
			}
		}
		
		var blob = new Blob([ str ], {
			type : 'text/plain'
		});
		var me = this;
		try {
			me._logWriter.seek(me._logWriter.length);
			me._logWriter.write(blob);
			console.log(str);
		} catch (e) {
			console.log(e.message);
		}
	}

};
