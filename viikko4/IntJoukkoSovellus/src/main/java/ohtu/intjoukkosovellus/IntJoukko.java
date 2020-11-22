package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {
    private int[] luvut;
    private int koko; // Tyhjässä joukossa alkioiden_määrä on nolla
    private int kasvatuskoko; // Uusi taulukko on tämän verran vanhaa suurempi

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasiteetti ei saa olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IllegalArgumentException("Kasvatuskoko ei saa olla negatiivinen");
        }
        luvut = new int[kapasiteetti]; // Java alustaa taulukot nollilla
        koko = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, 5);
    }

    public IntJoukko() {
        this(5, 5);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < koko; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }
        return false;
    }

    private void sijoitaLuku(int luku) {
        if (koko == luvut.length) {
            int[] suurempiLukutaulukko = new int[koko+kasvatuskoko];
            System.arraycopy(luvut, 0, suurempiLukutaulukko, 0, koko);
            luvut = suurempiLukutaulukko;
        }
        luvut[koko] = luku;
        koko++;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            sijoitaLuku(luku);
            return true;
        }
        return false;
    }

    private void lisaaJoukko(IntJoukko toinen) {
        int[] hakutaulukko = Arrays.copyOfRange(luvut, 0, koko);
        Arrays.sort(hakutaulukko);

        int[] toisenLuvut = toinen.toIntArray();
        for (int luku: toisenLuvut) {
            // Luku ei ole tässä joukossa jos binarySearch on negatiivinen
            if (Arrays.binarySearch(hakutaulukko, luku) < 0) {
                sijoitaLuku(luku);
            }
        }
    }

    public boolean poista(int luku) {
        for (int i = 0; i < koko; i++) {
            if (luku == luvut[i]) {
                koko--;
                for (int j = i; j < koko; j++) {
                    luvut[j] = luvut[j + 1];
                }
                return true;
            }
        }

        return false;
    }

    public int mahtavuus() {
        return koko;
    }

    @Override
    public String toString() {
        if (koko == 0) {
            return "{}";
        }

        StringBuilder tuotos = new StringBuilder("{");
        for (int i = 0; i < koko - 1; i++) {
            tuotos.append(luvut[i]);
            tuotos.append(", ");
        }
        tuotos.append(luvut[koko - 1]);
        tuotos.append("}");

        return tuotos.toString();
    }

    public int[] toIntArray() {
        return Arrays.copyOfRange(luvut, 0, koko);
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko(a.mahtavuus() + b.mahtavuus());
        yhdiste.lisaaJoukko(a);
        yhdiste.lisaaJoukko(b);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aLuvut = a.toIntArray();
        int[] bLuvut = b.toIntArray();

        // Kopioidaan luvut yhteen taulukkoon
        int[] molempienLuvut = new int[aLuvut.length + bLuvut.length];
        System.arraycopy(aLuvut, 0, molempienLuvut, 0, aLuvut.length);
        System.arraycopy(bLuvut, 0, molempienLuvut, aLuvut.length, bLuvut.length);

        // Laitetaan järjestykseen niin että samat luvut menevät peräkkäin
        Arrays.sort(molempienLuvut);

        // Otetaan vain peräkkäiset luvut (eli jotka ovat molemmissa joukoissa)
        for (int i = 0; i < molempienLuvut.length - 1; i++) {
            if (molempienLuvut[i] == molempienLuvut[i + 1]) {
                // Käytetään IntJoukon sisuskaluja välttääksemme turhat tarkistukset
                // tämä on OK koska leikkaus on aluksi tyhjä
                leikkaus.sijoitaLuku(molempienLuvut[i]);
            }
        }

        return leikkaus;
    }

    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        erotus.lisaaJoukko(a);

        int[] bLuvut = b.toIntArray();
        for (int i = 0; i < bLuvut.length; i++) {
            erotus.poista(bLuvut[i]);
        }

        return erotus;
    }
}
