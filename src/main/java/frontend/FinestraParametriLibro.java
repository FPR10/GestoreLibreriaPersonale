package main.java.frontend;

import main.java.backend.LibreriaSingleton;
import main.java.controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FinestraParametriLibro extends JFrame {

    private static final String segnapostoTitolo = "Obbligatorio";
    private JTextField campoTitolo;
    private JTextField campoAutoreNome;
    private JTextField campoAutoreCognome;
    private JTextField campoISBN;
    private JComboBox<String> comboGenere;
    private JComboBox<String> comboStato;
    private JButton salva;

    private Controller c;
    private DefaultTableModel modelloTabella;

    public FinestraParametriLibro(Controller c, DefaultTableModel modelloTabella) {

        this.c = c;
        this.modelloTabella = modelloTabella;


        setTitle("Aggiungi un nuovo libro");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        // Parametri libro
        JLabel labelTitolo = new JLabel("Titolo:");
        labelTitolo.setBounds(30, 30, 110, 25);
        campoTitolo = new JTextField();
        campoTitolo.setBounds(140, 30, 210, 25);
        labelTitolo.setForeground(Color.RED);

        JLabel labelAutoreNome = new JLabel("Nome autore:");
        labelAutoreNome.setBounds(30, 70, 110, 25);
        campoAutoreNome = new JTextField();
        campoAutoreNome.setBounds(140, 70, 210, 25);

        JLabel labelAutoreCognome = new JLabel("Cognome autore:");
        labelAutoreCognome.setBounds(30, 110, 110, 25);
        campoAutoreCognome = new JTextField();
        campoAutoreCognome.setBounds(140, 110, 210, 25);
        labelAutoreCognome.setForeground(Color.RED);

        JLabel labelISBN = new JLabel("ISBN:");
        labelISBN.setBounds(30, 150, 150, 25);
        campoISBN = new JTextField();
        campoISBN.setBounds(140, 150, 150, 25);
        labelISBN.setForeground(Color.RED);

        JLabel labelGenere = new JLabel("Genere:");
        labelGenere.setBounds(30, 190, 100, 25);
        String[]opzioniGenere = {"BIOGRAFIA","AUTOBIOGRAFIA", "ROMANZO","GIALLO", "THRILLER", "AVVENTURA","AZIONE", "FANTASCIENZA",
        "DISTOPIA", "FANTASY", "HORROR", "ROSA"};
        comboGenere = new JComboBox<>(opzioniGenere);
        comboGenere.setBounds(140, 190, 200, 25);

        JLabel labelStato = new JLabel("Stato lettura:");
        labelStato.setBounds(30, 230, 100, 25);
        String[] opzioniStato = {"DA LEGGERE", "IN LETTURA", "LETTO"};
        comboStato = new JComboBox<>(opzioniStato);
        comboStato.setBounds(140, 230, 200, 25);

        JLabel labelValutazione = new JLabel("Valutazione:");
        labelValutazione.setBounds(30, 270, 100, 25);
        String[] opzioniValutazione = {"UNA STELLA", "DUE STELLE", "TRE STELLE", "QUATTRO STELLE", "CINQUE STELLE"};
        comboValutazione = new JComboBox<>(opzioniValutazione);
        comboValutazione.setBounds(140, 270, 200, 25);


        salva = new JButton("Salva");
        salva.setBounds(140, 300, 100, 30);

        // Aggiungi tutto al frame
        add(labelTitolo);
        add(campoTitolo);

        add(labelAutoreNome);
        add(campoAutoreNome);

        add(labelAutoreCognome);
        add(campoAutoreCognome);

        add(labelISBN);
        add(campoISBN);

        add(labelGenere);
        add(comboGenere);

        add(labelStato);
        add(comboStato);
        add(salva);


        aggiungiSegnapostoObbligatorio(campoTitolo, segnapostoTitolo);
        aggiungiSegnapostoObbligatorio(campoAutoreCognome, segnapostoTitolo);
        aggiungiSegnapostoObbligatorio(campoISBN, segnapostoTitolo);

        setVisible(true);

        setController(c);

    }

    public void aggiungiSegnapostoObbligatorio(JTextField campo, String testoSegnaposto) {
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


    private void setController(Controller c){
           salva.addActionListener(e -> Controller.SalvaLibro(campoTitolo, campoAutoreNome, campoAutoreCognome,
                   campoISBN, comboGenere, comboStato,segnapostoTitolo,this , modelloTabella));
    }

}

