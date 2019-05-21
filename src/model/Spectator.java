package model;

public class Spectator implements Comparable<Spectator> {

	private Spectator right;
	private Spectator left;
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String country;
	private String photo;
	private String birthday;
	
	public Spectator(String id, String firstName, String lastName, String email, String gender, String country, String photo, String birthday) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.photo = photo;
		this.birthday = birthday;
		this.gender = gender;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the right
	 */
	public Spectator getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Spectator right) {
		this.right = right;
	}

	/**
	 * @return the left
	 */
	public Spectator getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Spectator left) {
		this.left = left;
	}
	/**
	 * This is an Override of the method compareTo
	 * which helps to compare two different objects
	 */
	@Override
	public int compareTo(Spectator o) {
		if(id.compareTo(o.getId()) > 0) {
			return 1;
		}else if(id.compareTo(o.getId()) < 0) {
			return -1;
		}else {
			return 0;
		}
	}	
}
