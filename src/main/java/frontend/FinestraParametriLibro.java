package main.java.frontend;
import main.java.backend.libro.Libro;
import main.java.controller.Controller;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Map;


public class FinestraParametriLibro extends JFrame {

    private static final String segnapostoTitolo = "Obbligatorio";
    private JTextField campoTitolo;
    private JTextField campoAutoreNome;
    private JTextField campoAutoreCognome;
    private JTextField campoISBN;
    private JComboBox<String> comboGenere;
    private JComboBox<String> comboStato;
    private JComboBox<String> comboValutazione;
    private JButton salva;
    private Libro libroDaModificare;
    private int rigaLibroDaModifica;

    private Controller c;
    private DefaultTableModel modelloTabella;


    /*
    Costruttore per aggiungere un libro
     */
    public FinestraParametriLibro(Controller c, DefaultTableModel modelloTabella) {
        this.c = c;
        this.modelloTabella = modelloTabella;


        setTitle("Aggiungi un nuovo libro");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        // Parametri libro
        JLabel labelTitolo = new JLabel("Titolo:");
        labelTitolo.setBounds(30, 30, 110, 25);
        campoTitolo = new JTextField();
        campoTitolo.setBounds(140, 30, 210, 25);
        labelTitolo.setForeground(Color.RED);

        JLabel labelAutoreNome = new JLabel("Nome autore:");
        labelAutoreNome.setBounds(30, 70, 110, 25);
        campoAutoreNome = new JTextField();
        campoAutoreNome.setBounds(140, 70, 210, 25);

        JLabel labelAutoreCognome = new JLabel("Cognome autore:");
        labelAutoreCognome.setBounds(30, 110, 110, 25);
        campoAutoreCognome = new JTextField();
        campoAutoreCognome.setBounds(140, 110, 210, 25);
        labelAutoreCognome.setForeground(Color.RED);

        JLabel labelISBN = new JLabel("ISBN:");
        labelISBN.setBounds(30, 150, 150, 25);
        campoISBN = new JTextField();
        campoISBN.setBounds(140, 150, 150, 25);
        labelISBN.setForeground(Color.RED);

        JLabel labelGenere = new JLabel("Genere:");
        labelGenere.setBounds(30, 190, 100, 25);
        String[]opzioniGenere = {"NON SELEZIONATO","BIOGRAFIA","AUTOBIOGRAFIA", "ROMANZO","GIALLO", "THRILLER", "AVVENTURA","AZIONE", "FANTASCIENZA",
        "DISTOPIA", "FANTASY", "HORROR", "ROSA","SCIENTIFICO"};
        comboGenere = new JComboBox<>(opzioniGenere);
        comboGenere.setBounds(140, 190, 200, 25);

        JLabel labelStato = new JLabel("Stato lettura:");
        labelStato.setBounds(30, 230, 100, 25);
        String[] opzioniStato = {"NON SELEZIONATO","DA LEGGERE", "IN LETTURA", "LETTO"};
        comboStato = new JComboBox<>(opzioniStato);
        comboStato.setBounds(140, 230, 200, 25);

        JLabel labelValutazione = new JLabel("Valutazione:");
        labelValutazione.setBounds(30, 270, 100, 25);
        String[] opzioniValutazione = {"NON SELEZIONATO","UNA STELLA", "DUE STELLE", "TRE STELLE", "QUATTRO STELLE", "CINQUE STELLE"};
        comboValutazione = new JComboBox<>(opzioniValutazione);
        comboValutazione.setBounds(140, 270, 200, 25);


        salva = new JButton("Salva");
        salva.setBounds(175, 360, 100, 30);

        // Aggiunta delle label al frame
        add(labelTitolo);
        add(campoTitolo);

        add(labelAutoreNome);
        add(campoAutoreNome);

        add(labelAutoreCognome);
        add(campoAutoreCognome);

        add(labelISBN);
        add(campoISBN);

        add(labelGenere);
        add(comboGenere);

        add(labelStato);
        add(comboStato);

        add(labelValutazione);
        add(comboValutazione);

        add(salva);


        //Aggiunta del segnaposto 'obbligatorio' per titolo, autore e isbn
        aggiungiSegnapostoObbligatorio(campoTitolo, segnapostoTitolo);
        aggiungiSegnapostoObbligatorio(campoAutoreCognome, segnapostoTitolo);
        aggiungiSegnapostoObbligatorio(campoISBN, segnapostoTitolo);

        setVisible(true);

        if (this.libroDaModificare == null) {
            setControllerAggiunta(c);
        }
    }


    /*
    Costruttore per modificare un libro
     */
//    public FinestraParametriLibro(Controller c, DefaultTableModel modelloTabella, Map.Entry<Integer, String> toMod) {
//        this(c, modelloTabella);
//
//        Libro libro = Controller.getLibroFromISBN(toMod.getValue()); //isbn
//        this.libroDaModificare = libro;
//        this.rigaLibroDaModifica = toMod.getKey();
//
//
//        setTitle("Modifica libro");
//        salva.setText("Modifica");
//
//        // Campi precompilati con le informazioni già inserite
//        campoTitolo.setText(libro.getTitolo());
//        campoTitolo.setForeground(Color.BLACK);
//
//        campoAutoreNome.setText(libro.getNomeAutore());
//        campoAutoreNome.setForeground(Color.black);
//        campoAutoreCognome.setText(libro.getCognomeAutore());
//        campoAutoreCognome.setForeground(Color.BLACK);
//
//        campoISBN.setText(libro.getISBN());
//        campoISBN.setForeground(Color.BLACK);
//
//        comboGenere.setSelectedItem(libro.getGenLib().name().replace("_"," "));
//        comboStato.setSelectedItem(libro.getStatLett().name().replace("_"," "));
//        comboValutazione.setSelectedItem(libro.getValPers().name().replace("_"," "));
//
//        // Imposto il controller con comportamento di modifica
//        setControllerModifica(c);
//    }

