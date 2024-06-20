package com.example.dinnerapp.presentation.epoxy.events.eventlisteners

import com.example.dinnerapp.presentation.epoxy.events.componentevents.MealCategoriesComponentEvents
import kotlinx.coroutines.Job

interface MealCategoriesComponentEventListener {
    fun onEvent(event: MealCategoriesComponentEvents): Job
}
