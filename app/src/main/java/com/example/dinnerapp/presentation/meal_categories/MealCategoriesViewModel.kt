package com.example.dinnerapp.presentation.meal_categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinnerapp.domains.models.MealCategory
import com.example.dinnerapp.domains.usecases.GetMealCategoriesUsecase
import com.example.dinnerapp.network.utils.Result
import com.example.dinnerapp.presentation.events.componentevents.MealCategoriesComponentEvents
import com.example.dinnerapp.presentation.events.eventlisteners.MealCategoriesComponentEventListener
import com.example.dinnerapp.presentation.events.eventlisteners.MealCategoriesFragmentEventListener
import com.example.dinnerapp.presentation.events.fragmentevents.MealCategoriesFragmentEvents
import com.example.dinnerapp.presentation.utils.ItemState.ItemNotSelected
import com.example.dinnerapp.presentation.utils.ListState.Loaded
import com.example.dinnerapp.presentation.utils.NavigationEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealCategoriesViewModel
    @Inject
    constructor(
        private val getMealCategoriesUsecase: GetMealCategoriesUsecase,
    ) : ViewModel(), MealCategoriesFragmentEventListener, MealCategoriesComponentEventListener {
        private val _navigationEvents = MutableSharedFlow<NavigationEvents?>()
        val navigationEvents = _navigationEvents.asSharedFlow()

        private val _mealListSuccess =
            MutableSharedFlow<Loaded<List<ItemNotSelected<MealCategory?>?>?>?>()
        val mealListSuccessFlow = _mealListSuccess.asSharedFlow()

        private val _mealListError = MutableSharedFlow<String?>()
        val mealListErrorFlow = _mealListError.asSharedFlow()

        /**
         * Fragment events.
         */
        override fun onEvent(event: MealCategoriesFragmentEvents) {
            fragmentEventsHandler(event)
        }

        /**
         * Component events.
         */
        override fun onEvent(event: MealCategoriesComponentEvents) {
            componentEventsHandler(event)
        }

        // region event handlers
        private fun navigationEventsHandler(event: NavigationEvents) =
            viewModelScope.launch {
                when (event) {
                    is NavigationEvents.ToDrinkCategories -> {
                        _navigationEvents.emit(NavigationEvents.ToDrinkCategories)
                    }

                    NavigationEvents.ToDinner -> TODO()
                    NavigationEvents.ToMealCategories -> TODO()
                }
            }

        private fun fragmentEventsHandler(event: MealCategoriesFragmentEvents) =
            viewModelScope.launch {
                when (event) {
                    is MealCategoriesFragmentEvents.GetMealCategories -> {
                        getMealCategories()
                    }
                }
            }

        private fun componentEventsHandler(event: MealCategoriesComponentEvents) =
            viewModelScope.launch {
                when (event) {
                    is MealCategoriesComponentEvents.OnClickMealCategory -> {
                        navigationEventsHandler(event.navigationEvent)
                    }
                }
            }

        //endregion

        // region API calls
        private suspend fun getMealCategories() {
            val mealCategoriesResult = getMealCategoriesUsecase.invoke()
            sendMealCategoriesResult(mealCategoriesResult)
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
