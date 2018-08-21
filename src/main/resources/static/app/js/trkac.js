var primeriApp = angular.module("primeriApp", ['ngRoute']);

primeriApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/trkaci.html'
    }).when('/trkaci/edit/:id',{
        templateUrl: '/app/html/partial/edit-trkac.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

primeriApp.controller("trkaciCtrl", function($scope, $http, $location){

	var baseUrlTrke = "/api/trke";//trke
    var baseUrlTrkaci = "/api/trkaci";
    

    $scope.promeniRezim = function(){
        $scope.rezimDodavanja = !$scope.rezimDodavanja;
    };
    $scope.rezimDodavanja = true;

    $scope.pageNum = 0;
    $scope.totalPages = 0;
          
    $scope.trke = [];//trke
    $scope.trkaci = []; //e
    $scope.pol = [];
    
    $scope.novaTrkac = {};
    $scope.novaTrkac.ime = "";
    $scope.novaTrkac.prezime = "";
    $scope.novaTrkac.pol = "";
    $scope.novaTrkac.velicinaMajce = "";
    $scope.novaTrkac.adresa = "";
    $scope.novaTrkac.grad = "";
    $scope.novaTrkac.drzava = "";
    $scope.novaTrkac.najBoljeVreme = "";
    $scope.novaTrkac.klub = "";


    $scope.trazenaTrkac = {};
    $scope.trazenaTrkac.ime = "";
    $scope.trazenaTrkac.prezime = "";
    $scope.trazenaTrkac.pol= "";  
    $scope.trazenaTrkac.velicinaMajce = "";
    $scope.trazenaTrkac.adresa = "";   
    $scope.trazenaTrkac.grad = "";
    $scope.trazenaTrkac.drzava = "";
    $scope.trazenaTrkac.najBoljeVreme = "";
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

        if($scope.trazenaTrkac.pol != ""){
            config.params.pol = $scope.trazenaTrkac.pol;
        }
		
        if($scope.trazenaTrkac.velicinaMajce != ""){
            config.params.velicinaMajce = $scope.trazenaTrkac.velicinaMajce;
        }
        
        if($scope.trazenaTrkac.adresa != ""){
            config.params.adresa = $scope.trazenaTrkac.adresa;
        }
        
        if($scope.trazenaTrkac.grad != ""){
            config.params.grad = $scope.trazenaTrkac.grad;
        }
        
        if($scope.trazenaTrkac.drzava != ""){
            config.params.drzava = $scope.trazenaTrkac.drzava;
        }
        
        if($scope.trazenaTrkac.najBoljeVreme != ""){
            config.params.najBoljeVreme = $scope.trazenaTrkac.najBoljeVreme;
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
      
    var getTrke = function(){ //Trke
    	//alert(" PORUKA ");
        $http.get(baseUrlTrke) //Trke
            .then(function success(data){
                $scope.trke = data.data; //trke
                //alert("Ušpesno ucitavanje vrednosti Trke ");
               // alert(  $scope.trke[0].nazivTrke);
                //alert(  Object.keys($scope.trke[0]));
               

            },
        	function error(data){
        		//alert("Neušpesno ucitavanje vrednosti Trke t");
        	});

    };

    getTrke();  //Trke
    getTrkaci();
   
    var getPol = function(){ //Trke
    	//alert(" PORUKA ");
        $http.get(baseUrlTrkaci) //Trke
            .then(function success(data){
                $scope.pol = data.data; //trke
                //alert("Ušpesno ucitavanje vrednosti Trke ");
                //alert(  $scope.pol[0].pol);
                //alert(  Object.keys($scope.trke[0]));
               

            },
        	function error(data){
        		//alert("Neušpesno ucitavanje vrednosti Trke ");
        	});

    };
    
    getPol();
    

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
        $http.post(baseUrlTrkaci, $scope.novaTrkac)//Trka
            .then(function success(data){
            	getTrkaci();
            	
//                $scope.novaTrkac.naziv = "";
//                $scope.novaTrkac.datumOdrzavanja = "";
//                $scope.novaTrkac.mestoOdrzavanja = "";
//                $scope.novaTrkac.trkaId = "";
                
                $scope.novaTrkac.ime = "";
                $scope.novaTrkac.prezime = "";
                $scope.novaTrkac.pol = "";
                $scope.novaTrkac.velicinaMajce = "";
                $scope.novaTrkac.adresa = "";
                $scope.novaTrkac.grad = "";
                $scope.novaTrkac.drzava = "";
                $scope.novaTrkac.najBoljeVreme = "";
                $scope.novaTrkac.klub = "";
                
                //$scope.novaTrkac.takmicenjaNazivTrke = "";
                
                
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
    	                          
        var baseUrlKnjige = "/api/trkaci";
        var baseUrlTrkaci = "/api/trkaci";

        $scope.staraTrkac = null;

        var getStaraTrkac = function(){

            $http.get(baseUrlKnjige + "/" + $routeParams.id)
                .then(
                	function success(data){
                		$scope.staraTrkac = data.data;
                	},
                	function error(data){
                		alert("Neušpesno dobavljanje knjige.");
                	}
                );

        }
        getStaraTrkac();
        
        $scope.izmeni = function(){
            $http.put(baseUrlKnjige + "/" + $scope.staraTrkac.id, $scope.staraTrkac)
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
    
        var getPol = function(){ //Trke
        	//alert(" PORUKA ");
            $http.get(baseUrlTrkaci) //Trke
                .then(function success(data){
                    $scope.pol = data.data; //trke
                    //alert("Ušpesno ucitavanje vrednosti Trke ");
                    //alert(  $scope.pol[0].pol);
                    //alert(  Object.keys($scope.trke[0]));
                   

                },
            	function error(data){
            		alert("Neušpesno ucitavanje vrednosti pol ");
            	});

        };
        
        getPol();
});
