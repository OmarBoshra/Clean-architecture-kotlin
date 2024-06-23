package com.example.dinnerapp.presentation.events.eventlisteners

import com.example.dinnerapp.presentation.events.activityevents.MainActivityEvents
import kotlinx.coroutines.Job

interface MainActivityEventListener {
    fun onEvent(event: MainActivityEvents): Job
}
