(function() {

  var MessageService = function($rootScope) {
    $rootScope.errors = [];
    $rootScope.alerts = [];

    var error = function(code, message) {
      $rootScope.errors.push({code: code, message: message});
    };

    var info = function(code, message) {
      $rootScope.alerts.push({code: code, message: message});
    };

    var clearError = function() {
      $rootScope.errors = [];
    };

    var clearInfo = function() {
      $rootScope.alerts = [];
    };

    return {
      error: error,
      info: info,
      clearError: clearError,
      clearInfo: clearInfo
    };
  };

  MessageService.$inject = ['$rootScope'];
  app.service('MessageService', MessageService);

}());
