package com.example.dinnerapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinnerapp.network.domains.MealCategory
import com.example.dinnerapp.network.usecases.GetMealCategoriesUsecase
import com.example.dinnerapp.network.utils.Result
import com.example.dinnerapp.presentation.epoxy.events.componentevents.MealCategoriesComponentEvents
import com.example.dinnerapp.presentation.epoxy.events.eventlisteners.ComponentEventListener
import com.example.dinnerapp.presentation.epoxy.events.eventlisteners.MealCategoriesFragmentEventListener
import com.example.dinnerapp.presentation.epoxy.events.fragmentevents.MealCategoriesFragmentEvents
import com.example.dinnerapp.presentation.utils.ItemState.ItemNotSelected
import com.example.dinnerapp.presentation.utils.ListState.Loaded
import com.example.dinnerapp.presentation.utils.NavigationEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealCategoriesViewModel
    @Inject
    constructor(
        private val getMealCategoriesUsecase: GetMealCategoriesUsecase,
    ) : ViewModel(), MealCategoriesFragmentEventListener, ComponentEventListener {
        private val _navigationEvents = MutableSharedFlow<NavigationEvents?>()
        val navigationEvents = _navigationEvents.asSharedFlow()

        private val _mealListSuccess =
            MutableStateFlow<Loaded<List<ItemNotSelected<MealCategory?>?>?>?>(null)
        val mealListSuccessFlow = _mealListSuccess.asStateFlow()

        private val _mealListError = MutableStateFlow<String?>(null)
        val mealListErrorFlow = _mealListError.asStateFlow()

        /**
         * Fragment events.
         */
        override fun onEvent(event: MealCategoriesFragmentEvents) =
            viewModelScope.launch {
                when (event) {
                    is MealCategoriesFragmentEvents.OnFragmentCreate -> {
                        fragmentEventsHandler(event)
                    }
                }
            }

        /**
         * Component events.
         */
        override fun onEvent(event: MealCategoriesComponentEvents) =
            viewModelScope.launch {
                when (event) {
                    is MealCategoriesComponentEvents.OnClickMealCategory -> {
                        navigationEventsHandler(event.navigationEvent)
                    }
                }
            }

        // region event handlers
        private suspend fun navigationEventsHandler(event: NavigationEvents) {
            when (event) {
                is NavigationEvents.ToDrinkCategories -> {
                    _navigationEvents.emit(NavigationEvents.ToDrinkCategories)
                }

                NavigationEvents.ToDinner -> TODO()
                NavigationEvents.ToMealCategories -> TODO()
            }
        }

        private suspend fun fragmentEventsHandler(event: MealCategoriesFragmentEvents) {
            when (event) {
                is MealCategoriesFragmentEvents.OnFragmentCreate -> {
                    getMealCategories()
                }
            }
        }

        //endregion

        // region API calls
        private suspend fun getMealCategories() {
            val moviesResult = getMealCategoriesUsecase.invoke()
            sendMealCategoriesResult(moviesResult)
        }

        private suspend fun sendMealCategoriesResult(result: Result<List<MealCategory?>?>) {
            when (result) {
                is Result.Success -> {
                    val mealCategories: List<MealCategory?> = result.data ?: emptyList()
                    val loadedItems = createLoadedState(mealCategories)

                    _mealListSuccess.emit(loadedItems)
                }

                is Result.Error -> {
                    _mealListError.emit(result.errorMessage)
                }
            }
        }

        //endregion

        // region state creation

        private fun createLoadedState(mealCategories: List<MealCategory?>): Loaded<List<ItemNotSelected<MealCategory?>?>?> {
            val items: List<ItemNotSelected<MealCategory?>?> =
                mutableListOf<ItemNotSelected<MealCategory?>>().apply {
                    mealCategories.forEach { mealCategory ->
                        mealCategory?.let {
                            add(
                                ItemNotSelected(
                                    it,
                                    it.idMealCategory,
                                ),
                            )
                        }
                    }
                }

            return Loaded(items)
        }

        //endregion
    }
