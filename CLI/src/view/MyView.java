package view;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * MyView - extands CommonView and manage the size of the client - the view
 */

public class MyView extends CommonView {
	
	/**
	 * Constructor of MyView
	 * @param in - get BufferedReader, after initialize the cli with him
	 * @param out - get PrintWriter, after initialize the cli with him
	 */
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
	public void displayMessage(String message) {
		cli.setMessage(message);
	}

}
