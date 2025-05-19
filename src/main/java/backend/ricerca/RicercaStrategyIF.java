package main.java.backend.ricerca;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;

import java.util.List;

public interface RicercaStrategyIF {
    List<Libro> ricerca (LibreriaSingleton l);

    default String cleanString (String autore){
        //rimuove caratteri speciali, punteggiature e spazi a inizio e fine della parola
        return autore.toLowerCase().replaceAll("[^a-z0-9àèéòùì ]", "").trim();
    }
}
