var app = angular.module('app',[]);

app.controller('CreditCardAccountController', ['$scope','CreditCardAccountService', function ($scope,CreditCardAccountService) {

    $scope.addCreditCard = function () {
        if ($scope.creditCard != null && $scope.creditCard.name) {
            CreditCardAccountService.addCreditCard($scope.creditCard.name, $scope.creditCard.cardNumber, $scope.creditCard.accountBalance)
              .then (function success(response){
                  $scope.message = 'Credit card added!';
                  CreditCardAccountService.getAllCreditCards()
                        .then(function success(response){
                            //console.log(response);
                            $scope.creditCardAccounts = response.data.cardAccounts;

                        },
                        function error (response ){
                            $scope.message='Error getting all cards!';
                        });
              },
              function error(response){
                  $scope.message = 'Error adding credit card!';
            });
        }
    }

}]);

app.service('CreditCardAccountService',['$http', function ($http) {

    this.addCreditCard = function addSubmit(name, cardNumber, accountBalance){
        return $http({
          method: 'POST',
          url: 'api/cards',
          data: {name:name, cardNumber:cardNumber, accountBalance:accountBalance}
        });
    }

    this.getAllCreditCards = function getAllCreditCardAccounts(){
            return $http({
              method: 'GET',
              url: 'api/cards'
            });
        }

}]);