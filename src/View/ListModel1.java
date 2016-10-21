package View;
import java.util.Vector;
import java.io.Serializable;
import java.util.Enumeration;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.swing.event.*;

//Author Adam Shinohara and Benjamin Shinohara
//Class that extends the AbstractListModel so that it can used to make 
//use of vectors to make a list model.  Also when extending the abstract list model,
//it implements the serializable and listmodel.
public class ListModel1<E> extends AbstractListModel<E> {

	private Vector<E> delegate = new Vector<E>();

	//adds an element to the vector
	 public void addElement(E element) {
	        int index = delegate.size();
	        delegate.addElement(element);
	        fireIntervalAdded(this, index, index);
	    }
	 
	 //removes an element from the vector at a certain index.
	 public E remove(int index) {
	        E rv = delegate.elementAt(index);
	        delegate.removeElementAt(index);
	        fireIntervalRemoved(this, index, index);
	        return rv;
	    }
	

	 //gets the element from the vector at a certrain index.
	@Override
	public E getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return delegate.elementAt(arg0);
	}

	//returns the size of the vector.
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return delegate.size();
	}

	

}
