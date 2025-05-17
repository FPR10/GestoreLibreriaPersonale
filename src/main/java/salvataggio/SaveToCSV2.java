package main.java.salvataggio;

import main.java.backend.libro.Libro;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.List;

public class SaveToCSV2 implements SaveFileStrategyIF{
    private static final String titoloCSV = "Titolo, Autore, ISBN, Genere, Valutazione, Stato lettura";

    @Override
    public void salva(List<Libro> libreria, String filePath) {
        try(FileWriter fw = new FileWriter(filePath)){
            Formatter ft = new Formatter(fw);
            ft.format("%-30s%-30s%-30s%-0s%-20s%-20s%n",
                    "Titolo", "Autore", "ISBN", "Genere", "Valutazione", "Stato lettura");
            for (Libro l : libreria){
                ft.format("%20s", l.getTitolo());
                ft.format("%20s",l.getAutore());
                ft.format("%20s",l.getISBN() );
                ft.format("%20s",l.getGenLib().toString());
                ft.format("%20s", l.getValPers().toString());
                ft.format("%20s%n", l.getStatLett().toString());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
