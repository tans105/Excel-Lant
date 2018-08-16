<!DOCTYPE html>
<html lang="en-US">

<head>
    <!-- External Javascript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-sanitize.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.8/angular-animate.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.8/angular-aria.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-messages/1.6.0/angular-messages.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.1/angular-material.min.js"></script>
    <script src="http://ngmaterial.assets.s3.amazonaws.com/svg-assets-cache.js"></script>
    <script src="js/libs/select.js"></script>


    <!-- External Stylesheets -->
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.4.5/select2.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.8.5/css/selectize.default.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.1/angular-material.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style.css">

    <!-- Project stylesheets -->
    <link rel="stylesheet" href="css/select.css">
    <link rel="stylesheet" href="css/loader.css">

    <!-- Project Controllers -->
    <script src="js/controller/table.js"></script>
</head>

<body>
    <div ng-app="excelLent" ng-controller="downloadCtrl">
        <!-- <div style="text-align:center;">
			<img src="./images/excellent.PNG">
		</div> -->
        <div style="padding: 10px; /* margin-top: 40px; */ margin-left: 150px; margin-right: 150px; background: white;" md-whiteframe="5">
            <div class="row">
                <div class="col-md-3">
                    <h4>Select Table to download :</h4>
                </div>
                <div class="col-md-7"">
					<ui-select ng-model=" tableList.selected" ng-disabled="loading" theme="bootstrap" ng-change="getTableColumns()">
                    <ui-select-match placeholder="Select the table to Download...">{{$select.selected}}</ui-select-match>
                    <ui-select-choices repeat="item in tableList | filter: $select.search">
                        <div ng-bind-html="item | highlight: $select.search"></div>

                    </ui-select-choices>
                    </ui-select>
                </div>
                <div class="col-md-1">
                    <button type="button" class="btn btn-primary btn-s" ng-disabled="loading||!tableList.selected" ng-click="downloadTable()" ng-disabled="!tableList.selected && !selectedList">
                        <span class="glyphicon glyphicon-download"></span> Download
                    </button>
                </div>
                <div class="col-md-1">
                    <button type="button" class="btn btn-danger btn-s" ng-click="reset()" ng-disabled="loading">Reset</button>
                </div>
            </div>
        </div>
        <div class="loader" ng-show="loading">
            <i class="fa fa-spinner fa-spin fa-5x fa-fw"></i>
        </div>
        <div style="padding: 10px; margin-top: 50px; margin-left: 572px; margin-right: 591px; background: white;" md-whiteframe="15">
            <div layout="row" layout-wrap="">
                <div flex="100" layout="column">
                    <div>

                        <fieldset class="demo-fieldset">
                            <legend class="demo-legend">
                                <h3>Select the columns</h3>
                            </legend>
                            <div layout="row" layout-wrap="" flex="">
                                <div flex-xs="" flex="50">
                                    <md-checkbox ng-disabled="loading" aria-label="Select All" ng-checked="isChecked()" ng-click="toggleAll()"> Select All </md-checkbox>
                                </div>
                                <hr>
                                <hr>
                                <div class="container">
                                    <div class="demo-select-all-checkboxes" flex="100" ng-repeat="item in items">
                                        <md-checkbox ng-checked="exists(item, selectedList)" ng-click="toggle(item, selectedList)" ng-disabled="loading"> {{ item
                                            }} </md-checkbox>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>
            </div>

        </div>
    </div>
</body>

</html>
