package main.java.backend.salvataggio;
import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;

import java.util.List;

public interface SaveFileStrategyIF {
    void salva(LibreriaSingleton libreria, String filePath);
}
