package com.ljomoila.nhl.facade;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.ljomoila.nhl.domain.Game;
import com.ljomoila.nhl.domain.LiveFeed;
import com.ljomoila.nhl.domain.Player;
import com.ljomoila.nhl.domain.Team;
import com.ljomoila.nhl.service.NhlServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NhlFacadeImplTest {
    @Mock
    NhlServiceImpl service;

    NhlFacadeImpl facade;


    @Before
    public void setup() {
        when(service.getTeams()).thenReturn(new Gson().fromJson("[{\"id\":1,\"name\":\"New Jersey Devils\",\"shortName\":\"New Jersey\",\"abbreviation\":\"NJD\"},{\"id\":2,\"name\":\"New York Islanders\",\"shortName\":\"NY Islanders\",\"abbreviation\":\"NYI\"}]", new TypeToken<ArrayList<Team>>(){}.getType()));
    }

    @Test
    public void testGetTeams() {
        // when
        List<Team> teams = getFacade().getTeams();

        // then
        assertEquals(2, teams.size());
        assertEquals(1, teams.get(0).getId());
        assertEquals("New Jersey Devils", teams.get(0).getName());
        assertEquals("New Jersey", teams.get(0).getShortName());
        assertEquals("NJD", teams.get(0).getAbbreviation());
    }

    @Test
    public void testGetPlayer() {
        // given
        String playerApiUrl = "/api/player/foo";
        Player expectedPlayer = new Player(1, "Test Player", "Player", "FIN", playerApiUrl);
        when(service.getPlayer(playerApiUrl)).thenReturn(expectedPlayer);

        // when
        Player player = getFacade().getPlayer(playerApiUrl);

        // then
        assertEquals(expectedPlayer, player);
    }

    @Test
    public void testGetGames() {
        // given
        String gameDate = "2023-01-01";
        List<String> gamePaths = Arrays.asList(new String[]{"/api/v1/game/2022030243/feed/live"});

        when(service.getScheduleGamesByDate(gameDate)).thenReturn(gamePaths);
        when(service.getLiveFeed("/api/v1/game/2022030243/feed/live")).thenReturn(new Gson().fromJson("{\"liveData\":{\"linescore\":{\"currentPeriodOrdinal\":\"3rd\",\"currentPeriodTimeRemaining\":\"Final\",\"teams\":{\"home\":{\"team\":{\"id\":1,\"name\":\"Edmonton Oilers\",\"link\":\"/api/v1/teams/22\",\"abbreviation\":\"EDM\",\"triCode\":\"EDM\"},\"goals\":1,\"shotsOnGoal\":28,\"goaliePulled\":false,\"numSkaters\":5,\"powerPlay\":false},\"away\":{\"team\":{\"id\":2,\"name\":\"Vegas Golden Knights\",\"link\":\"/api/v1/teams/54\",\"abbreviation\":\"VGK\",\"triCode\":\"VGK\"},\"goals\":5,\"shotsOnGoal\":33,\"goaliePulled\":false,\"numSkaters\":5,\"powerPlay\":false}}},\"boxscore\":{\"teams\":{\"away\":{\"team\":{\"id\":2,\"name\":\"Vegas Golden Knights\",\"link\":\"/api/v1/teams/54\",\"abbreviation\":\"VGK\",\"triCode\":\"VGK\"},\"teamStats\":{\"teamSkaterStats\":{\"goals\":5,\"pim\":6,\"shots\":33,\"powerPlayPercentage\":\"0.0\",\"powerPlayGoals\":0,\"powerPlayOpportunities\":4,\"faceOffWinPercentage\":\"48.4\",\"blocked\":23,\"takeaways\":6,\"giveaways\":16,\"hits\":25}},\"players\":{\"ID8476905\":{\"person\":{\"id\":8476905,\"fullName\":\"Chandler Stephenson\",\"link\":\"/api/v1/people/8476905\",\"shootsCatches\":\"L\",\"rosterStatus\":\"Y\"},\"jerseyNumber\":\"20\",\"position\":{\"code\":\"C\",\"name\":\"Center\",\"type\":\"Forward\",\"abbreviation\":\"C\"},\"stats\":{\"skaterStats\":{\"timeOnIce\":\"15:10\",\"assists\":0,\"goals\":1,\"shots\":2,\"hits\":0,\"powerPlayGoals\":0,\"powerPlayAssists\":0,\"penaltyMinutes\":0,\"faceOffPct\":66.67,\"faceOffWins\":10,\"faceoffTaken\":15,\"takeaways\":0,\"giveaways\":0,\"shortHandedGoals\":0,\"shortHandedAssists\":0,\"blocked\":0,\"plusMinus\":1,\"evenTimeOnIce\":\"10:23\",\"powerPlayTimeOnIce\":\"3:55\",\"shortHandedTimeOnIce\":\"0:52\"}}},\"ID8476927\":{\"person\":{\"id\":8476927,\"fullName\":\"Teddy Blueger\",\"link\":\"/api/v1/people/8476927\",\"shootsCatches\":\"L\",\"rosterStatus\":\"Y\"},\"jerseyNumber\":\"53\",\"position\":{\"code\":\"N/A\",\"name\":\"Unknown\",\"type\":\"Unknown\",\"abbreviation\":\"N/A\"},\"stats\":{}},\"ID8478499\":{\"person\":{\"id\":8478499,\"fullName\":\"Adin Hill\",\"link\":\"/api/v1/people/8478499\",\"shootsCatches\":\"L\",\"rosterStatus\":\"Y\"},\"jerseyNumber\":\"33\",\"position\":{\"code\":\"G\",\"name\":\"Goalie\",\"type\":\"Goalie\",\"abbreviation\":\"G\"},\"stats\":{\"goalieStats\":{\"timeOnIce\":\"47:48\",\"assists\":0,\"goals\":0,\"pim\":0,\"shots\":24,\"saves\":24,\"powerPlaySaves\":2,\"shortHandedSaves\":0,\"evenSaves\":22,\"shortHandedShotsAgainst\":0,\"evenShotsAgainst\":22,\"powerPlayShotsAgainst\":2,\"decision\":\"W\",\"savePercentage\":100,\"powerPlaySavePercentage\":100,\"evenStrengthSavePercentage\":100}}}}},\"home\":{\"team\":{\"id\":1,\"name\":\"Edmonton Oilers\",\"link\":\"/api/v1/teams/22\",\"abbreviation\":\"EDM\",\"triCode\":\"EDM\"},\"teamStats\":{\"teamSkaterStats\":{\"goals\":1,\"pim\":10,\"shots\":28,\"powerPlayPercentage\":\"0.0\",\"powerPlayGoals\":0,\"powerPlayOpportunities\":2,\"faceOffWinPercentage\":\"51.6\",\"blocked\":13,\"takeaways\":8,\"giveaways\":7,\"hits\":50}},\"players\":{\"ID8475717\":{\"person\":{\"id\":8475717,\"fullName\":\"Calvin Pickard\",\"link\":\"/api/v1/people/8475717\",\"shootsCatches\":\"L\",\"rosterStatus\":\"Y\"},\"jerseyNumber\":\"30\",\"position\":{\"code\":\"N/A\",\"name\":\"Unknown\",\"type\":\"Unknown\",\"abbreviation\":\"N/A\"},\"stats\":{}},\"ID8475218\":{\"person\":{\"id\":8475218,\"fullName\":\"Mattias Ekholm\",\"link\":\"/api/v1/people/8475218\",\"shootsCatches\":\"L\",\"rosterStatus\":\"Y\"},\"jerseyNumber\":\"14\",\"position\":{\"code\":\"D\",\"name\":\"Defenseman\",\"type\":\"Defenseman\",\"abbreviation\":\"D\"},\"stats\":{\"skaterStats\":{\"timeOnIce\":\"19:57\",\"assists\":0,\"goals\":0,\"shots\":3,\"hits\":0,\"powerPlayGoals\":0,\"powerPlayAssists\":0,\"penaltyMinutes\":0,\"faceOffWins\":0,\"faceoffTaken\":0,\"takeaways\":1,\"giveaways\":1,\"shortHandedGoals\":0,\"shortHandedAssists\":0,\"blocked\":0,\"plusMinus\":-1,\"evenTimeOnIce\":\"17:06\",\"powerPlayTimeOnIce\":\"0:30\",\"shortHandedTimeOnIce\":\"2:21\"}}},\"ID8477998\":{\"person\":{\"id\":8477998,\"fullName\":\"Warren Foegele\",\"link\":\"/api/v1/people/8477998\",\"shootsCatches\":\"L\",\"rosterStatus\":\"Y\"},\"jerseyNumber\":\"37\",\"position\":{\"code\":\"L\",\"name\":\"Left Wing\",\"type\":\"Forward\",\"abbreviation\":\"LW\"},\"stats\":{\"skaterStats\":{\"timeOnIce\":\"10:47\",\"assists\":0,\"goals\":1,\"shots\":2,\"hits\":6,\"powerPlayGoals\":0,\"powerPlayAssists\":0,\"penaltyMinutes\":2,\"faceOffWins\":0,\"faceoffTaken\":0,\"takeaways\":1,\"giveaways\":0,\"shortHandedGoals\":0,\"shortHandedAssists\":0,\"blocked\":0,\"plusMinus\":0,\"evenTimeOnIce\":\"10:15\",\"powerPlayTimeOnIce\":\"0:32\",\"shortHandedTimeOnIce\":\"0:00\"}}},\"ID8475760\":{\"person\":{\"id\":8475760,\"fullName\":\"Nick Bjugstad\",\"link\":\"/api/v1/people/8475760\",\"shootsCatches\":\"R\",\"rosterStatus\":\"Y\"},\"jerseyNumber\":\"72\",\"position\":{\"code\":\"C\",\"name\":\"Center\",\"type\":\"Forward\",\"abbreviation\":\"C\"},\"stats\":{\"skaterStats\":{\"timeOnIce\":\"13:44\",\"assists\":0,\"goals\":0,\"shots\":2,\"hits\":6,\"powerPlayGoals\":0,\"powerPlayAssists\":0,\"penaltyMinutes\":0,\"faceOffPct\":45.45,\"faceOffWins\":5,\"faceoffTaken\":11,\"takeaways\":0,\"giveaways\":0,\"shortHandedGoals\":0,\"shortHandedAssists\":0,\"blocked\":2,\"plusMinus\":-2,\"evenTimeOnIce\":\"10:56\",\"powerPlayTimeOnIce\":\"0:00\",\"shortHandedTimeOnIce\":\"2:48\"}}},\"ID8480802\":{\"person\":{\"id\":8480802,\"fullName\":\"Ryan McLeod\",\"link\":\"/api/v1/people/8480802\",\"shootsCatches\":\"L\",\"rosterStatus\":\"Y\"},\"jerseyNumber\":\"71\",\"position\":{\"code\":\"C\",\"name\":\"Center\",\"type\":\"Forward\",\"abbreviation\":\"C\"},\"stats\":{\"skaterStats\":{\"timeOnIce\":\"15:49\",\"assists\":1,\"goals\":0,\"shots\":0,\"hits\":3,\"powerPlayGoals\":0,\"powerPlayAssists\":0,\"penaltyMinutes\":0,\"faceOffPct\":75,\"faceOffWins\":6,\"faceoffTaken\":8,\"takeaways\":0,\"giveaways\":0,\"shortHandedGoals\":0,\"shortHandedAssists\":0,\"blocked\":0,\"plusMinus\":0,\"evenTimeOnIce\":\"12:26\",\"powerPlayTimeOnIce\":\"0:31\",\"shortHandedTimeOnIce\":\"2:52\"}}}}}}}},\"gameData\":{\"status\":{\"detailedState\":\"Final\"}}}", new TypeToken<LiveFeed>(){}.getType()));
        when(service.getPlayer(anyString())).thenReturn(any(Player.class));

        // when
        List<Game> games = getFacade().getGames(gameDate);

        // then
        assertEquals(2, games.size());
    }

    @Test
    public void testGetPlayerStats() {
        // given
        int playerId = 12345;
        String statsType = "yearByYear";

        when(service.getPlayerStats(playerId, statsType)).thenReturn(new Gson().fromJson("{\"timeOnIce\":\"1195:03\",\"assists\":21.0,\"goals\":12.0,\"pim\":43.0,\"shots\":152.0,\"games\":73.0,\"hits\":29.0,\"powerPlayGoals\":0.0,\"powerPlayPoints\":5.0,\"powerPlayTimeOnIce\":\"120:24\",\"evenTimeOnIce\":\"934:39\",\"penaltyMinutes\":\"43\",\"faceOffPct\":46.46,\"shotPct\":7.9,\"gameWinningGoals\":1.0,\"overTimeGoals\":0.0,\"shortHandedGoals\":2.0,\"shortHandedPoints\":2.0,\"shortHandedTimeOnIce\":\"140:00\",\"blocked\":32.0,\"plusMinus\":1.0,\"points\":33.0,\"shifts\":1604.0}", new TypeToken<LinkedTreeMap>(){}.getType()));

        // when
        LinkedTreeMap playerStatsMap = getFacade().getPlayerStats(playerId, statsType);

        // then
        assertEquals(33, ((Double) playerStatsMap.get("points")).intValue());
        assertEquals(1, ((Double) playerStatsMap.get("plusMinus")).intValue());
    }

    private NhlFacadeImpl getFacade() {
        return new NhlFacadeImpl(service);
    }
}
