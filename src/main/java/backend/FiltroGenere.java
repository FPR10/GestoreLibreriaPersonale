package backend;

import java.util.ArrayList;
import java.util.List;

public class FiltroGenere implements FiltroStrategyIF{

    private static Genere_Libri genere;

    public FiltroGenere(Genere_Libri genere){
        this.genere = genere;
    }

    @Override
    public List<Libro> filtra(List<Libro> libri) {
        List<Libro> ret = new ArrayList<>();
        for (Libro l : libri){
            if (l.getGenLib().equals(genere))
                ret.add(l);
        }
        return ret;
    }
}
