// for single page application
var app = angular.module('aktors', ['ngRoute']);

// managing routes
app.config(function ($routeProvider) {

    $routeProvider
            .when('/home', {
                templateUrl: 'templates/home.html',
                controller: 'HomeController'
            })

            .when('/clients', {
                templateUrl: 'templates/clients.html',
                controller: 'ClientsRestController'
            })

            .when('/products', {
                templateUrl: 'templates/products.html',
                controller: 'ProductsRestController'
            })

            .otherwise({
                templateUrl: 'templates/home.html',
                controller: 'HomeController'
            });
});



app.controller('HomeController', function ($scope) {
    $scope.message = 'Hello from HomeController';
});

app.controller('ClientsRestController', function ($scope) {
    $scope.message = 'Hello from Clients';
});

app.controller('ProductsRestController', function ($scope) {
    $scope.message = 'Hello from products';
});


/*
 angular.module('main', [])
 .controller('home', function ($scope, $http) {
 
 
 /*
 $http.get('/clients/').success(function (data) {
 $scope.greeting = {content: data};
 });
 });
 */
