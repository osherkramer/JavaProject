package view;

import java.io.IOException;
import controller.Controller;

public abstract class CommonView implements View {

	Controller controller;
	CLI cli;
	
	public CommonView(Controller control, CLI cli){
		this.controller = control;
		this.cli = cli;
	}
	
	@Override
	public void exit() {
		cli.setRunning(false);
		
	}
	
	@Override
	public abstract void start()  throws IOException;

	@Override
	public abstract void displayMassage(String message);

}
