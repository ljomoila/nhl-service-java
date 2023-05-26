package com.ljomoila.nhl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameData {
    Status status;

    public GameData(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
