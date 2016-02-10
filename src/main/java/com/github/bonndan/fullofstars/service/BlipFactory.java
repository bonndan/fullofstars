package com.github.bonndan.fullofstars.service;

import com.github.bonndan.fullofstars.models.Blip;
import org.kohsuke.github.GHRepository;

/**
 * Blip Factory.
 * 
 * 
 * 
 */
public class BlipFactory {

    public static Blip fromGHRepository(GHRepository ghr) {

        Blip blip = new Blip();
        blip.setTitle(ghr.getFullName());
        blip.setUrl(ghr.getHtmlUrl());
        blip.setLanguage(ghr.getLanguage());
        return blip;
    }
}
