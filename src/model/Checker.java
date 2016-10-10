package model;

public class Checker {

	public Checker(){
		
	}
	
	public int check(User user, Song song){
		if(user.valid()&&song.canbeplayed()&&song.gettime()<user.getCredits())
			return 1;
		if(!user.valid()&&!song.canbeplayed())
			return -3;
		if(song.canbeplayed())
			return -1;
		return -2;
	}
}
