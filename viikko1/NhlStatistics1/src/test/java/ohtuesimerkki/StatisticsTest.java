package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));  // 5
            players.add(new Player("Lemieux", "PIT", 45, 54)); // 2
            players.add(new Player("Kurri",   "EDM", 37, 53)); // 4
            players.add(new Player("Yzerman", "DET", 42, 56)); // 3
            players.add(new Player("Gretzky", "EDM", 35, 89)); // 1

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchKurri() {
        assertEquals(stats.search("Kurri").getName(), "Kurri");
    }

    @Test
    public void searchMikael() {
        assertEquals(stats.search("Mikael"), null);
    }

    @Test
    public void teamEDM() {
        List<Player> team = stats.team("EDM");

        // present
        assertTrue(team.stream().filter(player -> player.getName() == "Semenko").findAny().isPresent());
        assertTrue(team.stream().filter(player -> player.getName() == "Kurri").findAny().isPresent());
        assertTrue(team.stream().filter(player -> player.getName() == "Gretzky").findAny().isPresent());

        // empty
        assertTrue(team.stream().filter(player -> player.getName() == "Kalle").findAny().isEmpty());
        assertTrue(team.stream().filter(player -> player.getName() == "Lemieux").findAny().isEmpty());
    }

    @Test
    public void topNegative1() {
        List<Player> top = stats.topScorers(-1);
        assertTrue(top.isEmpty());
    }

    @Test
    public void top0() {
        List<Player> top = stats.topScorers(0);
        assertTrue(top.isEmpty());
    }

    @Test
    public void top1() {
        List<Player> top = stats.topScorers(1);

        assertEquals(top.size(), 1);
        assertEquals(top.get(0).getName(), "Gretzky");
    }

    @Test
    public void top4() {
        List<Player> top = stats.topScorers(4);

        assertEquals(top.size(), 4);
        assertEquals(top.get(0).getName(), "Gretzky");
        assertEquals(top.get(1).getName(), "Lemieux");
        assertEquals(top.get(2).getName(), "Yzerman");
        assertEquals(top.get(3).getName(), "Kurri");
    }

    @Test
    public void top10() {
        List<Player> top = stats.topScorers(10);

        assertEquals(top.size(), 5);
        assertEquals(top.get(0).getName(), "Gretzky");
        assertEquals(top.get(1).getName(), "Lemieux");
        assertEquals(top.get(2).getName(), "Yzerman");
        assertEquals(top.get(3).getName(), "Kurri");
        assertEquals(top.get(4).getName(), "Semenko");
    }
}
