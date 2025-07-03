package main.java.backend.filtro;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;

import java.util.ArrayList;
import java.util.List;

public class FiltroStatoLettura implements FiltroStrategyIF {

    private final Stato_Lettura sl;

    public FiltroStatoLettura (Stato_Lettura sl){
        this.sl = sl;
    }

    @Override
    public List<Libro> filtra() {
        LibreriaSingleton l = LibreriaSingleton.INSTANCE;
        List<Libro> ret = new ArrayList<>();
        for (Libro lib : l.getLibreria()){
            if (lib.getStatLett()!=null && lib.getStatLett().equals(sl))
                ret.add(lib);
        }
        return ret;
    }
}
