var primeriApp = angular.module("primeriApp", ['ngRoute']);

primeriApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
    when('/',{
        templateUrl: 'main.htm'
    }).when('/red',{
        templateUrl: 'red.htm'
    }).when('/green',{
        templateUrl: 'green.htm'
    }).when('/blue',{
        templateUrl: 'blue.htm'
    }).otherwise({
        redirectTo: '/'
    });
}]);


//var primeriApp = angular.module("primeriApp", ["ngRoute"]);
//primeriApp.config(function($routeProvider) {
//    $routeProvider
//    .when("/", {
//        templateUrl : "main.htm"
//    })
//    .when("/red", {
//        templateUrl : "red.htm"
//    })
//    .when("/green", {
//        templateUrl : "green.htm"
//    })
//    .when("/blue", {
//        templateUrl : "blue.htm"
//    });
//});