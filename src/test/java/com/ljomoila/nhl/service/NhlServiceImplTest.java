package com.ljomoila.nhl.service;

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
    public void testGetTeamsThrows() {
        // given
        when(client.get("/teams")).thenThrow(RuntimeException.class);

        try {
            // when
            service.getTeams();
            assertEquals(false, true);
        } catch (Exception e) {
            // then
            assertEquals(true, true);
        }
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
    public void testGetPlayerThrows() {
        // given
        when(client.get("/player")).thenThrow(RuntimeException.class);

        try {
            // when
            service.getPlayer("/player");
            assertEquals(false, true);
        } catch (Exception e) {
            // then
            assertEquals(true, true);
        }
    }

    // TOD0: live feed and scheduled games
}
