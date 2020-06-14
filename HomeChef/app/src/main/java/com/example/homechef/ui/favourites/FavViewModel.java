package com.example.homechef.ui.favourites;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.homechef.MealRepository;
import com.example.homechef.localstorage.Favourite;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavViewModel extends AndroidViewModel {
    private  MealRepository repository;
    private LiveData<List<Favourite>> allfavs;
    public FavViewModel(@NotNull Application application){
        super(application);
        repository=new MealRepository(application);
        allfavs=repository.getAllFav();
    }

    public void delete(Favourite fav){
        repository.delete(fav);
    }

    public void deleteAll(){
        repository.deleteAllFav();
    }

    public LiveData<List<Favourite>> getAllfavs(){
        return allfavs;
    }

    public void init() {
        allfavs=repository.getAllFav();
    }
}
