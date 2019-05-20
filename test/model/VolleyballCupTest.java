package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class VolleyballCupTest {

	private VolleyballCup vc;
	private Participant p;
	private Spectator s;
	
	public void setupScenary1(){
		vc = new VolleyballCup();
		p = new Participant("96-6406996","Ellie","Diggar","ediggar1@howstuffworks.com","Female","Panama","https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","7/19/1974");
		Participant p3 = new Participant("48-3107973","Mateo","Rodriguez","ediggar1@howstuffworks.com","Male","Peru","https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","10/20/1984");
		Participant p4 = new Participant("51-6289987","Sara","Lopez","ediggar1@howstuffworks.com","Female","Colombia","https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","5/10/1996");
		
		vc.setFirst(p);
		p.setPrev(p4);
		vc.getFirst().setNext(p3);
		vc.getFirst().getNext().setNext(p4);
		
	}
	public void setupScenary2() {
		vc = new VolleyballCup();
		s = new Spectator("96-6406996","Ellie","Diggar","ediggar1@howstuffworks.com","Female","Panama",
		"https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","7/19/1974");
		
		vc.addSpectator("48-3107973","Mateo","Rodriguez","ediggar1@howstuffworks.com","Male","Peru",
		"https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","10/20/1984");
		
		vc.addSpectator("96-6406996","Ellie","Diggar","ediggar1@howstuffworks.com","Female","Panama",
		"https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","7/19/1974");
		
		vc.addSpectator("51-6289987","Sara","Lopez","ediggar1@howstuffworks.com","Female","Colombia",
		"https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","5/10/1996");
	}
	@Test
	public void testAddParticipant() {
		setupScenary1();
		vc.addParticipant("49-3304903","Hadley","Glanester","ediggar1@howstuffworks.com","Male","Colombia","https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","5/10/1996");
		Participant proof = vc.getFirst().getPrev();
		assertTrue("Not at the end", proof.getId().equalsIgnoreCase("49-3304903"));
		
	}
	
	@Test
	public void testSearchParticipant() {
		setupScenary1();
		Participant searched = vc.searchingParticipant("96-6406996");
		
		assertNotNull("It is sending null",  searched);
		assertTrue("Not the same participant", searched.equals(p));
	}
	@Test
	public void testSearchSpectator() {
		setupScenary2();
		Spectator searched = vc.searchingSpectator("96-6406996");
		
		assertNotNull("It is sending null",  searched);
		assertTrue("Not the same spectator", searched.getId().equalsIgnoreCase(s.getId()));
	}

}
