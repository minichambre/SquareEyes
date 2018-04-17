import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;


/**
 * Created by dgowin on 21/11/2017.
 */
public class TrainingTest {
    public ArrayList<TvShow> shows;
    public Training training;
    @Before
    public void setUp() throws Exception {
        training = new Training();

        shows = new ArrayList<TvShow>();
        ArrayList<String> genres = new ArrayList<String>(Arrays.asList("genre1", "genre2"));


        shows.add(new TvShow("show one", genres, "network", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));
        genres.add("genre3");
        shows.add(new TvShow("show two", genres, "network", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" ));
    }


    @Test
    public void getGenresTest() throws Exception {
        training.getGenres(shows);
        Assert.assertTrue(training.setOfGenres.size() == 3);
    }

    @Test
    public void testFavourtingAShow()
    {
        TvGuide tvGuide = new TvGuide();
        try {
            tvGuide.jsonReader("src\\testSchedule.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] rows = new Object[TvGuide.shows.size()][4];
        for (int i = 0; i < TvGuide.shows.size(); i++) {
            rows[i][0]= TvGuide.shows.get(i).name;
            rows[i][1]= TvGuide.shows.get(i).network;
            rows [i][2] = TvGuide.shows.get(i).genre.toString().replace("[", "").replace("]", "");
            rows [i][3] = new Boolean(false);
        }

        JTable table = new JTable(new TrainingTableModel(rows));
        Assert.assertTrue(Training.preferredNames.size() == 0);
        Assert.assertTrue(Training.preferredGenres.size() == 0);
        table.setValueAt(true,3,3);
        Assert.assertTrue(Training.preferredNames.size() == 1);
        Assert.assertTrue(Training.preferredGenres.size() == 1);


    }


}