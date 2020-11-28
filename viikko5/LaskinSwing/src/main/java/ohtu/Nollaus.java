package ohtu;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Nollaus extends Laskuoperaatio {
    public Nollaus(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    protected void laske(int arvo) {
        sovellus.nollaa();
    }
}
