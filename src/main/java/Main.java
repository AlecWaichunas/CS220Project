import edu.siu.google.query.DomainDetails;
import edu.siu.google.query.DomainDetailsPageMiner;
import edu.siu.google.query.DomainList;
import edu.siu.google.query.GoogleClient;

/**
 * Created by Akec on 4/11/2017.
 */
public class Main {

    private static final String API_KEY = "AIzaSyC2LUS6c0VpzqXvuq4IxhraiEQegnS9sJ8";
    private static final String CX_CODE = "009490387646566916815:kvn4wlmj7zw";

    public static void main(String args[]){
        System.out.println(GoogleClient.CallGoogleSearchApi("https://www.googleapis.com/customsearch/v1?",
                "key=" + API_KEY + "&cx=" + CX_CODE +
                "&q=Discrete%20Mathematics%20and%20Its%20Applications"));

        DomainDetailsPageMiner ddpm = new DomainDetailsPageMiner("https://www.googleapis.com/",
                "https://www.google.com/", API_KEY, CX_CODE);

        DomainDetails[] dl = ddpm.MineRequest(null, "Discrete Mathematics and Its Applications Seventh edition",
                1).toArray();

        for(int i = 0; i < dl.length; i++){
            System.out.println(i + ":\n" + dl[i].link);
        }

    }

}
