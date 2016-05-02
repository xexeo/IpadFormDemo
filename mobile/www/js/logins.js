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
	}, {
		usr : '008',
		pwd : '0950',
	}, {
		usr : '009',
		pwd : '0260',
	}, {
		usr : '010',
		pwd : '0090',
	}, {
		usr : '011',
		pwd : '0750',
	}, {
		usr : '012',
		pwd : '1810',
	}, {
		usr : '013',
		pwd : '0690',
	}, {
		usr : '014',
		pwd : '1695',
	}, {
		usr : '015',
		pwd : '0790',
	}, {
		usr : '016',
		pwd : '0334',
	}, {
		usr : '017',
		pwd : '0270',
	}, {
		usr : '018',
		pwd : '0934',
	}, {
		usr : '019',
		pwd : '1010',
	}, {
		usr : '020',
		pwd : '0138',
	}, {
		usr : '021',
		pwd : '0290',
	}, {
		usr : '022',
		pwd : '0300',
	}, {
		usr : '023',
		pwd : '0245',
	}, {
		usr : '024',
		pwd : '0550',
	}, {
		usr : '025',
		pwd : '0530',
	}, {
		usr : '026',
		pwd : '0690',
	}, {
		usr : '027',
		pwd : '0190',
	}, {
		usr : '028',
		pwd : '0610',
	}, {
		usr : '029',
		pwd : '0090',
	}, {
		usr : '030',
		pwd : '0050',
	}, {
		usr : '031',
		pwd : '0606',
	}, {
		usr : '032',
		pwd : '0070',
	}, {
		usr : '033',
		pwd : '0155',
	}, {
		usr : '034',
		pwd : '0250',
	}, {
		usr : '035',
		pwd : '2195',
	}, {
		usr : '036',
		pwd : '2130',
	}, {
		usr : '037',
		pwd : '0300',
	}, {
		usr : '038',
		pwd : '1990',
	}, {
		usr : '039',
		pwd : '2470',
	}, {
		usr : '040',
		pwd : '0030',
	}, {
		usr : '041',
		pwd : '0070',
	}, {
		usr : '042',
		pwd : '0015',
	}, {
		usr : '043',
		pwd : '0130',
	}, {
		usr : '044',
		pwd : '0110',
	}, {
		usr : '045',
		pwd : '0125',
	}, {
		usr : '046',
		pwd : '0160',
	}, {
		usr : '047',
		pwd : '0470',
	}, {
		usr : '048',
		pwd : '0090',
	}, {
		usr : '049',
		pwd : '0050',
	}, {
		usr : '050',
		pwd : '0080',
	}, {
		usr : '051',
		pwd : '0112',
	}, {
		usr : '052',
		pwd : '0550',
	}, {
		usr : '053',
		pwd : '0340',
	}, {
		usr : '054',
		pwd : '0190',
	}, {
		usr : '055',
		pwd : '0770',
	}, {
		usr : '056',
		pwd : '0750',
	}, {
		usr : '057',
		pwd : '0050',
	}, {
		usr : '058',
		pwd : '0510',
	}, {
		usr : '059',
		pwd : '0570',
	}, {
		usr : '060',
		pwd : '0440',
	}, {
		usr : '061',
		pwd : '0050',
	}, {
		usr : '062',
		pwd : '0280',
	}, {
		usr : '063',
		pwd : '0150',
	}, {
		usr : '064',
		pwd : '0860',
	}, {
		usr : '065',
		pwd : '0300',
	}, {
		usr : '066',
		pwd : '0680',
	}, {
		usr : '067',
		pwd : '0730',
	}, {
		usr : '068',
		pwd : '0670',
	}, {
		usr : '069',
		pwd : '0750',
	}, {
		usr : '070',
		pwd : '0240',
	}, {
		usr : '071',
		pwd : '0360',
	}, {
		usr : '072',
		pwd : '0290',
	}, {
		usr : '073',
		pwd : '0130',
	}, {
		usr : '074',
		pwd : '0970',
	}, {
		usr : '075',
		pwd : '0040',
	}, {
		usr : '076',
		pwd : '0030',
	}, {
		usr : '077',
		pwd : '0335',
	}, {
		usr : '078',
		pwd : '0570',
	}, {
		usr : '079',
		pwd : '0510',
	}, {
		usr : '080',
		pwd : '0130',
	}, {
		usr : '081',
		pwd : '0250',
	}, {
		usr : '082',
		pwd : '0250',
	}, {
		usr : '083',
		pwd : '0152',
	}, {
		usr : '084',
		pwd : '0010',
	}, {
		usr : '085',
		pwd : '0310',
	}, {
		usr : '086',
		pwd : '0230',
	}, {
		usr : '087',
		pwd : '1130',
	}, {
		usr : '088',
		pwd : '0110',
	}, {
		usr : '089',
		pwd : '0110',
	}, {
		usr : '090',
		pwd : '1280',
	}, {
		usr : '091',
		pwd : '1310',
	}, {
		usr : '092',
		pwd : '0210',
	}, {
		usr : '093',
		pwd : '1195',
	}, {
		usr : '094',
		pwd : '0010',
	}, {
		usr : '095',
		pwd : '0030',
	}, {
		usr : '096',
		pwd : '1370',
	}, {
		usr : '097',
		pwd : '0850',
	}, {
		usr : '098',
		pwd : '0315',
	}, {
		usr : '099',
		pwd : '0195',
	}, {
		usr : '100',
		pwd : '0330',
	}, {
		usr : '101',
		pwd : '0870',
	}, {
		usr : '102',
		pwd : '0910',
	}, {
		usr : '103',
		pwd : '0890',
	}, {
		usr : '104',
		pwd : '0250',
	}, {
		usr : '105',
		pwd : '0930',
	}, {
		usr : '106',
		pwd : '0230',
	}, {
		usr : '107',
		pwd : '0070',
	}, {
		usr : '108',
		pwd : '0030',
	}, {
		usr : '109',
		pwd : '0190',
	}, {
		usr : '110',
		pwd : '0670',
	}, {
		usr : '111',
		pwd : '0350',
	}, {
		usr : '112',
		pwd : '0590',
	}, {
		usr : '113',
		pwd : '0220',
	}, {
		usr : '114',
		pwd : '0830',
	}, {
		usr : '115',
		pwd : '0100',
	}, {
		usr : '116',
		pwd : '0205',
	}, {
		usr : '117',
		pwd : '0090',
	}, {
		usr : '118',
		pwd : '0150',
	}, {
		usr : '119',
		pwd : '0370',
	}, {
		usr : '120',
		pwd : '0410',
	}, {
		usr : '121',
		pwd : '0830',
	}, {
		usr : '122',
		pwd : '0230',
	}, {
		usr : '123',
		pwd : '0170',
	}, {
		usr : '124',
		pwd : '0370',
	}, {
		usr : '125',
		pwd : '0170',
	}, {
		usr : '126',
		pwd : '0490',
	}, {
		usr : '127',
		pwd : '0685',
	}, {
		usr : '128',
		pwd : '0050',
	}, {
		usr : '129',
		pwd : '0750',
	}, {
		usr : '130',
		pwd : '0270',
	}, {
		usr : '131',
		pwd : '0023',
	}, {
		usr : '132',
		pwd : '0270',
	}, {
		usr : '133',
		pwd : '0325',
	}, {
		usr : '134',
		pwd : '0212',
	}, {
		usr : '135',
		pwd : '0612',
	}, {
		usr : '136',
		pwd : '0090',
	}, {
		usr : '137',
		pwd : '0450',
	}, {
		usr : '138',
		pwd : '1390',
	}, {
		usr : '139',
		pwd : '0058',
	}, {
		usr : '140',
		pwd : '0390',
	}, {
		usr : '141',
		pwd : '1290',
	}, {
		usr : '142',
		pwd : '0880',
	}, {
		usr : '143',
		pwd : '0180',
	}, {
		usr : '144',
		pwd : '0050',
	}, {
		usr : '145',
		pwd : '0030',
	}, {
		usr : '146',
		pwd : '0592',
	}, {
		usr : '147',
		pwd : '0330',
	}, {
		usr : '148',
		pwd : '0755',
	}, {
		usr : '149',
		pwd : '0414',
	}, {
		usr : '150',
		pwd : '0510',
	}, {
		usr : '151',
		pwd : '0150',
	}, {
		usr : '152',
		pwd : '0335',
	}, {
		usr : '153',
		pwd : '0215',
	}, {
		usr : '154',
		pwd : '0270',
	}, {
		usr : '155',
		pwd : '0322',
	}, {
		usr : '156',
		pwd : '0280',
	}, {
		usr : '157',
		pwd : '0510',
	}, {
		usr : '158',
		pwd : '0410',
	}, {
		usr : '159',
		pwd : '0460',
	}, {
		usr : '160',
		pwd : '0530',
	}, {
		usr : '161',
		pwd : '0140',
	}, {
		usr : '162',
		pwd : '0420',
	}, {
		usr : '163',
		pwd : '0630',
	}, {
		usr : '164',
		pwd : '0250',
	}, {
		usr : '165',
		pwd : '0300',
	}, {
		usr : '166',
		pwd : '0370',
	}, {
		usr : '167',
		pwd : '0250',
	}, {
		usr : '168',
		pwd : '0480',
	}, {
		usr : '169',
		pwd : '0115',
	}, {
		usr : '170',
		pwd : '0130',
	}, {
		usr : '171',
		pwd : '0070',
	}, {
		usr : '172',
		pwd : '0190',
	}, {
		usr : '173',
		pwd : '0398',
	}, {
		usr : '174',
		pwd : '0330',
	}, {
		usr : '175',
		pwd : '0290',
	}, {
		usr : '176',
		pwd : '2710',
	}, {
		usr : '177',
		pwd : '0210',
	}, {
		usr : '178',
		pwd : '2810',
	}, {
		usr : '179',
		pwd : '0205',
	}, {
		usr : '180',
		pwd : '0180',
	}, {
		usr : '181',
		pwd : '0080',
	}, {
		usr : '182',
		pwd : '0250',
	}, {
		usr : '183',
		pwd : '0455',
	}, {
		usr : '184',
		pwd : '0310',
	}, {
		usr : '185',
		pwd : '0500',
	}, {
		usr : '186',
		pwd : '0480',
	}, {
		usr : '187',
		pwd : '1230',
	}, {
		usr : '188',
		pwd : '1140',
	}, {
		usr : '189',
		pwd : '0230',
	}, {
		usr : '190',
		pwd : '0010',
	}, {
		usr : '191',
		pwd : '0490',
	}, {
		usr : '192',
		pwd : '0095',
	}, {
		usr : '193',
		pwd : '0730',
	}, {
		usr : '194',
		pwd : '0410',
	}, {
		usr : '195',
		pwd : '2930',
	}, {
		usr : '196',
		pwd : '2850',
	}, {
		usr : '197',
		pwd : '2130',
	}, {
		usr : '198',
		pwd : '0590',
	}, {
		usr : '199',
		pwd : '3350',
	}, {
		usr : '200',
		pwd : '0530',
	}, {
		usr : '201',
		pwd : '0170',
	}, {
		usr : '202',
		pwd : '0190',
	}, {
		usr : '203',
		pwd : '0170',
	}, {
		usr : '204',
		pwd : '0350',
	}, {
		usr : '205',
		pwd : '0150',
	}, {
		usr : '206',
		pwd : '0210',
	}, {
		usr : '207',
		pwd : '0330',
	}, {
		usr : '208',
		pwd : '1530',
	}, {
		usr : '209',
		pwd : '0167',
	}, {
		usr : '210',
		pwd : '0065',
	}, {
		usr : '211',
		pwd : '0070',
	}, {
		usr : '212',
		pwd : '0110',
	}, {
		usr : '213',
		pwd : '0270',
	}, {
		usr : '214',
		pwd : '0070',
	}, {
		usr : '215',
		pwd : '0055',
	}, {
		usr : '216',
		pwd : '0210',
	}, {
		usr : '217',
		pwd : '1210',
	}, {
		usr : '218',
		pwd : '1375',
	}, {
		usr : '219',
		pwd : '1510',
	}, {
		usr : '220',
		pwd : '1300',
	}, {
		usr : '221',
		pwd : '0031',
	}, {
		usr : '222',
		pwd : '1148',
	}, {
		usr : '223',
		pwd : '1090',
	}, {
		usr : '224',
		pwd : '0132',
	}, {
		usr : '225',
		pwd : '3275',
	}, {
		usr : '226',
		pwd : '4425',
	}, {
		usr : '227',
		pwd : '0330',
	}, {
		usr : '228',
		pwd : '1150',
	}, {
		usr : '229',
		pwd : '0210',
	}, {
		usr : '230',
		pwd : '0470',
	}, {
		usr : '231',
		pwd : '1310',
	}, {
		usr : '232',
		pwd : '0140',
	}, {
		usr : '233',
		pwd : '0180',
	}, {
		usr : '234',
		pwd : '0220',
	}, {
		usr : '235',
		pwd : '1850',
	}, {
		usr : '236',
		pwd : '0410',
	}, {
		usr : '237',
		pwd : '0130',
	}, {
		usr : '238',
		pwd : '0310',
	}, {
		usr : '239',
		pwd : '0070',
	}, {
		usr : '240',
		pwd : '0230',
	}, {
		usr : '241',
		pwd : '0210',
	}, {
		usr : '242',
		pwd : '3830',
	}, {
		usr : '243',
		pwd : '4010',
	}, {
		usr : '244',
		pwd : '4125',
	}, {
		usr : '245',
		pwd : '4270',
	}, {
		usr : '246',
		pwd : '0310',
	}, {
		usr : '247',
		pwd : '2910',
	}, {
		usr : '248',
		pwd : '1590',
	}, {
		usr : '249',
		pwd : '0015',
	}, {
		usr : '250',
		pwd : '0180',
	}, {
		usr : '251',
		pwd : '3010',
	}, {
		usr : '252',
		pwd : '0110',
	}, {
		usr : '253',
		pwd : '0377',
	}, {
		usr : '254',
		pwd : '0027',
	}, {
		usr : '255',
		pwd : '1010',
	}, {
		usr : '256',
		pwd : '0190',
	}, {
		usr : '257',
		pwd : '1250',
	}, {
		usr : '258',
		pwd : '0060',
	}, {
		usr : '259',
		pwd : '0612',
	}, {
		usr : '260',
		pwd : '2410',
	}, {
		usr : '261',
		pwd : '0190',
	}, {
		usr : '262',
		pwd : '2360',
	}, {
		usr : '263',
		pwd : '2470',
	}, {
		usr : '264',
		pwd : '2570',
	}, {
		usr : '265',
		pwd : '0170',
	}, {
		usr : '266',
		pwd : '0130',
	}, {
		usr : '267',
		pwd : '1043',
	}, {
		usr : '268',
		pwd : '1077',
	}, {
		usr : '269',
		pwd : '1010',
	}, {
		usr : '270',
		pwd : '0610',
	}, {
		usr : '271',
		pwd : '0783',
	}, {
		usr : '272',
		pwd : '0810',
	}, {
		usr : '273',
		pwd : '0850',
	}, {
		usr : '274',
		pwd : '0105',
	}, {
		usr : '275',
		pwd : '0010',
	}, {
		usr : '276',
		pwd : '0930',
	}, {
		usr : '277',
		pwd : '1010',
	}, {
		usr : '278',
		pwd : '0210',
	}, {
		usr : '279',
		pwd : '0300',
	}, {
		usr : '280',
		pwd : '0290',
	}, {
		usr : '281',
		pwd : '0495',
	}, {
		usr : '282',
		pwd : '0330',
	}, {
		usr : '283',
		pwd : '0150',
	}, {
		usr : '284',
		pwd : '0272',
	}, {
		usr : '285',
		pwd : '0210',
	}, {
		usr : '286',
		pwd : '0025',
	}, {
		usr : '287',
		pwd : '0075',
	}, {
		usr : '288',
		pwd : '0370',
	}, {
		usr : '289',
		pwd : '0950',
	}, {
		usr : '290',
		pwd : '0390',
	}, {
		usr : '291',
		pwd : '0440',
	}, {
		usr : '292',
		pwd : '0434',
	}, {
		usr : '293',
		pwd : '0810',
	}, {
		usr : '294',
		pwd : '1670',
	}, {
		usr : '295',
		pwd : '0930',
	}, {
		usr : '296',
		pwd : '0040',
	}, {
		usr : '297',
		pwd : '0110',
	}, {
		usr : '298',
		pwd : '0180',
	}, {
		usr : '299',
		pwd : '0110',
	}, {
		usr : '300',
		pwd : '0060',
	}, {
		usr : '000',
		pwd : '0150',
	}, {
		usr : '000',
		pwd : '0440',
	} ]
};
