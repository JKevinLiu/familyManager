mainApp.config(['$routeProvider', function($routeProvider) {
    // 路由配置
    var route = $routeProvider;
    route.when('/view/:id', { controller: 'viewController', templateUrl: 'main.html' });
    route.otherwise({ redirectTo: 'index' });
}]);

//创建一个提供数据的服务器
mainApp.factory("service", function() {
    var list = [
        { id: 1, title: "个人信息", url: "http://www.cnblogs.com" },
        { id: 2, title: "个人订单", url: "http://www.zhihu.com" },
        { id: 3, title: "报表统计", url: "http://www.codeproject.com/" },
        { id: 4, title: "产品管理", url: "http://www.stackoverflow.com/" }
    ];
    return function(id) {
        //假如ID为无效值返回所有
        if (!id) return list;
        var t = 0;
        //匹配返回的项目
        angular.forEach(list, function(v, i) {
            if (v.id == id) t = i;
        });
        return list[t];
    }
});