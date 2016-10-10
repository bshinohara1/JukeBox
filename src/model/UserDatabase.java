package model;

import java.util.HashMap;

public class UserDatabase {

	HashMap<String, User> database;

	public UserDatabase() {
		database = new HashMap<String, User>();
		adduser(new User("Chris", "1"));
		adduser(new User("Devon", "22"));
		adduser(new User("River", "333"));
		adduser(new User("Ryan", "4444"));
	}

	public boolean contain(String name) {
		return database.containsKey(name);
	}

	public void adduser(User newuser) {
		if (!contain(newuser.username))
			database.put(newuser.username, newuser);
	}
	
	public void reuser(User newuser) {
		if (contain(newuser.username))
			database.remove(newuser.username);
	}
	
	public int size(){
		return database.size();
	}
	
	public boolean validator(String loginuser, String loginpass){
			if(database.containsKey(loginuser) && database.get(loginuser).getpass().equals(loginpass))
				return true;
		return false;
	}
	
	public void removeuser(User newuser) {
		if (contain(newuser.username))
			database.remove(newuser.username);
	}
	
	public User getuser(String loginname){
		return database.get(loginname);
	}

	public HashMap getlist() {
		// TODO Auto-generated method stub
		return database;
	}
	
}
