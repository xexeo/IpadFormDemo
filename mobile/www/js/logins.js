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
				// var i = Number(posto) - 1;
				for (i = 0; i < logins.users.length; i++) {
					/* comentar o 'for' se o sequencial do posto for igual ao posicionamento dele na lista. */
					var login = logins.users[i];
					if ((posto == login.usr) && (senha == login.pwd)) {
						return true;
					}
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
		pwd : "123", // senha admin
		perguntaExtra : true
	},

	users : [
	// usr = número do posto
	// pwd = 4 últimos números do SNV
	{
		usr : '002',
		pwd : '0808',
		fase : '1',
		ladoA : 'LUCAS DO RIO VERDE',
		ladoB : 'INÍCIO DA TRAVESSIA URBANA DE SORRISO'
	}, {
		usr : '048',
		pwd : '0324',
		fase : '1',
		ladoA : 'VILA SÃO PEDRO',
		ladoB : 'ACESSO COLÔNIA AGRÍCOLA SEXTA LINHA'
	}, {
		usr : '049',
		pwd : '0450',
		fase : '1',
		ladoA : 'ENTR MS-441(B) (P/BANDEIRANTES)',
		ladoB : 'ENTR MS-244 (P/BONFIM)'
	}, {
		usr : '052',
		pwd : '0030',
		fase : '1',
		ladoA : 'ENTR BR-070(B)',
		ladoB : 'ENTR MT-170 (CARAMÚJO)'
	}, {
		usr : '053',
		pwd : '0592',
		fase : '1',
		ladoA : 'ENTR MT-270(B)',
		ladoB : 'ENTR MT-469(A)'
	}, {
		usr : '066',
		pwd : '2710',
		fase : '1',
		ladoA : 'DIV SP/PR (CAB NORTE PONTE S/ RIO PARDINHO)',
		ladoB : 'INÍCIO VARIANTE DO ALPINO(PISTA DIREITA)'
	}, {
		usr : '067',
		pwd : '2810',
		fase : '1',
		ladoA : 'ENTR PR-427 (CAMPO DO TENENTE)',
		ladoB : 'DIV PR/SC (RIO NEGRO/MAFRA)'
	}, {
		usr : '068',
		pwd : '0205',
		fase : '1',
		ladoA : 'ACESSO GUARAPUAVA',
		ladoB : 'ENTR BR-466 (P/GUARAPUAVA)'
	}, {
		usr : '069',
		pwd : '0310',
		fase : '1',
		ladoA : 'ENTR PR-182 (P/CAPITÃO LEÔNIDAS MARQUES)',
		ladoB : 'ENTR BR-163'
	}, {
		usr : '071',
		pwd : '0490',
		fase : '1',
		ladoA : 'ENTR PR-281 (P/TIJUCAS DO SUL)',
		ladoB : 'DIV PR/SC (ENTR BR-101)'
	}, {
		usr : '072',
		pwd : '0095',
		fase : '1',
		ladoA : 'ENTR PR-510/511 (P/CONTENDA)',
		ladoB : 'ENTR PR-428 (LAPA)'
	}, {
		usr : '084',
		pwd : '1150',
		fase : '1',
		ladoA : 'ENTR BR-472 (P/PALMITINHO)',
		ladoB : 'ENTR RS-585/587 (SEBERI)'
	}, {
		usr : '085',
		pwd : '1310',
		fase : '1',
		ladoA : 'ENTR RS-348 (VAL DE SERRA)',
		ladoB : 'ENTR RS-509 (P/SANTA MARIA)'
	}, {
		usr : '086',
		pwd : '0180',
		fase : '1',
		ladoA : 'ACESSO A MINAS DO LEÃO',
		ladoB : 'ENTR BR-471 (PANTANO GRANDE)'
	}, {
		usr : '087',
		pwd : '1840',
		fase : '1',
		ladoA : 'ENTR RS-705 (P/GERIBA)',
		ladoB : 'ENTR BR-290(B)'
	}, {
		usr : '088',
		pwd : '0410',
		fase : '1',
		ladoA : 'ENTR BR-293(A)',
		ladoB : 'ENTR BR-472(A)'
	}, {
		usr : '089',
		pwd : '0130',
		fase : '1',
		ladoA : 'ENTR BR-473 (P/BAGÉ)',
		ladoB : 'ENTR RS-630 (P/DOM PEDRITO)'
	}, {
		usr : '090',
		pwd : '0070',
		fase : '1',
		ladoA : 'ENTR BR-471(A) (QUINTA)',
		ladoB : 'ACESSO PELOTAS'
	}, {
		usr : '091',
		pwd : '0210',
		fase : '1',
		ladoA : 'ACESSO LESTE A  ITAQUI',
		ladoB : 'ENTR BR-290(A)/293(A) (URUGUAIANA)'
	}, {
		usr : '092',
		pwd : '3830',
		fase : '1',
		ladoA : 'GARUVA',
		ladoB : 'ENTR SC-430 (PIRABEIRABA)'
	}, {
		usr : '093',
		pwd : '4010',
		fase : '1',
		ladoA : 'BALNEÁRIO DE CAMBORIÚ',
		ladoB : 'P/PORTO BELO'
	}, {
		usr : '094',
		pwd : '4125',
		fase : '1',
		ladoA : 'PONTE SOBRE O RIO DA MADRE',
		ladoB : 'PONTE SOBRE O RIO ARAÇATUBA'
	}, {
		usr : '095',
		pwd : '4240',
		fase : '1',
		ladoA : 'ENTR BR-285(A) (ARARANGUÁ)',
		ladoB : 'ENTR BR-285(B)/SC-285 (SANGA DA TOCA)'
	}, {
		usr : '099',
		pwd : '0377',
		fase : '1',
		ladoA : 'ENTR SC-157(B) (P/MODELO)',
		ladoB : 'ENTR BR-158(A) (P/CUNHA PORÃ)'
	}, {
		usr : '112',
		pwd : '0025',
		fase : '1',
		ladoA : 'ENTR PR-508 (P/MATINHOS)',
		ladoB : 'ENTR BR-101/PR-408 (P/MORRETES)'
	}, {
		usr : '113',
		pwd : '0075',
		fase : '1',
		ladoA : 'ACESSO SANTA',
		ladoB : 'ENTR BR-376(B)/PR-428 (SÃO LUIS PURUNÃ)'
	}, {
		usr : '114',
		pwd : '0950',
		fase : '1',
		ladoA : 'ENTR MS-375 (ZUZU)',
		ladoB : 'ENTR BR-163(A) (NOVA ALVORADA DO SUL)'
	}, {
		usr : '115',
		pwd : '0440',
		fase : '0',
		ladoA : 'ENTR MS-434 (RAIMUNDO)',
		ladoB : 'ENTR BR-483/497 (INÍCIO DA PISTA DUPLA)'
	}, {
		usr : '118',
		pwd : '0040',
		fase : '0',
		ladoA : 'ENTR BR-101',
		ladoB : 'ENTR SC-108(A) (NEUDOR)'
	}, {
		usr : '119',
		pwd : '1490',
		fase : '0',
		ladoA : 'ENTR BR-280(A)/PR-446',
		ladoB : 'ENTR PR-170'
	}, {
		usr : '121',
		pwd : '0834',
		fase : '0',
		ladoA : 'ENTR MT-423',
		ladoB : 'ITAUBA'
	}, {
		usr : '207',
		pwd : '0612',
		fase : '0',
		ladoA : 'ENTR BR-267(A)',
		ladoB : 'INÍCIO DUPLICAÇÃO'
	}, {
		usr : '209',
		pwd : '1390',
		fase : '0',
		ladoA : 'ENTR MS-339/446/448 (MIRANDA)',
		ladoB : 'ENTR MS-185/243 (GUAICURUS)'
	}, {
		usr : '210',
		pwd : '0058',
		fase : '0',
		ladoA : 'BURITIZINHO',
		ladoB : 'ENTR MS-223(A) (SILVOLÂNDIA)'
	}, {
		usr : '211',
		pwd : '0390',
		fase : '0',
		ladoA : 'ENTR MS-316 (PARAÍSO DAS ÁGUAS)',
		ladoB : 'ENTR MS-324(A)',
		perguntaExtra : true
	}, {
		usr : '214',
		pwd : '0330',
		fase : '0',
		ladoA : 'ENTR MT-373',
		ladoB : 'ENTR MT-130(A) (PRIMAVERA DO LESTE)'
	}, {
		usr : '232',
		pwd : '0180',
		fase : '0',
		ladoA : 'ENTR PR-549 (P/LUIZIANA)',
		ladoB : 'ENTR PR-462 (P/IRETAMA)'
	}, {
		usr : '234',
		pwd : '0250',
		fase : '0',
		ladoA : 'ENTR BR-158(B) (LARANJEIRAS DO SUL)',
		ladoB : 'ENTR PR-473'
	}, {
		usr : '235',
		pwd : '0455',
		fase : '0',
		ladoA : 'ACESSO ÁGUA MINERAL SANTA CLARA',
		ladoB : 'ENTR PR-281(A) (P/CHOPINZINHO)'
	}, {
		usr : '236',
		pwd : '0500',
		fase : '0',
		ladoA : 'ENTR PR-468(B)',
		ladoB : 'ENTR PR-180 (GOIO ERÊ)'
	}, {
		usr : '237',
		pwd : '1230',
		fase : '0',
		ladoA : 'ENTR PR-431(B)',
		ladoB : 'ENTR PR-092(A) (P/BARRA DO JACARÉ)'
	}, {
		usr : '240',
		pwd : '0710',
		fase : '0',
		ladoA : 'ACESSO PIRAPÓ',
		ladoB : 'ACESSO CAMBIRA'
	}, {
		usr : '262',
		pwd : '0132',
		fase : '0',
		ladoA : 'PADRONAL',
		ladoB : 'RIO 12 DE OUTUBRO'
	}, {
		usr : '263',
		pwd : '4425',
		fase : '0',
		ladoA : 'ENTR ACESSO SUL DE CAPÃO DA CANOA',
		ladoB : 'ENTR BR-290(A) (OSÓRIO)'
	}, {
		usr : '264',
		pwd : '0330',
		fase : '0',
		ladoA : 'ENTR BR-470',
		ladoB : 'ENTR BR-470/116(A) (CANOAS)'
	}, {
		usr : '265',
		pwd : '0210',
		fase : '0',
		ladoA : 'ENTR BR-153(B)/RS-324 (P/PONTÃO)',
		ladoB : 'ENTR BR-377(A)/386 (CARAZINHO)'
	}, {
		usr : '266',
		pwd : '0470',
		fase : '0',
		ladoA : 'ENTR RS-168 (P/SÃO PAULO DAS MISSÕES)',
		ladoB : 'ENTR BR-472 (FRONT BRASIL/ARGENTINA) (PORTO XAVIER)'
	}, {
		usr : '267',
		pwd : '0140',
		fase : '0',
		ladoA : 'A. BELTRÃO',
		ladoB : 'ENTR BR-287 (SANTIAGO)'
	}, {
		usr : '268',
		pwd : '1850',
		fase : '0',
		ladoA : 'ENTR BR-290(B)',
		ladoB : 'ENTR BR-392'
	}, {
		usr : '269',
		pwd : '1350',
		fase : '0',
		ladoA : 'ENTR BR-290(A) (P/ROSÁRIO DO SUL)',
		ladoB : 'ENTR RS-640 (P/CACEQUI)'
	}, {
		usr : '270',
		pwd : '0230',
		fase : '0',
		ladoA : 'ENTR BR-473 (SARANDI)',
		ladoB : 'CURRAL ALTO'
	}, {
		usr : '271',
		pwd : '0310',
		fase : '0',
		ladoA : 'ENTR RS-476 (LAJEADO GRANDE)',
		ladoB : 'ENTR RS-110 (VÁRZEA DO CEDRO)'
	}, {
		usr : '272',
		pwd : '0015',
		fase : '0',
		ladoA : 'ENTR BR-282(B)/470(A)',
		ladoB : 'ENTR SC-455 (P/IBICUÍ)'
	}, {
		usr : '273',
		pwd : '3010',
		fase : '0',
		ladoA : 'DIV SC/RS (RIO PELOTAS)',
		ladoB : 'ENTR BR-285(A) (P/VACARIA)'
	}, {
		usr : '274',
		pwd : '0110',
		fase : '0',
		ladoA : 'ENTR BR-486 (BOM RETIRO)',
		ladoB : 'ENTR SC-345(B) (P/URUBICI)'
	}, {
		usr : '275',
		pwd : '0027',
		fase : '0',
		ladoA : 'ENTR SC-473 (P/ANCHIETA)',
		ladoB : 'SÃO JOSÉ DO CEDRO'
	}, {
		usr : '295',
		pwd : '0370',
		fase : '0',
		ladoA : 'ENTR BR-153 (P/TIBAGI/IPIRANGA)',
		ladoB : 'ENTR BR-373(A)/487(A) (CAETANO)'
	}, {
		usr : '296',
		pwd : '0390',
		fase : '0',
		ladoA : 'RIO ANHANDUÍ',
		ladoB : 'ENTR BR-262(A) (CAMPO GRANDE)'
	}, {
		usr : '299',
		pwd : '0110',
		fase : '0',
		ladoA : 'ENTR SC-418 (P/POMERODE)',
		ladoB : 'ENTR BR-477(B) (P/TIMBÓ)'
	}, {
		usr : '300',
		pwd : '0110',
		fase : '0',
		ladoA : 'ENTR BR-116(B)',
		ladoB : 'P/TRES BARRAS'
	} ]
};
