package main.java.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.Objects;

public class GUI extends JFrame{

    private JTable tabella;

    public GUI() {
        /*
        INIZIALIZZAZIONE
         */
        setTitle("Gestore Libreria Personale");
        setSize(900, 600);
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

        //Parametri essenziali della visualizzazione
        modelloTabella.setColumnIdentifiers(new String[]{"Titolo", "Autore", "ISBN","Genere", "Stato lettura"});
        modelloTabella.addRow(new String[]{"aa","bb","cc","dd","ee"});
        tabella = new JTable(modelloTabella);


        // Personalizzazione tabella
        tabella.setShowGrid(false);
        tabella.setRowHeight(28);
        tabella.setIntercellSpacing(new Dimension(0, 0));
        tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabella.setSelectionBackground(Color.BLACK);
        tabella.setSelectionForeground(Color.WHITE);
        tabella.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabella.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabella.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tabella.getTableHeader().setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // Aggiungi padding superiore


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

        // Pannello bottoni
        JPanel pannelloBottoni = new JPanel();
        pannelloBottoni.setLayout(null);
        pannelloBottoni.setBounds(0, 0, 1000, 600);
        add(pannelloBottoni);

        // Pannello di stato in basso
        JPanel pannelloStato = new JPanel();
        pannelloStato.setLayout(new BorderLayout());
        pannelloStato.setBounds(0, 540, 900, 40);
        pannelloStato.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pannelloStato.setBackground(Color.LIGHT_GRAY);
        JLabel statusLabel = new JLabel("Totale libri: 0");
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
        JButton bottoneAggiungi = new JButton("+ Aggiungi libro");
        bottoneAggiungi.setBackground(Color.green);
        bottoneAggiungi.setFocusPainted(false);
        bottoneAggiungi.setBounds(50, 50, 150, 30);
        pannelloBottoni.add(bottoneAggiungi);


        // Bottone modifica libro
        JButton bottoneModifica = new JButton("\uD83D\uDD8A\uFE0F" + " "+ "Modifica");
        bottoneModifica.setBackground(Color.orange);
        bottoneModifica.setFocusPainted(false);
        bottoneModifica.setBounds(50, 90, 150, 30);
        pannelloBottoni.add(bottoneModifica);

        // Bottone modifica libro
        JButton bottoneElimina = new JButton("Elimina");
        bottoneElimina.setBackground(Color.red);
        bottoneElimina.setFocusPainted(false);
        bottoneElimina.setBounds(50, 130, 150, 30);
        pannelloBottoni.add(bottoneElimina);


        // RadioButton
        JRadioButton r1 = new JRadioButton("Per autore");
        r1.setFocusPainted(false);
        r1.setBounds(500, 50, 100, 20);
        pannelloBottoni.add(r1);

        JRadioButton r2 = new JRadioButton("Per titolo");
        r2.setFocusPainted(false);
        r2.setBounds(600, 50, 100, 20);
        pannelloBottoni.add(r2);

        JRadioButton r3 = new JRadioButton("Per ISBN");
        r3.setFocusPainted(false);
        r3.setBounds(700, 50, 100, 20);
        pannelloBottoni.add(r3);

        ButtonGroup gruppo = new ButtonGroup();
        gruppo.add(r1);
        gruppo.add(r2);
        gruppo.add(r3);


        // Barra di ricerca
        JTextField barraRicerca = new JTextField("Cerca libro");
        barraRicerca.setBounds(500, 85, 300, 25);
        barraRicerca.setForeground(java.awt.Color.GRAY);
        pannelloBottoni.add(barraRicerca);

        barraRicerca.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (barraRicerca.getText().equals("Cerca libro")) {
                    barraRicerca.setText("");
                    barraRicerca.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (barraRicerca.getText().isEmpty()) {
                    barraRicerca.setText("Cerca libro");
                    barraRicerca.setForeground(java.awt.Color.GRAY);
                }
            }
        });


        // Bottone modifica libro
        JButton bottoneRicerca = new JButton("\uD83D\uDD0D");
        bottoneRicerca.setFocusPainted(false);
        bottoneRicerca.setBounds(820, 85, 50, 25);
        pannelloBottoni.add(bottoneRicerca);


        // Bottone salvataggio JSON
        JButton bottoneSalvaJSON = new JButton("\uD83D\uDCBE" + " JSON");
        bottoneSalvaJSON.setFocusPainted(false);
        pannelloSalvataggio.add(bottoneSalvaJSON);


        //Bottone salvataggio CSV
        JButton bottoneSalvaCSV = new JButton("\uD83D\uDCBE" + " CSV");
        bottoneSalvaCSV.setFocusPainted(false);
        pannelloSalvataggio.add(bottoneSalvaCSV);

    }




    public static void main(String[] args) {
        // Esegui l'interfaccia grafica sul thread dell'EDT
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setVisible(true);
        });
    }

}
