package main.java.backend.filtro;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Libro;

import java.util.List;

public class NessunFiltro implements FiltroStrategyIF{
    @Override
    public List<Libro> filtra() {
        LibreriaSingleton l = LibreriaSingleton.INSTANCE;
        return l.getLibreria();
    }
}
