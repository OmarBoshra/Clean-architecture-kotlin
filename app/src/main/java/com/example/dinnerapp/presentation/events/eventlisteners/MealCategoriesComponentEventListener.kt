package com.example.dinnerapp.presentation.events.eventlisteners

import com.example.dinnerapp.presentation.events.componentevents.MealCategoriesComponentEvents
import kotlinx.coroutines.Job

interface MealCategoriesComponentEventListener {
    fun onEvent(event: MealCategoriesComponentEvents)
}
