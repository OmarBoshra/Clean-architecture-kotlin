package com.example.dinnerapp.presentation.epoxy.events.eventlisteners

import com.example.dinnerapp.presentation.epoxy.events.fragmentevents.MealCategoriesFragmentEvents
import kotlinx.coroutines.Job

interface MealCategoriesFragmentEventListener {
    fun onEvent(event: MealCategoriesFragmentEvents): Job
}
