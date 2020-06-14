package com.example.homechef.ui.random;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homechef.MealRepository;
import com.example.homechef.resource.Meal;

public class RandomViewModel extends AndroidViewModel {

    private LiveData<Meal> randomMeal;
    private MealRepository repository;

    public RandomViewModel(Application application) {
        super(application);
        repository=repository.getInstance(application);
    }

    public LiveData<Meal> getRandomMeal() {
        return randomMeal;
    }

    public void updateMeal() {
        repository.getRandomMeal();
    }

    public void init() {
        randomMeal=repository.fetchRandomMeal();
    }
}