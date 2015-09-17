package controller;

/**
 * MazeSize class - extends the CommonComand
 * manage the get size of maze3d, ask from the model to calculate the size of maze
 */

public class MazeSize extends CommonCommand {

	/**
	 * MazeSize constructor
	 * @param controller - get the Controller to work with him
	 */
	public MazeSize(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		String[] parm = str.split(" ");
		if(parm.length != 2){
			controller.setMessage("Invalid Command");
			return;
		}
		
		controller.getModel().mazeSizeMemory(parm[1]);

	}

}
