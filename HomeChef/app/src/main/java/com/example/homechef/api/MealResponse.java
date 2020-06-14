package com.example.homechef.api;
import com.example.homechef.resource.Meal;

import java.util.ArrayList;

public class MealResponse {
    private ArrayList<Meal> meals;

    public Meal getMeal(){
        return meals.get(0);
    }
    public ArrayList<Meal> getMeals() {
        return meals;
    }
}
