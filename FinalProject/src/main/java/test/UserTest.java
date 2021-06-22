package test;

import org.junit.Assert;
import org.junit.Test;

import dao.MySQLUserDAO;
import entities.User;
import exceptions.DAOException;

public class UserTest {
	@Test
	public void getUserTest() {
		try {
			User u = MySQLUserDAO.getUserById(1);
			Assert.assertNotNull(u);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertUserTest() {
		User u = new User();
		u.setEmail("some@email.com");
		u.setFirstName("Name1");
		u.setSurname("Surname1");
		u.setLastName("LastName1");
		u.setLogin("testlogin");
		u.setPassword("testPassword");
		
	}
	
	@Test
	public void getAllUsersTest() {
		try {
			MySQLUserDAO.getAllUsers();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
