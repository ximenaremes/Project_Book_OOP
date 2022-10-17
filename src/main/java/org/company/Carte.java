package org.company;

public class Carte {
    private  String titlu;
    private  String autor;
    private String numarDePagini;
    private String anulPublicarii;
    private String LoculPublicarii;

    public Carte() {
        this.titlu = titlu;
        this.autor = autor;
        this.numarDePagini = numarDePagini;
        this.anulPublicarii= anulPublicarii;
        this.LoculPublicarii = LoculPublicarii;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNumarDePagini() {
        return numarDePagini;
    }

    public void setNumarDePagini(String numarDePagini) {
        this.numarDePagini = numarDePagini;
    }

    public String getAnulPublicarii() {
        return anulPublicarii;
    }

    public void setAnulPublicarii(String anulPublicarii) {
        this.anulPublicarii = anulPublicarii;
    }

    public String getLoculPublicarii() {
        return LoculPublicarii;
    }

    public void setLoculPublicarii(String loculPublicarii) {
        LoculPublicarii = loculPublicarii;
    }



}


