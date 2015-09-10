import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Controller;
import controller.Dir;
import controller.Display;
import controller.Exit;
import controller.FileSize;
import controller.Generate3dMaze;
import controller.LoadMaze;
import controller.MazeSize;
import controller.MyController;
import controller.SaveMaze;
import controller.Solve;
import model.Model;
import model.MyModel;
import view.CLI;
import view.MyView;
import view.View;

public class Run {
	public static void main(String[] args) {
		Controller controller = new MyController();
		HashMap<String,Command> hash = new HashMap<String,Command>();
		
		hash.put("dir", new Dir(controller));
		hash.put("display", new Display(controller));
		hash.put("generate", new Generate3dMaze(controller));
		hash.put("solve", new Solve(controller));
		hash.put("save", new SaveMaze(controller));
		hash.put("load", new LoadMaze(controller));
		hash.put("maze", new MazeSize(controller));
		hash.put("file", new FileSize(controller));
		hash.put("exit", new Exit(controller));
		
		
		
		Model model = new MyModel(controller);
		CLI cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(new OutputStreamWriter(System.out)), hash);
		View view = new MyView(controller, cli);
		
		controller.setModel(model);
		controller.setView(view);
		
		try {
			view.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
