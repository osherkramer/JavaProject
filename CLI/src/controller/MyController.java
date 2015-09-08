package controller;

import java.util.HashMap;

public class MyController extends CommonController {
	
	@Override
	public HashMap<String, Command> createHashMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMassage(String message) {
		this.view.displayMassage(message);

	}

}
