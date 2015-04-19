/* States */

qcmApp
    .config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
    	
            // Use $urlRouterProvider to configure any redirects (when) and invalid urls (otherwise).
            $urlRouterProvider
                .when('/', '/')
                .otherwise('home');

            // State Configurations
            $stateProvider
                .state("home",{
                    url:"/home",
                    templateUrl: baseUrl+'/app/prof/view/index.html',
                    controller: 'IndexProfController'
                });
               
        }
    ])   
;