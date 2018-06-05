mainApp.controller("initController", ["$rootScope", "userService", function($rootScope, userService) {
    // 获取用户数据
    var promise = userService.getUser();

    promise.then(function(data) {
        $rootScope.user = data;
        $rootScope.role = $rootScope.user.role;
        $rootScope.permissions = $rootScope.user.role.permissionList;
    }, function(data) {
       //fail
        alert(data);
    });
}]);

mainApp.controller("viewController", ["$scope", "$routeParams", function($scope, $routeParams) {
    //var code = $routeParams.code;
    //alert("url : " + code);
}]);

mainApp.controller("orderController", ["$scope","$http", function($scope, $http) {
    $http.get("order/list").then(
        function (res) {
            $scope.orders = res.data.result;
        },
        function(res){
            //fail
            alert(res.data.desc);
        }
    );

    $scope.add = function(){
        alert("add");
    };

    $scope.del = function(){
        alert("del");
    };

    $scope.query = function(){
        alert("query");
    }

}]);

