mainApp.config(['$routeProvider', function($routeProvider) {
    // 路由配置
    var route = $routeProvider;

    route.when('/nav/:code', {
        templateUrl: function($routeParams){return 'nav/'+$routeParams.code;},
        controller: 'viewController'
    });

    route.when('/subnav/:option', {
        templateUrl: function($routeParams){return 'subnav/'+$routeParams.option;},
        controller: 'orderOptionController'

    });

    route.otherwise({ templateUrl: 'main'});

}]);