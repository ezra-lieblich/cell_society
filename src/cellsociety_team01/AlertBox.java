package cellsociety_team01;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
@author Christopher Lu
@author Eric Song
* Creates Pop up box with an error message. 
* For example, if a user inputs a negative value for shark reproduce term, a text box will pop up saying that the sharkReproduce value was invalid,
* and displays the default value shark reproduce has been set to.
**/

public class AlertBox {

	
	/**
	 * Displays a pop-up box with message errorMessage
	 * @param errorMessage
	 */
	public static void displayError(String errorMessage) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frame frame = new Frame();
				JOptionPane.showMessageDialog(frame, errorMessage);
			}
		});
	}

}
