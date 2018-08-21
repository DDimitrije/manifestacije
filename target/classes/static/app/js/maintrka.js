var primeriApp = angular.module("primeriApp", ['ngRoute']);

primeriApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/trkaci.html'
    }).when('/trkaci/edit/:id',{
        templateUrl: '/app/html/partial/edit-trkaci.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

primeriApp.controller("trkaciCtrl", function($scope, $http, $location){

	var baseUrlTrke = "/api/trke";
    var baseUrlTrkaci = "/api/trkaci";
    

    $scope.promeniRezim = function(){
        $scope.rezimDodavanja = !$scope.rezimDodavanja;
    };
    $scope.rezimDodavanja = true;

    $scope.pageNum = 0;
    $scope.totalPages = 0;

    $scope.trke = [];
    $scope.trkaci = [];

    $scope.novaTrkac = {};
    $scope.novaTrkac.ime = "";
    $scope.novaTrkac.prezime = "";
    $scope.novaTrkac.pol = "";
    $scope.novaTrkac.adresa = "";
    $scope.novaTrkac.grad = "";
    $scope.novaTrkac.klub = "";
    $scope.novaTrkac.najBoljeVreme = "";
    $scope.novaTrkac.velicinaMajce = "";
    $scope.novaTrkac.trkaId = "";


    $scope.trazenaTrkac = {};
    $scope.trazenaTrkac.ime = "";
    $scope.trazenaTrkac.prezime = "";
    $scope.trazenaTrkac.klub = "";

    var getTrkaci = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazenaTrkac.ime != ""){
            config.params.ime = $scope.trazenaTrkac.ime;
        }

        if($scope.trazenaTrkac.prezime != ""){
            config.params.prezime = $scope.trazenaTrkac.prezime;
        }

        if($scope.trazenaTrkac.klub != ""){
            config.params.klub = $scope.trazenaTrkac.klub;
        }
		

        $http.get(baseUrlTrkaci, config)
            .then(function success(data){
                $scope.trkaci = data.data;
                $scope.totalPages = data.headers('totalPages');

            });
    };

    var getTrke = function(){

        $http.get(baseUrlTrke)
            .then(function success(data){
                $scope.trke = data.data;
            });

    };

    getTrke();
    getTrkaci();
   

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getTrkaci();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getTrkaci();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlTrkaci, $scope.novaTrkac)
            .then(function success(data){
            	getTrkaci();
            	
            	
    			$scope.novaTrkac.ime = "";
  			    $scope.novaTrkac.prezime = "";
    			$scope.novaTrkac.pol = "";
    			$scope.novaTrkac.adresa = "";
    			$scope.novaTrkac.grad = "";
    			$scope.novaTrkac.klub = "";
    			$scope.novaTrkac.najBoljeVreme = "";
    			$scope.novaTrkac.velicinaMajce = "";
    			$scope.novaTrkac.trkaId = "";

            });
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getTrkaci();
    }

    $scope.izmeni = function(id){
        $location.path('/trkaci/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlTrkaci + "/" + id).then(
            function success(data){
            	getTrkaci();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
    $scope.kupi = function(id){
    	$http.post(baseUrlTrkaci + "/" + id + "/kupovina").then(
    		function success(data){
    			alert("Trkaci je uspesno kupljena.");
    			getTrkaci();
    		},
    		function error(data){
    			alert("Nije uspela kupovina knjige.")
    		}
    	);
    }
});

primeriApp.controller("editTrkacCtrl", function($scope, $http, $routeParams, $location){

    var baseUrlTrkaci = "/api/trkaci";

    $scope.staraTrkac = null;

    var getStaraTrkac = function(){

        $http.get(baseUrlTrkaci + "/" + $routeParams.id)
            .then(
            	function success(data){
            		$scope.staraTrkac = data.data;
            	},
            	function error(data){
            		alert("Neu�pesno dobavljanje knjige.");
            	}
            );

    }
    getStaraTrkac();
    
    $scope.izmeni = function(){
        $http.put(baseUrlTrkaci + "/" + $scope.staraTrkac.id, $scope.staraTrkac)
            .then(
        		function success(data){
        			alert("Uspe�no izmenjen objekat!");
        			$location.path("/");
        		},
        		function error(data){
        			alert("Neuspe�na izmena knjige.");
        		}
            );
    }
});
