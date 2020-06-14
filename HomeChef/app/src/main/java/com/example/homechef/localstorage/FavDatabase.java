package com.example.homechef.localstorage;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Favourite.class,version = 1,exportSchema = false)
public abstract class FavDatabase extends RoomDatabase {
    private static FavDatabase instance;

    public abstract FavDao favDao();

    public static synchronized FavDatabase getInstance(Context context){
    if(instance==null) instance= Room.databaseBuilder(context.getApplicationContext(),
            FavDatabase.class,"fav_database")
            .fallbackToDestructiveMigration().build();
    return instance;
    }

    //callback for populating
    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private FavDao favDao;
        private PopulateDbAsyncTask(FavDatabase favDatabase){
            favDao=favDatabase.favDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favDao.insert(new Favourite(342,"none","Meal"));
            return null;
        }
    }
}
