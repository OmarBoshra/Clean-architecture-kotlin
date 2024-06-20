package com.example.dinnerapp.presentation.utils

sealed class NavigationEvents {
    object ToMealCategories : NavigationEvents()

    object ToDrinkCategories : NavigationEvents()

    object ToDinner : NavigationEvents()
}
