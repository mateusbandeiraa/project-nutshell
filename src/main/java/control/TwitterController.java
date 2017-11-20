package control;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterController {
	
	public static List<String> getTweetsFrom(String user) throws Exception {
		
		List<String> tweets = new ArrayList<>();
		OAuth2Token token;
		token = getOAuth2Token();

		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setApplicationOnlyAuthEnabled(true);
		cb.setOAuth2TokenType(token.getTokenType());
		cb.setOAuth2AccessToken(token.getAccessToken());

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		
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
	
	private static OAuth2Token getOAuth2Token()
	{
		OAuth2Token token = null;
		ConfigurationBuilder cb;

		cb = new ConfigurationBuilder();
		cb.setApplicationOnlyAuthEnabled(true);

		try
		{
			token = new TwitterFactory(cb.build())
				.getInstance().getOAuth2Token();
		}
		catch (Exception e)
		{
			System.out.println("Can't get OAuth2 token");
			e.printStackTrace();
			System.exit(0);
		}

		return token;
	}
}
