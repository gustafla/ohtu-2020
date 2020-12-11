package ohtu.kivipaperisakset;

public class KPSTekoaly extends Peli {

    private static final Tekoaly tekoaly = new Tekoaly();

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
