package controller;

import java.io.File;

/**
 * Dir class - extends the CommonComand
 * manage the dir command that display list of files in folder.
 */

public class Dir extends CommonCommand{
	
	/**
	 * Dir constructor
	 * @param controller - get the controller to work with him
	 */
	public Dir(Controller controller) {
		super(controller);
	}

	@Override
	public void doCommand(String str) {
		try {
			File file = new File(str);	
			String[] string = file.list();
			String list = "";
			
			for(int i = 0; i <string.length; i++)
				list += string[i] + '\n';
			
			controller.setMessage(list);
		}
		catch (NullPointerException e){
			controller.setMessage("Invalid path");
		}
		
	}
}
