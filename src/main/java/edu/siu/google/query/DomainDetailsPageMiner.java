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

    private static final String API_KEY = "AIzaSyC2LUS6c0VpzqXvuq4IxhraiEQegnS9sJ8";
    private static final String CX_CODE = "009490387646566916815:kvn4wlmj7zw";

    public String baseUrl;
    public String googleUrl;

    public int requestCount;

    public String lastOutput;
    public JsonParser parser;

    public DomainDetailsPageMiner(String baseUrl, String googleUrl){
        this.baseUrl = baseUrl;
        this.googleUrl = googleUrl;

        parser = new JsonParser();
        requestCount = 1;
    }

    public LinkedList<DomainDetails> MineRequest(LinkedList<DomainDetails> domainList, String searchParam, int searchIndex, int numPages){
        if(searchIndex < 1) searchIndex = 1;

        String searchParamURLEncoded = URLEncoder.encode(searchParam);
        String query = "customsearch/v1?" +
        "key=" + API_KEY + "&cx=" + CX_CODE +
                "&start=" + searchIndex +
                "&q=" + searchParamURLEncoded;

        String apioutput = GoogleClient.CallGoogleSearchApi(this.baseUrl, query);
        lastOutput = apioutput;

        System.out.println("Mining Request#" + this.requestCount + ": "
                + this.googleUrl + "/#q=" + searchParamURLEncoded);

        if(domainList == null) domainList = new LinkedList<DomainDetails>(50);
        JsonObject pageObject = parser.parse(apioutput).getAsJsonObject();
        JsonArray itemsArray = pageObject.getAsJsonArray("items");

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
        if(searchIndex < numPages * 10) domainList = MineRequest(domainList, searchParam, searchIndex += 10, numPages * 10);

        return domainList;
    }


}
