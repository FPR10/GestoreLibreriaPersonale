package main.java.backend;
import main.java.backend.libro.Libro;
import java.util.List;

public interface LibreriaIF extends Iterable<Libro> {

    //Metodi per la gestione dei dati
    void aggiungiLibro (Libro l);
    void rimuoviLibro (Libro l);
    void modificaLibro (Libro l);
    boolean isEmpty();
    List<Libro> getLibreria();

}
