package main.java.backend.ricerca;
import main.java.backend.libro.Libro;
import java.util.ArrayList;
import java.util.List;

public class RicercaPerISBN implements RicercaStrategyIF{

    private final String ISBN;

    public RicercaPerISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public List <Libro> ricerca(List<Libro> libri) {
        List<Libro> ret = new ArrayList<>();
        if (libri.isEmpty()){
            System.err.print("Libreria vuota !");
            return ret;
        }
        for (Libro elem : libri){
            if (cleanString(elem.getISBN()).contains(cleanString(ISBN)))
                ret.add(elem);
        }
        return ret;

    }
}