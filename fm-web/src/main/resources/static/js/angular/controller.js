mainApp.controller("initController", ["$rootScope", "userService",function($rootScope, userService) {
    // 获取用户数据
    var promise = userService.getUser();

    promise.then(function(data) {
        $rootScope.user = data;
        $rootScope.role = $rootScope.user.role;
        $rootScope.permissions = $rootScope.user.role.permissionList;
    }, function(data) {
        alert(data);
    });
}]);


mainApp.controller("orderController", ["$scope",function($scope) {

}]);

