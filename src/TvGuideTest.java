import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Assert;

/**
 * Created by ksanghb on 14/11/2017.
 */
public class TvGuideTest {
    public TvGuide testGuide;
    @org.junit.Before
    public void setUp() throws Exception {
        testGuide = new TvGuide();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

   @org.junit.Test (expected = FileNotFoundException.class)
   public void FileDoesntExistTest() throws Exception {
       testGuide.jsonReader("fhfhj.txt");


    }

    @org.junit.Test
    public void jsonRetrievalTest() throws IOException
    {
        testGuide.jsonReader("M:\\Year 3\\CE320\\squarereyes\\src\\testSchedule.json");
        Assert.assertTrue(testGuide.shows.get(0).name.equals("Breakfast"));
        Assert.assertTrue(testGuide.shows.get(1).name.equals("The Andrew Marr Show"));
        Assert.assertTrue(testGuide.shows.get(4).network.equals("CBBC"));
    }

    @org.junit.Test
    public void testJsonRetrievalGenres() throws IOException
    {
        testGuide.jsonReader("M:\\Year 3\\CE320\\squarereyes\\src\\testSchedule.json");
        Assert.assertTrue(testGuide.shows.get(22).genre.get(0).equals("Comedy"));
        Assert.assertTrue(testGuide.shows.get(22).genre.get(1).equals("Travel"));
    }





}
