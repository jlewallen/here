'use strict';
define(['lib/angular/angular',
		'lib/angular/angular-ui-router',
		'lib/angular/ui-bootstrap',
		'shared/modules/repositories-module',
		'shared/services/url-helper',
		'lib/less'
	],
	function(angular) {
		return angular.module('here', ['ui.router', 'ui.bootstrap', 'here.repos', 'here.shared']);
	}
);