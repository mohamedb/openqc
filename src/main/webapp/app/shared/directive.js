/* Directives */

qcmApp
    // page preloader
    .directive('pageLoader', [
        '$timeout',
        function ($timeout) {
            return {
                restrict: 'AE',
                template: '<div class="rect1"></div><div class="rect2"></div><div class="rect3"></div><div  class="rect4"></div><div class="rect5"></div>',
                link: function (scope, el, attrs) {
                    el.addClass('pageLoader hide');
                    scope.$on('$stateChangeStart', function (event) {
                        el.toggleClass('hide animate');
                    });
                    scope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState) {
                        event.targetScope.$watch('$viewContentLoaded', function () {
                            $timeout(function () {
                                el.toggleClass('hide animate');
                            }, 600);
                        });
                    });
                }/*== End function link == */
            };
        }
    ])
    ;
qcmApp.directive('changeOnHoover', function($timeout){
	return {
		template: '<b>Hello </b>',
		link:function(scope,element,attrs){ 		
			element.bind('mouseover',function(){
				element.addClass(attrs.changeOnHoover);
			});
			
		}
		
	};
	
});

 

qcmApp.directive('hideAlert', function($timeout){
	return {
		  
		link:function(scope,element,attrs){
			var numberOfMs = scope.$eval(attrs.hideAfterTime);
			$timeout(function(){				
				element.hide();
				scope.$apply(attrs.hideAlert);
			},numberOfMs);
		}
		
	};
	
});

qcmApp.directive('enter',function(){
	var k=0;
	return function(scope,element){
		element.bind("mouseover",function(){
			k++;
			console.log('mouseOver event !'+k);
		});
	};
});