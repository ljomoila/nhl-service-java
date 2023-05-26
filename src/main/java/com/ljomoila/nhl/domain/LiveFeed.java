package com.ljomoila.nhl.domain;

import com.google.gson.internal.LinkedTreeMap;

public class LiveFeed {
    private LinkedTreeMap feed;
    private GameData game;

    public LiveFeed(LinkedTreeMap feed, GameData game) {
        this.feed = feed;
        this.game = game;
    }

    public LinkedTreeMap getFeed() {
        return feed;
    }

    public GameData getGame() {
        return game;
    }
}
