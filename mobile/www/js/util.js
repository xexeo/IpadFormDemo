var util = {

	// Funções para o inicializa
	inicializaSelect : function(nome_campo, lista) {
		var insert_inicial = "<option value='-1'>Selecione</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + item + "'>" + item + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	inicializaSelectPaises : function(nome_campo) {
		var insert_inicial = "<option value='-1'>Selecione</option>\n";
		$.each(paises.listados(), function(index, item) {
			insert_inicial += "<option value='" + item + "'>" + item + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	// Funções para o progresso
	/**
	 * 
	 * @param nome_registro
	 *            nome do atributo da variável global registro
	 * @param nome_campo
	 *            id do componente html
	 * @param grupo_proximo
	 *            id da div que irá sofrer show/hide
	 */
        progressoRadioSimNao : function(nome_registro, nome_campo, grupo_proximo) {
		$('#' + nome_campo + '_sim').click(function() {
			$('#' + grupo_proximo).show();
			app.setAtributo(nome_registro, true);
		});
		$('#' + nome_campo + '_nao').click(function() {
			$('#' + grupo_proximo).show();
			app.setAtributo(nome_registro, false);
		});
	},

	/**
	 * 
	 * @param nome_registro
	 *            nome do atributo da variável global registro
	 * @param nome_campo
	 *            id do componente html
	 * @param grupo_proximo
	 *            id da div que irá sofrer show/hide
	 */
	progressoSelect : function(nome_registro, nome_campo, grupo_proximo) {
		$('#' + nome_campo).change(function() {
			if (Number($(this).val()) != -1) {
				app.setAtributo(nome_registro, $(this).val());
				$("#" + grupo_proximo).show();
			} else {
				$("#" + grupo_proximo).hide();
				app.setAtributo(nome_registro, null);
			}
		});
	},

        /**
	 * 
	 * @param nome_registro
	 *            nome do atributo da variável global registro
	 * @param nome_campo
	 *            id do componente html
	 * @param grupo_proximo
	 *            id da div que irá sofrer show/hide
	 */
	progressoInputText : function(nome_registro, nome_campo, grupo_proximo) {
		$('#' + nome_campo).change(function() {
			app.setAtributo(nome_registro, $(this).val());
			$("#" + grupo_proximo).show();
		});
		$('#' + nome_campo).keypress(function() {
			app.setAtributo(nome_registro, $(this).val());
			$("#" + grupo_proximo).show();
		});
	}

};
