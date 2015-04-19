/* Factories */
qcmApp
    .factory('utils', function () {
        return {
            //  
            findById: function findById(a, id) {
                for (var i = 0; i < a.length; i++) {
                    if (a[i].id == id) return a[i];
                }
                return null;
            }
        };
    });
qcmApp
    .factory('qcmData', function ($http,$q) {
    	/*
    	 * Get Message from the server
    	 */
    	var service={};
        var donnees=[];
        var donneesFormations=[];	 
        var transformerListeEnTableau= function(serviceObjet){
            donnees.push(serviceObjet);                 	 
  	};
        var transformerFormationsEnTableau= function(serviceObjet){
            donneesFormations.push(serviceObjet);                 	 
  	};
    	service.getEtudiants= function(){
    		 var deferred = $q.defer();
    		 $http.get(baseUrl+"/etudiant")
             .success(function(data,status,headers,config){
                                 _.each(data, transformerListeEnTableau);
                                   deferred.resolve(donnees);
                                }
                     )
             .error(function(data,status,headers,config){                        
                               deferred.reject('There was an error while getting Etudiants data!');
                        }
                   );	 
    	     return deferred.promise;
    	 };
    	service.getSujets= function(){
    		 var deferred = $q.defer();
    		 $http.get(baseUrl+"/sujet")
             .success(function(data,status,headers,config){
                                 _.each(data, transformerListeEnTableau);
                                   deferred.resolve(donnees);
                                }
                     )
             .error(function(data,status,headers,config){                        
                               deferred.reject('There was an error while getting Sujets data!');
                        }
                   );	 
    	     return deferred.promise;
    	 };
    	service.getFormations= function(){
    		 var deferred = $q.defer();
    		 $http.get(baseUrl+"/formation")
             .success(function(data,status,headers,config){
                                 _.each(data, transformerFormationsEnTableau);
                                   deferred.resolve(donneesFormations);
                                }
                     )
             .error(function(data,status,headers,config){                        
                               deferred.reject('There was an error while getting Sujets data!');
                        }
                   );	 
    	     return deferred.promise;
    	 };
    	 return service;
    });