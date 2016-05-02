var logins = {

	autenticaMaster : function(usuario, senha) {
		if ((usuario == logins.user_master.usr) && (senha == logins.user_master.pwd)) {
			return true;
		}
		return false;
	},

	autentica : function(usuario, senha) {
		if ((logins.user_admin != undefined) && (logins.user_admin.usr != undefined) && (usuario == logins.user_admin.usr)
				&& (senha == logins.user_admin.pwd)) {
			return true;
		} else if (String(usuario).length == 5) {
			var regex = new RegExp("[^0-9]+");
			var posto = String(usuario).substr(0, 3).replace(regex, '');
			var sentido = String(usuario).substr(3, 2).toUpperCase();
			if ((posto.length == 3) && (Number(posto) > 0) && ((sentido == 'AB') || (sentido == 'BA'))) {
				if (sentido == 'BA') {
					senha = util.reverse(senha);
				}
				var i = Number(posto) - 1;
				// 'for' desnecessário já que o posto é um número sequencial e corresponde à posição-1 de cada login em 'users'
				// for (i = 0; i < logins.users.length; i++) {
				login = logins.users[i];
				if ((posto == login.usr) && (senha == login.pwd)) {
					return true;
				}
			}
		}
		return false;
	},

	user_master : { // Usuário exclusivo para configurar o idIpad na instalação, ele não faz login no aplicativo.
		usr : 'Master', // usuário mestre
		pwd : "Aaa" // senha mestra
	},

	user_admin : { // Usuário destinado aos testes do aplicativo. TODO: iremos removê-lo ao ir para produção?
		usr : 'admin', // usuário admin
		pwd : "123" // senha admin
	},

	users : [
	// usr = número do posto
	// pwd = 4 últimos números do SNV
	{
		usr : '001',
		pwd : '1590',
	}, {
		usr : '002',
		pwd : '0808',
	}, {
		usr : '003',
		pwd : '0834',
	}, {
		usr : '004',
		pwd : '0852',
	}, {
		usr : '005',
		pwd : '0670',
	}, {
		usr : '006',
		pwd : '0790',
	}, {
		usr : '007',
		pwd : '0890',
	} ]
};
