package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends Peli {
    private static final TekoalyParannettu tekoaly = new TekoalyParannettu(20);

    @Override
    protected String tokanSiirto(String ekanSiirto) {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        if (ekanSiirto != null) {
            tekoaly.asetaSiirto(ekanSiirto);
        }
        return tokanSiirto;
    }
}
