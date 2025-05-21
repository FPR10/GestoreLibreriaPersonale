package main.java.backend.filtro;

public interface FiltroFactoryIF {
    FiltroStrategyIF creaFiltro(String tipoFiltro);

    static String clearString(String oldString){
        return oldString.replace(" ", "_");
    }
}
