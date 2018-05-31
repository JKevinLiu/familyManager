mainApp.config(['$routeProvider', function($routeProvider) {
    // 路由配置
    var route = $routeProvider;
    route.when('/view/PERSONAL_INFO', { controller: 'viewController', templateUrl: 'userinfo'});
    route.when('/view/CORE_INFO', { controller: 'viewController', templateUrl: 'orderinfo'});
    route.when('/view/CALC_INFO', { controller: 'viewController', templateUrl: 'reportinfo'});
    route.when('/view/PRODUCT_MANAGER', { controller: 'viewController', templateUrl: 'productinfo' });
    route.otherwise({ controller: 'viewController',templateUrl: 'main'});
}]);
