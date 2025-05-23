package main.java.org.example;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
import main.java.backend.libro.Valutazione_Personale;
import main.java.backend.ricerca.*;
import main.java.backend.salvataggio.SalvaJSON;
import main.java.backend.salvataggio.SalvaRipristinaStrategyIF;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibreriaSingleton libreria = LibreriaSingleton.INSTANCE;


        libreria.aggiungiLibro(new Libro.Builder("1984",  "Orwell", "9781234567897")
                .setGenereLibri(Genere_Libri.FANTASCIENZA)
                .setStatoLettura(Stato_Lettura.LETTO)
                .setValutazionePersonale(Valutazione_Personale.CINQUE_STELLE)
                .build());

        libreria.aggiungiLibro(new Libro.Builder("Divina Commedia", "Alighieri", "9788831234567")
                .setGenereLibri(Genere_Libri.ROMANZO)
                .setStatoLettura(Stato_Lettura.DA_LEGGERE)
                .setValutazionePersonale(Valutazione_Personale.DUE_STELLE)
                .build());

        libreria.aggiungiLibro(new Libro.Builder("Il nome della rosa",  "Eco", "9788837654321")
                .setGenereLibri(Genere_Libri.DISTOPIA)
                .setStatoLettura(Stato_Lettura.LETTO)
                .setValutazionePersonale(Valutazione_Personale.UNA_STELLA)
                .build());

        libreria.aggiungiLibro(new Libro.Builder("Harry Potter e la pietra filosofale", "Rowling", "9788888888888")
                .setGenereLibri(Genere_Libri.FANTASY)
                .setStatoLettura(Stato_Lettura.IN_LETTURA)
                .setValutazionePersonale(Valutazione_Personale.TRE_STELLE)
                .build());

        libreria.aggiungiLibro(new Libro.Builder("Il giovane Holden",  "Salinger", "9789999999999")
                .setGenereLibri(Genere_Libri.ROMANZO)
                .setStatoLettura(Stato_Lettura.LETTO)
                .setValutazionePersonale(Valutazione_Personale.CINQUE_STELLE)
                .build());

        libreria.stampaLibreria();
        System.out.println("#############################################################");

        libreria.modificaLibro(new Libro.Builder("Il giovane Holden",  "Salinger", "9789999999999")
                .setGenereLibri(Genere_Libri.ROMANZO)
                .setStatoLettura(Stato_Lettura.NON_SELEZIONATO)
                .setValutazionePersonale(Valutazione_Personale.DUE_STELLE)
                .build());

        libreria.stampaLibreria();

    }

}
