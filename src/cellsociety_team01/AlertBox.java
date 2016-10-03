package cellsociety_team01;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class AlertBox {

	public static void displayError(String errorMessage) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frame frame = new Frame();
				JOptionPane.showMessageDialog(frame, errorMessage);
			}
		});
		// Frame frame = new Frame();
		// JOptionPane.showMessageDialog(frame,errorMessage);
	}

}
