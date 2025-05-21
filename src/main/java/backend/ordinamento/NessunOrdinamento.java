package main.java.backend.ordinamento;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;

import java.util.List;

public class NessunOrdinamento implements OrdinamentoStrategyIF{
    @Override
    public List<Libro> ordina(LibreriaSingleton l) {
        return l.getLibreria();
    }
}
