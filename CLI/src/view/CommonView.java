package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Controller;

public abstract class CommonView implements View {

	Controller controller;
	CLI cli;
	HashMap<String,Command> hashCommand;
	BufferedReader in;
	PrintWriter out;

	public void setController(Controller controller){
		this.controller = controller;
	}
	
	
	
	@Override
	public void exit() {
		cli.setRunning(false);
		
	}
	
	@Override
	public abstract void start();

	@Override
	public abstract void displayMassage(String message);
	
	@Override
	public void setCommands(HashMap<String,Command> hashCommand){
		this.hashCommand = hashCommand;
	}
}
