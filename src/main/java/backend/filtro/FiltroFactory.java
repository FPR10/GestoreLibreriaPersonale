package main.java.backend.filtro;

import main.java.backend.libro.Genere_Libri;
import main.java.backend.libro.Stato_Lettura;

public class FiltroFactory implements FiltroFactoryIF{

    @Override
    public FiltroStrategyIF setStrategy(String paramFiltro, String tipoFiltro) {
        switch(tipoFiltro){
            case "genere": return new FiltroGenere(Genere_Libri.valueOf(paramFiltro));
            case "stato lettura": return new FiltroStatoLettura(Stato_Lettura.valueOf(paramFiltro.replace(" ","_")));
            default: return new NessunFiltro();
        }
    }
}
