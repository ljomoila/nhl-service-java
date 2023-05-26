package com.ljomoila.nhl.facade;

import com.ljomoila.nhl.domain.Game;
import com.ljomoila.nhl.domain.Player;
import com.ljomoila.nhl.domain.Team;

import java.util.List;

public interface NhlFacade {
    List<Team> getTeams();
    Player getPlayer(String apiLink);
    List<Game> getGames(String date);
}
