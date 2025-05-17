package main.java.backend.ordinamento;

import main.java.backend.libro.Libro;

import java.util.Comparator;
import java.util.List;

public class OrdinamentoPerAutore implements OrdinamentoStrategyIF {


    @Override
    public List<Libro> ordina(List<Libro> libri) {
        libri.sort(Comparator.comparing(Libro::getAutore));
        return libri;
    }
}
