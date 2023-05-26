package com.ljomoila.nhl.domain;

import com.google.gson.internal.LinkedTreeMap;

public class LiveFeed {
    private LiveData liveData;
    private GameData gameData;

    public LiveFeed(LiveData liveData, GameData game) {
        this.liveData = liveData;
        this.gameData = game;
    }

    public LiveData getLiveData() {
        return liveData;
    }

    public GameData getGameData() {
        return gameData;
    }
}
