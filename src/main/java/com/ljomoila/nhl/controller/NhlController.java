package com.ljomoila.nhl.controller;

import com.google.gson.internal.LinkedTreeMap;
import com.ljomoila.nhl.domain.Game;
import com.ljomoila.nhl.domain.Player;
import com.ljomoila.nhl.domain.Team;
import com.ljomoila.nhl.exception.NhlException;
import com.ljomoila.nhl.facade.NhlFacadeImpl;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @ApiOperation(value = "All teams")
    @GetMapping("/teams")
    public List<Team> getTeams() {
        logger.info("Getting teams");

        return facade.getTeams();
    }

    @ApiOperation(value = "Player by id")
    @GetMapping("/player/{id}")
    @ResponseBody
    public Player getPlayer(@PathVariable String id) {
        logger.info("Getting player by id: " +  id);

        return facade.getPlayer(id);
    }

    @ApiOperation(value = "Player stats by id and type")
    @GetMapping("/player/{id}/stats/{type}")
    @ResponseBody
    public LinkedTreeMap getPlayerStats(@PathVariable int id, @PathVariable String type) {
        logger.info("Getting player stats: " +  id + " type: " + type);

        return facade.getPlayerStats(id, type);
    }

    @ApiOperation(value = "All scheduled games and stats by date")
    @GetMapping("/games/{date}")
    @ResponseBody
    public List<Game> getGames(@PathVariable String date) {
        logger.info("Getting games for date: " +  date);

        return facade.getGames(date);
    }

    @ExceptionHandler({ NhlException.class, Exception.class })
    @ResponseBody
    public HashMap handleException(HttpServletRequest req, Exception ex) {
        HashMap<String, Object> error = new HashMap();
        error.put("error", true);
        error.put("message", ex.getMessage());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof NhlException) {
            status = ((NhlException) ex).getStatus();
        }

        error.put("status", status);

        logger.error(error.toString());

        return error;
    }
}
