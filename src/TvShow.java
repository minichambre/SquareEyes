/**
 * Created by jdaviee on 14/11/2017.
 */

import org.joda.time.DateTime;

import java.util.ArrayList;

public class TvShow {
    private static final int DEFAULT_RATING = 5;
    public static final int MAX_NUMBER_OF_TIME_VALUES = 9;
    String name;
    ArrayList<String> genre;
    String network;
    String imageRoot;
    String description;
    double rating;
    int episode;
    int duration;
    DateTime viewingTime;

    @Override
    public String toString() {
        return "TvShow{" +
                "name='" + name + '\'' +
                ", genre=" + genre.toString() +
                ", network='" + network + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", episode=" + episode +
                ", duration=" + duration +
                ", viewingTime=" + viewingTime +
                '}';
    }


    public String getName(){
        return name;
    }

    public TvShow(String name, ArrayList<String> genre, String network, String imageRoot, String description,
                  String episode, String duration, String viewingTime) {
        this.rating = DEFAULT_RATING;
        this.name = name;
        this.genre = genre;
        this.network = network;
        this.imageRoot = imageRoot;
        this.description = removeHTMLTags(description);
        this.episode = convertStringToInt(episode);
        this.duration = convertStringToInt(duration);
        this.viewingTime = convertStringToDateTime(viewingTime);
    }

    /**
     * Converts string to an Int
     * @param s
     * @return
     */
    public static int convertStringToInt(String s){
        if (s != null)
        {return Integer.parseInt(s);}
        return -1;
    }

    /**
     * Converts string to a Double
     * @param s
     * @return
     */
    public static double convertStringToDouble(String s){
       return Double.parseDouble(s);
    }

    /**
     * Converts string to a DateTime
     * @param s
     * @return
     */
    public static DateTime convertStringToDateTime(String s){
        s.trim();

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()){
            if (c == '-' || c == 'T' || c == ':' || c == '+'){
                c = '/';
            }
            sb.append(c);
        }
        String givenTimes[] = sb.toString().split("/");
        int convertedTimes[] = new int[MAX_NUMBER_OF_TIME_VALUES];

        for(int i=0; i<givenTimes.length; i++){
            int timeInInt = convertStringToInt(givenTimes[i]);
            convertedTimes[i] = timeInInt;
        }

        int year = convertedTimes[0];
        int month = convertedTimes[1];
        int day = convertedTimes[2];
        int hour = convertedTimes[3];
        int minute = convertedTimes[4];
        int second = convertedTimes[5];
        int millisecond = convertedTimes[6];

        return new DateTime(year, month, day, hour, minute, second, millisecond);
    }

    public void rateShow(double x)
    {
        this.rating = x;
    }

    public double getRating()
    {
        return this.rating;
    }

    public String removeHTMLTags(String description){
        if(description == null){
            return "";
        }else{
            String descriptionHTMLRemoved = "";
            descriptionHTMLRemoved = "" + description.replaceAll("\\<.*?>","");
            return descriptionHTMLRemoved;
        }
    }

}
