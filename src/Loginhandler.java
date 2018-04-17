import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by zhood on 17/11/2017.
 */
public class Loginhandler implements ActionListener {
    GUI theApp;

    public Loginhandler(GUI theApp){
        this.theApp = theApp;
    }

    public void actionPerformed(ActionEvent event ){

        theApp.centerField.removeAll();

        JLabel text1 = new JLabel();
        TvGuide x = new TvGuide();

        try {
            x.jsonReader("src\\testSchedule.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        text1.setText(x.shows.get(5).getName());
        text1.setPreferredSize(new Dimension(250, 350));
        theApp.centerField.add(text1, BorderLayout.CENTER);
        theApp.centerField.updateUI();
    }
}
