package view;
import java.util.HashMap;

import controller.Command;
import controller.Controller;


public interface View {
	void start();
	void displayMassage(String message);
	void exit();
	void setCommands(HashMap<String,Command> hashCommand);
	void setController(Controller controller);
}
