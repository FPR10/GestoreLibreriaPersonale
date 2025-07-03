package main.java.controller;

import main.java.backend.LibreriaSingleton;
import main.java.backend.filtro.*;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
import main.java.backend.libro.Valutazione_Personale;
import main.java.backend.ordinamento.OrdinamentoFactory;
import main.java.backend.ordinamento.OrdinamentoFactoryIF;
import main.java.backend.ordinamento.OrdinamentoStrategyIF;
import main.java.backend.ricerca.RicercaFactory;
import main.java.backend.ricerca.RicercaFactoryIF;
import main.java.backend.ricerca.RicercaStrategyIF;
import main.java.backend.salvataggio.*;
import main.java.frontend.FinestraParametriLibro;
import main.java.frontend.GUI;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;


public class Controller {
    private static LibreriaSingleton libreria;
    private static GUI grafica;

    private static final String cartellaDownload = System.getProperty("user.home") + File.separator + "Downloads";
    private static final String successoOperazione = "Operazione avvenuta con successo";
    private static final String fallimentoOperazione = "Errore";


    public Controller(LibreriaSingleton impl, GUI front) {
        libreria = impl;
        grafica = front;
        grafica.setController(this);
    }


    /**
       Gestione degli ActionListener
     */

    //Ripristino colore originale di una riga della tabella selezionata
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



    //  Fa scomparire labelVecchia quando l'utente preme sul campo
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


    /**
        Gestione delle operazioni di AGGIUNTA, MODIFICA ed ELIMINAZIONE di un libro
     */

