package ohtu.kivipaperisakset;

public class KPSTekoaly extends Peli {
    private Tekoaly tekoaly;

    protected KPSTekoaly(Tekoaly tekoaly) {
        this.tekoaly = tekoaly;
    }

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
