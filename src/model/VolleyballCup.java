package model;
import java.io.*;


public class VolleyballCup {

	private Spectator root;
	
	public VolleyballCup() {
		
	}
	
	public void loadInfo(String path) throws IOException {
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
			addSpectator(id, fn, ln, em, gn, cn, ph, bd);
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
	
	public Spectator searchSpectator(String id) {
		Spectator found = null;
		Spectator current = root;
		while(current != null) {
			if(current.getId().equals(id)) {
				found = current;
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
}
