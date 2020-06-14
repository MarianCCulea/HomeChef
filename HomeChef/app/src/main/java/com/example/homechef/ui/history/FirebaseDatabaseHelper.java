package com.example.homechef.ui.history;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.homechef.resource.History;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private List<History> historyList = new ArrayList<>();

    public interface DataStatus {
        void dataIsLoaded(List<History> histories, List<String> strings);
        void dataIsInserted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("History");
    }

    public void getHistory(final DataStatus dataStatus) {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                historyList.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    History history=new History();
                    history.setDate(ds.getValue(History.class).getDate());
                    history.setIdMeal(ds.getValue(History.class).getIdMeal());
                    history.setStrMeal(ds.getValue(History.class).getStrMeal());
                    history.setStrMealThumb(ds.getValue(History.class).getStrMealThumb());


                    Log.i("Database", "MUEI---"+ history);
                    historyList.add(history);
                }
                dataStatus.dataIsLoaded(historyList, keys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
