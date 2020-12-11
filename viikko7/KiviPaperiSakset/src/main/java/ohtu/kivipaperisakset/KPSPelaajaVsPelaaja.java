package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends Peli {
    protected KPSPelaajaVsPelaaja() {
    }

    @Override
    protected String tokanSiirto(String ekanSiirto) {
        System.out.println("Toisen pelaajan siirto: ");
        return scanner.nextLine();
    }
}
