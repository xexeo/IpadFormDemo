var util = {
		
	// Funções para o inicializa
	inicializaSelect : function(nome_campo, lista) {
		var insert_inicial = "<option value='-1'>Selecione</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + index + "'>" + item + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	inicializaSelectCustom : function(nome_campo, lista, mensagem) {
		var insert_inicial = "<option value='-1'>" + mensagem + "</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + index + "'>" + item + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},
	
	inicializaSelectCustomValueAsIndex : function(nome_campo, lista, mensagem) {
		var insert_inicial = "<option value='-1'>" + mensagem + "</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + item + "'>" + item + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},
	
	inicializaSelectMunicipio : function(nome_campo, uf_sigla, mensagem) {
		var insert_inicial = "<option value='-1'>" + mensagem + "</option>\n";
				
		$.each(lista_municipios[uf_sigla], function(index, item) {
			insert_inicial += "<option value='" + item.id + "'>" + item.nome + "</option>\n";
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

	progressoSelectPais : function(nome_registro, nome_campo, proximo_imediato, proximo_imediato2, grupo_proximo) {
		$('#' + nome_campo).change(function() {
			grupo_proximo_imediato = "grupo_" + proximo_imediato;
			grupo_proximo_imediato2 = "grupo_" + proximo_imediato2;
			if (Number($(this).val()) == -1) {
				$("#" + grupo_proximo_imediato).hide();
				$("#" + grupo_proximo_imediato2).hide();
				$("#" + grupo_proximo).hide();
				app.setAtributo(nome_registro, null);
				app.setAtributo(proximo_imediato, null);
				app.setAtributo(proximo_imediato2, null);
			} else if (Number($(this).val()) != 0) { // País diferente de Brasil
				$("#" + grupo_proximo_imediato).hide();
				$("#" + grupo_proximo_imediato2).hide();
				$("#" + grupo_proximo).show();
				app.setAtributo(nome_registro, $(this).val());
				app.setAtributo(proximo_imediato, null);
				app.setAtributo(proximo_imediato2, null);
			} else { // País é Brasil
				$("#" + grupo_proximo_imediato).show();
				$("#" + grupo_proximo).hide();
				app.setAtributo(nome_registro, $(this).val());
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
	},
	
	
	// Funções para validação dos componentes
	validaSelect : function(nome_campo, campo_aviso) {
		if(Number($('#' + nome_campo).val()) != -1) {
			return true;
		} else {
			util.alerta_msg(campo_aviso);
			return false;
		}
	},
	
	validaRadioSimNao : function(nome_campo, campo_aviso) {
		var option = $('input[name=' + nome_campo +']:checked').val();
		if(option != null) {
			return true;
		} else {
			util.alerta_msg(campo_aviso);
			return false;
		}
	},
	
	validaInputText : function(nome_campo, campo_aviso) {
		var value = $.trim($('#' + nome_campo).val())
		if(value.length > 0) {
			return true;
		} else {
			util.alerta_msg(campo_aviso);
			return false;
		}
	},
	
	alerta_msg : function(campo_aviso) {
		alert("Campo " + campo_aviso + " não foi preenchido");
	}	

};
