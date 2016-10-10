package Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import View.LoginView;




public class JukeBoxGui extends JFrame {
	
	public static void main(String[] args) {
		JukeBoxGui g = new JukeBoxGui();
		g.setVisible(true);
	}
	
	private LoginView loginView;
	private JPanel currentView;
	public static final int width = 450;
	public static final int height = 400;

	public JukeBoxGui() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setLocation(100, 40);
		this.setTitle("Tic Tac Toe");

		
		loginView = new LoginView(width, height);
		// Set default view
		setViewTo(loginView);
	}
	private void setViewTo(JPanel newView) {
		if (currentView != null)
			remove(currentView);
		currentView = newView;
		add(currentView);
		currentView.repaint();
		validate();
	}

	

}
