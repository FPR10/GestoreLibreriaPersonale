package main.java.backend;

import main.java.backend.libro.Libro;

import java.util.Iterator;

public enum LibreriaSingleton implements LibreriaIF{
    ;

    @Override
    public void aggiungiLibro(Libro l) {

    }

    @Override
    public void rimuoviLibro(Libro l) {

    }

    @Override
    public void modificaLibro(Libro l) {

    }

    @Override
    public Iterator<Libro> iterator() {
        return null;
    }
}
