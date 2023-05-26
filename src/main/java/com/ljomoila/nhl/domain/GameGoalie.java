package com.ljomoila.nhl.domain;

import java.text.DecimalFormat;

public class GameGoalie extends GamePlayer {
    private int shots;
    private int saves;
    private Double savePercentage;

    public GameGoalie(int id, String lastName, String nationality, int assists, int goals, int shots, int saves, Double savePercentage, String apiLink) {
        super(id, lastName, nationality, assists, goals, apiLink);

        this.shots = shots;
        this.saves = saves;
        this.savePercentage = savePercentage;
    }

    public int getShots() {
        return shots;
    }

    public int getSaves() {
        return saves;
    }

    public String getSavePercentage() {
        return new DecimalFormat("#.##").format(savePercentage) + "%";
    }

    @Override
    public Player.PlayerPosition getPosition() {
        return PlayerPosition.Goalie;
    }

    @Override
    public String getPoints() {
        if (goals == 0 && assists == 0) return null;

        return super.getPoints();
    }

    @Override
    public String toString() {
        return "GameGoalie{" +
                "shots=" + shots +
                ", saves=" + saves +
                ", savePercentage=" + savePercentage +
                '}';
    }
}
