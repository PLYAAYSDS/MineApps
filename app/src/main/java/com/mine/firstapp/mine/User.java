package com.mine.firstapp.mine;

public class User {
    private String uId;
    private String email;
    private String nama;
    private String alamat;
    private String noHP;
    private String photo;

    public User(){
    }

    public User(String uId, String email, String nama, String alamat, String noHP, String photo) {
        this.uId = uId;
        this.email = email;
        this.nama = nama;
        this.alamat = alamat;
        this.noHP = noHP;
        this.photo = photo;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
