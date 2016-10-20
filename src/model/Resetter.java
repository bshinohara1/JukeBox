package model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

import java.util.Iterator;
/*
 * Author: Adam and Ben Shinohara
 * Resetter is a class that will determine if the next day has happened and if so it will reset the song count and the user count so that
 * they have the ability to play the song again or play more songs.
 */
public class Resetter {
	
	static Resetter Resets = null;
	public LocalDateTime dates;
	LocalDate cur;
	UserDatabase users;
	SongLibrary songs;
	
	//local consuctor for the resetter, takes a UserDatabase and a Songlibrary for future use of resetting.
	private Resetter(UserDatabase a, SongLibrary b){
		users = a;
		songs = b;
		cur = LocalDate.now();
		dates = cur.atStartOfDay();
	}
	
	//if there is no resetter then it makes one and returns it otherwise it returns the current resetter.
	public static Resetter getResetter(UserDatabase a, SongLibrary b){
		if(Resets == null)
			Resets = new Resetter(a,b);
		return Resets;
	}
	
	//checks to see if the day has changed and if so it will reset the values in bot collections.
	public void checkday(){
		cur = LocalDate.now().plusDays(1);
		if(dates.compareTo(cur.atStartOfDay())<0){
			dates = cur.atStartOfDay();
			resetsongs();
			resetusercount();
			
		}
	}
	
	//Will reset the count for all users in the hashmap.
	private void resetusercount() {
		// TODO Auto-generated method stub
		HashMap<String,User> userlist = users.getlist();
		Collection<User> holder = userlist.values();
		for (Iterator<User> iterator = holder.iterator(); iterator.hasNext();) {
			  User value = iterator.next(); 
			  value.reset();
			}
	
	}
	
	//will reset the song play count for each song in the hashmap.
	private void resetsongs() {
		// TODO Auto-generated method stub
		HashMap<String,Song> songlist = songs.getlist();
		Collection<Song> holder = songlist.values();
		for (Iterator<Song> iterator = holder.iterator(); iterator.hasNext();) {
			  Song value = iterator.next(); 
			  value.setplayed();
			}
		
	}

}
