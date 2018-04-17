import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by esenav on 11/12/2017.
 */
public class SystemNotificationTest {
    @Test
    public void testNotification() throws Exception {
        ArrayList<TvShow> shows = new ArrayList<TvShow>();

        ArrayList<String> genres = new ArrayList<String>(Arrays.asList("genre1", "genre2"));
        shows.add(new TvShow("show 1", genres, "network1", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));
        shows.add(new TvShow("show 2", genres, "network2", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));

        SystemNotification n = new SystemNotification();
        SystemNotification.check = 1;
        n.displayNotification(shows.get(0));
        TrayIcon[] result = n.notification.getTrayIcons();
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(n.notificationImage.getToolTip().equals(shows.get(0).name));
    }

    @Test
    public void testIfNotificationRunsWhenNotSupported()
    {
        ArrayList<TvShow> shows = new ArrayList<TvShow>();

        ArrayList<String> genres = new ArrayList<String>(Arrays.asList("genre1", "genre2"));
        shows.add(new TvShow("show 1", genres, "network1", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));
        shows.add(new TvShow("show 2", genres, "network2", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));

        SystemNotification n = new SystemNotification();
        SystemNotification.check = 0;
        n.displayNotification(shows.get(0));
        Assert.assertTrue(n.notificationImage == null);
    }

}