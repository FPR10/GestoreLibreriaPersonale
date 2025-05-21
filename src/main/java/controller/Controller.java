package main.java.controller;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
import main.java.frontend.FinestraParametriLibro;
import main.java.frontend.GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;


public class Controller {
    private static LibreriaSingleton libreria;
    private final GUI grafica;

    public Controller(LibreriaSingleton impl, GUI grafica) {
        this.libreria = impl;
        this.grafica = grafica;
        grafica.setController(this);
    }


    /*
    Consente di ripristinare il colore originale di una riga della tabella selezionata
     */
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


    /*
      Fa scomparire labelVecchia quando l'utente preme sul campo
     */
    public static FocusListener gestisciFocus(JTextField campo, String labelVecchia, String labelNuova){
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(labelVecchia)) {
                    campo.setText(labelNuova);
                    campo.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(labelVecchia);
                    campo.setForeground(java.awt.Color.GRAY);
                }
            }
        };
    }


    /*
      Riceve i campi del form di compilazione del Libro, crea un libro in LibreriaSingleton e lo aggiunge alla tabella GUI.
      Notifica visivamente se l'operazione è andata o meno a buon fine.
     */
    public static void SalvaLibro(JTextField campoTitolo, JTextField campoAutoreNome, JTextField campoAutoreCognome,
                                  JTextField campoIsbn, JComboBox<String> campoGenere, JComboBox<String> campoStato,
                                  String segnapostoTitolo, FinestraParametriLibro finestra, DefaultTableModel modelloTabella) {
        String titolo = campoTitolo.getText();
        String autoreNome = campoAutoreNome.getText();
        String autoreCognome = campoAutoreCognome.getText();
        String isbn = campoIsbn.getText();
        String genere = (String) campoGenere.getSelectedItem();
        String stato = (String) campoStato.getSelectedItem();
        String cleanedStato = stato.replace(" ", "_");

        if (titolo.isEmpty() || autoreCognome.isEmpty() || isbn.isEmpty() ||
                titolo.equals(segnapostoTitolo) || autoreCognome.equals(segnapostoTitolo) || isbn.equals(segnapostoTitolo)) {
            JOptionPane.showMessageDialog(null,
                    "Compila tutti i campi obbligatori!",
                    "Campi mancanti",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Libro salvato!", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);

        Libro toAdd = new Libro.Builder(titolo,autoreCognome, isbn)
                .setAutoreNome(autoreNome)
                .setGenereLibri(Genere_Libri.valueOf(genere))
                .setStatoLettura(Stato_Lettura.valueOf(cleanedStato))
                .build();

        //Aggiunta al backend
        libreria.aggiungiLibro(toAdd);

        //Aggiunta al frontend
        modelloTabella.addRow(new String[]{titolo,autoreCognome+" "+autoreNome,isbn,genere,stato});

        finestra.dispose();  //chisura automatica finestra se il libro è stato salvato
    }


    public static void modificaLibro(Libro l){

    }

    public static void eliminaLibro(Libro l){

    public static void eliminaLibro(Map.Entry<Integer,Libro> toDelete, DefaultTableModel modelloTabella){
        libreria.rimuoviLibro(toDelete.getValue());
        modelloTabella.removeRow(toDelete.getKey());
    }


    public static void salvaJSON(){
        SalvaFileStrategyIF sf = new SalvaJSON();
        sf.salva(libreria, "salvataggio.json");
        JOptionPane.showMessageDialog(null, "Libro salvato in JSON!", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void salvaCSV(){
        SalvaFileStrategyIF sf = new SalvaJSON();
        sf.salva(libreria, "salvataggio.json");
        JOptionPane.showMessageDialog(null, "Libro salvato in JSON!", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void applicaFiltro(String tipoFiltro){

    }

    public static void applicaOrdinamento(String tipoOrdinamento){

    }


    public static void ricerca (String testoDaRicercare, String tipoRicerca){

    }

        /*
        modelloTabella.addRow(new String[]{"aa","bb","cc","dd","ee"});
        modelloTabella.addRow(new String[]{"aa","bb","cc","dd","ee"});
        modelloTabella.addRow(new String[]{"aa","bb","cc","dd","ee"});
        modelloTabella.addRow(new String[]{"aa","bb","cc","dd","ee"});
         */
}
