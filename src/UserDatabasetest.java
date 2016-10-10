import static org.junit.Assert.*;

import org.junit.Test;

import model.SongLibrary;
import model.UserDatabase;

public class UserDatabasetest {

	@Test
	public void test() {
		UserDatabase a = new UserDatabase();
		assertEquals(a.size(),4);
		assertTrue(a.contain("Chris"));
		assertTrue(a.validator("Chris", "1"));
	}
	@Test
	public void test1() {
		UserDatabase c = new UserDatabase();
		SongLibrary b = new SongLibrary();
		Resetter a = new Resetter(c,b);
		
	}

}
