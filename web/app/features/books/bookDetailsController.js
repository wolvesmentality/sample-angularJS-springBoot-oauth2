(function() {

  app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when("/book", {
        templateUrl: "features/books/bookDetails.html"
      })
      .when("/book/:bookId", {
        templateUrl: "features/books/bookDetails.html"
      });
  }]);

  var BookDetailsController = function($scope, $http, BookService, $routeParams) {

    $scope.bookId = angular.isDefined($routeParams.bookId) ? $routeParams.bookId : null;
    if ($scope.bookId !== null) {
      BookService.getById($scope.bookId).then(function(response) {
        $scope.book = response.data;
      });
    } else {

    }

  };

  BookDetailsController.$inject = ['$scope', '$http', 'BookService', '$routeParams'];

  app.controller('BookDetailsController', BookDetailsController);

}());

