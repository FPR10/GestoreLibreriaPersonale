package main.java.backend;

import main.java.backend.libro.Libro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum LibreriaSingleton implements LibreriaIF{

    INSTANCE;

    List<Libro> libreria = new ArrayList<>();

    @Override
    public void aggiungiLibro(Libro l) {
        if (l==null){
            throw new IllegalArgumentException("Libro da inserire non può essere null");
        }
        if(!libreria.contains(l))
            libreria.add(l);
    }

    @Override
    public void rimuoviLibro(Libro l) {
        if (l==null){
            throw new IllegalArgumentException("Libro da rimuovere non può essere null");
        }
        libreria.remove(l);
    }

    @Override
    public void modificaLibro(Libro l) {
        if (l==null){
            throw new IllegalArgumentException("Libro da modificare non può essere null");
        }
        int indiceLibro = libreria.indexOf(l);
        if (indiceLibro!=-1) {//libro trovato
            libreria.set(indiceLibro, l);
        }
        else{
            throw new IllegalArgumentException("Il libro non è presente nella libreria");
        }
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

    @Override
    public int size(){
        return libreria.size();
    }

    public void clear(){
        libreria.clear();
    }
}
