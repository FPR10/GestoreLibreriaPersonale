package main.java.backend.ordinamento;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrdinamentoPerValutazione implements OrdinamentoStrategyIF {

    @Override
    public List<Libro> ordina() {
        LibreriaSingleton libri = LibreriaSingleton.INSTANCE;
        List<Libro> copia = libri.getLibreria();

        //Per gestire i libri eventualmente senza valutazione
        List<Libro> libriConVal = new ArrayList<>();
        List<Libro> libriSenzaVal = new ArrayList<>();

        for (Libro l : copia) {
            if (l.getValPers() == null) {
                libriSenzaVal.add(l);
            } else {
                libriConVal.add(l);
            }
        }
        //Ordinamento dei libri con valutazione
        libriConVal.sort(Comparator.comparing(Libro::getValPers).reversed());

        //Aggiunta dei libri senza valutazione in fondo
        libriConVal.addAll(libriSenzaVal);

        return libriConVal;
    }

}

