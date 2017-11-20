package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import entity.TweetArrayList;
import entity.TweetTreeMap;

public class LogicController {
	public final static String NOT_WORDS_REGEX = "[^a-zA-Z \\u00c0-\\u00d6\\u00d8-\\u00f6\\u00f8-\\u02af\\u1d00-\\u1d25\\u1d62-\\u1d65\\u1d6b-\\u1d77\\u1d79-\\u1d9a\\u1e00-\\u1eff\\u2090-\\u2094\\u2184-\\u2184\\u2488-\\u2490\\u271d-\\u271d\\u2c60-\\u2c7c\\u2c7e-\\u2c7f\\ua722-\\ua76f\\ua771-\\ua787\\ua78b-\\ua78c\\ua7fb-\\ua7ff\\ufb00-\\ufb06]";
	public final static String URLS_REGEX = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

	public static void main(String[] args) {

		// TWEET RETRIEVER
		List<String> tweets = retrieveTweets("mateusbandeiraa");

		// TWEET SORTER BY WORDS
		TweetTreeMap wordsMap = sortTweetsByWords(tweets);

		// TweetArrayList SORTER BY # OF TWEETS
		List<Entry<String, List<String>>> sortedWordsList = sortTweetMapByMostUsedWords(wordsMap);

		// System.out.println("#2 sortedWordsList:\n" + sortedWordsList);

		String[] assembly;
		try {
			assembly = tweetAssembly(sortedWordsList);
			System.out.println(assembly[0] + assembly[1]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String[] tweetInANutshell(String username) throws Exception {
		// TWEET RETRIEVER
		List<String> tweets = retrieveTweets(username);

		// TWEET SORTER BY WORDS
		TweetTreeMap wordsMap = sortTweetsByWords(tweets);

		// TweetArrayList SORTER BY # OF TWEETS
		List<Entry<String, List<String>>> sortedWordsList = sortTweetMapByMostUsedWords(wordsMap);

		// System.out.println("#2 sortedWordsList:\n" + sortedWordsList);

		String[] assembly = tweetAssembly(sortedWordsList);

		return assembly;
	}

	private static List<String> retrieveTweets(String user) {
		List<String> tweets = null;
		try {
			tweets = TwitterController.getTweetsFrom(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}

	private static TweetTreeMap sortTweetsByWords(List<String> tweets) {
		TweetTreeMap wordsMap = new TweetTreeMap();
		TweetTreeMap trimmedWordsMap = null;
		for (String tweet : tweets) {
			tweet = tweet.replaceAll(URLS_REGEX, "");
			String trimmed = tweet.toLowerCase();
			trimmed = trimmed.replaceAll(NOT_WORDS_REGEX, ""); // REMOVES EVERYTHING BUT LETTERS AND SPACES
			trimmed = trimmed.replaceAll("[^ \\S]+|[\\s]{2,}", " "); // REMOVES CARRIAGE RETURN & DOUBLE SPACES

			String[] words = trimmed.split(" ");
			for (String word : words) {
				if (word.length() < 3)
					continue;

				if (wordsMap.containsKey(word)) {
					wordsMap.get(word).add(tweet);
				} else {
					List<String> tweetsWithWord = new ArrayList<>();
					tweetsWithWord.add(tweet);
					wordsMap.put(word, tweetsWithWord);
				}
			}

			trimmedWordsMap = new TweetTreeMap();

			for (String word : wordsMap.keySet()) {
				List<String> tweetsWithWord = wordsMap.get(word);
				if (tweetsWithWord.size() > 1)
					trimmedWordsMap.put(word, tweetsWithWord);
			}
		}
		return trimmedWordsMap;
	}

	private static TweetArrayList sortTweetMapByMostUsedWords(Map<String, List<String>> wordsMap) {
		TweetArrayList sortedWordsList = new TweetArrayList(wordsMap.entrySet());

		Collections.sort(sortedWordsList, new Comparator<Entry<String, List<String>>>() {
			@Override
			public int compare(Entry<String, List<String>> o1, Entry<String, List<String>> o2) {
				return o2.getValue().size() - o1.getValue().size();
			}
		});
		return sortedWordsList;
	}

	private static String[] tweetAssembly(List<Entry<String, List<String>>> wordsList) throws Exception {

		// SELECTS THE FIRST WORD AND THE TWEETS THAT CONTAIN IT
		int randomIndex = 0;
		String commonWord = "";
		List<String> commonTweets = null;

		String firstTweet = "";
		int loopcounter = 0;
		while (firstTweet.indexOf(commonWord) < 7) {
			if(loopcounter > 10)
				throw new Exception();
			randomIndex = new Random().nextInt(wordsList.size());
			Entry<String, List<String>> firstSelectedEntry = wordsList.get(randomIndex);
			commonWord = firstSelectedEntry.getKey();
			commonTweets = firstSelectedEntry.getValue();
			// SELECTS THE FIRST TWEET RANDOMLY
			randomIndex = new Random().nextInt(commonTweets.size());
			firstTweet = commonTweets.get(randomIndex);
			loopcounter++;

		}
		
		commonTweets.remove(randomIndex);

		String firstHalf = firstTweet.substring(0, firstTweet.toLowerCase().indexOf(commonWord));

		// SELECTS THE SECOND TWEET

		randomIndex = new Random().nextInt(commonTweets.size());
		String secondTweet = commonTweets.get(randomIndex);
		// PREVENTS THE COMMON WORD TO BE THE LAST IN THE TWEET
		while (secondTweet.indexOf(commonWord) == (secondTweet.length() - commonWord.length())) {
			firstTweet = commonTweets.get(randomIndex);
		}

		String secondHalf = secondTweet.substring(secondTweet.toLowerCase().indexOf(commonWord), secondTweet.length());

		System.out.println("First tweet: " + firstTweet);
		System.out.println("Common word: " + commonWord);
		System.out.println("First Half: " + firstHalf);
		System.out.println("Second tweet: " + secondTweet);
		System.out.println("Second half: " + secondHalf);

		return new String[] { firstHalf, secondHalf };
	}
}