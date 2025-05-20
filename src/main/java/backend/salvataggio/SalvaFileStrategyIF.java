package main.java.backend.salvataggio;
import main.java.backend.LibreriaSingleton;

public interface SalvaFileStrategyIF {
    void salva(LibreriaSingleton libreria, String filePath);
}
