package ohtu;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Erotus extends Laskuoperaatio {
    public Erotus(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    protected void laske(int arvo) {
        sovellus.miinus(arvo);
    }
}
