package view;

import java.util.HashMap;
import controller.Command;
import controller.Controller;

/**
 * View Interface
 * set the functional of the view side
 */

public interface View {
	/**
	 * start the view side
	 */
	void start();
	
	/**
	 * display the message that get from 
	 * @param message - get string to view for the client
	 */
	void displayMessage(String message);
	
	/**
	 * exit from the view - close threads and the view
	 */
	void exit();
	
	/**
	 * set the HashMap that mapped between strings and commands
	 * @param hashCommand
	 */
	void setCommands(HashMap<String,Command> hashCommand);
	
	/**
	 * set the controller that work with the view
	 * @param controller - get object of controller
	 */
	void setController(Controller controller);
}
