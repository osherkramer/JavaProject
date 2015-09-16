package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;

public interface Model {
	
	void createSolution(String name);	
	Solution<Position> getSolution(String name);
	void generate(String name, int x, int y, int z);
	Maze3d getMazeByName(String name);
	void crossBy(String by, int index, String name);
	void saveMaze(String arg);
	void loadMaze(String arg);
	void mazeSizeMemory(String name);
	void mazeSizeFile(String name);
	void setController(Controller controller);
	
	void exit();
}
