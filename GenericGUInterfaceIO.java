import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

class GenericGUInterfaceIO extends JFrame {

    GenericGUInterfaceIO() {

        JPanel p1 = new JPanel();
        // rows & columns set for application
        //Adjusts horizontal configuration of input fields. Use hgap (3rd integer)
        p1.setLayout(new GridLayout(5, 2,-400,0));
        FlowLayout layout = new FlowLayout();
        JPanel p2 = new JPanel();
        p2.setLayout(layout);
        JPanel p3 = new JPanel();
        //Adjusts vertical configuration of bottom, use vgap (4th integer)
        p3.setLayout(new GridLayout(2,2,0,-65));

        //Establishes the GUI element vars
        //Field label names
        JLabel desc1, desc2, desc3, desc4, desc5, out1desc, out2desc;
        //Text field variables
        JTextField f1, f2, f3;
        JTextArea out1, out2;
        //Button variable
        JButton buttonSubmit;
        //Dropdown variable
        JComboBox<String> drop1, drop2;
        //Dropdown list variable
        String[] dropdownList = {

                "LIST", "OF", "THINGS", "HIDDEN", "Custom Path"

        };
        String[] dropdownList2 = {

                "SECOND", "LIST", "INITIALLY", "HIDDEN", "FROM", "VIEW"

        };
        //Constructor for GUI element vars
        desc1 = new JLabel("   First Input:", JLabel.LEFT);
        desc1.setFont(new Font("Open Sans", Font.PLAIN, 16));
        f1 = new JTextField(20);
        f1.setFont(new Font("Open Sans", Font.PLAIN, 14));

        desc2 = new JLabel("   Second Input:", JLabel.LEFT);
        desc2.setFont(new Font("Open Sans", Font.PLAIN, 16));
        f2 = new JTextField(20);
        f2.setFont(new Font("Open Sans", Font.PLAIN, 14));

        desc3 = new JLabel("   First List:", JLabel.LEFT);
        desc3.setFont(new Font("Open Sans", Font.PLAIN, 16));
        drop1 = new JComboBox<>(dropdownList);
        drop1.setFont(new Font("Open Sans", Font.PLAIN, 14));
        desc5 = new JLabel("   Hidden List:", JLabel.LEFT);
        desc5.setVisible(false);
        desc5.setFont(new Font("Open Sans", Font.PLAIN, 16));
        drop2 = new JComboBox<>(dropdownList2);
        drop2.setFont(new Font("Open Sans", Font.PLAIN, 14));
        drop2.setVisible(false);
        drop1.addActionListener(e ->
        {
            var getDrop1 = drop1.getSelectedItem();
            if (getDrop1 == "HIDDEN") {
                drop2.setVisible(true);
                desc5.setVisible(true);
            }
            else {
                drop2.setVisible(false);
                desc5.setVisible(false);
            }
        });

        desc4 = new JLabel("   Autofill:", JLabel.LEFT);
        desc4.setFont(new Font("Open Sans", Font.PLAIN, 16));
        f3 = new JTextField("",20);
        f3.setFont(new Font("Open Sans", Font.PLAIN, 14));

        out1desc = new JLabel("(Output 1)", JLabel.CENTER);
        out1desc.setFont(new Font("Consolas", Font.PLAIN, 18));
        out1desc.setVerticalAlignment(JLabel.CENTER);
        out1 = new JTextArea();
        out1.setFont(new Font("Consolas", Font.PLAIN, 13));
        out1.setLineWrap(true);
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        out1.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(0, 0, 0, 0)));

        out2desc = new JLabel("(Output 2)", JLabel.CENTER);
        out2desc.setFont(new Font("Consolas", Font.PLAIN, 18));
        out2desc.setVerticalAlignment(JLabel.CENTER);
        out2 = new JTextArea();
        out2.setFont(new Font("Consolas", Font.PLAIN, 13));
        out2.setLineWrap(true);
        out2.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(0, 0, 0, 0)));

        buttonSubmit = new JButton("SUBMIT");
        buttonSubmit.addActionListener(e -> {
            //Pulls user input and gathers the data here
            var site = drop1.getSelectedItem();
            //Custom Path logic that lets users input their own OU path, which is taken literally, substitutes dropdown input
            if (site == "THINGS"){
                f3.selectAll();
                f3.replaceSelection("");
                f3.replaceSelection(drop1.getSelectedItem()+""+drop2.getSelectedItem()+"");
            }
            else if (site != "Custom Path") {
                f3.selectAll();
                f3.replaceSelection("");
                f3.replaceSelection(drop1.getSelectedItem()+"");
            }
            //Let's get that user input, choo choo!
            String f1rec = f1.getText();
            String f2rec = f2.getText();
            char firstinitial = f1rec.charAt(0);
            String username = (firstinitial+f2rec).toLowerCase();
            String f3rec = f3.getText();
            //Start Output Code
            System.out.println("Created by Jeremy Sprader");
            out1.selectAll();
            out1.replaceSelection("");
            out1.append(
                    //Script 1 Code Output here
                    "Enable-MailUser -Identity '"+f3rec+f1rec+" "+f2rec+"' -ExternalEmailAddress "+username+"@email.com"
            );
            out2.selectAll();
            out2.replaceSelection("");
            out2.append(
                    //Script 2 Code Output here
                    "Set-MailUser -Identity '"+f3rec+f1rec+" "+f2rec+"' -EmailAddressPolicyEnabled $false -EmailAddresses 'smtp:"+username+"@email.gov', 'smtp:"+username+"@email.com', 'SMTP:"+username+"@defaultemail.com'"
            );

        });
        //Ordering of input and output elements
        //First row
        p1.add(desc1);
        p1.add(f1);
        //Second row
        p1.add(desc2);
        p1.add(f2);
        //Third row
        p1.add(desc3);
        p1.add(drop1);
        //Can you guess?
        p1.add(desc5);
        p1.add(drop2);
        p1.add(desc4);
        p1.add(f3);
        //Start of output section
        p3.add(out1desc);
        p3.add(out2desc);
        p3.add(out1);
        p3.add(out2);
        // Literally just  adds the submit button
        p2.add(buttonSubmit);

        //General layout of all the elements
        add(p1, "North");
        add(p2, "South");
        add(p3, "Center");


        // Window size, features, etc
        setVisible(true);
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Script Generator GUI");
    }

    //End of main method
    public static void main(String[] args)
    {
        // Calls the main method
        new GenericGUInterfaceIO();
    }
}

/*
Program created by Jeremy Sprader
Version 2.1.1
 */
