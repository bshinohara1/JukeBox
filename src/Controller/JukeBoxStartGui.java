
//Author: Adam and Ben Shinohara
//This class represents the GUI that is used to create
//this initial Jukebox. Utilizes the login view class 
//as it's main functional component. Need a SongLibrary
//and userdatabase to have the GUI function properly
//and create the login view. Utilizes a windowlistener 

package Controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
	public static final int width = 900;
	public static final int height = 650;
	private SongLibrary lib = null;
	private UserDatabase users = null;

	// Constructor that initializes all the variables,
	// sets the width and height, and sets when to close
	// the JFrame
	public JukeBoxStartGui() {
		this.setSize(width, height);
		this.setLocation(100, 40);
		this.setTitle("JukeBox");
		users = users.getDatabase();
		lib = lib.getSongLibrary();

		loginView = new LoginView(width, height, lib, users);
		loginView.setLocation(25, 425);

		// Set default view
		setViewTo(loginView);
		WindowListener1 windowListen = new WindowListener1();
		this.addWindowListener(windowListen);

	}

	// Window listener private classs that is used as an adapter.
	// Only the necessary functions in the listener are changed
	private class WindowListener1 extends WindowAdapter {

		// When the window closes, this function runs to determine
		// the action that is taken. Calls another close function
		// to determine if the state of the collection should be saved
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.exit(loginView.close());
		}

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
