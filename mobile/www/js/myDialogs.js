/* global util */
/// @file myDialogs.js
/// @namespace myDialogs
/// Janelas de diálogo
var myDialogs = {

	/// @function myDialogs.alert
	/// Mostra uma janela de alerta
	/// @param {string} txt		mensagem
	/// @param {string} title	título da janela
	/// @param {function} cb	função _callback_
	/// @param {string} type	['info', 'error']
	/// @return {void} função sem retorno
	alert : function (txt, title, cb, type){
		title = (title == null)? "Aviso!" : title;
		var icon;
		var btn;
		switch (type){
			case 'info' :
				icon = 'fa fa-info-circle';
				btn = 'btn-warning';
				break;
			case 'error' :
				icon = 'fa fa-exclamation-triangle';
				btn = 'btn-danger';
				break;
			default:
				icon = null;
				btn = 'btn-info';
				
		}
		var d = $.alert({
			title : title,
			content : txt,
			icon : icon,
			confirmButton : "Ok",
			confirmButtonClass : btn,
			animation : 'none',
			closeAnimation: 'none',
			animationBounce : 1, //1 is none
			confirm : function(){
				if(util.isFunction(cb)){
					cb();
				}
			},
			
		});
		d.$body.addClass("ui-page-theme-a").css('margin-top', '0px');
		
	},
	
	
	/// @function myDialogs.confirm
	/// Monstra uma janela da confirmação
	/// @param {string} txt			mensagem
	/// @param {function} cbOK		função _callback_ disparada ao clicar no botão OK
	/// @param {function} cbCancel	função _callback_ disparada ao clicar no botão Cancel
	/// @param {string} title		título da janela
	/// @param {string} btnOK		texto do botão OK
	/// @param {string} btnCancel	texto do botão Cancel
	/// @return {void} função sem retorno
	confirm : function(txt, cbOK, cbCancel, title, btnOK, btnCancel){
		title = (title == null)? "Confirmação." : title;
		btnOK = (btnOK == null)? "Confirmar" : btnOK;
		btnCancel = (btnCancel == null)? "Cancelar" : btnCancel;
		$.confirm({
			title : title,
			content: txt,
			confirmButton : btnOK,
			confirmButtonClass : "btn-primary",//"ui-btn ui-corner-all ui-btn-b",
			cancelButton : btnCancel,
			cancelButtonClass : "btn-info",//"ui-btn ui-corner-all ui-btn-b",
			animation : 'none',
			closeAnimation: 'none',
			animationBounce : 1, //1 is none
			confirm : function(){
				if(util.isFunction(cbOK)){
					cbOK();
				}
			},
			cancel: function(){
				if(util.isFunction(cbCancel)){
					cbCancel();
				}
			}
		}).$body.addClass("ui-page-theme-a")/*8.children(".buttons").css("width","100%")*/;
	},
	
	/// @function myDialogs.prompt
	/// Monstra uma janela de diálogo com um campo para entrada de dados
	/// @param {string} txt			mensagem
	/// @param {function} cb		função _callback_ que recebe o dado informado
	/// @param {string} title		título da janela
	/// @param {string} btnOK		texto do botão OK
	/// @param {string} btnCancel	texto do botão Cancel
	/// @param {string} placeholder	texto _placeholder_ do campo de entrada de dados
	/// @param {string} inputType	['text', 'password', 'number']
	/// @return {void} função sem retorno
	prompt : function(txt, cb, title, btnOK, btnCancel, placeholder, inputType){
		title = (title == null)? "Entrada de dados." : title;
		btnOK = (btnOK == null)? "Entrar" : btnOK;
		btnCancel = (btnCancel == null)? "Cancelar" : btnCancel;
		placeholder = (placeholder == null)? "Entre com os dados" : placeholder;
		if (inputType != 'text' && inputType != 'password' && inputType != 'number'){ 
			inputType = 'text';
		}
		var dialog = $.confirm({
			title : title,
			content : txt,
			confirmButton : btnOK,
			confirmButtonClass : "btn-primary",//"ui-btn ui-corner-all ui-btn-b",
			cancelButton : btnCancel,
			cancelButtonClass : "btn-info",//"ui-btn ui-corner-all ui-btn-b",
			animation : 'none',
			closeAnimation: 'none',
			animationBounce : 1, //1 is none
			confirm : function(){
				var enteredVal = this.$content.find('input').val(); // get the input value.
				if (enteredVal.trim() == '') { // validate it.
					return false; // dont close the dialog. (and maybe show some error.)
				}
				if(util.isFunction(cb)){
					cb(enteredVal);
				}
			},
		});
		dialog.$content.append('<p><input style="width:100%;" id="prompt_input" autofocus type="'+ 
                                inputType +'" placeholder="' + placeholder + '" data-theme="a"></p>');
        dialog.$content.find("#prompt_input").textinput();//textinput({theme: "a"});
		dialog.$body.addClass("ui-page-theme-a");
		//dialog.$body.children(".buttons").css("width","100%");
        
	},
	
};


