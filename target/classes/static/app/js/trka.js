var primeriApp = angular.module("primeriApp", ['ngRoute']);

primeriApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/trke.html'
    }).when('/trke/edit/:id',{
        templateUrl: '/app/html/partial/edit-trka.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

primeriApp.controller("trkeCtrl", function($scope, $http, $location){

	var baseUrlManifestacije = "/api/manifestacije";//trke
    var baseUrlTrke = "/api/trke";
    

    $scope.promeniRezim = function(){
        $scope.rezimDodavanja = !$scope.rezimDodavanja;
    };
    $scope.rezimDodavanja = true;

    $scope.pageNum = 0;
    $scope.totalPages = 0;
          
    $scope.manifestacije = [];//trke
    $scope.trke = []; //e
    
    
    $scope.novaTrka = {};
    $scope.novaTrka.duzinaStaze = "";
    $scope.novaTrka.kategorija = "";
//    $scope.novaTrka.mestoOdrzavanja = "";
//    $scope.novaTrka.manifestacijaId = "";
//    $scope.novaTrka.manifestacijaNazivTrke = "";


    $scope.trazenaTrka = {};
    $scope.trazenaTrka.duzinaStaze = "";
    $scope.trazenaTrka.kategorija = "";
    //$scope.trazenaTrka.mestoOdrzavanja = "";
    //$scope.trazenaTrka.manifestacijaId = "";
   // $scope.trazenaTrka.takmicenjaNazivTrke = "";
    
    
    var getTrke = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazenaTrka.duzinaStaze != ""){
            config.params.duzinaStaze = $scope.trazenaTrka.duzinaStaze;
        }

        if($scope.trazenaTrka.kategorija != ""){
            config.params.kategorija = $scope.trazenaTrka.kategorija;
        }

//        if($scope.trazenaTrka.mestoOdrzavanja != ""){
//            config.params.mestoOdrzavanja = $scope.trazenaTrka.mestoOdrzavanja;
//        }
		
        $http.get(baseUrlTrke, config)
            .then(function success(data){
                $scope.trke = data.data;
                $scope.totalPages = data.headers('totalPages');

            });
    };
      
    var getManifestacije = function(){ //Trke
    	alert(" PORUKA ");
        $http.get(baseUrlTrke) //Trke
            .then(function success(data){
                $scope.manifestacije = data.data; //trke
                alert("Ušpesno ucitavanje vrednosti Manifestacije ");
                alert(  $scope.manifestacije[0].duzinaStaze);
                //alert(  Object.keys($scope.manifestacije[0]));
               

            },
        	function error(data){
        		alert("Neušpesno ucitavanje vrednosti Manifestacije ");
        	});

    };
    
    getManifestacije();  //Trke
    getTrke();
   

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getTrke();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getTrke();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlTrke, $scope.novaTrka)//Trka
            .then(function success(data){
            	getTrke();
            	
                $scope.novaTrka.naziv = "";
                $scope.novaTrka.duzinaStaze = "";
                $scope.novaTrka.kategorija = "";
                //$scope.novaTrka.manifestacijaId = "";
                //$scope.novaTrka.takmicenjaNazivTrke = "";
                
                
            });
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getTrke();
    }

    $scope.izmeni = function(id){
        $location.path('/trke/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlTrke + "/" + id).then(
            function success(data){
            	getTrke();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
    $scope.kupi = function(id){
    	$http.post(baseUrlTrke + "/" + id + "/kupovina").then(
    		function success(data){
    			alert("Trke je uspesno kupljena.");
    			getTrke();
    		},
    		function error(data){
    			alert("Nije uspela kupovina knjige.")
    		}
    	);
    }
});

    
primeriApp.controller("editTrkaCtrl", function($scope, $http, $routeParams, $location){
    	                          
        var baseUrlKnjige = "/api/trke";

        $scope.staraTrka = null;

        var getStaraTrka = function(){

            $http.get(baseUrlKnjige + "/" + $routeParams.id)
                .then(
                	function success(data){
                		$scope.staraTrka = data.data;
                	},
                	function error(data){
                		alert("Neušpesno dobavljanje knjige.");
                	}
                );

        }
        getStaraTrka();
        
        $scope.izmeni = function(){
            $http.put(baseUrlKnjige + "/" + $scope.staraTrka.id, $scope.staraTrka)
                .then(
            		function success(data){
            			alert("Uspešno izmenjen objekat!");
            			$location.path("/");
            		},
            		function error(data){
            			alert("Neuspešna izmena knjige.");
            		}
                );
        }    
    
});
