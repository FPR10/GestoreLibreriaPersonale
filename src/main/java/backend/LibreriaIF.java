package main.java.backend;

import main.java.backend.filtro.FiltroStrategyIF;
import main.java.backend.libro.Libro;
import main.java.backend.ordinamento.OrdinamentoStrategyIF;

import java.util.List;

public interface LibreriaIF extends Iterable<Libro> {
    //Metodi base
    void aggiungiLibro (Libro l);
    void rimuoviLibro (Libro l);
    void modificaLibro (Libro l);

}
