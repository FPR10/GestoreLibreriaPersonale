import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;

import java.util.ArrayList;
import java.util.List;

public class LibriEsempio {

    private static List<Libro> libri = new ArrayList<>();

    public void popolaLibreria(){
        Libro l1 = new Libro.Builder("I promessi sposi", "Manzoni", "9788817046671")
                .setGenereLibri(Genere_Libri.ROMANZO)
                .build();

        Libro l2 = new Libro.Builder("Fondamenti di Python", "Horstmann", "9788891635433")
                .build();

        Libro l3 = new Libro.Builder("Basi di dati", "Atzeni", "9788838656545")
                .setAutoreNome("Paolo")
                .setStatoLettura(Stato_Lettura.IN_LETTURA)
                .build();

        libri.add(l1);
        libri.add(l2);
        libri.add(l3);
    }

    public static List<Libro> getLibri(){
       return libri;
    }

}
