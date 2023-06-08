package com.ljomoila.nhl.facade;

import com.google.gson.internal.LinkedTreeMap;
import com.ljomoila.nhl.domain.Game;
import com.ljomoila.nhl.domain.Player;
import com.ljomoila.nhl.domain.Team;

import java.util.HashMap;
import java.util.List;

public interface NhlFacade {
    List<Team> getTeams();
    Player getPlayer(String apiLink);
    LinkedTreeMap getPlayerStats(int id, String type);
    List<Game> getGames(String date);
}
