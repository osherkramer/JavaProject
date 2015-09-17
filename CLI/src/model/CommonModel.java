package model;

import java.util.HashMap;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;
/**
 * CommonModel implements Model interface
 * abstract class of the model
 */

public abstract class CommonModel implements Model {
	
	Controller controller;
	HashMap<String, Maze3d> hashMaze = new HashMap<String,Maze3d>();
	HashMap<String, Solution<Position>> hashSolution = new HashMap<String, Solution<Position>>();
	
	@Override
	public void setController(Controller controller){
		this.controller = controller;
	}
	
	@Override	
	public Solution<Position> getSolution(String name){
		return hashSolution.get(name);
	}
	
	@Override
	public abstract void generate(String name, int x, int y, int z);
	
	@Override
	public abstract void getMazeByName(String name);
	
	@Override
	public abstract void createSolution(String name);
	
	@Override
	public abstract void crossBy(String by, int index, String name);
	
	@Override
	public abstract void saveMaze(String arg);
	
	@Override
	public abstract void loadMaze(String arg);
	
	@Override
	public abstract void mazeSizeMemory(String name);

	@Override
	public abstract void mazeSizeFile(String name);
	
	@Override
	public abstract void exit();
}
