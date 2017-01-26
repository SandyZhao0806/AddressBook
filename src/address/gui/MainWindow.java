package address.gui;


import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import address.data.AddressBook;
import address.data.AddressEntry;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class MainWindow {
	/**
	 *Identifying the frame of the mainwindow
	 */
	private JFrame frame;
	
	/**
	 *Identifying a JList to store all of the addressentry
	 */
	JList <AddressEntry> addressEntryJList;
	
	/**
	 * <p>ab is an instance of class AddressBook</br>
	 * ab is the field of class MainWindow</br>
	 * class MainWindow can call the methods in class AddressBook according to ab
	 */
	AddressBook ab = new AddressBook();
	
	/**
	 * This is the getter method of frame
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * This is the setter method of frame
	 * @param frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

 
 /**
  * This is the MainWindow of the application
  * @throws SQLException
  * @throws ClassNotFoundException
  */
 public MainWindow() throws SQLException, ClassNotFoundException {
                           
   ab.readFromDB();
     //Now when we create our JList do it from our ListModel rather than our vector of AddressEntry
   addressEntryJList = new JList<AddressEntry>(ab.getMyaddressEntryListModel());
     
   //setting up the look of the JList
   this.addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
   this.addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
   this.addressEntryJList.setVisibleRowCount(-1);

      //setup GUI and use the JList we created
   initialize();

 }

 
 /**
  * Initialize the contents of the frame.
  */
private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 550, 350);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 //create scrollPane associated with JList
	JScrollPane scrollPane = new JScrollPane(this.addressEntryJList);
	
	frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	JButton btnRemove = new JButton("Remove");
	JPanel theButtons = new JPanel( );
	frame.add(theButtons,BorderLayout.NORTH);
	theButtons.setLayout(new FlowLayout(FlowLayout.LEADING));
	theButtons.add(btnRemove);

btnRemove.addActionListener(new ActionListener() {  //BASED ON event from hitting remove button,                                                                                                                        //Remove item from our JList's ListModel
	public void actionPerformed(ActionEvent arg0) {
		int index = addressEntryJList.getSelectedIndex();
		if(index != -1)//something is selected otherwise do nothing
       {   
		 try {
			System.out.println("====delete===ID==:"+ab.getAddressEntryList().get(index).getID());
			ab.deleteFromDB(ab.getAddressEntryList().get(index).getID());
			ab.remove(ab.getAddressEntryList(), index);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
     }

  });


	JButton btnNew = new JButton("New");
	theButtons.add(btnNew);
btnNew.addActionListener(new ActionListener() {  //BASED ON event from hitting remove button,                                                                                                                        //Remove item from our JList's ListModel
		public void actionPerformed(ActionEvent arg0) {
			
			MyJDialog dialog = new MyJDialog(frame, "Addition",ab);
	        // set the size of the window
	        dialog.setSize(450, 300);
		}
		  });

	JButton btnUpdate = new JButton("Update");
	theButtons.add(btnUpdate);
btnUpdate.addActionListener(new ActionListener() {  //BASED ON event from hitting remove button,                                                                                                                        //Remove item from our JList's ListModel
	public void actionPerformed(ActionEvent arg0) {
		int index = addressEntryJList.getSelectedIndex();
		if(index != -1)//something is selected otherwise do nothing
       {   //retrieve the DeffaultListModel associated
             // with our JList and remove from it the AddressEntry at this index
		AddressEntry aE = ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).getElementAt(index);

		MyJDialog dialog = new MyJDialog(frame, "Update", ab , aE );
        // set the size of the window
        dialog.setSize(450, 300);
	}
	}
	  });


	JButton btnSearch = new JButton("Search");
	theButtons.add(btnSearch);
	btnSearch.addActionListener(new ActionListener() {  //BASED ON event from hitting remove button,                                                                                                                        //Remove item from our JList's ListModel
	public void actionPerformed(ActionEvent arg0) {
		MyJDialog dialog = new MyJDialog(frame , ab , "Search");
	    // set the size of the window
	    dialog.setSize(450, 300);
	}
	
	  });
}

}