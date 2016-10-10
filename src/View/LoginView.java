package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class LoginView extends JPanel{
	
	private JButton song1;
	private JButton song2;
	private JButton signOut;
	private JButton login;
	private int height, width;
	private JLabel accountName;
	private JLabel password;
	private JLabel status;
	private JTextField textEditorAcc;
	private JTextField textEditorPass;

	public LoginView(int width, int height) {
		this.height = height;
		this.width = width;
		initializeJTextAreaPanel();
	}

	private void initializeJTextAreaPanel() {
		JPanel textAreaPanel = new JPanel();
		Font myFont = new Font("Courier", Font.BOLD, 36);
		textAreaPanel.setLayout(null);
		textAreaPanel.setFont(myFont);
		accountName = new JLabel("Account Name");
		password = new JLabel("Password");
		textEditorAcc = new JTextField("", 50);
		textEditorPass = new JTextField("", 50);
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
		
		login = new JButton("Login");
		textAreaPanel.add(login);
		login.setLocation(230, 100);
		login.setSize(120, 40);
		
		signOut = new JButton("Sign Out");
		textAreaPanel.add(signOut);
		signOut.setLocation(100, 100);
		signOut.setSize(120, 40);
		
		status = new JLabel("Status or something");
		textAreaPanel.add(status);
		status.setLocation(100, 150);
		status.setSize(300, 30);
		
		textAreaPanel.setBackground(Color.PINK);

		this.add(textAreaPanel);

		this.setLayout(null);
		textAreaPanel.setLocation(10, 100);
		textAreaPanel.setSize(400, 200);
		
		song1 = new JButton("Select Song 1");
		this.add(song1);
		song1.setLocation(20, 20);
		song1.setSize(120, 25);
		
		song2 = new JButton("Select Song 2");
		this.add(song2);
		song2.setLocation(20, 55);
		song2.setSize(120, 25);
		this.setBackground(Color.PINK);
		
		
	}

}
