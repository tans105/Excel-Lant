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
			$scope.items = data;
			$scope.selectedList = angular.copy(data);
			$scope.columnList = data;

		}, function errorCallback(response) {
			console.log(response);

		});

	}
	

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

//	  $scope.isIndeterminate = function() {
//	    return ($scope.selected.length !== 0 &&
//	        $scope.selected.length !== $scope.items.length);
//	  };

	  $scope.toggleAll = function() {
	    if ($scope.selectedList.length === $scope.items.length) {
	      $scope.selectedList = [];
	    } else if ($scope.selectedList.length === 0 || $scope.selectedList.length > 0) {
	      $scope.selectedList = $scope.items.slice(0);
	    }
	  };
	  
	  $scope.isChecked = function(){
		  if(angular.isDefined($scope.selectedList) && angular.isDefined($scope.items)){
			  return ($scope.selectedList.length == $scope.items.length);
		  }else{
			  return false;
		  }
	  }
	  
	  $scope.downloadTable=function(){
		  console.log("Download Table called!!!");
		  console.log("TABLE NAME: " +$scope.tableList.selected);
		  console.log("COLUMN NAME: " +$scope.selectedList);
	  }
	  
	  $scope.reset=function(){
		  console.log("reset called");
		  $scope.tableList.selected=null;
		  $scope.selectedList=[];
		  $scope.items=[];
		  $scope.columnList=[];
		  
		  
	  }

});