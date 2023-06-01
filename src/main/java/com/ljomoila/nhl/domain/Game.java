package com.ljomoila.nhl.domain;

public class Game {
    private GameTeam home;
    private GameTeam away;
    private String timeRemaining;
    private String period;
    private GameStatus status;

    public Game() {
    }

    public Game(GameTeam home, GameTeam away, String timeRemaining, GameStatus status, String period) {
        this.home = home;
        this.away = away;
        this.timeRemaining = timeRemaining;
        this.status = status;
        this.period = period;
    }

    public GameTeam getHome() {
        return home;
    }

    public GameTeam getAway() {
        return away;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public GameStatus getStatus() {
        return status;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "Game{" +
                "home=" + home +
                ", away=" + away +
                ", timeRemaining='" + timeRemaining + '\'' +
                ", period='" + period + '\'' +
                ", status=" + status +
                '}';
    }

    public enum GameStatus {
        InProgress,
        Final,
        Scheduled
    }
}
