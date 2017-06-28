app.controller('loanController', function($scope, $http) {
	$scope.headingTitle = "Request Loan";
	$scope.loanRequested = false;
	$scope.loanRequestStatusMessage = "";

	$scope.requestLoan = function() {
		console.log('requested user = ' + $scope.customer.name);
		
		var loanData = {
				customerName : $scope.customer.name,
				amount: $scope.customer.amount,
				age: $scope.customer.age,
				job: $scope.customer.job,
				children: $scope.customer.children
		};
		
		var result = $http.post('request-loan-be/', loanData).then(function(result) {
			$scope.loanRequested = true;
			$scope.loanRequestStatusMessage = result.data.message;
		});
	}
});

app.controller('rolesController', function($scope) {
	$scope.headingTitle = "Roles List";
});
