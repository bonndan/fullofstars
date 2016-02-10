package com.github.bonndan.fullofstars.models;

import java.io.Serializable;
import java.net.URL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *A blip on the tech radar.
 * 
 * 
 * 
 */
@Entity
public class Blip implements Serializable  {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @MapsId @ManyToOne
    private User user;
    
    @NotEmpty
    private String title;
    private URL url;
    private String language;
    private TechRadar.Quadrant quadrant;
    
    @Max(100)
    private Integer proximity;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getProximity() {
        return proximity;
    }

    public void setProximity(Integer proximity) {
        this.proximity = proximity;
    }
    
}
