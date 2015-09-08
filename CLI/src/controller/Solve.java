package controller;

import algorithms.demo.MazeDomain;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Solution;
import algorithms.search.State;

public class Solve extends CommonCommand {

	public Solve(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		String[] parm = str.split(" ");
		if(parm.length != 2)
			controller.setMassage("Invalid Parameter");
		else{
			if(parm[1].equalsIgnoreCase("BFS"))
				BFS(parm[0]);
			else if(parm[1].equalsIgnoreCase("AirDistance"))
				AirDistance(parm[0]);
			else if(parm[1].equalsIgnoreCase("ManhattanDistance"))
				ManhattanDistance(parm[0]);
			else
				controller.setMassage("Algorithm not exist");
			
			
		}

	}
	
	public void BFS(String name){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Maze3d maze = controller.getModel().getMazeByName(name);
				BFS<Position> bfs = new BFS<Position>();
				Solution<Position> bfsSolution = bfs.search(new MazeDomain(maze));
				controller.getModel().addSolution(name, bfsSolution);
				controller.setMassage("Solution for " + name + " is ready");
			}
		});
	}
	public void AirDistance(String name){
			new Thread(new Runnable() {
			
				@Override
				public void run() {
					Maze3d maze = controller.getModel().getMazeByName(name);
					AStar<Position> astarAirDistance = new AStar<Position>(new MazeAirDistance(new State<Position>(maze.getGoalPosition())));
					Solution<Position> astarAir = astarAirDistance.search(new MazeDomain(maze));
					controller.getModel().addSolution(name, astarAir);
					controller.setMassage("Solution for " + name + " is ready");
				}
			});
	}
	public void ManhattanDistance(String name){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Maze3d maze = controller.getModel().getMazeByName(name);
				AStar<Position> astarManhattanDistance = new AStar<Position>(new MazeManhattanDistance(new State<Position>(maze.getGoalPosition())));
				Solution<Position> astarManhattan = astarManhattanDistance.search(new MazeDomain(maze));
				controller.getModel().addSolution(name, astarManhattan);
				controller.setMassage("Solution for " + name + " is ready");
			}
		});
	}

}
