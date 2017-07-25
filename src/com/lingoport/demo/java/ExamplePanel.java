package com.lingoport.demo.java;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.event.*;
import java.text.*;

import com.lingoport.demo.java.utils.Debug;
import com.lingoport.demo.java.utils.Encoding;
import com.lingoport.demo.java.utils.I18nUtils;
import com.lingoport.demo.java.utils.ImageHelper;


/**
 * Simple Example panel set up for the i18n demo. 
 * 
 * In this tutorial code, i18n issues include hard coded strings and date/time formats:
 * <UL><LI>
 * Externalize the hard-code strings with Globalyzer, first on one, then on all hard-coded string
 * (Note: place an ignore line on the @ directive line and create a filter for the Debug.log calls)
 * </LI><LI>
 * Create a fr_FR pseudo locale properties file with \u3070 (Japanese character) 
 * using Globalyzer after all strings are externalized (check the scan results)
 * </LI><LI>
 * Date/Time need to heed a locale (see Globalyzer help pages from the Summary view). 
 * Use the I18nUtils.getLocale method to pass a Locale instance to the
 * date format instance; in effect, fr_FR is the pseudo-locale.
 * </LI>
 * </UL>
 */
public class ExamplePanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -772706090092654841L;
	
	JPanel infoPanel_;
    JPanel datePanel_;
    Box buttonBox_;

    JButton nextButton_;
    JButton addButton_;
    JButton exitButton_;

    JTextField nameJText_ = new JTextField(15);
    JTextField streetJText_ = new JTextField(15);
    JTextField apartmentJText_ = new JTextField(15);
    JTextField cityJText_ = new JTextField(15);
    JTextField zipJText_ = new JTextField(15);
    JTextField phoneJText_ = new JTextField(15);
    JTextField emailJText_ = new JTextField(15);
    
    // Initial Test Data for Debugging
    String name_ = "Jim Smith";  //$NON-NLS-L$ 
    String street_ = "2025 Main St."; //$NON-NLS-L$ 
    String apartment_; 
    String city_ = "Boulder, CO"; //$NON-NLS-L$ 
    String zip_="80301";  //$NON-NLS-L$ 
    String phone_;
    String email_;
    
    
    Date today_ = new Date();
    Date time_ = new Date();

    int counter_ = 0;

    @SuppressWarnings("rawtypes") //$NON-NLS-L$ 
	ArrayList addresses = new ArrayList(10);


    /**
     * The only constructor.
     */
    public ExamplePanel() {
        setLayout(new BorderLayout());
        initialize();
    }

    /**
     * Lays out all of the scanner-configuration components in the panel.
     */
    private void initialize() {

        createDatePanel();
        createInfoPanel();
        createButtons();

        add(datePanel_, BorderLayout.NORTH);
        add(infoPanel_, BorderLayout.CENTER);
        add(buttonBox_, BorderLayout.SOUTH);
    }

    public void createButtons() {

        ImageIcon nextButtonIcon = ImageHelper.createImageIcon("images/next.jpg");
        nextButton_ = new JButton( nextButtonIcon);
        nextButton_.setToolTipText(I18nUtils.getString("EXAM_NEXTCLE_1"));
        nextButton_.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	// To be implemented for Sprint 5
            	nameJText_.setText("");
            	streetJText_.setText("");
            	apartmentJText_.setText("");
            	cityJText_.setText("");
            	zipJText_.setText("");
            	phoneJText_.setText("");
            	emailJText_.setText("");
            	
                // Clears the Text Fields
                Debug.log("Next action taken; For now, clears the field. Implement in Sprint 5.");
            }
        });
        
        // Will the getCharacterInstance get caught?
        BreakIterator bi = java.text.BreakIterator.getCharacterInstance();
        Locale lc = I18nUtils.getLocale();
        bi = java.text.BreakIterator.getCharacterInstance(lc);
        
        ImageIcon addButtonIcon = ImageHelper.createImageIcon("images/add.jpg");
        addButton_ = new JButton( addButtonIcon);
        addButton_.setToolTipText("Add Record"); // $NON-NLS-L$
        addButton_.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Write to a file the data
                // Start with the name for now
                // TODO : the entire data
                String testFilename = "test.html"; 
                name_ = nameJText_.getText();
                Encoding.writeOutput(testFilename, name_, Encoding.ASCII);
                Debug.log("Add action taken: ["+ name_ + "], the name only, is persisted for checking; let's read it in again.");
                
                // Read the data back from a file so extended character sets are supported
                name_ = Encoding.readInput(testFilename, Encoding.ASCII);
                /*TODO*/
                nameJText_.setText(name_);
                
            }
        });

        ImageIcon exitButtonIcon = ImageHelper.createImageIcon("images/exit.jpg");
        exitButton_ = new JButton(exitButtonIcon);
        exitButton_.setToolTipText("Exit");
        exitButton_.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Debug.log("Exit action taken. Bye!");
                System.exit(0);
            }
        });

        buttonBox_ = new Box(BoxLayout.X_AXIS);
        buttonBox_.add(Box.createGlue());
        buttonBox_.add(nextButton_);
        buttonBox_.add(Box.createHorizontalStrut(10));
        buttonBox_.add(addButton_);
        buttonBox_.add(Box.createHorizontalStrut(10));
        buttonBox_.add(exitButton_);
        buttonBox_.add(Box.createGlue());
    }


    /**
     * Creates the JPanel associated with all of the user-entry widgets
     * associated with scanner report generation.
     */
    private void createDatePanel() {

        datePanel_ = new JPanel(new GridBagLayout());
        
//        String message = MessageFormat.format("{0} for Today: ", new Object[]{I18nUtils.getLocale()});
//        TitledBorder tb = new TitledBorder(new LineBorder(Color.darkGray), message);
        
        // Example of string concatenation below:
        TitledBorder tb = new TitledBorder(new LineBorder(Color.darkGray),                                        
                                           I18nUtils.getLocale()
         									+
         									" for Today: ");
        datePanel_.setBorder(tb);
        datePanel_.setToolTipText("Displays today's date and time.");

        JLabel dateLabel = new JLabel("Today's Date: ");       

//         String template = "Time: {0}";
//         String timeStr  = MessageFormat.format(template, I18nUtils.getLocale());
//         JLabel timeLabel = new JLabel(timeStr);
        
        // Example of String concatenation below        
        StringBuffer timeSB = new StringBuffer("Time: ");
        // timeSB.append(I18nUtils.getLocale());
        
        JLabel timeLabel = new JLabel(timeSB.toString()); /*GLOBALYZER_TODO*/  //$NON-NLS-L$ 

/*GLOBALYZER_START_IGNORE*/

        //-- Lay out the components.
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10,
                                        10, 10);
