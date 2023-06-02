package com.ljomoila.nhl.controller;

import com.ljomoila.nhl.domain.Game;
import com.ljomoila.nhl.domain.Player;
import com.ljomoila.nhl.domain.Team;
import com.ljomoila.nhl.exception.NhlException;
import com.ljomoila.nhl.facade.NhlFacadeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class NhlController {
    private static final Logger logger = LoggerFactory.getLogger(NhlController.class);

    private final NhlFacadeImpl facade;

    @Autowired
    public NhlController(NhlFacadeImpl facade) {
        this.facade = facade;
    }

    @GetMapping("/teams")
    public List<Team> getTeams() {
        logger.info("Getting teams");

        try {
            return facade.getTeams();
        }
        catch (Exception exc) {
            throw generateAndLogException("Failed to get teams", exc);
        }
    }

    @GetMapping("/player/{apiLink}")
    @ResponseBody
    public Player getPlayer(@PathVariable String apiLink) {
        logger.info("Getting player with api link: " +  apiLink);

        try {
            return facade.getPlayer(apiLink);
        }
        catch (Exception exc) {
            throw generateAndLogException("Failed to get player with api link: " + apiLink , exc);
        }
    }

    @GetMapping("/games/{date}")
    @ResponseBody
    public List<Game> getGames(@PathVariable String date) {
        logger.info("Getting games for date: " +  date);

        try {
            return facade.getGames(date);
        }
        catch (Exception exc) {
            throw generateAndLogException("Failed to get games, for date: " + date, exc);
        }
    }

    private static ResponseStatusException generateAndLogException(String message, Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e instanceof NhlException)
            status = ((NhlException) e).getStatus();

        logger.error(message + " status: " + status);

        return new ResponseStatusException(status, message, e);
    }

}
