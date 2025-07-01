package main.java.backend.salvataggio;
import main.java.backend.LibreriaSingleton;

public interface SalvaRipristinaStrategyIF {
    void salva(String filePath);
    void ripristina(String filePath);
}
