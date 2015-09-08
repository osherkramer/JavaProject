package controller;

public abstract class CommonCommand implements Command {
	
	Controller controller;
	
	public CommonCommand(Controller controller){
		this.controller = controller;
	}

	@Override
	public abstract void doCommand(String str);
}
