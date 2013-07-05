var WhatDoHow = WhatDoHow || {
    Models: {},
    Collections: {},
    Views: {},
    Layouts: {},
    Controllers: {},
    Routers: {}
};


/**
MODELS
**/
WhatDoHow.Models.Statement = Backbone.Model.extend({
    
    urlRoot: '',

    defaults: {
        class: null,
        id: null,
        statewhat: null,
        statedo: null,
        statehow: null,
        location: null
    }
});


/**
COLLECTIONS
**/
WhatDoHow.Collections.Statements = Backbone.Collection.extend({
    
    model: WhatDoHow.Models.Statement,

    url: "statement/getstatements"

});


/**
VIEWS
**/
WhatDoHow.Views.StatementFormView = Backbone.Marionette.ItemView.extend({
    template: '#statement-form-template',

    ui: {
        whatStatement: "#what_input",
        doStatement: "#do_input",
        howStatement: "#how_input",
        locationStatement: "#location_input"
    },

    events: {
        'keyup ' : 'getStatements'
    },

    getStatements: function(){
        WhatDoHow.App.controller.showStatements(
            this.ui.whatStatement.val(), 
            this.ui.doStatement.val(), 
            this.ui.howStatement.val(), 
            this.ui.locationStatement.val());
    }
});


WhatDoHow.Views.StatementsView = Backbone.Marionette.CollectionView.extend({
});


WhatDoHow.Views.StatementView = Backbone.Marionette.ItemView.extend({
    
    model: WhatDoHow.Models.Statement,

    template: '#statement-template'

});

/**
CONTROLLERS
**/
WhatDoHow.Controllers.ViewController = Backbone.Marionette.Controller.extend({
    
    showStatements: function(whatStatement, doStatement, howStatement, locationStatement){
        //console.log(whatStatement + doStatement + howStatement + locationStatement);
        var statements = new WhatDoHow.Collections.Statements();
        statements.fetch({
            data: $.param({
                statementWhat: whatStatement,
                statementDo: doStatement,
                statementHow: howStatement,
                statementLocation: locationStatement
            }), 

            success: function(){
                var statementsView = new WhatDoHow.Views.StatementsView({
                    itemView: WhatDoHow.Views.StatementView,
                    collection: statements
                });
                WhatDoHow.App.statementsRegion.show(statementsView);
            }
        });
    }

});


/**
APP
**/
WhatDoHow.App = new Backbone.Marionette.Application();

WhatDoHow.App.addRegions({
    formRegion: "#form-region",
    statementsRegion: "#statements-region"
});

WhatDoHow.App.addInitializer(function(){
    WhatDoHow.App.controller = new WhatDoHow.Controllers.ViewController();
    WhatDoHow.App.formRegion.show(new WhatDoHow.Views.StatementFormView());
    // Backbone.history.start();
});

$(document).ready(function(){
    WhatDoHow.App.start();
});




