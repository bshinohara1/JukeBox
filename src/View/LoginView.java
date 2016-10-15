//Author: Adam and Ben Shinohara
//This class represents the login view of the Gui. Class sets up all the 
//JButtons and fields that are used in the view. Users have the option
//to log in and then play songs displays errors at appropriate times
//like when someone tries to play a song while not logged in. This 
//class utilizes action listener in the form of a Buttonlistener

package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Checker;
import model.Resetter;
import model.Song;
import model.SongLibrary;
import model.User;
import model.UserDatabase;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

//LoginView Class that extends JPanel
public class LoginView extends JPanel {

	private JButton song1;
	private JButton song2;
	private JButton signOut;
	private JButton login;
	private int height, width;
	private JLabel accountName;
	private JLabel password;
	private JLabel status;
	private JTextField textEditorAcc;
	private JPasswordField textEditorPass;
	private SongLibrary ourSongs;
	private UserDatabase ourUsers;
	private User curUser;
	private Queue<Song> ourQueue;
	private boolean userStatus;
	private Song firstSong;
	private Song secondSong;
	private Resetter myRest;
	private Checker myCheck;

	// Constructor that is used to initialize all the instance variables
	// this includes intializing all the panel components
	public LoginView(int width, int height, SongLibrary lib, UserDatabase users) {
		ourUsers = users;
		ourSongs = lib;
		this.height = height;
		this.width = width;
		userStatus = false;
		ourQueue = new LinkedList<Song>();
		myRest = new Resetter(users, lib);
		myCheck = new Checker();
		firstSong = ourSongs.getSong("Tada");
		secondSong = ourSongs.getSong("Space Music");
		initializeJTextAreaPanel();
	}

	// This function is used to initialize the JPanel.
	// JButtons, fields, and all other Panel components are
	// put in there correct location. Some buttons included
	// are login, song 1/2, and signout.
	private void initializeJTextAreaPanel() {
		JPanel textAreaPanel = new JPanel();
		Font myFont = new Font("Courier", Font.BOLD, 36);
		textAreaPanel.setLayout(null);
		textAreaPanel.setFont(myFont);
		accountName = new JLabel("Account Name");
		password = new JLabel("Password");
		textEditorAcc = new JTextField("", 50);
		textEditorPass = new JPasswordField("", 50);
		textAreaPanel.add(accountName);
		accountName.setLocation(100, 10);
		accountName.setSize(100, 30);
		textAreaPanel.add(textEditorAcc);
		textEditorAcc.setLocation(200, 10);
		textEditorAcc.setSize(150, 30);

		textAreaPanel.add(password);
		password.setLocation(100, 50);
		password.setSize(100, 30);
		textAreaPanel.add(textEditorPass);
		textEditorPass.setLocation(200, 50);
		textEditorPass.setSize(150, 30);

		ButtonListener1 buttonListener1 = new ButtonListener1();
		login = new JButton("Login");
		textAreaPanel.add(login);
		login.setLocation(230, 100);
		login.setSize(120, 40);
		login.addActionListener(buttonListener1);

		signOut = new JButton("Sign Out");
		textAreaPanel.add(signOut);
		signOut.setLocation(100, 100);
		signOut.setSize(120, 40);
		signOut.addActionListener(buttonListener1);

		status = new JLabel("Not logged in");
		textAreaPanel.add(status);
		status.setLocation(100, 150);
		status.setSize(300, 30);

		textAreaPanel.setBackground(Color.PINK);

		this.add(textAreaPanel);

		this.setLayout(null);
		textAreaPanel.setLocation(10, 100);
		textAreaPanel.setSize(400, 200);

		ButtonListener buttonListener = new ButtonListener();
		song1 = new JButton("Select Song 1");
		this.add(song1);
		song1.setLocation(20, 20);
		song1.setSize(120, 25);
		song1.addActionListener(buttonListener);

		song2 = new JButton("Select Song 2");
		this.add(song2);
		song2.setLocation(20, 55);
		song2.setSize(120, 25);
		this.setBackground(Color.PINK);
		song2.addActionListener(buttonListener);

	}

	// Private WaitingForSongToEnd class is used to determine when a song has
	// ended. This allows multiple songs to be queued and then played one after
	// another
	private class WaitingForSongToEnd implements EndOfSongListener {

