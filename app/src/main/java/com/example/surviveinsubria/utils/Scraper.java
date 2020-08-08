package com.example.surviveinsubria.utils;

import android.os.AsyncTask;

import com.example.surviveinsubria.DatabaseHelper;
import com.example.surviveinsubria.objects.Esame;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class Scraper extends AsyncTask<Void, Void, List<Esame>> {


    protected List<Esame> doInBackground(Void... voids) {
        String url = "https://uninsubria.esse3.cineca.it/ListaAppelliOfferta.do;jsessionid=9126A5F94A2BB5D1B66D77210D0000CD.esse3-uninsubria-prod-02";
        HashMap<String, String> data = new HashMap<>();
        data.put("fac_id", "10022");
        data.put("cds_id", "10104");
        data.put("btnSubmit", "Avvia Ricerca");
        /*
         * si possono prendere con un'altra richiesta eventualmente
         * 10019 -> [D01] DIPARTIMENTO DI BIOTECNOLOGIE E SCIENZE DELLA VITA (DBSV)
         * 10023 -> [D05] DIPARTIMENTO DI DIRITTO, ECONOMIA E CULTURE
         * 10018 -> [D00] DIPARTIMENTO DI ECONOMIA
         * 10021 -> [D03] DIPARTIMENTO DI MEDICINA CLINICA E SPERIMENTALE
         * 10027 -> [D08] DIPARTIMENTO DI MEDICINA E CHIRURGIA
         * 10024 -> [D06] DIPARTIMENTO DI SCIENZA E ALTA TECNOLOGIA
         * 10020 -> [D02] DIPARTIMENTO DI SCIENZE CHIRURGICHE E MORFOLOGICHE
         * 10022 -> [D04] DIPARTIMENTO DI SCIENZE TEORICHE E APPLICATE
         * 10028 -> [D09] DIPARTIMENTO DI SCIENZE UMANE E DELL’INNOVAZIONE PER IL TERRITORIO
         * 10026 -> [D07] SCUOLA DI MEDICINA
         */
        Document doc = null;
        try {
            doc = Jsoup.connect(url).data(data).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Esame> esami = new ArrayList<>();
        System.out.println(doc.title());
        for (Element riga : doc.select("tr")) {
            Elements colonne = riga.select("td");
            if (colonne.size() > 0 && colonne.get(0).text().startsWith("[")) {
                String corso = colonne.get(0).text();
                LocalDateTime dataOra;
                String[] params = colonne.get(2).text().split("-");
                if (params.length == 2 && !params[1].isEmpty()) {
                    dataOra = LocalDateTime.parse(colonne.get(2).text(), DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"));
                } else {
                    dataOra = LocalDate.parse(params[0].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
                }
                Esame e = new Esame("[F004] INFORMATICA", corso, dataOra);
                System.out.println(e);
                esami.add(e);
            }
        }

        return esami;
    }

    protected void onPostExecute(List<Esame> esami) {
        esami.stream().forEach(new Consumer<Esame>() {
            @Override
            public void accept(Esame esame) {
                new DatabaseHelper().addExam(esame);
            }
        });
    }
}