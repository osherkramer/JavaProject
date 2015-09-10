package view;


import java.io.BufferedReader;
import java.io.PrintWriter;

public class MyView extends CommonView {
	
	
	public MyView(BufferedReader in, PrintWriter out){
		this.in = in;
		this.out = out;		
	}

	@Override
	public void start(){
		cli = new CLI(in,out, hashCommand);
		cli.start();
	}

	@Override
	public void displayMassage(String message) {
		System.out.println(message);
	}

}
