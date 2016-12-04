<!DOCTYPE html>
<html lang="en-US">

<body>
	<%--
<form action="/excelLent/web/downloadTableData" method="POST">

    <button type="submit" value="Submit">Submit</button>

</form>
--%>
	<div ng-app="excelLent" ng-controller="downloadCtrl">
		<p>
			Name: <input type="text" ng-model="name">
		</p>
		<p>{{name}}</p>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

	<script src="js/controller/table.js"></script>
</body>
</html>
