package com.example.dinnerapp.presentation.Activities

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.dinnerapp.R
import com.example.dinnerapp.databinding.ActivityMainBinding
import com.example.dinnerapp.presentation.events.activityevents.MainActivityEvents
import com.example.dinnerapp.presentation.utils.NavigationEvents
import com.example.dinnerapp.utils.BaseActivity
import com.example.dinnerapp.utils.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private val viewModel: MainActivityViewModel by viewModels()

    override fun setup() {
        launchAndRepeatWithViewLifecycle {
            launch { observeNavigationEvents() }
            launch { toMealCategories() }
        }
    }

    // region activity events .
    private fun toMealCategories() {
        viewModel.onEvent(MainActivityEvents.ToMealCategories(NavigationEvents.ToMealCategories))
    }

    //endregion

    // region activity observers .
    private suspend fun observeNavigationEvents() {
        viewModel.navigationEvents.collect {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.main_content) as NavHostFragment
            val navController = navHostFragment.navController
            navController
                .setGraph(R.navigation.nav_graph)
        }
    }

    //endregion
}
