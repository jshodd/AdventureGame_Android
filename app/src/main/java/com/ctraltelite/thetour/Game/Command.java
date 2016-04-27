package com.ctraltelite.thetour.Game;


public class Command {
	
	private String firstWord;
	private String secondWord;
	
	public Command(String firstWord, String secondWord) {
		this.firstWord = firstWord;
		this.secondWord = secondWord;
	}
	
	public Command(String firstWord) {
		this(firstWord, null);
	}
	
	public String getFirstWord() {
		return firstWord;
	}
	
	public String getSecondWord() {
		return secondWord;
	}
	
	public boolean hasSecondWord() {
		return secondWord != null;
	}

}
