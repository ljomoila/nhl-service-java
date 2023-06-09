package com.ljomoila.nhl.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GamePlayer extends Player {
    int assists = 0;
    int goals = 0;
    String points = null;

    public GamePlayer() {
    }

    public GamePlayer(int id, String fullName, String lastName, String nationality, int assists, int goals, String apiLink) {
        super(id, fullName, lastName, nationality, apiLink);

        this.assists = assists;
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getGoals() {
        return goals;
    }

    public String getPoints() {
        return goals + "+" + assists;
    }

    @Override
    public String toString() {
        return "GamePlayer{" +
                "assists=" + assists +
                ", goals=" + goals +
                ", points='" + points + '\'' +
                '}';
    }
}
