package com.ljomoila.nhl.controller;

import com.ljomoila.nhl.domain.Game;
import com.ljomoila.nhl.domain.Player;
import com.ljomoila.nhl.domain.Team;
import com.ljomoila.nhl.facade.NhlFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class NhlController {
    private final NhlFacadeImpl facade;

    @Autowired
    public NhlController(NhlFacadeImpl facade) {
        this.facade = facade;
    }

    @GetMapping("/teams")
    public List<Team> getTeams() {
        return facade.getTeams();
    }

    @GetMapping("/player/{apiLink}")
    @ResponseBody
    public Player getPlayer(@PathVariable String apiLink) {
        return facade.getPlayer(apiLink);
    }

    @GetMapping("/games/{date}")
    @ResponseBody
    public List<Game> getGames(@PathVariable String date) {
        return facade.getGames(date);
    }

    // TODO: exception handling
}
