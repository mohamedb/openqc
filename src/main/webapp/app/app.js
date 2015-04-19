"use strict";

var underscore = angular.module('underscore', []);
underscore.factory('_', function () {
    return window._; /* Underscore must already be loaded on the page */
});


var qcmApp = angular.module('qcmApp',
        ['ui.router', 'ngAnimate', 'ui.load', 'ngSanitize', 'ngCookies', 'ui.bootstrap', 'textAngular', 'underscore','cgNotify']);

qcmApp.run(
        ['$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
                $rootScope.pageTitle = 'Bienvenu !';
            }
        ]
        );



