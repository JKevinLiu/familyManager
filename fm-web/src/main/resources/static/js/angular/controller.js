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
    //$scope.code = $routeParams.code;
}]);

mainApp.controller("orderController", ["$scope","$http", "$location", function($scope, $http, $location) {
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
        $location.path("subnav/add_order");
    };

    $scope.del = function(){
        alert("del");
    };

    $scope.query = function(){
        alert("query");
    }
}]);

mainApp.controller("orderOptionController", ["$scope", "$routeParams", function($scope, $routeParams) {
    $scope.option = $routeParams.option;

    //定义数组
    $scope.items = [];

    //添加的方法
    $scope.add = function(){
        //创建对象
        var item = {
            "aliasName" : $scope.alias_select,
            "productName" : $scope.product_select,
            "price" : $scope.price,
            "remark" : $scope.remark
        }
        //放进数组
        $scope.items.push(item);
    }

    //删除一行
    $scope.dele =function($index){
        $scope.items.splice($index, 1);
    }
}]);


mainApp.controller("productController", ["$scope", "$http", function($scope, $http) {
    $scope.right_title = "添加产品";

    $scope.getAilas = function(){
        $http.get("product/alias").then(
            function(res){
                $scope.alias = res.data.result;
            },function(res){
                //fail
                alert(res.data.desc);
            }
        );
    };

    $scope.getAliasAndchilren = function(aliasId){
        if(aliasId == null){
            $scope.aliasAndchilren = [];
            return;
        }
        $http.get("product/" + aliasId + "/AliaAndChildren").then(
            function(res){
                $scope.aliasAndchilren = res.data.result;
            },function(res){
                alert(res.data.desc);
            }
        );
    }

    $scope.change = function(){
        $scope.getAliasAndchilren($scope.left_alias_select);
    }

    $scope.editProduct = function(product){
        $scope.right_title = "修改产品";
    }

    $scope.getAilas();
}]);

