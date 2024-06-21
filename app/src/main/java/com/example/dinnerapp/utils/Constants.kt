package com.example.dinnerapp.utils

object Constants {
    object MealConstants {
        const val BASE_URL = "https://www.themealdb.com/"
        const val MEAL_CATEGORIES_URL = "api/json/v1/1/categories.php"
    }

    object StateConstants {
        const val LOADING = "loading.."
        const val ERROR = "error"
        const val LOADED = "loaded"
    }
}
