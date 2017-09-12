(function() {

  app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/login', {
        templateUrl: 'common/login/login.html'
      })
      .when('/access_token=:accessToken', {
        template: ''
      });
  }]);

  var LoginController = function($window, $http, $location, $rootScope, $scope, UserService,
  $cookieStore, $localStorage, $route, $routeParams) {

    $scope.credentials = {};
    $scope.login = function() {
      var data = $.param({login: $scope.credentials.login, password: $scope.credentials.password});
      console.log('data', data);
      UserService.login(data).then(function(response) {
        var userToken = response.data;
        $localStorage.userToken = userToken;
        if ($scope.rememberMe) {
          $cookieStore.put('userToken', userToken);
        }
        $route.reload();
      //$location.path("/");
      /*
      UserService.get(function(user) {
        $rootScope.user = user;
        $location.path("/");
      });
      */
      });
    };

    $scope.googleLogin = function() {

      var clientId = 'your_google_client_id.apps.googleusercontent.com';
      var scope = 'email';
      var redirectUri = 'http://localhost:3000/';
      var responseType = "token";
      var url = 'https://accounts.google.com/o/oauth2/v2/auth?';
      url = url + 'scope=' + scope;
      url = url + '&client_id=' + clientId;
      url = url + "&redirect_uri=" + redirectUri;
      url = url + "&response_type=" + responseType;
      $window.location.replace(url);

    };

    var authorize = function() {
      var hash = $location.path().substr(1);

      var splitted = hash.split('&');
      var params = {};

      for (var i = 0;i < splitted.length;i++) {
        var param = splitted[i].split('=');
        var key = param[0];
        var value = param[1];
        params[key] = value;
        $rootScope.accesstoken = params;
      }
      UserService.get();
    };

    if (angular.isDefined($routeParams.accessToken)) {
      authorize();
    }
  };

  LoginController.$inject = ['$window', '$http', '$location', '$rootScope', '$scope', 'UserService',
    '$cookieStore', '$localStorage', '$route', '$routeParams'];
  app.controller('LoginController', LoginController);

}());
