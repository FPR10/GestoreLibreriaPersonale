package backend;

import java.util.List;

public interface LibreriaIF {
    //Metodi base
    void aggiungiLibro (Libro l);
    void rimuoviLibro (Libro l);
    void modificaLibro (Libro l);

    //Metodi di ricerca
    Libro ricercaPerTitolo (String titolo);
    Libro ricercaPerAutore (String autore);

    //Metodi per filtraggio
    List <Libro> filtraLibri(FiltroStrategy filtro);

    //Metodi per ordinamento
    List <Libro> ordinaLibri (OrdinamentoStrategy ordinamento);

}
