package main.java.salvataggio;
import main.java.backend.Libro;

import java.util.List;

public interface SaveFileStrategyIF {
    void salva(List<Libro> libreria, String filePath);
}
