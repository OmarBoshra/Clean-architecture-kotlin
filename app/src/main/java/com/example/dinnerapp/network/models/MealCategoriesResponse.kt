package com.example.dinnerapp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealCategoriesResponse(
    @SerialName("categories") val mealCategoriesList: List<MealCategoryResponse>,
)

@Serializable
data class MealCategoryResponse(
    @SerialName("idCategory") val idMealCategory: String? = "",
    @SerialName("strCategory") val mealCategoryName: String? = "",
    @SerialName("strCategoryThumb") val mealCategoryUrl: String? = "",
    @SerialName("strCategoryDescription") val mealCategoryDescription: String? = "",
)
