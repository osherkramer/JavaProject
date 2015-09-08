package model;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;

public abstract class CommonModel implements Model {
	
	Controller controller;
	HashMap<String, Maze3d> hashMaze = new HashMap<String,Maze3d>();
	HashMap<String, Solution<Position>> hashSolution = new HashMap<String, Solution<Position>>();
	
	public CommonModel(Controller control){
		this.controller = control;
	}
	
	public void addSolution(String name, Solution<Position> solution){
		hashSolution.put(name, solution);
	}
	
	public Solution<Position> getSolution(String name){
		return hashSolution.get(name);
	}
	
	@Override
	public abstract void generate(String name, int x, int y, int z);
	
	@Override
	public abstract Maze3d getMazeByName(String name);

}
