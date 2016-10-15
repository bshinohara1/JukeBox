package model;


/*
 * Author: Adam and Ben Shinohara
 * This is the class song.  Contain all the information that a song should have.
 */
public class Song {
	String name;
	String artist;
	int time;
	int played;
	String loc;
	int maxplays =3;
	
	//Constuctor that makes a song.
	public Song(String name, String artist, int time, String location){
		this.name=name;
		this.artist=artist;
		this.time=time;
		loc = location;
		played = maxplays;
	}
	
	//Returns the title or the name of a song
	public String getname(){
		return name;
	}
	
	//returns the artist of the song
	public String getartist(){
		return artist;
	}
	
	//returns the duration of a song
	public int gettime(){
		return time;
	}
	
	//returns where the song is stored on the computer so that it can be used to play later
	public String getlocation(){
		return loc;
	}
	
	//reduces the number of times a song can be played
	public void play(){
		played--;
	}
	
	//resets the number of times a song can be played
	public void setplayed(){
		played =maxplays;
	}
	
	//determines whether or not a song can be played again
	public boolean canbeplayed(){
		if(played ==0)
			return false;
		return true;
	}

}
