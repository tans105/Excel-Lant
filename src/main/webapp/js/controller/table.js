/**
 * Created by Tan$ on 12/3/2016.
 */

var app = angular.module('excelLent', [ 'ui.select', 'ngSanitize',
		'ngMaterial', 'ngMessages', 'material.svgAssetsCache' ]);
app
		.controller(
				'downloadCtrl',
				function($scope, $http, $timeout, $interval) {

					app.filter('propsFilter', function() {
						return function(items, props) {
							var out = [];

							if (angular.isArray(items)) {
								var keys = Object.keys(props);

								items.forEach(function(item) {
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
					$scope.loading = true;
					$http({
						method : 'GET',
						url : '/ExcelLent/web/getTableList'
					}).then(
							function successCallback(response) {
								console.log(response);
								var decodedResponse = JSON.parse(angular
										.toJson(response));
								var data = decodedResponse["data"];
								console.log(data);
								$scope.tableList = data;
								$scope.loading = false;

							}, function errorCallback(response) {
								console.log(response);
								$scope.loading = false;

							});

					$scope.getTableColumns = function() {
						console.log($scope.tableList.selected);
						$scope.loading = true;
						$http({
							method : 'POST',
							url : '/ExcelLent/web/getColumnsOfTable',
							data : {
								'tableName' : $scope.tableList.selected
							}

						}).then(
								function successCallback(response) {
									console.log(response);
									var decodedResponse = JSON.parse(angular
											.toJson(response));
									var data = decodedResponse["data"];
									console.log(data);
									$scope.items = data;
									$scope.selectedList = angular.copy(data);
									$scope.columnList = data;
									$scope.loading = false;

								}, function errorCallback(response) {
									$scope.loading = false;
									console.log(response);

								});

					}

					$scope.toggle = function(item, list) {
						var idx = list.indexOf(item);
						if (idx > -1) {
							list.splice(idx, 1);
						} else {
							list.push(item);
						}
					};

					$scope.exists = function(item, list) {
						return list.indexOf(item) > -1;
					};

					// $scope.isIndeterminate = function() {
					// return ($scope.selected.length !== 0 &&
					// $scope.selected.length !== $scope.items.length);
					// };

					$scope.toggleAll = function() {
						if ($scope.selectedList.length === $scope.items.length) {
							$scope.selectedList = [];
						} else if ($scope.selectedList.length === 0
								|| $scope.selectedList.length > 0) {
							$scope.selectedList = $scope.items.slice(0);
						}
					};

					$scope.isChecked = function() {
						if (angular.isDefined($scope.selectedList)
								&& angular.isDefined($scope.items)) {
							return ($scope.selectedList.length == $scope.items.length);
						} else {
							return false;
						}
					}

					$scope.downloadTable = function() {
						console.log("Download Table called!!!");
						console.log("TABLE NAME: " + $scope.tableList.selected);
						console.log("COLUMN NAME: " + $scope.selectedList);
						$scope.loading = true;
						$http({
							method : 'POST',
							url : '/ExcelLent/web/downloadTableData',
							responseType : "arraybuffer",
							data : {
								'tableName' : $scope.tableList.selected,
								'columnList' : $scope.selectedList
							}

						})
								.then(
										function successCallback(response) {
											console.log(response);
											var byteLength = response.data.byteLength;
											console.log(response.data.byteLength);
											if (byteLength == 0) {
												alert("System Table Detected...Taking care of it!!");
											} else {
												var header = response
														.headers('Content-Disposition')
												var fileName = header
														.split("=")[1].replace(
														/\"/gi, '');

												var blob = new Blob(
														[ response.data ],
														{
															type : 'application/vnd.openxmlformats-officedocument.presentationml.presentation;charset=UTF-8'
														});
												var objectUrl = (window.URL || window.webkitURL)
														.createObjectURL(blob);
												var link = angular
														.element('<a/>');
												link.attr({
													href : objectUrl,
													download : fileName
												})[0].click();
											}
											$scope.loading = false;

										}, function errorCallback(response) {
											$scope.loading = false;
											console.log(response);

										});

					}

					$scope.reset = function() {
						console.log("reset called");
						$scope.tableList.selected = null;
						$scope.selectedList = [];
						$scope.items = [];
						$scope.columnList = [];

					}

					function arrayBufferToString(buf) {
						return String.fromCharCode.apply(null, new Uint16Array(
								buf));
					}

				});