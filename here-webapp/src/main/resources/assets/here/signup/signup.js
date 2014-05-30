'use strict';
define(['text!./signup.html', 'layout/default-layout', 'app/angular-module'], function(template, layout, module) {
	signupController.$inject = ['$scope', '$state', 'defaultResource'];

	function signupController($scope, $state, defaultResource) {
		$scope.signupInfo = {
			firstName : 'John',
			lastName : 'Doe',
			email : 'jdoe@test.com',
			password : 'asdfasdf'
		};
		$scope.signup = function() {
			defaultResource("profile").post($scope.signupInfo).then(function(id) {
				console.log(id);
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