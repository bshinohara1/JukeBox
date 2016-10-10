import static org.junit.Assert.*;

import org.junit.Test;

import model.UserDatabase;

public class UserDatabasetest {

	@Test
	public void test() {
		UserDatabase a = new UserDatabase();
		assertEquals(a.size(),4);
	}

}
