package backend;

public class Libro {
    private String titolo;
    private String autore;
    private String ISBN;
    private Genere_Libri genLib;
    private Valutazione_Personale valPers;
    private Stato_Lettura statLett;


    public static class Builder{
        //Parametri obbligatori
        private String titolo;
        private String autore;
        private String ISBN;

        //Opzionali
        private Genere_Libri genLib;
        private Stato_Lettura statLett;
        private Valutazione_Personale valPers;

        public Builder (String titolo, String autore, String ISBN){
            this.titolo = titolo;
            this.autore = autore;
            this.ISBN = ISBN;
        }

        public Builder setGenereLibri (Genere_Libri gl){
            genLib=gl;
            return this;
        }

        public Builder setStatoLettura (Stato_Lettura sl){
            statLett = sl;
            return this;
        }

        public Builder setValutazionePersonale (Valutazione_Personale vp){
            valPers = vp;
            return this;
        }

        public Libro build(){
            return new Libro(this);
        }
    }

    private Libro (Builder b){
        titolo = b.titolo;
        autore = b.autore;
        ISBN = b.ISBN;
        genLib = b.genLib;
        valPers = b.valPers;
        statLett = b.statLett;
    }
}
