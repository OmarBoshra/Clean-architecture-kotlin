package com.example.dinnerapp.presentation.epoxy.events.eventlisteners

import com.example.dinnerapp.presentation.epoxy.events.activityevents.MainActivityEvents
import kotlinx.coroutines.Job

interface MainActivityEventListener {
    fun onEvent(event: MainActivityEvents): Job
}
