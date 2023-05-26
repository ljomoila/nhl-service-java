package com.ljomoila.nhl.domain;

import com.google.gson.internal.LinkedTreeMap;

public class LineScore {
    String currentPeriodOrdinal;
    String currentPeriodTimeRemaining;
    LinkedTreeMap teams;

    public LineScore(String currentPeriodOrdinal, String currentPeriodTimeRemaining, LinkedTreeMap teams) {
        this.currentPeriodOrdinal = currentPeriodOrdinal;
        this.currentPeriodTimeRemaining = currentPeriodTimeRemaining;
        this.teams = teams;
    }

    public String getCurrentPeriodOrdinal() {
        return currentPeriodOrdinal;
    }

    public String getCurrentPeriodTimeRemaining() {
        return currentPeriodTimeRemaining;
    }

    public LinkedTreeMap getTeams() {
        return teams;
    }
}
