package examples.events;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MoveMessageDemo extends JFrame {
	public MoveMessageDemo() {
		// Create a MovableMessagePanel instance for moving a message
		MovableMessagePanel p = new MovableMessagePanel("Welcome to Java");

		// Place the message panel in the frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(p);
	}

	/** Main method */
	public static void main(String[] args) {
		MoveMessageDemo frame = new MoveMessageDemo();
		frame.setTitle("MoveMessageDemo");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	// Inner class: MovableMessagePanel draws a message
	static class MovableMessagePanel extends JPanel {
		private String message = "Welcome to Java";
		private int x = 20;
		private int y = 20;

		/** Construct a panel to draw string s */
		public MovableMessagePanel(String s) {
			message = s;
			this.addMouseMotionListener(new MouseMotionAdapter() {
				/** Handle mouse dragged event */
				@Override
				public void mouseDragged(MouseEvent e) {
					// Get the new location and repaint the screen
					x = e.getX();
					y = e.getY();
					repaint();
				}
			});
		}

		/** Paint the component */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString(message, x, y);
		}
	}
}
