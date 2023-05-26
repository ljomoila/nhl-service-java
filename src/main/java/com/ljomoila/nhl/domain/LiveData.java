package com.ljomoila.nhl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveData {
    // TODO: create POJO's
    @SerializedName("linescore")
    LineScore lineScore;
    @SerializedName("boxscore")
    BoxScore boxScore;

    public LiveData(LineScore lineScore, BoxScore boxScore) {
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
