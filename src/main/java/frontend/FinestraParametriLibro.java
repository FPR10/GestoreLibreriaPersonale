package main.java.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FinestraParametriLibro extends JFrame {

    private static String segnapostoTitolo = "Obbligatorio";

    public FinestraParametriLibro() {



        setTitle("Aggiungi un nuovo libro");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        // Parametri libro
        JLabel labelTitolo = new JLabel("Titolo:");
        labelTitolo.setBounds(30, 30, 110, 25);
        JTextField campoTitolo = new JTextField();
        campoTitolo.setBounds(140, 30, 210, 25);
        labelTitolo.setForeground(Color.RED);

        JLabel labelAutoreNome = new JLabel("Nome autore:");
        labelAutoreNome.setBounds(30, 70, 110, 25);
        JTextField campoAutoreNome = new JTextField();
        campoAutoreNome.setBounds(140, 70, 210, 25);

        JLabel labelAutoreCognome = new JLabel("Cognome autore:");
        labelAutoreCognome.setBounds(30, 110, 110, 25);
        JTextField campoAutoreCognome = new JTextField();
        campoAutoreCognome.setBounds(140, 110, 210, 25);
        labelAutoreCognome.setForeground(Color.RED);

        JLabel labelISBN = new JLabel("ISBN:");
        labelISBN.setBounds(30, 150, 150, 25);
        JTextField campoISBN = new JTextField();
        campoISBN.setBounds(140, 150, 150, 25);
        labelISBN.setForeground(Color.RED);

        JLabel labelGenere = new JLabel("Genere:");
        labelGenere.setBounds(30, 190, 100, 25);
        String[]opzioniGenere = {"BIOGRAFIA/AUTOBIOGRAFIA", "GIALLO", "THRILLER", "AVVENTURA/AZIONE", "FANTASCIENZA",
        "DISTOPIA", "FANTASY", "HORROR", "ROMANZO DI FORMAZIONE", "ROSA"};
        JComboBox<String> comboGenere = new JComboBox<>(opzioniGenere);
        comboGenere.setBounds(140, 190, 200, 25);

        JLabel labelStato = new JLabel("Stato lettura:");
        labelStato.setBounds(30, 230, 100, 25);
        String[] opzioniStato = {"DA LEGGERE", "IN LETTURA", "LETTO"};
        JComboBox<String> comboStato = new JComboBox<>(opzioniStato);
        comboStato.setBounds(140, 230, 200, 25);


        JButton salva = new JButton("Salva");
        salva.setBounds(140, 300, 100, 30);
        salva.addActionListener(e -> {
            String titolo = campoTitolo.getText();
            String autoreNome = campoAutoreNome.getText();
            String autoreCognome = campoAutoreCognome.getText();
            String isbn = campoISBN.getText();
            String genere = (String) comboGenere.getSelectedItem();
            String stato = (String) comboStato.getSelectedItem();

            if (titolo.isEmpty() || autoreCognome.isEmpty() || isbn.isEmpty() ||
                 titolo.equals(segnapostoTitolo)|| autoreCognome.equals(segnapostoTitolo)|| isbn.equals(segnapostoTitolo)) {
                JOptionPane.showMessageDialog(this,
                        "Compila tutti i campi obbligatori!",
                        "Campi mancanti",
                        JOptionPane.WARNING_MESSAGE);
                return; //blocco creazione
            }



            JOptionPane.showMessageDialog(this, "Libro salvato!", "Operazione avvenuta con successo",JOptionPane.INFORMATION_MESSAGE);


            // Chiudi la finestra
            dispose();
        });

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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FinestraParametriLibro frame = new FinestraParametriLibro();
            frame.setVisible(true);
        });
    }
}

