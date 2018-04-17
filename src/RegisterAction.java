import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by esenav on 01/12/2017.
 */
public class RegisterAction implements ActionListener {

    GUI theApp;
    public RegisterAction(GUI theApp){
        this.theApp = theApp;
    }

    JTextField userfield, emailfield, telNoField;
    JPasswordField pasfield, pasfield2;
    JLabel user, pas, pasrep, email, telNo;
    JButton reg, ret;

    private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NUMBER_PATTERN = "^\\d*$";


    public void actionPerformed(ActionEvent event){
        theApp.centerField.removeAll();
        theApp.centerField.setLayout(new GridLayout(0,2));
        fieldgenerator();
        centerfieldfillIn();
    }

    public void fieldgenerator(){
        user = new JLabel("UserName");
        userfield = new JTextField();
        pas = new JLabel("Password");
        pasfield = new JPasswordField();
        pasrep = new JLabel("Repeat password");
        pasfield2 = new JPasswordField();
        email = new JLabel("Email Address");
        emailfield = new JTextField();
        telNo = new JLabel("Telephone Number");
        telNoField = new JTextField();
        ret = new JButton("Cancel Registration");
        reg = new JButton("Confirm Registration");

    }
    public class RegisterFile implements ActionListener {
        RegisterAction Tapp;
        public RegisterFile(RegisterAction Tapp){
            this.Tapp = Tapp;
        }

        public void actionPerformed (ActionEvent event) {

            if(userfield.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog
                        (null, " Required Username", "InfoBox: " + "Required Username", JOptionPane.INFORMATION_MESSAGE);
                userfield.requestFocus();
                return;
            }

            if(pasfield.getPassword().toString().trim().isEmpty()){
                JOptionPane.showMessageDialog
                        (null, " Required Password", "InfoBox: " + "Required Password", JOptionPane.INFORMATION_MESSAGE);
                pasfield.requestFocus();
                return;
            }
            if(emailfield.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog
                        (null, " Required Email", "InfoBox: " + "Required Email", JOptionPane.INFORMATION_MESSAGE);
                emailfield.requestFocus();
                return;
            }
            if(telNoField.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog
                        (null, " Required Phone Number", "InfoBox: " + "Required Phone Number", JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            if (!emailfield.getText().matches(EMAIL_PATTERN)) {
                JOptionPane.showMessageDialog
                        (null, " Invalid Email Address", "InfoBox: " + "Invalid Email Address", JOptionPane.INFORMATION_MESSAGE);
                emailfield.requestFocus();
                return;
            }

            if (!telNoField.getText().matches(NUMBER_PATTERN)) {
                JOptionPane.showMessageDialog
                        (null, " Invalid Phone Number", "InfoBox: " + "Invalid Phone Number", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String Username = userfield.getText();
            File file = new File(Username + "."+"txt");

            if (file.exists()){
                JOptionPane.showMessageDialog
                        (null, " Username already exists", "InfoBox: " + "Invalid Username", JOptionPane.INFORMATION_MESSAGE);
            }else {
            try {
                if(createPass(file)){
                    file.createNewFile();
                    theApp.centerField.removeAll();
                    theApp.LoginScreen();
                    theApp.window.add(theApp.centerField, BorderLayout.CENTER);

                    theApp.centerField.updateUI();
                }

                System.out.println("Returns to Login Screen");
            } catch(IOException ioe) {
                JOptionPane.showMessageDialog
                        (null, " Please enter a valid login name", "InfoBox: " + "Invalid Username", JOptionPane.INFORMATION_MESSAGE);
            }}

        }




    }
    public class retLog implements ActionListener{
        RegisterAction Tapp2;
        public retLog(RegisterAction Tapp){
            this.Tapp2 = Tapp;}

        public void actionPerformed(ActionEvent event){
            theApp.centerField.removeAll();
            theApp.LoginScreen();
            theApp.window.add(theApp.centerField, BorderLayout.CENTER);
            theApp.centerField.updateUI();
            System.out.println("Returns to Login Screen");
        }

    }





   public boolean createPass (File file){
       BufferedWriter bw = null;
       FileWriter fw = null;

       char[] password = pasfield.getPassword();
       char[] repassword = pasfield2.getPassword();
       String email = emailfield.getText();
       String phone = telNoField.getText();

       try {
           if(Arrays.equals(password, repassword)&& (password.length != 0)){
               fw = new FileWriter(file);
               bw = new BufferedWriter(fw);
               bw.write(password );
               bw.newLine();
               bw.write(email);
               bw.newLine();
               bw.write(phone);
               bw.flush();

               JOptionPane.showMessageDialog
                       (null, " You are now registered", "InfoBox: " + "Registration Complete", JOptionPane.INFORMATION_MESSAGE);


           }else{
               JOptionPane.showMessageDialog
                       (null, " Ensure that you enter the same password", "InfoBox: " + "Invalid password", JOptionPane.INFORMATION_MESSAGE);
               pasfield.setText("");
               pasfield2.setText("");
               return false;
           }
           return true;

       } catch (IOException e) {
           e.printStackTrace();
           return false;
       } finally {
           try {
               if (bw != null)
                   bw.close();
               if (fw != null)
                   fw.close();
           } catch (IOException ex) {
               ex.printStackTrace();
           }
       }
   }




        public void centerfieldfillIn(){
            theApp.centerField.add(user);
            theApp.centerField.add(userfield);
            theApp.centerField.add(pas);
            theApp.centerField.add(pasfield);
            theApp.centerField.add(pasrep);
            theApp.centerField.add(pasfield2);
            theApp.centerField.add(email);
            theApp.centerField.add(emailfield);
            theApp.centerField.add(telNo);
            theApp.centerField.add(telNoField);
            theApp.centerField.add(ret);
            theApp.centerField.add(reg);
            reg.addActionListener(new RegisterFile(this));
            ret.addActionListener(new retLog(this));
            theApp.centerField.updateUI();

    }

}
