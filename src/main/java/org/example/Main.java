package main.java.org.example;

import main.java.backend.LibreriaSingleton;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
import main.java.backend.libro.Valutazione_Personale;
import main.java.backend.ordinamento.OrdinamentoPerAutore;
import main.java.backend.ordinamento.OrdinamentoStrategyIF;
import main.java.backend.ricerca.RicercaPerAutore;
import main.java.backend.ricerca.RicercaPerISBN;
import main.java.backend.ricerca.RicercaPerTitolo;
import main.java.backend.ricerca.RicercaStrategyIF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Libro> libreria = new ArrayList<>();


        libreria.add(new Libro.Builder("1984",  "Orwell", "9781234567897")
                .setGenereLibri(Genere_Libri.FANTASCIENZA)
                .setStatoLettura(Stato_Lettura.LETTO)
                .setValutazionePersonale(Valutazione_Personale.STELLE_5)
                .build());

        libreria.add(new Libro.Builder("Divina Commedia", "Alighieri", "9788831234567")
                .setGenereLibri(Genere_Libri.ROMANZO_STORICO)
                .setStatoLettura(Stato_Lettura.DA_LEGGERE)
                .setValutazionePersonale(Valutazione_Personale.STELLE_4)
                .build());

        libreria.add(new Libro.Builder("Il nome della rosa",  "Eco", "9788837654321")
                .setGenereLibri(Genere_Libri.DISTOPIA)
                .setStatoLettura(Stato_Lettura.LETTO)
                .setValutazionePersonale(Valutazione_Personale.STELLE_5)
                .build());

        libreria.add(new Libro.Builder("Harry Potter e la pietra filosofale", "Rowling", "9788888888888")
                .setGenereLibri(Genere_Libri.FANTASY)
                .setStatoLettura(Stato_Lettura.IN_LETTURA)
                .setValutazionePersonale(Valutazione_Personale.STELLE_4)
                .build());

        libreria.add(new Libro.Builder("Il giovane Holden",  "Salinger", "9789999999999")
                .setGenereLibri(Genere_Libri.ROMANDO_FORMAZIONE)
                .setStatoLettura(Stato_Lettura.LETTO)
                .setValutazionePersonale(Valutazione_Personale.STELLE_3)
                .build());


        RicercaStrategyIF rs = new RicercaPerISBN("97888312");
        List <Libro> ret = rs.ricerca(libreria);
        for (Libro elem : ret)
            System.out.println(elem.toString());


        LibreriaSingleton ls = LibreriaSingleton.INSTANCE;
        ls.prova();

    }

}


/*
Prove vecchie:

SaveToCSV stc = new SaveToCSV();
        SaveToJson stj = new SaveToJson();
        stc.salva(libreria,"prova2.csv");
        stj.salva(libreria, "prova1.json");

FiltroStrategyIF fs = new FiltroStatoLettura(Stato_Lettura.IN_LETTURA);
List<Libro> ret = fs.filtra(libreria);
        for(Libro elem : ret){
        System.out.println(elem);
        }


   System.out.println("Inizio");
        Collections.sort(libreria, Comparator.comparing(Libro::getAutore));
        for (Libro elem: libreria){
        System.out.println(elem);
        }
                System.out.println("Fine");


FiltroStrategyIF fs = new FiltroStatoLettura(Stato_Lettura.IN_LETTURA);
        List<Libro> ret = fs.filtra(libreria);
        for (Libro elem : ret){
            System.out.println(elem.toString());
        }
 */

/*
        String autore = "Sal";
        RicercaStrategyIF rs = new RicercaPerAutore(autore);
        List<Libro> ret = rs.ricerca(libreria);
        System.out.println(cleanString(autore));
        for (Libro elem:ret){
            System.out.println(cleanString(elem.getAutore()));
            System.out.println(elem.toString());
        }
         */


        /*
        //Prova di robustezza
        String titolo = "Pietra FIL.oSOF,ALE";
        RicercaStrategyIF rs = new RicercaPerTitolo(titolo);
        List<Libro>ret = rs.ricerca(libreria);
        for (Libro elem : ret){
            System.out.println(elem.toString());
        }
         */