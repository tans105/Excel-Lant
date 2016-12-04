<!DOCTYPE html>
<html lang="en-US">
<head>
<!-- Latest compiled and minified CSS -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.1/angular-material.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-select/0.20.0/select.min.css">
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.css">

</head>
<body>

	<div ng-app="excelLent" ng-controller="downloadCtrl as ctrl"
		style="padding: 10px; margin-top: 150px; margin-left: 150px; margin-right: 150px; background: white;"
		md-whiteframe="15">
		<div class="row">
			<div class="col-md-6">
				<h3 style="color: #607D8B;">Select Report to download :</h3>
			</div>
			<div class="col-md-6" style="margin-top: 20px; float: left;'">
			<ui-select ng-model="reportMst.selectedDownloadType" theme="select2">
			<ui-select-match placeholder="Report types...">{{$select.selected.name}}</ui-select-match>
			<ui-select-choices
				repeat="type in reportMst | propsFilter: {name: $select.search ,code: $select.search}">
			<span ng-bind-html="type.name | highlight: $select.search"></span> </ui-select-choices> </ui-select>
		</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.0/angular-animate.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.0/angular-aria.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.0/angular-messages.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.0/angular-sanitize.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.1/angular-material.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-select/0.20.0/select.min.js"></script>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>







	<script src="js/controller/table.js"></script>
</body>
</html>
