package controller;

import model.Model;
import view.View;

public abstract class CommonController implements Controller {
	
	Model model;
	View view;
	

	public CommonController(Model model, View view){
		this.model = model;
		this.view = view;
	}
	
	public Model getModel() { return model; }
	public View getView() {return view; }
	
	@Override
	public abstract void setMassage(String massage);

}
