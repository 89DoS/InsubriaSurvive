package com.example.surviveinsubria.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Persona extends ObjectDatabase{
    private String email;
    private String nome;
    private String cognome;
    private List<Esame> pianoDiStudi;

    public Persona() {

    }

    public Persona(String id, String email, String nome, String cognome) {
        super(id);
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.pianoDiStudi = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public List<Esame> getPianoDiStudi() {
        return pianoDiStudi;
    }

    public void setPianoDiStudi(List<Esame> pianoDiStudi) {
        this.pianoDiStudi = pianoDiStudi;
    }

    public void addEsame(Esame e) {
        pianoDiStudi.add(e);
    }

    @Override
    public Map<String, Object> toDatabase() {
        Map<String, Object> mappa = super.toDatabase();
        mappa.put("email", email);
        mappa.put("nome", nome);
        mappa.put("cognome", cognome);
        mappa.put("pianoDiStudi", pianoDiStudi.stream().map(new Function<Esame, Object>() {
            @Override
            public Object apply(Esame esame) {
                return esame.toDatabase();
            }
        }).collect(Collectors.toList()));

        return mappa;
    }

    @Override
    public ObjectDatabase fromDatabase(Map<String, Object> object) {
        return null;
    }
}