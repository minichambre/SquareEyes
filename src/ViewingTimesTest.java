import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by ksanghb on 08/12/2017.
 */
public class ViewingTimesTest {
    ViewingTimes theApp = new ViewingTimes();
    @Before
    public void setUp(){
        theApp.viewingHours.clear();
    }
    @Test
    public void mapTest(){
        int[] times = new int[2];
        times[0] = 1;
        times[1] = 5;

        int[] times2 = new int[2];
        times2[0] = 5;
        times2[1] = 12;

        ArrayList<int[]> vals = new ArrayList<int[]>();
        ArrayList<int[]> vals2 = new ArrayList<int[]>();
        vals.add(times);
        theApp.viewingHours.put("Monday", vals);

        vals2.add(times);
        vals2.add(times2);
        theApp.viewingHours.put("Tuesday", vals2);

        assertTrue(theApp.viewingHours.get("Monday").size() == 1);
        System.out.println(theApp.viewingHours.get("Monday").size());
        assertTrue(theApp.viewingHours.get("Tuesday").size() == 2);
        System.out.println(theApp.viewingHours.get("Tuesday").size());
    }

}