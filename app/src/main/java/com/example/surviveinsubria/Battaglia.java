package com.example.surviveinsubria;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.example.surviveinsubria.objects.Esame;

public class Battaglia extends Fragment {
    private static final String TAG = "Battaglia";

    private CompactCalendarView compactCalendarView;
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_battaglia, container, false);
        final TextView data = view.findViewById(R.id.mese);
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ITALIAN);
        String date = formatter.format(today);
        data.setText(date);

        compactCalendarView = view.findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendarView.setLocale(TimeZone.getTimeZone(ZoneId.systemDefault()), Locale.ITALIAN);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        // getAppelliUtente()

        ArrayList<Esame> esami = new ArrayList<>();
        esami.add(new Esame("Informatica", "Algoritmi", LocalDateTime.parse("15/10/2019 - 12:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), Color.RED));
        esami.add(new Esame("Informatica", "Analisi", LocalDateTime.parse("15/10/2019 - 15:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), Color.GREEN));
        esami.add(new Esame("Informatica", "Android", LocalDateTime.parse("19/10/2019 - 12:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), Color.RED));
        esami.add(new Esame("Informatica", "Algebra", LocalDateTime.parse("18/10/2019 - 12:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), Color.YELLOW));
        esami.add(new Esame("Informatica", "Architettura", LocalDateTime.parse("17/10/2019 - 12:00", DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))));

        for (Esame esame : esami) {
            addAppello(esame);
        }


        List<Event> events = compactCalendarView.getEvents(1571748353000L); // can also take a Date object


        Log.d(TAG, "Events: " + events);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Log.d(getTag(), "Day was clicked: " + dateClicked + " with events " + events);
                StringBuilder appelli = new StringBuilder();
                for (Event appello : events) {
                    Esame esame = (Esame) appello.getData();
                    assert esame != null;
                    String text = esame.getDatetime().getHour() + ":" + esame.getDatetime();
                    text += " --> " + esame.getCorso();
                    appelli.append(text).append("\n\n");
                }

                final TextView data = view.findViewById(R.id.dati_giorno);
                data.setText(appelli);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                data.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
        return view;
    }

    public void addAppello(Esame esame) {
        Event evento = new Event(
                esame.getColore(),
                esame.getDatetime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                esame
        );
        compactCalendarView.addEvent(evento);
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}

