package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

/**
 * CLI Class - manage the Command Line Interface for the client
 */

public class CLI {
	BufferedReader in;
	PrintWriter out;
	HashMap<String,Command> hash;
	boolean running;
	
	/**
	 * CLI Constructor - initialize the CLI object
	 * @param in - get BufferedReader
	 * @param out - get PrintWriter
	 * @param hash - get HashMap that mapped between String to Command
	 */
	public CLI(BufferedReader in, PrintWriter out, HashMap<String,Command> hash){
		this.in = in;
		this.out = out;
		this.hash = hash;
		this.running = true;
	}
	
	/**
	 * Stop the running of the main thread that run the CLI
	 * @param running - get false for stop the CLI
	 */
	public void setRunning(boolean running){ this.running = running; }
	
	/**
	 * Start the thread that manage the cli and running it
	 */
	public void start(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String str = null;
				Command com = null;
				
				while(running){
						try {
							str = in.readLine();
						} catch (IOException e) {
							out.println(e.getMessage());
							out.flush();
						}
						String[] command = str.split(" ", 2);
						com = hash.get(command[0]);
						if(com != null)
							if(command.length == 1)
								com.doCommand("");
							else	
								com.doCommand(command[1]);
						else{
							out.println("Error! Command not exist");
							out.flush();
						}

				}
			}
		}).start();
	}
	
	public void setMessage(String str){
		out.println(str);
		out.flush();
	}
}
