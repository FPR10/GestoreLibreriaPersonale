package main.java.backend;

import main.java.backend.filtro.FiltroStrategyIF;
import main.java.backend.libro.Libro;
import main.java.backend.ordinamento.OrdinamentoStrategyIF;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Libreria implements LibreriaIF{

    List<Libro> libreria = new LinkedList<>();

    @Override
    public void aggiungiLibro(Libro l) {
        if (!libreria.contains(l))
            libreria.add(l);
        else
            System.err.println("Errore libro già inserito !");
    }


    /*
    Supponiamo che il libro che venga passato come argomento del rimuoviLibro sia sicuramente presente nella libreria
     */
    @Override
    public void rimuoviLibro(Libro l) {
         libreria.remove(l);
    }

    @Override
    public void modificaLibro(Libro l) {

    }

    @Override
    public Libro ricercaPerTitolo(String titolo) {
        return null;
    }

    @Override
    public Libro ricercaPerAutore(String autore) {
        return null;
    }

    @Override
    public List<Libro> filtraLibri(FiltroStrategyIF filtro) {
        return List.of();
    }

    @Override
    public List<Libro> ordinaLibri(OrdinamentoStrategyIF ordinamento) {
        return List.of();
    }


    @Override
    public Iterator<Libro> iterator() {
        return libreria.iterator();
    }
}
