package main.java.backend.ordinamento;

import main.java.backend.filtro.NessunFiltro;
import main.java.backend.libro.Stato_Lettura;

public class OrdinamentoFactory implements OrdinamentoFactoryIF {

    @Override
    public OrdinamentoStrategyIF setStrategy(String tipoOrdinamento) {
        switch(tipoOrdinamento){
            case "Ordina per autore" :
                return new OrdinamentoPerAutore();
            case "Ordina per titolo":
                return new OrdinamentoPerTitolo();
            case "Ordina per valutazione":
                return new OrdinamentoPerValutazione();
            default: return new NessunOrdinamento();
        }
    }
}
