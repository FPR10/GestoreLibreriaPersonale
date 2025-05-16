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

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public String getISBN() {
        return ISBN;
    }

    public Genere_Libri getGenLib() {
        return genLib;
    }

    public Valutazione_Personale getValPers() {
        return valPers;
    }

    public Stato_Lettura getStatLett() {
        return statLett;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setGenLib(Genere_Libri genLib) {
        this.genLib = genLib;
    }

    public void setValPers(Valutazione_Personale valPers) {
        this.valPers = valPers;
    }

    public void setStatLett(Stato_Lettura statLett) {
        this.statLett = statLett;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", genere=" + genLib +
                ", valutazione=" + valPers +
                ", stato lettura=" + statLett +
                '}';
    }

    public static void main(String[] args) {
        Libro b = new Libro.Builder("1984","Orwell","1311452")
                .setGenereLibri(Genere_Libri.AVVENTURA_AZIONE)
                .setValutazionePersonale(Valutazione_Personale.STELLE_4)
                .setStatoLettura(Stato_Lettura.DA_LEGGERE).build();

        Libro b2 = new Libro.Builder("1984","Orwell","1311452").build();


        System.out.println(b.toString());
        System.out.println(b2.toString());
    }
}
