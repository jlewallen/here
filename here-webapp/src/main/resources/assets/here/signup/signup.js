'use strict';
define(['text!./signup.html', 'layout/default-layout', 'app/angular-module'], function(template, layout, module) {
	signupController.$inject = ['$scope', '$state', 'defaultResource', 'authenticationTokenRepository'];

	function signupController($scope, $state, defaultResource, authenticationTokenRepository) {
		$scope.signupInfo = {
			firstName : 'John',
			lastName : 'Doe',
			email : 'jdoe@test.com',
			password : 'asdfasdf'
		};
		$scope.signup = function() {
			defaultResource("profile").post($scope.signupInfo).then(function(id) {
				authenticationTokenRepository.saveToken(id).then(function() {
					defaultResource("profile").get().then(function(profile) {
						$state.go("dashboard");
					});
				});
			});
		};
	}

	return {
		name : 'signup',
		url : '/signup',
		parent : layout,
		template : template,
		controller : signupController
	};
});