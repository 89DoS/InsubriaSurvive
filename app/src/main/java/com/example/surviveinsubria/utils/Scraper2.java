package com.example.surviveinsubria.utils;

import com.example.surviveinsubria.DatabaseHelper;
import com.example.surviveinsubria.objects.Esame;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper2 {
    private String url;

    public Scraper2(String url) {
        this.url = url;
    }

    public Document get() throws IOException {
        return Jsoup.connect(url).get();
    }

    public Document post(Map<String, String> data) throws IOException {
        return Jsoup.connect(url).data(data).post();
    }

    public static void test(String[] args) throws IOException {
        //Scraper2 scraper2 = new Scraper2("https://uninsubria.esse3.cineca.it/ListaTurniFacolta.do;jsessionid=9171740819EFEC6888BB13B43A25058C.esse3-uninsubria-prod-02'");
        Scraper2 scraper2 = new Scraper2("https://uninsubria.esse3.cineca.it/ListaAppelliOfferta.do;jsessionid=9126A5F94A2BB5D1B66D77210D0000CD.esse3-uninsubria-prod-02");
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
        Document doc = scraper2.post(data);
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
                new DatabaseHelper().addExam(e);
            }
        }
    }
}
