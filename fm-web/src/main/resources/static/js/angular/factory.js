mainApp.factory("userService",["$http","$q", function($http, $q) {
    var user = {};
    user.getUser = function(){
        var deferred = $q.defer(); // 声明延后执行，表示要去监控后面的执行
        var promise  = $http.get('user/userinfo').then(
            function(res){
                deferred.resolve(res.data.result);  // 声明执行成功，即http请求数据成功，可以返回数据了
            }, function(res){
                deferred.reject(res.data.desc);
            }
        );
        return deferred.promise;
    }
    return user;
}]);