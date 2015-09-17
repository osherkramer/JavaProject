package controller;

/**
 * FileSize class - extends the CommonComand
 * manage the calculate size of file that include maze3d,
 * ask from the model to calculate the file size
 */

public class FileSize extends CommonCommand {

	/**
	 * FileSize constructor
	 * @param controller - get the controller to work with him
	 */
	public FileSize(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		String[] parm = str.split(" ");
		if(parm.length != 2){
			controller.setMessage("Invalid Command");
			return;
		}
		
		controller.getModel().mazeSizeFile(parm[1]);

	}

}
