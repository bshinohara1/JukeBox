package Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import View.LoginView;
import model.SongLibrary;
import model.UserDatabase;




public class JukeBoxStartGui extends JFrame {
	
	public static void main(String[] args) {
		JukeBoxStartGui g = new JukeBoxStartGui();
		g.setVisible(true);
	}
	
	private LoginView loginView;
	private JPanel currentView;
	public static final int width = 450;
	public static final int height = 400;
	private SongLibrary lib;
	private UserDatabase users;

	public JukeBoxStartGui() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setLocation(100, 40);
		this.setTitle("Tic Tac Toe");
		users = new UserDatabase();
		lib = new SongLibrary();
		

		
		loginView = new LoginView(width, height, lib, users);
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
