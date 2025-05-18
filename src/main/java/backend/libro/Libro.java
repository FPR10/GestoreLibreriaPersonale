package main.java.backend.libro;

import java.util.Objects;

public class Libro {
    private String titolo;
    private String autoreNome;
    private String autoreCognome;
    private String ISBN;
    private Genere_Libri genLib;
    private Valutazione_Personale valPers;
    private Stato_Lettura statLett;

    //Definizione del Builder come inner class
    public static class Builder{
        //Parametri obbligatori
        private String titolo;
        private String autoreNome;
        private String autoreCognome;
        private String ISBN;

        //Opzionali
        private Genere_Libri genLib;
        private Stato_Lettura statLett;
        private Valutazione_Personale valPers;

        public Builder (String titolo, String autoreNome,String autoreCognome, String ISBN){
            //if (!checkISBN(ISBN)){
            //    throw new IllegalArgumentException("Formato ISBN non valido");
            //}
            this.titolo = titolo;
            this.autoreNome = autoreNome;
            this.autoreCognome = autoreCognome;
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
        autoreNome = b.autoreNome;
        autoreCognome = b.autoreCognome;
        ISBN = b.ISBN;
        genLib = b.genLib;
        valPers = b.valPers;
        statLett = b.statLett;
    }

    //Getter e setter
    public String getTitolo() {
        return titolo;
    }

    public String getNomeAutore() {
        return autoreNome;
    }

    public String getCognomeAutore() {
        return autoreCognome;
    }

    public String getAutore(){
        return autoreNome + " " + autoreCognome;
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

    public void setAutoreNome(String nomeAutore) {
        this.autoreNome = nomeAutore;
    }

    public void setAutoreCognome(String cognomeAutore) {
        this.autoreNome = cognomeAutore;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Libro{");
        sb.append("titolo='").append(titolo).append('\'');
        sb.append(", autore='").append(autoreNome).append(" ").append(autoreCognome).append('\'');
        sb.append(", ISBN='").append(ISBN).append('\'');
        sb.append(", genere=").append(genLib);
        sb.append(", val. personale=").append(valPers);
        sb.append(", stato lettura=").append(statLett);
        sb.append('}');
        return sb.toString();
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


}
