package main.java.backend.salvataggio;

import org.apache.commons.io.FilenameUtils;


public class SalvaRipristinaFactory implements SalvaRipristinaFactoryIF{

    @Override
    public SalvaRipristinaStrategyIF setStrategy(String filePath, String estensione) {
        switch(estensione){
            case "csv": return new SalvaCSV();
            default: return new SalvaJSON();

        }
    }
}
