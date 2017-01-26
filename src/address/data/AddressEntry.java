/**
 *
 * @author Xiaobei Zhao
 * @version 1.0
 * @since  Oct 2016, JDK 8
 *
 * purpose: This class is an entity class, all the fields of one person are defined in it. 
*/
package address.data;

public class AddressEntry {
	/**
	 *Identifying the ID of the AddressEntry
	 */
	private int ID;
	/**
	 *Identifying the name of the AddressEntry
	 */
	private Name name = new Name(null,null);
	/**
	 *Identifying the address of the AddressEntry
	 */
	private Address address = new Address(null,null,null,00000);
	/**
	 *Identifying the phone of the AddressEntry
	 */
	private String phone;
	/**
	 *Identifying the email of the AddressEntry
	 */
	private String email;
	
	
	/**
	 * <p>The constructor of the class AddressEntry</p>
	 */
	public AddressEntry() {
		super();
	}


	/**
	 * <p>The constructor of the class AddressEntry</p>
	 * using all the fields of class AddressEntry
	 * @param ID
	 * @param name
	 * @param address
	 * @param phone
	 * @param email
	 */
	public AddressEntry(int ID, Name name, Address address,
			String phone, String email) {
		super();
		this.ID = ID;
		this.name = name;
		this.address= address;
		this.phone = phone;
		this.email = email;
	}
	

	/**
	 * This is the getter method of ID
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * This is the setter method of ID
	 * @param ID
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * This is the getter method of name
	 * @return name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * This is the setter method of name
	 * @param name
	 */
	public void setName(Name name) {
		this.name = name;
	}

	/**
	 * This is the getter method of address
	 * @return address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * This is the setter method of address
	 * @param address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * This is the getter method of Phone
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * This is the setter method of Phone
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * This is the getter method of Email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * This is the setter method of Email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 *<p> This method is used to connect all the fields of an AddressEnty</br>
	 *use the "+" operation to connect the string</br>
	 *return a string
	 */
	public String toString(){
		return getID()+": "
			  +getName().getFirstName()+"  "
			  +getName().getLastName()+", "
			  +getAddress().getStreet()+", "
			  +getAddress().getCity()+", "
			  +getAddress().getState()+", "
			  +getAddress().getZip()+", "
			  +getPhone()+", "
			  +getEmail()+"\n";
	}

}
