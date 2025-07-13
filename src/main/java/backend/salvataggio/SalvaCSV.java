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

    @Override
    public void salva(String filePath) {
        LibreriaSingleton libreria = LibreriaSingleton.INSTANCE;
        if (filePath==null){
            throw new IllegalArgumentException("File path scorretto");
        }
        try(FileWriter fw = new FileWriter(filePath)){
            for (Libro l : libreria.getLibreria()){
                fw.append(l.getTitolo()).append(",");
                fw.append(l.getNomeAutore()!= null ? l.getNomeAutore() : " ").append(",");
                fw.append(l.getCognomeAutore()).append(",");
                fw.append(l.getISBN()).append(",");

                //Append + gestione di eventuali null
                fw.append(l.getGenLib()!= null ? l.getGenLib().toString() : " ").append(",");
                fw.append(l.getValPers()!= null ? l.getValPers().toString() : " ").append(",");
                fw.append(l.getStatLett()!= null ? l.getStatLett().toString() : " ").append("\n");
            }
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio CSV: " + e.getMessage());
        }
    }

    @Override
    public void ripristina(String filePath) {
        LibreriaSingleton libreria = LibreriaSingleton.INSTANCE;
        if (filePath==null){
            throw new IllegalArgumentException("File path scorretto");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String riga;
            while ((riga = reader.readLine()) != null) {
                String[] campi = riga.split(",", -1);

                Libro.Builder toBuild = new Libro.Builder(campi[0], campi[2], campi[3]);

                //Ripristino e check di eventuali valori null
                if(!campi[1].isBlank()){
                    toBuild.setAutoreNome(campi[1]);
                }
                if(!campi[4].isBlank()){
                    toBuild.setGenereLibri(Genere_Libri.valueOf(campi[4]));
                }
                if(!campi[5].isBlank()){
                    toBuild.setValutazionePersonale(Valutazione_Personale.valueOf(campi[5]));
                }
                if(!campi[6].isBlank()){
                    toBuild.setStatoLettura(Stato_Lettura.valueOf(campi[6]));
                }

                Libro l = toBuild.build();
                libreria.aggiungiLibro(l);
            }
        } catch (IOException e) {
            System.err.println("Errore durante il ripristino CSV: " + e.getMessage());
        }
    }

}

