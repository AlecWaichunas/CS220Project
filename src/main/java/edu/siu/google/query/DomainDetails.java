package edu.siu.google.query;

import java.util.Comparator;

/**
 * Created by Alec on 4/11/2017.
 */
public class DomainDetails implements Comparable<DomainDetails> {

    /**
     * used to compare with other DomainDetails
     *
     * @param o comparing this object to another
     * @return value of how much bigger or lower this object is to the other
     */

    public static Comparator<DomainDetails> LinkComparator = new Comparator<DomainDetails>() {
        public int compare(DomainDetails o1, DomainDetails o2) {
            return o2.title.compareTo(o1.title);
        }
    };

    //properties of a gathered domain details
    public String title;
    public String link;
    public String displayLink;
    public String snippet;
    public String mime;
    public String fileFormat;
    public String formattedUrl;

    public String image_link;
    public String thumbnail;
    public String og_image;


    public int compareTo(DomainDetails o) {
        if(this.link == null) return 1;
        if(this.link.toLowerCase().contains("pdf")){
            return 0;
        } else if(this.link.toLowerCase().contains("pdf") && o.link.toLowerCase().contains("pdf")){
            return 0;
        } else return 1;
    }
}
