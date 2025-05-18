package main.java.backend.ricerca;
import main.java.backend.libro.Libro;
import java.util.ArrayList;
import java.util.List;

public class RicercaPerTitolo implements RicercaStrategyIF{

    private final String titolo;

    public RicercaPerTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public List<Libro> ricerca(List<Libro> libri) {
        List<Libro> ret = new ArrayList<>();
         if (libri.isEmpty()){
             System.err.print("Libreria vuota !");
             return ret;
         }
         for (Libro elem : libri){
             if (cleanString(elem.getTitolo()).contains(cleanString(titolo)))
                 ret.add(elem);
         }
         return ret;
    }
}
