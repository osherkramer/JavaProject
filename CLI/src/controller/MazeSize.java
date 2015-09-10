package controller;

public class MazeSize extends CommonCommand {

	public MazeSize(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		String[] parm = str.split(" ");
		if(parm.length != 2){
			controller.setMassage("Invalid Command");
			return;
		}
		
		controller.getModel().mazeSizeMemory(parm[1]);

	}

}
