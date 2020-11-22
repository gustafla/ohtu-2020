package ohtu.intjoukkosovellus;

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

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            if (koko == luvut.length) {
                int[] suurempiLukutaulukko = new int[koko+kasvatuskoko];
                System.arraycopy(luvut, 0, suurempiLukutaulukko, 0, koko);
                luvut = suurempiLukutaulukko;
            }
            luvut[koko] = luku;
            koko++;
            return true;
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < koko; i++) {
            if (luku == luvut[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                luvut[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < koko - 1; j++) {
                apu = luvut[j];
                luvut[j] = luvut[j + 1];
                luvut[j + 1] = apu;
            }
            koko--;
            return true;
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
        } else if (koko == 1) {
            return "{" + luvut[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < koko - 1; i++) {
                tuotos += luvut[i];
                tuotos += ", ";
            }
            tuotos += luvut[koko - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[koko];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }
}
