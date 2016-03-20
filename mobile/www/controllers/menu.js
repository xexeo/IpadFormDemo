controllers.menu = {
  config : function(){
                $('#menu_nova_pesquisa').click(function() {
                    app.trocaPagina("selecionar_tipo.html", controllers.selecionar_tipo);
                    
                });
            },  
};

