/**
 *
 * @author Xiaobei Zhao
 * @version 1.0
 * @since  Oct 2016, JDK 8
 *
 * purpose: The AddressBookApplication program implements 
 * an application that operate the entries in the  Addressbook 
*/
package address;

import java.awt.EventQueue;
import java.sql.SQLException;

import address.gui.MainWindow;

public class AddressBookApplication {
	  /**
	   * Create the application.
	   * @param args Unused.
	   * @return Nothing.
	   * @throws SQLException 
	   * @throws ClassNotFoundException 
	   */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	    EventQueue.invokeLater(new Runnable() {
			public void run() {
			try {
				MainWindow window = new MainWindow();
				window.getFrame().setVisible(true);
	           } catch (Exception e) {
	           e.printStackTrace();
	           }
	       }
	    });

	}
}