    public FinestraParametriLibro(Controller c, DefaultTableModel modelloTabella, Map.Entry<Integer, String> toMod) {
        this.c = c;
        this.modelloTabella = modelloTabella;

        Libro libro = Controller.getLibroFromISBN(toMod.getValue()); //isbn
        this.libroDaModificare = libro;
        this.rigaLibroDaModifica = toMod.getKey();

        setTitle("Modifica libro");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        // Parametri libro
        JLabel labelTitolo = new JLabel("Titolo:");
        labelTitolo.setBounds(30, 30, 110, 25);
        campoTitolo = new JTextField();
        campoTitolo.setBounds(140, 30, 210, 25);
        labelTitolo.setForeground(Color.RED);

        JLabel labelAutoreNome = new JLabel("Nome autore:");
        labelAutoreNome.setBounds(30, 70, 110, 25);
        campoAutoreNome = new JTextField();
        campoAutoreNome.setBounds(140, 70, 210, 25);

        JLabel labelAutoreCognome = new JLabel("Cognome autore:");
        labelAutoreCognome.setBounds(30, 110, 110, 25);
        campoAutoreCognome = new JTextField();
        campoAutoreCognome.setBounds(140, 110, 210, 25);
        labelAutoreCognome.setForeground(Color.RED);

        JLabel labelISBN = new JLabel("ISBN:");
        labelISBN.setBounds(30, 150, 150, 25);
        campoISBN = new JTextField();
        campoISBN.setBounds(140, 150, 150, 25);
        labelISBN.setForeground(Color.RED);

        JLabel labelGenere = new JLabel("Genere:");
        labelGenere.setBounds(30, 190, 100, 25);
        String[]opzioniGenere = {"NON SELEZIONATO","BIOGRAFIA","AUTOBIOGRAFIA", "ROMANZO","GIALLO", "THRILLER", "AVVENTURA","AZIONE", "FANTASCIENZA",
                "DISTOPIA", "FANTASY", "HORROR", "ROSA","SCIENTIFICO"};
        comboGenere = new JComboBox<>(opzioniGenere);
        comboGenere.setBounds(140, 190, 200, 25);

        JLabel labelStato = new JLabel("Stato lettura:");
        labelStato.setBounds(30, 230, 100, 25);
        String[] opzioniStato = {"NON SELEZIONATO","DA LEGGERE", "IN LETTURA", "LETTO"};
        comboStato = new JComboBox<>(opzioniStato);
        comboStato.setBounds(140, 230, 200, 25);

        JLabel labelValutazione = new JLabel("Valutazione:");
        labelValutazione.setBounds(30, 270, 100, 25);
        String[] opzioniValutazione = {"NON SELEZIONATO","UNA STELLA", "DUE STELLE", "TRE STELLE", "QUATTRO STELLE", "CINQUE STELLE"};
        comboValutazione = new JComboBox<>(opzioniValutazione);
        comboValutazione.setBounds(140, 270, 200, 25);

        salva = new JButton("Modifica");
        salva.setBounds(175, 360, 100, 30);

        // Aggiunta delle label al frame
        add(labelTitolo);
        add(campoTitolo);
        add(labelAutoreNome);
        add(campoAutoreNome);
        add(labelAutoreCognome);
        add(campoAutoreCognome);
        add(labelISBN);
        add(campoISBN);
        add(labelGenere);
        add(comboGenere);
        add(labelStato);
        add(comboStato);
        add(labelValutazione);
        add(comboValutazione);
        add(salva);

        // Campi precompilati con le informazioni già inserite
        campoTitolo.setText(libro.getTitolo());
        campoTitolo.setForeground(Color.BLACK);

        campoAutoreNome.setText(libro.getNomeAutore());
        campoAutoreNome.setForeground(Color.BLACK);
        campoAutoreCognome.setText(libro.getCognomeAutore());
        campoAutoreCognome.setForeground(Color.BLACK);

        campoISBN.setText(libro.getISBN());
        campoISBN.setForeground(Color.BLACK);

        comboGenere.setSelectedItem(libro.getGenLib().name().replace("_"," "));
        comboStato.setSelectedItem(libro.getStatLett().name().replace("_"," "));
        comboValutazione.setSelectedItem(libro.getValPers().name().replace("_"," "));

        setVisible(true);

        // Imposto il controller con comportamento di modifica
        setControllerModifica(c);
    }


    public void aggiungiSegnapostoObbligatorio(JTextField campo, String testoSegnaposto) {
        campo.setForeground(Color.GRAY);
        campo.setText(testoSegnaposto);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(testoSegnaposto)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(testoSegnaposto);
                }
            }
        });
    }


    //Controller per l'aggiunta del libro
    private void setControllerAggiunta(Controller c){
           salva.addActionListener(e -> Controller.SalvaLibro(campoTitolo, campoAutoreNome, campoAutoreCognome,
                   campoISBN, comboGenere, comboStato,comboValutazione,segnapostoTitolo,this , modelloTabella));
    }

    //Controller per la modifica del libro
    private void setControllerModifica(Controller c){
        salva.addActionListener(e -> Controller.ModificaLibro(libroDaModificare, rigaLibroDaModifica, campoTitolo, campoAutoreNome,
                campoAutoreCognome, campoISBN, comboGenere, comboStato, comboValutazione, this, modelloTabella));
    }


}