/*GLOBALYZER_END_IGNORE*/

        /*GLOBALYZER_TODO*/
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.0;
        datePanel_.add(dateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.0;
        
        // Fix the code by adding a locale, here French, which really should come from a Locale framework
        // datePanel_.add(new JLabel(DateFormat.getDateInstance(DateFormat.FULL, I18nUtils.getLocale()).format(today_)), constraints); // $NON-NLS-L$ 
        Locale userLocale = I18nUtils.getLocale();
        DateFormat formattedDate = DateFormat.getDateInstance(DateFormat.MEDIUM, userLocale);
        //String formattedDate = DateFormat.getDateInstance(userLocale);
        formattedDate = DateFormat.getDateInstance(); 
        datePanel_.add(new JLabel(DateFormat.getDateInstance(DateFormat.FULL).format(today_)), constraints); 

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        datePanel_.add(timeLabel, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        
        // Fix the code by adding a locale, here French, which really should come from a Locale framework
        // datePanel_.add(new JLabel(DateFormat.getTimeInstance(DateFormat.DEFAULT, I18nUtils.getLocale()).format(time_)), constraints); // $NON-NLS-L$ 

        datePanel_.add(new JLabel(DateFormat.getTimeInstance(DateFormat.DEFAULT).format(time_)), constraints);
    }


    /**
     * Creates the JPanel associated with all of the user-entry widgets
     * associated with scanner report generation.
     */
    private void createInfoPanel() {

        infoPanel_ = new JPanel(new GridBagLayout());
        TitledBorder tb = new TitledBorder(new LineBorder(Color.darkGray),
                                           I18nUtils.getString("EXAM_ADDRESS_1"));
        infoPanel_.setBorder(tb);
        infoPanel_.setToolTipText(I18nUtils.getString("EXAM_DISPLAY_2"));

        JLabel nameLabel = new JLabel(I18nUtils.getString("EXAM_NAME_3"));
        JLabel streetLabel = new JLabel(I18nUtils.getString("EXAM_STREET_4"));
        JLabel apartmentLabel = new JLabel(I18nUtils.getString("EXAM_APARTME_5"));
        JLabel cityLabel = new JLabel(I18nUtils.getString("EXAM_CITY_6"));
        JLabel zipLabel = new JLabel(I18nUtils.getString("EXAM_ZIPCODE_7"));
        JLabel phoneLabel = new JLabel("Phone: ");
        JLabel emailLabel = new JLabel("Email: ");


        JLabel street = new JLabel(street_);
        JLabel apartment = new JLabel(apartment_);
        JLabel city = new JLabel(city_);
        JLabel zip = new JLabel(zip_);
        JLabel phone = new JLabel(phone_);
        JLabel email = new JLabel(email_);



        //-- Lay out the components.
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10,
                                        10, 10);

        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0.0;
        infoPanel_.add(nameLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0.0;
        nameJText_.setText(name_);
        infoPanel_.add(nameJText_, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        infoPanel_.add(streetLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        streetJText_.setText(street_);
        infoPanel_.add(streetJText_, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 0.0;
        infoPanel_.add(apartmentLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 0.0;
        infoPanel_.add(apartmentJText_, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        infoPanel_.add(zipLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        zipJText_.setText(zip_);
        infoPanel_.add(zipJText_, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.weightx = 0.0;
        infoPanel_.add(cityLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.weightx = 0.0;
        cityJText_.setText(city_);
        infoPanel_.add(cityJText_, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        infoPanel_.add(phoneLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        infoPanel_.add(phoneJText_, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        infoPanel_.add(emailLabel, constraints);


        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        infoPanel_.add(emailJText_, constraints);

    }



    /**
     * Validates that all of the selected name fields have text in them.
     * @return false if the user failed to enter a name in a checked field.
     */
    public boolean validateFields() {
        boolean valid = true;
        return valid;
    }


    /**
     * The user sees this popup when the user fails to enter a filename.
     */
    @SuppressWarnings("unused")  
	private void showEmptyFilenameMessage() {

        String message = "A Field Is Empty";
        String heading = "Missing Field";
        JOptionPane.showMessageDialog(new JFrame(),
                                      message, heading,
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    public class AddressInfo {

        String name_;
        String street_;
        String apartment_;
        String zip_;
        String city_;
        String phone_;
        String email_;

        public String getName() {
            return name_;
        }

        public String getStreet() {
            return street_;
        }

        public String getApartment() {
            return apartment_;
        }

        public String getZip() {
            return zip_;
        }

        public String getCity() {
            return city_;
        }

        public String getPhone() {
            return phone_;
        }

        public String getEmail() {
            return email_;
        }


        public void setName(String aName) {
            name_ = aName;
        }

        public void setStreet(String aStreet) {
            street_ = aStreet;
        }

        public void setApartment(String anApartment) {
            apartment_ = anApartment;
        }

        public void setZip(String aZip) {
            zip_ = aZip;
        }

        public void setCity(String aCity) {
            city_ = aCity;
        }

        public void setPhone(String aPhone) {
            phone_ = aPhone;
        }

        public void setEmail(String anEmail) {
            email_ = anEmail;
        }
    }
}