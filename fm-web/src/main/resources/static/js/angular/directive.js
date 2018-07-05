mainApp.directive('orderPage',["$http", function($http){
    return {
        restrict: 'EA',
        replace: false,
        scope: false,
        templateUrl: 'page',
        link: function($scope, elem, attr){

            $scope.gotoPage = 1;

            //分页对象初始化
            $scope.reqPage = {
                "pageSize" : 3,//每页记录数
                "curPage" : 1,//当前页码
            };

            $scope.page = {};

            //设置获取页面的ng-click
            $scope.getPage = function(){//获取指定的页面

                $http.post("order/list",$scope.reqPage)
                    .then(
                        function (res) {
                            $scope.page = res.data.result;
                            $scope.$emit("orderList", $scope.page.list);//$emit：子传父，传递event与data；此处用于给父controller传递数据orderList
                        },
                        function(res){
                            alert(res.data.desc);
                        }
                    );


            };

            $scope.jumpPage = function(pageNo){
                if(pageNo > $scope.page.pages || pageNo < 1){
                    alert(" Invalid page number !");
                    return;
                }

                $scope.reqPage.curPage = pageNo;
                $scope.getPage();
            };

            $scope.getPage();//初始化获取第一页数据
        }
    };
}]);