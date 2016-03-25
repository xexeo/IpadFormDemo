controllers.menu = {
  config : function(){
                $('#menu_nova_pesquisa').click(function() {
                    //clear registro
                    registro = {};
                    app.trocaPagina("selecionar_tipo.html", controllers.selecionar_tipo);
                    
                });
            },  
};

