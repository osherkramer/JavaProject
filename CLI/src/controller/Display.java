package controller;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * Display class - extends the CommonComand
 * manage the display commands, ask from the model to get the object to display.
 */

public class Display extends CommonCommand {

	/**
	 * Display constructor
	 * @param controller - set the controller to work with him
	 */
	public Display(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		String[] parm = str.split(" ");
		
		if(parm[0].equalsIgnoreCase("cross"))
			cross(str);
		else if(parm[0].equalsIgnoreCase("solution"))
			solution(parm[1]);
		else if(parm.length == 1)
			defaultDisplay(parm[0]);
		else
			controller.setMessage("Invalid Command");
			

	}
	
	/**
	 * cross by section - ask the model for the cross
	 * @param arg - get the parameters of cross
	 */
	private void cross(String arg){
		String[] parm = arg.split(" ");
		if(parm.length != 7){
			controller.setMessage("Invalid Command");
			return;
		}
		String by = parm[3];
		int index = 0;
		try{
			index = Integer.parseInt(parm[4]);
		}
		catch(NumberFormatException e){
			controller.setMessage("Invalid index");
			return;
		}
		String name = parm[6];
		
		controller.getModel().crossBy(by, index, name);
	}
	
	/**
	 * solution - ask the model for the solution of maze
	 * @param arg - get the name of the maze
	 */
	private void solution(String name){
		Solution<Position>	solution = controller.getModel().getSolution(name);
		if(solution == null)
			controller.setMessage("Not exist solution for " + name + " maze");
		else
			controller.setMessage(solution.toString());
	}
	
	
	/**
	 * ask from the model the maze with specific name
	 * @param name - maze name
	 */
	private void defaultDisplay(String name){
		controller.getModel().getMazeByName(name);
	}
}
