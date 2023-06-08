package com.ljomoila.nhl.service;

import com.google.gson.internal.LinkedTreeMap;
import com.ljomoila.nhl.domain.Player;
import com.ljomoila.nhl.domain.Team;
import com.ljomoila.nhl.integration.NhlClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NhlServiceImplTest {
    @Mock
    NhlClient client;

    NhlServiceImpl service;

    @Before
    public void setup() {
        service = new NhlServiceImpl(client);
    }

    @Test
    public void testGetTeams() {
        // given
        when(client.get("/teams")).thenReturn("{\"copyright\":\"NHL\",\"teams\":[{\"id\":1,\"name\":\"New Jersey Devils\",\"link\":\"/api/v1/teams/1\",\"venue\":{\"name\":\"Prudential Center\",\"link\":\"/api/v1/venues/null\",\"city\":\"Newark\",\"timeZone\":{\"id\":\"America/New_York\",\"offset\":-4,\"tz\":\"EDT\"}},\"abbreviation\":\"NJD\",\"teamName\":\"Devils\",\"locationName\":\"New Jersey\",\"firstYearOfPlay\":\"1982\",\"division\":{\"id\":18,\"name\":\"Metropolitan\",\"nameShort\":\"Metro\",\"link\":\"/api/v1/divisions/18\",\"abbreviation\":\"M\"},\"conference\":{\"id\":6,\"name\":\"Eastern\",\"link\":\"/api/v1/conferences/6\"},\"franchise\":{\"franchiseId\":23,\"teamName\":\"Devils\",\"link\":\"/api/v1/franchises/23\"},\"shortName\":\"New Jersey\",\"officialSiteUrl\":\"http://www.newjerseydevils.com/\",\"franchiseId\":23,\"active\":true},{\"id\":2,\"name\":\"New York Islanders\",\"link\":\"/api/v1/teams/2\",\"venue\":{\"name\":\"UBS Arena\",\"link\":\"/api/v1/venues/null\",\"city\":\"Elmont\",\"timeZone\":{\"id\":\"America/New_York\",\"offset\":-4,\"tz\":\"EDT\"}},\"abbreviation\":\"NYI\",\"teamName\":\"Islanders\",\"locationName\":\"New York\",\"firstYearOfPlay\":\"1972\",\"division\":{\"id\":18,\"name\":\"Metropolitan\",\"nameShort\":\"Metro\",\"link\":\"/api/v1/divisions/18\",\"abbreviation\":\"M\"},\"conference\":{\"id\":6,\"name\":\"Eastern\",\"link\":\"/api/v1/conferences/6\"},\"franchise\":{\"franchiseId\":22,\"teamName\":\"Islanders\",\"link\":\"/api/v1/franchises/22\"},\"shortName\":\"NY Islanders\",\"officialSiteUrl\":\"http://www.newyorkislanders.com/\",\"franchiseId\":22,\"active\":true}]  }");

        // when
        List<Team> teams = service.getTeams();

        // then
        assertEquals(2, teams.size());
    }

    @Test
    public void testGetPlayer() {
        // given
        when(client.get("/player")).thenReturn("{\"copyright\":\"NHL\",\"people\":[{\"id\":1,\"fullName\":\"Warren Foegele\",\"link\":\"/api/v1/people/1\",\"firstName\":\"Warren\",\"lastName\":\"Foegele\",\"primaryNumber\":\"37\",\"birthDate\":\"1996-04-01\",\"currentAge\":27,\"birthCity\":\"Markham\",\"birthStateProvince\":\"ON\",\"birthCountry\":\"CAN\",\"nationality\":\"CAN\",\"height\":\"6' 2\\\"\",\"weight\":198,\"active\":true,\"alternateCaptain\":false,\"captain\":false,\"rookie\":false,\"shootsCatches\":\"L\",\"rosterStatus\":\"Y\",\"currentTeam\":{\"id\":22,\"name\":\"Edmonton Oilers\",\"link\":\"/api/v1/teams/22\"},\"primaryPosition\":{\"code\":\"L\",\"name\":\"Left Wing\",\"type\":\"Forward\",\"abbreviation\":\"LW\"}}]  }");

        // when
        Player player = service.getPlayer("/player");

        // then
        assertEquals(1, player.getId());
        assertEquals("Warren Foegele", player.getFullName());
        assertEquals("Foegele", player.getLastName());
        assertEquals("CAN", player.getNationality());
        assertEquals("/api/v1/people/1", player.getApiLink());
    }

    @Test
    public void testGetPlayerStats() {
        // given
        when(client.get("/people/12345/stats?stats=yearByYear")).thenReturn("{\"copyright\":\"NHL and the NHL Shield are registered trademarks of the National Hockey League. NHL and NHL team marks are the property of the NHL and its teams. © NHL 2023. All Rights Reserved.\",\"stats\":[{\"type\":{\"displayName\":\"yearByYear\",\"gameType\":null},\"splits\":[{\"season\":\"20152016\",\"stat\":{\"assists\":11,\"goals\":4,\"pim\":10,\"games\":27,\"penaltyMinutes\":\"10\",\"points\":15},\"team\":{\"name\":\"HIFK U16\",\"link\":\"/api/v1/teams/null\"},\"league\":{\"name\":\"U16 SM-sarja\",\"link\":\"/api/v1/league/null\"},\"sequenceNumber\":81243},{\"season\":\"20222023\",\"stat\":{\"timeOnIce\":\"1195:03\",\"assists\":21,\"goals\":12,\"pim\":43,\"shots\":152,\"games\":73,\"hits\":29,\"powerPlayGoals\":0,\"powerPlayPoints\":5,\"powerPlayTimeOnIce\":\"120:24\",\"evenTimeOnIce\":\"934:39\",\"penaltyMinutes\":\"43\",\"faceOffPct\":46.46,\"shotPct\":7.9,\"gameWinningGoals\":1,\"overTimeGoals\":0,\"shortHandedGoals\":2,\"shortHandedPoints\":2,\"shortHandedTimeOnIce\":\"140:00\",\"blocked\":32,\"plusMinus\":1,\"points\":33,\"shifts\":1604},\"team\":{\"id\":13,\"name\":\"Florida Panthers\",\"link\":\"/api/v1/teams/13\"},\"league\":{\"id\":133,\"name\":\"National Hockey League\",\"link\":\"/api/v1/league/133\"},\"sequenceNumber\":1}]}]  }");

        // when
        LinkedTreeMap stats = service.getPlayerStats(12345, "yearByYear");

        // then
        assertEquals(33, ((Double) stats.get("points")).intValue());
    }

    // TOD0: live feed and scheduled games
}
