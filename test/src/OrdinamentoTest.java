import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Valutazione_Personale;
import main.java.backend.ordinamento.OrdinamentoPerAutore;
import main.java.backend.ordinamento.OrdinamentoPerTitolo;
import main.java.backend.ordinamento.OrdinamentoPerValutazione;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdinamentoTest {
    static LibreriaSingleton libreria;

    @BeforeAll
    static void inizializza() {
        libreria = LibreriaSingleton.INSTANCE;
        Libro l1 = new Libro.Builder("Divina Commedia", "Alighieri", "1")
                .setValutazionePersonale(Valutazione_Personale.CINQUE_STELLE).build();
        Libro l2 = new Libro.Builder("Promessi Sposi", "Manzoni", "2")
                .setValutazionePersonale(Valutazione_Personale.DUE_STELLE).build();
        Libro l3 = new Libro.Builder("Basi di dati", "Atzeni", "3")
                .setValutazionePersonale(Valutazione_Personale.TRE_STELLE).build();
        Libro l4 = new Libro.Builder("Algoritmi e strutture dati", "Demetrescu", "4")
                .setValutazionePersonale(Valutazione_Personale.QUATTRO_STELLE).build();
        Libro l5 = new Libro.Builder("Vita nuova", "Alighieri", "5")
                .setValutazionePersonale(Valutazione_Personale.UNA_STELLA).build();
        Libro l6 = new Libro.Builder("Zeta", "Ruffo", "6").build(); // Nessuna valutazione

        libreria.aggiungiLibro(l1);
        libreria.aggiungiLibro(l2);
        libreria.aggiungiLibro(l3);
        libreria.aggiungiLibro(l4);
        libreria.aggiungiLibro(l5);
        libreria.aggiungiLibro(l6);
    }

    /*
    Ordinamento per autore + verifica che due autori vengano ordinati in base all'ordine di inserimento
     */
    @Test
    void testOrdinamentoAutore(){
        OrdinamentoPerAutore ordAut = new OrdinamentoPerAutore();
        List<Libro>ret = ordAut.ordina();
        assertEquals("Alighieri", ret.getFirst().getCognomeAutore());
        assertEquals("Alighieri", ret.get(1).getCognomeAutore());
        assertEquals("Atzeni", ret.get(2).getCognomeAutore());
        assertEquals("Demetrescu", ret.get(3).getCognomeAutore());
        assertEquals("Manzoni", ret.get(4).getCognomeAutore());
        assertEquals(6, libreria.size());

    }

    @Test
    void testOrdinamentoTitolo(){
        OrdinamentoPerTitolo ordTit = new OrdinamentoPerTitolo();
        List<Libro>ret = ordTit.ordina();
        assertEquals("Algoritmi e strutture dati", ret.getFirst().getTitolo());
        assertEquals("Basi di dati", ret.get(1).getTitolo());
        assertEquals("Divina Commedia", ret.get(2).getTitolo());
        assertEquals("Promessi Sposi", ret.get(3).getTitolo());
        assertEquals("Vita nuova", ret.get(4).getTitolo());
        assertEquals(6, libreria.size());
    }

    @Test
    void testOrdinamentoValutazione(){
        OrdinamentoPerValutazione ordVal = new OrdinamentoPerValutazione();
        List<Libro>ret = ordVal.ordina();
        assertEquals("Algoritmi e strutture dati", ret.get(1).getTitolo());
        assertEquals("Basi di dati", ret.get(2).getTitolo());
        assertEquals("Divina Commedia", ret.get(0).getTitolo());
        assertEquals("Promessi Sposi", ret.get(3).getTitolo());
        assertEquals("Vita nuova", ret.get(4).getTitolo());
        assertEquals("Zeta", ret.get(5).getTitolo());  //libro senza valutazione
        assertEquals(6, libreria.size());
    }

    @Test
    void testOrdinaLibreriaVuota(){
        libreria.clear();
        OrdinamentoPerValutazione ordVal = new OrdinamentoPerValutazione();
        List<Libro>ret = ordVal.ordina();
        assertEquals(0,ret.size());
    }


}
