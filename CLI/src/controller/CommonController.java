package controller;

import model.Model;
import view.View;

/**
 * CommonController class - implements the Controller interface
 * abstract class
 */

public abstract class CommonController implements Controller {
	
	Model model;
	View view;
	
	/**
	 * CommonController Constructor - initialize the Model and View that
	 * the controller work with them
	 * @param model - get object from type Model
	 * @param view - get object from type View
	 */
	public CommonController(Model model, View view){
		this.model = model;
		this.view = view;
	}
	
	@Override
	public Model getModel() { return model; }
	
	@Override
	public View getView() {return view; }
	
	@Override
	public abstract void setMessage(String massage);

}
