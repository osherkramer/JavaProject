package controller;

import java.util.HashMap;
import model.Model;
import view.View;

/**
 * MyController class extends CommonController
 * manage the side of the controller
 */

public class MyController extends CommonController {
	HashMap<String,Command> hash;
	
	/**
	 * MyController constructor - get Model and View
	 * initialize the model and view in the CommonController
	 * create the HashMap from String to Command
	 * @param model - get object from type Model
	 * @param view - get object from type View
	 */
	
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
	public void setMessage(String message) {
		this.view.displayMessage(message);

	}

}
