package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viitegeneraattori;
    Varasto varasto;
    Kauppa kauppa;

    @Before
    public void setup() {
        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        // määritellään että tuote numero 2 on talouspaperi jonka hinta on 15 ja saldo 1
        when(varasto.saldo(2)).thenReturn(1);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "talouspaperi", 15));
        // määritellään että tuote numero 3 on vessapaperi jonka hinta on 999 ja saldo 0
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "vessapaperi", 999));

        pankki = mock(Pankki.class);

        viitegeneraattori = mock(Viitegeneraattori.class);
        when(viitegeneraattori.uusi()).thenReturn(42);

        kauppa = new Kauppa(varasto, pankki, viitegeneraattori);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksetMaksetaanTilisiirrolla() {
        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void kahdenEriTuotteenOstoksetTilisiirrolla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("leevi", "999");

        verify(pankki).tilisiirto(eq("leevi"), eq(42), eq("999"), anyString(), eq(20));
    }

    @Test
    public void kahdenSamanTuotteenOstoksetTilisiirrolla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("null", "6543");

        verify(pankki).tilisiirto(eq("null"), eq(42), eq("6543"), anyString(), eq(10));
    }

    @Test
    public void loppuneenTuotteenOstoksetTilisiirrolla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("veeti", "23456");

        verify(pankki).tilisiirto(eq("veeti"), eq(42), eq("23456"), anyString(), eq(15));
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksen() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("veeti", "23456");

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("lauri", "asdf1234");

        verify(pankki).tilisiirto(eq("lauri"), eq(42), eq("asdf1234"), anyString(), eq(5));
    }

    @Test
    public void uusiViiteJokaiselleMaksutapahtumalle() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("veeti", "23456");

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("lauri", "asdf1234");

        verify(viitegeneraattori, times(2)).uusi();
    }
}
