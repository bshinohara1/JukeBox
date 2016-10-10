import static org.junit.Assert.*;

import org.junit.Test;

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
		Resetter a = new Resetter();
		
	}

}
