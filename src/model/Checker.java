package model;

/*
 * Class check will be used to see if the combination of the user and song can be played.
 */
public class Checker {

	//constuctor for checker.
	public Checker(){
		
	}
	
	//will take a user and a song and determine whether or not they can be played.
	//will also return different int values for reasons why they cant be played. 
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
