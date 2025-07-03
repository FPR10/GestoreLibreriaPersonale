import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;
import main.java.backend.salvataggio.SalvaCSV;
import main.java.backend.salvataggio.SalvaJSON;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SalvataggioTest {

    static LibreriaSingleton libreria;
    static LibriEsempio datiInput;
    static List<Libro> listaLibriEsempio;

    @BeforeAll
    static void inizializza() {
        libreria = LibreriaSingleton.INSTANCE;
        datiInput = new LibriEsempio();
        datiInput.popolaLibreria();
        listaLibriEsempio= datiInput.getLibri();

        for (Libro l : listaLibriEsempio){
            libreria.aggiungiLibro(l);
        }
    }

    @Test
    void salvaRipristinaJson(){
        Libro l = listaLibriEsempio.getLast();

        SalvaJSON salvJson = new SalvaJSON();
        salvJson.salva("libri.json");
        libreria.clear();
        assertEquals(0, libreria.size());
        salvJson.ripristina("libri.json");


        List<Libro> libriRipristinati = libreria.getLibreria();
        assertEquals(6, libriRipristinati.size());
        assertTrue(libriRipristinati.contains(l));
    }

    @Test
    void salvaRipristinaCsv(){
        Libro l = listaLibriEsempio.get(2);

        SalvaCSV salvCSV = new SalvaCSV();
        salvCSV.salva("libri.csv");
        libreria.clear();
        assertEquals(0, libreria.size());
        salvCSV.ripristina("libri.csv");


        List<Libro> libriRipristinati = libreria.getLibreria();
        assertEquals(6, libriRipristinati.size());
        assertTrue(libriRipristinati.contains(l));
    }
    
}
