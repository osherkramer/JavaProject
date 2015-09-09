package controller;

public class LoadMaze extends CommonCommand{
	public LoadMaze(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		controller.getModel().loadMaze(str);
	}
}
