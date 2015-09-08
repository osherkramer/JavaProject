package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface Model {
	void addSolution(String name, Solution<Position> solution);
	Solution<Position> getSolution(String name);
	void generate(String name, int x, int y, int z);
	Maze3d getMazeByName(String name);
}
