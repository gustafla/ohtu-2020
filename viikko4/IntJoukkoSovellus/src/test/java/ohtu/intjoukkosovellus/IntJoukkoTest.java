package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaa(10);
        joukko.lisaa(3);
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaa(4);
        assertEquals(3, joukko.mahtavuus());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaa(10);
        joukko.lisaa(3);
        assertEquals(2, joukko.mahtavuus());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.kuuluu(10));
        assertFalse(joukko.kuuluu(5));
        assertFalse(joukko.kuuluu(0));
        assertTrue(joukko.kuuluu(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poista(3);
        assertFalse(joukko.kuuluu(3));
        assertEquals(1, joukko.mahtavuus());
    }
    
    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};
        
        joukko.lisaa(55);
        joukko.poista(10);
        joukko.lisaa(99);

        int[] vastaus = joukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }
    
    
    @Test
    public void toimiiKasvatuksenJalkeen(){
        int[] lisattavat = {1,2,4,5,6,7,8,9,11,12,13,14};
        for (int luku : lisattavat) {
            joukko.lisaa(luku);
        }
        assertEquals(14, joukko.mahtavuus());
        assertTrue(joukko.kuuluu(11));
        joukko.poista(11);
        assertFalse(joukko.kuuluu(11));
        assertEquals(13, joukko.mahtavuus());
    }
    
    @Test
    public void toStringToimii(){
        assertEquals("{10, 3}", joukko.toString());
    }
    
    @Test
    public void toStringToimiiYhdenKokoiselleJoukolla(){
        joukko = new IntJoukko();
        joukko.lisaa(1);
        assertEquals("{1}", joukko.toString());
    }

    @Test
    public void toStringToimiiTyhjallaJoukolla(){
        joukko = new IntJoukko();
        assertEquals("{}", joukko.toString());
    }     

    @Test
    public void yhdisteToimii() {
        int[] odotettu = {1, 3, 10, 11, 2000};

        IntJoukko joukko2 = new IntJoukko();
        joukko2.lisaa(1);
        joukko2.lisaa(10);
        joukko2.lisaa(11);
        joukko2.lisaa(2000);
        joukko2.lisaa(2000);

        int[] yhdisteTaulukko = IntJoukko.yhdiste(joukko, joukko2).toIntArray();
        Arrays.sort(yhdisteTaulukko);
        assertArrayEquals(yhdisteTaulukko, odotettu);
    }

    @Test
    public void leikkausToimii() {
        int[] odotettu = {1, 2, 3, 9, 10, 2000};

        IntJoukko joukko1 = new IntJoukko();
        joukko1.lisaa(1);
        joukko1.lisaa(2);
        joukko1.lisaa(3);
        joukko1.lisaa(10);
        joukko1.lisaa(5);
        joukko1.lisaa(18);
        joukko1.lisaa(9);
        joukko1.lisaa(2000);
        joukko1.lisaa(2001);

        IntJoukko joukko2 = new IntJoukko();
        joukko2.lisaa(1);
        joukko2.lisaa(10);
        joukko2.lisaa(3);
        joukko2.lisaa(9);
        joukko2.lisaa(16);
        joukko2.lisaa(23);
        joukko2.lisaa(2);
        joukko2.lisaa(2000);

        int[] leikkausTaulukko = IntJoukko.leikkaus(joukko1, joukko2).toIntArray();
        Arrays.sort(leikkausTaulukko);
        assertArrayEquals(leikkausTaulukko, odotettu);
    }
}
