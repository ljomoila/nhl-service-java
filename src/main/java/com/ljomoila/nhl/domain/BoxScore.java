package com.ljomoila.nhl.domain;

import com.google.gson.internal.LinkedTreeMap;

public class BoxScore {
    LinkedTreeMap teams;

    public BoxScore(LinkedTreeMap teams) {
        this.teams = teams;
    }

    public LinkedTreeMap getTeams() {
        return teams;
    }
}
