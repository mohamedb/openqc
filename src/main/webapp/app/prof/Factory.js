/* Factories */
qcmApp
    .factory('messageData', function ($http,$q) {
    	/*
    	 * Get Message from the server
    	 */
    	 var service={};
    	 
    	 var messages;
    	 var alerts={};
    	 service.getAlerts=function(){
    		 return alerts;
    	 }

    	 service.getMessages= function(){
    		 var deferred = $q.defer();
    		 $http.get(Routing.generate('ng_message_all_data'))
             .success(function(data,status,headers,config){                                
                                 deferred.resolve(data);
                                }
                     )
             .error(function(data,status,headers,config){                        
                               deferred.reject('There was an error')
                        }
                   );
        	 
    	     return deferred.promise;
    	 }
    	 
    	 
    	 return service;
    });