package com.example.homechef;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homechef.api.MealApi;
import com.example.homechef.api.MealResponse;
import com.example.homechef.api.ServiceGenerator;
import com.example.homechef.localstorage.FavDao;
import com.example.homechef.localstorage.FavDatabase;
import com.example.homechef.localstorage.Favourite;
import com.example.homechef.resource.Meal;
import com.google.android.gms.common.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRepository {
    private FavDao favDao;
    private LiveData<List<Favourite>> favs;
    private MutableLiveData<List<Meal>> meals;
    private MutableLiveData<Meal> randomMeal;
    private static MealRepository instance;


    public MealRepository(Application app){
        FavDatabase favDatabase=FavDatabase.getInstance(app);
        favDao=favDatabase.favDao();
        favs=favDao.getAllFav();
        meals = new MutableLiveData<>();
        randomMeal=new MutableLiveData<>();
    }

   // private MealRepository() { meals = new MutableLiveData<>(); }

    public static synchronized MealRepository getInstance(Application app) {
        if (instance == null) {
            instance = new MealRepository(app);
        }
        return instance;
    }

    public LiveData<List<Meal>> getMeal() {
        return meals;
    }

    public LiveData<Meal> fetchRandomMeal() {
        return randomMeal;
    }

    public void getRandomMeal() {
        MealApi api = ServiceGenerator.getMealApi();
        Call<MealResponse> call = api.getRandomMeal();

        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.code() == 200) {
                    randomMeal.setValue(response.body().getMeal());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i("API", "Something went wrong :(");
            }
        });

    }

    public void getMealsByString(String id) {
        MealApi api = ServiceGenerator.getMealApi();
        Call<MealResponse> call = api.getMeal(id);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.code() == 200) {
                    meals.setValue(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i("API", "Something went wrong :(");
            }
        });
    }

    public void insert(Favourite fav){
        new InsertFavAsyncTask(favDao).execute(fav);
    }

    public void delete(Favourite fav){
        new DeleteFavAsyncTask(favDao).execute(fav);
    }

    public void deleteAllFav(){
        new DeleteAllFavAsyncTask(favDao).execute();
    }

    public LiveData<List<Favourite>> getAllFav(){
        return favs;
    }

    private static class InsertFavAsyncTask extends AsyncTask<Favourite,Void,Void>{

        private FavDao favDao;

        private InsertFavAsyncTask(FavDao favDao){
            this.favDao=favDao;
        }

        @Override
        protected Void doInBackground(Favourite... favourites) {
            favDao.insert(favourites[0]);
            return null;
        }
    }

    private static class DeleteFavAsyncTask extends AsyncTask<Favourite,Void,Void>{

        private FavDao favDao;

        private DeleteFavAsyncTask(FavDao favDao){
            this.favDao=favDao;
        }

        @Override
        protected Void doInBackground(Favourite... favourites) {
            favDao.delete(favourites[0]);
            return null;
        }
    }

    private static class DeleteAllFavAsyncTask extends AsyncTask<Void,Void,Void>{

        private FavDao favDao;

        private DeleteAllFavAsyncTask(FavDao favDao){
            this.favDao=favDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            favDao.deleteAllFav();
            return null;
        }
    }


}
