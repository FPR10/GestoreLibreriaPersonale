package main.java.backend.filtro;

import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Stato_Lettura;

public class FiltroFactory implements FiltroFactoryIF{

    @Override
    public FiltroStrategyIF creaFiltro(String tipoFiltro) {
           switch (tipoFiltro){
               case "Filtra stato lettura" : {
                   Stato_Lettura stato = Stato_Lettura.valueOf(FiltroFactoryIF.clearString(tipoFiltro));
                   return new FiltroStatoLettura(stato);
               }
               case "Filtra per genere":{
                   Genere_Libri genere = Genere_Libri.valueOf(FiltroFactoryIF.clearString(tipoFiltro));
                   return new FiltroGenere(genere);
               }
               default: return new NessunFiltro();

           }
    }

}
