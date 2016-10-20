//Author: Adam and Ben Shinohara
//This class is composed of the Junit tests for the model view

package Tests;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.junit.Test;

import model.Checker;
import model.Resetter;
import model.Song;
import model.SongLibrary;
import model.User;
import model.UserDatabase;

public class UserDatabasetest {

	//Tests that the userdatabase works.
	@Test
	public void test() {
		UserDatabase a = null; 
		a = a.getDatabase();
		assertEquals(a.size(),4);
		assertTrue(a.contain("Chris"));
		assertTrue(a.validator("Chris", "1"));
		assertFalse(a.validator("Chris", "11"));
	}
	
	//Goes over a series of tests including the checker and the reseter as well as the song library,
	//song,user, and userdatabase to ensure that the code works as intended.
	@Test
	public void test1() {
		UserDatabase c = null;
		c = c.getDatabase();
		SongLibrary b = null;
		b = b.getSongLibrary();
		Song hold = b.getSong("StarStrukk");
		Resetter a = null;
		a = a.getResetter(c, b);
		Checker checks = new Checker();
		assertTrue(c.contain("Chris"));
		User one = c.getuser("Chris");
		assertEquals(3,one.getCount());
		assertEquals(checks.check(one, hold),1);
		one.minusCount();
		assertEquals(one.getCredits(),1500*60);
		one.minusCredits(1000);
		assertEquals(one.getCredits(),1500*60-1000);
		assertEquals(2,one.getCount());
		one.minusCount();
		assertEquals(checks.check(one, hold),1);
		one.minusCount();
		assertEquals(checks.check(one, hold),-1);
		assertTrue(b.contains("StarStrukk"));
		assertEquals(hold.canbeplayed(),true);
		assertEquals(hold.getname(),"StarStrukk");
		assertEquals(hold.getartist(),"3OH!3");
		assertEquals(hold.gettime(),184);
		hold.play();
		hold.play();
		hold.play();
		assertEquals(checks.check(one, hold),-3);
		assertFalse(hold.canbeplayed());
		LocalDate cur;
		cur = LocalDate.now();
		a.dates = cur.atStartOfDay().minusDays(1);
		a.checkday();
		assertEquals(3,one.getCount());
		assertEquals(hold.canbeplayed(),true);
		assertEquals(hold.getlocation(),"songfiles/stuff.mp3");
		c.removeuser(c.getuser("Ryan"));
		assertEquals(c.size(),3);
		assertEquals(3,b.getColumnCount());
		assertEquals(String.class,b.getColumnClass(0));
		assertEquals(String.class,b.getColumnClass(1));
		assertEquals(Integer.class,b.getColumnClass(2));
		assertEquals(10,b.getRowCount());
		assertEquals("Song Title",b.getColumnName(0));
		assertEquals("Artist",b.getColumnName(1));
		assertEquals("Duration",b.getColumnName(2));
		assertFalse(b.isCellEditable(0, 0));
		Collection<Song> here = b.getSongs();
		String temps = (String) b.getValueAt(0, 0);
		String temp1 = (String) b.getValueAt(0, 1);
		int temp3 =  (int) b.getValueAt(0, 2);
	}
	
	

}
