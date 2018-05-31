mainApp.controller("userController", ["$scope", "userService",function($scope, userService) {
    // 获取用户数据
    var promise = userService.getUser();

    promise.then(function(data) {
        $scope.user = data;
        $scope.role = $scope.user.role;
        $scope.permissions = $scope.user.role.permissionList;
    }, function(data) {
        alert(data);
    });


}]);


// 创建查看控制器 viewController, 注意应为需要获取URL ID参数 我们多设置了一个 依赖注入参数 “$routeParams” 通过它获取传入的 ID参数
mainApp.controller("viewController", ["$scope", "userService", function($scope, userService) {
    $scope.user = userService.getUser();
}]);