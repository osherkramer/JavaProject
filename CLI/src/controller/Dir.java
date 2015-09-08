package controller;

import java.io.File;

public class Dir extends CommonCommand{
	
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
			
			controller.setMassage(list);
		}
		catch (NullPointerException e){
			controller.setMassage("Invalid path");
		}
		
	}
}
