package com.example.dinnerapp.presentation.events.activityevents

import com.example.dinnerapp.presentation.utils.NavigationEvents

sealed class MainActivityEvents {
    class ToMealCategories(val navigationEvent: NavigationEvents) :
        MainActivityEvents()
}
