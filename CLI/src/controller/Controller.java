package controller;

import model.Model;
import view.View;

/**
 * Controller interface - set the functional of Controller
 */
public interface Controller {
	
	/**
	 * get the model that the controller work with him
	 * @return - object from type Model
	 */
	Model getModel();
	
	/**
	 * get the view that the controller work with him
	 * @return - object from type View
	 */
	View getView();
	
	/**
	 * set a Message that will be send to the view
	 * @param massage - the massage to send
	 */
	void setMessage(String massage);
}
