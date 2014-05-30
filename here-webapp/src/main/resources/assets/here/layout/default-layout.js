'use strict';
define(['text!./default-layout.html', 'lib/lodash'], function(template, _) {
	defaultLayoutController.$inject = ['$scope', '$state', 'authenticationTokenRepository'];

	function defaultLayoutController($scope, $state, authenticationTokenRepository) {
		$scope.$state = $state;

		$scope.inStates = function() {
			return _.any(arguments, $state.includes);
		};

		$scope.logout = function() {
			authenticationTokenRepository.clearToken().then(function() {
				$state.go("signup");
			});
		};
	}

	return {
		name : 'default',
		abstract : true,
		controller : defaultLayoutController,
		template : template,
		resolve : {

		}
	};
});