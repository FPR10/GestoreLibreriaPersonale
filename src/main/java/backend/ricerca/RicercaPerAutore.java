package main.java.backend.ricerca;
import main.java.backend.libro.Libro;
import java.util.ArrayList;
import java.util.List;


public class RicercaPerAutore implements RicercaStrategyIF{

    private final String autore;

    public RicercaPerAutore(String autore) {
        this.autore = autore;
    }

    @Override
    public List<Libro> ricerca(List<Libro> libri) {
        List<Libro>ret = new ArrayList<>();
        if (libri.isEmpty()){
            System.err.print("La libreria è vuota !");
        }
        for (Libro elem : libri){
            if (cleanString(elem.getAutore()).contains(cleanString(autore))){
                ret.add(elem);
            }

        }
        return ret;
    }

    private String cleanString (String autore){
        //rimuove caratteri speciali, punteggiature e spazi a inizio e fine della parola
        return autore.toLowerCase().replaceAll("[^a-z0-9àèéòùì ]", "").trim();
    }
}
