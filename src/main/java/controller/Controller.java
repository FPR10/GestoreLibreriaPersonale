package main.java.controller;

import main.java.backend.LibreriaSingleton;
import main.java.backend.filtro.FiltroStrategyIF;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
import main.java.backend.libro.Valutazione_Personale;
import main.java.backend.salvataggio.SalvaCSV;
import main.java.backend.salvataggio.SalvaFileStrategyIF;
import main.java.backend.salvataggio.SalvaJSON;
import main.java.frontend.FinestraParametriLibro;
import main.java.frontend.GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Map;



public class Controller {
    private static LibreriaSingleton libreria;
    private static GUI grafica;

    public Controller(LibreriaSingleton impl, GUI front) {
        libreria = impl;
        grafica = front;
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
                                  JComboBox<String> campoValutazione,String segnapostoTitolo,
                                  FinestraParametriLibro finestra, DefaultTableModel modelloTabella) {
        String titolo = campoTitolo.getText();
        String autoreNome = campoAutoreNome.getText();
        String autoreCognome = campoAutoreCognome.getText();
        String isbn = campoIsbn.getText();
        String genere = (String) campoGenere.getSelectedItem();
        String stato = (String) campoStato.getSelectedItem();
        String cleanedStato = stato.replace(" ", "_");
        String valutazione = (String) campoValutazione.getSelectedItem();
        String cleanedValutazione = valutazione.replace(" ", "_");

        if (titolo.isEmpty() || autoreCognome.isEmpty() || isbn.isEmpty() ||
                titolo.equals(segnapostoTitolo) || autoreCognome.equals(segnapostoTitolo) || isbn.equals(segnapostoTitolo)) {
            JOptionPane.showMessageDialog(null,
                    "Compila tutti i campi obbligatori!",
                    "Campi mancanti",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Libro toAdd = new Libro.Builder(titolo,autoreCognome, isbn)
                .setAutoreNome(autoreNome)
                .setGenereLibri(Genere_Libri.valueOf(genere))
                .setStatoLettura(Stato_Lettura.valueOf(cleanedStato))
                .setValutazionePersonale(Valutazione_Personale.valueOf(cleanedValutazione))
                .build();


        //Solleva errore e non lo aggiunge
        if (libreria.contains(toAdd)){
            JOptionPane.showMessageDialog(null, "Libro già inserito !", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Aggiunta al backend
        libreria.aggiungiLibro(toAdd);
        modelloTabella.addRow(new String[]{titolo,autoreCognome+" "+autoreNome,isbn,genere,stato});

        JOptionPane.showMessageDialog(null, "Libro salvato!", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);
        grafica.setContatore(modelloTabella.getRowCount());
        finestra.dispose();  //chisura automatica finestra se il libro è stato salvato
    }


    //TODO
    public static void modificaLibro(Libro l){
    }


    public static void eliminaLibro(Map.Entry<Integer,Libro> toDelete, DefaultTableModel modelloTabella){
        libreria.rimuoviLibro(toDelete.getValue());
        modelloTabella.removeRow(toDelete.getKey());
        grafica.setContatore(modelloTabella.getRowCount());
    }


    public static void salvaJSON(){
        SalvaFileStrategyIF sf = new SalvaJSON();
        sf.salva(libreria, "salvataggio.json");
        JOptionPane.showMessageDialog(null, "Libreria salvata in JSON!", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void salvaCSV(){
        SalvaFileStrategyIF sf = new SalvaCSV();
        sf.salva(libreria, "salvataggio.csv");
        JOptionPane.showMessageDialog(null, "Libreria salvata in CSV!", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void applicaFiltro(String tipoFiltro){

    }

    public static void applicaOrdinamento(String tipoOrdinamento){

    }


    public static void ricerca (String testoDaRicercare, String tipoRicerca){

    }


}
