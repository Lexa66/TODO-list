angular
    .module('TodoList')
    .controller('TaskController', function ($http, $scope, AuthService) {
        var edit = false;
        $scope.buttonText = 'Create';
        var init = function () {
            $http.get('api/task').success(function (res) {
                $scope.taskList = res;

                $scope.taskForm.$setPristine();
                $scope.task = null;
                $scope.buttonText = 'Create';

            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        $scope.initEdit = function (task) {
            edit = true;
            $scope.task = task;
            $scope.message = '';
            $scope.deleteMessage = '';
            $scope.buttonText = 'Update';
        };
        $scope.initAddTask = function () {
            edit = false;
            $scope.task = null;
            $scope.taskForm.$setPristine();
            $scope.message = '';
            $scope.deleteMessage = '';
            $scope.buttonText = 'Create';
        };
        $scope.deleteTask = function (task) {
            $http.delete('api/task/' + task.id).success(function (res) {
                $scope.deleteMessage = "Success!";
                init();
            }).error(function (error) {
                $scope.deleteMessage = error.message;
            });
        };
        var editTask = function () {
            $http.put('api/task', $scope.task).success(function (res) {
                $scope.task = null;
                $scope.confirmPassword = null;
                $scope.taskForm.$setPristine();
                $scope.message = "Editing Success";
                init();
            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        var addTask = function () {
            $http.post('api/task', $scope.task).success(function (res) {
                $scope.task = null;
                $scope.confirmPassword = null;
                $scope.taskForm.$setPristine();
                $scope.message = "Task Created";
                init();
            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        $scope.submit = function () {
            if (edit) {
                editTask();
            } else {
                addTask();
            }
        };
        init();
    });
