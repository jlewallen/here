'use strict';
define(['text!./dashboard.html', 'layout/default-layout', 'app/angular-module'], function(template, layout, module) {
	dashboardController.$inject = ['$scope', '$state', 'defaultResource'];

	function dashboardController($scope, $state, defaultResource) {
		defaultResource("profile").get().then(function(profile) {
			$scope.profile = profile;
		});

		defaultResource("available-places").get().then(function(places) {
			$scope.places = places;
		});

		$scope.checkin = function(place) {
			defaultResource("places", place.id, "checkins").post({}).then(function(checkin) {
				console.log(checkin);
			});
		};
	}

	return {
		name : 'dashboard',
		url : '/dashboard',
		parent : layout,
		template : template,
		controller : dashboardController
	};
});