controllers.menu = {
  config : function(){
                $('#menu_nova_pesquisa').click(function() {
                    //clear registro
                    app.iniciaRegistro();
                    app.trocaPagina("views/selecionar_tipo.html", controllers.selecionar_tipo);
                });
                
                $("#duplica_log").click(function(){
                    myLogger.copyLog();
                });
            },  
            
};
