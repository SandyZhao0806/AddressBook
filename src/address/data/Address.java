package address.data;

public class Address {
	/**
	 *Identifying the street of the AddressEntry
	 */
	private String street;
	/**
	 *Identifying the city of the AddressEntry
	 */
	private String city;
	/**
	 *Identifying the state of the AddressEntry
	 */
	private String state;
	/**
	 *Identifying the zip of the AddressEntry
	 */
	private int zip;
	/**
	 * <p>The constructor of the class Address</p>
	 * using all the fields of class Address
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public Address(String street, String city, String state, int zip) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	/**
	 * This is the getter method of Street
	 * @return street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * This is the setter method of Street
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * This is the getter method of City
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * This is the setter method of City
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * This is the getter method of State
	 * @return state
	 */
	public String getState() {
		return state;
	}
	/**
	 * This is the setter method of State
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * This is the getter method of Zip
	 * @return zip
	 */
	public int  getZip() {
		return zip;
	}
	/**
	 * This is the setter method of Zip
	 * @param zip
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}
}
