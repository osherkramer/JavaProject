package controller;

public class Solve extends CommonCommand {

	public Solve(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String arg) 
	{
		controller.getModel().createSolution(arg);
	}
}
