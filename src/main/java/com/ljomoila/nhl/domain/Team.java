package com.ljomoila.nhl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {
    private int id;
    private String name;
    private String shortName;
    private String abbreviation;
    private String apiLink;

    public Team(int id, String link) {
        this.id = id;
        this.apiLink = link;
    }

    public Team(int id, String name, String shortName, String abbreviation, String link) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.abbreviation = abbreviation;
        this.apiLink = link;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getApiLink() {
        return apiLink;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}
