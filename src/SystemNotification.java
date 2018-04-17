import javax.swing.*;
import java.awt.*;

/**
 * Created by esenav on 11/12/2017.
 */
public class SystemNotification {
    public TrayIcon notificationImage;
    SystemTray notification;
    public static int check = 0;

    public void CheckTray() {
        if (SystemTray.isSupported()) {
            SystemNotification not = new SystemNotification();
            check = 1;

        } else {
            JOptionPane.showMessageDialog(null, "System notification not supported :(");
        }
    }

    public void displayNotification(TvShow show) {
       if (check==1){
           notification = SystemTray.getSystemTray();
           Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
           notificationImage = new TrayIcon(image, "Test");
           notificationImage.setImageAutoSize(true);
           notificationImage.setToolTip(show.name);
           try {
               notification.add(notificationImage);
           } catch (AWTException e) {
               e.printStackTrace();
           }
           notificationImage.displayMessage(show.name, "Is starting in 30 minutes at " + show.viewingTime, TrayIcon.MessageType.INFO);
       }
    }

}
