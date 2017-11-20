$(document).ready(function(){
	var mainCardHeight = $("#mainCard").css("height");
	var mainCardWidth = $("#mainCard").css("width");
	var secondaryCardHeight = $("#secondaryCard").css("height");
	var secondaryCardWidth = $("#secondaryCard").css("width");
	
	if(mainCardWidth > secondaryCardWidth){
		$("#secondaryCard").css("width", mainCardWidth);
	}else{
		$("#mainCard").css("width", secondaryCardWidth);
	}
	
//	if(mainCardHeight > secondaryCardHeight){
//		$("#secondaryCard").css("height", mainCardHeight);
//	}else{
//		$("#mainCard").css("height", secondaryCardHeight);
//	}
	
	
	$("#mainForm").submit(function(){
		$("#loadingModal").modal("show");
		var twUsername = $("#twUsername").val();
		
		$.post("AjaxServlet", $.param({username:twUsername}), function(response) {
		    console.log("Success!");
		    console.log("Servlet response: " + response);
		    
		   if(response[0] !== "/error/"){
			var tweet = "<a class=\"firstTweet\">" + response[0] + "</a><a class=\"secondTweet\">" + response[1] + "</a>";
			var urlSafeTweet = "@"+twUsername+"'s twitter in a nutshell is:\n\"" + tweet + "\"\n\nSee yours at";
		    $("#tweetText").html(tweet);
		    $("#quoteUsername").html("@" + twUsername);
		    $(".twitter-share-button").attr("data-text", urlSafeTweet);
		    $.getScript("https://platform.twitter.com/widgets.js");
		   } else{
			   $("#tweetText").html("There was an error: " + response[1]);
		   }
		    $("#loadingModal").modal("hide");
		    $("#mainCarousel").carousel(1);
		
		});
	});
	$("#backButton").click(function(){
		$("#mainCarousel").carousel(0);
	});
});