package com.example.dinnerapp.presentation.events.componentevents

import com.example.dinnerapp.presentation.utils.NavigationEvents

sealed class MealCategoriesComponentEvents {
    class OnClickMealCategory(val navigationEvent: NavigationEvents) :
        MealCategoriesComponentEvents()
}
