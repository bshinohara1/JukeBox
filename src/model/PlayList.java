package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;


public class PlayList implements Serializable, ListModel {

	static PlayList curPlayList = null;
	private Queue<Song> ourQueue;
	
	// constuctor for the song library
	private PlayList(Queue<Song> theQueue) {
		ourQueue = theQueue;
		
	}

	// checks to see if the library has been made if not makes it and returns
	// it.
	public static PlayList getSongPlayList(Queue<Song> theQueue) {
		if (curPlayList == null)
			curPlayList = new PlayList(theQueue);
		return curPlayList;
	}
	
	public Queue<Song> getQueue()
	{
		return ourQueue;
	}
	public void setQueue(Queue<Song> theQueue)
	{
	    ourQueue = theQueue;
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getElementAt(int arg0) {
		Iterator qIterator = ourQueue.iterator();
		String result = "";
		int count = 0;
		while (qIterator.hasNext() && count < arg0) {
			if (count - 1 == arg0)
			{
				return qIterator.next();
			}
			qIterator.next();
		}

		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return ourQueue.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}
	
	
}
