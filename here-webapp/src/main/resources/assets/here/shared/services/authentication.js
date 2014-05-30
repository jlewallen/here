'use strict';
define(['shared/modules/shared-module', 'lib/angular/angular', 'lib/angular/angular-ui-router', 'shared/services/url-helper'], function(module, angular, router) {
	module.factory("authenticationTokenRepository", ['$window', '$q', function($window, $q) {
		return {
			saveToken : function(token) {
				if (token) {
					$window.localStorage.token = angular.toJson(token);
				} else {
					$window.localStorage.token = null;
				}
				return $q.when();
			},
			clearToken : function() {
				$window.localStorage.token = null;
				return $q.when();
			},
			getToken : function() {
				if ($window.localStorage.token) {
					return angular.fromJson($window.localStorage.token);
				}
				return null;
			}
		};
	}]);

	module.config(['$provide', '$httpProvider', function($provide, $httpProvider) {
		$provide.factory('tokenInterceptor', ['$q', '$rootScope', 'authenticationTokenRepository', function($q, $rootScope, authenticationTokenRepository) {
			return {
				'request' : function(config) {
					var token = authenticationTokenRepository.getToken();
					if (token != null) {
						config.headers['Identity'] = token.id;
					}
					return config || $q.when(config);
				}
			};
		}]);

		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		$httpProvider.interceptors.push('tokenInterceptor');
	}]);
});