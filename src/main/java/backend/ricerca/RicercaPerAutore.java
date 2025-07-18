package main.java.backend.ricerca;
import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;
import java.util.ArrayList;
import java.util.List;


public class RicercaPerAutore implements RicercaStrategyIF{

    private final String autore;

    public RicercaPerAutore(String autore) {
        this.autore = autore;
    }

    @Override
    public List<Libro> ricerca() {
        LibreriaSingleton libri = LibreriaSingleton.INSTANCE;
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


}
