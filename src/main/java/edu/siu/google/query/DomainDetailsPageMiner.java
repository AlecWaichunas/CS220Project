package edu.siu.google.query;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.siu.datastructures.LinkedList;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Alec on 4/11/2017.
 */
public class DomainDetailsPageMiner {

    //API KEY AND SEARCH ENGINE KEY
    private static final String API_KEY = "AIzaSyAnT72x36xnl1RjJgGDFwAh5DL__tTPa0o";//"AIzaSyC2LUS6c0VpzqXvuq4IxhraiEQegnS9sJ8";
    private static final String CX_CODE = "009490387646566916815:kvn4wlmj7zw";

    //BASE URL and the google url
    public String baseUrl;
    public String googleUrl;

    //how many total requests there are
    public int requestCount;
    //the output and Json parser
    public String lastOutput;
    public JsonParser parser;

    /**
     *
     * @param baseUrl the baseurl to parse
     * @param googleUrl the google api url
     */
    public DomainDetailsPageMiner(String baseUrl, String googleUrl){
        this.baseUrl = baseUrl;
        this.googleUrl = googleUrl;

        parser = new JsonParser();
        requestCount = 1;
    }

    /**
     * Gets the search results made by google
     *
     * @param domainList the found domains so far
     * @param searchParam the search parameters to search for
     * @param searchIndex what index to start from
     * @param numPages the total amount of pages to search
     * @return list of domains that have PDF in them
     */

    public LinkedList<DomainDetails> MineRequest(LinkedList<DomainDetails> domainList, String searchParam, int searchIndex, int numPages){
        //resets search index
        if(searchIndex < 1) searchIndex = 1;

        //Encodes url and puts the custom search query together
        String searchParamURLEncoded = URLEncoder.encode(searchParam + " PDF");
        String query = "customsearch/v1?" +
        "key=" + API_KEY + "&cx=" + CX_CODE +
                "&start=" + searchIndex +
                "&q=" + searchParamURLEncoded;

        //calls the apiOutput and stores it
        String apioutput = GoogleClient.CallGoogleSearchApi(this.baseUrl, query);
        lastOutput = apioutput;

        System.out.println("Mining Request#" + this.requestCount + ": "
                + this.googleUrl + "/#q=" + searchParamURLEncoded);

        //creates domain list if it is the first call
        if(domainList == null) domainList = new LinkedList<DomainDetails>(50);
        //uses google Java JSon parser to get values
        JsonObject pageObject = parser.parse(apioutput).getAsJsonObject();
        JsonArray itemsArray = pageObject.getAsJsonArray("items");

        //uses google Java JSon parser to get values
        for(Iterator<JsonElement> iter = itemsArray.iterator(); iter.hasNext();){

            JsonObject domainObject = iter.next().getAsJsonObject();
            String title = domainObject.get("title").getAsString();
            String link = domainObject.get("link").getAsString();
            String displayLink = domainObject.get("displayLink").getAsString();
            String snippet = domainObject.get("snippet").getAsString();
            String mime = null;
            if(domainObject.has("mime"))
                mime = domainObject.get("mime").getAsString();
            String fileFormat = null;
            if(domainObject.has("fileFormat"))
                fileFormat = domainObject.get("fileFormat").getAsString();
            String formattedUrl = domainObject.get("formattedUrl").getAsString();
            String[] imgUrl = new String[3];

            if(domainObject.has("pagemap") && domainObject.getAsJsonObject("pagemap").has("scraped") &&
                    domainObject.getAsJsonObject("pagemap").getAsJsonArray("scraped").size() > 0 &&
                    ((JsonObject) domainObject.getAsJsonObject("pagemap").getAsJsonArray("scraped").get(0)).has("image_link"))
                    imgUrl[0] = ((JsonObject) domainObject.getAsJsonObject("pagemap").getAsJsonArray("scraped").get(0)).get("image_link").getAsString();

            if(domainObject.has("pagemap") && domainObject.getAsJsonObject("pagemap").has("cse_thumbnail") &&
                    domainObject.getAsJsonObject("pagemap").getAsJsonArray("cse_thumbnail").size() > 0 &&
                    ((JsonObject) domainObject.getAsJsonObject("pagemap").getAsJsonArray("cse_thumbnail").get(0)).has("src"))
                    imgUrl[1] = ((JsonObject) domainObject.getAsJsonObject("pagemap").getAsJsonArray("cse_thumbnail").get(0)).get("src").getAsString();

            if(domainObject.has("pagemap") && domainObject.getAsJsonObject("pagemap").has("metatags") &&
                    domainObject.getAsJsonObject("pagemap").getAsJsonArray("metatags").size() > 0 &&
                    ((JsonObject) domainObject.getAsJsonObject("pagemap").getAsJsonArray("metatags").get(0)).has("og:image"))
                    imgUrl[2] = ((JsonObject) domainObject.getAsJsonObject("pagemap").getAsJsonArray("metatags").get(0)).get("og:image").getAsString();


            if(!link.toLowerCase().contains("pdf")) continue;
            // Sets all of DomainDetails properties and stores it to the array
            DomainDetails domainDetails = new DomainDetails();
            domainDetails.title = title;
            domainDetails.link = link;
            domainDetails.displayLink = displayLink;
            domainDetails.snippet = snippet;
            domainDetails.mime = mime;
            domainDetails.fileFormat = fileFormat;
            domainDetails.formattedUrl = formattedUrl;
            
            domainDetails.image_link = imgUrl[0];
            domainDetails.thumbnail = imgUrl[1];
            domainDetails.og_image = imgUrl[2];

            domainList.add(domainDetails);
        }

        requestCount++;
        //calls the method again if the amount of domains were less than the domaain calls
        if(searchIndex < numPages * 10) domainList = MineRequest(domainList, searchParam, searchIndex + 10, numPages);

        return domainList;
    }


}
