package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class TweetArrayList extends ArrayList<Entry<String, List<String>>> {

	private static final long serialVersionUID = 1L;

	public TweetArrayList(Set<Entry<String, List<String>>> entrySet) {
		super(entrySet);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("This ArrayList contains " + this.size() + " words\n");
		for (Entry<String, List<String>> e : this) {
			sb.append("   \"" + e.getKey() + "\" has " + e.getValue().size() + " occurences.\n");
		}
		return sb.toString();
	}

}
