package main.java.backend.ordinamento;

import main.java.backend.libro.Libro;

import java.util.Comparator;
import java.util.List;

public class OrdinamentoPerValutazione implements OrdinamentoStrategyIF {
    @Override
    public List<Libro> ordina(List<Libro> libri) {
        libri.sort(Comparator.comparing(Libro::getValPers).reversed());
        return libri;
    }
}
