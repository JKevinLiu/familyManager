/**
 * 初始化
 */
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


/**
 * order控制器
 */
mainApp.controller("orderController", ["$scope","$http", "$location", function($scope, $http, $location) {
    $scope.orders = [];

    $scope.add = function(){
        $location.path("subnav/add_order");
    };

    $scope.del = function(id){
        if(!confirm("确定删除该条记录吗？")){
            return;
        }

        $http.delete("order/"+ id +"/del").then(
            function(res){
                alert(res.data.result);
                $scope.query();
            },function(res){
                alert(res.data.result);
            }
        );
    };

    $scope.$on('orderList', function(event,data) {
        $scope.orders = data;
    });

}]);


/**
 * order_option控制器
 */
mainApp.controller("orderOptionController", ["$rootScope", "$scope", "$routeParams", "$http",
    function($rootScope, $scope, $routeParams, $http) {
        $scope.option = $routeParams.option;

        $scope.option_item = {};
        $scope.option_item.alias_select = "-1";
        $scope.option_item.product_select = "-1";

        //定义数组
        $scope.order = {};
        $scope.order.orderItemList = [];

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

        $scope.getChildProducts = function(id){
            if(id == null || id == "-1"){
                $scope.option_item.alias_select = "-1";
                $scope.option_item.product_select = "-1";
                alert("请选择大类！");
                return;
            }
            $http.get("product/" + id + "/childlist").then(
                function(res){
                    $scope.products = res.data.result;
                },function(res){
                    //fail
                    alert(res.data.desc);
                }
            );
        };

        $scope.getProductById = function(id){
            if(id == null || id == "-1"){
                alert("请选择产品！");
                return;
            }
            $http.get("product/" + id + "/show").then(
                function(res){
                    $scope.curProduct = res.data.result;
                },function(res){
                    //fail
                    alert(res.data.desc);
                }
            );
        }

        /**
         * 选择大类
         */
        $scope.changeAlias = function(id){
            $scope.getChildProducts(id);
        };

        /**
         * 选择产品
         */
        $scope.changeProduct = function(id){
            $scope.getProductById(id);
        };

        /**
         * add item
         */
        $scope.add = function(){
            //validate params
            if($scope.option_item.alias_select == "-1" || $scope.option_item.product_select == "-1"){
                alert("请选择产品");
                return;
            }

            if($scope.option_item.price == null || $scope.option_item.price <= 0){
                alert("金额必须大于0");
                return;
            }

            var alias = $scope.alias;
            var aliasName = "";

            for(var i = 0; i < alias.length; i++){
                if(alias[i].id == $scope.option_item.alias_select){
                    aliasName = alias[i].name;
                    break;
                }
            }
            //创建对象
            var item = {
                "aliasId" : $scope.option_item.alias_select,
                "aliasName" : aliasName,
                "productId" : $scope.curProduct.id,
                "productCode" : $scope.curProduct.code,
                "productName" : $scope.curProduct.name,
                "price" : $scope.option_item.price * 100,
                "description" : $scope.option_item.description
            }
            //放进数组
            $scope.order.orderItemList.push(item);
        };

        /**
         * delete item
         */
        $scope.del =function($index){
            $scope.order.orderItemList.splice($index, 1);
        }

        /**
         * 提交
         */
        $scope.commit = function(){
            if($scope.order.orderItemList.length == 0){
                alert("请至少添加一条明细！");
                return;
            }
            $scope.order.userId = $rootScope.user.id;
            $scope.order.username = $rootScope.user.nickname;

            console.log(angular.toJson($scope.order));
            $http.post("order/save",angular.toJson($scope.order)).then(
                function(res){
                    alert(res.data.result);

                    //清空
                    $scope.option_item = {};
                    $scope.option_item.alias_select = "-1";
                    $scope.option_item.product_select = "-1";
                    $scope.order = {};
                    $scope.order.orderItemList = [];
                    $scope.curProduct = {};

                },function(res){
                    alert(res.data.result);
                }
            );
        }

        /**
         * 清空
         */
        $scope.reset = function(){
            if(!confirm("确定清空明细列表？")){
                return;
            }
            $scope.order.orderItemList = [];
        }

        $scope.getAilas();
}]);




/**
 * product控制器
 */
mainApp.controller("productController", ["$scope", "$http", function($scope, $http) {
    $scope.right_title = "添加产品";
    $scope.show_product = {};
    $scope.right_alias_select_edit = true;
    $scope.left_alias_select = "-1";
    $scope.right_alias_select = "-1";


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
        $scope.show_product = {};


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

    $scope.editProduct = function(product, index){
        var flag = false;
        var ck_products = $("input[name='ck_product']");


        for (var i = 0; i < ck_products.length; i++) {
            if(i == index && ck_products[i].checked){  //判断是否选中
                $scope.right_title = "修改产品";
                $scope.show_product = product;
                $scope.right_alias_select = product.parentId +"";


                if(product.parentId == "-1"){
                    $scope.right_alias_select_edit = false;
                }else{
                    $scope.right_alias_select_edit = true;
                }


                flag = true;


            }else{
                if(ck_products[i].checked){
                    ck_products[i].checked = false;
                }
            }
        }


        if(flag){
            return;
        }


        $scope.show_product = {};
        $scope.right_alias_select = "-1";
        $scope.right_title = "添加产品";
        $scope.right_alias_select_edit = true;
    }

    $scope.commit = function(){
        if(!($scope.show_product.code && $scope.show_product.name)){
            alert("请填写产品信息！");
            return;
        }
        $http.post("product/save",angular.toJson($scope.show_product)).then(
            function(res){
                alert(res.data.result);
                $scope.getAilas();
                if($scope.right_alias_select != -1){
                    $scope.getAliasAndchilren($scope.right_alias_select);
                }
            },function(res){
                alert(res.data.result);
            }
        );
    }

    $scope.del = function(id, parentId){
        if(!confirm("确定删除该条记录吗？")){
            return;
        }

        $http.delete("product/"+ id +"/del").then(
            function(res){
                alert(res.data.result);

                $scope.show_product = {};
                $scope.getAilas();

                if(parentId == -1){
                    $scope.aliasAndchilren = [];
                }else{
                    $scope.getAliasAndchilren($scope.left_alias_select);
                }
            },function(res){
                alert(res.data.result);
            }
        );
    }

    $scope.getAilas();
}]);



/**
 * page控制器
 */
mainApp.controller("pageController", ["$scope","$http", function($scope, $http) {

}]);