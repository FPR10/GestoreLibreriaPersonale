package main.java.backend.ordinamento;

public interface OrdinamentoFactoryIF {
    OrdinamentoStrategyIF creaOrdinamento(String tipoOrdinamento);

    static String clearString(String oldString){
        return oldString.replace(" ", "_");
    }
}
