package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import entity.TweetArrayList;
import entity.TweetTreeMap;

public class Test {
	public static void main(String[] args) {
		final String NOT_WORDS_REGEX = "[^a-zA-Z \\u00c0-\\u00d6\\u00d8-\\u00f6\\u00f8-\\u02af\\u1d00-\\u1d25\\u1d62-\\u1d65\\u1d6b-\\u1d77\\u1d79-\\u1d9a\\u1e00-\\u1eff\\u2090-\\u2094\\u2184-\\u2184\\u2488-\\u2490\\u271d-\\u271d\\u2c60-\\u2c7c\\u2c7e-\\u2c7f\\ua722-\\ua76f\\ua771-\\ua787\\ua78b-\\ua78c\\ua7fb-\\ua7ff\\ufb00-\\ufb06]";

		// TWEET RETRIEVER
		List<String> tweets;
		try {
			tweets = TwitterController.getTweetsFrom("mateusbandeiraa");
		} catch (Exception e) {
			tweets = new ArrayList<>();
			e.printStackTrace();
		}

		// TWEET SORTER BY WORDS
		Map<String, List<String>> wordsTable = new TweetTreeMap();

		for (String tweet : tweets) {
			String trimmed = tweet.toLowerCase();
			trimmed = trimmed.replaceAll(NOT_WORDS_REGEX, ""); // REMOVES EVERYTHING BUT LETTERS AND SPACES
			trimmed = trimmed.replaceAll("[^ \\S]+|[\\s]{2,}", " "); // REMOVES CARRIAGE RETURN & DOUBLE SPACES

			String[] words = trimmed.split(" ");
			for (String word : words) {
				if (wordsTable.containsKey(word)) {
					wordsTable.get(word).add(tweet);
				} else {
					List<String> tweetsWithWord = new ArrayList<>();
					tweetsWithWord.add(tweet);
					wordsTable.put(word, tweetsWithWord);
				}
			}
		}

		// System.out.println("#1 TweetTreeMap:\n" + wordsTable);

		// TweetArrayList SORTER BY # OF TWEETS
		List<Entry<String, List<String>>> sortedWordsTable = new TweetArrayList(wordsTable.entrySet());

		Collections.sort(sortedWordsTable, new Comparator<Entry<String, List<String>>>() {
			@Override
			public int compare(Entry<String, List<String>> o1, Entry<String, List<String>> o2) {
				return o2.getValue().size() - o1.getValue().size();
			}
		});

		System.out.println("#2 TweetArrayList:\n" + sortedWordsTable);

	}
}