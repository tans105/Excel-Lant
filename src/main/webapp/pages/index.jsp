<!DOCTYPE html>
<html lang="en-US">
<head>
<!-- Latest compiled and minified CSS -->


<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-sanitize.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.8/angular-animate.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.8/angular-aria.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-messages/1.6.0/angular-messages.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.1/angular-material.min.js"></script>
<script
	src="http://ngmaterial.assets.s3.amazonaws.com/svg-assets-cache.js"></script>
<!-- ui-select files -->
<script src="js/libs/select.js"></script>
<link rel="stylesheet" href="css/select.css">

<script src="js/controller/table.js"></script>

<!-- themes -->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.4.5/select2.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.8.5/css/selectize.default.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.1/angular-material.min.css">



</head>
<body>

	<div ng-app="excelLent" ng-controller="downloadCtrl"
		style="padding: 10px; margin-top: 150px; margin-left: 150px; margin-right: 150px; background: white;"
		md-whiteframe="15">
		<div class="row">
			<div class="col-md-3">
				<h4 style="color: #607D8B;">Select Table to download :</h4>
			</div>
			<div class="col-md-4"">
				<ui-select ng-model="tableList.selected" theme="bootstrap"
					ng-change="getTableColumns()"> <ui-select-match
					placeholder="Select the table to Download...">{{$select.selected}}</ui-select-match>
				<ui-select-choices
					repeat="item in tableList | filter: $select.search">
				<div ng-bind-html="item | highlight: $select.search"></div>

				</ui-select-choices> </ui-select>
			</div>

			<div class="col-md-4"">
				<ui-select ng-model="columnList.selected" theme="bootstrap"
					ng-disabled="(!tableList.selected)"> <ui-select-match
					placeholder="Select the table to Download...">{{$select.selected}}</ui-select-match>
				<ui-select-choices repeat="item in columnList | filter: $select.search">
				<div ng-bind-html="item | highlight: $select.search"></div>

				</ui-select-choices> </ui-select>
			</div>

			<div class="col-md-1">
				<button type="button" class="btn btn-primary btn-s" ng-click="#">
					<span class="glyphicon glyphicon-download"></span> Download
				</button>
			</div>
		</div>

		<div layout="row" layout-wrap="">
			<div flex="100" layout="column">
				<div>

					<fieldset class="demo-fieldset">
						<legend class="demo-legend">Select the columns </legend>
						<div layout="row" layout-wrap="" flex="">
							<div flex-xs="" flex="50">
								<md-checkbox aria-label="Select All" ng-checked="isChecked()"
									ng-click="toggleAll()">
								Select All </md-checkbox>
							</div>
							<div class="demo-select-all-checkboxes" flex="100"
								ng-repeat="item in items">
								<md-checkbox ng-checked="exists(item, selectedList)"
									ng-click="toggle(item, selectedList)"> {{ item }} </md-checkbox>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
