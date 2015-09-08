package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public interface Controller {
	void setModel(Model m);
	void setView(View v);
	Model getModel();
	View getView();
	
	HashMap<String,Command> createHashMap();
	
	void setMassage(String massage);
}
