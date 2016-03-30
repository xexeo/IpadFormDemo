var controllers = controllers || {};

//insert controller files
var insert_controllers={
    //files to be inserted
    controller_files : ['menu.js', 
                        'selecionar_tipo.js',
                        'confirmar_veiculo.js', 
                        'identificacao_visual_simples.js',
                        'caracterizacao_simples.js',
                        'caracterizacao_viagem_simples.js'
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
