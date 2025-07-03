import main.java.backend.filtro.FiltroGenere;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibriEsempio {

    private static List<Libro> libri = new ArrayList<>();

    public void popolaLibreria(){
        Libro l1 = new Libro.Builder("I promessi sposi", "Manzoni", "9788817046671")
                .setGenereLibri(Genere_Libri.ROMANZO)
                .setStatoLettura(Stato_Lettura.LETTO)
                .build();

        Libro l2 = new Libro.Builder("Fondamenti di Python", "Horstmann", "9788891635433")
                .setStatoLettura(Stato_Lettura.DA_LEGGERE)
                .setGenereLibri(Genere_Libri.SCIENTIFICO)
                .build();

        Libro l3 = new Libro.Builder("Basi di dati", "Atzeni", "9788838656545")
                .setAutoreNome("Paolo")
                .setGenereLibri(Genere_Libri.SCIENTIFICO)
                .setStatoLettura(Stato_Lettura.IN_LETTURA)
                .build();

        Libro l4 = new Libro.Builder("Algoritmi e strutture dati", "Demetrescu", "978883866468")
                .setGenereLibri(Genere_Libri.SCIENTIFICO)
                .setStatoLettura(Stato_Lettura.LETTO)
                .build();

        Libro l5 = new Libro.Builder("Sistemi dinamici", "Benvenuti", "9788838665387")
                .setStatoLettura(Stato_Lettura.IN_LETTURA)
                .setGenereLibri(Genere_Libri.SCIENTIFICO)
                .build();

        Libro l6 = new Libro.Builder("Design Pattern", "Gamma", "9788871921501")
                .setStatoLettura(Stato_Lettura.LETTO)
                .setGenereLibri(Genere_Libri.SCIENTIFICO)
                .build();

        libri.add(l1);
        libri.add(l2);
        libri.add(l3);
        libri.add(l4);
        libri.add(l5);
        libri.add(l6);
    }

    public static List<Libro> getLibri(){
       return libri;
    }

}
