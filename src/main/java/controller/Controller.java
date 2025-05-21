package main.java.controller;

import main.java.backend.LibreriaSingleton;
import main.java.frontend.FinestraParametriLibro;
import main.java.frontend.GUI;

import javax.swing.*;
import java.awt.event.*;




public class Controller {
    private final LibreriaSingleton impl;
    private final GUI grafica;

    public Controller(LibreriaSingleton impl, GUI grafica) {
        this.impl = impl;
        this.grafica = grafica;
        grafica.setController(this);
    }

    public static MouseAdapter ripristinaSelezione(JTable tabella, int[] ultimaRigaSelezionata) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int riga = tabella.rowAtPoint(e.getPoint());

                if (riga == -1) return; // clic fuori dalla tabella

                // Se clicco sulla stessa riga già selezionata → deseleziona
                if (tabella.isRowSelected(riga) && ultimaRigaSelezionata[0] == riga) {
                    tabella.clearSelection();
                    ultimaRigaSelezionata[0] = -1;
                } else {
                    // Se clicco su una nuova riga → seleziona quella
                    tabella.setRowSelectionInterval(riga, riga);
                    ultimaRigaSelezionata[0] = riga;
                }
            }
        };
    }


    public static FocusListener gestisciFocus(JTextField campo){
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals("Cerca libro")) {
                    campo.setText("");
                    campo.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText("Cerca libro");
                    campo.setForeground(java.awt.Color.GRAY);
                }
            }
        };
    }

    public static void SalvaLibro(JTextField campoTitolo, JTextField campoAutoreNome, JTextField campoAutoreCognome, JTextField campoIsbn,
                                  JComboBox<String> campoGenere, JComboBox<String> campoStato, String segnapostoTitolo, FinestraParametriLibro finestra){
            String titolo = campoTitolo.getText();
            String autoreNome = campoAutoreNome.getText();
            String autoreCognome = campoAutoreCognome.getText();
            String isbn = campoIsbn.getText();
            String genere = (String) campoGenere.getSelectedItem();
            String stato = (String) campoStato.getSelectedItem();

        if(titolo.isEmpty()||autoreCognome.isEmpty()||isbn.isEmpty()||
                titolo.equals(segnapostoTitolo)||autoreCognome.equals(segnapostoTitolo)||isbn.equals(segnapostoTitolo))

            {
                JOptionPane.showMessageDialog(null,
                        "Compila tutti i campi obbligatori!",
                        "Campi mancanti",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        JOptionPane.showMessageDialog(null,"Libro salvato!","Operazione avvenuta con successo",JOptionPane.INFORMATION_MESSAGE);
        finestra.dispose();
        }


}
