import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dgowin on 07/11/2017.
 *
 */
public class Main {
    //System.out.println("dgowin");
    //System.out.println("jdaviee");
    //System.out.println("mtpilk");
    //System.out.println("ksanghb");
    //System.out.println("esenav");
    //system.out.println("zhood");
    //System.out.println("skapno");
    //System.out.println("hrsalm");



    public static void main(String[] args) throws IOException{
        /*
        TvGuide tvGuide = new TvGuide();
        tvGuide.jsonReader("src\\testSchedule.json");

        Filter f = new Filter(tvGuide.shows);
        ArrayList<TvShow> filteredByGenre = f.filterByGenre("Travel");

        for (TvShow s : filteredByGenre)
        {
            System.out.println(s.name);
            for (String g : s.genre)
            {
                System.out.println(g);
            }
        } */

        TvGuide test = new TvGuide();
        test.jsonReader("M:\\Year 3\\CE320\\squarereyes\\src\\testSchedule.json");

        for (TvShow s: test.shows
             ) {
            System.out.println(s.getName() + " Time: " + s.viewingTime.toString());


        }
    }


}
