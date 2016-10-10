package model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

import java.util.Iterator;

public class Resetter {
	
	LocalDateTime dates;
	LocalDate cur;
	UserDatabase users;
	SongLibrary songs;
	
	
	public Resetter(UserDatabase a, SongLibrary b){
		users = a;
		songs = b;
		cur = LocalDate.now();
		dates = cur.atStartOfDay();
	}
	
	public void checkday(){
		cur = LocalDate.now().plusDays(1);
		if(dates.compareTo(cur.atStartOfDay())<0){
			dates = cur.atStartOfDay();
			resetsongs();
			resetusercount();
			
		}
	}
	
	private void resetusercount() {
		// TODO Auto-generated method stub
		HashMap userlist = users.getlist();
		Collection<User> holder = userlist.values();
		for (Iterator<User> iterator = holder.iterator(); iterator.hasNext();) {
			  User value = iterator.next(); 
			  value.reset();
			}
	
	}
	
	private void resetsongs() {
		// TODO Auto-generated method stub
		HashMap songlist = songs.getlist();
		Collection<Song> holder = songlist.values();
		for (Iterator<Song> iterator = holder.iterator(); iterator.hasNext();) {
			  Song value = iterator.next(); 
			  value.setplayed();
			}
		
	}

}
