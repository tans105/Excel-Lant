/**
 * Created by Tan$ on 12/3/2016.
 */

var app = angular.module('excelLent', [ 'ui.select', 'ngSanitize','ngMaterial', 'ngMessages', 'material.svgAssetsCache' ]);
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
	
	$scope.items = [1,2,3,4,5];
	  $scope.selected = [1];
	  $scope.toggle = function (item, list) {
	    var idx = list.indexOf(item);
	    if (idx > -1) {
	      list.splice(idx, 1);
	    }
	    else {
	      list.push(item);
	    }
	  };

	  $scope.exists = function (item, list) {
	    return list.indexOf(item) > -1;
	  };

	  $scope.isIndeterminate = function() {
	    return ($scope.selected.length !== 0 &&
	        $scope.selected.length !== $scope.items.length);
	  };

	  $scope.isChecked = function() {
	    return $scope.selected.length === $scope.items.length;
	  };

	  $scope.toggleAll = function() {
	    if ($scope.selected.length === $scope.items.length) {
	      $scope.selected = [];
	    } else if ($scope.selected.length === 0 || $scope.selected.length > 0) {
	      $scope.selected = $scope.items.slice(0);
	    }
	  };

});