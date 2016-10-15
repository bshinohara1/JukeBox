
//Author: Adam and Ben Shinohara
//This class represents the GUI that is used to create
//this initial Jukebox. Utilizes the login view class 
//as it's main functional component. Need a SongLibrary
//and userdatabase to have the GUI function properly
//and create the login view

package Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import View.LoginView;
import model.SongLibrary;
import model.UserDatabase;

//Gui class that extends JFrame
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

	// Constructor that initializes all the variables,
	// sets the width and height, and sets when to close
	// the JFrame
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

	// Sets the current JPanel. This function is used to
	// change the current JPanel to the newView Jpanel that
	// is passed to the function.
	private void setViewTo(JPanel newView) {
		if (currentView != null)
			remove(currentView);
		currentView = newView;
		add(currentView);
		currentView.repaint();
		validate();
	}

}
