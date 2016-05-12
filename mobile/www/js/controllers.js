var controllers = controllers || {};

//insert controller files
var insert_controllers={
    //files to be inserted
    controller_files : ['menu.js', 
                        'selecionar_tipo.js',
                        'confirmar_veiculo.js', 
                        'simples/identificacao_visual_simples.js',
                        'simples/caracterizacao_simples.js',
                        'simples/caracterizacao_viagem_simples.js',
                        'onibus/identificacao_visual_onibus.js',
                        'onibus/caracterizacao_onibus.js',
                        'onibus/caracterizacao_viagem_onibus.js',
                        'carga/identificacao_visual_carga.js',
                        'carga/caracterizacao_carga.js',
                        'carga/caracterizacao_viagem_carga.js',
                        'carga/caracterizacao_viagem2_carga.js',
                        'pergunta_extra.js'
                    ],
    
    controller_files_path : 'controllers/',
    
    insert : function(){
            var me = this; 
            $.each(me.controller_files,
                    function(index, item){
                        var s = document.createElement("script");
                        s.type = "text/javascript";
                        s.src = me.controller_files_path + item;
                        $("head").append(s);
                        //$(document).append(s);
                });
    }
};
