package main.java.frontend;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
//import com.formdev.flatlaf.FlatLightLaf;
//import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

public class LibreriaApp extends JFrame {
    private JTable tabellaLibri;
    private DefaultTableModel modelloTabella;
    private JTextField campoTitolo, campoAutore, campoAnno;
    private JButton bottoneAggiungi, bottoneRimuovi, bottoneModifica, bottoneCerca;
    private JTextField campoCerca;
    private List<Object[]> libriOriginali = new ArrayList<>();

    public LibreriaApp() {
        /*
        // Imposta il tema moderno
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            // Alternativa dark theme
            // FlatArcDarkIJTheme.setup();
        } catch (Exception e) {
            System.err.println("Impossibile impostare il tema FlatLaf: " + e);
        }
        */

        setTitle("Libreria Personale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Colori personalizzati
        Color accentColor = new Color(56, 142, 233);
        Color bgColor = new Color(245, 245, 247);

        // Icona applicazione (opzionale)
        // setIconImage(new ImageIcon("book_icon.png").getImage());

        // Modello tabella
        modelloTabella = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende le celle non modificabili direttamente
            }
        };

        modelloTabella.setColumnIdentifiers(new String[]{"Titolo", "Autore", "Anno", "Stato"});
        tabellaLibri = new JTable(modelloTabella);

        // Personalizzazione tabella
        tabellaLibri.setRowHeight(28);
        tabellaLibri.setShowGrid(false);
        tabellaLibri.setIntercellSpacing(new Dimension(0, 0));
        tabellaLibri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaLibri.setSelectionBackground(accentColor);
        tabellaLibri.setSelectionForeground(Color.WHITE);
        tabellaLibri.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabellaLibri.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabellaLibri.getTableHeader().setBackground(new Color(240, 240, 240));
        tabellaLibri.getTableHeader().setForeground(new Color(60, 60, 60));

