package controller;

public class SaveMaze extends CommonCommand {

	public SaveMaze(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		controller.getModel().saveMaze(str);
	}

}
