package main.java.frontend;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class GUI extends JFrame {

    private JTable tabella;

    public GUI() {
        setTitle("Gestore Libreria Personale");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); //finestra centrata su schermo


        // Pannello bottoni
        JPanel pannelloBottoni = new JPanel();
        pannelloBottoni.setLayout(null);
        pannelloBottoni.setBounds(0, 0, 300, 600);
        add(pannelloBottoni);


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
    }

    public static void main(String[] args) {
        // Esegui l'interfaccia grafica sul thread dell'EDT
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setVisible(true);
        });
    }

}
