package com.github.bonndan.fullofstars.models;

import java.net.URL;
import org.kohsuke.github.GHRepository;

/**
 *A blip on the tech radar.
 * 
 * 
 * 
 */
public class Blip {

    public static Blip fromGHRepository(GHRepository ghr) {
        
        Blip blip = new Blip();
        blip.setTitle(ghr.getFullName());
        blip.setUrl(ghr.getHtmlUrl());
        blip.setLanguage(ghr.getLanguage());
        return blip;
    }
    
    private String title;
    private URL url;
    private String language;
    private TechRadar.Quadrant quadrant;
    private TechRadar.Assessment assessment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public TechRadar.Quadrant getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(TechRadar.Quadrant quadrant) {
        this.quadrant = quadrant;
    }

    public TechRadar.Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(TechRadar.Assessment assessment) {
        this.assessment = assessment;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
}
