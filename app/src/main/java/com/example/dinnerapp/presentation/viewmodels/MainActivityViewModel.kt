package com.example.dinnerapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinnerapp.presentation.epoxy.events.activityevents.MainActivityEvents
import com.example.dinnerapp.presentation.epoxy.events.eventlisteners.MainActivityEventListener
import com.example.dinnerapp.presentation.utils.NavigationEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(), MainActivityEventListener {
    private val _navigationEvents = MutableSharedFlow<NavigationEvents?>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    /**
     * Activity events.
     */
    override fun onEvent(event: MainActivityEvents) =
        viewModelScope.launch {
            when (event) {
                is MainActivityEvents.OnActivityCreate -> {
                    navigationEventHandler(event.navigationEvent)
                }
            }
        }

    // region Navigation events.
    private suspend fun navigationEventHandler(event: NavigationEvents) {
        when (event) {
            is NavigationEvents.ToMealCategories -> {
                _navigationEvents.emit(NavigationEvents.ToMealCategories)
            }

            else -> {}
        }
    }

    //endregion
}
