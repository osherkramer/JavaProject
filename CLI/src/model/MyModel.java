package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.IO.MyCompressorOutputStream;
import algorithms.IO.MyDecompressorInputStream;
import algorithms.demo.MazeDomain;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Solution;
import algorithms.search.State;
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

	@Override
	public void createSolution(String name) {
		String[] parm=name.split(" ");
		
		if(parm.length != 2){
			controller.setMassage("Invalid Command");
			return;
		}
		
		new Thread(new Runnable(){

			@Override
			public void run()
			{
				
				if(parm[1].equalsIgnoreCase("bfs")){
					Maze3d maze = controller.getModel().getMazeByName(parm[0]);
					BFS<Position> bfs = new BFS<Position>();
					Solution<Position> bfsSolution = bfs.search(new MazeDomain(maze));
					hashSolution.put(parm[0], bfsSolution);
					controller.setMassage("Solution for " + parm[0] + " is ready");
				}
				else if(parm[1].equalsIgnoreCase("ManhattanDistance")){
					Maze3d maze = controller.getModel().getMazeByName(parm[0]);
					AStar<Position> astarManhattanDistance = new AStar<Position>(new MazeManhattanDistance(new State<Position>(maze.getGoalPosition())));
					Solution<Position> astarManhattan = astarManhattanDistance.search(new MazeDomain(maze));
					hashSolution.put(parm[0], astarManhattan);
					controller.setMassage("Solution for " + parm[0] + " is ready");
					
				}
				else if(parm[1].equalsIgnoreCase("AirDistance")){
					Maze3d maze = controller.getModel().getMazeByName(parm[0]);
					AStar<Position> astarAirDistance = new AStar<Position>(new MazeAirDistance(new State<Position>(maze.getGoalPosition())));
					Solution<Position> astarAir = astarAirDistance.search(new MazeDomain(maze));
					hashSolution.put(parm[0], astarAir);
					controller.setMassage("Solution for " + parm[0] + " is ready");
				}
				else
					controller.setMassage("Invalid algorithm");
			}		
		}).start();
		
		
	}

	@Override
	public void crossBy(String by, int index, String name) {
		Maze3d maze = hashMaze.get(name);
		
		String strMaze ="";
		int[][] maze2d = null;
		if(maze == null){
			controller.setMassage("Maze not exist");
			return;
		}
		
		
		
		try{
			switch(by){
			case "X":
				maze2d = maze.getCrossSectionByX(index);
				break;
			case "x":
				maze2d = maze.getCrossSectionByX(index);
				break;
			case "Y":
				maze2d = maze.getCrossSectionByY(index);
				break;
			case "y":
				maze2d = maze.getCrossSectionByY(index);
				break;
			case "Z":
				maze2d = maze.getCrossSectionByZ(index);
				break;
			case "z":
				maze2d = maze.getCrossSectionByZ(index);
				break;
			default:
				controller.setMassage("Invalid cross section");
				return;
			}
		}
		catch(IndexOutOfBoundsException e){
			controller.setMassage("Invalid index");
			return;
		}
		
		
		
		for(int i = 0; i < maze2d.length; i++){
			for(int j = 0; j < maze2d[i].length; j++)
				strMaze += String.valueOf(maze2d[i][j]) + " ";
			strMaze += '\n';
		}
		
		controller.setMassage(strMaze);
		
				
		
		
	}

	@Override
	public void saveMaze(String arg) {
		String[] parm = arg.split(" ");
		if(parm.length != 3){
			controller.setMassage("Invalid Command");
			return;
		}
		
		Maze3d maze = hashMaze.get(parm[1]);
		if(maze == null){
			controller.setMassage("Maze " + parm[1] + " not exist");
			return;
		}
		
		OutputStream out = null;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(parm[2] + ".maz"));
			out.write(maze.toByteArray());	
		} catch (FileNotFoundException e) {
			controller.setMassage(e.getMessage());
			return;
		} catch (IOException e) {
			controller.setMassage(e.getMessage());
			return;
		}
		finally{
			try {
				out.flush();
			} catch (IOException e) {
				controller.setMassage(e.getMessage());
			}
			try {
				out.close();
			} catch (IOException e) {
				controller.setMassage(e.getMessage());
			}
		}
		
		controller.setMassage("File " + parm[2] + " save");
		
	}

	@Override
	public void loadMaze(String arg) {
		String[] parm = arg.split(" ");
		Maze3d loaded = null;
		if(parm.length != 3){
			controller.setMassage("Invalid Command");
			return;
		}
		
		InputStream in = null;
		try {
			in = new MyDecompressorInputStream(new FileInputStream(parm[2] + ".maz"));
			byte b[] = new byte[2048]; //change size
			in.read(b);
			loaded = new Maze3d(b);
		} catch (FileNotFoundException e) {
			controller.setMassage(e.getMessage());
			return;
		} catch (IOException e) {
			controller.setMassage(e.getMessage());
			return;
		}
		finally{
			try {
				in.close();
			} catch (IOException e) {
				controller.setMassage(e.getMessage());
			}
		}
		
		hashMaze.put(parm[1], loaded);
		controller.setMassage("File " + parm[2] + " load");
		
	}	

}
