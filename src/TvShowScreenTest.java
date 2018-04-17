import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hrsalm on 01/12/2017.
 */
public class TvShowScreenTest {
    public TvShowScreen tvShowScreen;
    GUI gui;
    @Before
    public void setUp() throws Exception {
        gui = new GUI();
        tvShowScreen = new TvShowScreen(gui);
        TvGuide testGuide = new TvGuide();
        testGuide.jsonReader("src\\testSchedule.json");
    }

    @Test
    public void testDatesForComboBox() throws IOException{
        ArrayList<String> test = tvShowScreen.datesForComboBox();
        Assert.assertTrue(test.size() == 7);
    }

    @Test
    public void testStringToLocalDateTime() throws IOException{
        String localDateTime = "2017-12-07";
        LocalDate localDate = new LocalDate("2017-12-07");
        Assert.assertTrue(tvShowScreen.stringToLocalDate(localDateTime).equals(localDate));
    }
    @Test
    public void testLocalDateToDateTime() throws IOException{
        LocalDate localDate = new LocalDate("2017-12-07");
        DateTime dateTime = new DateTime("2017-12-07T00:00:00+00:00");
        Assert.assertTrue(dateTime.equals(tvShowScreen.localDateToDateTime(localDate)));
    }

    @Test
    public void testStringToDateTime() throws IOException{
        String localDate = "2017-12-07";
        DateTime dateTime = new DateTime("2017-12-07T00:00:00+00:00");
        Assert.assertTrue(dateTime.equals(tvShowScreen.stringToDateTime(localDate)));
    }



}