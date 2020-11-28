package ohtu;

import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Tapahtumankuuntelija implements ActionListener {
    private JButton undo;
    private Sovelluslogiikka sovellus;

    private Map<JButton, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(nollaa, new Nollaus(tuloskentta, syotekentta, nollaa, undo, sovellus));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() != undo) {
            Komento komento = komennot.get(ae.getSource());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }
    }
}
