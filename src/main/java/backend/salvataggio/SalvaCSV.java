package main.java.backend.salvataggio;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
import main.java.backend.libro.Valutazione_Personale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SalvaCSV implements SalvaRipristinaStrategyIF {

    private static final String titoloCSV = "Titolo, Autore, ISBN, Genere, Valutazione, Stato lettura";

    @Override
    public void salva(LibreriaSingleton libreria, String filePath) {
        try(FileWriter fw = new FileWriter(filePath)){
            for (Libro l : libreria.getLibreria()){
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

    @Override
    public void ripristina(LibreriaSingleton libreria, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String riga;
            while ((riga = reader.readLine()) != null) {
                String[] campi = riga.split(",");

                Libro libro = new Libro.Builder(campi[0], campi[1], campi[2])
                        .setGenereLibri(Genere_Libri.valueOf(campi[3]))
                        .setValutazionePersonale(Valutazione_Personale.valueOf(campi[4]))
                        .setStatoLettura(Stato_Lettura.valueOf(campi[5]))
                        .build();
                libreria.aggiungiLibro(libro);
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Errore durante il ripristino CSV: " + e.getMessage());
        }
    }

}

