package main.java.frontend;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setVisible(true);
        });
    }
}
