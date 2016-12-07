/**
 * Created by Tan$ on 12/3/2016.
 */

var app = angular.module('excelLent', [ 'ui.select', 'ngSanitize' ]);
app.controller('downloadCtrl', function($scope, $http, $timeout, $interval) {

	app.filter('propsFilter', function() {
		return function(items, props) {
			var out = [];

			if (angular.isArray(items)) {
				var keys = Object.keys(props);

				items
						.forEach(function(item) {
							var itemMatches = false;

							for (var i = 0; i < keys.length; i++) {
								var prop = keys[i];
								var text = props[prop].toLowerCase();
								if (item[prop].toString().toLowerCase()
										.indexOf(text) !== -1) {
									itemMatches = true;
									break;
								}
							}

							if (itemMatches) {
								out.push(item);
							}
						});
			} else {
				// Let the output be the input untouched
				out = items;
			}

			return out;
		};
	});

	$scope.tableList = null;

	$http({
		method : 'GET',
		url : '/ExcelLent/web/getTableList'
	}).then(function successCallback(response) {
		console.log(response);
		var decodedResponse = JSON.parse(angular.toJson(response));
		var data = decodedResponse["data"];
		console.log(data);
		$scope.tableList = data;

	}, function errorCallback(response) {
		console.log(response);

	});
	
	

	$scope.getTableColumns = function() {
		console.log($scope.tableList.selected);
		$http({
			method : 'POST',
			url : '/ExcelLent/web/getColumnsOfTable',
			data : {
				'tableName' : $scope.tableList.selected
			}

		}).then(function successCallback(response) {
			console.log(response);
			var decodedResponse = JSON.parse(angular.toJson(response));
			var data = decodedResponse["data"];
			console.log(data);
			$scope.columnList = data;

		}, function errorCallback(response) {
			console.log(response);

		});

	}

});