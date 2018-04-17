import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by esenav on 05/12/2017.
 */
public class LogOut implements ActionListener {

    GUI theApp;

    public LogOut(GUI theApp){
        this.theApp = theApp;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BufferedWriter fileout = null;
       try {
           if(Training.preferredShows.isEmpty()){
               System.out.println("List is empty");
           }else{
               File file = new File(theApp.LoginName.getText()+"."+"txt");
               if (file.exists()){
                   FileWriter file1 = new FileWriter(file, true);
                   fileout = new BufferedWriter(file1);
                   Object[] recommendedList = Training.preferredShows.toArray();
                   TvShow[] rec = Training.preferredShows.toArray(new TvShow[Training.preferredShows.size()]);

                   fileout.newLine();
                   for ( Object items : rec) {
                       System.out.println("Items from Recommended List: "+items);
                       fileout.write(items.toString());
                   }
                   System.out.println("Shows written in file");
                   fileout.flush();
                   file1.close();
                   fileout.close();
               }
           }
           } catch (IOException e1) {
           e1.printStackTrace();
       }
        theApp.tab.removeAll();
        theApp.centerField.removeAll();
        theApp.NavigationTab();
        theApp.LoginScreen();
        theApp.window.add(theApp.tab, BorderLayout.NORTH);
        theApp.window.add(theApp.centerField, BorderLayout.CENTER);
        theApp.tab.updateUI();
        theApp.centerField.updateUI();
    }


}
