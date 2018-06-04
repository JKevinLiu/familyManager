mainApp.config(['$routeProvider',"userService", function($routeProvider, userService) {
    // 路由配置
    var route = $routeProvider;

    // 获取用户数据
    var promise = userService.getUser();

    promise.then(function(data) {
        var permissions = data.role.permissionList;

        for(var i = 0; i < permissions.length; i++){
            route.when('/view/'+permissions[i].url, { templateUrl: ''+permissions[i].mapping});
        }

        route.otherwise({ templateUrl: 'main'});

    }, function(data) {
        alert(data);
    });
}]);