        // Personalizzazione renderer per stile alternato righe
        tabellaLibri.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value,
                        isSelected, false, row, column);

                if (!isSelected) {
                    comp.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 248, 250));
                }
                setBorder(new EmptyBorder(0, 10, 0, 0)); // Padding nelle celle
                return comp;
            }
        });

        // Scroll pane per la tabella con bordo sottile
        JScrollPane scrollPane = new JScrollPane(tabellaLibri);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        // Pannello superiore per ricerca
        JPanel pannelloRicerca = new JPanel(new BorderLayout(10, 0));
        pannelloRicerca.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        pannelloRicerca.setBackground(bgColor);

        JPanel ricercaInputPanel = new JPanel();
        ricercaInputPanel.setLayout(new BoxLayout(ricercaInputPanel, BoxLayout.X_AXIS));
        ricercaInputPanel.setBackground(bgColor);

        campoCerca = new JTextField();
        campoCerca.putClientProperty("JTextField.placeholderText", "Cerca libri...");
        bottoneCerca = createStyledButton("Cerca", accentColor);

        ricercaInputPanel.add(campoCerca);
        ricercaInputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        ricercaInputPanel.add(bottoneCerca);

        pannelloRicerca.add(new JLabel("Gestione Libreria"), BorderLayout.WEST);
        pannelloRicerca.add(ricercaInputPanel, BorderLayout.EAST);

        // Pannello input con layout migliorato
        JPanel pannelloInput = new JPanel();
        pannelloInput.setLayout(new BoxLayout(pannelloInput, BoxLayout.Y_AXIS));
        pannelloInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        pannelloInput.setBackground(Color.WHITE);

        JPanel campiPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        campiPanel.setBackground(Color.WHITE);

        // Crea pannelli per ogni campo
        campiPanel.add(createLabeledTextField("Titolo", campoTitolo = new JTextField()));
        campiPanel.add(createLabeledTextField("Autore", campoAutore = new JTextField()));
        campiPanel.add(createLabeledTextField("Anno", campoAnno = new JTextField()));

        // Pannello per bottoni
        JPanel bottoniPanel = new JPanel();
        bottoniPanel.setLayout(new BoxLayout(bottoniPanel, BoxLayout.X_AXIS));
        bottoniPanel.setBackground(Color.WHITE);
        bottoniPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Bottoni con stile moderno
        bottoneAggiungi = createStyledButton("Aggiungi Libro", accentColor);
        bottoneModifica = createStyledButton("Modifica", new Color(255, 171, 64));
        bottoneRimuovi = createStyledButton("Rimuovi", new Color(235, 87, 87));

        bottoniPanel.add(Box.createHorizontalGlue());
        bottoniPanel.add(bottoneAggiungi);
        bottoniPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottoniPanel.add(bottoneModifica);
        bottoniPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottoniPanel.add(bottoneRimuovi);

        pannelloInput.add(campiPanel);
        pannelloInput.add(bottoniPanel);

        // Aggiunta pannello di stato in basso
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        statusPanel.setBackground(bgColor);
        JLabel statusLabel = new JLabel("Totale libri: 0");
        statusPanel.add(statusLabel, BorderLayout.WEST);

        // Layout principale
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bgColor);

        mainPanel.add(pannelloRicerca, BorderLayout.NORTH);
        mainPanel.add(pannelloInput, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        // Listener per aggiunta libro
        bottoneAggiungi.addActionListener(e -> {
            String titolo = campoTitolo.getText().trim();
            String autore = campoAutore.getText().trim();
            String anno = campoAnno.getText().trim();

            if (!titolo.isEmpty() && !autore.isEmpty() && !anno.isEmpty()) {
                Object[] nuovoLibro = new Object[]{titolo, autore, anno, "Disponibile"};
                modelloTabella.addRow(nuovoLibro);
                libriOriginali.add(nuovoLibro);
                campoTitolo.setText("");
                campoAutore.setText("");
                campoAnno.setText("");
                statusLabel.setText("Totale libri: " + modelloTabella.getRowCount());
                showNotification("Libro aggiunto con successo!", NotificationType.SUCCESS);
            } else {
                showNotification("Inserisci tutti i campi", NotificationType.ERROR);
            }
        });

        // Listener per rimozione
        bottoneRimuovi.addActionListener(e -> {
            int selectedRow = tabellaLibri.getSelectedRow();
            if (selectedRow >= 0) {
                // Rimuovere anche dalla lista originale per la ricerca
                Object[] libro = new Object[]{
                        modelloTabella.getValueAt(selectedRow, 0),
                        modelloTabella.getValueAt(selectedRow, 1),
                        modelloTabella.getValueAt(selectedRow, 2),
                        modelloTabella.getValueAt(selectedRow, 3)
                };

                libriOriginali.removeIf(l -> l[0].equals(libro[0]) && l[1].equals(libro[1]));
                modelloTabella.removeRow(selectedRow);
                statusLabel.setText("Totale libri: " + modelloTabella.getRowCount());
                showNotification("Libro rimosso", NotificationType.INFO);
            } else {
                showNotification("Seleziona una riga da rimuovere", NotificationType.WARNING);
            }
        });

        // Listener per modifica
        bottoneModifica.addActionListener(e -> {
            int selectedRow = tabellaLibri.getSelectedRow();
            if (selectedRow >= 0) {
                String titolo = campoTitolo.getText().trim();
                String autore = campoAutore.getText().trim();
                String anno = campoAnno.getText().trim();

                if (!titolo.isEmpty() && !autore.isEmpty() && !anno.isEmpty()) {
                    modelloTabella.setValueAt(titolo, selectedRow, 0);
                    modelloTabella.setValueAt(autore, selectedRow, 1);
                    modelloTabella.setValueAt(anno, selectedRow, 2);

                    // Aggiorna anche la lista originale
                    for (int i = 0; i < libriOriginali.size(); i++) {
                        Object[] libro = libriOriginali.get(i);
                        if (libro[0].equals(modelloTabella.getValueAt(selectedRow, 0))
                                && libro[1].equals(modelloTabella.getValueAt(selectedRow, 1))) {
                            libro[0] = titolo;
                            libro[1] = autore;
                            libro[2] = anno;
                            break;
                        }
                    }

                    campoTitolo.setText("");
                    campoAutore.setText("");
                    campoAnno.setText("");
                    showNotification("Libro modificato", NotificationType.SUCCESS);
                } else {
                    showNotification("Inserisci tutti i campi", NotificationType.ERROR);
                }
            } else {
                showNotification("Seleziona un libro da modificare", NotificationType.WARNING);
            }
        });

        // Listener per la selezione della tabella
        tabellaLibri.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabellaLibri.getSelectedRow();
                if (selectedRow >= 0) {
                    campoTitolo.setText(modelloTabella.getValueAt(selectedRow, 0).toString());
                    campoAutore.setText(modelloTabella.getValueAt(selectedRow, 1).toString());
                    campoAnno.setText(modelloTabella.getValueAt(selectedRow, 2).toString());
                }
            }
        });

        // Listener per ricerca
        bottoneCerca.addActionListener(e -> cercaLibri());
        campoCerca.addActionListener(e -> cercaLibri());

        // Aggiungi qualche libro di esempio
        aggiungiLibriEsempio();
        statusLabel.setText("Totale libri: " + modelloTabella.getRowCount());
    }

    private void cercaLibri() {
        String testoCerca = campoCerca.getText().trim().toLowerCase();
        if (testoCerca.isEmpty()) {
            // Ripristina lista originale
            modelloTabella.setRowCount(0);
            for (Object[] libro : libriOriginali) {
                modelloTabella.addRow(libro);
            }
            return;
        }

        modelloTabella.setRowCount(0);
        for (Object[] libro : libriOriginali) {
            String titolo = libro[0].toString().toLowerCase();
            String autore = libro[1].toString().toLowerCase();
            String anno = libro[2].toString().toLowerCase();

            if (titolo.contains(testoCerca) || autore.contains(testoCerca) || anno.contains(testoCerca)) {
                modelloTabella.addRow(libro);
            }
        }

        if (modelloTabella.getRowCount() == 0) {
            showNotification("Nessun libro trovato", NotificationType.INFO);
        }
    }

    private JPanel createLabeledTextField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(100, 100, 100));

        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);

        return panel;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(130, 36));

        // Effetto hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(makeColorDarker(color, 0.1f));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(makeColorDarker(color, 0.2f));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(makeColorDarker(color, 0.1f));
            }
        });

        return button;
    }

    private Color makeColorDarker(Color color, float factor) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return Color.getHSBColor(hsb[0], hsb[1], Math.max(0, hsb[2] - factor));
    }

    private void aggiungiLibriEsempio() {
        String[][] esempi = {
                {"Il Nome della Rosa", "Umberto Eco", "1980"},
                {"1984", "George Orwell", "1949"},
                {"Il Piccolo Principe", "Antoine de Saint-Exupéry", "1943"},
                {"Orgoglio e Pregiudizio", "Jane Austen", "1813"},
                {"Lo Hobbit", "J.R.R. Tolkien", "1937"}
        };

        for (String[] libro : esempi) {
            Object[] nuovoLibro = {libro[0], libro[1], libro[2], "Disponibile"};
            modelloTabella.addRow(nuovoLibro);
            libriOriginali.add(nuovoLibro);
        }
    }

    // Enumerazione per tipi di notifica
    enum NotificationType {
        SUCCESS, ERROR, WARNING, INFO
    }

    // Mostra notifiche temporanee
    private void showNotification(String message, NotificationType type) {
        Color bgColor;
        Color fgColor = Color.WHITE;
        String iconText;

        switch (type) {
            case SUCCESS:
                bgColor = new Color(39, 174, 96);
                iconText = "✓";
                break;
            case ERROR:
                bgColor = new Color(235, 87, 87);
                iconText = "✗";
                break;
            case WARNING:
                bgColor = new Color(242, 153, 74);
                iconText = "!";
                break;
            default: // INFO
                bgColor = new Color(52, 152, 219);
                iconText = "ℹ";
        }

        // Crea un pannello di notifica pop-up
        JPanel notificationPanel = new JPanel(new BorderLayout(10, 0));
        notificationPanel.setBackground(bgColor);
        notificationPanel.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        JLabel iconLabel = new JLabel(iconText);
        iconLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        iconLabel.setForeground(fgColor);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setForeground(fgColor);

        notificationPanel.add(iconLabel, BorderLayout.WEST);
        notificationPanel.add(messageLabel, BorderLayout.CENTER);

        // Crea JDialog per mostrare la notifica
        JDialog dialog = new JDialog(this);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));
        dialog.getContentPane().add(notificationPanel);
        dialog.pack();

        // Posiziona in basso a destra
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = getX() + getWidth() - dialog.getWidth() - 20;
        int y = getY() + getHeight() - dialog.getHeight() - 20;
        dialog.setLocation(x, y);

        dialog.setVisible(true);

        // Timer per chiudere automaticamente la notifica
        Timer timer = new Timer(3000, e -> {
            dialog.dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }

    // Main per eseguire l'applicazione
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibreriaApp().setVisible(true);
        });
    }
}