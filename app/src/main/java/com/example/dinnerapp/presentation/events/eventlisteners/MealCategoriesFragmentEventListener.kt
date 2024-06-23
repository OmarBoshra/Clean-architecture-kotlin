package com.example.dinnerapp.presentation.events.eventlisteners

import com.example.dinnerapp.presentation.events.fragmentevents.MealCategoriesFragmentEvents
import kotlinx.coroutines.Job

interface MealCategoriesFragmentEventListener {
    fun onEvent(event: MealCategoriesFragmentEvents)
}
