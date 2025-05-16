package backend;

import java.util.Comparator;
import java.util.List;

public class OrdinamentoPerAutore implements OrdinamentoStrategyIF{

    private String autore;

    public OrdinamentoPerAutore (String autore){
        this.autore = autore;
    }

    @Override
    public List<Libro> ordina(List<Libro> libri) {
        libri.sort(Comparator.comparing(Libro::getAutore));
        return libri;
    }
}
