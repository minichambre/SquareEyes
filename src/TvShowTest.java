import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by jdaviee on 14/11/2017.
 */
public class TvShowTest {


    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void TestConvertStringToInt() throws Exception {
        String s = "9";
        int x = TvShow.convertStringToInt(s);
        int expected = 9;
        Assert.assertEquals(expected, x);
    }

    @org.junit.Test
    public void TestConvertStringToDateTime() throws Exception {
        String s = "2017-11-05T11:00:00+00:00";
        DateTime testTime = TvShow.convertStringToDateTime(s);
        DateTime expected = new DateTime (2017, 11, 05, 11,0,0,0);
        Assert.assertEquals(DateTime.class, testTime.getClass());
        Assert.assertEquals(expected, testTime);
    }

    @Test
    public void TestConvertStringToDouble() throws Exception {
        String s = "3.5";
        Double x = TvShow.convertStringToDouble(s);
        Double expected = 3.5;
        Assert.assertEquals(Double.class, x.getClass());
        Assert.assertEquals(expected, x);
    }

    @Test
    public void testAddRating()
    {
        ArrayList<String> genres = new ArrayList<String>();
        genres.add("Comedy");
        TvShow test = new TvShow("testName", genres, "BBC1", "a", "b", "10", "10", "2017-11-05T11:00:00+00:00");
        test.rateShow(9);
        Assert.assertTrue(test.getRating() == 9);
    }

    @Test
    public void testDescriptionFiler(){
        ArrayList<String> genres = new ArrayList<String>();
        ArrayList<TvShow> tvShows = new ArrayList<>();
        TvShow test = new TvShow("testName", genres, "BBC1", "a", "travel", "10", "10", "2017-11-05T11:00:00+00:00");
        tvShows.add(test);
        Assert.assertTrue(test.genre.size() == 0);
        Assert.assertTrue(Filter.filterByGenre("Travel", tvShows).equals(tvShows));

    }

    @Test
    public void testRemoveHTMLTags(){
        ArrayList<String> genres = new ArrayList<>();

        TvShow test = new TvShow("testName", genres, "BBC1", "a", "<p> description <b> <div ='code'>test</div></b> </p>", "10", "10", "2017-11-05T11:00:00+00:00");
        System.out.println(test.description);
        Assert.assertTrue(!(test.description.contains("<p>")&&!(test.description.contains("</p>"))));
    }

}