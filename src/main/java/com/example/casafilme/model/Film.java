package com.example.casafilme.model;

public class Film {
    public enum Tip {
        ARTISTIC,
        SERIAL
    }

    public enum Categorie {
        DRAMA,
        COMEDIE,
        ACTIUNE,
        SF,
        FANTASY,
        HORROR,
        DOCUMENTAR,
        ROMANTIC,
        THRILLER,
        ANIMATIE
    }
    private Integer id;
    private String titlu;
    private Tip tip;
    private Integer an;
    private Categorie categorie;
    private String regizor;
    private Integer durata;
    private String image;

    public Film(Integer id,String titlu, Tip tip, Categorie categorie, int an, String regizor, int durata) {
        this.id=id;
        this.titlu = titlu;
        this.tip = tip;
        this.categorie = categorie;
        this.an = an;
        this.regizor=regizor;
        this.durata=durata;
    }

    public Film(String titlu, Tip tip, Categorie categorie, int an, String regizor, int durata) {
        this.titlu = titlu;
        this.tip = tip;
        this.categorie = categorie;
        this.an = an;
        this.regizor = regizor;
        this.durata = durata;
    }

    public Film(Integer id,String titlu, Tip tip, Categorie categorie, int an, String regizor, int durata,String image) {
        this.id=id;
        this.titlu = titlu;
        this.tip = tip;
        this.categorie = categorie;
        this.an = an;
        this.regizor = regizor;
        this.durata = durata;
        this.image=image;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setRegizor(String regizor) {
        this.regizor = regizor;
    }

    public Integer getAn() {
        return an;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Tip getTip() {
        return tip;
    }

    public void setAn(Integer an) {
        this.an = an;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegizor() {
        return regizor;
    }

    public Integer getDurata() {
        return durata;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
