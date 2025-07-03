import main.java.backend.LibreriaSingleton;
import main.java.backend.filtro.FiltroGenere;
import main.java.backend.filtro.FiltroStatoLettura;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiltroTest {

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

    /**
    Test FiltroGenere
     */
    @Test
    void testFiltroGenere(){
        FiltroGenere fg = new FiltroGenere(Genere_Libri.ROMANZO);
        List<Libro> res = fg.filtra();
        assertEquals(1, res.size());
    }

    @Test
    void testNullFiltroGenere(){
        FiltroGenere fg = new FiltroGenere(null);
        List<Libro> res = fg.filtra();
        assertEquals(0, res.size());
    }


    /**
     Test FiltroStatoLettura
     */
    @Test
    void testFiltroStatLett(){
        FiltroStatoLettura fs = new FiltroStatoLettura(Stato_Lettura.LETTO);
        List<Libro> res = fs.filtra();
        assertEquals(3, res.size());
    }

    @Test
    void testNullFiltroStatLett(){
        FiltroStatoLettura fs = new FiltroStatoLettura(null);
        List<Libro> res = fs.filtra();
        assertEquals(0, res.size());
    }

    //Test usando un libro senza genere e senza stato lettura
    @AfterAll
    static void testFiltraLibroValNull() {
        libreria.clear();
        Libro l = new Libro.Builder("Divina Commedia", "Dante Alighieri", "1234").build();
        libreria.aggiungiLibro(l);

        FiltroGenere fg = new FiltroGenere(Genere_Libri.ROMANZO);
        List<Libro> resGenere = fg.filtra();
        assertEquals(0,resGenere.size());

        FiltroStatoLettura fs = new FiltroStatoLettura(Stato_Lettura.LETTO);
        List<Libro> resStatLett = fs.filtra();
        assertEquals(0,resStatLett.size());
    }

}
