package com.example.homechef.ui.search;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homechef.MealRepository;
import com.example.homechef.resource.Meal;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {


    private LiveData<List<Meal>> mMeals;
    private MealRepository repository;

    public SearchViewModel(Application app) {
        super(app);
        repository = MealRepository.getInstance(app);
       }

    public void init(){
        mMeals=repository.getMeal();
    }

    public LiveData<List<Meal>> getMeal() {
        return mMeals;
    }

    public void updateMeal(String s) {
        repository.getMealsByString(s);
    }

}