package com.ljomoila.nhl.service;

import com.ljomoila.nhl.domain.*;

import java.util.List;

public interface NhlService {
    List<Team> getTeams();
    Player getPlayer(String link);
    List<String> getScheduleGamesByDate(String date);
    LiveFeed getLiveFeed(String gamePath);
}
