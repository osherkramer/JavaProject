package controller;

/**
 * Exit class - extends the CommonComand
 * manage the exit from the controller, model and the view.
 */

public class Exit extends CommonCommand {

	/**
	 * Exit constructor
	 * @param controller - set the controller to work with him
	 */
	public Exit(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		controller.getModel().exit(); //////////////
		controller.getView().exit();
	}

}
