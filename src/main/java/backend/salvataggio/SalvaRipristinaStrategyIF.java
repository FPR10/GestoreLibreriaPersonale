package main.java.backend.salvataggio;
import main.java.backend.LibreriaSingleton;

public interface SalvaRipristinaStrategyIF {
    void salva(LibreriaSingleton libreria, String filePath);
    void ripristina(LibreriaSingleton libreria, String filePath);
}
