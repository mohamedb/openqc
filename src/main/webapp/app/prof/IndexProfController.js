qcmApp.controller('IndexProfController', [
    '$scope',
    '$http',
    '$state',
    'notify',
    function ($scope, $http, $state, notify) {

        var notificationTemplateUrl = baseUrl + '/app/shared/template/gmail-notif.html';

        $scope.users = null;
        $http({
            url: baseUrl + "/ws/etudiants",
            method: 'GET',
            data: {"a": "b"}
        }).success(function (data, status, headers, config) {
            $scope.users = data;
        });
        $http({
            url: baseUrl + "/ws/auth/current",
            method: 'GET',
            data: {"a": "b"}
        }).success(function (data, status, headers, config) {
            notify({
                message: "Welcome " + data + " on the App",
                templateUrl: notificationTemplateUrl
            });
        });

    }
]);