package ohtu;

import javax.swing.JTextField;
import javax.swing.JButton;

public abstract class Laskuoperaatio extends Komento {
    private int edellinenArvo;

    public Laskuoperaatio(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        edellinenArvo = 0;
    }

    private void esita(int tulos) {
        syotekentta.setText("");
        tuloskentta.setText("" + tulos);
        if (tulos == 0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
    }

    @Override
    public void suorita() {
        int edellinenArvo = sovellus.tulos();
        int syote = 0;

        try {
            syote = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        laske(syote);

        esita(sovellus.tulos());

        if (sovellus.tulos() != edellinenArvo) {
            this.edellinenArvo = edellinenArvo;
            undo.setEnabled(true);
        }
    }

    @Override
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(edellinenArvo);

        esita(sovellus.tulos());

        undo.setEnabled(false);
    }

    protected abstract void laske(int arvo);
}
