package main.java.frontend;

import main.java.controller.Controller;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.AbstractMap;
import java.util.Map;


public class GUI extends JFrame{

    private JTable tabella;
    private DefaultTableModel modelloTabella;

    private final JButton bottoneAggiungi;
    private final JButton bottoneModifica;
    private final JButton bottoneElimina;
    private final JButton bottoneRicerca;
    private final JButton bottoneSalvaJSON;
    private final JButton bottoneSalvaCSV;
    private final JComboBox<String> comboFiltro;
    private  JMenuItem voceGeneri;
    private  JPopupMenu popupGeneri;
    private  JPopupMenu popupStatoLettura;
    private JMenuItem voceStatoLettura;
    private final JComboBox<String>comboOrdinamento;
    private final JTextField barraRicerca;
    private final JButton bottoneRipristinaVista;
    private final JButton bottoneRipristinoFile;
    private  JRadioButton r1;
    private  JRadioButton r2;
    private  JRadioButton r3;
    private ButtonGroup gruppo;
    private int [] ultimaRigaSelezionata = {-1};
    private static int contElementi = 0;
    private JLabel statusLabel;




    public GUI() {

        /**
        INIZIALIZZAZIONE FINESTRA
         */
        setTitle("Gestore Libreria Personale");
        setSize(1400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); //finestra centrata su schermo


        /**
        INIZIALIZZAZIONE E MODIFICA TABELLA
         */

        //Impedisco di modificare direttamente le celle della tabella
        modelloTabella = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int righe, int colonne) {
                return false;
            }
        };

        //Parametri header tabella
        modelloTabella.setColumnIdentifiers(new String[]{"Titolo", "Autore", "ISBN","Genere", "Valutazione", "Stato lettura"});
        tabella = new JTable(modelloTabella);


        // Personalizzazione tabella
        tabella.setShowGrid(false);
        tabella.setRowHeight(28);
        tabella.setIntercellSpacing(new Dimension(0, 0));
        tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabella.setSelectionForeground(Color.WHITE);
        tabella.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabella.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabella.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tabella.getTableHeader().setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // Aggiungi padding superiore

        // Colore della riga selezionata
        tabella.setSelectionBackground(Color.BLACK);


        //Colore sfondo alternato per effetto tabella
        tabella.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value,
                        isSelected, false, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        comp.setBackground(Color.WHITE);
                    } else {
                        comp.setBackground(Color.lightGray);
                    }
                }

                setBorder(new EmptyBorder(0, 10, 0, 0)); // Padding nelle celle
                return comp;
            }
        });


        /**
        PANNELLI
         */

        //Pannello scrollabile della tabella
        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setBorder((BorderFactory.createEmptyBorder(35,0,0,0))); //agisce su altezza header tabella
        add(scrollPane, BorderLayout.CENTER);

        // Pannello bottoni
        JPanel pannelloBottoni = new JPanel();
        pannelloBottoni.setLayout(null);
        pannelloBottoni.setPreferredSize(new Dimension(900, 190));
        add(pannelloBottoni, BorderLayout.NORTH);


        // Pannello di stato in basso
        JPanel pannelloStato = new JPanel(new BorderLayout());
        pannelloStato.setPreferredSize(new Dimension(900,40));
        pannelloStato.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        pannelloStato.setBackground(Color.white);
        statusLabel = new JLabel("Totale libri:" + " " +contElementi);
        pannelloStato.add(statusLabel, BorderLayout.WEST);
        add(pannelloStato,BorderLayout.SOUTH);


        //Pannello bottoni salvataggio
        JPanel pannelloSalvataggio = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pannelloSalvataggio.setOpaque(false); // trasparente
        pannelloStato.add(pannelloSalvataggio, BorderLayout.EAST);


        /**
        BOTTONI
         */

        // Bottone aggiunta libro
        bottoneAggiungi = new JButton("+ Aggiungi libro");
        bottoneAggiungi.setBackground(Color.green);
        bottoneAggiungi.setFocusPainted(false);
        bottoneAggiungi.setBounds(150, 50, 150, 30);
        pannelloBottoni.add(bottoneAggiungi);


        // Bottone modifica libro
        // \uD83D\uDD8A\uFE0F -> carattere della matita
        bottoneModifica = new JButton("\uD83D\uDD8A\uFE0F" + " "+ "Modifica libro");
        bottoneModifica.setBackground(Color.orange);
        bottoneModifica.setFocusPainted(false);
        bottoneModifica.setBounds(150, 90, 150, 30);
        pannelloBottoni.add(bottoneModifica);


        // Bottone elimina libro
        bottoneElimina = new JButton("Elimina libro");
        bottoneElimina.setBackground(Color.red);
        bottoneElimina.setFocusPainted(false);
        bottoneElimina.setBounds(150, 130, 150, 30);
        pannelloBottoni.add(bottoneElimina);


        // Bottoni di corredo alla ricerca
        r1 = new JRadioButton("Per autore");
        r1.setActionCommand("autore");
        r1.setFocusPainted(false);
        r1.setBounds(500, 50, 100, 20);
        pannelloBottoni.add(r1);

        r2 = new JRadioButton("Per titolo");
        r2.setActionCommand("titolo");
        r2.setFocusPainted(false);
        r2.setBounds(600, 50, 100, 20);
        pannelloBottoni.add(r2);

        r3 = new JRadioButton("Per ISBN");
        r3.setActionCommand("isbn");
        r3.setFocusPainted(false);
        r3.setBounds(700, 50, 100, 20);
        pannelloBottoni.add(r3);

        gruppo = new ButtonGroup();
        gruppo.add(r1);
        gruppo.add(r2);
        gruppo.add(r3);

        // Barra di ricerca
        barraRicerca = new JTextField("Cerca libro");
        barraRicerca.setBounds(500, 85, 300, 25);
        barraRicerca.setForeground(Color.GRAY);
        pannelloBottoni.add(barraRicerca);

        // Bottone ricerca libro
        bottoneRicerca = new JButton("\uD83D\uDD0D");
        bottoneRicerca.setFocusPainted(false);
        bottoneRicerca.setBounds(825, 85, 50, 25);
        pannelloBottoni.add(bottoneRicerca);


        //Bottone ripristino file
        bottoneRipristinoFile = new JButton("Carica libreria");
        bottoneRipristinoFile.setFocusPainted(false);
        pannelloSalvataggio.add(bottoneRipristinoFile);


        // Bottone salvataggio JSON
        bottoneSalvaJSON = new JButton("\uD83D\uDCBE" + " JSON");
        bottoneSalvaJSON.setFocusPainted(false);
        pannelloSalvataggio.add(bottoneSalvaJSON);


        //Bottone salvataggio CSV
        bottoneSalvaCSV = new JButton("\uD83D\uDCBE" + " CSV");
        bottoneSalvaCSV.setFocusPainted(false);
        pannelloSalvataggio.add(bottoneSalvaCSV);


        String[] opzioniFiltro = {"Nessun filtro", "Filtra per genere", "Filtra per stato lettura"};
        comboFiltro = new JComboBox<>(opzioniFiltro);
        comboFiltro.setBounds(1100,50,170,25);
        pannelloBottoni.add(comboFiltro);

        // Menù a popup per generi
        popupGeneri = new JPopupMenu();
        String[] generi = {"BIOGRAFIA", "AUTOBIOGRAFIA", "ROMANZO", "GIALLO", "THRILLER",
                "AVVENTURA", "AZIONE", "FANTASCIENZA", "DISTOPIA", "FANTASY",
                "HORROR", "ROSA", "SCIENTIFICO"};
        for (String g : generi) {
            voceGeneri = new JMenuItem(g);
            voceGeneri.addActionListener(e->Controller.applicaFiltroGenere(((JMenuItem) e.getSource()).getText()));
            popupGeneri.add(voceGeneri);
        }

        //Menù a popup per stato lettura
        popupStatoLettura = new JPopupMenu();
        String[] statiLettura = {"LETTO", "DA LEGGERE", "NON LETTO"};
        for (String sl : statiLettura){
            voceStatoLettura = new JMenuItem(sl);
            voceStatoLettura.addActionListener(e->Controller.applicaFiltroStatLett(((JMenuItem) e.getSource()).getText()));
            popupStatoLettura.add(voceStatoLettura);
        }


        //Menù ordinamento
        String[] opzioniOrdinamento = {"Ordine d'inserimento","Ordina per autore", "Ordina per titolo", "Ordina per valutazione"};
        comboOrdinamento = new JComboBox<>(opzioniOrdinamento);
        comboOrdinamento.setBounds(1100, 100, 170, 25);
        pannelloBottoni.add(comboOrdinamento);

        //Bottone ripristina vista libreria
        bottoneRipristinaVista = new JButton("\uD83D\uDD04" + "Ripristina vista");
        bottoneRipristinaVista.setFocusPainted(false);
        bottoneRipristinaVista.setBounds(1100, 150, 170, 25);
        pannelloBottoni.add(bottoneRipristinaVista);


    }

    /**
    Getter e setter
     */

    //Restituisce indice di riga e isbn del libro selezionato
    public Map.Entry<Integer,String> getLibroSelezionato() {
        int rigaSelezionata = tabella.getSelectedRow();
        String isbn = (String) modelloTabella.getValueAt(rigaSelezionata, 2);
        return new AbstractMap.SimpleEntry<>(rigaSelezionata, isbn);
    }

    //Restituisce la funzionalità di ricerca scelta
    private String getFiltroRicerca() {
        ButtonModel bottoneSel = gruppo.getSelection();
        String tipoRicerca = bottoneSel.getActionCommand();
        return switch (tipoRicerca) {
            case "titolo" -> "Per titolo";
            case "isbn" -> "Per isbn";
            default -> "Per autore";
        };
    }

    public DefaultTableModel getModelloTabella(){
        return modelloTabella;
    }

    //Consente di settare il contatore presente sulla status bar in basso a sinistra
    public void setContatore(int cont) {
        contElementi = cont;
        statusLabel.setText("Totale libri: " + cont);
    }


    public void setPopupGeneri(boolean cond) {
        popupGeneri.setVisible(cond);
        popupStatoLettura.setVisible(!cond);
        popupGeneri.show(comboFiltro, comboFiltro.getWidth(), 0);
    }

    public void setPopupStatoLettura(boolean cond){
        popupStatoLettura.setVisible(cond);
        popupGeneri.setVisible(!cond);
        popupStatoLettura.show(comboFiltro, comboFiltro.getWidth(),0);
    }

    /**
    Set di controller e FinestraParametriLibro per gestire gli action listener
     */


    public void setController(Controller controller) {

          //Gestione aggiunta libro
          bottoneAggiungi.addActionListener(e-> new FinestraParametriLibro(controller, modelloTabella));

          //Gestione modifica libro
          bottoneModifica.addActionListener(e -> new FinestraParametriLibro(controller,modelloTabella,getLibroSelezionato()));

          //Gestione eliminazione libro
          bottoneElimina.addActionListener(e -> Controller.eliminaLibro(controller, getLibroSelezionato(),modelloTabella));

          //Ripristino colore riga ri-selezionata
          tabella.addMouseListener(Controller.ripristinaSelezione(tabella,ultimaRigaSelezionata));

          //Comparsa e scomparsa di 'Cerca libro'
          barraRicerca.addFocusListener(Controller.gestisciFocus(barraRicerca, "Cerca libro", ""));

          bottoneSalvaJSON.addActionListener(e->Controller.salva("json"));

          bottoneSalvaCSV.addActionListener(e->Controller.salva("csv"));

          comboFiltro.addActionListener(e -> Controller.applicaFiltro((String) comboFiltro.getSelectedItem(), modelloTabella));

          comboOrdinamento.addActionListener(e -> Controller.applicaOrdinamento((String) comboOrdinamento.getSelectedItem()));

          bottoneRicerca.addActionListener(e -> Controller.ricerca(barraRicerca.getText(), getFiltroRicerca()));

          bottoneRipristinaVista.addActionListener(e -> Controller.ripristinaVista());

          bottoneRipristinoFile.addActionListener(e -> Controller.caricaLibreriaDaFile(modelloTabella));
    }
}
