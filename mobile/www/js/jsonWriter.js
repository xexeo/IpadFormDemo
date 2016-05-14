jsonWriter = {
	_fila : [],

	_ocupado : false,

	_logWriter : null,

	setJsonFile : function(arquivo) {
		jsonWriter._logFile = arquivo;
	},

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

	_monitoraFila : function() {
		if (jsonWriter._fila.length > 0 && !jsonWriter._ocupado) {
			jsonWriter._ocupado = true;
			jsonWriter._internalWrite(jsonWriter._fila.shift());
		}
	},

	appendRow : function(str) {
		console.log(str);
		jsonWriter._fila.push(str);
	},

	/**
	 * @param {fileEntry}
	 *            arquivo para leitura
	 * @param {function}
	 *            cb callback que recebe o resultado da leitura do arquivo json
	 */
	read : function(arquivo, cb) {
		arquivo.file(function(file) {
			var reader = new FileReader();
			reader.onloadend = function(e) {
				cb(this.result);
			};
			reader.readAsText(file);
		});
	},

	_internalWrite : function(row) {
		var blob = new Blob([ row + '\n' ], {
			type : 'text/plain'
		});
		var me = this;
		try {
			me._logWriter.seek(me._logWriter.length);
			me._logWriter.write(blob);
			console.log(row);
		} catch (e) {
			console.log(e.message);
		}
	}

};
