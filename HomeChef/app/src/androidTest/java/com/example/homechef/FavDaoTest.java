package com.example.homechef;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.homechef.localstorage.FavDao;
import com.example.homechef.localstorage.FavDatabase;
import com.example.homechef.localstorage.Favourite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class FavDaoTest {
    private FavDao dao;
    private FavDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FavDatabase.class).build();
        dao = db.favDao();
    }

    @Test
    public void insert() throws Exception{
        Favourite fav= new Favourite(12,"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/wxywrq1468235067.jpg","Apple Frangipan Tart");
        db.favDao().insert(fav);
        LiveData<List<Favourite>> byName = dao.getAllFav();
        assertEquals(byName.getValue().get(0).getId(), fav.getId());
    }

    @Test
    public void insert2() throws Exception{
        Favourite fav= new Favourite(12,"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/wxywrq1468235067.jpg","Apple Frangipan Tart");
        Favourite fav2= new Favourite(32,"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/wxywrq1468235067.jpg","Osso Buco alla Milanese");
        List<Favourite> favs=new ArrayList<>();
        favs.add(fav);
        favs.add(fav2);
        db.favDao().insert(fav);
        db.favDao().insert(fav2);
        LiveData<List<Favourite>> byName = dao.getAllFav();
        assertThat(byName.getValue(), equalTo(favs));
    }

    @Test
    public void delete() throws Exception{
        Favourite fav= new Favourite(12,"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/wxywrq1468235067.jpg","Apple Frangipan Tart");
        Favourite fav2= new Favourite(32,"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/wxywrq1468235067.jpg","Osso Buco alla Milanese");
        List<Favourite> favs=new ArrayList<>();
        favs.add(fav);
        favs.add(fav2);
        db.favDao().insert(fav);
        db.favDao().insert(fav2);
        dao.deleteAllFav();
        LiveData<List<Favourite>> byName = dao.getAllFav();
        assertNull(byName.getValue().get(0));
    }


    @After
    void close(){
        db.close();
    }


}
