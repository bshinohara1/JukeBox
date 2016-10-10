package model;

public class User {

	int maxplays = 3;
	int beginCredits = 1500*60;
	String username;
	String pass;
	int credits;
	int count;
	
	public User(String username,String pass){
		this.pass=pass;
		this.username=username;
		count=maxplays;
		credits = beginCredits;
	}
	
	public int getCredits(){
		return credits;
	}
	
	public int getCount(){
		return count;
	}
	
	public void minusCredits(int sub){
		credits -= sub;
	}
	
	public void minusCount(){
		count--;
	}
	
	public boolean valid(){
		if(count==0)
			return false;
		return true;
	}
	
	public void reset(){
		count=maxplays;
	}

	public String getpass() {
		// TODO Auto-generated method stub
		return pass;
	}
	
}
