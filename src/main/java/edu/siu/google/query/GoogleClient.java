package edu.siu.google.query;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alec on 4/11/2017.
 */
public class GoogleClient  {

    /**
     *
     * @param baseUrl google api url
     * @param query search query
     * @return the apioutput
     */
    public static String CallGoogleSearchApi(String baseUrl, String query){
        String apiOutput = null;

        try {
            //creates the connection
            URL url = new URL(baseUrl + query);
            URLConnection urlConnection = url.openConnection();
            //opens the input stream, so we can get the api JSON output
            InputStream is = urlConnection.getInputStream();
            if(is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                //reads line by line and stores the JSON output
                StringBuffer output = new StringBuffer();
                String t;
                while (true) {
                    t = reader.readLine();
                    if (t != null) {
                        output.append(t);
                    } else break;
                }
                apiOutput = output.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return apiOutput;
    }

    //NOT IMPLEMENTED YET
    public static boolean DownloadPDFFromURL(String Url){
        return false;
    }

}
