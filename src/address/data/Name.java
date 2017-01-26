package address.data;

public class Name {
	/**
	 *Identifying the FirstName of the AddressEntry
	 */
	private String firstName;
	/**
	 *Identifying the LastName of the AddressEntry
	 */
	private String lastName;
	
	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/**
	 * This is the getter method of FirstName
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * This is the setter method of FirstName
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * This is the getter method of LastName
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * This is the setter method of LastName
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

}
