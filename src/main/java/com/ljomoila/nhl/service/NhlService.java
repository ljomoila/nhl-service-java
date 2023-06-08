package com.ljomoila.nhl.service;

import com.google.gson.internal.LinkedTreeMap;
import com.ljomoila.nhl.domain.*;

import java.util.HashMap;
import java.util.List;

public interface NhlService {
    List<Team> getTeams();
    Player getPlayer(String link);
    LinkedTreeMap getPlayerStats(int id, String type);
    List<String> getScheduleGamesByDate(String date);
    LiveFeed getLiveFeed(String gamePath);
}
