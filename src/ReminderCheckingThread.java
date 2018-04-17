import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by skapno on 11/12/2017.
 */

public class ReminderCheckingThread extends Thread implements Runnable{
    int i = 0;
    private ArrayList<TvShow> reminderList = new ArrayList<>();
    @Override
    public void run() {
        while(true){
            for (TvShow tvShow : reminderList) {
                while(tvShow.viewingTime.minusMinutes(30)==(new DateTime())){
                    SystemNotification systemNotification = new SystemNotification();
                    systemNotification.displayNotification(tvShow);
                    System.out.println(reminderList.toString());
                    reminderList.remove(tvShow);
                }
                i=0;
            }
        }
    }

    public synchronized void addShow(TvShow tvShow){
        reminderList.add(tvShow);
    }


}
