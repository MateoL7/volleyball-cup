package model;

import org.junit.jupiter.api.Test;

public class VolleyballCupTest {

	private VolleyballCup vc;
	
	public void setupScenary1(){
		vc = new VolleyballCup();
		Participant p2 = new Participant("96-6406996","Ellie","Diggar","ediggar1@howstuffworks.com","Female","Panama","https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","7/19/1974");
		Participant p3 = new Participant("48-3107973","Mateo","Rodriguez","ediggar1@howstuffworks.com","Male","Peru","https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","10/20/1984");
		Participant p4 = new Participant("51-6289987","Sara","Lopez","ediggar1@howstuffworks.com","Female","Colombia","https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","5/10/1996");
		
		vc.setFirst(p2);
		vc.getFirst().setNext(p3);
		vc.getFirst().getNext().setNext(p4);
		
	}
	@Test
	public void testAddParticipant() {
		
		vc.addParticipant("49-3304903","Hadley","Glanester","ediggar1@howstuffworks.com","Male","Colombia","https://robohash.org/autemnumquamnam.bmp?size=50x50&set=set1","5/10/1996");
	}
	
	@Test
	public void testSearchParticipant() {
		
	}

}
