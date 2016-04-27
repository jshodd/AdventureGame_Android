package com.ctraltelite.thetour.Game;


import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * I read a single two-word command from the user.
 * I use System.in as my source.
 * 
 * @author C. David Shaffer
 * @version 1.0
 * @see Command

 */
public class CommandReader {
	
	// use of fully-qualified class name
	private java.util.Scanner keyboard;
	
	/**
	 * Create a CommandReader that reads from System.in.
	 */
	public CommandReader() {
		keyboard = new java.util.Scanner(System.in);
	}
	
	/**
	 * Prompt and read a one- or two-word command.
	 * 
	 * @return the resulting Command object
	 * @see Command
	 */
	public Command readCommand(ByteArrayInputStream bias) throws IOException {;
		BufferedReader br=new BufferedReader(new InputStreamReader(bias));
		String line = br.readLine();
		Log.w("blah",line);
		java.util.StringTokenizer st = new java.util.StringTokenizer(line);
		String firstWord = st.nextToken();
		String secondWord = null;
		if (st.hasMoreTokens()) 
			secondWord = st.nextToken();
		return new Command(firstWord, secondWord);
	}

}
