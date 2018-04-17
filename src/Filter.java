

import org.apache.http.util.EntityUtils;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class Filter {
    public static double Rating = 5.5;
    String name;
    String genre;
    String network;
    String imageRoot;
    String description;
    private ArrayList<String> genres;

    public Filter() {

    }

    public ArrayList<TvShow> shows;

    public String[] getDates()
    {
        String[] days = new String[7];
        DateTime day = new DateTime();
        days[0] = day.toLocalDate().toString();
        for (int i = 1; i <= 6; i++) {
            days[i] = (day.plusDays(i).toLocalDate().toString());
        }

        return days;

    }

    public String loadShows(String date) {
        // this will work with the website api not the file
        //this.shows = new ArrayList<>();
        try {
            String url = "http://api.tvmaze.com/schedule?country=GB&date=" + date;

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.addHeader("accept", "application/json");
            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());
            return json;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static ArrayList<TvShow> filterByGenre(String genre, ArrayList<TvShow> shows) {
        ArrayList<TvShow> showsByGenre = new ArrayList<>();

        for (TvShow show : shows) {
            if (show.genre.size() == 0) {
                if (filterByDesc(genre, show)) {
                    show.genre.add(genre);
                }
                else
                {
                    show.genre.add("None");
                }
            }
            for (String g : show.genre) {
                if (g.toLowerCase().contains(genre.toLowerCase())) {
                    if(!showsByGenre.contains(show.name)){
                        showsByGenre.add(show);
                    }
                }
            }
        }
        return showsByGenre;
    }

    public static boolean filterByDesc(String genre, TvShow show) {
        try{
        if(show.description.toLowerCase().contains(genre.toLowerCase())){
            return true;
        }else{
            return false;
        }
        }catch (Exception e){
            return false;
        }
    }

    public static ArrayList<TvShow> filterByName(ArrayList<TvShow> sourceOfShows, String name)
    {
        ArrayList<TvShow> matchedResults = new ArrayList<TvShow>();

        for (TvShow show: sourceOfShows)
        {
            if (show.name.equals(name) && !matchedResults.contains(show))
            {
                matchedResults.add(show);
            }
        }

        return matchedResults;

    }

    //Method which takes an argument of String which is an image URL
    //It then turns this imageURL into an image and returns an ImageIcon
    public static ImageIcon getImage(String imgURL) throws IOException{
        URL url = new URL(imgURL);
        BufferedImage image = ImageIO.read(url);
        Image i = getScaledImage(image, 150, 140);
        return new ImageIcon(i);
    }
    public static Image getScaledImage(BufferedImage image, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    public DateTime getTomorrowDate(DateTime dateTime){
        return dateTime.plusDays(1);
    }

    public DateTime getStartOfDate(DateTime date){
        return date.toLocalDate().toDateTimeAtStartOfDay();
    }

    public ArrayList<TvShow> filterByDate(DateTime dateTime, ArrayList<TvShow> sourceOfShows){
        ArrayList<TvShow> filteredArrayList = new ArrayList<TvShow>();
        for (TvShow tvShow : sourceOfShows) {
            if(tvShow.viewingTime.isAfter(getStartOfDate(dateTime)) && tvShow.viewingTime.isBefore(getTomorrowDate(getStartOfDate(dateTime)))){
                filteredArrayList.add(tvShow);
            }
        }
        return filteredArrayList;
    }

    public static ArrayList<TvShow> filterByTime(ArrayList<TvShow> sourceOfShows, Map<String, ArrayList<int[]>> hours){
        ArrayList<TvShow> filteredArrayList = new ArrayList<TvShow>();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for(TvShow show : sourceOfShows){
            for(int i = 0; i < 7; i++){
                if(show.viewingTime.dayOfWeek().getAsText() == days[i]){
                    if(hours.get(days[i]) == null){
                        break;
                    }else{
                        for(int j = 0; j < hours.get(days[i]).size(); j++){
                            if(show.viewingTime.getHourOfDay() >= ((hours.get(days[i]).get(j))[0]) && show.viewingTime.getHourOfDay() <= ((hours.get(days[i]).get(j))[1])){
                                filteredArrayList.add(show);
                                System.out.println(3);
                            }
                        }

                    }
                }
            }

        }
        return filteredArrayList;
    }
}