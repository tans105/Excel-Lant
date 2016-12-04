/**
 * Created by Tan$ on 12/3/2016.
 */

var app = angular.module('excelLent',
		['ngSanitize', 'ui.select']);
app.controller('downloadCtrl', function($scope, $http, $timeout, $interval) {

	$scope.reportMst = [ {
		"name" : "Two Wheeler Report",
		"code" : "0001"
	}, {
		"name" : "3M Report",
		"code" : "0002"
	}, {
		"name" : "Three Wheeler Report",
		"code" : "0003"
	}, ];

	
	
	$http({
		method : 'GET',
		url : '/ExcelLent/web/getTableDropDown'
	}).then(function successCallback(response) {
		console.log("Table Data----------->" + response);
	}, function errorCallback(response) {
		console.log(response);
	});
	
	  
});