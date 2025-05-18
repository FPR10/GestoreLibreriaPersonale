package main.java.backend.ricerca;

import main.java.backend.libro.Libro;

import java.util.List;

public interface RicercaStrategyIF {
    List<Libro> ricerca (List<Libro>libri);
}
