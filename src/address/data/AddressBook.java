/**
 *
 * @author Xiaobei Zhao
 * @version 1.0
 * @since  Oct 2016, JDK 8
 *
 * purpose: This class is used to operate the addressEntryList and the data in file
 * including readfromfile, writetofile, sorted, add, remove, search, list.
*/
package address.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.DefaultListModel;


public class AddressBook {
	
	/**
	 * This is a ArrayList which is used to store every AddressEntry information.
	 */
	ArrayList<AddressEntry> addressEntryList = new ArrayList<AddressEntry>();
	
	/**
	 * This is a DefaultListModel which is used to store every AddressEntry information.
	 */
	DefaultListModel<AddressEntry> myaddressEntryListModel = new DefaultListModel<AddressEntry>();
	
	/**
	 * This is the getter method of addressEntryList
	 * @return
	 */
	public ArrayList<AddressEntry> getAddressEntryList() {
		return addressEntryList;
	}
	
	/**
	 * This is the setter method of addressEntryList
	 * @param addressEntryList
	 */
	public void setAddressEntryList(ArrayList<AddressEntry> addressEntryList) {
		this.addressEntryList = addressEntryList;
	}
	
	/**
	 * This is the getter method of myaddressEntryListModel
	 * @return
	 */
	public DefaultListModel<AddressEntry> getMyaddressEntryListModel() {
		return myaddressEntryListModel;
	}

	/**
	 * This is the setter method of myaddressEntryListModel
	 * @param myaddressEntryListModel
	 */
	public void setMyaddressEntryListModel(DefaultListModel<AddressEntry> myaddressEntryListModel) {
		this.myaddressEntryListModel = myaddressEntryListModel;
	}

	/**
	 * @override
	 * <p>by overriding the compare method of class comparator</br>
	 * give the rule of how entries sorted -- in alphabetic order by the person's last name
	 * ignore case
	 */
	Comparator<AddressEntry> comparator = new Comparator<AddressEntry>(){
		public int compare(AddressEntry aE1,AddressEntry aE2){
			return aE1.getName().getLastName().toLowerCase().compareTo(aE2.getName().getLastName().toLowerCase());
		}
		
	};

