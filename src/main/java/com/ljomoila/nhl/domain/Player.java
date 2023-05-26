package com.ljomoila.nhl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    private int id;
    private String name;
    private String lastName;
    private String nationality;
    private String apiLink;
    private PlayerPosition position = PlayerPosition.Skater;

    public Player() {
    }

    public Player(int id, String fullName, String lastName, String nationality, String apiLink) {
        this.id = id;
        this.name = fullName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.apiLink = apiLink;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public String getApiLink() {
        return apiLink;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", apiLink='" + apiLink + '\'' +
                ", position=" + position +
                '}';
    }

    public enum PlayerPosition {
        Skater,
        Goalie
    }
}
