package com.example.homechef.ui.shopping;

import android.os.Bundle;

import com.example.homechef.localstorage.Favourite;
import com.example.homechef.resource.History;
import com.example.homechef.resource.Meal;
import com.example.homechef.ui.search.SearchViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.homechef.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShoppingActivity extends AppCompatActivity {
    private static Meal CURRENT_MEAL;
    private FloatingActionButton fab;
    private ShoppingViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Toolbar toolbar = findViewById(R.id.toolbar21);
        setSupportActionBar(toolbar);

        viewModel = new ViewModelProvider(this).get(ShoppingViewModel.class);



        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.insert(new Favourite(CURRENT_MEAL.getIdMeal(),CURRENT_MEAL.getStrMealThumb(),CURRENT_MEAL.getStrMeal()));
                Snackbar.make(view, "Added to favourites!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fab.hide();
            }
        });

        CURRENT_MEAL=getIntent().getParcelableExtra("Meal");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null) {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            Date current = Calendar.getInstance().getTime();
            String formated = DateFormat.getDateInstance(DateFormat.FULL).format(current);
            History history = new History(CURRENT_MEAL.getIdMeal(), CURRENT_MEAL.getStrMeal(), CURRENT_MEAL.getStrMealThumb(), formated);
            mDatabase.child("history").child(user.getDisplayName()).child(Math.random() + "").setValue(history).
                    addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(ShoppingActivity.this, "Added meal to history", Toast.LENGTH_SHORT).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ShoppingActivity.this, "Added meal to history FAILED!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static Meal getCurrentMeal() {
        return CURRENT_MEAL;
    }
}
