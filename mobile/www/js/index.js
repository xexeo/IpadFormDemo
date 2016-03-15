var app={

/*
    Application constructor
 */
    initialize: function() {
        this.bindEvents();
        this.extraConfig();
    },
/*
    bind any events that are required on startup to listeners:
*/
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },

    
/*
    this runs when the device is ready for user interaction:
*/
    onDeviceReady: function() {

    },
/*
    appends @error to the message div:
*/
    showError: function(error) {
        app.display(error);
    },

    extraConfig : function(){
        //initialize panel
        $(function () { $("[data-role=panel]").panel().enhanceWithin(); });
   }


};      // end of app

$( document ).ready(function(){app.initialize()});
