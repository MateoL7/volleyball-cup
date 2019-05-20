package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SpectatorTest {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String country;
	private String photo;
	private String birthday;
	
	private void setupScenary1() {
		id = "78-9883409";
		firstName = "Roldan";
		lastName = "Erricker";
		email = "rerricker0@nyu.edu";
		country = "China";
		photo = "https://robohash.org/voluptatemexpeditased.jpg?size=50x50&set=set1";
		birthday = "14/12/2007";
		gender = "Male";
	}
	
	@Test
	public void testSpectator() {
		setupScenary1();
		Spectator p = new Spectator(id, firstName, lastName, email, gender, country, photo, birthday);
		assertNotNull("The builder is not working", p);
		assertTrue("Not the right value", p.getId().equalsIgnoreCase(id));
		assertTrue("Not the right value", p.getFirstName().equalsIgnoreCase(firstName));
		assertTrue("Not the right value", p.getLastName().equalsIgnoreCase(lastName));
		assertTrue("Not the right value", p.getEmail().equalsIgnoreCase(email));
		assertTrue("Not the right value", p.getGender().equalsIgnoreCase(gender));
		assertTrue("Not the right value", p.getCountry().equalsIgnoreCase(country));
		assertTrue("Not the right value", p.getPhoto().equalsIgnoreCase(photo));
		assertTrue("Not the right value", p.getBirthday().equalsIgnoreCase(birthday));
	}

}
