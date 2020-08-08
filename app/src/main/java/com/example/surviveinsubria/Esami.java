package com.example.surviveinsubria;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.example.surviveinsubria.esami.EsameAdapter;
import com.example.surviveinsubria.objects.Esame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Esami extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private EsameAdapter adapter;
    private ArrayList<Esame> esami;

    public Esami(){

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_esami, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        esami = new ArrayList<>();
        esami.add(new Esame("Informatica", "Algoritmi", LocalDateTime.parse("15/10/2019 - 12:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), Color.RED));
        esami.add(new Esame("Informatica", "Analisi", LocalDateTime.parse("15/10/2019 - 15:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), Color.GREEN));
        esami.add(new Esame("Informatica", "Android", LocalDateTime.parse("19/10/2019 - 12:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), Color.RED));
        esami.add(new Esame("Informatica", "Algebra", LocalDateTime.parse("18/10/2019 - 12:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), Color.YELLOW));
        esami.add(new Esame("Informatica", "Architettura", LocalDateTime.parse("17/10/2019 - 12:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))));

        // specify an adapter (see also next example)
        adapter = new EsameAdapter(esami.toArray(new Esame[0]));
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
    }

}
