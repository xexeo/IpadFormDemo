/// @file jsonWriter.js
/// @namespace jsonWriter
/// Escreve um arquivo no formato JSON
jsonWriter = {
	_fila : [],

	_ocupado : false,

	_logWriter : null,

	/// @function jsonWriter.setJsonFile
	/// Configura o arquivo a ser escrito
	/// @param {FileEntry} arquivo referência para um arquivo
	/// @return {void} função sem retorno
	setJsonFile : function(arquivo) {
		jsonWriter._logFile = arquivo;
	},

	/// @function jsonWriter.setJsonWriter
	/// Configura o processo de escrita do arquivo e inicia o monitoramento da fila que receberá as linhas a serem escritas
	/// @param {FileWriter} writer referência para um objeto escritor
	/// @return {void} função sem retorno
	setJsonWriter : function(writer) {
		var me = this;

		// inicia o monitor
		setInterval(function() {
			jsonWriter._monitoraFila();
		}, 300);

		jsonWriter._logWriter = writer;
		jsonWriter._logWriter.onwriteend = function(e) {
			me._ocupado = false;
		};
		jsonWriter._logWriter.onerror = function(e) {
			console.log('Erro de escrita: ' + e.message);
		};
	},

	/// @function jsonWriter._monitoraFila
	/// Monitora e consome a fila para escrita
	/// @return {void} função sem retorno
	_monitoraFila : function() {
		if (jsonWriter._fila.length > 0 && !jsonWriter._ocupado) {
			jsonWriter._ocupado = true;
			jsonWriter._internalWrite(jsonWriter._fila.shift());
		}
	},

	/// @function jsonWriter.appendRow
	/// Acrescenta uma linha na fila de escrita
	/// @param {string} str linha a ser escrita
	/// @return {void} função sem retorno
	appendRow : function(str) {
		console.log(str);
		jsonWriter._fila.push(str);
	},

	
	/// @function jsonWriter.read
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

	/// @function jsonWriter._internalWrite
	/// Escreve uma linha no arquivo
	/// @param {string} row linha a ser escrita
	/// @return {void} função sem retorno
	_internalWrite : function(row) {
		var blob = new Blob([ row + '\n' ], {
			type : 'text/plain'
		});
		var me = this;
		try {
			me._logWriter.seek(me._logWriter.length);
			me._logWriter.write(blob);
		} catch (e) {
			console.log(e.message);
		}
	}

};
