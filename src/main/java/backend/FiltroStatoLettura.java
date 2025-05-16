package main.java.backend;

import java.util.ArrayList;
import java.util.List;

public class FiltroStatoLettura implements FiltroStrategyIF{

    private static Stato_Lettura sl;

    public FiltroStatoLettura (Stato_Lettura sl){
        this.sl = sl;
    }

    @Override
    public List<Libro> filtra(List<Libro> libri) {
        List<Libro> ret = new ArrayList<>();
        for (Libro l : libri){
            if (l.getStatLett().equals(sl))
                ret.add(l);
        }
        return ret;
    }
}
