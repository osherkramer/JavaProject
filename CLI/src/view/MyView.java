package view;


import java.io.IOException;
import controller.Controller;

public class MyView extends CommonView {
	
	
	public MyView(Controller control, CLI cli){
		super(control, cli);
	}

	@Override
	public void start() throws IOException {
		Thread t=new Thread(cli);
		t.start();
	}

	@Override
	public void displayMassage(String message) {
		System.out.println(message);
	}

}
