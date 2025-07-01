package main.java.backend.salvataggio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SalvaJSON implements SalvaRipristinaStrategyIF {

    @Override
    public void salva(LibreriaSingleton libreria, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(libreria.getLibreria(), writer);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio JSON: " + e.getMessage());
        }
    }

    @Override
    public void ripristina(LibreriaSingleton libreria, String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Libro>>(){}.getType();
            List<Libro> libri = gson.fromJson(reader, listType);
            for (Libro l : libri) {
                libreria.aggiungiLibro(l);
            }
        } catch (IOException e) {
            System.err.println("Errore durante il ripristino JSON: " + e.getMessage());
        }
    }

}
