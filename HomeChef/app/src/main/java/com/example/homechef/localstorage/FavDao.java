package com.example.homechef.localstorage;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavDao {

    @Insert
    void insert(Favourite fav);

    @Delete
    void delete(Favourite fav);


    @Query("Delete from favourite_table")
    void deleteAllFav();

    @Query("Select * from favourite_table Order By name asc")
    LiveData<List<Favourite>> getAllFav();

}
