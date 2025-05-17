package main.java.backend.filtro;
import main.java.backend.libro.Libro;

import java.util.List;

public interface FiltroStrategyIF {
    List<Libro> filtra (List<Libro> libri);
}
