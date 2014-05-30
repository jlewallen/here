require.config({
	baseUrl : "/assets/here",
	paths : {
		'text' : 'lib/text',
		"moment" : "lib/moment"
	},
	shim : {
		'test-util' : ['angular-matchers'],
		'lib/angular/angular' : { deps : ['lib/jquery'], exports : 'angular' },
		'lib/angular/angular-ui-router' : ['lib/angular/angular'],
		'lib/angular/ui-bootstrap' : ['lib/angular/angular'],
		'lib/angular/angular-mocks' : ['lib/angular/angular'],
		'lib/sprintf' : {
			exports : 'sprintf'
		}
	}
});

require(["app/angular-start"], function() {
});