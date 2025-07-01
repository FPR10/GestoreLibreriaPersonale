package main.java.frontend;

import main.java.backend.libro.Libro;
import main.java.controller.Controller;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Map;

public class FinestraParametriLibro extends JFrame {

    // Campi della finestra di dialogo per inserimento/modifica libro
    private JTextField campoTitolo;
    private JTextField campoAutoreNome;
    private JTextField campoAutoreCognome;
    private JTextField campoISBN;
    private JComboBox<String> comboGenere;
    private JComboBox<String> comboStato;
    private JComboBox<String> comboValutazione;
    private JButton salva;

    // Dati per la modifica
    private Libro libroDaModificare;
    private int rigaLibroDaModifica;


    private Controller controller;
    private DefaultTableModel modelloTabella;

    /**
     * Costruttore per la finestra di AGGIUNTA di un nuovo libro
     */
    public FinestraParametriLibro(Controller controller, DefaultTableModel modelloTabella) {
        this.controller = controller;
        this.modelloTabella = modelloTabella;

        creaFinestra("Aggiungi un nuovo libro");
        creaComponenti();
        aggiungiComponentiFinestra();
        aggiungiSegnapostiCampiObbligatori();
        impostaControllerAggiunta();

        setVisible(true);
    }

    /**
     * Costruttore per la finestra di MODIFICA un libro esistente
     */
    public FinestraParametriLibro(Controller controller, DefaultTableModel modelloTabella,
                                  Map.Entry<Integer, String> libroModifica) {
        this.controller = controller;
        this.modelloTabella = modelloTabella;
        this.libroDaModificare = Controller.getLibroFromISBN(libroModifica.getValue());
        this.rigaLibroDaModifica = libroModifica.getKey();

        creaFinestra("Modifica libro");
        creaComponenti();
        aggiungiComponentiFinestra();
        precompilaCampi();
        impostaControllerModifica();

        setVisible(true);
    }

