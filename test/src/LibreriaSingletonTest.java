import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibreriaSingletonTest {

    static LibreriaSingleton libreria;
    static LibriEsempio datiInput;
    static List<Libro> listaLibriEsempio;

    @BeforeAll
    static void inizializza() {
        libreria = LibreriaSingleton.INSTANCE;
        datiInput = new LibriEsempio();
        datiInput.popolaLibreria();
        listaLibriEsempio= datiInput.getLibri();
    }


    /**
    Due istanze del Singleton devono essere uguali
     */
    @Test
    void testIstance(){
        LibreriaSingleton ist1 = LibreriaSingleton.INSTANCE;
        LibreriaSingleton ist2 = LibreriaSingleton.INSTANCE;
        assertSame(ist1,ist2);
    }

    @BeforeAll
    static void testLibreriaVuota(){
        assertFalse(libreria.contains(listaLibriEsempio.getFirst()));
    }


    @Test
    void testInserimento(){
        for (Libro l : listaLibriEsempio){
            libreria.aggiungiLibro(l);
        }
        assertTrue(libreria.contains(listaLibriEsempio.getFirst()));
        assertTrue(libreria.contains(listaLibriEsempio.getLast()));
        assertEquals(libreria.size(), listaLibriEsempio.size());
    }

    @Test
    void testInserimentoNull(){
        assertThrows(IllegalArgumentException.class, ()-> libreria.aggiungiLibro(null));
    }

    @Test
    void testModificaNull(){
        assertThrows(IllegalArgumentException.class, ()-> libreria.modificaLibro( null));
    }

    @Test
    void testModificaLibro(){
        Libro vecchio = new Libro.Builder("Prova 1", "Ruffo", "9788817046671")
                .setGenereLibri(Genere_Libri.ROMANZO)
                .build();

        libreria.aggiungiLibro(vecchio);

        Libro libroMod = new Libro.Builder("LibroMod", "Russo", "9788817046671")
                .setGenereLibri(Genere_Libri.ROMANZO)
                .build();

        libreria.modificaLibro(libroMod);


        int index = libreria.getLibreria().indexOf(libroMod);
        Libro l = libreria.getLibreria().get(index);
        assertTrue(libreria.contains(libroMod));
        assertEquals("LibroMod", l.getTitolo());
        assertNotEquals("Ruffo", l.getCognomeAutore());

    }

    /**
     * Modifiche su una copia non cambiano LibreriaSingleton
     */
    @Test
    void testGetCopiaLibreria(){
        Libro l = listaLibriEsempio.getFirst();
        libreria.aggiungiLibro(l);

        List<Libro> libri = libreria.getLibreria();
        libri.remove(l);
        assertFalse(libri.contains(l));
        assertTrue(libreria.contains(l));
    }
}
