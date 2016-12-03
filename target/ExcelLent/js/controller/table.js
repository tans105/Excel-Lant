/**
 * Created by Tan$ on 12/3/2016.
 */

var app = angular.module('excelLent', []);
app.controller('downloadCtrl', function($scope,$http) {
    $scope.name= "Tanmay";
    $http({
        method: 'GET',
        url: '/excelLent/web/getTableDropDown'
    }).then(function successCallback(response) {
        console.log("Table Data----------->"+response);
    }, function errorCallback(response) {
        console.log(response);
    });

});