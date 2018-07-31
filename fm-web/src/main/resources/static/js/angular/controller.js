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
 * mainController控制器
 */
mainApp.controller("mainController", ["$scope","$http", "$rootScope", function($scope, $http, $rootScope) {
    $scope.calcParams = {
        userId : $rootScope.user.id
    };

    $scope.calc = function(){
        $http.post("/calc/lastPay",angular.toJson($scope.calcParams)).then(
            function(res){
                alert(res.data.result);
            },function(res){
                alert(res.data.result);
            }
        );
    };
}]);

/**
 * order控制器
 */
mainApp.controller("orderController", ["$scope","$http", "$location", function($scope, $http, $location) {
    $scope.orders = [];

    $scope.add = function(){
        $location.path("subnav/add_order");
    };

    $scope.edit = function(id){
        $location.path("subnav/edit_order/"+id);
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
mainApp.controller("orderOptionController", ["$rootScope", "$scope", "$routeParams", "$http", "$location",
    function($rootScope, $scope, $routeParams, $http, $location) {
        $scope.orderId = $routeParams.id;

        $scope.titleName = "添加收支";

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

            if($scope.order.orderItemList.length > 5){
                alert("最多添加5条明细！");
                return;
            }
            $scope.order.userId = $rootScope.user.id;
            $scope.order.username = $rootScope.user.nickname;

            $http.post("order/save",angular.toJson($scope.order)).then(
                function(res){
                    alert(res.data.result);
                    $location.path("nav/order_info");
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

        if($scope.orderId != undefined && $scope.orderId != 0){
            $scope.titleName = "编辑明细";
            //获取orderitem
            $http.get("order/" + $scope.orderId + "/show").then(
                function(res){
                    $scope.order = res.data.result;
                },function(res){
                    //fail
                    alert(res.data.desc);
                }
            );
        }

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


/**
 * reportController控制器
 */
mainApp.controller("reportController", ["$rootScope", "payMonthService", "$scope", function($rootScope, payMonthService, $scope) {
    var userId = $rootScope.user.id;
    var canvas,ctx;
    var x_scale = 0,y_scale = 0,heightVal=0,stepWidth=0,stepHeight=0;
    var stepYArr = [],stepXArr = [];
    var arrowWidth = 4,arrowHeight = 6;
    var stepNum = 3;
    var str1 = "消费额";
    var str2 = "单位(元)";

    var promise = payMonthService.getPayMonth(userId);

    promise.then(function(data) {
        init(data);//柱状图
    }, function(data) {
        alert(data);
    });

    var init = function(data){
        canvas = document.getElementById("canvas");
        canvas.width = data.width;
        canvas.height = data.height;
        x_scale = data.width/10;//x轴刻度
        y_scale = data.height/10;//y轴刻度
        ctx = canvas.getContext("2d");

        drawXAxis(data.xAxis);//画X轴
        drawYaxis(data.maxValue,stepNum);//画Y轴
        drawBg();//画背景
        drawRect(data.starRate,data.rectColor);//画柱形
    }

    /*画x轴*/
    var drawXAxis = function(xAxis){
        ctx.beginPath();//清除之前的路径，开始一条新的路径
        //画x轴横线
        ctx.moveTo(x_scale,canvas.height-y_scale);
        ctx.lineTo(canvas.width-x_scale,canvas.height-y_scale);
        //加标签
        var len = xAxis.length;
        stepWidth = (canvas.width - 2*x_scale)/len;//一个类型所占的宽度
        for(var i=0; i<len; i++){
            //画标签，默认字体为12个像素
            ctx.font = "normal normal bold 14px 微软雅黑";
            ctx.fillStyle = "#285ea6";
            //字体居中
            ctx.fillText(xAxis[i],x_scale+(i+0.5)*stepWidth-xAxis[i].length*14/2,canvas.height-y_scale + 24);
            stepXArr.push(x_scale+(i+1)*stepWidth);
        }
        ctx.stroke();
        //加箭头
        drawArrow(canvas.width-x_scale,canvas.height-y_scale,false);
    }

    //画y轴
    var drawYaxis = function(maxValue,step){
        ctx.beginPath();
        //画Y轴线
        ctx.moveTo(x_scale,y_scale);
        ctx.lineTo(x_scale,canvas.height-y_scale);

        //加标签
        stepHeight = (canvas.height - 3*y_scale)/step;
        heightVal = (canvas.height - 3*y_scale )/maxValue;//比例
        for(var i=1; i<=step; i++){
            ctx.font = "normal normal bold 14px 微软雅黑";
            //字体居中
            ctx.fillText(maxValue/step*i,x_scale-50,canvas.height-y_scale-stepHeight*i+7);
            stepYArr.push(canvas.height-y_scale-stepHeight*i);
        }
        ctx.stroke();
        //加箭头
        drawArrow(x_scale,y_scale,true);
        //加Y轴顶部字体
        ctx.fillText(str1,x_scale-50,y_scale-8);
        ctx.fillText(str2,x_scale-60,y_scale+12);
    }

    //画柱形图
    var drawRect = function(starRate,colorArr){
        var rectWidth = stepWidth/2;
        for(var i=0,len=starRate.length;i<len;i++){
            ctx.beginPath();
            ctx.fillStyle = colorArr[i];
            ctx.fillRect(stepXArr[i]-stepWidth/2-rectWidth/2,canvas.height-y_scale-starRate[i]*heightVal,rectWidth,starRate[i]*heightVal);
            ctx.fill();
        }
    }

    //画箭头
    var drawArrow = function(left,top,flag){
        ctx.beginPath();
        ctx.moveTo(left,top);
        if(flag){
            ctx.lineTo(left+arrowWidth,top);
            ctx.lineTo(left,top-arrowHeight);
            ctx.lineTo(left-arrowWidth,top);
        }else{
            ctx.lineTo(left,top-arrowWidth);
            ctx.lineTo(left+arrowHeight,top);
            ctx.lineTo(left,top+arrowWidth);
        }

        ctx.fillStyle = "#666";
        ctx.fill();
    }

    //画背景矩形
    var drawBg = function(){
        for(var i=0;i<stepYArr.length;i++){
            ctx.beginPath();
            ctx.fillStyle = "#f2f2f2";
            if(i%2 == 0){
                ctx.fillRect(x_scale+1,stepYArr[i],canvas.width-2*x_scale-stepWidth/4,stepHeight);
                ctx.fill();
            }
        }
    }
}]);