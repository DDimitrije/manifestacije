var primeriApp = angular.module("primeriApp", ['ngRoute']);

primeriApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/manifestacije.html'
    }).when('/manifestacije/edit/:id',{
        templateUrl: '/app/html/partial/edit-manifestacija.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

primeriApp.controller("manifestacijeCtrl", function($scope, $http, $location){

	var baseUrlTakmicenjaGodinae = "/api/takmicenjaGodinae";//trke
    var baseUrlManifestacije = "/api/manifestacije";
    

    $scope.promeniRezim = function(){
        $scope.rezimDodavanja = !$scope.rezimDodavanja;
    };
    $scope.rezimDodavanja = true;

    $scope.pageNum = 0;
    $scope.totalPages = 0;
          
    $scope.takmicenjaGodinae = [];//trke
    $scope.manifestacije = []; //e
    
    
    $scope.novaManifestacija = {};
    $scope.novaManifestacija.naziv = "";
    $scope.novaManifestacija.datumOdrzavanja = "";
    $scope.novaManifestacija.mestoOdrzavanja = "";
    $scope.novaManifestacija.takmicenjaGodinaId = "";
    $scope.novaManifestacija.takmicenjaGodinaNazivTrke = "";


    $scope.trazenaManifestacija = {};
    $scope.trazenaManifestacija.naziv = "";
    $scope.trazenaManifestacija.datumOdrzavanja = "";
    $scope.trazenaManifestacija.mestoOdrzavanja = "";
    //$scope.trazenaManifestacija.takmicenjaGodinaId = "";
   // $scope.trazenaManifestacija.takmicenjaNazivTrke = "";
    
    
    var getManifestacije = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazenaManifestacija.naziv != ""){
            config.params.naziv = $scope.trazenaManifestacija.naziv;
        }

        if($scope.trazenaManifestacija.datumOdrzavanja != ""){
            config.params.datumOdrzavanja = $scope.trazenaManifestacija.datumOdrzavanja;
        }

        if($scope.trazenaManifestacija.mestoOdrzavanja != ""){
            config.params.mestoOdrzavanja = $scope.trazenaManifestacija.mestoOdrzavanja;
        }
		
        $http.get(baseUrlManifestacije, config)
            .then(function success(data){
                $scope.manifestacije = data.data;
                $scope.totalPages = data.headers('totalPages');

            });
    };
      
    var getTakmicenjaGodinae = function(){ //Trke
    	//alert(" PORUKA ");
        $http.get(baseUrlTakmicenjaGodinae) //Trke
            .then(function success(data){
                $scope.takmicenjaGodinae = data.data; //trke
                //alert("Ušpesno ucitavanje vrednosti TakmicenjaGodinae ");
               // alert(  $scope.takmicenjaGodinae[0].nazivTrke);
                //alert(  Object.keys($scope.takmicenjaGodinae[0]));
               

            },
        	function error(data){
        		alert("Neušpesno ucitavanje vrednosti TakmicenjaGodinae ");
        	});

    };

    getTakmicenjaGodinae();  //Trke
    getManifestacije();
   

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getManifestacije();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getManifestacije();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlManifestacije, $scope.novaManifestacija)//Trka
            .then(function success(data){
            	getManifestacije();
            	
                $scope.novaManifestacija.naziv = "";
                $scope.novaManifestacija.datumOdrzavanja = "";
                $scope.novaManifestacija.mestoOdrzavanja = "";
                $scope.novaManifestacija.takmicenjaGodinaId = "";
                //$scope.novaManifestacija.takmicenjaNazivTrke = "";
                
                
            });
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getManifestacije();
    }

    $scope.izmeni = function(id){
        $location.path('/manifestacije/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlManifestacije + "/" + id).then(
            function success(data){
            	getManifestacije();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
    $scope.kupi = function(id){
    	$http.post(baseUrlManifestacije + "/" + id + "/kupovina").then(
    		function success(data){
    			alert("Manifestacije je uspesno kupljena.");
    			getManifestacije();
    		},
    		function error(data){
    			alert("Nije uspela kupovina knjige.")
    		}
    	);
    }
});
//
//primeriApp.controller("editManifestacijaCtrl", function($scope, $http, $routeParams, $location){
//
//    var baseUrlManifestacije = "/api/manifestacije";
//
//    $scope.staraManifestacija = null;
//
//    var getStaraManifestacija = function(){
//
//        $http.get(baseUrlManifestacije + "/" + $routeParams.id)
//            .then(
//            	function success(data){
//            		$scope.staraManifestacija = data.data;
//            	},
//            	function error(data){
//            		alert("Neu�pesno dobavljanje knjige.");
//            	}
//            );
//
//    }
//    getStaraManifestacija();
//    
//    $scope.izmeni = function(){
//        $http.put(baseUrlManifestacije + "/" + $scope.staraManifestacija.id, $scope.staraManifestacija)
//            .then(
//        		function success(data){
//        			alert("Uspe�no izmenjen objekat!");
//        			$location.path("/");
//        		},
//        		function error(data){
//        			alert("Neuspe�na izmena knjige.");
//        		}
//            );
//    }
    
    primeriApp.controller("editManifestacijaCtrl", function($scope, $http, $routeParams, $location){
    	                          
        var baseUrlKnjige = "/api/manifestacije";

        $scope.staraManifestacija = null;

        var getStaraManifestacija = function(){

            $http.get(baseUrlKnjige + "/" + $routeParams.id)
                .then(
                	function success(data){
                		$scope.staraManifestacija = data.data;
                	},
                	function error(data){
                		alert("Neušpesno dobavljanje knjige.");
                	}
                );

        }
        getStaraManifestacija();
        
        $scope.izmeni = function(){
            $http.put(baseUrlKnjige + "/" + $scope.staraManifestacija.id, $scope.staraManifestacija)
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
