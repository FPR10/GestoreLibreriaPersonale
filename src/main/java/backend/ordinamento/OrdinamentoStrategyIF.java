package main.java.backend.ordinamento;

import main.java.backend.libro.Libro;

import java.util.List;

public interface OrdinamentoStrategyIF {
    List<Libro> ordina (List<Libro> libri);
}
