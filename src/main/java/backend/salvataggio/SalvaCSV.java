package main.java.backend.salvataggio;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;

import java.io.FileWriter;
import java.io.IOException;

public class SalvaCSV implements SalvaFileStrategyIF {

    private static final String titoloCSV = "Titolo, Autore, ISBN, Genere, Valutazione, Stato lettura";

    @Override
    public void salva(LibreriaSingleton libreria, String filePath) {
        try(FileWriter fw = new FileWriter(filePath)){
            for (Libro l : libreria){
                fw.append(l.getTitolo()).append(",");
                fw.append(l.getAutore()).append(",");
                fw.append(l.getISBN()).append(",");
                fw.append(l.getGenLib().toString()).append(",");
                fw.append(l.getValPers().toString()).append(",");
                fw.append(l.getStatLett().toString()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
