package com.example.surviveinsubria.objects;

import android.graphics.Color;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Esame extends ObjectDatabase implements Comparable {
    private String corsoDiLaurea;
    private String corso;
    private LocalDateTime datetime;
    private int colore;

    public Esame() {
        super();
    }

    public Esame(String corsoDiLaurea, String corso, LocalDateTime datetime, int colore) {
        super(corso + datetime);
        this.corsoDiLaurea = corsoDiLaurea;
        this.corso = corso;
        this.datetime = datetime;
        this.colore = colore;
    }

    public Esame(String corsoDiLaurea, String corso, LocalDateTime datetime) {
        this(corsoDiLaurea, corso, datetime, Color.GRAY);
    }

    public String getCorsoDiLaurea() {
        return corsoDiLaurea;
    }

    public void setCorsoDiLaurea(String corsoDiLaurea) {
        this.corsoDiLaurea = corsoDiLaurea;
    }

    public String getCorso() {
        return corso;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getColore() {
        return colore;
    }

    public void setColore(int colore) {
        this.colore = colore;
    }

    @Override
    public String toString() {
        return "Esame [corsoDiLaurea=" + corsoDiLaurea + ", corso=" + corso + ", datetime=" + datetime + "]";
    }

    @Override
    public int compareTo(Object o) {
        Esame esame = (Esame) o;
        if (datetime.equals(esame.datetime)) return corso.compareTo(esame.corso);
        else return datetime.compareTo(esame.datetime);
    }

    @Override
    public Map<String, Object> toDatabase() {
        Map<String, Object> mappa = super.toDatabase();
        mappa.put("corsoDiLaurea", corsoDiLaurea);
        mappa.put("corso", corso);
        mappa.put("datatime", datetime.toString());

        return mappa;
    }

    @Override
    public ObjectDatabase fromDatabase(Map<String, Object> object) {
        setPrimaryKey((String) object.get("id"));
        setCorsoDiLaurea((String) object.get("corsoDiLaurea"));
        setCorso((String) object.get("corso"));
        setDatetime(LocalDateTime.parse((String) object.get("datatime"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));

        return this;
    }
}
