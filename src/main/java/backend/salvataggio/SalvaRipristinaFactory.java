package main.java.backend.salvataggio;

import org.apache.commons.io.FilenameUtils;


public class SalvaRipristinaFactory implements SalvaRipristinaFactoryIF{

    @Override
    public SalvaRipristinaStrategyIF scelta(String filePath) {
        String estensione = FilenameUtils.getExtension(filePath);
        switch(estensione){
            case "csv": return new SalvaCSV();
            default: return new SalvaJSON();

        }
    }
}
