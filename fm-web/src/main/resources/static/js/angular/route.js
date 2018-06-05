mainApp.config(['$routeProvider', function($routeProvider) {
    // 路由配置
    var route = $routeProvider;

    route.when('/view/:code', {
        templateUrl: function($routeParams){return 'nav/'+$routeParams.code;},
        controller: 'viewController'
    });
    route.otherwise({ templateUrl: 'main'});

}]);