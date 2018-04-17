/**
 * Created by ksanghb on 22/11/2017.
 */
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FilterTest {
    public ArrayList<TvShow> filterShows;
    public Filter filter;

    @Before
    public void setUp() throws Exception {
        filterShows = new ArrayList<TvShow>();
        filter = new Filter();

        ArrayList<String> genres = new ArrayList<String>(Arrays.asList("genre1", "genre2"));
        filterShows.add(new TvShow("show 1", genres, "network1", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));
        filterShows.add(new TvShow("show 2", genres, "network2", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));
        filterShows.add(new TvShow("show 3", genres, "network2", "imgLink", "description", "8", "150", "2017-11-06T11:00:00+00:00" ));
        filterShows.add(new TvShow("show 4", genres, "network3", "imgLink", "description", "8", "150", "2017-11-07T11:00:00+00:00" ));
        filterShows.add(new TvShow("show 5", genres, "network1", "imgLink", "description", "8", "150", "2017-11-07T11:00:00+00:00" ));


    }

    @Test (expected = IOException.class)
    public void testNonexistImage() throws IOException
    {
        String url = "https://media.mnn.com//images/2014/07/dog-trying-to-smell.jpg";
        ImageIcon testImage = (Filter.getImage(url));

    }

    @Test
    public void testImage() throws IOException{
        String url = "https://media.mnn.com/assets/images/2014/07/dog-trying-to-smell.jpg";
        ImageIcon testImage = Filter.getImage(url);
        Assert.assertTrue( (testImage.getIconWidth() > 0));

    }

    @Test
    public void testFilterByName() throws IOException
    {
        ArrayList<TvShow> testData = new ArrayList<TvShow>();
        ArrayList<String> genres = new ArrayList<String>(Arrays.asList("genre1", "genre2"));

        testData.add(new TvShow("show one", genres, "cbbc", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));
        testData.add(new TvShow("show two", genres, "nickelodeon", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));
        testData.add(new TvShow("show one", genres, "cbbc", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));

        ArrayList<TvShow> results = Filter.filterByName(testData ,"show one");
        Assert.assertTrue(results.size() == 2);
    }

    @Test
    public void testFilterByDate() throws IOException
    {
        //test dates include [2017-11-05,2017-11-06,2017-11-07]
        ArrayList<TvShow> NovFive = filter.filterByDate(new DateTime("2017-11-05T11:00:00+00:00"),filterShows);
        System.out.println(NovFive.toString());
        Assert.assertTrue(NovFive.size() == 2);
        ArrayList<TvShow> NovSix = filter.filterByDate(new DateTime("2017-11-06T11:00:00+00:00"),filterShows);
        System.out.println(NovSix.toString());
        Assert.assertTrue(NovSix.size() == 1);
        ArrayList<TvShow> NovSev = filter.filterByDate(new DateTime("2017-11-07T11:00:00+00:00"),filterShows);
        System.out.println(NovSev.toString());
        Assert.assertTrue(NovSev.size() == 2);

    }

    @Test
    public void testGetTomorrowDate() throws IOException {
        DateTime testDateOne = new DateTime("2017-12-04T11:00:00+00:00");
        Assert.assertEquals(filter.getTomorrowDate(testDateOne),new DateTime("2017-12-05T11:00:00+00:00"));
    }

    @Test
    public void testGetStartOfDate() throws IOException{
        DateTime testDateTwo = new DateTime("2017-12-04T11:00:00+00:00");
        Assert.assertTrue(filter.getStartOfDate(testDateTwo).equals(new DateTime("2017-12-04T00:00:00+00:00")));
    }

    @Test
    public void testGetStartOfTomorrow() throws IOException{
        DateTime testDateTwo = new DateTime("2017-12-04T11:00:00+00:00");
        Assert.assertTrue(filter.getTomorrowDate(filter.getStartOfDate(testDateTwo)).equals(new DateTime("2017-12-05T00:00:00+00:00")));

    }

    @Test
    public void testLoadShows()
    {
        Filter f = new Filter();
        Assert.assertTrue(f.loadShows("")  != null);
    }

    @Test
    public void testGetDates()
    {

        DateTime day = new DateTime();
        String[] validDays = new String[7];
        validDays[0] = day.toLocalDate().toString();
        for (int i = 1; i <= 6; i++) {
            validDays[i]= day.plusDays(i).toLocalDate().toString();
        }

        Filter f = new Filter();
        String[] days = f.getDates();

        for (int i = 0; i < 7; i ++)
        {
            Assert.assertTrue(days[i].equals(validDays[i]));
        }
    }
}