      //Riceve i campi del form di compilazione del Libro. Crea un libro in LibreriaSingleton e lo aggiunge alla tabella GUI.
      //Notifica visivamente se l'operazione è andata o meno a buon fine.
    public static void SalvaLibro(JTextField campoTitolo, JTextField campoAutoreNome, JTextField campoAutoreCognome,
                                  JTextField campoIsbn, JComboBox<String> campoGenere, JComboBox<String> campoStato,
                                  JComboBox<String> campoValutazione,String segnapostoTitolo,
                                  FinestraParametriLibro finestra, DefaultTableModel modelloTabella) {


        Libro toAdd = creaLibroDaForm(campoTitolo,campoAutoreNome,campoAutoreCognome,campoIsbn,campoGenere,campoStato,campoValutazione);

        //Mancato inserimento di tutti i campi obbligatori
        if (campiObbligatoriOk(toAdd.getTitolo(),toAdd.getISBN(),toAdd.getCognomeAutore(),segnapostoTitolo)){
            JOptionPane.showMessageDialog(null, "Compila tutti i campi obbligatori!", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Libro già presente
        if (libreria.contains(toAdd)){
            JOptionPane.showMessageDialog(null, "Libro già inserito !", fallimentoOperazione, JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Aggiunta a backend e frontend
        libreria.aggiungiLibro(toAdd);
        aggiungiLibroGUI(modelloTabella, toAdd);

        JOptionPane.showMessageDialog(null, "Libro salvato!", successoOperazione, JOptionPane.INFORMATION_MESSAGE);
        grafica.setContatore(modelloTabella.getRowCount());
        finestra.dispose();  //chisura automatica finestra se il libro è stato salvato
    }


    public static void ModificaLibro(Libro libroDaModificare, int rigaLibroMod, JTextField campoTitolo, JTextField campoAutoreNome,
                                     JTextField campoAutoreCognome, JTextField campoIsbn, JComboBox<String> campoGenere,
                                     JComboBox<String> campoStato, JComboBox<String> campoValutazione,
                                     FinestraParametriLibro finestra,DefaultTableModel modelloTabella) {

        Libro toMod = creaLibroDaForm(campoTitolo,campoAutoreNome,campoAutoreCognome,campoIsbn,campoGenere,campoStato,campoValutazione);

        libreria.rimuoviLibro(libroDaModificare);

        if (libreria.contains(toMod)) {
            libreria.aggiungiLibro(libroDaModificare);
            JOptionPane.showMessageDialog(null, "Libro con questi dati è già presente!", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Se non esiste, procedi con la modifica
        libreria.aggiungiLibro(toMod);

        aggiornaLibroGUI(modelloTabella,rigaLibroMod,toMod);
        JOptionPane.showMessageDialog(null, "Libro modificato!", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);
        finestra.dispose();
    }



    public static void eliminaLibro(Controller c, Map.Entry<Integer,String> toDelete , DefaultTableModel modelloTabella){
        Libro l = Controller.getLibroFromISBN(toDelete.getValue());
        libreria.rimuoviLibro(l);
        modelloTabella.removeRow(toDelete.getKey());
        grafica.setContatore(modelloTabella.getRowCount());
    }


    /**
    Gestione di salvataggio/caricamento su/da file
     */

    public static void salva(String estensioneFile){
        String path = cartellaDownload + File.separator + "salvataggio." + estensioneFile;
        SalvaRipristinaFactoryIF fact = new SalvaRipristinaFactory();
        SalvaRipristinaStrategyIF sf = fact.setStrategy(path,estensioneFile);
        sf.salva(path);
        JOptionPane.showMessageDialog(null, "Libreria salvata!", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void caricaLibreriaDaFile(DefaultTableModel modelloTabella){
        JFrame frame = new JFrame();

        //Inizializzazione del file choser nella cartella downloads
        JFileChooser fileChooser = new JFileChooser(cartellaDownload);

        //Scelta delle estensioni accettate
        FileNameExtensionFilter filtroEstensione = new FileNameExtensionFilter(".json,.csv", "csv", "json");
        fileChooser.setFileFilter(filtroEstensione);


        int ret = fileChooser.showOpenDialog(frame);
        if(ret == JFileChooser.APPROVE_OPTION) {
            File fileSelezionato = fileChooser.getSelectedFile();
            String pathFile = fileSelezionato.getAbsolutePath();
            String estensioneFile = FilenameUtils.getExtension(pathFile);

            SalvaRipristinaFactoryIF srf = new SalvaRipristinaFactory();
            SalvaRipristinaStrategyIF srs = srf.setStrategy(pathFile, estensioneFile);
            srs.ripristina(pathFile);
            aggiornaTabellaGUI(libreria.getLibreria());
            grafica.setContatore(modelloTabella.getRowCount());
            JOptionPane.showMessageDialog(null, "Libreria importata da file !",
                    "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Errore nell'importazione", "Errore", JOptionPane.WARNING_MESSAGE);
        }
    }



    /**
    Comparsa menù in base a filtro selezionato
     */
    public static void selezionaFiltro(String tipoFiltro){
        switch(tipoFiltro){
            case "Filtra per genere": {
                grafica.setPopupGeneri(true);
                break;
            }
            case "Filtra per stato lettura":{
                grafica.setPopupStatoLettura(true);
                break;
            }
            default:
                break;
        }
    }

    /**
    Filtraggio, ordinamento, ricerca
     */
    public static void applicaFiltro(String paramFiltro, String tipoFiltro){
        FiltroFactoryIF fact = new FiltroFactory();
        FiltroStrategyIF fs = fact.setStrategy(paramFiltro, tipoFiltro);
        List<Libro> ret = fs.filtra();
        aggiornaTabellaGUI(ret);
    }


    public static void applicaOrdinamento(String tipoOrdinamento){
        OrdinamentoFactoryIF ordFac = new OrdinamentoFactory();
        OrdinamentoStrategyIF ordStr = ordFac.setStrategy(tipoOrdinamento);
        List<Libro> ret = ordStr.ordina();
        aggiornaTabellaGUI(ret);
    }


    public static void ricerca (String testoDaRicercare, String tipoRicerca){
        RicercaFactoryIF ricFac = new RicercaFactory();
        RicercaStrategyIF ricStr = ricFac.setStrategy(testoDaRicercare,tipoRicerca);
        List<Libro>ret = ricStr.ricerca();
        aggiornaTabellaGUI(ret);
    }


    /**
    Utils
     */

    //Creazione di un oggetto Libro partendo dai dati presenti nel modulo di compilazione
    private static Libro creaLibroDaForm (JTextField campoTitolo, JTextField campoAutoreNome, JTextField campoAutoreCognome, JTextField campoIsbn,
                                          JComboBox<String> campoGenere, JComboBox<String> campoStato, JComboBox<String> campoValutazione){
        String titolo = campoTitolo.getText();
        String autoreNome = campoAutoreNome.getText();
        String autoreCognome = campoAutoreCognome.getText();
        String isbn = campoIsbn.getText();
        String genere = (String) campoGenere.getSelectedItem();
        String stato = (String) campoStato.getSelectedItem();
        String valutazione = (String) campoValutazione.getSelectedItem();

        Libro l = new Libro.Builder(titolo,autoreCognome, isbn)
                .setAutoreNome(autoreNome)
                .setGenereLibri(Genere_Libri.valueOf(cleanString(genere)))
                .setStatoLettura(Stato_Lettura.valueOf(cleanString(stato)))
                .setValutazionePersonale(Valutazione_Personale.valueOf(cleanString(valutazione)))
                .build();
        return l;
    }

    //Boolean check per controllare che tutti i parametri obbligatori siano stati inseriti
    private static boolean campiObbligatoriOk(String titolo, String autoreCognome, String isbn, String segnaposto){
        return (titolo.isEmpty() || autoreCognome.isEmpty() || isbn.isEmpty() || titolo.equals(segnaposto) || autoreCognome.equals(segnaposto) || isbn.equals(segnaposto));
    }

    private static void aggiornaTabellaGUI(List<Libro>libri){
        DefaultTableModel modelloTabella = grafica.getModelloTabella();

        modelloTabella.setRowCount(0);

        for (Libro libro : libri) {
            Object[] riga = {
                    libro.getTitolo(),
                    libro.getAutore(),
                    libro.getISBN(),
                    libro.getGenLib().toString().replace("_"," "),
                    libro.getValPers().toString().replace("_"," "),
                    libro.getStatLett().toString().replace("_", " ")
            };
            modelloTabella.addRow(riga);
        }
    }

    //Ripristina la visualizzazione originale della tabella ('eliminando' l'effetto di filtro/ordinamento/ricerca)
    public static void ripristinaVista() {
        aggiornaTabellaGUI(libreria.getLibreria());
    }

    private static String cleanString(String oldString){
        return oldString.replace(" ", "_");
    }

    public static Libro getLibroFromISBN (String isbn){
        for (Libro l : libreria.getLibreria()){
            if (l.getISBN().equals(isbn))
                return l;
        }
        return null;
    }

   //Visualizzazione di un libro aggiunto nella tabella
    private static void aggiungiLibroGUI(DefaultTableModel modelloTabella, Libro l){
        String[] rigaTabella = new String[]{
                l.getTitolo(),
                l.getAutore(),
                l.getISBN(),
                l.getGenLib().toString().replace("_"," "),
                l.getValPers().toString().replace("_", " "),
                l.getStatLett().toString().replace("_", " ")
        };

        modelloTabella.addRow(rigaTabella);
    }

    //Visualizzazione di un libro modificato nella tabella
    private static void aggiornaLibroGUI(DefaultTableModel modelloTabella, int riga, Libro l) {
        modelloTabella.setValueAt(l.getTitolo(), riga, 0);
        modelloTabella.setValueAt(l.getAutore(), riga, 1);
        modelloTabella.setValueAt(l.getISBN(), riga, 2);
        modelloTabella.setValueAt(l.getGenLib().toString().replace("_"," "), riga, 3);
        modelloTabella.setValueAt(l.getValPers().toString().replace("_"," "), riga, 4);
        modelloTabella.setValueAt(l.getStatLett().toString().replace("_"," "), riga, 5);
    }



}
