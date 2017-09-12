
(function() {

  app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when("/", {
        templateUrl: "features/home/home.html"
      });
  }]);

  var HomeController = function() {

  };

  app.controller('HomeController', HomeController);

}());
