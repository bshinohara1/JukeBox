package model;

/*
 * This is the user class.  It is used to keep track of the users information.  Everything from their username to their 
 * credits left to play the song is stored here.
 */
public class User {

	int maxplays = 3;
	int beginCredits = 1500*60;
	String username;
	String pass;
	int credits;
	int count;
	
	//default constuctor for User
	public User(String username,String pass){
		this.pass=pass;
		this.username=username;
		count=maxplays;
		credits = beginCredits;
	}
	
	//Returns the amount of credit the user has.
	public int getCredits(){
		return credits;
	}
	
	//returns how many more times a user can play a song 
	public int getCount(){
		return count;
	}
	
	//reduces the credits based on an int.
	public void minusCredits(int sub){
		credits -= sub;
	}
	
	//reduces the count of the user by 1
	public void minusCount(){
		count--;
	}
	
	//determines whether the user can play a song.
	public boolean valid(){
		if(count==0)
			return false;
		return true;
	}
	
	//resets the number of plays a user has left
	public void reset(){
		count=maxplays;
	}

	//returns the password of the user
	public String getpass() {
		// TODO Auto-generated method stub
		return pass;
	}
	
}
