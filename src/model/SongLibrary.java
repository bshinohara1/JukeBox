package model;

import java.util.Collection;
import java.util.HashMap;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/*
 * Author: Adam and Ben Shinohara
 * This is a class that makes a list of songs and stores them in a hashmap in order to retrieve the 
 * correct song.
 */
public class SongLibrary implements TableModel{

	static SongLibrary Songlib = null;
	HashMap<String,Song> lib ;
	Object[] list;
	
	private SongLibrary(){
		lib = new HashMap<String , Song>();
		list = lib.values().toArray();
		in();
		
	}
	public static SongLibrary getSongLibrary(){
		if(Songlib == null)
			Songlib = new SongLibrary();
		return Songlib;
	}

	//adds the initial songs to the hashmap
	private void in() {
		// TODO Auto-generated method stub
		addsong(new Song("Danse Macabre","Kevin MacLeod", 34, "songfiles/DanseMacabreViolinHook.mp3"));
		addsong(new Song("Determined Tumbao","FreePlay Music",  20, "songfiles/DeterminedTumbao.mp3"));
		addsong(new Song("Flute","Sun Microsystems",  5 , "songfiles/flute.aif"));
		addsong(new Song("Loping Sting","Kevin MacLeod",  4, "songfiles/LopingSting.mp3"));
		addsong(new Song("Space Music","Unknown",  6, "songfiles/spacemusic.au"));
		addsong(new Song("Swing Cheese","FreePlay Music",  15, "songfiles/SwingCheese.mp3"));
		addsong(new Song("Tada","Microsoft",  2, "songfiles/tada.wav"));
		addsong(new Song("The Curtain Rises","Kevin MacLeod",  28, "songfiles/TheCurtainRises.mp3"));
		addsong(new Song("Untameable Fire","Pierre Langer",  282, "songfiles/UntameableFire.mp3"));
		addsong(new Song("StarStrukk","3OH!3",  184, "songfiles/stuff.mp3"));
	}

	//function that add a song to the hashmap if it isn't already in the list
	private void addsong(Song song) {
		// TODO Auto-generated method stub
		if(!lib.containsKey(song.name)){
			lib.put(song.name,song);
		}
		list=lib.values().toArray();
	}
	
	//This will remove a song given the song name.
	private void removesong(String songname) {
		// TODO Auto-generated method stub
		if(lib.containsKey(songname)){
			lib.remove(songname);
		}
		list= lib.values().toArray();
	}
	
	//Will return a boolean telling if the song is contained in the hashmap.
	public boolean contains(String songname){
		return lib.containsKey(songname);
	}
	
	//This will return the song at the hashmap based on the songname given
	public Song getSong(String songname){
		return lib.get(songname);
	}

	//this will return the hashmap so that it can be used later.
	public HashMap<String,Song> getlist() {
		// TODO Auto-generated method stub
		return lib;
	}
	
	//this will return a collection of all the songs
	public Collection<Song> getSongs(){
		return lib.values();
	}
	
	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex == 2)
			return Integer.class;
		return String.class;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex == 0)
		      return "Song Title";
		    if (columnIndex == 1)
		      return "Artist";
		    return "Duration";
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Song temp = (Song) list[rowIndex];
		if(columnIndex == 0)
			return temp.name;
		if(columnIndex == 1)
			return temp.artist;
		return temp.time;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
}
