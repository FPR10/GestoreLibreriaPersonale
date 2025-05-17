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

    //Metodi di ricerca
    Libro ricercaPerTitolo (String titolo);
    Libro ricercaPerAutore (String autore);

    //Metodi per filtraggio
    List <Libro> filtraLibri(FiltroStrategyIF filtro);

    //Metodi per ordinamento
    List <Libro> ordinaLibri (OrdinamentoStrategyIF ordinamento);

}
