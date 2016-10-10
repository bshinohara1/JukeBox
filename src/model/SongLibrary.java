package model;

import java.util.HashMap;

public class SongLibrary {

	HashMap<String,Song> lib ;
	
	public SongLibrary(){
		lib = new HashMap<String , Song>();
		addsong(new Song("Danse Macabre","Kevin MacLeod", 34, "songfiles/DanseMacabreViolinHook.mp3"));
		addsong(new Song("Determined Tumbao","FreePlay Music",  20, "songfiles/DeterminedTumbao.mp3"));
		addsong(new Song("Flute","Sun Microsystems",  5 , "songfiles/flute.aif"));
		addsong(new Song("Loping Sting","Kevin MacLeod",  4, "songfiles/LopingSting.mp3"));
		addsong(new Song("Space Music","Unknown",  6, "songfiles/spacemusinc.au"));
		addsong(new Song("Swing Cheese","FreePlay Music",  15, "songfiles/SwingCheese.mp3"));
		addsong(new Song("Tada","Microsoft",  2, "songfiles/tada.wav"));
		addsong(new Song("The Curtain Rises","Kevin MacLeod",  28, "songfiles/TheCurtainRises.mp3"));
		addsong(new Song("Untameable Fire","Pierre Langer",  282, "songfiles/UntameableFire.mp3"));
		addsong(new Song("stuff","3OH!3",  184, "songfiles/stuff.mp3"));
	}

	private void addsong(Song song) {
		// TODO Auto-generated method stub
		if(!lib.containsKey(song.name)){
			lib.put(song.name,song);
		}
	}
	
	private void removesong(String songname) {
		// TODO Auto-generated method stub
		if(lib.containsKey(songname)){
			lib.remove(songname);
		}
	}
	
	public boolean contains(String songname){
		return lib.containsKey(songname);
	}
	
	public Song getSong(String songname){
		return lib.get(songname);
	}

	public HashMap getlist() {
		// TODO Auto-generated method stub
		return lib;
	}
	
	
}
