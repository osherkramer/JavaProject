package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import controller.Controller;

public class MyModel extends CommonModel {

	public MyModel(Controller control) {
		super(control);
	}

	@Override
	public void generate(String name, int x, int y, int z) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Maze3d maze = new MyMaze3dGenerator().generate(x, y, z);
				hashMaze.put(name,maze);
			}
		}).start();
		
		controller.setMassage("maze " + name + " is ready");
	}
	
	public Maze3d getMazeByName(String name){
		return hashMaze.get(name);
	}	

}
