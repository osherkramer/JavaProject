package controller;
/**
 * CommonCommand class - implements the Command interface
 * abstract class
 */

public abstract class CommonCommand implements Command {
	
	Controller controller;
	
	/**
	 * CommonCommand constructor
	 * @param controller - get the controller that it will be work with him
	 */
	public CommonCommand(Controller controller){
		this.controller = controller;
	}

	@Override
	public abstract void doCommand(String str);
}
