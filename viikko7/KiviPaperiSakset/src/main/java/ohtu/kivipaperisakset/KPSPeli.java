package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSPeli implements Peli {
    protected static final Scanner scanner = new Scanner(System.in);

    protected KPSPeli() {
    }

    public static Peli luoPeli(String valinta) {
        // Tässä pitäisi oikeastaan käyttää jotain lueteltua tyyppiä
        // sisäisen rajapinnan "kielenä" merkitsemään minkälainen peli
        // halutaan, sen sijaan että on tällainen piiloriippuvuus pääohjelmassa
        // sijaitsevaan käyttöliittymään
        if (valinta.endsWith("a")) {
            return new KPSPelaajaVsPelaaja();
        } else if (valinta.endsWith("b")) {
            return new KPSTekoaly(new TekoalyPerus());
        } else if (valinta.endsWith("c")) {
            return new KPSTekoaly(new TekoalyParannettu(20));
        }
        return null;
    }

    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        System.out.println("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        String tokanSiirto = tokanSiirto(null);

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.println("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            tokanSiirto = tokanSiirto(ekanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    protected abstract String tokanSiirto(String ekanSiirto);
}
