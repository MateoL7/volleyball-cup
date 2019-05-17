package model;
import java.io.*;


public class VolleyballCup {

	private Spectator root;
	private Participant first;

	public VolleyballCup() {

	}

	public void loadInfo(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line = br.readLine();
		int counter = 0;
		while(line != null) {
			if(line.charAt(0) != '#' && line.charAt(0) != 'i') {
				String[] info = line.split(",");
				String id = info[0];
				String fn = info[1];
				String ln = info[2];
				String em = info[3];
				String gn = info[4];
				String cn = info[5];
				String ph = info[6];
				String bd = info[7];
				addSpectator(id, fn, ln, em, gn, cn, ph, bd);
				if(counter%2 == 0) {
					addParticipant(id, fn, ln, em, gn, cn, ph, bd);
				
				}
				counter++;
			}

			line = br.readLine();
		}
		System.out.println(root.getId());
		br.close();
	}

	public void addSpectator(String id, String firstName, String lastName, String email, String gender, String country, String photo, String birthday) {
		Spectator p = new Spectator(id, firstName, lastName, email, gender, country, photo, birthday);
		if(root == null) {
			root = p;
		}else {
			Spectator current = root;
			boolean added = false;
			while(added == false) {
				if(p.compareTo(current) > 0) {
					if(current.getRight() == null) {
						current.setRight(p);
						added = true;
					}else {
						current = current.getRight();
					}
				}
				else {
					if(current.getLeft() == null) {
						current.setLeft(p);
						added = true;
					}else {
						current = current.getLeft();
					}
				}
			}

		}
	}

	public Spectator searchingSpectator(String id) {
		Spectator found = null;
		Spectator current = root;
		boolean keep = true;
		while(current != null && keep) {
			if(current.getId().equals(id)) {
				found = current;
				keep = false;
			}
			else if(current.getId().compareTo(id) > 0){
				current = current.getLeft();
			}
			else if(current.getId().compareTo(id) < 0){
				current = current.getRight();
			}
		}
		return found;
	}
	public void addParticipant(String id, String firstName, String lastName, String email, String gender, String country, String photo, String birthday) {
		Participant p = new Participant(id, firstName, lastName, email, gender, country, photo, birthday);

		if(first != null) {

			Participant last = first.getPrev();

			last.setNext(p);
			first.setPrev(p);;
			p.setNext(first);
			p.setPrev(last);


		}
		else {
			first = p;
			first.setNext(p);
			first.setPrev(p);
		}
	}
	public Participant searchingParticipant(String id) {
		Participant found = null;
		Participant current = first;
		boolean keep = true;
		while(current.getNext() != first && keep) {
			if(current.getId().equals(id)) {
				found = current;
				keep = false;
			}

			current = current.getNext();
		}
		return found;
	}
	
   public Participant createCountryList(String country) {
	   Participant temp = first;
	   Participant newFirst = null;
	   while(temp.getNext() != first) {
		
		   if(temp.getCountry().equals(country)) {
			Participant p = new Participant(temp.getId(), temp.getFirstName(), temp.getLastName(), temp.getEmail(), temp.getGender(), temp.getCountry(), temp.getPhoto(), temp.getBirthday());

			if(newFirst != null) {

				Participant last = newFirst.getPrev();

				last.setNext(p);
				newFirst.setPrev(p);;
				p.setNext(newFirst);
				p.setPrev(last);


			}
			else {
				newFirst = p;
				newFirst.setNext(p);
				newFirst.setPrev(p);
			}
			
		   }
		   temp = temp.getNext();
	  
	  }
	/**   
	 * este codigo era para comprobar que funciona la creacion de la lista
	 * Este metodo se activa en el metodo loadFile de volleyController
	   Participant temp1 = newFirst;
	   while(temp1.getNext() != newFirst) {
		   System.out.println(temp1.getFirstName());
		   temp1 = temp1.getNext();
	   }
	  */
	   return newFirst;
   }
}
