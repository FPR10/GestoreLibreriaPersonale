package main.java.backend.ordinamento;
import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;
import java.util.Comparator;
import java.util.List;

public class OrdinamentoPerTitolo implements OrdinamentoStrategyIF {

    @Override
    public List<Libro> ordina(LibreriaSingleton libri) {
        List<Libro>copia = libri.getLibreria();
        copia.sort(Comparator.comparing(Libro::getTitolo));
        return copia;
    }
}
