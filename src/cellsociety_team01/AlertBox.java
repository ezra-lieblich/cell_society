package cellsociety_team01;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class AlertBox {

	public static void displayError(String errorMessage){
		Frame frame = new Frame();
		JOptionPane.showMessageDialog(frame,errorMessage);
	}

}
