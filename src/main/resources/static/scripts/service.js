app.service("postService", PostService);

function PostService($http) {
	
	this.postEvent = (event) => {
		return $http(
				{
					method: 'POST',
					url: 'http://localhost:8080/events',
					headers: {
						'Content-Type' : 'application/json',
						'Accept' : 'application/json'
					},
					data: event
				});
	};
}