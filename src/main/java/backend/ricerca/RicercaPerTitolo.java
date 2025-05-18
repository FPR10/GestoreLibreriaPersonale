package main.java.backend.ricerca;

import main.java.backend.libro.Libro;

import java.util.List;

public class RicercaPerTitolo implements RicercaStrategyIF{
    @Override
    public List<Libro> ricerca(List<Libro> libri) {
        return List.of();
    }
}
