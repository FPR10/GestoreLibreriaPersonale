package backend;

import java.util.Objects;

public class Libro {
    private String titolo;
    private String autore;
    private String ISBN;
    private Genere_Libri genLib;
    private Valutazione_Personale valPers;
    private Stato_Lettura statLett;

    //Definizione del Builder come inner class
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
            if (!checkISBN(ISBN)){
                throw new IllegalArgumentException("Formato ISBN non valido");
            }
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

        public Libro build() {
            return new Libro(this);
        }
    }

    private static boolean checkISBN (String isbn){
        if (isbn == null || isbn.length()!=13){
            return false;
        }
        int sum = 0;
        for (int i = 0; i< 12; i++){
            int num = Character.getNumericValue(isbn.charAt(i));
            if (i % 2==0)
                sum += num;//posizioni pari hanno peso 1
            else
                sum+= num*3; //posizioni dispari hanno peso 3
        }
        int checkSum = (10-(sum%10))%10;
        int lastDigit = Character.getNumericValue(isbn.charAt(12));
        return checkSum==lastDigit;
    }

    private Libro (Builder b){
        titolo = b.titolo;
        autore = b.autore;
        ISBN = b.ISBN;
        genLib = b.genLib;
        valPers = b.valPers;
        statLett = b.statLett;
    }

    //Getter e setter
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Libro libro)) return false;
        return Objects.equals(ISBN, libro.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ISBN);
    }


    public static void main(String[] args) {
        Libro b = new Libro.Builder("1984","Orwell","1311452")
                .setGenereLibri(Genere_Libri.AVVENTURA_AZIONE)
                .setValutazionePersonale(Valutazione_Personale.STELLE_4)
                .setStatoLettura(Stato_Lettura.DA_LEGGERE).build();

        Libro bb = new Libro.Builder("1984","Orwell","1311452")
                .setGenereLibri(Genere_Libri.AVVENTURA_AZIONE)
                .setValutazionePersonale(Valutazione_Personale.STELLE_4)
                .setStatoLettura(Stato_Lettura.DA_LEGGERE).build();
        Libro b2 = new Libro.Builder("1984","Orwell","1311452").build();

        System.out.println(b.toString());
        System.out.println(bb.toString());
        System.out.println(b.toString());

        System.out.println(b.equals(bb));
    }
}
