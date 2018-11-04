package com.example.jandi.bolaapp;

public class modelBola {
    private String id;
    private String nama;
    private String gambar1;
    private String poin1;
    private String gambar2;
    private String poin2;
    private String link;

    public modelBola(String id, String nama, String poin1, String gambar1, String poin2, String gambar2, String link) {
        this.id = id;
        this.nama = nama;
        this.poin1 = poin1;
        this.gambar1 = gambar1;
        this.poin2 = poin2;
        this.gambar2 = gambar2;
        this.link = link;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar1() {
        return gambar1;
    }

    public void setGambar1(String gambar1) {
        this.gambar1 = gambar1;
    }

    public String getGambar2() {
        return gambar2;
    }

    public void setGambar2(String gambar2) {
        this.gambar2 = gambar2;
    }
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }
    public String getPoin1() {
        return poin1;
    }

    public void setPoin1(String poin1) {
        this.poin1 = poin1;
    }
    public String getPoin2() {
        return poin2;
    }

    public void setPoin2(String poin2) {
        this.poin2 = poin2;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}

