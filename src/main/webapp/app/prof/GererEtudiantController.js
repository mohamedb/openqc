qcmApp.controller('GererEtudiantController', [
    '$scope',
    '$http',
    '$state',
    'qcmData',
    function ($scope, $http, $state,qcmData) {        
        $scope.etudiants=null;
        $scope.listeEtudiants=null;
        var getData= function (){
        qcmData.getEtudiants()
               		.then(function(data){
               			$scope.etudiants = data;
                                $scope.listeEtudiants= data;
               		}, function(data){
               			//$scope.alerts.push({type:'danger',msg:data});
               		});
                    };
        getData();
        $scope.supprimerEtudiant= function (idEtudiant){          
             $http({
                url: baseUrl+"/etudiant/del",
                method: 'POST',
                data: {"idEtudiant": idEtudiant}
            })
            .success(function (data, status, headers, config) {
                // $scope.etudiants=null;
                //  getData();
                window.location.reload();
                        
            });
        };
        $scope.formations=null;
        $scope.formData={};
        $scope.formData.etudiantSelectionne=null;
        $scope.formData.formationSelectionne=null;
        $scope.formData.sujetSelectionne=null;
        qcmData.getFormations()
               		.then(function(formations){
               			$scope.formations = formations;
               		}, function(data){
               			//$scope.alerts.push({type:'danger',msg:data});
               		});
                    
        
        $scope.submitForm = function(isValid) {
             
            if (isValid){
                console.log("idE: "+$scope.formData.etudiantSelectionne.id+"idF "+$scope.formData.formationSelectionne.id+"idS"+$scope.formData.sujetSelectionne.id);
                var inscription={
                    idEtudiant:$scope.formData.etudiantSelectionne.id,
                    idFormation:$scope.formData.formationSelectionne.id,
                    idSujet:$scope.formData.sujetSelectionne.id
                };
                
                $http({
                url: baseUrl + "/etudiant/inscrire",
                method: 'POST',
                data: {
                    "inscription": inscription
                }
                })
                .success(function (data, status, headers, config) {
                    if(data=="ok"){
                        alert("Inscription prise en compte");
                        $scope.formData={};
                    }
                            
                });
                
            }
        };
        
        
        
        //end controller!
    }
]);