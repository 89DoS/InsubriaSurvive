package com.example.surviveinsubria;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Orari extends Fragment {
    String spinnervalue;
    String spinnervalue1;

    public Orari(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_orari, container, false);
        final Spinner spinner = rootView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.Facoltà, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, true);
        View v = spinner.getSelectedView();
        ((TextView)v).setTextColor(Color.argb(255, 0, 87, 0));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnervalue = spinner.getSelectedItem().toString();
                ((TextView) view).setTextColor(Color.argb(255, 0, 87, 0 ));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Spinner spinner1 = rootView.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource (this.getContext(), R.array.Anno, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(0, true);
        View v1 = spinner1.getSelectedView();
        ((TextView)v1).setTextColor(Color.argb(255, 0, 87, 0));
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnervalue1 = spinner1.getSelectedItem().toString();
                ((TextView) view).setTextColor(Color.argb(255, 0, 87, 0 ));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button button = (Button) rootView.findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnervalue == null || spinnervalue1 == null){
                    Toast.makeText(Orari.this.getContext(), "seleziona la facoltà e l'anno", Toast.LENGTH_LONG).show();
                    return;
                }
                if (spinnervalue.equals("Informatica") && spinnervalue1.equals("Primo")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://unins.prod.up.cineca.it/calendarioPubblico/contesto=DIPARTIMENTO%2520DI%2520SCIENZE%2520TEORICHE%2520E%2520APPLICATE&titolo=F004%2520INFO%2520I%2520ANNO%2520I%2520SEMESTRE%252017%252F09%252F2018-21%252F12%252F2018&corsi=F004&anniCorso=1&mostraImpegniAnnullati=true&giorniNonVisualizzati=0&coloraPer=docente&lang=it"));
                    startActivity(browserIntent);
                }
                if (spinnervalue.equals("Informatica") && spinnervalue1.equals("Secondo")){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://unins.prod.up.cineca.it/calendarioPubblico/contesto=DIPARTIMENTO%2520DI%2520SCIENZE%2520TEORICHE%2520E%2520APPLICATE&titolo=F004%2520INFO%2520II%2520ANNO%2520I%2520SEMESTRE%252017%252F09%252F2018-21%252F12%252F2018&corsi=F004&anniCorso=2&mostraImpegniAnnullati=true&giorniNonVisualizzati=0&coloraPer=docente&lang=it"));
                    startActivity(browserIntent);
                }
                if (spinnervalue.equals("Informatica") && spinnervalue1.equals("Terzo")){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://unins.prod.up.cineca.it/calendarioPubblico/contesto=DIPARTIMENTO%2520DI%2520SCIENZE%2520TEORICHE%2520E%2520APPLICATE&titolo=F004%2520INFO%2520III%2520ANNO%2520I%2520SEMESTRE%252017%252F09%252F2018-21%252F12%252F2018&corsi=F004&anniCorso=3&mostraImpegniAnnullati=true&giorniNonVisualizzati=0&coloraPer=docente&lang=it"));
                    startActivity(browserIntent);
                }
                if (spinnervalue.equals("Economia") && spinnervalue1.equals("Primo")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://unins.prod.up.cineca.it/calendarioPubblico/contesto=DIPARTIMENTO%2520DI%2520ECONOMIA&titolo=CLEM%2520-%25201%25C2%25B0%2520anno&corsi=U010&percorsi=Qualsiasi&anniCorso=1&mostraImpegniAnnullati=true&giorniNonVisualizzati=0&vistaDefault=agendaWeek&coloraPer=evento&lang=it"));
                    startActivity(browserIntent);
                }
                if (spinnervalue.equals("Economia") && spinnervalue1.equals("Secondo")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://unins.prod.up.cineca.it/calendarioPubblico/contesto=DIPARTIMENTO%2520DI%2520ECONOMIA&titolo=CLEM%2520-%25202%25C2%25B0%2520anno&corsi=U010&percorsi=Qualsiasi&anniCorso=2&mostraImpegniAnnullati=true&giorniNonVisualizzati=0&vistaDefault=agendaWeek&coloraPer=evento&lang=it"));
                    startActivity(browserIntent);
                }
                if (spinnervalue.equals("Economia") && spinnervalue1.equals("Terzo")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://unins.prod.up.cineca.it/calendarioPubblico/contesto=DIPARTIMENTO%2520DI%2520ECONOMIA&titolo=CLEM%2520-%25203%25C2%25B0%2520anno&corsi=U010&percorsi=Qualsiasi&anniCorso=3&mostraImpegniAnnullati=true&giorniNonVisualizzati=0&vistaDefault=agendaWeek&coloraPer=evento&lang=it"));
                    startActivity(browserIntent);
                }


            }
        });
        return rootView;
    }
}
