package com.example.homechef.ui.shopping;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.homechef.MealRepository;
import com.example.homechef.localstorage.Favourite;

public class ShoppingViewModel extends AndroidViewModel {

    private MealRepository repository;

    public ShoppingViewModel(@NonNull Application application) {
        super(application);
        repository = MealRepository.getInstance(application);

    }

    public void insert(Favourite fav){
        repository.insert(fav);
    }
}
