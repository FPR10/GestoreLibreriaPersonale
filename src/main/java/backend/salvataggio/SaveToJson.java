package main.java.backend.salvataggio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToJson implements SaveFileStrategyIF{

    @Override
    public void salva(LibreriaSingleton libreria, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(libreria, writer);
        } catch (IOException e) {
            System.err.println("Errore durante l'esportazione: " + e.getMessage());
        }
    }

}
