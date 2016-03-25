var paises = {
    
    listados : function(){
        return paises.america_sul.concat(paises.america_norte_central_caribe,
                                paises.africa, paises.asia, paises.europa, paises.oceania); 
    },

    america_sul : ['Argentina', 'Bolívia', 'Brasil', 'Chile', 'Colômbia', 
        'Equador', 'Guiana', 'Paraguai', 'Peru', 'Suriname', 'Uruguai', 'Venezuela'],

    america_norte_central_caribe : ['Antígua e Barbuda', 'Bahamas', 'Barbados',
        'Belize', 'Canadá', 'Costa Rica', 'Cuba', 'Domínica', 'República Dominicana',
        'El Salvador', 'Granada', 'Guatemala', 'Haiti', 'Honduras', 'Jamaica', 
        'México', 'Nicarágua', 'Panamá', 'São Cristóvão e Neves', 'Santa Lúcia', 
        'São Vicente e Granadinas', 'Trinidad e Tobago', 'Estados Unidos'],

    africa : ['Argélia', 'Angola', 'Benim', 'Botsuana', 'Burkina Faso',
        'Burundi', 'Camarões', 'Cabo Verde', 'República Centro-Africana',
        'Chade', 'Comores', 'República Democrática do Congo', 'Djibouti',
        'Egito', 'Guiné Equatorial', 'Eritreia', 'Etiópia', 'Gabão', 'Gâmbia',
        'Gana', 'Guiné', 'Guiné-Bissau', 'Costa do Marfim', 'Quénia', 'Lesoto',
        'Libéria', 'Líbia', 'Madagascar', 'Malawi', 'Mali', 'Mauritânia',
        'Mauritius', 'Marrocos', 'Moçambique', 'Namíbia', 'Níger', 'Nigéria',
        'Ruanda', 'São Tomé e Príncipe', 'Senegal', 'Seicheles', 'Serra Leoa',
        'Somália', 'África do Sul', 'Sudão do Sul', 'Sudão', 'Suazilândia',
        'Tanzânia', 'Togo', 'Tunísia', 'Uganda', 'Zâmbia', 'Zimbabwe'],

    europa : ['Albânia', 'Andorra', 'Arménia', 'Áustria', 'Azerbaijão',
        'Bielorrússia', 'Bélgica', 'Bósnia e Herzegovina', 'Bulgária',
        'Croácia', 'Chipre', 'República Tcheca', 'Dinamarca', 'Estónia',
        'Finlândia', 'França', 'Geórgia', 'Alemanha', 'Grécia', 'Hungria',
        'Islândia', 'Irlanda', 'Itália', 'Letónia', 'Liechtenstein ',
        'Lituânia', 'Luxemburgo', 'Macedónia', 'Malta', 'Moldávia', 'Mónaco',
        'Montenegro', 'Países Baixos', 'Noruega', 'Polónia', 'Portugal',
        'Roménia', 'São Marino', 'Sérvia', 'Eslováquia', 'Eslovénia',
        'Espanha', 'Suécia', 'Suíça', 'Ucrânia', 'Reino Unido', 'Vaticano'],

    oceania : ['Austrália', 'Fiji', 'Kiribati', 'Ilhas Marshall', 'Micronésia',
        'Nauru', 'Nova Zelândia', 'Palau', 'Papua-Nova Guiné', 'Samoa',
        'Ilhas Salomão', 'Tonga', 'Tuvalu', 'Vanuatu'],

    asia : ['Afeganistão', 'Bahrein', 'Bangladeche', 'Butão', 'Brunei', 'Burma', 
        'Camboja', 'China', 'Timor Leste', 'India', 'Indonésia', 'Irã', 'Iraque', 
        'Israel', 'Japão', 'Jordânia', 'Cazaquistão', 'Coréia do Norte', 
        'Coréia do Sul', 'Kuwait', 'Quirguistão', 'Laos', 'Líbano', 'Malásia', 
        'Maldivas', 'Mongólia', 'Nepal', 'Omã', 'Paquistão', 'Filipinas', 'Qatar', 
        'Rússia', 'Arábia Saudita', 'Singapura', 'Sri Lanka', 'Síria', 'Tajaquistão', 
        'Tailândia', 'Turquia', 'Turcomenistão', 'Emirados Arábes Unidos', 
        'Uzbequistão', 'Vietname', 'Iémen']

};
