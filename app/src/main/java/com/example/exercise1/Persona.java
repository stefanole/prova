package com.example.exercise1;

public class Persona {

    private String nome;
    private String email;
    private String nazione;
    private String genere;

    public Persona(String nome, String email, String nazione, String genere) {
        this.nome = nome;
        this.email = email;
        this.nazione = nazione;
        this.genere = genere;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
