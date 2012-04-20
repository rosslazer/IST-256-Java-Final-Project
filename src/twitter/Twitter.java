/*
 * This program uses the Twitter Search API to get tweets that include
 *     a particular hashtag topic
 * As described by Twitter, these will be selected from the popular
 *     and recent tweets containing that tag
 * For more information on the types of searches that can be made:
 *       keywords, userids, geolocations, dates, only tweets since last search, etc.
 *     See https://dev.twitter.com/docs/using-search
 * This search is set up only to retrieve 4 pages of 15 tweets each.  For other
 *     parameters besides page, see this twitter search api page,
 *     with parameters and examples:  https://dev.twitter.com/docs/api/1/get/search
 *     e.g. number of tweets per page can be set to something besides 15
 *     and the language can be restricted,
 * The Twitter API search and parameters are encoded in a URL string
 *     and the results are returned as a JSON structure
 *     that is easily parsed by getting values of node id strings.
 *     For more results id strings, see the result examples in the search API page above.
 * Also note that if you want to get user information or to download large volumes of tweets,
 *    you can use the REST API and the Streaming API.  Both require an authorization code.
 */
package twitter;
import java.net.URL;
import java.io.*;
 
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * @author njmccracken
 * Based on a program by Shekhar Shah
 */
public class Twitter  { 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        // define a hashtag topic to search for (this will represent #obama)
        //String hashtagTopic = "obama";
        //String hashtagTopic = "ladygaga";

        //System.out.println("Tweets retrieved with #" + hashtagTopic);
        String topic = "goldfish";

        // send search request using the Twitter search http encoded URL
        
        URL url;
        int count = 0;
        String state = "NJ";
        int maxPage = 4;
        try
        {
            for (int page = 1; page <= maxPage; page++)
            {
            url= new URL("http://v2.factual.com/api/v2/tables/KPuQDT/read?APIKey=SXwvvEGxtSQYfLzA7SIgVxfJtqvfBqU2qyh8vASNIeG4b0FUVTxVbUtvLUDtrFCc&filters={%22state%22:{%22$eq%22:" +  "%22" + state +  "%22%20" +" }}&sort={%22lastname%22:1}"  );                //url= new URL("http://search.twitter.com/search.json?q=%23"
                //        + hashtag + "&page=" + page);
                ByteArrayOutputStream urlOutputStream = new ByteArrayOutputStream();
                IOUtils.copy(url.openStream(), urlOutputStream);
                String urlContents = urlOutputStream.toString();

                // parse the JSON object returned
                JSONObject jsonO = new JSONObject(urlContents);
                // System.out.println(jsonO.toString());
                JSONArray jsonArray=jsonO.getJSONArray("results");
                // for each item in the results, get the tweet id, the data, the tweet and the user
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    count ++;
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //System.out.println(jsonObject.toString());
                    //tweetUserID = jsonObject.getString("from_user");
                    //tweetID = jsonObject.getString("id");
                    //tweetText = jsonObject.getString("text");
                    //tweetDate = jsonObject.getString("created_at");
                    //System.out.println("Tweet " + count + "::  Twitter ID: " + tweetID
                      //      + "  Date: " + tweetDate + " From User: " + tweetUserID);
                    //System.out.println("    Text: " + tweetText );
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
