package com.ljomoila.nhl.service;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.ljomoila.nhl.domain.*;
import com.ljomoila.nhl.integration.NhlClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.*;

@Component
public class NhlServiceImpl implements NhlService {
    private final NhlClient client;

    @Autowired
    public NhlServiceImpl(NhlClient client) {
        this.client = client;
    }

    @Override
    @Cacheable("teams")
    public List<Team> getTeams() {
        try {
            String json = this.client.get(NhlClient.API_PATH + "/teams");

            Type teamListType = new TypeToken<ArrayList<Team>>(){}.getType();
            List<Team> teams = new Gson().fromJson(getJsonStringByKey(json, "teams"), teamListType);

            return teams;
        } catch(Exception e) {
            throw e;
        }
    }

    @Override
    @Cacheable(value = "player", key = "#link")
    public Player getPlayer(String link) {
        try {
            String json = this.client.get(link);

            Map<String, Object> retMap = new Gson().fromJson(
                    json, new TypeToken<HashMap<String, Object>>() {}.getType()
            );

            List<LinkedTreeMap> playerMap = (List<LinkedTreeMap>) Arrays.asList(retMap.get("people")).get(0);
            String playerJson = new Gson().toJson(playerMap.get(0));

            return new Gson().fromJson(playerJson, new TypeToken<Player>(){}.getType());
        } catch(Exception e) {
            throw e;
        }
    }

    @Override
    @Cacheable(value = "games", key = "#date")
    public List<String> getScheduleGamesByDate(String date) {
        try {
            String json = this.client.get(NhlClient.API_PATH + "/schedule?date=" + date);

            Map<String, Object> retMap = new Gson().fromJson(
                    json, new TypeToken<HashMap<String, Object>>() {}.getType()
            );

            // TODO: create POJO's
            List<LinkedTreeMap> dates = (List<LinkedTreeMap>) Arrays.asList(retMap.get("dates")).get(0);
            List<LinkedTreeMap> games = (List<LinkedTreeMap>) dates.get(0).get("games");

            List<String> gamePaths = new ArrayList<>();
            for (LinkedTreeMap game : games) {
                gamePaths.add((String) game.get("link"));
            }

            return gamePaths;
        } catch(Exception e) {
            throw e;
        }
    }

    @Override
    public LiveFeed getLiveFeed(String gamePath) {
        try {
            String json = this.client.get(gamePath);

            Type liveFeedType = new TypeToken<LiveFeed>(){}.getType();
            LiveFeed liveFeed = new Gson().fromJson(json, liveFeedType);

            return liveFeed;
        } catch(Exception e) {
            throw e;
        }
    }

    private static String getJsonStringByKey(String json, String key) {
        Gson gson = new Gson();
        Map<String, Object> retMap = gson.fromJson(
                json, new TypeToken<HashMap<String, Object>>() {}.getType()
        );

        return gson.toJson(retMap.get(key));
    }
}