	/**
	 * <p>This method is used to read each AddressEntry from the database</br>
	 * Then add to the addressEntryList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void readFromDB() throws ClassNotFoundException, SQLException{
		Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions
        //check Oracle documentation online
		//Or could do DriverManager.registerDriver (new oracle.jdbc.OracleDriver());

		//Connect to the database
		//generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
		//were given by our CS tech in an email ---THIS WI			LL BE DIFFERENT
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:yu8763/pMEDqZON@mcsdb1.sci.csueastbay.edu:1521/mcsdb1");

	//Create a Statement
	Statement stmt = conn.createStatement ();
	ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSBOOKENTRYTABLE");
	while (rset.next ())
	{ 		//get next row of table returned
			Name name = new Name(rset.getString("FIRSTNAME"),rset.getString("LASTNAME"));
			System.out.println("Name :"+name);
			Address address = new Address(rset.getString("STREET"),rset.getString("CITY"),rset.getString("STATE"),rset.getInt("ZIP"));
			AddressEntry aE = new AddressEntry(rset.getInt("ID"),name,address,rset.getString("PHONE"),rset.getString("EMAIL"));
			addressEntryList.add(aE);
			System.out.println(aE.toString());
			
	}
	this.sorted();
//	for(int i = 0; i<addressEntryList.size(); i++)
//	    {  this.myaddressEntryListModel.add(i, this.addressEntryList.get(i)); }
               
	}
	
	/**
	 * <p>This method is used to add a new entry to the database</br>
	 * @param aE
	 * @return aE1
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public AddressEntry addNewToDB(AddressEntry aE) throws ClassNotFoundException, SQLException{
		Class.forName ("oracle.jdbc.OracleDriver"); 
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:yu8763/pMEDqZON@mcsdb1.sci.csueastbay.edu:1521/mcsdb1");

		//Create a Statement
	Statement stmt = conn.createStatement ();
	String firstName = aE.getName().getFirstName();
	String lastName = aE.getName().getLastName();
	String street = aE.getAddress().getStreet();
	String city = aE.getAddress().getCity();
	String state = aE.getAddress().getState();
	int zip = aE.getAddress().getZip();
	String phone = aE.getPhone();
	String email =aE.getEmail();
	String query = "INSERT INTO ADDRESSBOOKENTRYTABLE VALUES(address_seq.NEXTVAL,'"+firstName+"','"+lastName+"','"+street+"','"+city+"','"+state+"','"+zip+"','"+phone+"','"+email+"')";
	//	"'"+id+"','"+firstName+"','"+lastName+"','"+street+"','"+city+"','"+state+"','"+zip+"','"+phone+"','"+email+"'";
	System.out.println(query);
	//  stmt.executeQuery("INSERT INTO ADDRESSBOOKENTRYTABLE VALUES(query)");
	stmt.executeQuery(query);
	
	ResultSet rset = stmt.executeQuery("SELECT ID FROM ADDRESSBOOKENTRYTABLE WHERE FIRSTNAME='"+firstName+"' AND LASTNAME='"+lastName+"'");
	AddressEntry aE1 = new AddressEntry();
	if (rset.next ()) {
	System.out.println("ID: "+rset.getInt("ID")+"========");
	// aE1.AddressEntry(rset.getInt("ID"), aE.getName(), aE.getAddress(), aE.getPhone(), aE.getEmail());
	 aE1.setID(rset.getInt("ID"));
	 aE1.setName(aE.getName());
	 aE1.setAddress(aE.getAddress());
	 aE1.setPhone(aE.getPhone());
	 aE1.setEmail(aE.getEmail());
	}
	return aE1;
}
	
	/**
	 * <p>This method is used to update the entry and write to the database</br>
	 * @param aE
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateInDB(AddressEntry aE) throws ClassNotFoundException, SQLException{
		Class.forName ("oracle.jdbc.OracleDriver"); 
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:yu8763/pMEDqZON@mcsdb1.sci.csueastbay.edu:1521/mcsdb1");

	//Create a Statement
	Statement stmt = conn.createStatement ();
	int id = aE.getID();
	String firstName = aE.getName().getFirstName();
	String lastName = aE.getName().getLastName();
	String street = aE.getAddress().getStreet();
	String city = aE.getAddress().getCity();
	String state = aE.getAddress().getState();
	int zip = aE.getAddress().getZip();
	String phone = aE.getPhone();
	String email =aE.getEmail();
	String query = "INSERT INTO ADDRESSBOOKENTRYTABLE VALUES('"+id+"','"+firstName+"','"+lastName+"','"+street+"','"+city+"','"+state+"','"+zip+"','"+phone+"','"+email+"')";
	//			"'"+id+"','"+firstName+"','"+lastName+"','"+street+"','"+city+"','"+state+"','"+zip+"','"+phone+"','"+email+"'";
	System.out.println(query);
	//    stmt.executeQuery("INSERT INTO ADDRESSBOOKENTRYTABLE VALUES(query)");
	stmt.executeQuery(query);
}
	
	/**
	 * <p>This method is used to delete the entry from the database</br>
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deleteFromDB(int id) throws ClassNotFoundException, SQLException{
	Class.forName ("oracle.jdbc.OracleDriver"); 
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:yu8763/pMEDqZON@mcsdb1.sci.csueastbay.edu:1521/mcsdb1");

	//Create a Statement
	Statement stmt = conn.createStatement ();
	stmt.executeQuery("DELETE FROM ADDRESSBOOKENTRYTABLE WHERE ID="+id);
		
	}
	
	/**
	 * <p>This method call the sort method in class {@link Collections}</br>
	 * And use the override comparator which is defined in this class 
	 * @param null
	 * @return nothing
	 */
	public void sorted(){

		Collections.sort(addressEntryList,comparator);
		this.myaddressEntryListModel.clear();
		for(int i = 0; i<addressEntryList.size(); i++)
	    {  this.myaddressEntryListModel.add(i, this.addressEntryList.get(i)); }
		
	}
	
	/**
	 * <p>This method is used to search</br>
	 * searhedList is An ArrayList that stores the result of AddressEntry which lastname is start with startOf_lastName</br>
	 * using the method starsWith in the class {@link String} to compare
	 * If the lastname of an AddressEntry starts with the startOf_lastName</br>
	 * add this entry to the searchList
	 * @param startOf_lastName 
	 * @return searhedList
	 */
	public List<AddressEntry> search(String startOf_lastName){
		sorted();
		List<AddressEntry> searchedList = new ArrayList<AddressEntry>();
		for(int i = 0;i < addressEntryList.size();i++){
			if(addressEntryList.get(i).getName().getLastName().toLowerCase().startsWith(startOf_lastName.toLowerCase())){
				searchedList.add(addressEntryList.get(i));
			}
		}
		return searchedList;
		}
		
	/**
	 * <p>This method is used to remove an AddressEntry from the addressEntryList</br>
	 * finList is an ArrayList stores the results of those eligible entries (passed by the method removal in the class {@link Menu})</br>
	 * numOf_Entry is the number of entry in the findList which need to remove</br>
	 * Use the default method remove of the {@link ArrayList}
	 * @param findList
	 * @param numOf_Entry
	 * @return nothing
	 */
	public void remove(List<AddressEntry> findList, int numOf_Entry){
		this.myaddressEntryListModel.remove(numOf_Entry);
		addressEntryList.remove(findList.get(numOf_Entry));
	}
	
	/**
	 * <p>This method is used to add a new AddressEntry to the addressEntryList</br>
	 * Use the add method of {@link ArrayList} 
	 * @param addressEntry
	 * @return nothing
	 */
	public void add(AddressEntry addressEntry){
		addressEntryList.add(addressEntry);
		this.myaddressEntryListModel.add(myaddressEntryListModel.size(),addressEntryList.get(addressEntryList.size() - 1));
	}


}
