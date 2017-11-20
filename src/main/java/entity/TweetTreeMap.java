package entity;

import java.util.List;
import java.util.TreeMap;

public class TweetTreeMap extends TreeMap<String, List<String>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("This TreeMap contains " + this.size() + " words\n");
		for(String word : this.keySet()) {
			sb.append("   \"" + word + "\" has " + this.get(word).size() + " occurrences.\n");
		}
		return sb.toString();
	}
	
}
