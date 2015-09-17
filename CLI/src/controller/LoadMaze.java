package controller;

/**
 * LoadMaze class - extends the CommonComand
 * manage the load of maze3d from file, ask from the model to load the maze
 */

public class LoadMaze extends CommonCommand{
	/**
	 * LoadMaze constructor
	 * @param controller - get the controller to work with him.
	 */
	public LoadMaze(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		controller.getModel().loadMaze(str);
	}
}
