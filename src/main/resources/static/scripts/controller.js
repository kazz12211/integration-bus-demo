app.controller("controller", ($scope, postService) => {
	$scope.events = [
		{
			type: "*", 
			subtype:"*", 
			object: '{"key": "value"}'
		},
		{
			type: "DOCUMENT", 
			subtype:"PROCESSED", 
			object: '{"docId": 1}'
		},
		{
			type: "ACTION",
			subtype: "PROCESSED",
			object: '{"docId": 1, "actionId": 2}'
		}
	];
	
	$scope.event = $scope.events[0];
	
	$scope.postEvent = () => {
		postService.postEvent($scope.event);
	};
	
	$scope.processDocument = () => {
		postService.postEvent($scope.events[1]);
	};
	
} );