angular.module('TodoList')
.controller('RegistrationController', function($http, $scope, AuthService) {
	$scope.submit = function() {
		$http.post('registration', $scope.user).success(function(res) {
			$scope.user = null;
			$scope.confirmPassword = null;
			$scope.registration.$setPristine();
			$scope.message = "Registration successfully !";
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
});
