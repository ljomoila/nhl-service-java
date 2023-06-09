package com.ljomoila.nhl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    private int id;
    private String fullName;
    private String lastName;
    private String nationality;
    @SerializedName("link")
    private String apiLink;

    private PlayerPosition position = PlayerPosition.Skater;

    public Player() {
    }

    public Player(int id, String fullName, String lastName, String nationality, String apiLink) {
        this.id = id;
        this.fullName = fullName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.apiLink = apiLink;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
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
                ", fullName='" + fullName + '\'' +
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
