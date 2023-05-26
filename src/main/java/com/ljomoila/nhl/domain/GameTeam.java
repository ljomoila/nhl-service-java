package com.ljomoila.nhl.domain;

import java.util.List;

public class GameTeam extends Team {
    private int goals;
    private List<GamePlayer> players;

    public GameTeam(int id, String name, int goals, List<GamePlayer> players, String link) {
        super(id, link);

        this.goals = goals;
        this.players = players;
    }


    public int getGoals() {
        return goals;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "GameTeam{" +
                "goals=" + goals +
                ", players=" + players +
                '}';
    }
}
