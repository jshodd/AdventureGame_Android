package com.ctraltelite.thetour.Game;


import android.util.Log;

import com.ctraltelite.thetour.R;

import java.io.StringWriter;

public class TheTour {
	private Player player;
    private StringWriter os;

	public TheTour(){
		player = new Player(makeRooms());
        os = new StringWriter();
	}

	public String getOS(){
        return os.toString();

    }


	private Room makeRooms() {

		Room pLH = new Room("The Lecture Hall", "As you are looking around the lecture hall, you start to notice the sudden quietness in the room. You turn around to see that both your tour guide and your beloved parents have left you in this desolate place. On the floor you see a piece of paper");
		pLH.addItem(new Item("PSY 101 Research Paper"));
		pLH.setImage(R.drawable.lecture_hall);
		Room greenRoom = new Room("The Green Room", "As you leave the lecture hall you enter a room with no windows, lacking any color aside from the horribly green carpet. you look around hoping to see where your companions have gone. You look north and see a long erie hallway, and east to see a staircase.");
		greenRoom.setImage(R.drawable.green_room);
        pLH.setNorthExit(greenRoom);
		greenRoom.setSouthExit(pLH);
		Room hallWay = new Room("HallWay","As you walk down the hallway you hear a terrifying mix of sobbing and what sounds like the screams of a small animal. The source of this terrifying sound is to the door to the west, while a staircase is on the door to your north and the green room is to the south.");
		hallWay.setImage(R.drawable.hallway);
        greenRoom.setNorthExit(hallWay);
		hallWay.setSouthExit(greenRoom);
		Room stairOne = new Room("Southern Stairway","You walk up two flights of stairs and find yourself standing in front of a glass wall. Behind this wall there are countless people hung over computers, their faces devoid of life. What kind of experiments are they performing on these poor souls? you notice a door to this bizarre test chamber to the east, and a door to your north");
		stairOne.setImage(R.drawable.lobby);
        greenRoom.setEastExit(stairOne);
		stairOne.setWestExit(greenRoom);
		Room theLab = new Room("Neuroscience Lab","You cautiously enter the room regardless of the terrifying sounds. You look around expecting some form of torture taking place, but as you look around you see multiple rats in holding containers and a student bent over a desk crying. You approach the student to ask what is wrong, and she tells you that she just realized that all of her capstone data is wrong and she has to restart her experiments. As bad as you feel, this doesn't concern you. The exit is to the east");
		theLab.setImage(R.drawable.rats);
        hallWay.setWestExit(theLab);
		theLab.setEastExit(hallWay);
		Room csLab = new Room("Computer Science Lab","You burst through the door rushing in to save these people, by god it looks like they haven't slept in weeks! The sound of keyboards clicking fills the room. Walls of text fill the screens in front of them with relentless speed. You pull the nearest person away from their computer to save them from this madness only to be pushed away by the student. To your surprise they are totally aware! You ask why they are voluntarily submitting themselves to this and he tells you that they have a computer science project due tomorrow and he can't figure out why his application keeps crashing. He is angered by your disturbing him and returns to his work. You look around confused and see a thumbdrive. The exit is to the west");
		csLab.addItem(new Item("Tommy's Thumbdrive"));
		csLab.setImage(R.drawable.cslab);
        stairOne.setEastExit(csLab);
		csLab.setWestExit(stairOne);
		Room mthLab = new Room("Math Room","You enter the room to see flickering lights and writing on the walls all around you. What kind of mad hieroglyphics are these? What would cause someone to become so distant from reality to create these? You can only assume that a Discrete Math class has been taught here recently as the feeling of despair still lingers. There are exits to the west and to the south");
		mthLab.setImage(R.drawable.math_room);
        hallWay.setNorthExit(mthLab);
		stairOne.setNorthExit(mthLab);
		stairOne.setSouthExit(greenRoom);
		mthLab.setWestExit(hallWay);
		mthLab.setSouthExit(stairOne);
		
		return pLH;
	}

	public void dropItem(Item item){
		getCurrentRoom().addItem(item);
		player.removeItem(item);
	}
	
	public String performCommand(Command command) {
        Log.w("blah",command.getFirstWord());
		switch(command.getFirstWord().toLowerCase()) {
		case "go":
			go(command.getSecondWord());
			break;
		case "inventory":
		case "inv":
			inventory();
			break;
		case "help":
			help();
			break;
		case "quit":
			quit();
			break;
		case "take":
			take(command.getSecondWord());
			break;
		default:
            os.getBuffer().setLength(0);
			os.write("\n\nI don't know how to do that.\n\n");
		}
		return null;
	}
	
	public void inventory() {
        os.getBuffer().setLength(0);
		os.write("\n\nYou have: "+player.getInventoryString()+"\n\n");
	}


	public void take(String secondWord) {
		if (!getCurrentRoom().hasItem()) {
            Log.w("blah","Take");
            os.getBuffer().setLength(0);
			os.write("\n\nThere isnt anything here.\n\n");

		}
		if (!getCurrentRoom().hasThisItem(secondWord)) {
            os.getBuffer().setLength(0);
			os.write("\n\nThere is no "+secondWord+" here.\n\n");
		}else {

            player.addItem(getCurrentRoom().getItem(secondWord));
            getCurrentRoom().removeItem(secondWord);
            os.getBuffer().setLength(0);
            os.write("\nYou picked up a "+secondWord+"!");
        }
	}


	public Room getCurrentRoom() {
		return player.getCurrentRoom();
	}


	private void quit() {
		System.out.println("\n\nThanks for playing!\n\n");
	}
	
	private void help() {
        os.getBuffer().setLength(0);
        os.write("\n\nAvailable commands:\n" +
                "\tgo <direction> -- move in the specified direction.  Example: \"go north\"\n" +
                "\thelp -- print this help text\n" +
                "quit -- quit the game\n" +
                "\ttake <item> -- pick up the specified item.  Example: \"take cookie\"\n\n");
	}


	private void go(String direction) {
		Room exit = getCurrentRoom().getExit(direction);
		if (exit == null) {
            os.getBuffer().setLength(0);
            os.write("I don't know how to go that direction.");
		} else {
			player.goTo(exit);
            os.getBuffer().setLength(0);
            os.write(player.getCurrentRoom().getDescription());
		}
	}

	public Player getPlayer(){
		return player;
	}

}
