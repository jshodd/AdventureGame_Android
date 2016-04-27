package com.ctraltelite.thetour.Game;

import android.util.Log;

import java.util.StringTokenizer;
import java.util.UUID;

/**
 * I am an object that can be in a room or 
 * held by a player.
 * 
 * @author C. David Shaffer
 * @version 1.0
 */
public class Item {
	
	private String description;
	private UUID mld;
	
	public Item(String description) {
		this.description = description;
		mld = UUID.randomUUID();
	}
	
	public String getDescription() {
		return description;
	}

	public boolean isDescribedBy(String word) {
		word = word.toLowerCase();
		// If word is a simple article then it isn't a proper description
		// of this item
		//if (word.equals("a") || word.equals("an") || word.equals("the"))
			//return false;
		
		// If my description contains word, then word describes me.
		StringTokenizer st = new StringTokenizer(description.toLowerCase());
		while(st.hasMoreTokens()) {
			Log.w("blah", "checking description");
			if (st.nextToken().equals(word)) {
				Log.w("blah", "returning True");
				return true;
			}
		}
		return false;
	}

}
