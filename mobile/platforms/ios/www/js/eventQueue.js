eventQueue = {
	_fila : [],

	_ocupado : false,

	initEventQueue : function() {
		app.logger.log("Iniciando fila de eventos.");
		var me = this;
		// inicia o monitor
		setInterval(function() {
			eventQueue._monitoraFila();
		}, 300);
		app.logger.log("Fila de eventos iniciada.");
	},

	_monitoraFila : function() {
		if (eventQueue._fila.length > 0 && !eventQueue._ocupado) {
			eventQueue._ocupado = true;
			eventQueue._consume(eventQueue._fila.shift());
			eventQueue._ocupado = false;
		}
	},

	appendRow : function(event) {
		if (!eventQueue._ocupado) {
			app.logger.log("Inserindo evento na fila: " + event);
			eventQueue._fila.push(event);
		} else {
			app.logger.log("Evento descartado da fila: " + event);
		}
	},

	_consumeQueue : function(row) {
		app.logger.log("Consumindo evento da fila: " + event);
		// TODO
		app.logger.log("Evento consumido da fila: " + event);
	}

};
