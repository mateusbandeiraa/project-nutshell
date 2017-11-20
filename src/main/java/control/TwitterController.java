package control;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class TwitterController {
	
	public static List<String> getTweetsFrom(String user) throws Exception {
		
		List<String> tweets = new ArrayList<>();
		Twitter twitter = TwitterFactory.getSingleton();
		
		Query query = new Query("from:" + user + " -filter:retweets AND -filter:replies AND -filter:replies");
		query.setResultType(Query.RECENT);
		query.count(100);
		
		QueryResult result = twitter.search(query);
		
		for (Status status : result.getTweets()) {
			tweets.add(status.getText());
		}
		System.out.println(tweets.size() + " tweets retrieved.");
		return tweets;

	}
}
