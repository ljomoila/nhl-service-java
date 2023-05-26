package com.ljomoila.nhl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedData {
    @JsonProperty("linescore")
    LineScore lineScore;
    BoxScore boxScore;

    public FeedData(@JsonProperty("linescore") LineScore lineScore, @JsonProperty("boxscore") BoxScore boxScore) {
        this.lineScore = lineScore;
        this.boxScore = boxScore;
    }

    public LineScore getLineScore() {

        return lineScore;
    }

    public BoxScore getBoxScore() {
        return boxScore;
    }
}
