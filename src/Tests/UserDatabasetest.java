//Author: Adam and Ben Shinohara
//This class is composed of the Junit tests for the model view

package Tests;
import static org.junit.Assert.*;

import java.time.LocalDate;

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
		Song hold = b.getSong("stuff");
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
		assertTrue(b.contains("stuff"));
		assertEquals(hold.canbeplayed(),true);
		assertEquals(hold.getname(),"stuff");
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
	}

}
