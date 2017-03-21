'use strict';

app.controller(	'UserController', [	'$scope', 'UserService', '$location','$rootScope','$cookieStore',
						'$http',
						function($scope, UserService, $location, $rootScope,
								$cookieStore,$http) {
							console.log("UserController...")
							var self = this;
							self.user = {userId : '',name : '', password : '',	mobile : '',status:'',reason:'',
									address : '', mailID : '',isOnline : '',	role : '',
								errorCode : '',	errorMessage : '',image:''
							};
							self.users = [];
							
							
							self.fetchAllUsers = function() {
								console.log("fetchAllUsers...")
								UserService
										.fetchAllUsers()
										.then(
												function(d) {
													self.users = d;
													
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};
												
							
							self.createUser = function(user) {
								console.log("createUser...")
								UserService
										.createUser(user)
										
										.then(
												self.fetchAllUsers,
												function(errResponse) {
													console
															.error('Error while creating User.');
												});
							};
							
							self.myProfile = function() {
								console.log("myProfile...")
								UserService
										.myProfile()
										.then(
												function(d) {
													self.user = d;
													$location.path("/myProfile")
												},
												function(errResponse) {
													console
															.error('Error while fetch profile.');
												});
							};
							
							self.accept = function(userId) {
								console.log("accept..." +userId)
								
								UserService
										.accept(userId)
										.then(
												function(d) {
													self.user = d;
													self.fetchAllUsers
													$location.path("/")
													alert(self.user.errorMessage)
													
												},
												
												function(errResponse) {
													console
															.error('Error while updating User.');
												});
							};
							
							self.makeAdmin= function(userId) {
								console.log("makeAdmin..." +userId)
								
								UserService
										.makeAdmin(userId)
										.then(
												function(d) {
													self.user = d;
													self.fetchAllUsers
													
													alert(self.user.errorMessage)
													
												},
												
												function(errResponse) {
													console
															.error('Error while updating User.');
												});
							};
							
							
							self.reject = function(userId) {
								console.log("reject..."+userId)
								var reason = prompt("Please enter the reason");
								UserService
										.reject(userId,reason)
										.then(
												function(d) {
													self.user = d;
													self.fetchAllUsers
													$location.path("/")
													alert(self.user.errorMessage)
													
												},
												null );
							};

							self.updateUser = function(user) {
								console.log("updateUser...")
								
								UserService
										.updateUser(user, userId)
										.then(
												
												self.fetchAllUsers,
												
												null);
							};
							
							/*self.updateUser = function(user) {
								console.log("updateUser...")
								UserService
										.updateUser(user, userId)
										.then(
												self.fetchAllUsers,
												null);
							};*/

							self.authenticate = function(user) {
								console.log("authenticate...")
								UserService
										.authenticate(user)
										
										.then(

												function(d) {

													self.user = d;
													console
															.log("user.errorCode: "
																	+ self.user.errorCode)
													if (self.user.errorCode == "404")

													{
														alert(self.user.errorMessage)

														self.user.userId = "";
														self.user.password = "";

													} else { 
														if(self.user.status=="R"){
															alert(self.user.reason)
															
															$location.path('/');
														}
														else{
														//valid credentials
														console
																.log("Valid credentials. Navigating to home page")
																alert(self.user.errorMessage)
														$rootScope.currentUser = self.user
														
														/*$http.defaults.headers.common['Authorization'] = 'Basic '
																+ $rootScope.currentUser;*/
														$cookieStore
																.put(
																		'currentUser',
																		$rootScope.currentUser);
														$location.path('/');

													}
													}

												},
												function(errResponse) {

													console
															.error('Error while authenticate Users');
												});
							};

							self.logout = function() {
								console.log("logout")
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								UserService.logout()
								$location.path('/');

							}

						
							self.fetchAllUsers();

							self.login = function() {
								{
									/*$scope.dataLoading = true;*/
									console.log('login validation????????',
											self.user);
									self.authenticate(self.user);
								}

							};

							self.submit = function() {
								{
									console.log('Saving New User', self.user);
									self.createUser(self.user);
								}
								self.reset();
							};

							self.reset = function() {
								self.user = {
									userId : '',
									name : '',
									password : '',
									mobile : '',
									address : '',
									email : '',
									isOnline : '',
									errorCode : '',
									errorMessage : ''
								};
								$scope.myForm.$setPristine(); // reset Form
							};

						} ]);