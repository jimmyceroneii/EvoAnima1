package com.example.evoanima;

import java.io.Serializable;

public class Anima implements Serializable {
    private String name;
    private String description;
    private String image_url;
    private int level;
    private int evolutionLevel;
    private String evolutionName;
    private String environment;

    // no evolution constructor
    public Anima(String name, String description, String image_url, int level, String environment) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.level = level;
        this.environment = environment;
    }

    public Anima() {

    }

    // evolution constructor
    public Anima(String name, String description, String image_url, int level, int evolutionLevel, String evolutionName, String environment) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.level = level;
        this.evolutionLevel = evolutionLevel;
        this.evolutionName = evolutionName;
        this.environment = environment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription() { this.description = description; }

    public String getDescription() {
        return description;
    }

    public String getEvolutionName() {
        return evolutionName;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setImage_url(String image_url) { this.image_url = image_url; }

    public int getLevel() {
        return level;
    }

    public void incrementLevel() {
        level++;
    }

    public void setEvolutionLevel(int evolutionLevel) {
        this.evolutionLevel = evolutionLevel;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getImage_url() {
        return image_url;
    }

    public int getEvolutionLevel() {
        return evolutionLevel;
    }

}
