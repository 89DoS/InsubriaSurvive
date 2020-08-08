package com.example.surviveinsubria;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.surviveinsubria.objects.Esame;
import com.example.surviveinsubria.objects.ObjectDatabase;
import com.example.surviveinsubria.objects.Persona;
import com.example.surviveinsubria.utils.CallbackListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String TAG = "Firebase";
    private static final String tabellaUtenti = "users";
    private static final String tabellaEsami = "exams";
    private FirebaseFirestore database;


    public DatabaseHelper (){
        database = FirebaseFirestore.getInstance();
    }

    private void add(final ObjectDatabase object, String table, String pk) {
        database.collection(table).document(pk).set(object.toDatabase())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, object.getClass().getSimpleName() + " successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void getAllExams(final CallbackListener callback) {
        database.collection(tabellaEsami).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ObjectDatabase> objects = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                objects.add(new Esame().fromDatabase(document.getData()));
                            }
                            callback.onSuccess(objects);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private ObjectDatabase get(String table, String pk) {
        return null;
    }


    public void addUser(Persona p) {
        add(p, tabellaUtenti, p.getPrimaryKey());
    }

    public void addExam(Esame e) {
        add(e, tabellaEsami, e.getPrimaryKey());
    }
}
