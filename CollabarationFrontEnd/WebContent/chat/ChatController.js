app.controller("ChatController", function($scope,$rootScope, ChatService) {
	
	console.log('in chat');
	/*self.users=[];*/
	$scope.messages = [];
	$scope.message = "";
	$scope.max = 140;

	$scope.addMessage = function() {
		
		ChatService.send($scope.message);
	 	$scope.message = "";
	 	console.log("addMessage")
	};

	ChatService.receive().then(null, null, function(message) {
		console.log("received " + message);

		$scope.messages.push(message);
	});
	
	/*self.fetchAllUsers = function() {
		console.log("fetchAllUsers...")
		UserService
				.fetchAllUsers()
				.then(
						function(d) {
							self.users = d;
							console.log("Users Length..." +self.users.length)
						},
						function(errResponse) {
							console
									.error('Error while fetching Users');
						});
	};
	self.fetchAllUsers();*/
});