    /**
     * Inizializza le proprietà base della finestra di dialogo
     */
    private void creaFinestra(String titolo) {
        setTitle(titolo);
        setSize(450,450);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    /**
     * Crea tutti i componenti dell'interfaccia
     */
    private void creaComponenti() {
        creaComponentiTesto();
        creaComboBox();
        creaPulsanteSalva();
    }

    /**
     * Crea i componenti di testo (label e textfield)
     */
    private void creaComponentiTesto() {
        // Titolo
        JLabel labelTitolo = new JLabel("Titolo:");
        labelTitolo.setBounds(30, 30, 110, 25);
        labelTitolo.setForeground(Color.RED);
        campoTitolo = new JTextField();
        campoTitolo.setBounds(140, 30, 210, 25);

        // Nome autore
        JLabel labelAutoreNome = new JLabel("Nome autore:");
        labelAutoreNome.setBounds(30, 70, 110, 25);
        campoAutoreNome = new JTextField();
        campoAutoreNome.setBounds(140, 70, 210, 25);

        // Cognome autore
        JLabel labelAutoreCognome = new JLabel("Cognome autore:");
        labelAutoreCognome.setBounds(30, 110, 110, 25);
        labelAutoreCognome.setForeground(Color.RED);
        campoAutoreCognome = new JTextField();
        campoAutoreCognome.setBounds(140, 110, 210, 25);

        // ISBN
        JLabel labelISBN = new JLabel("ISBN:");
        labelISBN.setBounds(30, 150, 150, 25);
        labelISBN.setForeground(Color.RED);
        campoISBN = new JTextField();
        campoISBN.setBounds(140, 150, 150, 25);
    }

    /**
     * Crea le ComboBox
     */
    private void creaComboBox() {
        // Genere
        JLabel labelGenere = new JLabel("Genere:");
        labelGenere.setBounds(30, 190, 100, 25);
        String[] opzioniGenere = getOpzioniGenere();
        comboGenere = new JComboBox<>(opzioniGenere);
        comboGenere.setBounds(140, 190, 200, 25);

        // Stato lettura
        JLabel labelStato = new JLabel("Stato lettura:");
        labelStato.setBounds(30, 230, 100, 25);
        String[] opzioniStato = getOpzioniStato();
        comboStato = new JComboBox<>(opzioniStato);
        comboStato.setBounds(140, 230, 200, 25);

        // Valutazione
        JLabel labelValutazione = new JLabel("Valutazione:");
        labelValutazione.setBounds(30, 270, 100, 25);
        String[] opzioniValutazione = getOpzioniValutazione();
        comboValutazione = new JComboBox<>(opzioniValutazione);
        comboValutazione.setBounds(140, 270, 200, 25);
    }

    /**
     * Crea il pulsante salva/modifica
     */
    private void creaPulsanteSalva() {
        String testoBottone;
        if (libroDaModificare == null) {
            testoBottone = "Salva";
        } else {
            testoBottone = "Modifica";
        }
        salva = new JButton(testoBottone);
        salva.setBounds(175, 360, 100, 30);
    }

    /**
     * Aggiunge tutti i componenti alla finestra
     */
    private void aggiungiComponentiFinestra() {
        // Campi di testo
        add(new JLabel("Titolo:") {{ setBounds(30, 30, 110, 25); setForeground(Color.RED); }});
        add(campoTitolo);

        add(new JLabel("Nome autore:") {{ setBounds(30, 70, 110, 25); }});
        add(campoAutoreNome);

        add(new JLabel("Cognome autore:") {{ setBounds(30, 110, 110, 25); setForeground(Color.RED); }});
        add(campoAutoreCognome);

        add(new JLabel("ISBN:") {{ setBounds(30, 150, 150, 25); setForeground(Color.RED); }});
        add(campoISBN);

        // ComboBox
        add(new JLabel("Genere:") {{ setBounds(30, 190, 100, 25); }});
        add(comboGenere);

        add(new JLabel("Stato lettura:") {{ setBounds(30, 230, 100, 25); }});
        add(comboStato);

        add(new JLabel("Valutazione:") {{ setBounds(30, 270, 100, 25); }});
        add(comboValutazione);

        // Pulsante
        add(salva);
    }

    /**
     * Aggiunge i segnaposti ai campi obbligatori
     */
    private void aggiungiSegnapostiCampiObbligatori() {
        String segn_obbl = "Obbligatorio";
        aggiungiSegnapostoObbligatorio(campoTitolo, segn_obbl);
        aggiungiSegnapostoObbligatorio(campoAutoreCognome, segn_obbl);
        aggiungiSegnapostoObbligatorio(campoISBN, segn_obbl);
    }

    /**
     * Precompila i campi (con le info inserite in fase di creazione) quando si modifica un libro
     */
    private void precompilaCampi() {
        if (libroDaModificare != null) {
            campoTitolo.setText(libroDaModificare.getTitolo());
            campoTitolo.setForeground(Color.BLACK);

            campoAutoreNome.setText(libroDaModificare.getNomeAutore());
            campoAutoreNome.setForeground(Color.BLACK);

            campoAutoreCognome.setText(libroDaModificare.getCognomeAutore());
            campoAutoreCognome.setForeground(Color.BLACK);

            campoISBN.setText(libroDaModificare.getISBN());
            campoISBN.setForeground(Color.BLACK);

            comboGenere.setSelectedItem(libroDaModificare.getGenLib().name().replace("_", " "));
            comboStato.setSelectedItem(libroDaModificare.getStatLett().name().replace("_", " "));
            comboValutazione.setSelectedItem(libroDaModificare.getValPers().name().replace("_", " "));
        }
    }

    /**
     * Imposta il controller per l'aggiunta di un nuovo libro
     */
    private void impostaControllerAggiunta() {
        salva.addActionListener(e -> Controller.SalvaLibro(
                campoTitolo, campoAutoreNome, campoAutoreCognome,
                campoISBN, comboGenere, comboStato, comboValutazione,
                "Obbligatorio", this, modelloTabella));
    }

    /**
     * Imposta il controller per la modifica di un libro
     */
    private void impostaControllerModifica() {
        salva.addActionListener(e -> Controller.ModificaLibro(
                libroDaModificare, rigaLibroDaModifica, campoTitolo, campoAutoreNome,
                campoAutoreCognome, campoISBN, comboGenere, comboStato, comboValutazione,
                this, modelloTabella));
    }

    /**
     * Gestisce la visualizzazione del segnaposto 'obbligatorio' in base al focus del mouse dell'utente
     */
    private void aggiungiSegnapostoObbligatorio(JTextField campo, String testoSegnaposto) {
        campo.setForeground(Color.GRAY);
        campo.setText(testoSegnaposto);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(testoSegnaposto)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(testoSegnaposto);
                }
            }
        });
    }

    // Metodi per ottenere le opzioni delle ComboBox
    private String[] getOpzioniGenere() {
        return new String[]{"NON SELEZIONATO", "BIOGRAFIA", "AUTOBIOGRAFIA", "ROMANZO",
                "GIALLO", "THRILLER", "AVVENTURA", "AZIONE", "FANTASCIENZA",
                "DISTOPIA", "FANTASY", "HORROR", "ROSA", "SCIENTIFICO"};
    }

    private String[] getOpzioniStato() {
        return new String[]{"NON SELEZIONATO", "DA LEGGERE", "IN LETTURA", "LETTO"};
    }

    private String[] getOpzioniValutazione() {
        return new String[]{"NON SELEZIONATO", "UNA STELLA", "DUE STELLE", "TRE STELLE",
                "QUATTRO STELLE", "CINQUE STELLE"};
    }
}