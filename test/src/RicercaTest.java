import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
import main.java.backend.ricerca.RicercaPerAutore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RicercaTest {

    static LibreriaSingleton libreria;

    @BeforeAll
    static void inizializza() {
        libreria = LibreriaSingleton.INSTANCE;
        Libro l1 = new Libro.Builder("Divina Commedia", " Alighieri", "1")
                .setGenereLibri(Genere_Libri.ROMANZO)
                .setStatoLettura(Stato_Lettura.LETTO)
                .build();

        Libro l2 = new Libro.Builder("Promessi Sposi", "Manzoni", "2")
                .setStatoLettura(Stato_Lettura.DA_LEGGERE)
                .setGenereLibri(Genere_Libri.ROMANZO)
                .build();
        Libro l3 = new Libro.Builder("Basi di dati", "Atzeni", "9788838656545")
                .setAutoreNome("Paolo")
                .setGenereLibri(Genere_Libri.SCIENTIFICO)
                .setStatoLettura(Stato_Lettura.IN_LETTURA)
                .build();

        Libro l4 = new Libro.Builder("Algoritmi e strutture dati", "Demetrescu", "978883866468")
                .setGenereLibri(Genere_Libri.SCIENTIFICO)
                .setStatoLettura(Stato_Lettura.LETTO)
                .build();

        Libro l5 = new Libro.Builder("Sistemi dinamici", "Benvenuti", "9788838665387")
                .setStatoLettura(Stato_Lettura.IN_LETTURA)
                .setGenereLibri(Genere_Libri.SCIENTIFICO)
                .build();

        libreria.aggiungiLibro(l1);
        libreria.aggiungiLibro(l2);
        libreria.aggiungiLibro(l3);
        libreria.aggiungiLibro(l4);
        libreria.aggiungiLibro(l5);
    }

    @Test
    public void testRicercaAutorePresente() {
        RicercaPerAutore ricerca = new RicercaPerAutore("Al");
        List<Libro> risultati = ricerca.ricerca();

        assertEquals(1, risultati.size());
        for (Libro libro : risultati) {
            assertTrue(libro.getAutore().contains("Al"));
        }
    }

    @Test
    public void testRicercaAutoreAssente() {
        RicercaPerAutore ricerca = new RicercaPerAutore("Camilleri");
        List<Libro> risultati = ricerca.ricerca();

        assertTrue(risultati.isEmpty());
    }

    @Test
    public void testRicercaInputMalformato() {
        RicercaPerAutore ricerca = new RicercaPerAutore("e_M_e.t");
        List<Libro> risultati = ricerca.ricerca();

        assertEquals(1, risultati.size());
        assertEquals("Demetrescu", risultati.get(0).getAutore());

        RicercaPerAutore ricerca2 = new RicercaPerAutore("env");
        List<Libro> risultati2 = ricerca2.ricerca();
        assertEquals(1, risultati.size());
        assertEquals("Benvenuti", risultati2.get(0).getAutore());
    }


}

