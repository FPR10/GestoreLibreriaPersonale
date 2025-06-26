package main.java.backend.salvataggio;

public interface SalvaRipristinaFactoryIF {
    SalvaRipristinaStrategyIF scelta (String filePath, String estensione);
}
