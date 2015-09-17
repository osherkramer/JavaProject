package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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

/**
 * MyModel class extends CommonModel
 * manage the size of the model
 */


public class MyModel extends CommonModel {

	ExecutorService threadpool;
	HashMap<Maze3d,String> mazeFile;
	
	/**
	 * Default Constructor of MyModel
	 */
	public MyModel() {
		threadpool = Executors.newFixedThreadPool(10);  //////////////////
		mazeFile = new HashMap<Maze3d,String>();
	}
	
	@Override
	public void generate(String name, int x, int y, int z) {
		threadpool.execute(new Runnable() {
			
			@Override
			public void run() {
				Maze3d maze = new MyMaze3dGenerator().generate(x, y, z);
				hashMaze.put(name,maze);
				controller.setMessage("maze " + name + " is ready");
				
			}
		});		
	}
	
	@Override
	public void getMazeByName(String name){
		Maze3d maze = hashMaze.get(name);
		if(maze == null)
			controller.setMessage("Not exist maze by name: " + name);
		else
			controller.setMessage(maze.toString()); 
	}

	@Override
	public void createSolution(String name) {
		String[] parm=name.split(" ");
		
		if(parm.length != 2){
			controller.setMessage("Invalid Command");
			return;
		}
		
		/////////////////////////////////////////////////////////////
		threadpool.execute(new Runnable() {
			
			@Override
			public void run() {
				if(parm[1].equalsIgnoreCase("bfs")){
					Maze3d maze = hashMaze.get(parm[0]); ////////
					BFS<Position> bfs = new BFS<Position>();
					Solution<Position> bfsSolution = bfs.search(new MazeDomain(maze));
					hashSolution.put(parm[0], bfsSolution);
					controller.setMessage("Solution for " + parm[0] + " is ready");
				}
				else if(parm[1].equalsIgnoreCase("ManhattanDistance")){
					Maze3d maze = hashMaze.get(parm[0]);/////////////
					AStar<Position> astarManhattanDistance = new AStar<Position>(new MazeManhattanDistance(new State<Position>(maze.getGoalPosition())));
					Solution<Position> astarManhattan = astarManhattanDistance.search(new MazeDomain(maze));
					hashSolution.put(parm[0], astarManhattan);
					controller.setMessage("Solution for " + parm[0] + " is ready");
					
				}
				else if(parm[1].equalsIgnoreCase("AirDistance")){
					Maze3d maze = hashMaze.get(parm[0]);/////////////////////
					AStar<Position> astarAirDistance = new AStar<Position>(new MazeAirDistance(new State<Position>(maze.getGoalPosition())));
					Solution<Position> astarAir = astarAirDistance.search(new MazeDomain(maze));
					hashSolution.put(parm[0], astarAir);
					controller.setMessage("Solution for " + parm[0] + " is ready");
				}
				else
					controller.setMessage("Invalid algorithm");
			}
		});
	}

	@Override
	public void crossBy(String by, int index, String name) {
		Maze3d maze = hashMaze.get(name);
		
		String strMaze ="";
		int[][] maze2d = null;
		if(maze == null){
			controller.setMessage("Maze not exist");
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
				controller.setMessage("Invalid cross section");
				return;
			}
		}
		catch(IndexOutOfBoundsException e){
			controller.setMessage("Invalid index");
			return;
		}
		
		
		
		for(int i = 0; i < maze2d.length; i++){
			for(int j = 0; j < maze2d[i].length; j++)
				strMaze += String.valueOf(maze2d[i][j]) + " ";
			strMaze += '\n';
		}
		
		controller.setMessage(strMaze);
		
				
		
		
	}

	@Override
	public void saveMaze(String arg) {
		String[] parm = arg.split(" ");
		if(parm.length != 3){
			controller.setMessage("Invalid Command");
			return;
		}
		
		Maze3d maze = hashMaze.get(parm[1]);
		if(maze == null){
			controller.setMessage("Maze " + parm[1] + " not exist");
			return;
		}
		
		OutputStream out = null;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(parm[2] + ".maz"));
			out.write(maze.toByteArray());	
			mazeFile.put(maze, parm[2] + ".maz");
		} catch (FileNotFoundException e) {
			controller.setMessage(e.getMessage());
			return;
		} catch (IOException e) {
			controller.setMessage(e.getMessage());
			return;
		}
		finally{
			try {
				out.flush();
			} catch (IOException e) {
				controller.setMessage(e.getMessage());
			}
			try {
				out.close();
			} catch (IOException e) {
				controller.setMessage(e.getMessage());
			}
		}
		
		
		controller.setMessage("File " + parm[2] + " save");
		
	}

	@Override
	public void loadMaze(String arg) {
		String[] parm = arg.split(" ");
		Maze3d loaded = null;
		if(parm.length != 3){
			controller.setMessage("Invalid Command");
			return;
		}
			
		InputStream in=null;
		try {
			in = new MyDecompressorInputStream(new FileInputStream(parm[2] + ".maz"));
			byte b[] = new byte[4096];
			in.read(b);
			loaded = new Maze3d(b);
		} catch (FileNotFoundException e) {
			controller.setMessage(e.getMessage());
			return;
		} catch (IOException e) {
			controller.setMessage(e.getMessage());
			return;
		}
		catch(NullPointerException e)
		{
			controller.setMessage(e.getMessage());
			return;
		}
		finally
		{
			try {
				in.close();
			} catch (IOException e) 
			{
				controller.setMessage("Maze "+ parm[1]+" was unsuccessfully");
			}
		}
			
		hashMaze.put(parm[1], loaded);
		controller.setMessage("Maze " + parm[2] + " loaded successfully");
	}
	
	@Override
	public void mazeSizeMemory(String name) {
		Maze3d maze = hashMaze.get(name);
		if(maze == null){
			controller.setMessage("Maze " + name + " not exist");
			return;
		}
		
		int size = 4*(maze.getX()*maze.getY()*maze.getZ() + 3 + 3 + 3);
		
		controller.setMessage("Maze " + name + " size in memory: " + size);
		
	}

	//////////////////////////////////////////////////
	@Override
	public void mazeSizeFile(String str) {
		
		try{
			String fielPath = mazeFile.get(hashMaze.get(str));
			if(fielPath == null){
				controller.setMessage("Maze " + str + " not exist in any file");
				return;
			}
			File maze = new File(fielPath);
			controller.setMessage("Maze file " + str + " size is: " + maze.length());
		}
		catch (NullPointerException e){
			controller.setMessage("Not exist " + str + " file");
		}
		
		
	}

	@Override///////////////////////////////
	public void exit(){
		threadpool.shutdown();
		try {
			while(!(threadpool.awaitTermination(10, TimeUnit.SECONDS)));
		} catch (InterruptedException e) {
			controller.setMessage(e.getMessage());
		}	
		
		
	}
}
