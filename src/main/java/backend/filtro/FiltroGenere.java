package main.java.backend.filtro;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;

import java.util.ArrayList;
import java.util.List;

public class FiltroGenere implements FiltroStrategyIF {

    private final Genere_Libri genere;

    public FiltroGenere(Genere_Libri genere){
        this.genere = genere;
    }

    @Override
    public List<Libro> filtra() {
        LibreriaSingleton l = LibreriaSingleton.INSTANCE;
        List<Libro> ret = new ArrayList<>();
        for (Libro lib : l.getLibreria()){
            if (lib.getGenLib().equals(genere))
                ret.add(lib);
        }
        return ret;
    }


}
