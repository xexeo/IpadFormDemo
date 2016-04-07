controllers.menu = {
  config : function(){
                $('#menu_nova_pesquisa').click(function() {
                    //clear registro
                    app.limpaRegistro();
                    app.trocaPagina("views/selecionar_tipo.html", controllers.selecionar_tipo);
                });
                
                $("#duplica_log").click(function(){
                    myLogger.copyLog();
                });
            },  
            
};

