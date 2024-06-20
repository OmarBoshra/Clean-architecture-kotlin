package com.example.dinnerapp.presentation.epoxy.events.activityevents

import com.example.dinnerapp.presentation.utils.NavigationEvents

sealed class MainActivityEvents {
    class OnActivityCreate(val navigationEvent: NavigationEvents) :
        MainActivityEvents()
}
