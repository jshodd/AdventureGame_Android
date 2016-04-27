package com.ctraltelite.thetour.Game;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.ctraltelite.thetour.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

	private String title;
	private String description;
	private Map<String,Room> exits;
	private List<Item> items;
	private int image;
	
	public Room(String title, String description) {
		this.title = title;
		this.description = description;
		exits = new HashMap<>();
		items = new ArrayList<Item>();
	}
	
	public Room getExit(String exitName) {
		return exits.get(exitName.toLowerCase());
	}
	
	public void setExit(String direction, Room exitRoom) {
		exits.put(direction.toLowerCase(), exitRoom);
	}

	public Room getNorthExit() {
		return getExit("north");
	}

	public void setNorthExit(Room northExit) {
		setExit("north", northExit);
	}

	public Room getEastExit() {
		return getExit("east");
	}

	public void setEastExit(Room eastExit) {
		setExit("east", eastExit);
	}

	public Room getSouthExit() {
		return getExit("south");
	}

	public void setSouthExit(Room southExit) {
		setExit("south", southExit);
	}

	public Room getWestExit() {
		return getExit("west");
	}

	public void setWestExit(Room westExit) {
		setExit("west", westExit);
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public Item getItem(String description){
		for (Item item:items){
			if (item.isDescribedBy(description.toLowerCase()))
				return item;
		}
		return null;
	}

	public boolean hasThisItem(String description){
		for (Item item:items){
			if (item.isDescribedBy(description.toLowerCase())){
				Log.w("room", "returning true");
				return true;}
		}
		return false;
	}

	private String getExitsString() {
		String result = "";
		for(String exitName: exits.keySet())
			result += exitName + " ";
		return result.trim();
	}
	
	private String getItemsString() {
		String out = null;
		for (Item item: items){
			out+= ", "+item.getDescription();
		}
		return out;
	}

	public String getLongDescription() {
		String result = title+
				"\n\n"+
				description+
				"\n\nItems: "+getItemsString()+
				"\n\nExits: "+
				getExitsString();
		return result;
	}

	public boolean hasItem() {
		return items != null;
	}

	public void removeItem(String description) {
		Log.w("room","removing item");
		for (int i=0;i<items.size();i++)	{
			if (items.get(i).isDescribedBy(description)){
				items.remove(i);

			}
		}
	}

    public void setImage (int image){
        this.image = image;
    }

    public int getImage(){
        Log.w("room","setting image");
        return image;
    }
}
