package main.java.backend;

import main.java.backend.libro.Libro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public enum LibreriaSingleton implements LibreriaIF{

    //Istanza static final della classe enum
    INSTANCE;

    List<Libro> libreria = new ArrayList<>();


    @Override
    public void aggiungiLibro(Libro l) {
        if(!libreria.contains(l))
            libreria.add(l);
    }

    @Override
    public void rimuoviLibro(Libro l) {
        libreria.remove(l);
    }

    @Override
    public void modificaLibro(Libro l) {
        int indiceLibro = libreria.indexOf(l);
        if (indiceLibro!=-1)//libro trovato
            libreria.set(indiceLibro,l);
    }

    @Override
    public boolean isEmpty() {
        return libreria.isEmpty();
    }

    @Override
    public List<Libro> getLibreria() {
        return new ArrayList<>(libreria);
    }

    @Override
    public void stampaLibreria() {
        for (Libro elem : this)
            System.out.println(elem.toString());
    }

    @Override
    public boolean contains(Libro l) {
        return libreria.contains(l);
    }


    @Override
    public Iterator<Libro> iterator() {
        return libreria.iterator();
    }



}
