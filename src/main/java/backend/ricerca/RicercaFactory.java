package main.java.backend.ricerca;

public class RicercaFactory implements RicercaFactoryIF{
    @Override
    public RicercaStrategyIF ricerca(String toSearch, String tipoRicerca) {
        switch (tipoRicerca){
            case "Per autore" : return new RicercaPerAutore(toSearch);
            case "Per ISBN" : return new RicercaPerISBN(toSearch);
            default: return new RicercaPerTitolo(toSearch);
        }
    }
}
