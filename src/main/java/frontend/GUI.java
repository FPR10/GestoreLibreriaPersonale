package main.java.frontend;

import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
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
    private final JComboBox<String>comboOrdinamento;
    private final JTextField barraRicerca;
    private  JRadioButton r1;
    private  JRadioButton r2;
    private  JRadioButton r3;
    private ButtonGroup gruppo;
    private int [] ultimaRigaSelezionata = {-1};
    private static int contElementi = 0;
    private JLabel statusLabel;



    public GUI() {

        /*
        INIZIALIZZAZIONE FINESTRA
         */
        setTitle("Gestore Libreria Personale");
        setSize(1400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); //finestra centrata su schermo


        /*
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
        modelloTabella.setColumnIdentifiers(new String[]{"Titolo", "Autore", "ISBN","Genere", "Stato lettura", "Valutazione"});
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


        //Sfondo righe alternato in base alla riga
        tabella.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value,
                        isSelected, false, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        comp.setBackground(Color.WHITE); // Righe pari: bianco
                    } else {
                        comp.setBackground(Color.lightGray); // Righe dispari: grigio chiarissimo
                    }
                }

                setBorder(new EmptyBorder(0, 10, 0, 0)); // Padding nelle celle
                return comp;
            }
        });


        /*
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


        /*
        BOTTONI
         */

        // Bottone aggiunta libro
        bottoneAggiungi = new JButton("+ Aggiungi libro");
        bottoneAggiungi.setBackground(Color.green);
        bottoneAggiungi.setFocusPainted(false);
        bottoneAggiungi.setBounds(50, 50, 150, 30);
        pannelloBottoni.add(bottoneAggiungi);


        // Bottone modifica libro
        // \uD83D\uDD8A\uFE0F -> carattere della matita
        bottoneModifica = new JButton("\uD83D\uDD8A\uFE0F" + " "+ "Modifica libro");
        bottoneModifica.setBackground(Color.orange);
        bottoneModifica.setFocusPainted(false);
        bottoneModifica.setBounds(50, 90, 150, 30);
        pannelloBottoni.add(bottoneModifica);


        // Bottone elimina libro
        bottoneElimina = new JButton("Elimina libro");
        bottoneElimina.setBackground(Color.red);
        bottoneElimina.setFocusPainted(false);
        bottoneElimina.setBounds(50, 130, 150, 30);
        pannelloBottoni.add(bottoneElimina);


        // Bottoni di corredo alla ricerca
        r1 = new JRadioButton("Per autore");
        r1.setActionCommand("autore");
        r1.setFocusPainted(false);
        r1.setBounds(400, 50, 100, 20);
        pannelloBottoni.add(r1);

        r2 = new JRadioButton("Per titolo");
        r2.setActionCommand("titolo");
        r2.setFocusPainted(false);
        r2.setBounds(500, 50, 100, 20);
        pannelloBottoni.add(r2);

        r3 = new JRadioButton("Per ISBN");
        r3.setActionCommand("isbn");
        r3.setFocusPainted(false);
        r3.setBounds(600, 50, 100, 20);
        pannelloBottoni.add(r3);

        gruppo = new ButtonGroup();
        gruppo.add(r1);
        gruppo.add(r2);
        gruppo.add(r3);

        // Barra di ricerca
        barraRicerca = new JTextField("Cerca libro");
        barraRicerca.setBounds(400, 85, 300, 25);
        barraRicerca.setForeground(java.awt.Color.GRAY);
        pannelloBottoni.add(barraRicerca);

        // Bottone ricerca libro
        bottoneRicerca = new JButton("\uD83D\uDD0D");
        bottoneRicerca.setFocusPainted(false);
        bottoneRicerca.setBounds(725, 85, 50, 25);
        pannelloBottoni.add(bottoneRicerca);


        // Bottone salvataggio JSON
        bottoneSalvaJSON = new JButton("\uD83D\uDCBE" + " JSON");
        bottoneSalvaJSON.setFocusPainted(false);
        pannelloSalvataggio.add(bottoneSalvaJSON);


        //Bottone salvataggio CSV
        bottoneSalvaCSV = new JButton("\uD83D\uDCBE" + " CSV");
        bottoneSalvaCSV.setFocusPainted(false);
        pannelloSalvataggio.add(bottoneSalvaCSV);


        // Menu a tendina per filtrare
        String[] opzioniFiltro = {"Nessun filtro", "Filtra per genere", "Filtra per stato lettura"};
        comboFiltro = new JComboBox<>(opzioniFiltro);
        comboFiltro.setBounds(820, 50, 300, 25);
        pannelloBottoni.add(comboFiltro);

        // Menu a tendina per filtrare
        String[] opzioniOrdinamento = {"Ordina per autore", "Ordina per titolo", "Ordina per valutazione"};
        comboOrdinamento = new JComboBox<>(opzioniOrdinamento);
        comboOrdinamento.setBounds(820, 145, 300, 25);
        pannelloBottoni.add(comboOrdinamento);

    }

    public Map.Entry<Integer, Libro> getLibroSelezionato() {
        int rigaSelezionata = tabella.getSelectedRow();
        if (rigaSelezionata == -1) {
            return null;
        }

        String titolo = (String) modelloTabella.getValueAt(rigaSelezionata, 0);
        String autore = (String) modelloTabella.getValueAt(rigaSelezionata, 1);
        String isbn = (String) modelloTabella.getValueAt(rigaSelezionata, 2);
        String genere = (String) modelloTabella.getValueAt(rigaSelezionata, 3);
        String stato = (String) modelloTabella.getValueAt(rigaSelezionata, 4);
        String cleanedStato = stato.replace(" ", "_");

        Libro libro = new Libro.Builder(titolo, autore, isbn)
                .setGenereLibri(Genere_Libri.valueOf(genere))
                .setStatoLettura(Stato_Lettura.valueOf(cleanedStato))
                .build();

        return new AbstractMap.SimpleEntry<>(rigaSelezionata, libro);
    }



    private String getFiltroRicerca() {
        ButtonModel bottoneSel = gruppo.getSelection();
        String tipoRicerca = bottoneSel.getActionCommand();
        return switch (tipoRicerca) {
            case "titolo" -> "titolo";
            case "isbn" -> "isbn";
            default -> "autore";
        };
    }

    public DefaultTableModel getModelloTabella(){
        return modelloTabella;
    }

    public void setContatore(int cont) {
        contElementi = cont;
        statusLabel.setText("Totale libri: " + cont);
    }



    public void setController(Controller controller) {
          //Istanzia FinestraParametriLibro per la comparsa del modulo aggiuntivo per i parametri libro
          bottoneAggiungi.addActionListener(e-> new FinestraParametriLibro(controller, modelloTabella));

          //Ripristino colore riga selezioanta
          tabella.addMouseListener(Controller.ripristinaSelezione(tabella,ultimaRigaSelezionata));

          //Listener per far sparire 'Cerca libro'
          barraRicerca.addFocusListener(Controller.gestisciFocus(barraRicerca, "Cerca libro", ""));

          bottoneModifica.addActionListener(e -> Controller.modificaLibro(getLibroSelezionato().getValue()));

          bottoneElimina.addActionListener(e -> Controller.eliminaLibro(getLibroSelezionato(),modelloTabella));

          bottoneSalvaJSON.addActionListener(e -> Controller.salvaJSON());

          bottoneSalvaCSV.addActionListener(e -> Controller.salvaCSV());

          comboFiltro.addActionListener(e -> Controller.applicaFiltro((String) comboFiltro.getSelectedItem(), modelloTabella));

          comboOrdinamento.addActionListener(e -> Controller.applicaOrdinamento((String) comboOrdinamento.getSelectedItem()));

           bottoneRicerca.addActionListener(e -> Controller.ricerca(barraRicerca.getText(), getFiltroRicerca()));


    }
}
