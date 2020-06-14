package com.example.homechef.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApi {

    @GET("/api/json/v1/1/search.php")
    Call<MealResponse> getMeal(@Query("s") String id);
    @GET("/api/json/v1/1/random.php")
    Call<MealResponse> getRandomMeal();
    @GET("/api/json/v1/1/filter.php")
    Call<MealResponse> getMealByCategory(@Query("c") String id);
   /*
    @GET("/api/json/v1/1/categories.php")
    Call<CategoryResponse> getAllCategories();

    */
}