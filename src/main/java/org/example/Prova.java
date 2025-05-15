package org.example;

public class Prova {

    private String nome;
    private String ISBN;
    private String autore;
    private String annoPubblicazione;
    private StatoLettura sl;


    public static void main (String[]args){
            Prova p1 = new Prova("Divina Commedia", "1310101013 ", StatoLettura.DA_LEGGERE);
            System.out.println(p1.toString());
    }

    public Prova (String nome, String ISBN,StatoLettura stato){
        this.nome = nome;
        this.ISBN = ISBN;
        this.sl = stato;
    }

    @Override
    public String toString() {
        return "Prova{" +
                "nome='" + nome + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", autore='" + autore + '\'' +
                ", annoPubblicazione='" + annoPubblicazione + '\'' +
                ", sl=" + sl +
                '}';
    }
}
