package edu.siu;

import edu.siu.framework.ProgramFrame;
import edu.siu.google.query.DomainDetailsPageMiner;

/**
 * Created by Akec on 4/24/2017.
 */
public class Main {

    public static void main(String args[]){
        new ProgramFrame(new DomainDetailsPageMiner("https://www.googleapis.com/", "http:www.google.com/"));
    }

}
