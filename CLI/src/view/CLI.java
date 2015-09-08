package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI {
	BufferedReader in;
	PrintWriter out;
	HashMap<String,Command> hash;
	boolean running;
	
	public CLI(BufferedReader in, PrintWriter out, HashMap<String,Command> hash){
		this.in = in;
		this.out = out;
		this.hash = hash;
		this.running = true;
	}
	
	public void setRunning(boolean running){ this.running = running; }
	
	public void start() throws IOException{
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String str = null;
				Command com = null;
				
				while(running){
					try{
						str = in.readLine();
						String[] command = str.split(" ", 2);
						com = hash.get(command[0]);
						if(com != null)
							com.doCommand(command[1]);
						else
							out.write("Error! Command not exist");
					}
					catch (IOException e){
						throw e;
					}
				}
			}
		});
		
		
	}
}