		// This method determines when a song is finished playing and
		// starts the next song playing after the first one finishes.
		// Utilizes a queue to do this
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			ourQueue.remove();
			if (!ourQueue.isEmpty()) {
				EndOfSongListener waitForSongEnd = new WaitingForSongToEnd();
				SongPlayer.playFile(waitForSongEnd, ourQueue.peek().getlocation());
			}
		}
	}

	// Private class Button listener that is used for the two song listeners.
	// If a song is selected and a user is logged in, then
	// the song is played if all the criteria for playing a song is met.
	// If any of the criteria are not met, then an error message is played
	private class ButtonListener implements ActionListener {

		// Formats the time in seconds that is sent to it, and
		// returns the amount of time in a string representing
		// hours, minutes, and seconds
		private String format(int credits) {
			int hours = credits / 3600;
			String sHours = new String("");
			if (hours < 10) {
				sHours = "0" + hours;
			} else {
				sHours = "" + hours;
			}
			int minutes = credits % 3600;
			int minutesForS = minutes;
			minutes = minutes / 60;

			String sMinutes;
			if (minutes < 10) {
				sMinutes = "0" + minutes;
			} else {
				sMinutes = "" + minutes;
			}
			int sec = minutesForS % 60;

			String sSeconds = new String();
			if (sec < 10) {
				sSeconds = "0" + sec;
			} else {
				sSeconds = "" + sec;
			}
			String result = sHours + ":" + sMinutes + ":" + sSeconds;
			return result;
		}

		// Action performed method to determine which of the two song buttons
		// was clicked, and if all the criteria to play a song are met.
		// Plays the song if it is, shows an error message otherwise
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton buttonClicked = (JButton) arg0.getSource();
			Song songSelected;
			if (!userStatus) {
				JOptionPane.showMessageDialog(null, "Must be logged in to play a song");
			} else {

				if (buttonClicked.getText().equals("Select Song 1")) {
					songSelected = firstSong;
				} else {
					songSelected = secondSong;
				}

				myRest.checkday();
				int checkValue = myCheck.check(curUser, songSelected);
				if (checkValue == -1) {
					JOptionPane.showMessageDialog(null, "User can not play a song");
				} else if (checkValue == -2) {
					JOptionPane.showMessageDialog(null, "Song has reached it's max number of daily plays");
				} else if (checkValue == -3) {
					JOptionPane.showMessageDialog(null,
							"Song has reached it's max number of daily plays and User can't play song");
				} else if (checkValue > 0) {

					curUser.minusCount();
					songSelected.play();
					curUser.minusCredits(songSelected.gettime());
					status.setText("Status: Available Plays: " + curUser.getCount() + " , " + "Available Credit: "
							+ format(curUser.getCredits()));

					if (ourQueue.isEmpty()) {
						ourQueue.add(songSelected);
						EndOfSongListener waitForSongEnd = new WaitingForSongToEnd();
						SongPlayer.playFile(waitForSongEnd, ourQueue.peek().getlocation());
					} else {
						ourQueue.add(songSelected);
					}

				}
			}

		}
	}

	// Private class Button listener1 that is used for the login and logout
	// buttons.
	// Checks the user and password entered when login is hit and logins in if
	// these
	// user and password are correct. Sign out logs the user out and clears
	// the texts fields
	// If any of the criteria are not met, then an error message is played
	private class ButtonListener1 implements ActionListener {

		// Action performed method to determine if the signout or login button
		// was clicked, and if all the criteria to login are met, user logs in.
		// Shows an error message otherwise. Sign out signs the user out and
		// clears the text fields
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton buttonClicked = (JButton) arg0.getSource();

			if (buttonClicked.getText().equals("Login")) {
				String userName = textEditorAcc.getText();
				String userPass = textEditorPass.getText();

				if (ourUsers.validator(userName, userPass)) {
					curUser = ourUsers.getuser(userName);
					status.setText("Status: Available Plays: " + curUser.getCount() + " , " + "Available Credit: "
							+ format(curUser.getCredits()));
					userStatus = true;
				} else {
					curUser = null;
					userStatus = false;
					textEditorAcc.setText("");
					textEditorPass.setText("");
					status.setText("Not logged in");
					JOptionPane.showMessageDialog(null, "Incorrect UserName or Password");
				}
			} else {
				curUser = null;
				userStatus = false;
				textEditorAcc.setText("");
				textEditorPass.setText("");
				status.setText("Not logged in");
			}

		}

		// Formats the time in seconds that is sent to it, and
		// returns the amount of time in a string representing
		// hours, minutes, and seconds
		private String format(int credits) {
			int hours = credits / 3600;
			String sHours = new String("");
			if (hours < 10) {
				sHours = "0" + hours;
			} else {
				sHours = "" + hours;
			}
			int minutes = credits % 3600;
			int minutesForS = minutes;
			minutes = minutes / 60;

			String sMinutes;
			if (minutes < 10) {
				sMinutes = "0" + minutes;
			} else {
				sMinutes = "" + minutes;
			}
			int sec = minutesForS % 60;

			String sSeconds = new String();
			if (sec < 10) {
				sSeconds = "0" + sec;
			} else {
				sSeconds = "" + sec;
			}
			String result = sHours + ":" + sMinutes + ":" + sSeconds;
			return result;
		}
	}

}
