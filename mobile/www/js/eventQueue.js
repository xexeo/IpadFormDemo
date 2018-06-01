/// @file eventQueue.js
/// @namespace eventQueue
/// Implementação de uma fila de eventos
eventQueue = {
	_fila : [],

	_ocupado : false,

	/// @function eventQueue.initEventQueue
	/// Inicia a fila de eventos e o monitor da fila
	/// @return {void} função sem retorno
	initEventQueue : function() {
		app.logger.log("Iniciando fila de eventos.");
		var me = this;
		// inicia o monitor
		setInterval(function() {
			eventQueue._monitoraFila();
		}, 300);
		app.logger.log("Fila de eventos iniciada.");
	},

	/// @function eventQueue._monitoraFila
	/// Monitora a fila de eventos
	/// @return {void} função sem retorno
	_monitoraFila : function() {
		if (eventQueue._fila.length > 0 && !eventQueue._ocupado) {
			eventQueue._ocupado = true;
			eventQueue._consume(eventQueue._fila.shift());
			eventQueue._ocupado = false;
		}
	},

	/// @function eventQueue.appendRow
	/// Insere um evento na fila
	/// @return {void} função sem retorno
	appendRow : function(event) {
		if (!eventQueue._ocupado) {
			app.logger.log("Inserindo evento na fila: " + event);
			eventQueue._fila.push(event);
		} else {
			app.logger.log("Evento descartado da fila: " + event);
		}
	},

	/// @function eventQueue._consumeQueue
	/// Consome um evento da fila
	/// @return {void} função sem retorno
	_consumeQueue : function(row) {
		app.logger.log("Consumindo evento da fila: " + event);
		// TODO
		app.logger.log("Evento consumido da fila: " + event);
	}

};
