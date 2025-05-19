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
    }

    public static void main(String[] args) {
        // Esegui l'interfaccia grafica sul thread dell'EDT
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setVisible(true);
        });
    }

}
