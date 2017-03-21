var app=angular.module('myApp',['ngRoute','ngCookies']);


app.config(function($routeProvider){
	
	$routeProvider
		.when('/',{
			templateUrl:'user/login.html',
	    controller : 'UserController'
	})
	
	.when('/aboutUS',{
		templateUrl:'about/aboutUs.html'
	})
	
	.when('/contactUS',{
		templateUrl:'about/contactUs.html'
	})
	
	.when('/register',{	   
			templateUrl:'user/register.html',
	    controller : 'UserController'
	})
	
	.when('/myProfile', {
		templateUrl : 'user/my_profile.html',
		controller : 'UserController'
	})
	
	.when('/seeUsers', {
		templateUrl : 'user/users.html',
		controller : 'UserController'
	})
		
   /*#######################################Friends Control###################################*/
	
	.when('/friends', {
		templateUrl : 'friend/Friends.html',
		controller : 'FriendController'
	})
	
	.when('/allUsers', {
		templateUrl : 'friend/AllUsers.html',
		controller : 'FriendController'
	})
	
	.when('/friendsRequest', {
		templateUrl : 'friend/FriendsRequest.html',
		controller : 'FriendController'
	})
	
	
	/*###################################CHAT###################################################### */
	.when('/chat', {
		templateUrl : 'chat/SendChat.html',
		controller : 'ChatController'
	})
	
	/*#####################################File Upload##################################################*/
	
	.when('/uploadPic', {
		templateUrl : 'upload/picupload.html',
		
	})
	/*###########################################JOB##################################################*/
	
	.when('/getAllJobs', {
		templateUrl : 'job/view_job_details.html',
		controller : 'JobController'
		})
		
	.when('/postAJob', {
		templateUrl : 'job/post_job.html',
		controller : 'JobController'
	})
	
	/*.when('/chat', {
		templateUrl : 'job/search_job.html',
		controller : 'JobController'
	})*/
	
	.when('/getMyAppliedJobs', {
		templateUrl : 'job/view_applied_jobs.html',
		controller : 'JobController'
	})
	/*#######################################BLOG#############################################################3*/
	
	.when('/fetchAllBlogs', {
		templateUrl : 'blog/ViewBlogs.html',
		controller : 'BlogController'
	})
	
	.when('/blogsApp', {
		templateUrl : 'blog/ApproveBlogs.html',
		controller : 'BlogController'
	})
	
	.when('/createNewBlog', {
		templateUrl : 'blog/CreateBlog.html',
		controller : 'BlogController'
	})
	
	.when('/myblogs', {
		templateUrl : 'blog/MyCreatedBlogs.html',
		controller : 'BlogController'
	})
});


