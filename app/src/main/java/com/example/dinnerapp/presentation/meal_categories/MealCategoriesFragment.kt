package com.example.dinnerapp.presentation.meal_categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.dinnerapp.databinding.FragmentMealCategoriesBinding
import com.example.dinnerapp.presentation.epoxy.controllers.MealCategoriesEpoxyController
import com.example.dinnerapp.presentation.events.fragmentevents.MealCategoriesFragmentEvents
import com.example.dinnerapp.utils.BaseFragment
import com.example.dinnerapp.presentation.utils.ListState
import com.example.dinnerapp.utils.Constants
import com.example.dinnerapp.utils.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealCategoriesFragment : BaseFragment<FragmentMealCategoriesBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentMealCategoriesBinding
        get() = FragmentMealCategoriesBinding::inflate

    private val viewModel: MealCategoriesViewModel by viewModels()
    private lateinit var controller: MealCategoriesEpoxyController

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        // Setting up the controller here in order to listen to events through the viewmodel.
        controller = MealCategoriesEpoxyController(listener = viewModel)

        launchAndRepeatWithViewLifecycle {
            launch { observeMealCategories() }
            launch { observeMealCategoriesOnFailure() }
            launch { observeNavigationEvents() }
            launch { initializeUI() }
            launch { getMealCategories() }
        }
    }

    // region observables .
    private suspend fun observeMealCategories() {
        viewModel.mealListSuccessFlow.collect {
            controller.setLoadedState(it)
        }
    }

    private suspend fun observeMealCategoriesOnFailure() {
        viewModel.mealListErrorFlow.collect {
            controller.setErrorState(ListState.Error(Constants.StateConstants.ERROR))
        }
    }

    private suspend fun observeNavigationEvents() {
        viewModel.navigationEvents.collect {
        }
    }

    //endregion

    // region Fragment Events

    private fun initializeUI() {
        binding.epoxyRv.setController(controller)
        controller.setLoadingState(ListState.Loading(Constants.StateConstants.LOADING))
    }

    private fun getMealCategories() {
        viewModel.onEvent(MealCategoriesFragmentEvents.GetMealCategories)
    }

    //endregion
}
