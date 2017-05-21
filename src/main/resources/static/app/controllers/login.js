angular
    .module('TodoList')
    .controller('LoginController', function ($http, $scope, $state, AuthService, $rootScope) {

        $scope.login = function () {

            var base64Credential = btoa($scope.username + ':' + $scope.password);

            $http.get('user', {
                headers: {
                    'Authorization': 'Basic ' + base64Credential
                }
            }).success(function (res) {
                $scope.password = null;
                if (res.authenticated) {
                    $scope.message = '';
                    $http.defaults.headers.common['Authorization'] = 'Basic ' + base64Credential;
                    AuthService.user = res;
                    $rootScope.$broadcast('LoginSuccessful');
                    $state.go('task');
                } else {
                    $scope.message = 'Authentication Failed !';
                }
            }).error(function (error) {
                $scope.message = 'Authentication Failed !';
            });
        };
    });
