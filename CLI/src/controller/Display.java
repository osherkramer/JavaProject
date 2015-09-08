package controller;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;


public class Display extends CommonCommand {

	public Display(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		String[] parm = str.split(" ");
		
		if(parm[0].equalsIgnoreCase("cross"))
			cross(parm[3], parm[4], parm[6]);
		else if(parm[0].equalsIgnoreCase("solution"))
			solution(parm[1]);
		else if(parm.length == 1)
			defaultDisplay(parm[0]);
		else
			controller.setMassage("Invalid Command");
			

	}
	
	private void cross(String by, String index, String name){
				
	}
	
	private void solution(String name){
		Solution<Position>	solution = controller.getModel().getSolution(name);
		if(solution == null)
			controller.setMassage("Not exist solution for " + name + " maze");
		else
			controller.setMassage(solution.toString());
	}
	
	private void defaultDisplay(String name){
		Maze3d maze = controller.getModel().getMazeByName(name);
		if(maze == null)
			controller.setMassage("Not exist maze by name: " + name);
		controller.setMassage(maze.toString());
	}
}
