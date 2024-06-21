package com.example.dinnerapp.presentation.epoxy.controllers

import com.airbnb.epoxy.TypedEpoxyController
import com.example.dinnerapp.network.domains.MealCategory
import com.example.dinnerapp.presentation.epoxy.components.error
import com.example.dinnerapp.presentation.epoxy.components.loading
import com.example.dinnerapp.presentation.epoxy.components.mealCategory
import com.example.dinnerapp.presentation.epoxy.events.eventlisteners.MealCategoriesComponentEventListener
import com.example.dinnerapp.presentation.utils.ItemState
import com.example.dinnerapp.presentation.utils.ItemState.ItemNotSelected
import com.example.dinnerapp.presentation.utils.ListState
import com.example.dinnerapp.presentation.utils.ListState.Error
import com.example.dinnerapp.presentation.utils.ListState.Loaded
import com.example.dinnerapp.presentation.utils.ListState.Loading
import com.example.dinnerapp.utils.Constants

class MealCategoriesEpoxyController(
    private val listener: MealCategoriesComponentEventListener? = null,
    private var loadState: ListState<*> = Loading(Constants.StateConstants.LOADING),
) : TypedEpoxyController<List<ItemState<MealCategory?>?>?>() {
    override fun buildModels(data: List<ItemState<MealCategory?>?>?) {
        if (data == null) {
            return
        }
        when (val currentState = loadState) {
            is Loading -> buildLoadingState(currentState)
            is Error -> buildErrorState(currentState)
            is Loaded -> buildLoadedState(data)
        }
    }

    // region building list State.
    private fun buildLoadingState(loadingState: Loading) {
        loading {
            id(loadingState.message)
            message(loadingState.message)
        }
    }

    private fun buildErrorState(errorState: Error) {
        error {
            id(errorState.message)
            message(errorState.message)
        }
    }

    private fun buildLoadedState(data: List<ItemState<MealCategory?>?>) {
        data.forEach {
            mealCategory {
                id(it?.id)
                itemState(it)
                listener(this@MealCategoriesEpoxyController.listener)
            }
        }
    }

    //endregion

    // region updating list State.
    fun setLoadedState(loadState: Loaded<List<ItemNotSelected<MealCategory?>?>?>?) {
        loadState?.let {
            this.loadState = loadState
            val loadedData = loadState.itemState
            setData(loadedData)
        }
    }

    fun setErrorState(loadedState: Error?) {
        loadedState?.let {
            this.loadState = loadedState
            setData(emptyList())
        }
    }

    fun setLoadingState(loadedState: Loading?) {
        loadedState?.let {
            this.loadState = loadedState
            setData(emptyList())
        }
    }

    //endregion
}
