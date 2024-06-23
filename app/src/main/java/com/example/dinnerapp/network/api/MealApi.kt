package com.example.dinnerapp.network.api

import com.example.dinnerapp.data.models.MealCategoriesResponse
import com.example.dinnerapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface MealApi {
    @GET(Constants.MealConstants.MEAL_CATEGORIES_URL)
    suspend fun getMealCategoriesList(): Response<MealCategoriesResponse>
}
