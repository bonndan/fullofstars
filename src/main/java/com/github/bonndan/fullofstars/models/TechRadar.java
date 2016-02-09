package com.github.bonndan.fullofstars.models;

/**
 * @see https://www.thoughtworks.com/de/radar
 */
public class TechRadar {
    
    public enum Quadrant {
        Technologies,
        Tools,
        Platforms,
        Frameworks
    };
    
    public enum Assessment {
        Adopt,
        Trial,
        Assess,
        Hold
    };
}
