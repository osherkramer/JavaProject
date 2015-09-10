package controller;

import model.Model;
import view.View;

public interface Controller {
	Model getModel();
	View getView();
	void setMassage(String massage);
}
