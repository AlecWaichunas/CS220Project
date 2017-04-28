package edu.siu.google.query;

/**
 * Created by Alec on 4/11/2017.
 */
public class DomainDetails implements Comparable<DomainDetails> {

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
    
    public int compareTo(DomainDetails d){
    	if(!this.fileFormat.equals("pdf")){
    		return 0;
    	} else if(this.fileFormat.equals("pdf") && d.fileFormat.equals("pdf")){
    		return 0;
    	} else return 1;
	}

}
