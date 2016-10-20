package model;

import java.io.Serializable;
import java.util.HashMap;
/*
 * Author: Adam and Ben Shinohara
 * This is the UserDatabase class.  It stores a hashmap of users in order to store the users.
 */
public class UserDatabase implements Serializable {

	static UserDatabase Userlist = null;
	HashMap<String, User> database;

	//default constuctor for the user database.
	private UserDatabase() {
		database = new HashMap<String, User>();
		addf();
		
	}
	
	//Function that checks and see if the user database has been made.  If
	//it has been made it returns it or it will make a new database and return it.
	public static UserDatabase getDatabase(){
		if(Userlist == null)
			Userlist = new UserDatabase();
		return Userlist;
	}

	//initial adding of users.
	private void addf() {
		// TODO Auto-generated method stub
		adduser(new User("Chris", "1"));
		adduser(new User("Devon", "22"));
		adduser(new User("River", "333"));
		adduser(new User("Ryan", "4444"));
	}

	//checks to see if the user is in the list of users based on username
	public boolean contain(String name) {
		return database.containsKey(name);
	}

	//adds a user to the hashmap if it hasn't already been added.
	public void adduser(User newuser) {
		if (!contain(newuser.username))
			database.put(newuser.username, newuser);
	}
	
	
	//returns the size of the hashmap to detemine how many users there are.
	public int size(){
		return database.size();
	}
	
	//will return a boolean to see if the username and password match.
	public boolean validator(String loginuser, String loginpass){
			if(database.containsKey(loginuser) && database.get(loginuser).getpass().equals(loginpass))
				return true;
		return false;
	}
	
	//will remove a user from the hashmap.
	public void removeuser(User newuser) {
		if (contain(newuser.username))
			database.remove(newuser.username);
	}
	
	//will return the user based upon the login name.
	public User getuser(String loginname){
		return database.get(loginname);
	}

	//will return the 
	public HashMap<String,User> getlist() {
		// TODO Auto-generated method stub
		return database;
	}
	
}
