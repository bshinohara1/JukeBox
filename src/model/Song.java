package model;

import java.time.LocalDate;

public class Song {
	String name;
	String artist;
	int time;
	int played;
	String loc;
	int maxplays =3;
	
	public Song(String name, String artist, int time, String location){
		this.name=name;
		this.artist=artist;
		this.time=time;
		loc = location;
		played = maxplays;
	}
	
	public String getname(){
		return name;
	}
	
	public String getartist(){
		return artist;
	}
	
	public int gettime(){
		return time;
	}
	
	public String getlocation(){
		return loc;
	}
	
	public void play(){
		played--;
	}
	
	public void setplayed(){
		played =maxplays;
	}
	
	public boolean canbeplayed(){
		if(played ==0)
			return false;
		return true;
	}

}
