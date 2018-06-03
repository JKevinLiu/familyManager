mainApp.config(['$routeProvider', function($routeProvider) {
    // 路由配置
    var route = $routeProvider;
    route.when('/view/PERSONAL_INFO', { templateUrl: 'userinfo'});
    route.when('/view/CORE_INFO', { templateUrl: 'orderinfo'});
    route.when('/view/CALC_INFO', { templateUrl: 'reportinfo'});
    route.when('/view/PRODUCT_MANAGER', { templateUrl: 'productinfo' });
    route.otherwise({ templateUrl: 'main'});
}]);
