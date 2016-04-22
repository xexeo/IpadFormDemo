var util = {

	// Funções para o inicializa
	inicializaSelect : function(nome_campo, lista) {
		var insert_inicial = "<option value='-1'>Selecione</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + (index + 1) + "'>" + item + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	inicializaSelectCustom : function(nome_campo, lista, mensagem) {
		var insert_inicial = "<option value='-1'>" + mensagem + "</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + (index + 1) + "'>" + item + "</option>\n";
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

	inicializaSelectPais : function(nome_registro, nome_campo, inclui_Brasil, mensagem) {
		var lista = paises.listados();
		var insert_inicial = "";
		if (!inclui_Brasil) {
			if (util.isEmpty(mensagem)) {
				msgSelecione = "Selecione";
			} else {
				msgSelecione = mensagem;
			}
			insert_inicial = "<option value='-1'>" + msgSelecione + "</option>\n";
		}
		$.each(lista, function(index, item) {
			if (inclui_Brasil || (item != "Brasil")) {
				var select_Brasil = "";
				if (item == "Brasil") {
					select_Brasil = " selected";
					app.setAtributo(nome_registro, index + 1);
				}
				insert_inicial += "<option value='" + (index + 1) + "'" + select_Brasil + ">" + item + "</option>\n";
			}
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	inicializaSelectMunicipio : function(nome_campo, uf_sigla, mensagem) {
		var lista = lista_municipios[uf_sigla];
		var insert_inicial = "<option value='-1'>" + mensagem + "</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + item.id + "|" + item.geocod + "'>" + item.nome + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	inicializaSelectCargaRiscoOnu : function(nome_campo, mensagem, lista) {
		var insert_inicial = "<option value='-1'>" + mensagem + "</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + item.id + "'>" + item.numeroid + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	inicializaPlacaLetras : function(tipo_fluxo) {
		$('.input_placa').keyup(function() {
			var input_placa = $(this);
			var regex = new RegExp("[^a-zA-Z]+");
			input_placa.val(input_placa.val().replace(regex, '').toUpperCase());
			if (input_placa.val().length >= 3) {
				$('#placa_numeros_' + tipo_fluxo).focus();
			}
		});
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

	progressoRadioPlacaEstrangeira : function(tipo_fluxo) {
		$('#placa_estrangeira_' + tipo_fluxo + '_sim').click(function() {
			$('#grupo_pais_' + tipo_fluxo).show();
			$('#grupo_placa_' + tipo_fluxo).hide();
			app.setAtributo('placaEstrangeira', true);
			app.setAtributo("placa_letras", null);
			app.setAtributo("placa_numeros", null);
			$('#placa_letras_' + tipo_fluxo).val(null);
			$('#placa_numeros_' + tipo_fluxo).val(null);

			if (Number($('#pais_' + tipo_fluxo).val()) == -1) {
				$("#grupo_placa_unica_" + tipo_fluxo).hide();
			}
		});
		$('#placa_estrangeira_' + tipo_fluxo + '_nao').click(function() {
			$('#grupo_pais_' + tipo_fluxo).hide();
			$('#grupo_placa_unica_' + tipo_fluxo).hide();
			if ((registro.placaEstrangeira == undefined) || (registro.placaEstrangeira)) {
				$('#grupo_placa_numeros_' + tipo_fluxo).hide();
			}
			app.setAtributo('idPaisPlacaEstrangeira', null);
			app.setAtributo('placaEstrangeira', false);
			app.setAtributo('placa_unica', null);
			$('#placa_unica_' + tipo_fluxo).val(null);
			util.progressoRestartSelect("pais_" + tipo_fluxo, "Selecione");
			$("#grupo_placa_" + tipo_fluxo).show();
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

	progressoSelectPais : function(nome_registro, nome_campo, proximo_imediato, proximo_imediato2, grupo_proximo, fluxo) {
		$('#' + nome_campo).change(function() {
			grupo_proximo_imediato = "grupo_" + proximo_imediato + "_" + fluxo;
			grupo_proximo_imediato2 = "grupo_" + proximo_imediato2 + "_" + fluxo;
			if (Number($(this).val()) == -1) {
				$("#" + grupo_proximo_imediato).hide();
				$("#" + grupo_proximo_imediato2).hide();
				$("#" + grupo_proximo).hide();
				app.setAtributo(nome_registro, null);
				app.setAtributo(proximo_imediato, null);
				app.setAtributo(proximo_imediato2, null);
			} else if (Number($(this).val()) != 1) { // País diferente de Brasil
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

	progressoRestartSelect : function(nome_campo, mensagem) {
		$("#" + nome_campo + " option:contains('" + mensagem + "')").prop({
			selected : true
		});
		$("select#" + nome_campo).selectmenu("refresh", true);
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
		$('#' + nome_campo).keyup(function() {
			if (util.isEmpty($(this).val())) {
				$("#" + grupo_proximo).hide();
			} else {
				$("#" + grupo_proximo).show();
			}
		});
		$('#' + nome_campo).change(function() {
			app.setAtributo(nome_registro, $(this).val());
		});
	},

	// Funções para validação dos componentes
	validaSelect : function(nome_campo, campo_aviso) {
		if (Number($('#' + nome_campo).val()) != -1) {
			return true;
		} else {
			util.alerta_msg(campo_aviso);
			return false;
		}
	},

	validaRadioSimNao : function(nome_campo, campo_aviso) {
		var option = $('input[name=' + nome_campo + ']:checked').val();
		if (option != null) {
			return true;
		} else {
			util.alerta_msg(campo_aviso);
			return false;
		}
	},

	validaRadioChecked : function(nome_campo, campo_aviso) {
		var option = $('input[name=' + nome_campo + ']').val();
		if (option != null) {
			return true;
		} else {
			util.alerta_msg(campo_aviso);
			return false;
		}
	},

	validaInputText : function(nome_campo, campo_aviso) {
		var value = $.trim($('#' + nome_campo).val())
		if (value.length > 0) {
			return true;
		} else {
			util.alerta_msg(campo_aviso);
			return false;
		}
	},

	validaInputNumberRange : function(nome_campo, campo_aviso, min, max) {
		var value = $.trim($('#' + nome_campo).val())
		if ((!util.isEmpty(min) && !util.isEmpty(max) && Number(value) >= min && Number(value) <= max)
				|| (!util.isEmpty(min) && util.isEmpty(max) && Number(value) >= min)
				|| (!util.isEmpty(max) && util.isEmpty(min) && Number(value) <= max)) {
			return true;
		} else if (!util.isEmpty(min) || !util.isEmpty(max)) {
			var msgComplemento;
			if (!util.isEmpty(min) && !util.isEmpty(max)) {
				if (min >= max) {
					msgComplemento = "O valor do campo não deve ser diferente de " + min;
				} else {
					msgComplemento = "O valor do campo deve estar entre " + min + " e " + max;
				}
			} else if (!util.isEmpty(min)) {
				msgComplemento = "O menor valor posível para o campo é " + min;
			} else if (!util.isEmpty(max)) {
				msgComplemento = "O maior valor posível para o campo é " + max;
			}
			util.alerta_msg(campo_aviso, msgComplemento);
			return false;
		} else {
			return validaInputText(nome_campo, campo_aviso);
		}
	},

	// Outras funções
	alerta_msg : function(campo_aviso, msgComplemento) {
		if (util.isEmpty(msgComplemento)) {
			alert("O campo " + campo_aviso + " não foi preenchido.", "Erro no preenchimento!", null, 'error');
		} else {
			alert("O campo " + campo_aviso + " não foi preenchido corretamente.<br />" + msgComplemento,
					'Erro no preenchimento!', null, 'error');
		}
	},

	isEmpty : function(valor) {
		return (valor == undefined) || (valor == null) || (String(valor).trim().length == 0);
	},

	isFunction : function(functionToCheck) {
		var getType = {};
		return functionToCheck && getType.toString.call(functionToCheck) === '[object Function]';
	},

	getTimeInSeconds : function(date) {
		return Math.floor(date / 1000);
	}

};
