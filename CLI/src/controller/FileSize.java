package controller;

public class FileSize extends CommonCommand {

	public FileSize(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		String[] parm = str.split(" ");
		if(parm.length != 2){
			controller.setMassage("Invalid Command");
			return;
		}
		
		controller.getModel().mazeSizeFile(parm[1]);

	}

}
