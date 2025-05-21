package main.java;

import main.java.backend.LibreriaSingleton;
import main.java.controller.Controller;
import main.java.frontend.GUI;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            LibreriaSingleton libreria = LibreriaSingleton.INSTANCE;
            Controller controller = new Controller(libreria, frame);
            frame.setVisible(true);
        });
    }
}
