package edu.siu.google.query;

/**
 * Created by Alec on 4/11/2017.
 */
public class DomainDetails implements Comparable<DomainDetails> {

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

    /**
     *
     * @param o comparing this object to another
     * @return value of how much bigger or lower this object is to the other
     */
    public int compareTo(DomainDetails o) {
        return displayLink.compareTo(o.displayLink);
    }
}
