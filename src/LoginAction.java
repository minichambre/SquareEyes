import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by skapno on 04/12/2017.
 */
public class LoginAction implements ActionListener{
    GUI theApp;
    TvShowScreen screen;



    public LoginAction(GUI theApp, TvShowScreen screen){
        this.theApp = theApp;
        this.screen = screen;
    }

   public void Login() {
       String user = theApp.username.getText();
       File file = new File(user +"."+"txt");
            if (file.exists()){
                try {
                    if(LoginPassword(file)){
                        paint();
                        userShows(file);
                        JOptionPane.showMessageDialog
                                (null, " Welcome", "Successful login", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog
                                (null, " Invalid password", "Wrong password, try again.", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (FileNotFoundException e) {

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog
                        (null, " Please enter a valid login name", "InfoBox: Invalid Username ", JOptionPane.INFORMATION_MESSAGE);}
    }

    public boolean LoginPassword(File file) throws FileNotFoundException {

        char[] Password = theApp.passwordField.getPassword();
        String word = null;

        Scanner input = new Scanner(file);
        word = String.valueOf(input.nextLine());
        input.close();
        char[] chars = word.toCharArray();

        if(Arrays.equals(Password,chars)) {
            return true;
        }else{return false;}
    }
    public void paint(){
        theApp.centerField.removeAll();
        theApp.tab.removeAll();
        theApp.Nav_Buttons();
        theApp.LoginName.setText(theApp.username.getText());
        screen.tablescreen();
        theApp.tab.updateUI();
        theApp.centerField.add(screen.tvpane, BorderLayout.CENTER);
        theApp.centerField.updateUI();
    }
    public void userShows(File file) throws IOException{
        Scanner checkList = new Scanner(file);
        int count = 0;
        ArrayList<TvShow> shows = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while(checkList.hasNextLine()){
            count++;
            String x = checkList.nextLine();
            if(count==4 && !(x.isEmpty())){
                JOptionPane.showMessageDialog(null, "You finished training. Good Job !");
                for(Object line; (line = br.readLine())!= null;){
                    try{
                        shows.add((TvShow)line);
                    }catch (ClassCastException e){
                        JOptionPane.showMessageDialog(null, "Could not merge Recommended list back to program :)");
                        break;
                    }
                }
            }
        }
        Training.preferredShows = shows;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Login();

    }
}
