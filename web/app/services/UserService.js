(function() {

  var UserService = function($q, $rootScope, propertiesConstant, $http) {
    var login = function(credentials) {
      var url = propertiesConstant.API_URL + 'rest/authentication/login';
      return $http({
        method: 'POST',
        url: url,
        headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
        data: credentials
      });
    };

    var get = function() {
      return $http.get(propertiesConstant.API_URL + 'rest/authentication/current');
    };

    var logout = function() {
      var url = propertiesConstant.API_URL + 'rest/authentication/logout';
      return $http.get(url);
    };


    var authorize = function() {
      var d = $q.defer();
      $http.get(propertiesConstant.API_URL + 'rest/authentication/authorize')
        .success(function(response) {
          $rootScope.user = response.data;
          d.resolve();
        })
        .error(function() {
          d.reject();
        });
      return d.promise;
    };

    return {
      login: login,
      get: get,
      logout: logout,
      authorize: authorize
    };
  };

  UserService.$inject = ['$q', '$rootScope', 'propertiesConstant', '$http'];
  app.service('UserService', UserService);

}());
