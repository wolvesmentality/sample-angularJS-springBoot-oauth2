(function() {

  var StorageService = function($rootScope, $window) {

    var getLocalItem = function(key) {
      return JSON.parse($window.localStorage.getItem(key));
    };

    var setLocalItem = function(key, item) {
      $window.localStorage.setItem(key, JSON.stringify(item));
    };

    var removeLocalItem = function(key) {
      $window.localStorage.removeItem(key);
    };

    var getSessionItem = function(key) {
      return JSON.parse($window.sessionStorage.getItem(key));
    };

    var setSessionItem = function(key, item) {
      $window.sessionStorage.setItem(key, JSON.stringify(item));
    };

    var removeSessionItem = function(key) {
      $window.sessionStorage.removeItem(key);
    };

    return {
      getLocalItem: getLocalItem,
      setLocalItem: setLocalItem,
      removeLocalItem: removeLocalItem,
      getSessionItem: getSessionItem,
      setSessionItem: setSessionItem,
      removeSessionItem: removeSessionItem
    };
  };

  StorageService.$inject = ['$rootScope', '$window'];
  app.service('StorageService', StorageService);

}());
