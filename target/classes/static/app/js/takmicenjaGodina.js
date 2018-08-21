var primeriApp = angular.module("primeriApp", ['ngRoute']);

primeriApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/takmicenjaGodinae.html'
    }).when('/takmicenjaGodinae/edit/:id',{
        templateUrl: '/app/html/partial/edit-takmicenjaGodina.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

//}).when('/knjige/edit/:id',{
//    templateUrl: '/app/html/partial/edit-knjiga.html'

primeriApp.controller("takmicenjaGodinaeCtrl", function($scope, $http, $location){

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
    
    
    $scope.novaTakmicenjaGodina = {};
    $scope.novaTakmicenjaGodina.nazivTrke = "";



    $scope.trazenaTakmicenjaGodina = {};
    $scope.trazenaTakmicenjaGodina.nazivTrke = "";

    
    
    var getTakmicenjaGodinae = function(){ // Manifestacije

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazenaTakmicenjaGodina.nazivTrke != ""){
            config.params.nazivTrke = $scope.trazenaTakmicenjaGodina.nazivTrke;
        }

//        if($scope.trazenaManifestacija.datumOdrzavanja != ""){
//            config.params.datumOdrzavanja = $scope.trazenaManifestacija.datumOdrzavanja;
//        }
//
//        if($scope.trazenaManifestacija.mestoOdrzavanja != ""){
//            config.params.mestoOdrzavanja = $scope.trazenaManifestacija.mestoOdrzavanja;
//        }
		
        $http.get(baseUrlTakmicenjaGodinae, config)
            .then(function success(data){
                $scope.takmicenjaGodinae = data.data;
                $scope.totalPages = data.headers('totalPages');

            });
    };
      
    var getTakmicenjaGodinaeDva = function(){ //Trke
    	//alert(" PORUKA ");
        $http.get(baseUrlTakmicenjaGodinae) //Trke
            .then(function success(data){
                $scope.takmicenjaGodinae = data.data; //trke
                //alert("Ušpesno ucitavanje vrednosti TakmicenjaGodinae ");
               // alert(  $scope.takmicenjaGodinae[0].nazivTrke);
               // alert(  Object.keys($scope.takmicenjaGodinae[0]));
               

            },
        	function error(data){
        		alert("Neušpesno ucitavanje vrednosti TakmicenjaGodinae ");
        	});

    };

    getTakmicenjaGodinaeDva();  //Trke
    getTakmicenjaGodinae();
   

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getTakmicenjaGodinae();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getTakmicenjaGodinae();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlTakmicenjaGodinae, $scope.novaTakmicenjaGodina)//Trka
            .then(function success(data){
            	getTakmicenjaGodinae();
            	
                $scope.novaTakmicenjaGodina.nazivTrke = "";
//                $scope.novaManifestacija.datumOdrzavanja = "";
//                $scope.novaManifestacija.mestoOdrzavanja = "";
//                $scope.novaManifestacija.takmicenjaGodinaId = "";
                //$scope.novaManifestacija.takmicenjaNazivTrke = "";
                
                
            });
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getTakmicenjaGodinae();
    }

    $scope.izmeni = function(id){
        $location.path('/takmicenjaGodinae/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlTakmicenjaGodinae + "/" + id).then(
            function success(data){
            	getTakmicenjaGodinae();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
//    $scope.kupi = function(id){
//    	$http.post(baseUrlTakmicenjaGodinae + "/" + id + "/kupovina").then(
//    		function success(data){
//    			alert("Manifestacije je uspesno kupljena.");
//    			getTakmicenjaGodinae();
//    		},
//    		function error(data){
//    			alert("Nije uspela kupovina knjige.")
//    		}
//    	);
//    }
});

//primeriApp.controller("editTakmicenjaGodinaCtrl", function($scope, $http, $routeParams, $location){
//
//    //var baseUrlManifestacije = "/api/manifestacije";
//    var baseUrlTakmicenjaGodinae = "/api/takmicenjaGodinae";
//
//    $scope.staraTakmicenjaGodina = null;
//
//    var getStaraTakmicenjaGodina = function(){
//
//        $http.get(baseUrlTakmicenjaGodinae + "/" + $routeParams.id)
//            .then(
//            	function success(data){
//            		$scope.staraTakmicenjaGodina = data.data;
//            	},
//            	function error(data){
//            		alert("Neu�pesno dobavljanje knjige.");
//            	}
//            );
//
//    }
//    getStaraTakmicenjaGodina();
//    
//    $scope.izmeni = function(){
//        $http.put(baseUrlTakmicenjaGodinae + "/" + $scope.staraTakmicenjaGodina.id, $scope.staraTakmicenjaGodina)
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
                        
 primeriApp.controller("editTakmicenjaGodinaCtrl", function($scope, $http, $routeParams, $location){

        var baseUrlKnjige = "/api/takmicenjaGodinae";

        $scope.staraTakmicenjaGodina = null;

        var getStaraTakmicenjaGodina = function(){

            $http.get(baseUrlKnjige + "/" + $routeParams.id)
                .then(
                	function success(data){
                		$scope.staraTakmicenjaGodina = data.data;
                	},
                	function error(data){
                		alert("Neušpesno dobavljanje knjige.");
                	}
                );

        }
        getStaraTakmicenjaGodina();
        
        $scope.izmeni = function(){
            $http.put(baseUrlKnjige + "/" + $scope.staraTakmicenjaGodina.id, $scope.staraTakmicenjaGodina)
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
