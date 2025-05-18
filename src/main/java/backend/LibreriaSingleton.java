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
    public Iterator<Libro> iterator() {
        return libreria.iterator();
    }




    public void prova(){
        System.out.println("Ok");
    }


    //Come chiamare il Singleton
    public static void main(String[] args) {
        LibreriaSingleton ls = LibreriaSingleton.INSTANCE;
        ls.prova();
        LibreriaSingleton ls2 = LibreriaSingleton.INSTANCE;
        ls2.prova();
        System.out.println(ls==ls2); //true perchè fanno riferimento allo stesso oggetto in memoria
    }
}
