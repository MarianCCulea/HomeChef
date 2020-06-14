package com.example.homechef.resource;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class History {

    public String date;
    public int idMeal;
    public String strMeal;
    public String strMealThumb;

    public History(String date, int idMeal, String strMeal, String strMealThumb) {
        this.date = date;
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
    }

    public History() {
        // Default constructor required for calls to DataSnapshot.getValue(History.class)
    }


    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
