(function() {

  var ApplicationSettingService = function($http, propertiesConstant) {

    var getByName = function(name) {
      var url = propertiesConstant.API_URL + 'rest/appsetting/' + name;
      return $http.get(url).then(function(response) {
        return response.data;
      });
    };

    return {
      getByName: getByName
    };

  };

  ApplicationSettingService.$inject = ['$http', 'propertiesConstant'];
  app.service('ApplicationSettingService', ApplicationSettingService);

}());
