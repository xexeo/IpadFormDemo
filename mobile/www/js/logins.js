var logins = {

	autenticaMaster : function(usuario, senha) {
		if ((usuario == logins.user_master.usr) && (senha == logins.user_master.pwd)) {
			return true;
		}
		return false;
	},

	autentica : function(usuario, senha) {
		var posto = null;
		if ((logins.user_tester != undefined) && (logins.user_tester.usr != undefined) && (usuario == logins.user_tester.usr)
				&& (senha == logins.user_tester.pwd)) {
			logins.user_logado = logins.user_tester;
			return true;
		} else if (String(usuario).length == 3) {
			posto = String(usuario)
			return logins.verificaPostoSenha(posto, senha);
			//treinamento
		} else if (String(usuario).length == 4 && String(usuario).substr(3).toUpperCase() == 'T'){
			if (datas.verificaData()){
				alert("Logins de treinamento não funcionam em datas da pesquisa real","Erro!",null,'error');
				return false;
			}
			posto = String(usuario).substr(0,3);
			if (logins.verificaPostoSenha(posto, senha)){
				app.isTreinamento = true;
				alert("O sistema funcionará em modo de treinamento",null,null,'error');
				return true;
			}
		}
		return false;
	},
	
	verificaPostoSenha : function(posto, senha){
		if (posto.length == 3 && Number(posto) > 0) {
				var i = Number(posto) - 1;
				// for (i = 0; i < logins.users.length; i++) {
				/* comentar o 'for' se o sequencial do posto for igual ao posicionamento dele na lista. */
				var login = logins.users[i];
				if ((posto == login.usr) && (senha == login.pwd)) {
					logins.user_logado = login;
					return true;
				}
				// }
		}
		return false;
	},
	
	user_master : { // Usuário exclusivo para configurar o idIpad na instalação, ele não faz login no aplicativo.
		usr : 'Master', // usuário mestre
		pwd : "434723" // senha mestra (ver teclado numérico para a string 'idIPad')
	},

	user_tester : { // Usuário destinado aos testes do aplicativo.
		// Antes de ir para produção, basta comentar as duas linhas abaixo se quiser inativar para esse ambiente.
		usr : 'Teste', // usuário de teste
		pwd : "837837", // senha de teste (ver teclado numérico para a string 'tester')
		perguntaExtra : true
	},
	
	// Postos com pergunta extra na 1ª fase: 211
	// Postos com pergunta extra na 2ª fase: 020, 184, 185 
	users : [
	// usr = número do posto
	// pwd = 4 últimos números do SNV
	{
		usr : '001',
		pwd : '1590',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR AC-463/465 (BOM FUTURO) - ENTR BR-317'
	}, {
		usr : '002',
		pwd : '0808',
		fase : '1',
		ladoA : 'LUCAS DO RIO VERDE',
		ladoB : 'INÍCIO DA TRAVESSIA URBANA DE SORRISO',
		trecho : 'LUCAS DO RIO VERDE – INÍCIO DA TRAVESSIA URBANA DE SORRISO'
	}, {
		usr : '003',
		pwd : '0630',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-104(A) - ENTR BR-104(B)'
	}, {
		usr : '004',
		pwd : '0890',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR AL-225(A) - ENTR AL-225(B) (P/PORTO REAL DO COLÉGIO)'
	}, {
		usr : '005',
		pwd : '0950',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR AL-115(A) - ENTR AL-115(B) (PALMEIRA DOS ÍNDIOS)'
	}, {
		usr : '006',
		pwd : '0750',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-101(A) - ENTR BR-101(B) (P/ALAGOINHAS)'
	}, {
		usr : '007',
		pwd : '1810',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BA-120(A)/262(B) (P/ITAJUÍPE) - ENTR BR-415/BA-120(B) (ITABUNA)'
	}, {
		usr : '008',
		pwd : '0330',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-324(A) - ENTR BR-324(B)/BA-502/503 (FEIRA DE SANTANA)'
	}, {
		usr : '009',
		pwd : '0934',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BA-643 (PLANALTO) - ENTR BA-641'
	}, {
		usr : '010',
		pwd : '0365',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-242(A) - ENTR BR-330/BA-148 (SEABRA)'
	}, {
		usr : '011',
		pwd : '0300',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'JAGUARARI - ENTR BA-220 (SENHOR DO BONFIM)'
	}, {
		usr : '012',
		pwd : '0530',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-187 (P/MARRECOS) - ENTR BR-404/CE-176/187/363 (P/TAUÁ)'
	}, {
		usr : '013',
		pwd : '0090',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-253 (PACAJÚS) - ENTR BR-122(A)/CE-354 (CHORÓZINHO)'
	}, {
		usr : '014',
		pwd : '0260',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-226(B)/CE-275(B) (P/PEREIRO) - ENTR BR-404/434 (ICÓ)'
	}, {
		usr : '015',
		pwd : '2195',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR ES-245(B) - ENTR ES-440'
	}, {
		usr : '016',
		pwd : '2470',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR ES-375(A) (P/PIÚMA) - ENTR ES-375(B) (ICONHA)'
	}, {
		usr : '017',
		pwd : '0070',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-101(B) - ENTR ES-465 (P/DOMINGOS MARTINS)'
	}, {
		usr : '018',
		pwd : '0015',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'KM 15,3 - ENTR BR-484 (P/PONTE SOBRE RIO DOCE)'
	}, {
		usr : '019',
		pwd : '0110',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-116 (P/FORMOSA) - ENTR GO-346 (P/CABEÇEIRAS)'
	}, {
		usr : '020',
		pwd : '0090',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-457(B)/GO-219 - ENTR GO-020(A)',
		perguntaExtra : true
	}, {
		usr : '021',
		pwd : '0112',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-338 (ABADIÂNIA - FIM TRAVESSIA URBANA) - ENTR BR-153(A) (P/ANÁPOLIS)'
	}, {
		usr : '022',
		pwd : '0340',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-353(A) (LINDA VISTA) - ENTR BR-414(A)/GO-151/244/353(B) (PORANGATU)'
	}, {
		usr : '023',
		pwd : '0770',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-154/452(A)/483 - ENTR BR-452(B) (DIV GO/MG) (ITUMBIARA)'
	}, {
		usr : '024',
		pwd : '0510',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-060(B)/158(B)/GO-184(B) (P/ESTREITO) - ENTR GO-050(B)'
	}, {
		usr : '025',
		pwd : '0050',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'FIM DA DUPLICAÇÃO - ENTR BR-402/MA-110 (BACABEIRA)'
	}, {
		usr : '026',
		pwd : '0150',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-316(A) (CACHUCHA) - ENTR BR-316(B) (PERITORÓ)'
	}, {
		usr : '027',
		pwd : '0680',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'RIO PINDARÉ - ENTR BR-010(A) (AÇAILÂNDIA)'
	}, {
		usr : '028',
		pwd : '0670',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-251(A) (CANACÍ) - ENTR BR-135/251(B)/308/365 (MONTES CLAROS)'
	}, {
		usr : '029',
		pwd : '0370',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MA-026 (DEZESSETE) - ENTR MA-034(A)/127/349 (CAXIAS)'
	}, {
		usr : '030',
		pwd : '0130',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-410 (P/PONTO DIAMANTE) - ENTR MG-181 (JOÃO PINHEIRO)'
	}, {
		usr : '031',
		pwd : '0330',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-238 (P/SETE LAGOAS) - ENTR MG-432 (P/ESMERALDAS)'
	}, {
		usr : '032',
		pwd : '0040',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-040(A) (ANEL RODOVIÁRIO DE BELO HORIZONTE) - ENTR BR-040(B)'
	}, {
		usr : '033',
		pwd : '0570',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-499 (SANTOS DUMONT) - ENTR ANT UNIÃO E INDÚSTRIA (B. TRIUNFO)'
	}, {
		usr : '034',
		pwd : '0250',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-365(B)/452(B) - RIO TIJUCO'
	}, {
		usr : '035',
		pwd : '1130',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-342(B)/418/MG-217 (RIB STO ANTÔNIO) (TEÓFILO OTONI) - ACESSO ITAMBACURI'
	}, {
		usr : '036',
		pwd : '1280',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'FIM PISTA DUPLA - DOM CORRÊA'
	}, {
		usr : '037',
		pwd : '1430',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-116(A)/120 (LEOPOLDINA) - ENTR BR-116(B)'
	}, {
		usr : '038',
		pwd : '0850',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-259(B) (CURVELO) - ENTR BR-040(A)'
	}, {
		usr : '039',
		pwd : '0290',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-364(A)/262(A) (P/COMENDADOR GOMES) - ENTR BR-364(B)/262(B) (P/FRUTAL)'
	}, {
		usr : '040',
		pwd : '0890',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-187 (P/IBIÁ) - ENTR BR-146 (P/ARAXÁ)'
	}, {
		usr : '041',
		pwd : '0230',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-451 (ITUTINGA) - ENTR BR-354 (LAVRAS)'
	}, {
		usr : '042',
		pwd : '0215',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO CARMO DO PARANAIBA - ENTR MG-230'
	}, {
		usr : '043',
		pwd : '0100',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'PRESIDENTE OLEGÁRIO - ENTR BR-146(A)/365 (PATOS DE MINAS)'
	}, {
		usr : '044',
		pwd : '0370',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-153 - ENTR BR-154(A)'
	}, {
		usr : '045',
		pwd : '0230',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-425 (CORONEL FABRICIANO) - ENTR MG-320 (P/JAGUARAÇU)'
	}, {
		usr : '046',
		pwd : '0490',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-262(B) (BETIM) - ENTR MG-155'
	}, {
		usr : '047',
		pwd : '0750',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-458 (CAREAÇU) - ENTR BR-459 (P/POUSO ALEGRE)'
	}, {
		usr : '048',
		pwd : '0324',
		fase : '1',
		ladoA : 'VILA SÃO PEDRO',
		ladoB : 'ACESSO COLÔNIA AGRÍCOLA SEXTA LINHA',
		trecho : 'ACESSO COLÂNIA AGRICOLA SEXTA LINHA - FINAL PISTA DUPLA (VILA VARGAS)'
	}, {
		usr : '049',
		pwd : '0450',
		fase : '1',
		ladoA : 'ENTR MS-441(B) (P/BANDEIRANTES)',
		ladoB : 'ENTR MS-244 (P/BONFIM)',
		trecho : 'ENTR MS-441(B) (P/BANDEIRANTES) - ENTR MS-244 (P/BONFIM)'
	}, {
		usr : '050',
		pwd : '1290',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-158(B)/MS-395 (P/BRASILÂNDIA) - ENTR MS-453/459 (P/ARAPUÁ)'
	}, {
		usr : '051',
		pwd : '0880',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'FIM TRAV RIO PARANÁ (PONTE M. JOPPERT) - INCÍO DA PISTA DUPLA'
	}, {
		usr : '052',
		pwd : '0030',
		fase : '1',
		ladoA : 'ENTR BR-070(B)',
		ladoB : 'ENTR MT-170 (CARAMÚJO)',
		trecho : 'ENTR BR-070(B) - ENTR MT-170 (CARAMÚJO)'
	}, {
		usr : '053',
		pwd : '0592',
		fase : '1',
		ladoA : 'ENTR MT-270(B)',
		ladoB : 'ENTR MT-469(A)',
		trecho : 'ENTR MT-270(B) - ENTR MT-469(A)'
	}, {
		usr : '054',
		pwd : '0840',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'JANGADA - ENTR MT-246(B)'
	}, {
		usr : '055',
		pwd : '0510',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-222(B)/PA-332 (DOM ELISEU) - ENTR PA-125/263 (GURUPIZINHO)'
	}, {
		usr : '056',
		pwd : '0335',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PB-018 (P/CONDE) - ENTR PB-034'
	}, {
		usr : '057',
		pwd : '0010',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PB-100 (P/GALANTE) - ENTR BR-104(A)/408(B)/PB-095 (CAMPINA GRANDE)'
	}, {
		usr : '058',
		pwd : '0510',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-063 (P/AMARAJI) - ENTR PE-064/085 (RIBEIRÃO)'
	}, {
		usr : '059',
		pwd : '0460',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-460 - ENTR BR-316/428 (P/CABROBÓ)'
	}, {
		usr : '060',
		pwd : '0140',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-103 (P/BONITO) - ENTR BR-104/423(A) (CARUARÚ)'
	}, {
		usr : '061',
		pwd : '0420',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-450 (P/VERDEJANTE) - ENTR BR-116/361 (SALGUEIRO)'
	}, {
		usr : '062',
		pwd : '0370',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-428(A) (LAGOA GRANDE) - ENTR BR-235/407/423/428(B) (DIV PE/BA) (PETROLINA/JUAZEIRO)'
	}, {
		usr : '063',
		pwd : '0830',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PI-242 - ENTR BR-407(A)/PI-238/245(A) (PICOS)'
	}, {
		usr : '064',
		pwd : '0130',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-404(B)/407(B) - INÍCIO PISTA DUPLA (CAPITÃO CAMPOS)'
	}, {
		usr : '065',
		pwd : '0420',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'DEMERVAL LOBÃO - ENTR PI-223 (MONSENHOR GIL)'
	}, {
		usr : '066',
		pwd : '2710',
		fase : '1',
		ladoA : 'DIV SP/PR (CAB NORTE PONTE S/ RIO PARDINHO)',
		ladoB : 'INÍCIO VARIANTE DO ALPINO(PISTA DIREITA)',
		trecho : 'DIV SP/PR (CAB NORTE PONTE S/ RIO PARDINHO) - INÍCIO VARIANTE DO ALPINO(PISTA DIREITA)'
	}, {
		usr : '067',
		pwd : '2810',
		fase : '1',
		ladoA : 'ENTR PR-427 (CAMPO DO TENENTE)',
		ladoB : 'DIV PR/SC (RIO NEGRO/MAFRA)',
		trecho : 'ENTR PR-427 (CAMPO DO TENENTE) - DIV PR/SC (RIO NEGRO/MAFRA)'
	}, {
		usr : '068',
		pwd : '0205',
		fase : '1',
		ladoA : 'ACESSO GUARAPUAVA',
		ladoB : 'ENTR BR-466 (P/GUARAPUAVA)',
		trecho : 'ACESSO GUARAPUAVA - ENTR BR-466 (P/GUARAPUAVA)'
	}, {
		usr : '069',
		pwd : '0310',
		fase : '1',
		ladoA : 'ENTR PR-182 (P/CAPITÃO LEÔNIDAS MARQUES)',
		ladoB : 'ENTR BR-163',
		trecho : 'ACESSO OESTE CASCAVEL - ENTR BR-163/PR-182 (P/CAPITÃO LEÔNIDAS MARQUES)'
	}, {
		usr : '070',
		pwd : '0480',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'FIM PISTA DUPLA - ENTR PR-431 (P/JACAREZINHO)'
	}, {
		usr : '071',
		pwd : '0490',
		fase : '1',
		ladoA : 'ENTR PR-281 (P/TIJUCAS DO SUL)',
		ladoB : 'DIV PR/SC (ENTR BR-101)',
		trecho : 'ENTR PR-281 (P/TIJUCAS DO SUL) - DIV PR/SC (ENTR BR-101)'
	}, {
		usr : '072',
		pwd : '0095',
		fase : '1',
		ladoA : 'ENTR PR-510/511 (P/CONTENDA)',
		ladoB : 'ENTR PR-428 (LAPA)',
		trecho : 'ENTR PR-510/511 (P/CONTENDA) - ENTR PR-428 (LAPA)'
	}, {
		usr : '073',
		pwd : '0730',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-393(B) (TREVO MOURA BRASIL) - ENTR BR-492(A) (TREVO P/ AREAL)'
	}, {
		usr : '074',
		pwd : '2930',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RJ-162 (P/RIO DOURADO) - ENTR BR-120 (CASIMIRO DE ABREU)'
	}, {
		usr : '075',
		pwd : '2130',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RJ-159 (FLORIANO) - ENTR RJ-161 (RESENDE)'
	}, {
		usr : '076',
		pwd : '0310',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-356(A)/393(B) - ENTR BR-356(B) (ITAPERUNA)'
	}, {
		usr : '077',
		pwd : '0330',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'SAPUCAIA - ENTR BR-040(A)'
	}, {
		usr : '078',
		pwd : '0167',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO À LAGOA DO BONFIM - ENTR RN-315/002(A) (SÃO JOSÉ DE MIPIBÚ/NÍSIA FLORESTA)'
	}, {
		usr : '079',
		pwd : '0070',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RN-203 (P/SÃO PEDRO) - ACESSO BOM JESUS'
	}, {
		usr : '080',
		pwd : '0270',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-104(B) (LAJES) - ENTR RN-023 (CAIÇARA DO RIO DO VENTO)'
	}, {
		usr : '081',
		pwd : '0070',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RN-233 (P/CARAÚBAS) - ENTR RN-031/177(A)'
	}, {
		usr : '082',
		pwd : '1210',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-429(B) (JI-PARANÁ) - ENTR RO-473 (P/URUPÁ)'
	}, {
		usr : '083',
		pwd : '3275',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR ERS-703 (GUAIBA) - ENTR ERS-709 (P/ BARRA DO RIBEIRO)'
	}, {
		usr : '084',
		pwd : '1150',
		fase : '1',
		ladoA : 'ENTR BR-472 (P/PALMITINHO)',
		ladoB : 'ENTR RS-585/587 (SEBERI)',
		trecho : 'ENTR RSC-472 (P/ PALMITINHO) - ENTR ERS-585/587 (SEBERI)'
	}, {
		usr : '085',
		pwd : '1310',
		fase : '1',
		ladoA : 'ENTR RS-348 (VAL DE SERRA)',
		ladoB : 'ENTR RS-509 (P/SANTA MARIA)',
		trecho : 'ENTR ERS-348 (VAL DE SERRA) - ENTR ERS-509 (P/ SANTA MARIA)'
	}, {
		usr : '086',
		pwd : '0180',
		fase : '1',
		ladoA : 'ACESSO A MINAS DO LEÃO',
		ladoB : 'ENTR BR-471 (PANTANO GRANDE)',
		trecho : 'ACESSO A MINAS DO LEAO - ENTR BRS-471 (PANTANO GRANDE)'
	}, {
		usr : '087',
		pwd : '1840',
		fase : '1',
		ladoA : 'ENTR RS-705 (P/GERIBA)',
		ladoB : 'ENTR BR-290(B)',
		trecho : 'ENTR ERS-705 (GERIBA) - ENTR BRS-153(B) (P/ BAGE)'
	}, {
		usr : '088',
		pwd : '0410',
		fase : '1',
		ladoA : 'ENTR BR-293(A)',
		ladoB : 'ENTR BR-472(A)',
		trecho : 'ENTR BRS-293(A) (P/ QUARAI) - ENTR BRS-472(A) (P/ BARRA DO QUARAI)'
	}, {
		usr : '089',
		pwd : '0130',
		fase : '1',
		ladoA : 'ENTR BR-473 (P/BAGÉ)',
		ladoB : 'ENTR RS-630 (P/DOM PEDRITO)',
		trecho : 'ENTR RSC-473 (P/ BAGE) - ENTR ERS-630/634 (P/ DOM PEDRITO)'
	}, {
		usr : '090',
		pwd : '0070',
		fase : '1',
		ladoA : 'ENTR BR-471(A) (QUINTA)',
		ladoB : 'ACESSO PELOTAS',
		trecho : 'ENTR BRS-471(A) (QUINTA) - ACESSO SUL A PELOTAS'
	}, {
		usr : '091',
		pwd : '0210',
		fase : '1',
		ladoA : 'ACESSO LESTE A  ITAQUI',
		ladoB : 'ENTR BR-290(A)/293(A) (URUGUAIANA)',
		trecho : 'ACESSO LESTE A ITAQUI - ENTR BR-290(A)/293(A) (URUGUAIANA)'
	}, {
		usr : '092',
		pwd : '3830',
		fase : '1',
		ladoA : 'GARUVA',
		ladoB : 'ENTR SC-430 (PIRABEIRABA)',
		trecho : 'GARUVA - ENTR SC-430 (PIRABEIRABA)'
	}, {
		usr : '093',
		pwd : '4010',
		fase : '1',
		ladoA : 'BALNEÁRIO DE CAMBORIÚ',
		ladoB : 'P/PORTO BELO',
		trecho : 'BALNEÁRIO DE CAMBORIÚ - P/PORTO BELO'
	}, {
		usr : '094',
		pwd : '4125',
		fase : '1',
		ladoA : 'PONTE SOBRE O RIO DA MADRE',
		ladoB : 'PONTE SOBRE O RIO ARAÇATUBA',
		trecho : 'PONTE SOBRE O RIO DA MADRE - PONTE SOBRE O RIO ARAÇATUBA'
	}, {
		usr : '095',
		pwd : '4240',
		fase : '1',
		ladoA : 'ENTR BR-285(A) (ARARANGUÁ)',
		ladoB : 'ENTR BR-285(B)/SC-285 (SANGA DA TOCA)',
		trecho : 'ENTR BR-285(A)/SC-449 (ARARANGUÁ) - ENTR BR-285(B)/SC-285 (P/ERMO)'
	}, {
		usr : '096',
		pwd : '2910',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SC-352(B) (P/TAIÓ) - SÃO CRISTOVÃO DO SUL'
	}, {
		usr : '097',
		pwd : '1590',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SC-463 (P/JABORÁ) - ENTR BR-283 (P/CONCÓRDIA)'
	}, {
		usr : '098',
		pwd : '0020',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SC-114(A) (ÍNDIOS) - ENTR SC-114(B) (P/PAINEL)'
	}, {
		usr : '099',
		pwd : '0377',
		fase : '1',
		ladoA : 'ENTR SC-157(B) (P/MODELO)',
		ladoB : 'ENTR BR-158(A) (P/CUNHA PORÃ)',
		trecho : 'ENTR SC-157(B) (P/MODELO) - ENTR BR-158(A) (P/CUNHA PORÃ)'
	}, {
		usr : '100',
		pwd : '0190',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SC-114(B) (P/OTACÍLIO COSTA) - ENTR BR-116'
	}, {
		usr : '101',
		pwd : '0040',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SE-464 (P/SÃO CRISTOVÃO) - ENTR BR-349(B)/SE-265 (ITAPORANGA D`AJUDA)'
	}, {
		usr : '102',
		pwd : '0060',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SE-160 - ENTR SE-170(A)'
	}, {
		usr : '103',
		pwd : '2410',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-070 - ENTR SP-103 (CAÇAPAVA)'
	}, {
		usr : '104',
		pwd : '2570',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-228 - ENTR SP-057 (P/SIDERÚRGICA)'
	}, {
		usr : '105',
		pwd : '1043',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO GUAIÇARA - ENTR BR-154/267 (LINS)'
	}, {
		usr : '106',
		pwd : '0810',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'DIV MG/SP - ENTR SP-063 (P/BRAGANÇA PAULISTA)'
	}, {
		usr : '107',
		pwd : '0105',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR TO-420 - INICIO PISTA DUPLA ARAGUAINA'
	}, {
		usr : '108',
		pwd : '0205',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR TO-342(B) (MIRANORTE) - ENTR TO-348 (BARROLÂNDIA)'
	}, {
		usr : '109',
		pwd : '0300',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR TO-483 (FIGUEIRÓPOLIS) - ENTR TO-296(A)/373 (ALVORADA)'
	}, {
		usr : '110',
		pwd : '0495',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PI-260 (P/BARREIRAS DO PIAUÍ) - ENTR PI-255 (CORRENTE)'
	}, {
		usr : '111',
		pwd : '0150',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'FIM PISTA DUPLA - ENTR GO-156(B)'
	}, {
		usr : '112',
		pwd : '0025',
		fase : '1',
		ladoA : 'ENTR PR-508 (P/MATINHOS)',
		ladoB : 'ENTR BR-101/PR-408 (P/MORRETES)',
		trecho : 'ENTR PR-508 (P/MATINHOS) - ENTR BR-101/PR-408 (P/MORRETES)'
	}, {
		usr : '113',
		pwd : '0075',
		fase : '1',
		ladoA : 'ACESSO SANTA',
		ladoB : 'ENTR BR-376(B)/PR-428 (SÃO LUIS PURUNÃ)',
		trecho : 'ACESSO SANTA - ENTR BR-376(B)/PR-428 (SÃO LUIS PURUNÃ)'
	}, {
		usr : '114',
		pwd : '0950',
		fase : '1',
		ladoA : 'ENTR MS-375 (ZUZU)',
		ladoB : 'ENTR BR-163(A) (NOVA ALVORADA DO SUL)',
		trecho : 'ENTR MS-375 (ZUZU) - ENTR BR-163(A) (NOVA ALVORADA)'
	}, {
		usr : '115',
		pwd : '0440',
		fase : '1',
		ladoA : 'ENTR MS-434 (RAIMUNDO)',
		ladoB : 'ENTR BR-483/497 (INÍCIO DA PISTA DUPLA)',
		trecho : 'ENTR MS-434 (RAIMUNDO) - ENTR BR-483/497 (INÍCIO DA PISTA DUPLA)'
	}, {
		usr : '116',
		pwd : '0810',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PISTA INVERSA (B) - ENTR BR-101'
	}, {
		usr : '117',
		pwd : '0070',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-493(A) (P/MAGÉ) - ENTR RJ-107 (IMBARIÊ)'
	}, {
		usr : '118',
		pwd : '0040',
		fase : '1',
		ladoA : 'ENTR BR-101',
		ladoB : 'ENTR SC-108(A) (NEUDOR)',
		trecho : 'ENTR BR-101 - ENTR SC-108(A) (NEUDOR)'
	}, {
		usr : '119',
		pwd : '1490',
		fase : '1',
		ladoA : 'ENTR BR-280(A)/PR-446',
		ladoB : 'ENTR PR-170',
		trecho : 'ENTR BR-153(A)/PR-446 - ENTR PR-170'
	}, {
		usr : '120',
		pwd : '0060',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-262(B) - ENTR BR-050(B)'
	}, {
		usr : '121',
		pwd : '0834',
		fase : '1',
		ladoA : 'ENTR MT-423',
		ladoB : 'ITAUBA',
		trecho : 'ENTR MT-423 - ITAUBA'
	}, {
		usr : '122',
		pwd : '0852',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'MATUPÁ - GUARANTÃ DO NORTE'
	}, {
		usr : '123',
		pwd : '0790',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR AL-215(B) - ENTR AL-220(A) (P/BARRA DE SÃO MIGUEL)'
	}, {
		usr : '124',
		pwd : '0260',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-270 (P/BUÍQUE) - ENTR BR-110 (CRUZEIRO DO NORDESTE)'
	}, {
		usr : '125',
		pwd : '0090',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-218 (P/BOM CONSELHO) - ENTR PE-187 (P/PALMEIRINA)'
	}, {
		usr : '126',
		pwd : '1695',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-420(B) (P/LAJE) - ENTR BA-542 (P/GUERÉM)'
	}, {
		usr : '127',
		pwd : '0790',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BA-493 (ITATIM) - ENTR BA-245'
	}, {
		usr : '128',
		pwd : '0334',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'MAIRI - ENTR BR-349'
	}, {
		usr : '129',
		pwd : '0270',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'GAVIÃO - ENTR BR-349 (NOVA FÁTIMA)'
	}, {
		usr : '130',
		pwd : '1010',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'DIV BA/MG - ENTR BR-251(A) (P/SALINAS)'
	}, {
		usr : '131',
		pwd : '0530',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BA-937 (P/PAJEÚ DO VENTO) - ENTR BR-122(B)/430/BA-569 (CAETITÉ)'
	}, {
		usr : '132',
		pwd : '0245',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BA-131 (P/CAÉM) - ENTR BA-417'
	}, {
		usr : '133',
		pwd : '0550',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-235 - ENTR BA-220 (EUCLIDES DA CUNHA)'
	}, {
		usr : '134',
		pwd : '0690',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-373 (CARMELÓPOLIS) - ENTR CE-187/292 (CAMPOS SALES)'
	}, {
		usr : '135',
		pwd : '0190',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-266 (SUCESSO) - ENTR BR-226/404 (CRATEÚS)'
	}, {
		usr : '136',
		pwd : '0610',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-060(B) (MINEIROLÂNDIA) - ENTR CE-168 (PEDRA BRANCA)'
	}, {
		usr : '137',
		pwd : '0050',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-154 (PIRANGI) - ENTR CE-060(A)/265 (QUIXADÁ)'
	}, {
		usr : '138',
		pwd : '0590',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-456 (P/TARGINOS) - ENTR CE-257 (CANINDÉ)'
	}, {
		usr : '139',
		pwd : '0070',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-341 (CROATÁ) - ENTR CE-162 (SÃO LUÍS DO CURU)'
	}, {
		usr : '140',
		pwd : '0160',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-263 (P/JAGUARUANA) - RUSSAS'
	}, {
		usr : '141',
		pwd : '2130',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-381 (SÃO MATEUS) - ENTR ES-430 (P/JAGUARÉ)'
	}, {
		usr : '142',
		pwd : '0300',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR ES-137 - ENTR ES-130 (P/NOVA VENÉCIA)'
	}, {
		usr : '143',
		pwd : '1990',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BA-290 (TEIXEIRA DE FREITAS) - ENTR BR-418 (P/POSTO DA MATA)'
	}, {
		usr : '144',
		pwd : '0030',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'MORRO GRANDE (ACESSO IV C. ITAPEMIRIM) - ENTR ES-166 (COUTINHO)'
	}, {
		usr : '145',
		pwd : '0130',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-422 (RESPLENDOR) - ENTR BR-458 (CONSELHEIRO PENA)'
	}, {
		usr : '146',
		pwd : '0125',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-230 - ENTR GO-237'
	}, {
		usr : '147',
		pwd : '0160',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-114 (P/FLORES DE GOIÁS) - SANTA MARIA'
	}, {
		usr : '148',
		pwd : '0470',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'DIV MG/GO - ENTR GO-010(A)'
	}, {
		usr : '149',
		pwd : '0050',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-010 (P/LUZIÂNIA) - ENTR BR-050(B)/354/457/GO-309 (CRISTALINA)'
	}, {
		usr : '150',
		pwd : '0080',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'URUTAÍ - ENTR BR-490/GO-213/307 (IPAMERI)'
	}, {
		usr : '151',
		pwd : '0550',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-080(B) (P/SAO FRANCISCO DE GOIÁS) - ENTR GO-431 (P/PIRENOPOLIS)'
	}, {
		usr : '152',
		pwd : '0190',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-153(A)/GO-342(B) - ENTR BR-153(B)/GO-237 (URUAÇÚ)'
	}, {
		usr : '153',
		pwd : '0750',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-210(B) (P/PANAMÁ) - ENTR BR-154/452(A)/483'
	}, {
		usr : '154',
		pwd : '0050',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-040 - ENTR BR-154(A)/483(A)/GO-206(A) (P/CACHOEIRA DOURADA)'
	}, {
		usr : '155',
		pwd : '0570',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-194 (P/PORTELÂNDIA) - INÍCIO PISTA DUPLA'
	}, {
		usr : '156',
		pwd : '0440',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-174(B) - ENTR GO-206 (P/CAÇU)'
	}, {
		usr : '157',
		pwd : '0280',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MA-006(A) (COCALINHO) - ENTR MA-216 (BOM JARDIM)'
	}, {
		usr : '158',
		pwd : '0860',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MA-259 (P/TUNTUM) - ENTR MA-012/272 (BARRA DO CORDA)'
	}, {
		usr : '159',
		pwd : '0300',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-222(B) (ESTACA ZERO) - ENTR MA-240 (PIO XII)'
	}, {
		usr : '160',
		pwd : '0730',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'BOM JESUS DO TOCANTINS - RIO JACUNDÁ (R MÃE MARIA)'
	}, {
		usr : '161',
		pwd : '0750',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-122/251/365 (MONTES CLAROS) - ENTR BR-451 (BOCAIÚVA)'
	}, {
		usr : '162',
		pwd : '0240',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-342/MG-404 (SALINAS) - RIO VACARIA'
	}, {
		usr : '163',
		pwd : '0290',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MA-132/270 (COLINAS) - ENTR MA-134 (PEIXE)'
	}, {
		usr : '164',
		pwd : '0030',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-040(B) - ENTR MG-155 (SÃO BRÁS DO SUAÇUÍ)'
	}, {
		usr : '165',
		pwd : '0335',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-434 - ENTR MG-435 (P/CAETÉ)'
	}, {
		usr : '166',
		pwd : '0510',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-275 (P/CARANDAÍ) - ACESSO ALTO DOCE (INÍCIO PISTA DUPLA)'
	}, {
		usr : '167',
		pwd : '0130',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-040(B) - ENTR MG-135'
	}, {
		usr : '168',
		pwd : '0250',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-190 (RIO BAGAGEM) - ACESSO INDIANÓPOLIS'
	}, {
		usr : '169',
		pwd : '0152',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-352(B)/GO-210(B)/330 (CATALÃO) - ENTR GO-402'
	}, {
		usr : '170',
		pwd : '0010',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-050/365/452/455 (UBERLÂNDIA) - ENTR BR-153/464 (PRATA)'
	}, {
		usr : '171',
		pwd : '0310',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-050(B)/455/497 (UBERLÂNDIA) - ENTR BR-452(B)'
	}, {
		usr : '172',
		pwd : '0230',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-190 (P/NOVA PONTE) - ACESSO SANTA JULIANA'
	}, {
		usr : '173',
		pwd : '0110',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO CARLOS CHAGAS - ENTR MG-412'
	}, {
		usr : '174',
		pwd : '0110',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO SÃO JOSÉ DO JACURÍ - ENTR MG-117 (SÃO JOÃO EVANGELISTA)'
	}, {
		usr : '175',
		pwd : '1310',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'TREVO DE ACESSO SÃO JOÃO DO MANHUAÇU - ENTR MG-265 (P/DIVINO)'
	}, {
		usr : '176',
		pwd : '0210',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR ES-185(B) (P/IÚNA) - DIV ES/MG'
	}, {
		usr : '177',
		pwd : '1195',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO ITANHOMI - ENTR BR-458(A) (TURUAÇÚ)'
	}, {
		usr : '178',
		pwd : '0030',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-116(B) - ENTR MG-126 (BICAS)'
	}, {
		usr : '179',
		pwd : '1370',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-265/356 (MURIAÉ) - ENTR MG-285 (LARANJAL)'
	}, {
		usr : '180',
		pwd : '0310',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'PONTE S/ RIO PARÁ - ENTR MG-423(A) (PITANGUI)'
	}, {
		usr : '181',
		pwd : '0195',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO MORADA NOVA DE MINAS - ACESSO SÃO JOSÉ DO BURITI'
	}, {
		usr : '182',
		pwd : '0330',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-367(B) (GOUVEIA) - ACESSO NOSSA SENHORA DA GLÓRIA'
	}, {
		usr : '183',
		pwd : '0910',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-255 - ENTR BR-262(B) (DIV MG/SP)'
	}, {
		usr : '184',
		pwd : '0250',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-462(B) - ENTR BR-262',
		perguntaExtra : true
	}, {
		usr : '185',
		pwd : '0930',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-452 (P/UBERLÂNDIA) - ENTR BR-462 (PERDIZES)',
		perguntaExtra : true
	}, {
		usr : '186',
		pwd : '0070',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-265(B) - CAMPOS GERAIS'
	}, {
		usr : '187',
		pwd : '0030',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-354(B) - ENTR BR-265(A)'
	}, {
		usr : '188',
		pwd : '0190',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-135/338 (BARBACENA) - ACESSO TIRADENTES'
	}, {
		usr : '189',
		pwd : '0670',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-265 (LAVRAS) - ENTR MG-167(A) (P/TRÊS CORAÇÕES)'
	}, {
		usr : '190',
		pwd : '0350',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO FORMIGA - ENTR MG-164 (CANDEIAS)'
	}, {
		usr : '191',
		pwd : '0590',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-270 (CARMÓPOLIS DE MINAS) - ENTR BR-494(A) (P/OLIVEIRA)'
	}, {
		usr : '192',
		pwd : '0230',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-354(A) (P/BAMBUÍ) - ENTR BR-354(B)'
	}, {
		usr : '193',
		pwd : '0205',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO GUIMARÂNIA - ENTR MG-188'
	}, {
		usr : '194',
		pwd : '0090',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-410 - PRESIDENTE OLEGÁRIO'
	}, {
		usr : '195',
		pwd : '0150',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-060 (P/SÃO GONÇALO DO ABAETÉ) - ACESSO GALENA'
	}, {
		usr : '196',
		pwd : '0410',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-154(B)/461/464 (ITUIUTABA) - ACESSO GURINHATÃ'
	}, {
		usr : '197',
		pwd : '0830',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-365 (P/MONTE ALEGRE DE MINAS) - ENTR BR-464/497 (P/PRATA)'
	}, {
		usr : '198',
		pwd : '0170',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-229 (P/SENHORA DO PORTO) - ENTR MG-232 (P/CARMESIA)'
	}, {
		usr : '199',
		pwd : '0370',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-320 (P/SÃO JOSÉ DO GOIABAL) - ENTR BR-120 (VARGEM LINDA)'
	}, {
		usr : '200',
		pwd : '0170',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO À GOV. VALADARES - PERIQUITO'
	}, {
		usr : '201',
		pwd : '0685',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO FLORESTAL - ENTR BR-352 (PARÁ DE MINAS)'
	}, {
		usr : '202',
		pwd : '0050',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MG-050(B) (P/DIVINOPÓLIS) - ENTR MG-260 (P/CLÁUDIO)'
	}, {
		usr : '203',
		pwd : '0270',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO CONCEIÇÃO DO RIO VERDE - ENTR BR-460'
	}, {
		usr : '204',
		pwd : '0023',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO SANTA RITA DAS CALDAS - ENTR MG-179 (POUSO ALEGRE)'
	}, {
		usr : '205',
		pwd : '0270',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-369 (BOTELHOS) - ENTR BR-267(A) (P/BANDEIRA DO SUL)'
	}, {
		usr : '206',
		pwd : '0212',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-487(A) - ACESSO NAVIRAÍ I'
	}, {
		usr : '207',
		pwd : '0612',
		fase : '1',
		ladoA : 'ENTR BR-267(A)',
		ladoB : 'INÍCIO DUPLICAÇÃO',
		trecho : 'ENTR BR-267(A) - INÍCIO DUPLICAÇÃO'
	}, {
		usr : '208',
		pwd : '0090',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MS-141 (IVINHEMA) - VILA AMANDINA'
	}, {
		usr : '209',
		pwd : '1390',
		fase : '1',
		ladoA : 'ENTR MS-339/446/448 (MIRANDA)',
		ladoB : 'ENTR MS-185/243 (GUAICURUS)',
		trecho : 'ENTR MS-339/446/448 (MIRANDA) - ENTR MS-185/243 (GUAICURUS)'
	}, {
		usr : '210',
		pwd : '0058',
		fase : '1',
		ladoA : 'BURITIZINHO',
		ladoB : 'ENTR MS-223(A) (SILVOLÂNDIA)',
		trecho : 'BURITIZINHO - ENTR MS-223(A) (SILVOLÂNDIA)'
	}, {
		usr : '211',
		pwd : '0390',
		fase : '1',
		ladoA : 'ENTR MS-316 (PARAÍSO DAS ÁGUAS)',
		ladoB : 'ENTR MS-324(A)',
		trecho : 'ENTR MS-316 (PARAÍSO) - ENTR MS-324(A)'
		// perguntaExtra : true // Possuiu pergunta extra na 1ª fase
	}, {
		usr : '212',
		pwd : '0180',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-158(B) (P/TAMBOARA) - ACESSO ALTO PARANÁ/MARISTELA'
	}, {
		usr : '213',
		pwd : '0050',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-425 (PRESIDENTE PRUDENTE) - ENTR SP-425'
	}, {
		usr : '214',
		pwd : '0330',
		fase : '1',
		ladoA : 'ENTR MT-373',
		ladoB : 'ENTR MT-130(A) (PRIMAVERA DO LESTE)',
		trecho : 'ENTR MT-373 - ENTR MT-130(A) (PRIMAVERA DO LESTE)'
	}, {
		usr : '215',
		pwd : '0765',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'FIM VARIANTE II SERRA DE SÃO VICENTE - ACESSO DISTRITO INDUSTRIAL'
	}, {
		usr : '216',
		pwd : '0150',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-010(B) (P/SANTA MARIA DO PARÁ) - ENTR PA-324 (P/SALINÓPOLIS)'
	}, {
		usr : '217',
		pwd : '0270',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PB-177 (SOLEDADE) - ENTR PB-195 (JUAZEIRINHO)'
	}, {
		usr : '218',
		pwd : '0322',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'BARRA DE SANTANA - ENTR PB-196 (P/RIACHO DE SANTO ANTÔNIO)'
	}, {
		usr : '219',
		pwd : '0280',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-263 (P/ITAPETIM) - ENTR PE-320 (SÃO JOSÉ DO EGITO)'
	}, {
		usr : '220',
		pwd : '0410',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-015 (P/PAULISTA - VIADUTO SOBRE PE-015) - VIADUTO SOBRE AVENIDA CAXANGÁ'
	}, {
		usr : '221',
		pwd : '0530',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'DIV PE/AL - ENTR BR-423(A)'
	}, {
		usr : '222',
		pwd : '0630',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-590 (P/IPUBÍ) - ENTR BR-122 (OURICURÍ)'
	}, {
		usr : '223',
		pwd : '0250',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-292(B) - DIV CE/PE'
	}, {
		usr : '224',
		pwd : '0300',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PE-310/312 (CUSTÓDIA) - ENTR BR-426/PE-340 (SÍTIO DOS NUNES)'
	}, {
		usr : '225',
		pwd : '0250',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'RAJADA - ENTR BR-235(A)'
	}, {
		usr : '226',
		pwd : '0115',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PI-394 - ENTR PI-140 (CANTO DO BURITI)'
	}, {
		usr : '227',
		pwd : '0070',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PI-211 (CAXINGÓ) - ENTR PI-213(A)'
	}, {
		usr : '228',
		pwd : '0190',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO OESTE SOBRAL - ENTR BR-403(B)/CE-183'
	}, {
		usr : '229',
		pwd : '0330',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MA-034 (SÃO JOÃO DOS PATOS) - ENTR BR-230(B)/MA-364(A) (DOIS IRMÃOS)'
	}, {
		usr : '230',
		pwd : '0290',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR PI-130 (P/AMARANTE) - ENTR PI-120'
	}, {
		usr : '231',
		pwd : '0210',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-252 (GUAPIARA) - ENTR SP-165/249 (APIAÍ)'
	}, {
		usr : '232',
		pwd : '0180',
		fase : '1',
		ladoA : 'ENTR PR-549 (P/LUIZIANA)',
		ladoB : 'ENTR PR-462 (P/IRETAMA)',
		trecho : 'ENTR PR-549 (P/LUIZIANA) - ENTR PR-462 (P/IRETAMA)'
	}, {
		usr : '233',
		pwd : '0080',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO IVAIPORÃ - ENTR BR-487 (P/MANOEL RIBAS)'
	}, {
		usr : '234',
		pwd : '0250',
		fase : '1',
		ladoA : 'ENTR BR-158(B) (LARANJEIRAS DO SUL)',
		ladoB : 'ENTR PR-473',
		trecho : 'ENTR BR-158(B) (LARANJEIRAS DO SUL) - ENTR PR-473'
	}, {
		usr : '235',
		pwd : '0455',
		fase : '1',
		ladoA : 'ACESSO ÁGUA MINERAL SANTA CLARA',
		ladoB : 'ENTR PR-281(A) (P/CHOPINZINHO)',
		trecho : 'ACESSO ÁGUA MINERAL SANTA CLARA - ENTR PR-281(A) (P/CHOPINZINHO)'
	}, {
		usr : '236',
		pwd : '0500',
		fase : '1',
		ladoA : 'ENTR PR-468(B)',
		ladoB : 'ENTR PR-180 (GOIO ERÊ)',
		trecho : 'ENTR PR-468(B) - ENTR PR-180 (GOIO ERÊ)'
	}, {
		usr : '237',
		pwd : '1230',
		fase : '1',
		ladoA : 'ENTR PR-431(B)',
		ladoB : 'ENTR PR-092(A) (P/BARRA DO JACARÉ)',
		trecho : 'ENTR PR-431(B) - ENTR PR-092(A) (P/BARRA DO JACARÉ)'
	}, {
		usr : '238',
		pwd : '1140',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-331(A) - ENTR SP-331(B)'
	}, {
		usr : '239',
		pwd : '0230',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-369 - ENTR SP-261 (P/ÁGUAS DE SANTA BÁRBARA)'
	}, {
		usr : '240',
		pwd : '0710',
		fase : '1',
		ladoA : 'ACESSO PIRAPÓ',
		ladoB : 'ACESSO CAMBIRA',
		trecho : 'ACESSO PIRAPÓ - ACESSO CAMBIRA'
	}, {
		usr : '241',
		pwd : '0410',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO PARAÍBA DO SUL - ENTR RJ-135 (ANDRADE PINTO)'
	}, {
		usr : '242',
		pwd : '2850',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RJ-196 - ENTR RJ-182'
	}, {
		usr : '243',
		pwd : '0590',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ITAMONTE - ENTR BR-485 (GARGANTA DO REGISTRO)'
	}, {
		usr : '244',
		pwd : '3350',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RJ-149 (P/RIO CLARO) - ENTR BR-494(A) (ANGRA DOS REIS)'
	}, {
		usr : '245',
		pwd : '0530',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RJ-145 (P/BARRA DO PIRAÍ) - ENTR RJ-141 (DORANDIA)'
	}, {
		usr : '246',
		pwd : '0170',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-494 (ARANTINA) - AIURUOCA'
	}, {
		usr : '247',
		pwd : '0170',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RJ-198(B) - ENTR RJ-200 (MONTE ALEGRE)'
	}, {
		usr : '248',
		pwd : '0350',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RJ-202 - ENTR RJ-234 (P/PENEDO)'
	}, {
		usr : '249',
		pwd : '0150',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-482(B) - ENTR ES-181'
	}, {
		usr : '250',
		pwd : '0210',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-116/265 (MURIAÉ) - DIV MG/RJ'
	}, {
		usr : '251',
		pwd : '1530',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RJ-156 (P/VOLTA DO PIÃO) - ENTR BR-492(A)'
	}, {
		usr : '252',
		pwd : '0065',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO À JENIPABU - ENTR RN-160(B) (P/EXTREMOZ)'
	}, {
		usr : '253',
		pwd : '0110',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'TAIPÚ - ENTR RN-064 (P/CEARÁ MIRIM)'
	}, {
		usr : '254',
		pwd : '0055',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR CE-261 - DIV CE/RN'
	}, {
		usr : '255',
		pwd : '0210',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-230(A) (SANTA GERTRUDES) - ENTR PB-275(A) (P/SÃO JOSÉ DE ESPINHARA)'
	}, {
		usr : '256',
		pwd : '1375',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO USINA DE SAMUEL - INICIO PISTA DUPLA'
	}, {
		usr : '257',
		pwd : '1510',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'VISTA ALEGRE DO ABUANÃ - VILA EXTREMA'
	}, {
		usr : '258',
		pwd : '1300',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-421 (ARIQUEMES) - ENTR RO-459(A) (ALTO PARAISO)'
	}, {
		usr : '259',
		pwd : '0031',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RO-135 (P/NOVA LONDRINA) - FIM DO TRECHO PAVIMENTADO'
	}, {
		usr : '260',
		pwd : '1148',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RO-471 (P/MINISTRO ANDREAZZA) - ENTR RO-479(A) (P/ROLIM DE MOURA)'
	}, {
		usr : '261',
		pwd : '1090',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR RO-391 (POSTO GUAPORÉ) - MARCO RONDON'
	}, {
		usr : '262',
		pwd : '0132',
		fase : '1',
		ladoA : 'PADRONAL',
		ladoB : 'RIO 12 DE OUTUBRO',
		trecho : 'PADRONAL - RIO 12 DE OUTUBRO'
	}, {
		usr : '263',
		pwd : '4425',
		fase : '1',
		ladoA : 'ENTR ACESSO SUL DE CAPÃO DA CANOA',
		ladoB : 'ENTR BR-290(A) (OSÓRIO)',
		trecho : 'ENTR ACESSO SUL DE CAPÃO DA CANOA - ENTR BR-290(A) (OSÓRIO)'
	}, {
		usr : '264',
		pwd : '0330',
		fase : '1',
		ladoA : 'ENTR BR-470',
		ladoB : 'ENTR BR-470/116(A) (CANOAS)',
		trecho : 'ENTR RSC-470 (P/ MONTENEGRO) - ENTR ERS-124 (P/ POLO PETROQUIMICO)'
	}, {
		usr : '265',
		pwd : '0210',
		fase : '1',
		ladoA : 'ENTR BR-153(B)/RS-324 (P/PONTÃO)',
		ladoB : 'ENTR BR-377(A)/386 (CARAZINHO)',
		trecho : 'ENTR BRS-153(B)/ERS-324 (P/ PONTAO) - ENTR BRS-377(A)/386 (P/ CARAZINHO)'
	}, {
		usr : '266',
		pwd : '0470',
		fase : '1',
		ladoA : 'ENTR RS-168 (P/SÃO PAULO DAS MISSÕES)',
		ladoB : 'ENTR BR-472 (FRONT BRASIL/ARGENTINA) (PORTO XAVIER)',
		trecho : 'ENTR RS-168 (P/SÃO PAULO DAS MISSÕES) - ENTR BR-472 (FRONT BRASIL/ARGENTINA) (PORTO XAVIER)'
	}, {
		usr : '267',
		pwd : '0140',
		fase : '1',
		ladoA : 'A. BELTRÃO',
		ladoB : 'ENTR BR-287 (SANTIAGO)',
		trecho : 'ENTR ERS-533 (P/ CAPAO CIPO) - ENTR BRS-287 (CONTORNO DE SANTIAGO)'
	}, {
		usr : '268',
		pwd : '1850',
		fase : '3',
		ladoA : 'ENTR BR-290(B)',
		ladoB : 'ENTR BR-392',
		trecho : 'ENTR BRS-290(B) (P/ SAO GABRIEL) - ENTR BRS-392 (P/ CACAPAVA DO SUL)'
	}, {
		usr : '269',
		pwd : '1350',
		fase : '1',
		ladoA : 'ENTR BR-290(A) (P/ROSÁRIO DO SUL)',
		ladoB : 'ENTR RS-640 (P/CACEQUI)',
		trecho : 'ENTR BRS-158(A) (P/ AZEVEDO SODRE) - ENTR ERS-640 (P/ CACEQUI)'
	}, {
		usr : '270',
		pwd : '0230',
		fase : '1',
		ladoA : 'ENTR BR-473 (SARANDI)',
		ladoB : 'CURRAL ALTO',
		trecho : 'ENTR RSC-473 (VILA SARANDI) - CURRAL ALTO'
	}, {
		usr : '271',
		pwd : '0310',
		fase : '1',
		ladoA : 'ENTR RS-476 (LAJEADO GRANDE)',
		ladoB : 'ENTR RS-110 (VÁRZEA DO CEDRO)',
		trecho : 'ENTR ERS-476 (LAJEADO GRANDE) - ENTR ERS-110 (VARZEA DO CEDRO)'
	}, {
		usr : '272',
		pwd : '0015',
		fase : '1',
		ladoA : 'ENTR BR-282(B)/470(A)',
		ladoB : 'ENTR SC-455 (P/IBICUÍ)',
		trecho : 'ENTR BR-282(B)/470(A) - ENTR SC-455 (P/IBICUÍ)'
	}, {
		usr : '273',
		pwd : '3010',
		fase : '1',
		ladoA : 'DIV SC/RS (RIO PELOTAS)',
		ladoB : 'ENTR BR-285(A) (P/VACARIA)',
		trecho : 'DIVISA SC/RS (RIO PELOTAS) - ENTR BRS-285(A) (P/ VACARIA)'
	}, {
		usr : '274',
		pwd : '0110',
		fase : '1',
		ladoA : 'ENTR BR-486 (BOM RETIRO)',
		ladoB : 'ENTR SC-345(B) (P/URUBICI)',
		trecho : 'ENTR BR-486 (BOM RETIRO) - ENTR SC-345(B) (P/URUBICI)'
	}, {
		usr : '275',
		pwd : '0027',
		fase : '1',
		ladoA : 'ENTR SC-473 (P/ANCHIETA)',
		ladoB : 'SÃO JOSÉ DO CEDRO',
		trecho : 'ENTR SC-473 (P/ANCHIETA) - SÃO JOSÉ DO CEDRO'
	}, {
		usr : '276',
		pwd : '0290',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-280(A) - ENTR BR-280(B) (VITORINO)'
	}, {
		usr : '277',
		pwd : '0612',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BA-392 (ANTAS) - ENTR BA-220 (CÍCERO DANTAS)'
	}, {
		usr : '278',
		pwd : '0190',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-183 - ENTR BR-116(A) (P/LORENA)'
	}, {
		usr : '279',
		pwd : '2360',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-062 (P/ROSEIRA) - ENTR BR-383(A)/SP-132 (PINDAMONHANGABA)'
	}, {
		usr : '280',
		pwd : '2470',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-065 (P/IGARATÁ) - ENTR SP-056 (ARUJÁ)'
	}, {
		usr : '281',
		pwd : '2630',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-116(A) (JUQUIÁ) - ENTR SP-139 (REGISTRO)'
	}, {
		usr : '282',
		pwd : '0130',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-373(A) (ITAPETININGA) - ENTR BR-373(B)'
	}, {
		usr : '283',
		pwd : '1077',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO GUAIMBÉ - ENTR SP-333(A)'
	}, {
		usr : '284',
		pwd : '1010',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-355 - ENTR SP-425'
	}, {
		usr : '285',
		pwd : '0110',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-255 (ARARAQUARA) - ENTR BR-364(B)'
	}, {
		usr : '286',
		pwd : '0783',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO RINÓPOLIS - ENTR SP-294 (PARAPUÃ)'
	}, {
		usr : '287',
		pwd : '0850',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR SP-065 (ATIBAIA) - ENTR SP-023 (MARIPORÃ)'
	}, {
		usr : '288',
		pwd : '0010',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-222/230(A)/PA-150 (MARABÁ) - ENTR PA-405'
	}, {
		usr : '289',
		pwd : '0390',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-010(A) (PORTO FRANCO) - ENTR BR-010(B)/230(A)/MA-138 (ESTREITO)'
	}, {
		usr : '290',
		pwd : '1010',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR MA-012/375 (SÃO RAIMUNDO DAS MANGABEIRAS) - ENTR MA-006(A)'
	}, {
		usr : '291',
		pwd : '0290',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'FIM PISTA DUPLA - GURUPÍ - ENTR BR-242(B)/TO-280'
	}, {
		usr : '292',
		pwd : '0330',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-020(A)/135(B)/BA-020 - ENTR BR-020(B)'
	}, {
		usr : '293',
		pwd : '0272',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'INDIANÓPOLIS - VALE DO SONHO'
	}, {
		usr : '294',
		pwd : '0210',
		fase : '4',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR GO-217 (P/MAIRIPOTABA) - ENTR GO-320 (INDIARA)'
	}, {
		usr : '295',
		pwd : '0370',
		fase : '1',
		ladoA : 'ENTR BR-153 (P/TIBAGI/IPIRANGA)',
		ladoB : 'ENTR BR-373(A)/487(A) (CAETANO)',
		trecho : 'ENTR BR-153 (P/TIBAGI/IPIRANGA) - ENTR BR-373(A)/487(A) (CAETANO)'
	}, {
		usr : '296',
		pwd : '0390',
		fase : '1',
		ladoA : 'RIO ANHANDUÍ',
		ladoB : 'ENTR BR-262(A) (CAMPO GRANDE)',
		trecho : 'RIO ANHANDUÍ - ENTR BR-262(A) (CAMPO GRANDE)'
	}, {
		usr : '297',
		pwd : '0434',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'ACESSO ITAJÁ (GO) - ENTR MS-431 (P/SÃO JOÃO DO APORÉ)'
	}, {
		usr : '298',
		pwd : '0930',
		fase : '3',
		ladoA : '',
		ladoB : '',
		trecho : 'FNM (ENTR PISTA INVERSA) - ENTR BR-116(A)/493/RJ-109'
	}, {
		usr : '299',
		pwd : '0110',
		fase : '1',
		ladoA : 'ENTR SC-418 (P/POMERODE)',
		ladoB : 'ENTR BR-477(B) (P/TIMBÓ)',
		trecho : 'ENTR SC-418 (P/POMERODE) - ENTR BR-477(B) (P/TIMBÓ)'
	}, {
		usr : '300',
		pwd : '0110',
		fase : '1',
		ladoA : 'ENTR BR-116(B)',
		ladoB : 'P/TRES BARRAS',
		trecho : 'ENTR BR-116(B) - P/TRES BARRAS'
	}, {
		usr : '301',
		pwd : '0150',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'DIV PI/BA - ENTR BR-235/BA-161 (REMANSO)'
	}, {
		usr : '302',
		pwd : '0770',
		fase : '2',
		ladoA : '',
		ladoB : '',
		trecho : 'ENTR BR-369(A) (BARBOSA FERRAZ) - ENTR BR-158(A)/369(B) (ANEL VIÁRIO CAMPO MOURÃO)'
	} ],

	user_logado : {}
};
