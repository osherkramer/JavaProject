package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public abstract class CommonController implements Controller {
	
	Model model;
	View view;

	@Override
	public void setModel(Model m) {
		this.model = m;
	}

	@Override
	public void setView(View v) {
		this.view = v;
	}
	
	public Model getModel() { return model; }
	public View getView() {return view; }

	@Override
	public abstract HashMap<String, Command> createHashMap();
	
	@Override
	public abstract void setMassage(String massage);

}
