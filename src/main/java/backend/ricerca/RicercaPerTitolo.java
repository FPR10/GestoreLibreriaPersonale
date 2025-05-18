package main.java.backend.ricerca;

import main.java.backend.libro.Libro;

import java.util.List;

public class RicercaPerTitolo implements RicercaStrategyIF{
    @Override
    public List<Libro> ricerca(List<Libro> libri) {
        List<Libro> ret = new ArrayList<>();
         if (libri.isEmpty()){
             System.err.print("Libreria vuota !");
         }
         for (Libro elem : libri){
             if (elem.getTitolo().equalsIgnoreCase(titolo))
                 ret.add(elem);
         }
         return ret;
    }
}
