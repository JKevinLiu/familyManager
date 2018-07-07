mainApp.config(['$routeProvider', function($routeProvider) {
    // 路由配置
    var route = $routeProvider;

    route.when('/nav/:code', {
        templateUrl: function($routeParams){return 'nav/'+$routeParams.code;}
    });

    route.when('/subnav/add_order', {
        templateUrl: "subnav/add_order",
        controller: 'orderOptionController'
    });

    route.when('/subnav/edit_order/:id', {
        templateUrl: function($routeParams){return 'subnav/edit_order/'+$routeParams.id;},
        controller: 'orderOptionController'
    });

    route.otherwise({ templateUrl: 'main'});

}]);