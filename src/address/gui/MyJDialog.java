package address.gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import address.data.Address;
import address.data.AddressBook;
import address.data.AddressEntry;
import address.data.Name;
public class MyJDialog extends JDialog {
	
//	AddressBook ab = new AddressBook();
	
    private static final long serialVersionUID = 1L;
    private JLabel lbStartOfLastName;     // Declare input Label
    private JLabel lbFirstName;    // Declare output Label
    private JLabel lbLastName;    // Declare output Label
    private JLabel lbStreet;    // Declare output Label
    private JLabel lbCity;    // Declare output Label
    private JLabel lbState;    // Declare output Label
    private JLabel lbZip;    // Declare output Label
    private JLabel lbPhone;    // Declare output Label
    private JLabel lbEmail;    // Declare output Label
    private TextField tfStartOfLastName;  // Declare input TextField
    private TextField tfFirstName; // Declare output TextField
    private TextField tfLastName;  // Declare input TextField
    private TextField tfStreet; // Declare output TextField
    private TextField tfCity;  // Declare input TextField
    private TextField tfState; // Declare output TextField
    private TextField tfZip;  // Declare input TextField
    private TextField tfPhone; // Declare output TextField
    private TextField tfEmail; // Declare output TextField
    
    /**
     * <p>This is a constructor of the class MyJDialog</br>
     * handling the New button
     * @param parent
     * @param title
     * @param ab
     */
    public MyJDialog(JFrame parent, String title,AddressBook ab) {
    super(parent, title);
        System.out.println("creating the window..");

        // set the position of the window
        Point p = new Point(400, 400);
        setLocation(p.x, p.y);

        // Create  message
        JPanel messagePane = new JPanel();
        messagePane.setLayout(new SpringLayout());
        
        lbFirstName = new JLabel("FirstName: "); 
        messagePane.add(lbFirstName);              
        tfFirstName = new TextField(10);
        messagePane.add(tfFirstName); 
        lbFirstName.setLabelFor(tfFirstName);
        
        lbLastName = new JLabel("LastName: "); 
        messagePane.add(lbLastName);              
        tfLastName = new TextField(10);
        messagePane.add(tfLastName); 
        lbLastName.setLabelFor(tfLastName);
        
        lbStreet = new JLabel("Street: "); 
        messagePane.add(lbStreet);              
        tfStreet = new TextField(10);
        messagePane.add(tfStreet); 
        lbStreet.setLabelFor(tfStreet);
        
        lbCity = new JLabel("City: "); 
        messagePane.add(lbCity);              
        tfCity = new TextField(10);
        messagePane.add(tfCity); 
        lbCity.setLabelFor(tfCity);
        
        lbState = new JLabel("State: "); 
        messagePane.add(lbState);              
        tfState = new TextField(10);
        messagePane.add(tfState); 
        lbState.setLabelFor(tfState);
        
        lbZip = new JLabel("Zip: "); 
        messagePane.add(lbZip);              
        tfZip = new TextField(10);
        messagePane.add(tfZip); 
        lbZip.setLabelFor(tfZip);
        
        lbPhone = new JLabel("Phone: "); 
        messagePane.add(lbPhone);              
        tfPhone = new TextField(10);
        messagePane.add(tfPhone); 
        lbPhone.setLabelFor(tfPhone);
        
        lbEmail = new JLabel("Email: "); 
        messagePane.add(lbEmail);              
        tfEmail = new TextField(10);
        messagePane.add(tfEmail); 
        lbEmail.setLabelFor(tfEmail);
        
        SpringUtilities.makeCompactGrid(messagePane,
                8, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);
//        messagePane.add(new JLabel(message));
//        add(new TextField(10));
        // get content pane, which is usually the
        // Container of all the dialog's components.
        
        add(messagePane,BorderLayout.NORTH);

 

        // Create  buttons
        JPanel buttonPane = new JPanel();
        JButton btnSave = new JButton("save");
        JButton btnQuit = new JButton("quit");
        buttonPane.add(btnSave);
        buttonPane.add(btnQuit);
        
        // set action listener on the quit button
        btnQuit.addActionListener(new ActionListener() {  //BASED ON event from hitting quit button,                                                                                                                        //Remove item from our JList's ListModel
        	public void actionPerformed(ActionEvent arg0) {
        		 System.out.println("disposing the window..");
                 setVisible(false);
                 dispose();
             }

          });
        
        
        btnSave.addActionListener(new ActionListener() {  //BASED ON event from hitting quit button,                                                                                                                        //Remove item from our JList's ListModel
        	public void actionPerformed(ActionEvent arg0) {
        		 System.out.println("saving the window..");
        		 Name name = new Name(tfFirstName.getText(), tfLastName.getText());
        		 Address address = new Address(tfStreet.getText(), tfCity.getText(), tfState.getText(), Integer.parseInt(tfZip.getText()));
//                 AddressEntry aE = new AddressEntry((ab.addressEntryList.get(ab.addressEntryList.size()-1).getID()+1), name, address, tfPhone.getText(), tfEmail.getText());
        		 AddressEntry aE = new AddressEntry(0, name, address, tfPhone.getText(), tfEmail.getText());
//                 ab.add(aE);
//                 System.out.println("ID: "+(ab.addressEntryList.get(ab.addressEntryList.size()-1).getID()+1));
                 try {
					ab.add(ab.addNewToDB(aE));
					ab.sorted();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 setVisible(false);
                 dispose(); 
        	}

          });
        add(buttonPane, BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    /**
     * <p>This is a constructor of the class MyJDialog</br>
     * handling the Update button
     * @param parent
     * @param title
     * @param ab
     * @param aE
     */
    public MyJDialog(JFrame parent, String title , AddressBook ab, AddressEntry aE) {
        super(parent, title);
            System.out.println("creating the window..");

            // set the position of the window
            Point p = new Point(400, 400);
            setLocation(p.x, p.y);

            // Create  message
            JPanel messagePane = new JPanel();
            messagePane.setLayout(new SpringLayout());
            
            lbFirstName = new JLabel("FirstName: "); 
            messagePane.add(lbFirstName);              
            tfFirstName = new TextField(10);
            tfFirstName.setText(aE.getName().getFirstName());
            messagePane.add(tfFirstName); 
            lbFirstName.setLabelFor(tfFirstName);
            
            lbLastName = new JLabel("LastName: "); 
            messagePane.add(lbLastName);              
            tfLastName = new TextField(10);
            tfLastName.setText(aE.getName().getLastName());
            messagePane.add(tfLastName); 
            lbLastName.setLabelFor(tfLastName);
            
            lbStreet = new JLabel("Street: "); 
            messagePane.add(lbStreet);              
            tfStreet = new TextField(10);
            tfStreet.setText(aE.getAddress().getStreet());
            messagePane.add(tfStreet); 
            lbStreet.setLabelFor(tfStreet);
            
            lbCity = new JLabel("City: "); 
            messagePane.add(lbCity);              
            tfCity = new TextField(10);
            tfCity.setText(aE.getAddress().getCity());
            messagePane.add(tfCity); 
            lbCity.setLabelFor(tfCity);
            
            lbState = new JLabel("State: "); 
            messagePane.add(lbState);              
            tfState = new TextField(10);
            tfState.setText(aE.getAddress().getState());
            messagePane.add(tfState); 
            lbState.setLabelFor(tfState);
            
            lbZip = new JLabel("Zip: "); 
            messagePane.add(lbZip);              
            tfZip = new TextField(10);
            tfZip.setText(Integer.toString(aE.getAddress().getZip()));
            messagePane.add(tfZip); 
            lbZip.setLabelFor(tfZip);
            
            lbPhone = new JLabel("Phone: "); 
            messagePane.add(lbPhone);              
            tfPhone = new TextField(10);
            tfPhone.setText(aE.getPhone());
            messagePane.add(tfPhone); 
            lbPhone.setLabelFor(tfPhone);
            
            lbEmail = new JLabel("Email: "); 
            messagePane.add(lbEmail);              
            tfEmail = new TextField(10);
            tfEmail.setText(aE.getEmail());
            messagePane.add(tfEmail); 
            lbEmail.setLabelFor(tfEmail);
            
            SpringUtilities.makeCompactGrid(messagePane,
                    8, 2, //rows, cols
                    6, 6,        //initX, initY
                    6, 6);
//            messagePane.add(new JLabel(message));
//            add(new TextField(10));
            // get content pane, which is usually the
            // Container of all the dialog's components.
            
            add(messagePane,BorderLayout.NORTH);

     

            // Create  buttons
            JPanel buttonPane = new JPanel();
            JButton btnSave = new JButton("save");
            JButton btnQuit = new JButton("quit");
            buttonPane.add(btnSave);
            buttonPane.add(btnQuit);
            
            // set action listener on the quit button
            btnQuit.addActionListener(new ActionListener() {  //BASED ON event from hitting quit button,                                                                                                                        //Remove item from our JList's ListModel
            	public void actionPerformed(ActionEvent arg0) {
            		 System.out.println("disposing the window..");
                     setVisible(false);
                     dispose();
                 }

              });
            
            
            btnSave.addActionListener(new ActionListener() {  //BASED ON event from hitting quit button,                                                                                                                        //Remove item from our JList's ListModel
            	public void actionPerformed(ActionEvent arg0) {
            		 System.out.println("saving the window..");
            		 ab.remove(ab.getAddressEntryList(), ab.getAddressEntryList().indexOf(aE));
            		 Name name = new Name(tfFirstName.getText(), tfLastName.getText());
            		 Address address = new Address(tfStreet.getText(), tfCity.getText(), tfState.getText(), Integer.parseInt(tfZip.getText()));
                     AddressEntry aE1 = new AddressEntry(aE.getID(), name, address, tfPhone.getText(), tfEmail.getText());
                     ab.add(aE1);
                     try {
                    	ab.deleteFromDB(aE.getID());
    					ab.updateInDB(aE1);
    					ab.sorted();
    				} catch (ClassNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                     setVisible(false);
                     dispose();
            	}

              });
            add(buttonPane, BorderLayout.PAGE_END);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();
            setVisible(true);
        }
    
    /**
     * <p>This is a constructor of the class MyJDialog</br>
     * handling the Search button
     * @param parent
     * @param ab
     * @param title
     */
    public MyJDialog(JFrame parent, AddressBook ab,String title) {
        	super(parent, title);
            System.out.println("creating the window..");
	   		DefaultListModel<AddressEntry> findListModel = new DefaultListModel<AddressEntry>();
	   		JList <AddressEntry> addressEntryJList;
	   		addressEntryJList = new JList<AddressEntry>(findListModel);
	   		JScrollPane scrollPane = new JScrollPane(addressEntryJList);
			 add(scrollPane, BorderLayout.CENTER);
            // set the position of the window
            Point p = new Point(400, 400);
            setLocation(p.x, p.y);

            // Create  message
            JPanel messagePane = new JPanel();
//            messagePane.setLayout(new SpringLayout());

            
            lbStartOfLastName = new JLabel("StartOfLastName: "); 
            messagePane.add(lbStartOfLastName);              
            tfStartOfLastName = new TextField(10);
            messagePane.add(tfStartOfLastName); 
            lbStartOfLastName.setLabelFor(tfStartOfLastName);
            JButton btnSearch = new JButton("Search");
            messagePane.add(btnSearch);
            add(messagePane,BorderLayout.NORTH);
            
  
btnSearch.addActionListener(new ActionListener() {  //BASED ON event from hitting quit button,                                                                                                                        //Remove item from our JList's ListModel
	public void actionPerformed(ActionEvent arg0) {
		 System.out.println("saving the window..");
		 findListModel.clear();
		 String startOFLastName = tfStartOfLastName.getText();
		 
		 for(int i = 0; i<ab.search(startOFLastName).size(); i++)
		    {  findListModel.add(i, ab.search(startOFLastName).get(i)); }
		
		 //repaint
		 SwingUtilities.updateComponentTreeUI(MyJDialog.this);
		 System.out.println(ab.search(startOFLastName).get(0));
		 

	}
              });
			
//            add(buttonPane, BorderLayout.PAGE_END);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();
            setVisible(true);
        }
}

