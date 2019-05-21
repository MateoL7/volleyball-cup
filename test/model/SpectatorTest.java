package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


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
	
	private Spectator s;
	private Spectator s2;
	
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
	
	private void setupScenary2() {
		s = new Spectator(id, firstName, lastName, email, gender, country, photo, birthday);
		s2 = new Spectator(id, firstName, lastName, email, gender, country, photo, birthday );
	}
	
	@Test
	public void testSpectator() {
		setupScenary1();
		s = new Spectator(id, firstName, lastName, email, gender, country, photo, birthday);
		assertNotNull("The builder is not working", s);
		assertTrue("Not the right value", s.getId().equalsIgnoreCase(id));
		assertTrue("Not the right value", s.getFirstName().equalsIgnoreCase(firstName));
		assertTrue("Not the right value", s.getLastName().equalsIgnoreCase(lastName));
		assertTrue("Not the right value", s.getEmail().equalsIgnoreCase(email));
		assertTrue("Not the right value", s.getGender().equalsIgnoreCase(gender));
		assertTrue("Not the right value", s.getCountry().equalsIgnoreCase(country));
		assertTrue("Not the right value", s.getPhoto().equalsIgnoreCase(photo));
		assertTrue("Not the right value", s.getBirthday().equalsIgnoreCase(birthday));
	}
	
	@Test
	public void testCompareTo() {
		setupScenary1();
		setupScenary2();
		assertTrue("Not comparing correctly", s.compareTo(s2) == 0);
		
	}

}
