var myDialogs = {

	/**
	 * Shows an alert dialog
	 * @param String txt Message
	 * @param Strint title Dialog Title
	 * @param Function cb callback function
	 * @param String type optional dialog type ['info', 'error']
	 */
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
	
	/**
	 * Shows a confirmation dialog
	 * @param String txt Message
	 * @param Function cbOK Ok button click callback
	 * @param Function cbCancel Cancel button click callback
	 * @param String title Dialog title
	 * @param String btnOK Ok button text
	 * @param String btnCancel Cancel button text
	 */
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
	
	/**
	 * Shows a prompt dialog
	 * @param String txt Message
	 * @param Function cb Callback function that receives the entered data
	 * @param String title Dialog title
	 * @param String btnOK Ok button text
	 * @param String btnCancel Cancel button text
	 * @param String placeholder Placeholder text for the input box
	 * @param String inputType Type of the input -> ['text', 'password', 'number']
	 */
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


