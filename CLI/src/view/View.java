package view;

import java.io.IOException;


public interface View {
	void start() throws IOException;
	void displayMassage(String message);
}
