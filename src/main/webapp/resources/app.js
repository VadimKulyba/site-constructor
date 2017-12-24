'use strict';

var phonecatApp = angular.module('app', ['ngRoute']);

phonecatApp.config(['$routeProvider', function ($routeProvide) {
    $routeProvide
        .when('/',{
            templateUrl:'template/home.html',
            controller: 'AuthCtrl'
        })
        .when('/personal/:userLogin',{
            templateUrl:'template/personalPage.html',
            controller: 'PersonalCtrl'
        })
        .when('/personal',{
            templateUrl:'template/personalPage.html',
            controller: 'PersonalCtrl'
        })
        .when('/reg',{
            templateUrl:'template/registration.html',
            controller: 'AuthCtrl'
        })
        .when('/update',{
            templateUrl:'template/updatePage.html',
            controller: 'PersonalCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);

phonecatApp.controller('AuthCtrl',['$scope','$http', '$location', function($scope, $http, $location) {
    $http.post('session').success(function (data) {
        $scope.user = data;
        $scope.noVisibility = data;
    });

    $scope.reppassw = "";
    $scope.messageVis = false;
    $scope.noVisibility = false;
    
    $scope.addUser = function () {
        if($scope.reppassw === $scope.user.password) {
            $http.post('reg', $scope.user).success(function (data) {
                if(data) {
                    $scope.message = "You has been successfully reg!";
                    $scope.messageVis = true;
                }else{
                    $scope.message="This user does already exists!";
                    $scope.messageVis = true;
                }
            })
        }else{
            $scope.message = "Your passwords not equals!";
            $scope.messageVis = true;
        }
    };

    $scope.authorize = function () {
        $http.post('user', $scope.user).success(function (data) {
            if (data){
                $scope.noVisibility = data;
                $scope.user = data;
                $scope.message = false;
            } else {
                $scope.message = true;
            }
        });
    };

    $scope.logout = function () {
        $http.get('session').success(function (data) {
        });
    };

    $scope.deleteUser = function(){
        $http.remove('user', $scope.user).success(function (data) {
            $scope.users = data;
        });
    };
    // search
    $http.get('user/all').success(function(data, status, headers, config) {
        $scope.allUsers = data;
    });


    $scope.authorizeFB = function () {
        $http.post('facebook').success(function (data) {
            window.location=data;
        });
    };
}]);


phonecatApp.controller('PersonalCtrl',['$scope','$http', '$location','$routeParams', function($scope, $http, $location, $routeParams) {
    // for each user
    $scope.userLogin = $routeParams.userLogin;
    var url = 'user/' + $routeParams.userLogin;
    $http.post(url).success(function (data, status, headers, config) {
        $scope.user = data;
        // for authorize user
        $http.post('session').success(function (data) {
            $scope.authUser = data;
            $scope.isAdmin=($scope.authUser.role != 'USER');
            $scope.isAuthor = ($scope.user.login == $scope.authUser.login);
            $scope.inf =($scope.authUser.number || $scope.authUser.location
            || $scope.authUser.email || $scope.authUser.birthday);
        });
    });

    $scope.update = function () {

        $http.post('session').success(function (data) {
            $scope.authUser = data;
            if(typeof $scope.user.avatar == "undefined"){
            } else {
                $scope.authUser.avatar = $scope.user.avatar;
            } if(typeof $scope.user.name == "undefined"){
            } else {
                $scope.authUser.name = $scope.user.name;
            }if(typeof $scope.user.location == "undefined"){
            } else {
                $scope.authUser.location = $scope.user.location;
            }if(typeof $scope.user.email == "undefined"){
            } else {
                $scope.authUser.email = $scope.user.email;
            }if(typeof $scope.user.number == "undefined"){
            } else {
                $scope.authUser.number = $scope.user.number;
            }
            $http.post('user/update', $scope.authUser).success(function (data) {

            });
        });
    };
}]);
