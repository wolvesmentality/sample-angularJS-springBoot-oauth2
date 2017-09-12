(function() {

  var IndexController = function($scope, $cookieStore, UserService, $location, $rootScope, //
  $localStorage, ApplicationSettingService) {

    $scope.isAuthenticated = function() {
      var userToken = $localStorage.userToken;
      return (angular.isDefined(userToken) && userToken !== null);
    };

    $scope.logout = function() {
      UserService.logout().then(function(response) {
        delete $rootScope.userToken;
        delete $localStorage.userToken;
      //$location.path("/login");      
      });
    };

    $rootScope.hasRole = function(role) {
      if ($rootScope.user === undefined) {
        return false;
      }
      if ($rootScope.user.roles[role] === undefined) {
        return false;
      }
      return $rootScope.user.roles[role];
    };

    $scope.test = 'oooo';

    $rootScope.logout = function() {
      delete $rootScope.user;
      delete $rootScope.userToken;
      $cookieStore.remove('userToken');
      $location.path("/login");
    };

    var getVersion = function() {
      $scope.appVersion = ApplicationSettingService.getByName('appVersion').then(function(response) {
        $scope.appVersion = response;
      });
    };
    getVersion();
  };

  IndexController.$inject = ['$scope', '$cookieStore', //
    'UserService', '$location', '$rootScope', '$localStorage', 'ApplicationSettingService'];

  app.controller('IndexController', IndexController);

}());
