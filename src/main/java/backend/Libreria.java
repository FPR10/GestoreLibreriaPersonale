package main.java.backend;

import main.java.backend.filtro.FiltroStrategyIF;
import main.java.backend.libro.Libro;
import main.java.backend.ordinamento.OrdinamentoStrategyIF;

import java.util.List;

public class Libreria implements LibreriaIF {

    @Override
    public void aggiungiLibro(Libro l) {

    }

    @Override
    public void rimuoviLibro(Libro l) {

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
}
