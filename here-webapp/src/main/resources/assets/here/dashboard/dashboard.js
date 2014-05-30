'use strict';
define(['text!./dashboard.html', 'layout/default-layout', 'app/angular-module'], function(template, layout, module) {
	dashboardController.$inject = ['$scope', '$state'];

	function dashboardController($scope, $state) {
	}

	return {
		name : 'dashboard',
		url : '/dashboard',
		parent : layout,
		template : template,
		controller : dashboardController
	};
});