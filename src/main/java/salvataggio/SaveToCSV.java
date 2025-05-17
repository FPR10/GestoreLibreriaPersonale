package main.java.salvataggio;

import main.java.backend.libro.Libro;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.List;

public class SaveToCSV implements SaveFileStrategyIF{

    @Override
    public void salva(List<Libro> libreria, String filePath) {
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
