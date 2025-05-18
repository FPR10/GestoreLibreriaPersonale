package main.java.backend.ricerca;

import main.java.backend.libro.Libro;

import java.util.List;

public class RicercaPerAutore implements RicercaStrategyIF{

    @Override
    public List<Libro> ricerca(List<Libro> libri) {
        List<Libro>ret = new ArrayList<>();
        if (libri.isEmpty()){
            System.err.print("La libreria è vuota !");
        }
        for (Libro elem : libri){
            if (elem.getAutore().equalsIgnoreCase(autore)){
                ret.add(elem);
            }
        }
    }
}
