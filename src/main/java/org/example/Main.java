package main.java.org.example;

import main.java.backend.filtro.FiltroStatoLettura;
import main.java.backend.filtro.FiltroStrategyIF;
import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Libro;
import main.java.backend.libro.Stato_Lettura;
import main.java.backend.libro.Valutazione_Personale;
import main.java.salvataggio.SaveToCSV;
import main.java.salvataggio.SaveToJson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Libro> libreria = new ArrayList<>();

        libreria.add(new Libro.Builder("1984","Orwell","9788838656545")
                .setGenereLibri(Genere_Libri.AVVENTURA_AZIONE)
                .setStatoLettura(Stato_Lettura.DA_LEGGERE)
                .setValutazionePersonale(Valutazione_Personale.STELLE_4)
                .build());

        libreria.add(new Libro.Builder("Divina Commedia","Alighieri","9788838656545")
                .setGenereLibri(Genere_Libri.BIOGRAFIA_AUTOBIOGRAFIA)
                .setStatoLettura(Stato_Lettura.LETTO)
                .setValutazionePersonale(Valutazione_Personale.STELLA_1)
                .build());

        libreria.add(new Libro.Builder("Ferrari","Enzo","9788838656545")
                .setGenereLibri(Genere_Libri.BIOGRAFIA_AUTOBIOGRAFIA)
                .setStatoLettura(Stato_Lettura.IN_LETTURA)
                .setValutazionePersonale(Valutazione_Personale.STELLE_5)
                .build());

        libreria.sort((Comparator.comparing(Libro::getValPers)).reversed());
        for (Libro elem : libreria)
            //System.out.println("Nome: " + elem.getTitolo() + " - valutazione " + elem.getValPers());
            System.out.println(elem.getGenLib().toString());

        SaveToCSV stc = new SaveToCSV();
        SaveToJson stj = new SaveToJson();
        stc.salva(libreria,"prova2.csv");
        stj.salva(libreria, "prova1.json");



    }
}


/*
Prove vecchie:
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
 */