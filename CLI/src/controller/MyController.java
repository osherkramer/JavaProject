package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public class MyController extends CommonController {
	HashMap<String,Command> hash;
	
	public MyController(Model model, View view) {
		super(model, view);
		
		HashMap<String,Command> hash = new HashMap<String,Command>();
		hash.put("dir", new Dir(this));
		hash.put("display", new Display(this));
		hash.put("generate", new Generate3dMaze(this));
		hash.put("solve", new Solve(this));
		hash.put("save", new SaveMaze(this));
		hash.put("load", new LoadMaze(this));
		hash.put("maze", new MazeSize(this));
		hash.put("file", new FileSize(this));
		hash.put("exit", new Exit(this));
		
		view.setCommands(hash);
	}

	@Override
	public void setMassage(String message) {
		this.view.displayMassage(message);

	}

}
