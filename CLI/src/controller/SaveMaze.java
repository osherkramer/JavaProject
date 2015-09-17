package controller;

/**
 * SaveMaze class - extends the CommonComand
 * manage the save of maze3d, ask from the model to save the maze
 */

public class SaveMaze extends CommonCommand {

	/**
	 * SaveMaze constructor
	 * @param controller - get object of type Controller to work with him
	 */
	public SaveMaze(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		controller.getModel().saveMaze(str);
	}

}
