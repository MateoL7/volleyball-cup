package model;
import java.io.*;


public class VolleyballCup {

	private Spectator root;
	private Participant first;

	public VolleyballCup() {

	}
	
	public Participant getFirst() {
		return first;
	}
	public void setFirst(Participant p) {
		first = p;
	}
	/**
	 * This method loads the information from the .csv file and creates
	 * the tree and the list using external methods
	 */
	public void loadInfo(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line = br.readLine();
		int counter = 0;
		while(line != null) {
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

			line = br.readLine();
		}
		br.close();
	}
	/**
	 * This method adds a new Spectator to the tree
	 */
	public void addSpectator(String id, String firstName, String lastName, String email, String gender, String country, String photo, String birthday) {
		Spectator p = new Spectator(id, firstName, lastName, email, gender, country, photo, birthday);
		if(root == null) {
			root = p;
		}else {
			Spectator current = root;
			boolean added = false;
			while(!added) {
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
	/**
	 * This method searches for the spectator (in the tree) that matches the id given by the user
	 */
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
	/**
	 * This method adds a new participant to the linked list
	 */
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
	/**
	 * This method searches for the participant (in the linked list) that matches the id given by the user
	 */
	public Participant searchingParticipant(String id) {
		Participant found = null;
		Participant current = first;
		boolean keep = true;
		if(first != null) {
			while(current.getNext() != first && keep) {
				if(current.getId().equals(id)) {
					found = current;
					keep = false;
				}

				current = current.getNext();
			}
		}
		return found;
	}

	public Participant createCountryList(String country) {
		Participant temp = first;
		Participant newFirst = null;
		while(temp.getNext() != first) {

			if(temp.getCountry().equalsIgnoreCase(country)) {
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

	/**
	 * Este metodo sirve para crear un arbol binario de un pais en especifico 
	 * y por ahora se activa en el metodod loadFile de volleyController
	 * no he tocado nada mas :)
	 */
	public Spectator createSpectatorTree(String country, String path) throws IOException {
		Spectator newTree = null;
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line = br.readLine();
		while(line != null) {
				String[] info = line.split(",");
				String id = info[0];
				String fn = info[1];
				String ln = info[2];
				String em = info[3];
				String gn = info[4];
				String cn = info[5];
				String ph = info[6];
				String bd = info[7];
				if(cn.equalsIgnoreCase(country)) {
					Spectator p = new Spectator(id, fn, ln, em, gn, cn, ph, bd);
					if(newTree == null) {
						newTree = p;
					}else {
						Spectator current = newTree;
						boolean added = false;
						while(!added) {
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
			line = br.readLine();
		}
		br.close();
		return newTree;
	}


}
