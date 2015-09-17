package controller;

/**
 * Generate3dMaze class - extends the CommonComand
 * manage the create of maze3d, ask from the model to create the maze
 */

public class Generate3dMaze extends CommonCommand {

	/**
	 * Generate3dMaze constructor 
	 * @param controller - get object of type Controller
	 */
	public Generate3dMaze(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		String[] parm = str.split(" ");
		
		if(parm.length != 6)
			controller.setMessage("Invalid Command");
		else{
			int x = 0,y = 0,z = 0;
			try{
				x = Integer.parseInt(parm[3]);
				y = Integer.parseInt(parm[4]);
				z = Integer.parseInt(parm[5]);
			}
			catch (NumberFormatException e){
				controller.setMessage("Invalid Command");
			}
			
			controller.getModel().generate(parm[2], x, y, z);
			
		}
	}

}
