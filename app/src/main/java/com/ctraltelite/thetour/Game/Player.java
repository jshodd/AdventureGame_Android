package com.ctraltelite.thetour.Game;

import com.ctraltelite.thetour.Inventory.InventoryFragment;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private Room currentRoom;
	private List<Item> inventory;
	
	public Player(Room r) {
		inventory = new ArrayList<Item>();
		currentRoom = r;
	}
	
	public Room getCurrentRoom() { return currentRoom; }
	
	public void goTo(Room r) { currentRoom = r; }

	public List<Item> getInventory(){
		return inventory;
	}
	
	public Item getItem(String description) {
		for (Item item:inventory){
			if (item.getDescription().equalsIgnoreCase(description)){
				return item;
			}
		}
		return null;
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public String getInventoryString() {
		String out = null;
		if (!inventory.isEmpty()) {
			for (Item item : inventory) {
				out += ", " + item.getDescription();
			}
			return out;
		}
		else
			return "nothing";
	}
	public void removeItem(Item item){
		inventory.remove(item);
	}

}
