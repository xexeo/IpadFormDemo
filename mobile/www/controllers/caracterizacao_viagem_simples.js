controllers.caracterizacao_viagem_simples = {
    config : function(){
        var me = controllers.caracterizacao_viagem_simples;
		me.inicializaElementos();
		me.progressoTela();
        me.buttons();
    },
    
    buttons : function(){
       $("#caracterizacao_viagem_simples_avancar").click(function(){
           // TODO Salvar dados do ciclo de consulta
    	   
    	   // Ir para tela inicial
    	   app.trocaPagina('views/menu.html', controllers.menu);
       })
    },
    
	//Inicializa os elementos da tela
    inicializaElementos : function(){

    	// INICIO PAÍSES
        util.inicializaSelectCustom("origem_pais_simples", paises.listados(), "País");
        util.inicializaSelectCustomValueAsIndex("origem_uf_simples", lista_estados, "UF");
        
        util.inicializaSelectCustom("destino_pais_simples", paises.listados(), "País");
        util.inicializaSelectCustomValueAsIndex("destino_uf_simples", lista_estados, "UF");
        //FIM PAÍSES
        
        var lista_frequencias = ['Dia', 'Semana', 'Mês', 'Ano', 'Eventualmente'];
        util.inicializaSelect("frequencia_sel_simples", lista_frequencias);

        var lista_motivos_rota = ['Asfalto/Sinalização', 'Caminho mais curto', 'Caminho mais rápido', 
                                  'Proximidade hotéis/postos', 'Segurança', 'Turismo/Paisagem', 'Ausência de pedágio',
                                  'Ponto obrigatório de passagem', 'Outros'];
        util.inicializaSelect("motivo_rota_simples", lista_motivos_rota);
        
        var lista_motivos_viagem = ['Compra', 'Estudo', 'Lazer', 'Saúde','Trabalho', 'Negócios', 'Outros'];
        util.inicializaSelect("motivo_viagem_simples", lista_motivos_viagem);
        
        var lista_rendas = ['Até R$ 1.500,00', 'De R$ 1.501,00 a R$ 3.000,00', 'De R$ 3.001,00 a R$ 4.500,00',
                            'De R$ 4.501,00 a R$ 6.500,00', 'De R$ 6.501,00 a R$ 10.500,00', 'Acima de R$ 10.501,00',
                            'Não informada', 'Sem renda própria'];
        util.inicializaSelect("renda_simples", lista_rendas);
	},

	
	//Controla o show e hide dos elementos da tela
	progressoTela : function() {
		
		//Origem
		util.progressoSelectPais("origem_pais", "origem_pais_simples", "origem_uf_simples", "origem_municipio_simples", "grupo_destino_simples");
		util.progressoSelect("origem_uf", "origem_uf_simples", "grupo_origem_municipio_simples");
        $('#origem_uf_simples').change(function() {
			if ($(this).val() != '-1') {
				util.inicializaSelectMunicipio("origem_municipio_simples", $(this).val(), "Município");
			}
		});
		util.progressoSelect("origem_municipio", "origem_municipio_simples", "grupo_destino_simples");
		
		//Destino
		util.progressoSelectPais("destino_pais", "destino_pais_simples", "destino_uf_simples", "destino_municipio_simples", "grupo_frequencia_simples");
		util.progressoSelect("destino_uf", "destino_uf_simples", "grupo_destino_municipio_simples");
        $('#destino_uf_simples').change(function() {
			if ($(this).val() != '-1') {
				util.inicializaSelectMunicipio("destino_municipio_simples", $(this).val(), "Município");
			}
		});
		util.progressoSelect("destino_municipio", "destino_municipio_simples", "grupo_frequencia_simples");
        
		//Frequencia
        $('#frequencia_num_simples').keyup(function(){
        	app.setAtributo('frequencia_num', $(this).val());
        });
        util.progressoSelect("frequencia_sel", "frequencia_sel_simples", "grupo_motivo_rota_simples");
        
        //Motivo rota
        util.progressoSelect("motivo_rota", "motivo_rota_simples", "grupo_pessoas_simples");
        
        //Pessoas
        util.progressoInputText("pessoas", "pessoas_simples", "grupo_pessoas_trabalho_simples");
        util.progressoInputText("pessoas_trabalho", "pessoas_trabalho_simples", "grupo_motivo_viagem_simples");
        
        //Motivo viagem
        util.progressoSelect("motivo_viagem", "motivo_viagem_simples", "grupo_renda_simples");
        
        //Renda
        util.progressoSelect("renda", "renda_simples", "grupo_caracterizacao_viagem_simples_avancar");
    }
};

