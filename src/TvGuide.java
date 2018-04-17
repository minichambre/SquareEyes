
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;

import java.io.FileReader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ksanghb on 14/11/2017.
 */



public class TvGuide{
    public static ArrayList<TvShow> shows = new ArrayList<TvShow>();

    //Provide with a JSON file, which will be read and an arrayList of TvShow will be created from it.
    public void jsonReader(String jsonString )throws IOException{


        ObjectMapper objectMapper = new ObjectMapper();


        JsonNode array = objectMapper.readValue(jsonString, JsonNode.class);
        

        //Loop through all TV Shows listed in Json File
        for (int x = 0; x < array.size(); x++)
        {
        	shows.add(createTvShowFromJson(array.get(x)));
        }

    }
    
    //Each Json Node is passed to this method and then probed for specific elements we require.
    //These elements are then combined into a TvShow object and an array of them are returned
    private  TvShow createTvShowFromJson(JsonNode root)
    {
    	String description,episode,duration,viewingTime,name,network,imageRoot;
    	description=episode=duration=viewingTime=name=network =imageRoot = "Not Found";
        ArrayList<String> genres = new ArrayList<String>();
    	
    	try
    	{
    		//retrievable root node
            description = returnElementFromJson(root,"summary");
            episode = returnElementFromJson(root,"number");            
            duration = returnElementFromJson(root,"runtime");
            viewingTime = returnElementFromJson(root, "airstamp");
            
            
            //searching show node
            JsonNode deeperDetails = root.get("show");           
            name = returnElementFromJson(deeperDetails,"/name");

            JsonNode genresNode = deeperDetails.get("genres");
            for (int x = 0; x < genresNode.size(); x++) {
                genres.add(genresNode.get(x).textValue());
            }
            if (genres.size() == 0)
            {
                //there is no genre. Try looking at Type to see if one exists.
                genres.add(returnElementFromJson(deeperDetails,"/type"));
            }

            //searching networks node
            JsonNode networkDetails = deeperDetails.get("network");           
            network = returnElementFromJson(networkDetails,"name");
            
            //searching images node
            JsonNode imageDetails = deeperDetails.get("image");
            imageRoot = returnElementFromJson(imageDetails,"medium");
    	}catch (Exception e){}

        
        return new TvShow(name, genres, network, imageRoot, description, episode, duration, viewingTime);
    }
    
    //Method for accessing data from a nodes element.
    private  String returnElementFromJson(JsonNode object, String request)
    {
    	String value = "Not Found";
    	try
    	{
    		if (request.charAt(0) == '/')
    		{
    			value = object.at(request).textValue();
    		}
    		else
    		{		
        		value = object.get(request).textValue();
    		}
    	}catch(Exception e){System.out.println(request + " was not found.");}
    	
    	return value;
    }


}
