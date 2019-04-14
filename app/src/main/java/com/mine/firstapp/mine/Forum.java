package com.mine.firstapp.mine;

public class Forum {
    private String profilId;
    private String topik;
    private String pertanyaan;
    private String key;

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    private String jawaban;

    public Forum() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProfilId() {
        return profilId;
    }

    public void setProfilId(String profilId) {
        this.profilId = profilId;
    }

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

}
