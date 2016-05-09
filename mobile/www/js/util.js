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
			if (inclui_Brasil || (item != 'Brasil')) {
				var select_Brasil = "";
				if (item == 'Brasil') {
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
			insert_inicial += "<option value='" + item.id + "'>" + item.nome + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	getIdxArray : function(elem, array) {
		return jQuery.inArray(elem, array) + 1;
	},

	getListaFrequencias : function() {
		var lista_frequencias = [ 'Dia', 'Semana', 'Mês', 'Ano', 'Eventualmente' ];
		return lista_frequencias;
	},

	inicializaSelectFrequencia : function(tipo_fluxo) {
		var lista_frequencias = util.getListaFrequencias();
		var nome_campo = 'frequencia_sel_' + tipo_fluxo;
		util.inicializaSelect(nome_campo, lista_frequencias);
		$("#" + nome_campo).change(function() {
			var freq_num = $('#frequencia_num_' + tipo_fluxo);
			if ($(this).val() == util.getIdxArray('Eventualmente', lista_frequencias)) {
				freq_num.val(1);
				app.setAtributo('frequenciaQtd', 1);
			}
		});
	},

	inicializaSelectCargaRiscoOnu : function(nome_campo, mensagem, lista) {
		var insert_inicial = "<option value='-1'>" + mensagem + "</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + item.id + "'>" + item.numeroid + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	inicializaSelectTipoDeCarga : function(nome_campo, mensagem, lista) {
		var insert_inicial = "<option value='-1'>" + mensagem + "</option>\n";
		$.each(lista, function(index, item) {
			insert_inicial += "<option value='" + item.id + "'>" + item.nomeProduto + "</option>\n";
		});
		$("#" + nome_campo).html(insert_inicial).selectmenu("refresh", true);
	},

	inicializaPlacas : function(tipo_fluxo) {
		// $('.input_placa').keyup(function() {
		// var input = $(this);
		// input.val(input.val().toUpperCase());
		// });
		$('#placa_letras_' + tipo_fluxo).keyup(function() {
			var input = $(this);
			var regex = new RegExp("[^a-zA-Z]+");
			input.val(input.val().replace(regex, ''));
			var value = input.val();
			if (util.isEmpty(value) || value.length < Number(input.attr("maxlength"))) {
				$('#grupo_placa_numeros_' + tipo_fluxo).hide();
				$('#placa_numeros_' + tipo_fluxo).val("");
				// app.setAtributo('placa_numeros', null);
			} else {
				app.setAtributo('placa_letras', input.val());
				$('#grupo_placa_numeros_' + tipo_fluxo).show();
				$('#placa_letras' + tipo_fluxo).trigger('change');
				$('#placa_numeros_' + tipo_fluxo).focus();
			}
			// $('#placa_letras_' + tipo_fluxo).change(function() {
			// app.setAtributo('placa_letras', $(this).val());
			// });
		});
		$('#placa_numeros_' + tipo_fluxo).keyup(function() {
			var input = $(this);
			var regex = new RegExp("[^0-9]+");
			input.val(input.val().replace(regex, ''));
			var max_len = Number(input.attr("maxlength"));
			if (input.val().length >= max_len) {
				input.val(input.val().substring(0, max_len));
				$('#placa_numeros_' + tipo_fluxo).trigger('change');
				// app.setAtributo('placa_numeros', input.val());
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

	progressoRadioSimNaoAlternado : function(nome_registro, nome_campo, grupo_proximo_sim, grupo_proximo_nao) {
		$('#' + nome_campo + '_sim').click(function() {
			$('#' + grupo_proximo_sim).show();
			$('#' + grupo_proximo_nao).hide();
			app.setAtributo(nome_registro, true);
		});
		$('#' + nome_campo + '_nao').click(function() {
			$('#' + grupo_proximo_sim).hide();
			$('#' + grupo_proximo_nao).show();
			app.setAtributo(nome_registro, false);
		});
	},

	progressoRadioPlacaEstrangeira : function(tipo_fluxo) {
		$('#placa_estrangeira_' + tipo_fluxo + '_sim').click(function() {
			$('#grupo_pais_' + tipo_fluxo).show();
			$('#grupo_placa_' + tipo_fluxo).hide();
			app.setAtributo('placaEstrangeira', true);
			app.setAtributo('placa_letras', null);
			app.setAtributo('placa_numeros', null);
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

	progressoCheckboxAlternado : function(nome_registro, nome_campo, grupo_proximo_check, grupo_proximo_uncheck) {
		$('#' + nome_campo).click(function() {
			if ($(this).is(':checked')) {
				$('#' + grupo_proximo_check).show();
				$('#' + grupo_proximo_uncheck).hide();
				app.setAtributo(nome_registro, true);
			} else {
				$('#' + grupo_proximo_check).hide();
				$('#' + grupo_proximo_uncheck).show();
				app.setAtributo(nome_registro, false);
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

	progressoSelectPais : function(nome_registro_pais, nome_registro_municipio, nome_campo, proximo_imediato, proximo_imediato2,
			grupo_proximo, fluxo) {
		$('#' + nome_campo).change(function() {
			grupo_proximo_imediato = "grupo_" + proximo_imediato + "_" + fluxo;
			grupo_proximo_imediato2 = "grupo_" + proximo_imediato2 + "_" + fluxo;
			if (Number($(this).val()) == -1) {
				$("#" + grupo_proximo_imediato).hide();
				$("#" + grupo_proximo_imediato2).hide();
				$("#" + grupo_proximo).hide();
				app.setAtributo(nome_registro_pais, null);
				app.setAtributo(proximo_imediato, null);
				app.setAtributo(nome_registro_municipio, null);
			} else if (Number($(this).val()) != 1) { // País diferente de Brasil
				$("#" + grupo_proximo_imediato).hide();
				$("#" + grupo_proximo_imediato2).hide();
				$("#" + grupo_proximo).show();
				app.setAtributo(nome_registro_pais, $(this).val());
				app.setAtributo(proximo_imediato, null);
				app.setAtributo(nome_registro_municipio, null);
			} else { // País é Brasil
				$("#" + grupo_proximo_imediato).show();
				$("#" + grupo_proximo).hide();
				app.setAtributo(nome_registro_pais, $(this).val());
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
	 * @param autocomplete
	 *            bool para indicar se é um campo autocomplete
	 */
	progressoInputText : function(nome_registro, nome_campo, grupo_proximo, autocomplete) {

		if (autocomplete) { // autocomplete
			$('#' + nome_campo).change(function() {
				progride($(this).val());
				app.setAtributo(nome_registro, $(this).val());
			});
		} else { // textinput comum
			$('#' + nome_campo).keyup(function() {
				progride($(this).val());
			});
			$('#' + nome_campo).change(function() {
				app.setAtributo(nome_registro, $(this).val());
			});
		}

		function progride(value) {
			if (util.isEmpty(value)) {
				$("#" + grupo_proximo).hide();
			} else {
				$("#" + grupo_proximo).show();
			}
		}
	},

	progressoInputTextLen : function(nome_registro, nome_campo, grupo_proximo) {
		$('#' + nome_campo).keyup(function() {
			var input = $(this);
			var max_len = Number(input.attr("maxlength"));
			if (progride(input.val(), max_len)) {
				input.val(String(input.val()).trim().substring(0, max_len));
				input.trigger('change');
			}
		});
		$('#' + nome_campo).change(function() {
			app.setAtributo(nome_registro, $(this).val());
		});

		function progride(value, min_len) {
			if (util.isEmpty(value) || (String(value).trim().length < min_len)) {
				$("#" + grupo_proximo).hide();
			} else {
				$("#" + grupo_proximo).show();
				return true;
			}
			return false;
		}
	},

	progressoInputMoney : function(nome_registro, nome_campo, grupo_proximo, autocomplete) {
		$('#' + nome_campo).keyup(function() {
			progride($(this).maskMoney('unmasked')[0]);
		});
		$('#' + nome_campo).change(function() {
			app.setAtributo(nome_registro, $(this).maskMoney('unmasked')[0]);
		});

		function progride(value) {
			if (util.isEmpty(value) || (Number(value) == 0)) {
				$("#" + grupo_proximo).hide();
			} else {
				$("#" + grupo_proximo).show();
			}
		}
	},

	progressoPlacaNumeros : function(tipo_fluxo, grupo_proximo) {
		util.progressoInputTextLen("placa_numeros", "placa_numeros_" + tipo_fluxo, grupo_proximo);
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
		var value = $.trim($('#' + nome_campo).val());
		var max_len = Number($.trim($('#' + nome_campo).attr("maxlength")))
		if (value.length > 0) {
			if ((max_len > 0) && (value.length > max_len)) {
				util.alerta_msg(campo_aviso, "O campo deve ter no máximo " + max_len + " caracteres.");
				return false;
			} else {
				return true;
			}
		} else {
			util.alerta_msg(campo_aviso);
			return false;
		}
	},

	validaLenInputText : function(nome_campo, campo_aviso) {
		var value = $.trim($('#' + nome_campo).val());
		var len = Number($.trim($('#' + nome_campo).attr("maxlength")));
		if (len > 0) {
			if (value.length != len) {
				util.alerta_msg(campo_aviso, "O campo deve ter exatamente " + len + " caracteres.");
				return false;
			}
		}
		return true;
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
				msgComplemento = "O menor valor possível do campo é " + min;
			} else if (!util.isEmpty(max)) {
				msgComplemento = "O maior valor possível do campo é " + max;
			}
			util.alerta_msg(campo_aviso, msgComplemento);
			return false;
		} else {
			return validaInputText(nome_campo, campo_aviso);
		}
	},

	isFilterRunning : false, // controla se o filtro já terminou

	autocomplete : function(nome_do_campo, lista, title, txt_content) {
		title = (title == null) ? "Busca" : title;
		txt_content = (txt_content == null) ? "Entre com o início da palavra." : txt_content;
		var field = $('#' + nome_do_campo);
		var overlayInput = $.confirm({
			title : title,
			content : txt_content,
			confirmButton : null,
			cancelButton : null,
			animation : 'none',
			closeAnimation : 'none',
			animationBounce : 1,
			icon : 'fa fa-search',
			closeIcon : true,
			onClose : function() {
				util.isFilterRunning = false;
			},

			isCentered : false,
		});
		overlayInput.$body.addClass("ui-page-theme-a");

		var extraHtml = '<form class="ui-filterable" > \
							<input id="filtro" type="text" autofocus data-type="search"> \
						</form> \
						<ul id="filtro_autocomplete" data-role="listview" data-filter="true" data-input="#filtro" data-inset="true"></ul>';
		overlayInput.$content.append(extraHtml);
		var txtInput = overlayInput.$content.find('#filtro');
		app.logger.log(txtInput);
		txtInput.textinput();
		/*
		 * txtInput.one('load', function(){ $(this).focus(); alert('tentou focar'); });
		 */
		overlayInput.$content.find('#filtro_autocomplete').listview();
		overlayInput.$b.css('margin-top', '0px');

		$("#filtro_autocomplete").on("filterablebeforefilter", function(e, data) {
			app.logger.log('entrou no filtro');
			var ul_list = $(this);
			var auto_input = $(data.input);
			var auto_value = auto_input.val();
			var html = "";
			var minLength = 2;// só pesquisa quando a entrada for maior
			var newText = "";
			var waitCicle = false;
			ul_list.html("");

			if (auto_value && auto_value.length > minLength) {
				if (!util.isFilterRunning) {
					util.isFilterRunning = true;
					doFilter(auto_value);
					util.isFilterRunning = false;
				} else {
					newText = auto_value;
					if (!waitCicle) {
						waitCicle = setInterval(function() {
							if (!util.isFilterRunning) {
								util.isFilterRunning = true;
								clearInterval(waitCicle);
								waitCicle = false;
								doFilter(newText);
								util.isFilterRunning = false;
							}
						}, 300);
					}
				}
			}

			function doFilter(inputedTxt) {

				ul_list.html("<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>");
				ul_list.listview("refresh");
				var matcher = new RegExp(util.escapeRegex(inputedTxt), "i");
				var res = $.grep(lista, function(value) {
					value = value.label || value.value || value.nome || value;
					return matcher.test(value) || matcher.test(util.replaceDiacritics(value));
				});
				app.logger.log(JSON.stringify(res), null, '\t');
				$.each(res, function(i, val) {
					if (val.id != null) { // municipio
						html += "<li li_id='" + val.id + "' >" + val.nome + "</li>";
					} else {
						html += "<li>" + val.nome + "</li>";
					}
					// overlayInput.setDialogDimension();
				});
				ul_list.html(html);
				ul_list.children("li").click(function() {
					var txt;
					if ($(this).attr('li_id') != null) { // municipio
						txt = $(this).html() + " | " + $(this).attr('li_id');
					} else {
						txt = $(this).html();
					}
					field.val(txt).trigger('change').scrollLeft(0);
					// overlayInput.$content.find('#filtro').focus();
					overlayInput.close();
				});
				ul_list.listview("refresh");
				ul_list.trigger("updatelayout");
			}

		});

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
	},

	alphabet : {
		a : /[\u0061\u24D0\uFF41\u1E9A\u00E0\u00E1\u00E2\u1EA7\u1EA5\u1EAB\u1EA9\u00E3\u0101\u0103\u1EB1\u1EAF\u1EB5\u1EB3\u0227\u01E1\u00E4\u01DF\u1EA3\u00E5\u01FB\u01CE\u0201\u0203\u1EA1\u1EAD\u1EB7\u1E01\u0105\u2C65\u0250]/ig,
		aa : /[\uA733]/ig,
		ae : /[\u00E6\u01FD\u01E3]/ig,
		ao : /[\uA735]/ig,
		au : /[\uA737]/ig,
		av : /[\uA739\uA73B]/ig,
		ay : /[\uA73D]/ig,
		b : /[\u0062\u24D1\uFF42\u1E03\u1E05\u1E07\u0180\u0183\u0253]/ig,
		c : /[\u0063\u24D2\uFF43\u0107\u0109\u010B\u010D\u00E7\u1E09\u0188\u023C\uA73F\u2184]/ig,
		d : /[\u0064\u24D3\uFF44\u1E0B\u010F\u1E0D\u1E11\u1E13\u1E0F\u0111\u018C\u0256\u0257\uA77A]/ig,
		dz : /[\u01F3\u01C6]/ig,
		e : /[\u0065\u24D4\uFF45\u00E8\u00E9\u00EA\u1EC1\u1EBF\u1EC5\u1EC3\u1EBD\u0113\u1E15\u1E17\u0115\u0117\u00EB\u1EBB\u011B\u0205\u0207\u1EB9\u1EC7\u0229\u1E1D\u0119\u1E19\u1E1B\u0247\u025B\u01DD]/ig,
		f : /[\u0066\u24D5\uFF46\u1E1F\u0192\uA77C]/ig,
		g : /[\u0067\u24D6\uFF47\u01F5\u011D\u1E21\u011F\u0121\u01E7\u0123\u01E5\u0260\uA7A1\u1D79\uA77F]/ig,
		h : /[\u0068\u24D7\uFF48\u0125\u1E23\u1E27\u021F\u1E25\u1E29\u1E2B\u1E96\u0127\u2C68\u2C76\u0265]/ig,
		hv : /[\u0195]/ig,
		i : /[\u0069\u24D8\uFF49\u00EC\u00ED\u00EE\u0129\u012B\u012D\u00EF\u1E2F\u1EC9\u01D0\u0209\u020B\u1ECB\u012F\u1E2D\u0268\u0131]/ig,
		j : /[\u006A\u24D9\uFF4A\u0135\u01F0\u0249]/ig,
		k : /[\u006B\u24DA\uFF4B\u1E31\u01E9\u1E33\u0137\u1E35\u0199\u2C6A\uA741\uA743\uA745\uA7A3]/ig,
		l : /[\u006C\u24DB\uFF4C\u0140\u013A\u013E\u1E37\u1E39\u013C\u1E3D\u1E3B\u017F\u0142\u019A\u026B\u2C61\uA749\uA781\uA747]/ig,
		lj : /[\u01C9]/ig,
		m : /[\u006D\u24DC\uFF4D\u1E3F\u1E41\u1E43\u0271\u026F]/ig,
		n : /[\u006E\u24DD\uFF4E\u01F9\u0144\u00F1\u1E45\u0148\u1E47\u0146\u1E4B\u1E49\u019E\u0272\u0149\uA791\uA7A5]/ig,
		nj : /[\u01CC]/ig,
		o : /[\u006F\u24DE\uFF4F\u00F2\u00F3\u00F4\u1ED3\u1ED1\u1ED7\u1ED5\u00F5\u1E4D\u022D\u1E4F\u014D\u1E51\u1E53\u014F\u022F\u0231\u00F6\u022B\u1ECF\u0151\u01D2\u020D\u020F\u01A1\u1EDD\u1EDB\u1EE1\u1EDF\u1EE3\u1ECD\u1ED9\u01EB\u01ED\u00F8\u01FF\u0254\uA74B\uA74D\u0275]/ig,
		oi : /[\u01A3]/ig,
		ou : /[\u0223]/ig,
		oo : /[\uA74F]/ig,
		p : /[\u0070\u24DF\uFF50\u1E55\u1E57\u01A5\u1D7D\uA751\uA753\uA755]/ig,
		q : /[\u0071\u24E0\uFF51\u024B\uA757\uA759]/ig,
		r : /[\u0072\u24E1\uFF52\u0155\u1E59\u0159\u0211\u0213\u1E5B\u1E5D\u0157\u1E5F\u024D\u027D\uA75B\uA7A7\uA783]/ig,
		s : /[\u0073\u24E2\uFF53\u015B\u1E65\u015D\u1E61\u0161\u1E67\u1E63\u1E69\u0219\u015F\u023F\uA7A9\uA785\u1E9B]/ig,
		ss : /[\u00DF\u1E9E]/ig,
		t : /[\u0074\u24E3\uFF54\u1E6B\u1E97\u0165\u1E6D\u021B\u0163\u1E71\u1E6F\u0167\u01AD\u0288\u2C66\uA787]/ig,
		tz : /[\uA729]/ig,
		u : /[\u0075\u24E4\uFF55\u00F9\u00FA\u00FB\u0169\u1E79\u016B\u1E7B\u016D\u00FC\u01DC\u01D8\u01D6\u01DA\u1EE7\u016F\u0171\u01D4\u0215\u0217\u01B0\u1EEB\u1EE9\u1EEF\u1EED\u1EF1\u1EE5\u1E73\u0173\u1E77\u1E75\u0289]/ig,
		v : /[\u0076\u24E5\uFF56\u1E7D\u1E7F\u028B\uA75F\u028C]/ig,
		vy : /[\uA761]/ig,
		w : /[\u0077\u24E6\uFF57\u1E81\u1E83\u0175\u1E87\u1E85\u1E98\u1E89\u2C73]/ig,
		x : /[\u0078\u24E7\uFF58\u1E8B\u1E8D]/ig,
		y : /[\u0079\u24E8\uFF59\u1EF3\u00FD\u0177\u1EF9\u0233\u1E8F\u00FF\u1EF7\u1E99\u1EF5\u01B4\u024F\u1EFF]/ig,
		z : /[\u007A\u24E9\uFF5A\u017A\u1E91\u017C\u017E\u1E93\u1E95\u01B6\u0225\u0240\u2C6C\uA763]/ig,
		'' : /[\u0300\u0301\u0302\u0303\u0308]/ig
	},

	replaceDiacritics : function(str) {
		for ( var letter in util.alphabet) {
			str = str.replace(util.alphabet[letter], letter);
		}
		return str;
	},

	escapeRegex : function(value) {
		return value.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, "\\$&");
	},

	reverse : function(s) {
		var o = [];
		for (var i = 0, len = s.length; i <= len; i++)
			o.push(s.charAt(len - i));
		return o.join('');
	}

};
