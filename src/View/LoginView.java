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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
public class LoginView extends JPanel implements Serializable {

	private JButton song1;
	private JButton song2;
	private JButton signOut;
	private JButton login;
	private int height, width;
	private JLabel accountName;
	private JLabel password;
	private JLabel status = new JLabel("Not logged in");
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
	private JTextArea queueArea;
	private JLabel queueAreaLabel;
	private TableModel model;
	private JTable table;
	private JLabel tableLabel;
	private JButton playButton;



	
	// Constructor that is used to initialize all the instance variables
	// this includes intializing all the panel components
	public LoginView(int width, int height, SongLibrary lib, UserDatabase users) {
		int choice = JOptionPane.showConfirmDialog(null, "Load saved collection");
	    System.out.println(choice);
	    if (choice == 0) {
	       startWithPersistentVersion();
	       myRest.checkday();
	       if (curUser != null)
	       {
	       status.setText(curUser.getname() + ": Available Plays: " + curUser.getCount() + " , " + "Available Credit: "
					+ format(curUser.getCredits()));
	       }
	       if (!ourQueue.isEmpty())
	       {
	    	   EndOfSongListener waitForSongEnd = new WaitingForSongToEnd();
			   SongPlayer.playFile(waitForSongEnd, ourQueue.peek().getlocation());
	       }

	       
	    }
	    else
	    {
		ourUsers = users;
		ourSongs = lib;
		userStatus = false;
		ourQueue = new LinkedList<Song>();
		myCheck = new Checker();
		firstSong = ourSongs.getSong("Tada");
		secondSong = ourSongs.getSong("Space Music");
		myRest =  myRest.getResetter(users, lib);
	    }
	    this.height = height;
		this.width = width;
		myCheck = new Checker();
		initializeJTextAreaPanel();
		initializeQueuePanel();
		initializeJTable();
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
	


	private void startWithPersistentVersion() {
		 try {
		      ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("jukeboxSave.ser"));
		      ourUsers = (UserDatabase) inFile.readObject();
		      ourSongs = (SongLibrary) inFile.readObject();
		      curUser = (User) inFile.readObject();
		      ourQueue = (Queue<Song>) inFile.readObject();
		      userStatus = (boolean) inFile.readObject();
		      myRest = (Resetter) inFile.readObject();
		      inFile.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    } catch (ClassNotFoundException e) {
		      e.printStackTrace();
		    }
		
	}


	private void initializeJTable() {
		model = ourSongs;
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(rowSorter);
		scrollPane.setLocation(430, 40);
		scrollPane.setSize(400, 530);
		this.add(scrollPane);
		tableLabel = new JLabel("Select a song from this Jukebox");
		tableLabel.setLocation(430, 10);
		tableLabel.setSize(325, 30);
		this.add(tableLabel);
		ButtonListener buttonListener = new ButtonListener();
		playButton = new JButton("Play");
		this.add(playButton);
		playButton.setLocation(350, 120);
		playButton.setSize(65, 30);
		playButton.addActionListener(buttonListener);
				
				
				
//		song1 = new JButton("Select Song 1");
//		this.add(song1);
//		song1.setLocation(500, 20);
//		song1.setSize(120, 25);
//		song1.addActionListener(buttonListener);
	}

	private void initializeQueuePanel()
	{
		
		//Font myFont = new Font("Courier", Font.BOLD, 36);
		queueArea = new JTextArea("");
		queueArea.setLocation(15, 40);
		queueArea.setSize(325, 350);
		this.add(queueArea);
		if (ourQueue != null && !ourQueue.isEmpty())
		{
			queueArea.setText(printQueue());
		}
		
		queueAreaLabel = new JLabel("Play List (Song at the top is currently playing)");
		queueAreaLabel.setLocation(15, 10);
		queueAreaLabel.setSize(325, 30);
		this.add(queueAreaLabel);
	}
	
	private String printQueue()
	{
		Iterator qIterator = ourQueue.iterator();
		String result = "";
		while (qIterator.hasNext())
		{
			Song temp = (Song) qIterator.next();
			result += temp.getname() + " by " + temp.getartist() + " is " + temp.gettime() + " seconds\n";
 		}
		return result;
		
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
		accountName.setLocation(25, 10);
		accountName.setSize(100, 30);
		textAreaPanel.add(textEditorAcc);
		textEditorAcc.setLocation(125, 10);
		textEditorAcc.setSize(150, 30);

		textAreaPanel.add(password);
		password.setLocation(25, 50);
		password.setSize(100, 30);
		textAreaPanel.add(textEditorPass);
		textEditorPass.setLocation(125, 50);
		textEditorPass.setSize(150, 30);

		ButtonListener1 buttonListener1 = new ButtonListener1();
		login = new JButton("Login");
		textAreaPanel.add(login);
		login.setLocation(155, 100);
		login.setSize(120, 40);
		login.addActionListener(buttonListener1);

		signOut = new JButton("Sign Out");
		textAreaPanel.add(signOut);
		signOut.setLocation(25, 100);
		signOut.setSize(120, 40);
		signOut.addActionListener(buttonListener1);

		
		textAreaPanel.add(status);
		status.setLocation(25, 150);
		status.setSize(300, 30);

		textAreaPanel.setBackground(Color.PINK);

		this.add(textAreaPanel);

		this.setLayout(null);
		textAreaPanel.setLocation(15, 400);
		textAreaPanel.setSize(325, 200);

//		ButtonListener buttonListener = new ButtonListener();
//		song1 = new JButton("Select Song 1");
//		this.add(song1);
//		song1.setLocation(500, 20);
//		song1.setSize(120, 25);
//		song1.addActionListener(buttonListener);
//
//		song2 = new JButton("Select Song 2");
//		this.add(song2);
//		song2.setLocation(500, 55);
//		song2.setSize(120, 25);
//		this.setBackground(Color.PINK);
//		song2.addActionListener(buttonListener);
		this.setBackground(Color.PINK);

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
			queueArea.setText(printQueue());
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
				int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
				String songName = (String) model.getValueAt(selectedRow, 0);
				songSelected = ourSongs.getSong(songName);
				

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
					status.setText(curUser.getname() + ": Available Plays: " + curUser.getCount() + " , " + "Available Credit: "
							+ format(curUser.getCredits()));

					if (ourQueue.isEmpty()) {
						ourQueue.add(songSelected);
						queueArea.setText(printQueue());
						EndOfSongListener waitForSongEnd = new WaitingForSongToEnd();
						SongPlayer.playFile(waitForSongEnd, ourQueue.peek().getlocation());
					} else {
						ourQueue.add(songSelected);
						queueArea.setText(printQueue());
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
					status.setText(userName + ": Available Plays: " + curUser.getCount() + " , " + "Available Credit: "
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

	public int close() {
		int choice = JOptionPane.showConfirmDialog(null, "Save Jukebox collection");
		if (choice == 0)
		{
		FileOutputStream bytesToDisk = null;
	    try {
	      bytesToDisk = new FileOutputStream("jukeboxSave.ser");
	      ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
	      outFile.writeObject(ourUsers);
	      outFile.writeObject(ourSongs);
	      outFile.writeObject(curUser);
	      outFile.writeObject(ourQueue);
	      outFile.writeObject(userStatus);
	      outFile.writeObject(myRest);
	      outFile.close();
	   } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	    e.printStackTrace();
	  }
		}
		return 0;
	}

}
